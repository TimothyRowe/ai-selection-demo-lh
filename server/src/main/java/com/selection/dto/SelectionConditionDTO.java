package com.selection.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SelectionConditionDTO {

    private String categoryId;

    private BigDecimal priceMin;
    private BigDecimal priceMax;

    private Integer bsrRankMin;
    private Integer bsrRankMax;

    private Integer monthlySalesMin;
    private Integer monthlySalesMax;

    private BigDecimal ratingMin;

    private Integer reviewCountMin;
    private Integer reviewCountMax;

    private LocalDate listingDateAfter;

    private BigDecimal growthRateMonthlyMin;

    private Integer sellerCountMax;

    private BigDecimal fbaRatioMin;

    private String countryCode;
}
