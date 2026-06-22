package com.selection.controller;

import com.selection.dto.Result;
import com.selection.service.DataSyncService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "数据同步")
@RestController
@RequestMapping("/sync")
public class DataSyncController {

    @Resource
    private DataSyncService dataSyncService;

    @ApiOperation("触发市场数据同步")
    @PostMapping("/market-data")
    public Result<?> triggerMarketDataSync(@RequestParam String categoryId,
                                           @RequestParam String countryCode) {
        dataSyncService.syncMarketData(categoryId, countryCode);
        return Result.ok(null);
    }

    @ApiOperation("触发SKU同步")
    @PostMapping("/sku")
    public Result<?> triggerSkuSync() {
        dataSyncService.syncSkuData();
        return Result.ok(null);
    }
}
