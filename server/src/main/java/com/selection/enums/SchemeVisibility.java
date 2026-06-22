package com.selection.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum SchemeVisibility {
    PRIVATE("private", "个人可见"),
    TEAM("team", "团队可见"),
    GLOBAL("global", "全局可见");

    @EnumValue
    private final String code;
    private final String desc;

    SchemeVisibility(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
