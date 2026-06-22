package com.selection.service;

import com.selection.dto.PageResult;
import com.selection.dto.PoolAddDTO;
import com.selection.dto.PoolEvaluateDTO;
import com.selection.entity.SelectionPool;

import java.util.List;

public interface PoolService {

    void addToPool(PoolAddDTO dto, Long operatorId);

    void batchAddToPool(List<PoolAddDTO> dtos, Long operatorId);

    PageResult<SelectionPool> listPool(String status, int page, int pageSize);

    void evaluate(PoolEvaluateDTO dto, Long evaluatorId);

    void pushToErp(Long poolId);
}
