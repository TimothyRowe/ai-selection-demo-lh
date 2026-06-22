package com.selection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.selection.client.ErpClient;
import com.selection.entity.SelectionPool;
import com.selection.repository.SelectionPoolMapper;
import com.selection.service.DataSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class DataSyncServiceImpl implements DataSyncService {

    @Autowired
    private ErpClient erpClient;

    @Autowired
    private SelectionPoolMapper poolMapper;

    @Override
    public void syncSkuFromErp() {
        erpClient.listSkus(1, 100);
    }

    @Override
    @Transactional
    public void handleProductStatusCallback(Long erpProductId, String status) {
        QueryWrapper<SelectionPool> wrapper = new QueryWrapper<>();
        wrapper.eq("erp_product_id", erpProductId);
        SelectionPool pool = poolMapper.selectOne(wrapper);
        if (pool != null) {
            pool.setUpdatedAt(LocalDateTime.now());
            poolMapper.updateById(pool);
        }
    }
}
