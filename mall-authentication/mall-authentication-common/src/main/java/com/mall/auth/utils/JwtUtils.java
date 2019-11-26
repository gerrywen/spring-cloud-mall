package com.mall.auth.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mall.auth.entity.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * program: spring-cloud-mall->JwtUtils
 * description: jwt工具类
 * author: gerry
 * created: 2019-11-25 21:00
 **/
@Component
public class JwtUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    /**
     * 从token中获取登录用户名
     */
    public static String getUserNameFromToken(String token, String secret) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token, secret);
//            username =  claims.getSubject();
            username = ObjectUtils.toString(claims.get(JwtConstans.JWT_KEY_USER_NAME));
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 从token中获取登录用户id
     */
    public static Long getUserIdFromToken(String token, String secret) {
        Long userId;
        try {
            Claims claims = getClaimsFromToken(token, secret);
            userId = ObjectUtils.toLong(claims.get(JwtConstans.JWT_KEY_ID));
        } catch (Exception e) {
            userId = null;
        }
        return userId;
    }

    /**
     * 获取token中的用户信息
     *
     * @param token 用户请求中的令牌
     * @return 用户信息
     * @throws Exception
     */
    public static UserInfo getUserInfoFromToken(String token, String secret) throws Exception {
        Claims body = getClaimsFromToken(token, secret);
        return new UserInfo(
                ObjectUtils.toLong(body.get(JwtConstans.JWT_KEY_ID)),
                ObjectUtils.toString(body.get(JwtConstans.JWT_KEY_USER_NAME))
        );
    }

    /**
     * 验证token是否还有效
     *
     * @param token    客户端传入的token
     * @param userInfo 从数据库中查询出来的用户信息
     */
    public static boolean validateToken(String token, UserInfo userInfo, String secret) {
        String username = getUserNameFromToken(token, secret);
        return username.equals(userInfo.getUsername()) && !isTokenExpired(token, secret);
    }

    /**
     * 根据用户信息生成token
     */
    public static String generateToken(UserInfo userInfo, Integer expiration, String secret) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtConstans.JWT_KEY_ID, userInfo.getId());
        claims.put(JwtConstans.JWT_KEY_USER_NAME, userInfo.getUsername());
        claims.put(JwtConstans.JWT_KEY_CREATED, new Date());
        return generateToken(claims, expiration, secret);
    }

    /**
     * 判断token是否可以被刷新
     */
    public static boolean canRefresh(String token, String secret) {
        return !isTokenExpired(token, secret);
    }

    /**
     * 刷新token
     */
    public static String refreshToken(String token, Integer expiration, String secret) {
        Claims claims = getClaimsFromToken(token, secret);
        claims.put(JwtConstans.JWT_KEY_CREATED, new Date());
        return generateToken(claims, expiration, secret);
    }

    /**
     * 根据负责生成JWT的token
     */
    private static String generateToken(Map<String, Object> claims, Integer expiration, String secret) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate(expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private static Claims getClaimsFromToken(String token, String secret) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    /**
     * 生成token的过期时间
     */
    private static Date generateExpirationDate(Integer expiration) {
        return new Date(System.currentTimeMillis() + expiration);
    }

    /**
     * 判断token是否已经失效
     */
    private static boolean isTokenExpired(String token, String secret) {
        Date expiredDate = getExpiredDateFromToken(token, secret);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private static Date getExpiredDateFromToken(String token, String secret) {
        Claims claims = getClaimsFromToken(token, secret);
        return claims.getExpiration();
    }


    //***** 以下是另外一种方式 *****/


    /**
     * 生成token
     *
     * @param userInfo 用户信息
     * @return 加密的token
     */
    public static String createToken(UserInfo userInfo, Integer expiration, String secret) {
        try {
            Date date = new Date(System.currentTimeMillis() + expiration);
            Algorithm algorithm = Algorithm.HMAC256(userInfo.getId() + secret);
            return JWT.create()
                    .withExpiresAt(date)
                    .withClaim(JwtConstans.JWT_KEY_ID, userInfo.getId())
                    .withClaim(JwtConstans.JWT_KEY_USER_NAME, userInfo.getUsername())
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验token是否失效
     *
     * @param token    token值
     * @param userInfo 用户信息
     * @return 状态值
     */
    public static boolean checkToken(String token, UserInfo userInfo, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(userInfo.getId() + secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 获取用户名称
     *
     * @param token token值
     * @return 返回账户
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(JwtConstans.JWT_KEY_USER_NAME).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取用户id
     *
     * @param token token值
     * @return 返回账户
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(JwtConstans.JWT_KEY_ID).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
