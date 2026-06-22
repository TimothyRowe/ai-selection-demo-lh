package com.selection.controller;

import com.selection.dto.Result;
import com.selection.dto.SchemeCreateDTO;
import com.selection.dto.SelectionConditionDTO;
import com.selection.service.SelectionSchemeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "选品方案管理")
@RestController
@RequestMapping("/scheme")
public class SelectionController {

    @Resource
    private SelectionSchemeService selectionSchemeService;

    @ApiOperation("创建选品方案")
    @PostMapping
    public Result<?> createScheme(@RequestBody SchemeCreateDTO dto) {
        return Result.ok(selectionSchemeService.createScheme(dto));
    }

    @ApiOperation("更新选品方案")
    @PutMapping("/{id}")
    public Result<?> updateScheme(@PathVariable Long id, @RequestBody SchemeCreateDTO dto) {
        selectionSchemeService.updateScheme(id, dto);
        return Result.ok(null);
    }

    @ApiOperation("删除选品方案")
    @DeleteMapping("/{id}")
    public Result<?> deleteScheme(@PathVariable Long id) {
        selectionSchemeService.deleteScheme(id);
        return Result.ok(null);
    }

    @ApiOperation("方案列表")
    @GetMapping("/list")
    public Result<?> listSchemes(@RequestParam(required = false) String visibility) {
        return Result.ok(selectionSchemeService.listSchemes(visibility));
    }

    @ApiOperation("执行选品")
    @PostMapping("/execute")
    public Result<?> executeSelection(@RequestBody SelectionConditionDTO dto,
                                      @RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "20") int pageSize) {
        return Result.ok(selectionSchemeService.executeSelection(dto, page, pageSize));
    }
}
