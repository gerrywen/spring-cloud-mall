package com.mall.common.base.utils;

import org.springframework.cglib.core.Constants;

/**
 * @author wenguoli
 * @date 2019/9/26 11:26
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static boolean isNotEmpty(String str) {
        return str != null && !StringUtils.isEmpty(str);
    }

    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }

    public static String isNullToZero(Object obj) {
        String str = "";
        if (obj == null || Constants.NULL.equals(obj)) {
            str = "0";
        } else {
            str = String.valueOf(obj);
        }
        if (StringUtils.isEmpty(str)) {
            str = "0";
        }
        return str.trim();
    }

}
