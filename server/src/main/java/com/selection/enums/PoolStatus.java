package com.selection.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum PoolStatus {
    PENDING("pending", "待评估"),
    EVALUATING("evaluating", "评估中"),
    APPROVED("approved", "通过"),
    REJECTED("rejected", "淘汰");

    @EnumValue
    private final String code;
    private final String desc;

    PoolStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
