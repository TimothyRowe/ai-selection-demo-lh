package com.selection.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.selection.dto.PageResult;
import com.selection.dto.SchemeCreateDTO;
import com.selection.dto.SelectionConditionDTO;
import com.selection.entity.MarketDataSnapshot;
import com.selection.entity.SelectionLog;
import com.selection.entity.SelectionScheme;
import com.selection.enums.SchemeVisibility;
import com.selection.repository.MarketDataSnapshotMapper;
import com.selection.repository.SelectionLogMapper;
import com.selection.repository.SelectionSchemeMapper;
import com.selection.service.SelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SelectionServiceImpl implements SelectionService {

    @Autowired
    private MarketDataSnapshotMapper snapshotMapper;

    @Autowired
    private SelectionSchemeMapper schemeMapper;

    @Autowired
    private SelectionLogMapper logMapper;

    @Override
    public PageResult<MarketDataSnapshot> executeSelection(SelectionConditionDTO condition, int page, int pageSize) {
        QueryWrapper<MarketDataSnapshot> wrapper = buildQueryWrapper(condition);
        Page<MarketDataSnapshot> pageParam = new Page<>(page, pageSize);
        Page<MarketDataSnapshot> result = snapshotMapper.selectPage(pageParam, wrapper);
        return PageResult.of(result.getRecords(), result.getTotal(), page, pageSize);
    }

    @Override
    @Transactional
    public SelectionScheme createScheme(SchemeCreateDTO dto, Long userId) {
        SelectionScheme scheme = new SelectionScheme();
        scheme.setName(dto.getName());
        scheme.setDescription(dto.getDescription());
        scheme.setConditionsJson(JSONUtil.toJsonStr(dto.getConditions()));
        scheme.setVisibility(SchemeVisibility.valueOf(dto.getVisibility().toUpperCase()));
        scheme.setCreatorId(userId);
        scheme.setCreatedAt(LocalDateTime.now());
        scheme.setUpdatedAt(LocalDateTime.now());
        schemeMapper.insert(scheme);
        return scheme;
    }

    @Override
    @Transactional
    public SelectionScheme updateScheme(Long id, SchemeCreateDTO dto) {
        SelectionScheme scheme = schemeMapper.selectById(id);
        if (scheme == null) {
            throw new RuntimeException("方案不存在");
        }
        scheme.setName(dto.getName());
        scheme.setDescription(dto.getDescription());
        scheme.setConditionsJson(JSONUtil.toJsonStr(dto.getConditions()));
        scheme.setVisibility(SchemeVisibility.valueOf(dto.getVisibility().toUpperCase()));
        scheme.setUpdatedAt(LocalDateTime.now());
        schemeMapper.updateById(scheme);
        return scheme;
    }

    @Override
    @Transactional
    public void deleteScheme(Long id) {
        schemeMapper.deleteById(id);
    }

    @Override
    public List<SelectionScheme> listSchemes(Long userId, String visibility) {
        QueryWrapper<SelectionScheme> wrapper = new QueryWrapper<>();
        if (visibility != null) {
            wrapper.eq("visibility", visibility);
        }
        if (userId != null) {
            wrapper.and(w -> w.eq("creator_id", userId).or().eq("visibility", "global"));
        }
        return schemeMapper.selectList(wrapper);
    }

    private QueryWrapper<MarketDataSnapshot> buildQueryWrapper(SelectionConditionDTO c) {
        QueryWrapper<MarketDataSnapshot> w = new QueryWrapper<>();
        if (c.getCategoryId() != null) {
            w.eq("category_id", c.getCategoryId());
        }
        if (c.getCountryCode() != null) {
            w.eq("country_code", c.getCountryCode());
        }
        if (c.getPriceMin() != null) {
            w.ge("price", c.getPriceMin());
        }
        if (c.getPriceMax() != null) {
            w.le("price", c.getPriceMax());
        }
        if (c.getBsrRankMin() != null) {
            w.ge("bsr_rank", c.getBsrRankMin());
        }
        if (c.getBsrRankMax() != null) {
            w.le("bsr_rank", c.getBsrRankMax());
        }
        if (c.getMonthlySalesMin() != null) {
            w.ge("monthly_sales", c.getMonthlySalesMin());
        }
        if (c.getMonthlySalesMax() != null) {
            w.le("monthly_sales", c.getMonthlySalesMax());
        }
        if (c.getRatingMin() != null) {
            w.ge("rating", c.getRatingMin());
        }
        if (c.getReviewCountMin() != null) {
            w.ge("review_count", c.getReviewCountMin());
        }
        if (c.getReviewCountMax() != null) {
            w.le("review_count", c.getReviewCountMax());
        }
        if (c.getListingDateAfter() != null) {
            w.ge("listing_date", c.getListingDateAfter());
        }
        if (c.getGrowthRateMonthlyMin() != null) {
            w.ge("growth_rate_monthly", c.getGrowthRateMonthlyMin());
        }
        if (c.getSellerCountMax() != null) {
            w.le("seller_count", c.getSellerCountMax());
        }
        if (c.getFbaRatioMin() != null) {
            w.ge("fba_ratio", c.getFbaRatioMin());
        }
        return w;
    }
}
