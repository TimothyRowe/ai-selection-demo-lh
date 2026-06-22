package com.selection.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SchemeCreateDTO {

    @NotBlank(message = "方案名称不能为空")
    private String name;

    private String description;

    @NotNull(message = "筛选条件不能为空")
    @Valid
    private SelectionConditionDTO conditions;

    @NotBlank(message = "可见性不能为空")
    private String visibility;
}
