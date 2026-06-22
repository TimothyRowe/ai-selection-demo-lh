package com.selection.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("selection_log")
public class SelectionLog {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long schemeId;

    private Long executorId;

    private String conditionsJson;

    private Integer resultCount;

    private Integer poolAddedCount;

    private LocalDateTime createdAt;
}
