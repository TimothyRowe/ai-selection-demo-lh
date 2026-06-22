package com.selection.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.selection.client.SellerSpriteClient;
import com.selection.entity.MarketDataSnapshot;
import com.selection.repository.MarketDataSnapshotMapper;
import com.selection.service.MarketDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class MarketDataServiceImpl implements MarketDataService {

    private static final String CACHE_KEY_PREFIX = "market:";
    private static final long CACHE_TTL_HOURS = 24;

    @Autowired
    private MarketDataSnapshotMapper snapshotMapper;

    @Autowired
    private SellerSpriteClient sellerSpriteClient;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    @Transactional
    public void syncMarketData(String categoryId, String countryCode) {
        List<Map<String, Object>> rawData = sellerSpriteClient.fetchCategoryData(categoryId, countryCode);
        List<MarketDataSnapshot> snapshots = rawData.stream()
                .map(map -> BeanUtil.mapToBean(map, MarketDataSnapshot.class, true, null))
                .collect(Collectors.toList());
        batchSaveSnapshots(snapshots);
    }

    @Override
    @Transactional
    public void batchSaveSnapshots(List<MarketDataSnapshot> snapshots) {
        for (MarketDataSnapshot snapshot : snapshots) {
            snapshotMapper.insert(snapshot);
            String cacheKey = buildCacheKey(snapshot.getAsin(), snapshot.getCountryCode());
            redisTemplate.opsForValue().set(cacheKey, snapshot, CACHE_TTL_HOURS, TimeUnit.HOURS);
        }
    }

    @Override
    public MarketDataSnapshot getLatestSnapshot(String asin, String countryCode) {
        String cacheKey = buildCacheKey(asin, countryCode);
        Object cached = redisTemplate.opsForValue().get(cacheKey);
        if (cached instanceof MarketDataSnapshot) {
            return (MarketDataSnapshot) cached;
        }
        QueryWrapper<MarketDataSnapshot> wrapper = new QueryWrapper<>();
        wrapper.eq("asin", asin)
                .eq("country_code", countryCode)
                .orderByDesc("snapshot_date")
                .last("LIMIT 1");
        MarketDataSnapshot snapshot = snapshotMapper.selectOne(wrapper);
        if (snapshot != null) {
            redisTemplate.opsForValue().set(cacheKey, snapshot, CACHE_TTL_HOURS, TimeUnit.HOURS);
        }
        return snapshot;
    }

    private String buildCacheKey(String asin, String countryCode) {
        return CACHE_KEY_PREFIX + asin + ":" + countryCode;
    }
}
