package com.selection.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum PoolSource {
    MANUAL("manual", "人工添加"),
    AI("ai", "AI筛选"),
    ANOMALY("anomaly", "异常检测");

    @EnumValue
    private final String code;
    private final String desc;

    PoolSource(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
