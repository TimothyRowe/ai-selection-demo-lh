package com.selection.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.selection.enums.SchemeVisibility;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("selection_scheme")
public class SelectionScheme {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String description;

    private String conditionsJson;

    private SchemeVisibility visibility;

    private Long creatorId;

    private Long teamId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @TableLogic
    private Integer deleted;
}
