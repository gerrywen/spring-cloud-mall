package com.mall.common.base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 下划线转驼峰
 * @author wenguoli
 * @date 2019/9/27 11:34
 */
public class UnderLineToCamelUtils {
    public static String underLineToCamel(String source) {
        Matcher matcher = Pattern.compile("_(\\w)").matcher(source);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
