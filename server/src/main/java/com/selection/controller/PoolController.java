package com.selection.controller;

import com.selection.dto.PoolAddDTO;
import com.selection.dto.PoolEvaluateDTO;
import com.selection.dto.Result;
import com.selection.service.SelectionPoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "选品池管理")
@RestController
@RequestMapping("/pool")
public class PoolController {

    @Resource
    private SelectionPoolService selectionPoolService;

    @ApiOperation("加入选品池")
    @PostMapping("/add")
    public Result<?> addToPool(@RequestBody PoolAddDTO dto) {
        selectionPoolService.addToPool(dto);
        return Result.ok(null);
    }

    @ApiOperation("批量加入选品池")
    @PostMapping("/batch-add")
    public Result<?> batchAddToPool(@RequestBody List<PoolAddDTO> dtos) {
        selectionPoolService.batchAddToPool(dtos);
        return Result.ok(null);
    }

    @ApiOperation("选品池列表")
    @GetMapping("/list")
    public Result<?> listPool(@RequestParam(required = false) String status,
                              @RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "20") int pageSize) {
        return Result.ok(selectionPoolService.listPool(status, page, pageSize));
    }

    @ApiOperation("评估选品")
    @PostMapping("/evaluate")
    public Result<?> evaluate(@RequestBody PoolEvaluateDTO dto) {
        return Result.ok(selectionPoolService.evaluate(dto));
    }

    @ApiOperation("推送到ERP")
    @PostMapping("/{id}/push-erp")
    public Result<?> pushToErp(@PathVariable Long id) {
        selectionPoolService.pushToErp(id);
        return Result.ok(null);
    }
}
