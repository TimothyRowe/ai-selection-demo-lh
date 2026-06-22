package com.selection.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PoolAddDTO {

    @NotBlank(message = "ASIN不能为空")
    private String asin;

    @NotBlank(message = "国家编码不能为空")
    private String countryCode;

    private String source;

    private Long schemeId;

    private Long marketSnapshotId;
}
