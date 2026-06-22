package com.selection.controller;

import com.selection.dto.Result;
import com.selection.service.SelectionPoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "ERP回调")
@RestController
@RequestMapping("/callback")
public class CallbackController {

    @Resource
    private SelectionPoolService selectionPoolService;

    @ApiOperation("产品状态回调")
    @PostMapping("/product-status")
    public Result<?> handleProductStatus(@RequestParam String erpProductId,
                                          @RequestParam String status) {
        selectionPoolService.handleErpCallback(erpProductId, status);
        return Result.ok(null);
    }
}
