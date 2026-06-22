package com.selection.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.selection.enums.PoolSource;
import com.selection.enums.PoolStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("selection_pool")
public class SelectionPool {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String asin;

    private String countryCode;

    private PoolSource source;

    private Long sourceSchemeId;

    private PoolStatus status;

    private Long evaluatorId;

    private LocalDateTime evaluateTime;

    private String rejectReason;

    private Long marketSnapshotId;

    private Long erpProductId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
