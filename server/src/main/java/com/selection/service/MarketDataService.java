package com.selection.service;

import com.selection.entity.MarketDataSnapshot;

import java.util.List;

public interface MarketDataService {

    void syncMarketData(String categoryId, String countryCode);

    void batchSaveSnapshots(List<MarketDataSnapshot> snapshots);

    MarketDataSnapshot getLatestSnapshot(String asin, String countryCode);
}
