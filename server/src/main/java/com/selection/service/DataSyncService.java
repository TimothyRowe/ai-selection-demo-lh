package com.selection.service;

public interface DataSyncService {

    void syncSkuFromErp();

    void handleProductStatusCallback(Long erpProductId, String status);
}
