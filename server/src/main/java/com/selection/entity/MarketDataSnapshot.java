package com.selection.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("market_data_snapshot")
public class MarketDataSnapshot {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String asin;

    private String categoryId;

    private String categoryName;

    private BigDecimal price;

    private Integer bsrRank;

    private Integer monthlySales;

    private BigDecimal revenue;

    private BigDecimal rating;

    private Integer reviewCount;

    private Integer sellerCount;

    private BigDecimal fbaRatio;

    private LocalDate listingDate;

    private BigDecimal growthRateMonthly;

    private BigDecimal growthRateQuarterly;

    private LocalDate snapshotDate;

    private String countryCode;

    private LocalDateTime createdAt;
}
