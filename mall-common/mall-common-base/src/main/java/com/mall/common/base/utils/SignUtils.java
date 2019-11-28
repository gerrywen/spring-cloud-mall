package com.mall.common.base.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.SortedMap;

/**
 * 验证签名
 *
 * @author wenguoli
 * @date 2019/9/16 11:09
 */
@Component
public class SignUtils {
    /**
     * 日志记录
     */
    private static Logger logger = LoggerFactory.getLogger(SignUtils.class);


    /**
     * 验证签名
     *
     * @param params    请求参数
     * @param sign      签名值
     * @param timestamp 时间戳
     * @return 状态
     */
    public static boolean verifySign(SortedMap<String, String> params, String sign, Long timestamp) {
        String paramsJsonStr = "Timestamp" + timestamp + JSONObject.toJSONString(params);
        return verifySign(paramsJsonStr, sign);
    }

    /**
     * 验证签名
     *
     * @param params 请求参数
     * @param sign   签名值
     * @return 状态
     */
    public static boolean verifySign(String params, String sign) {
        logger.info("Header Sign : {}", sign);
        if (StringUtils.isEmpty(params)) {
            return false;
        }
        logger.info("Param : {}", params);
        String paramsSign = getParamsSign(params);
        logger.info("Param Sign : {}", paramsSign);
        return sign.equals(paramsSign);
    }

    /**
     * @param params 请求参数
     * @return 返回加密后的字符串
     */
    public static String getParamsSign(String params) {
        return DigestUtils.md5DigestAsHex(params.getBytes()).toUpperCase();
    }

}
