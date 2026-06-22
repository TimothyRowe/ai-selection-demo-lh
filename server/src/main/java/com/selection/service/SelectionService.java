package com.selection.service;

import com.selection.dto.PageResult;
import com.selection.dto.SchemeCreateDTO;
import com.selection.dto.SelectionConditionDTO;
import com.selection.entity.MarketDataSnapshot;
import com.selection.entity.SelectionScheme;

import java.util.List;

public interface SelectionService {

    PageResult<MarketDataSnapshot> executeSelection(SelectionConditionDTO condition, int page, int pageSize);

    SelectionScheme createScheme(SchemeCreateDTO dto, Long userId);

    SelectionScheme updateScheme(Long id, SchemeCreateDTO dto);

    void deleteScheme(Long id);

    List<SelectionScheme> listSchemes(Long userId, String visibility);
}
