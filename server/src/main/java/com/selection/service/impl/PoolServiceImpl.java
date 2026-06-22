package com.selection.service.impl;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.selection.client.ErpClient;
import com.selection.dto.PageResult;
import com.selection.dto.PoolAddDTO;
import com.selection.dto.PoolEvaluateDTO;
import com.selection.entity.SelectionPool;
import com.selection.enums.PoolSource;
import com.selection.enums.PoolStatus;
import com.selection.repository.SelectionPoolMapper;
import com.selection.service.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class PoolServiceImpl implements PoolService {

    @Autowired
    private SelectionPoolMapper poolMapper;

    @Autowired
    private ErpClient erpClient;

    @Override
    @Transactional
    public void addToPool(PoolAddDTO dto, Long operatorId) {
        SelectionPool pool = new SelectionPool();
        pool.setAsin(dto.getAsin());
        pool.setCountryCode(dto.getCountryCode());
        pool.setSource(PoolSource.valueOf(dto.getSource().toUpperCase()));
        pool.setSourceSchemeId(dto.getSchemeId());
        pool.setMarketSnapshotId(dto.getMarketSnapshotId());
        pool.setStatus(PoolStatus.PENDING);
        pool.setCreatedAt(LocalDateTime.now());
        pool.setUpdatedAt(LocalDateTime.now());
        poolMapper.insert(pool);
    }

    @Override
    @Transactional
    public void batchAddToPool(List<PoolAddDTO> dtos, Long operatorId) {
        for (PoolAddDTO dto : dtos) {
            addToPool(dto, operatorId);
        }
    }

    @Override
    public PageResult<SelectionPool> listPool(String status, int page, int pageSize) {
        QueryWrapper<SelectionPool> wrapper = new QueryWrapper<>();
        if (status != null) {
            wrapper.eq("status", status);
        }
        wrapper.orderByDesc("created_at");
        Page<SelectionPool> pageParam = new Page<>(page, pageSize);
        Page<SelectionPool> result = poolMapper.selectPage(pageParam, wrapper);
        return PageResult.of(result.getRecords(), result.getTotal(), page, pageSize);
    }

    @Override
    @Transactional
    public void evaluate(PoolEvaluateDTO dto, Long evaluatorId) {
        SelectionPool pool = poolMapper.selectById(dto.getId());
        if (pool == null) {
            throw new RuntimeException("选品池记录不存在");
        }
        // 状态流转校验: pending → evaluating → approved/rejected
        PoolStatus targetStatus = PoolStatus.valueOf(dto.getStatus().toUpperCase());
        validateStatusTransition(pool.getStatus(), targetStatus);

        pool.setStatus(targetStatus);
        pool.setEvaluatorId(evaluatorId);
        pool.setEvaluateTime(LocalDateTime.now());
        if (targetStatus == PoolStatus.REJECTED) {
            pool.setRejectReason(dto.getRejectReason());
        }
        pool.setUpdatedAt(LocalDateTime.now());
        poolMapper.updateById(pool);
    }

    @Override
    @Transactional
    public void pushToErp(Long poolId) {
        SelectionPool pool = poolMapper.selectById(poolId);
        if (pool == null) {
            throw new RuntimeException("选品池记录不存在");
        }
        if (pool.getStatus() != PoolStatus.APPROVED) {
            throw new RuntimeException("只有通过评估的商品才能推送到ERP");
        }
        Map<String, Object> erpResult = erpClient.createProductDraft(pool.getAsin(), pool.getCountryCode(), null);
        Long erpProductId = MapUtil.getLong(erpResult, "id");
        pool.setErpProductId(erpProductId);
        pool.setUpdatedAt(LocalDateTime.now());
        poolMapper.updateById(pool);
    }

    private void validateStatusTransition(PoolStatus current, PoolStatus target) {
        boolean valid = false;
        if (current == PoolStatus.PENDING && target == PoolStatus.EVALUATING) {
            valid = true;
        } else if (current == PoolStatus.EVALUATING &&
                (target == PoolStatus.APPROVED || target == PoolStatus.REJECTED)) {
            valid = true;
        }
        if (!valid) {
            throw new RuntimeException("状态流转不合法: " + current.getCode() + " → " + target.getCode());
        }
    }
}
