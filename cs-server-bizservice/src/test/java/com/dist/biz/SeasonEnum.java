package com.dist.biz;

import java.util.Objects;

/**
 * @author yujx
 * @date 2019/04/17 09:39
 */
public enum SeasonEnum {

    Spring(0, "春"),
    Summer(1, "夏"),
    Autumn(2, "秋"),
    Winter(3, "冬"),
    ;

    private Integer code;
    private String desc;

    SeasonEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getSeasonDesc(Integer code) {
        for (SeasonEnum seasonEnum : SeasonEnum.values()) {
            if (Objects.equals(seasonEnum.code, code)){
                return seasonEnum.desc;
            }
        }
        return null;
    }
}
