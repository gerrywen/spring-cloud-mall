package com.mall.common.base.validate;


import com.mall.common.base.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wenguoli
 * @date 2019/9/27 14:31
 */
public class ValidateRules {
    private static Map<String, String> validates = new HashMap<>();

    //https://www.cnblogs.com/yw0219/p/8047938.html
    static {
        validates.put("match", "^(.+?)(\\d+)-(\\d+)$"); //可以扩展的格式n1-n2
        validates.put("normal", "^\\d+$"); //自然数
        validates.put("integer", "^[-+]?\\d+$"); //正负整数
        validates.put("float", "^[-+]?\\d+(\\.\\d+)?$"); //正负浮点数
        validates.put("qq", "^[1-9]\\d{4,10}$");  //QQ号码
        validates.put("zip", "^[1-9]\\d{5}$");
        validates.put("english", "^[A-Za-z]+$"); //纯英文
        validates.put("chinese", "^[\\\\x4E00-\\\\x9FA5\\\\xF900-\\\\xFA2D]+/"); //纯中文
        validates.put("email", "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"); //邮箱
        validates.put("domain", "[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(/.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+/.?"); //域名
        validates.put("mobile", "^1[3-8]\\d{9}$"); //手机号
        validates.put("idcard", "^(\\s)*(\\d{15}|\\d{18}|\\d{17}x)(\\s)*$i");  //身份证
        validates.put("url", "^(http|https)://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$"); //url地址
        validates.put("*", "^[\\w\\W]+$"); //所有符号
        validates.put("passwd", "^[\\w\\W]+$"); //所有符号
        validates.put("spasswd", "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$"); //强密码
        validates.put("s", "^[\\u4e00-\\u9fa5A-Za-z0-9_]+$"); //中英文(非空)
        validates.put("date", "^\\d{4}-\\d{1,2}-\\d{1,2}$");
        validates.put("time", "^([0-9]|[01][0-9]|2[1-3])(:([0-5]?[0-9])){1,2}$");
        validates.put("chname", "^[\\u4e00-\\u9fa5]{2,25}$");
    }

    /**
     * 校验传入的参数是否符合规则
     *
     * @param data  待校验的值
     * @param rule  待校验的正则规则
     * @param value 验证的规则
     * @return bool
     */
    static boolean _valid(String data, String rule, String value) {
        rule = rule.toLowerCase();
        // 创建 Pattern 对象
        if ("chname".equals(rule)) {//中文名，少数名族有.
            data = data.replaceAll("\\.", "");
        }
        if ("date".equals(rule)) {//时间需要特殊处理
            return true;
        }
//        if ("idcard".equals(rule)) {//需要实现身份证算法判断生日和校验位等等
//
//        }
        Pattern r = Pattern.compile(validates.get("match"));
        System.out.println("match==" + validates.get("match"));
        Matcher m = r.matcher(rule);
        //追加s1-n格式的正则表达
        if (m.find()) {
            //找到第一个
            if (validates.containsKey(m.group(1))) {
                rule = validates.get(m.group(1)).replaceAll("\\+\\$", "") + "{" + m.group(2) + "," + m.group(3) + "}$";
            }
        } else {
//            rule = validates.containsKey(rule) ? validates.get(rule) : value;
            rule = validates.getOrDefault(rule, value);
        }
        //未设置任何规则及正则表达式则直接返回通过
        if (StringUtils.isBlank(rule)) {
            return true;
        }
        return Pattern.matches(rule, data);
    }
}
