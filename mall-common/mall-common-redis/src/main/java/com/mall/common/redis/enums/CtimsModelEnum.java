package com.mall.common.redis.enums;

import org.apache.commons.lang3.StringUtils;
/**
 * program: spring-cloud-mall->CtimsModelEnum
 * description:
 * author: gerry
 * created: 2019-11-26 20:45
 **/
public enum CtimsModelEnum {
    CTIMS_COMM_CAP("comm", "公共"),
    CTIMS_ORDER_CAP("order", "订单"),
    CTIMS_CACHE_AOP_CAP("cache:aop", "切面缓存");

    private String code;
    private String desc;

    public static CtimsModelEnum getByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        } else {
            CtimsModelEnum[] var1;
            var1 = values();
            for (CtimsModelEnum ctimsModelEnum : var1) {
                if (ctimsModelEnum.getCode().equals(code)) {
                    return ctimsModelEnum;
                }
            }

            return null;
        }
    }


    private CtimsModelEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
