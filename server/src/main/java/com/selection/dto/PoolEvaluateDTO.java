package com.selection.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PoolEvaluateDTO {

    @NotNull(message = "选品池ID不能为空")
    private Long id;

    @NotBlank(message = "评估状态不能为空")
    private String status;

    private String rejectReason;
}
