package com.mall.auth.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mall.auth.entity.UserInfo;
import com.mall.auth.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private static JwtProperties jwtProperties;

    /**
     * 从token中获取登录用户名
     */
    public static String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
//            username =  claims.getSubject();
            username =  ObjectUtils.toString(claims.get(JwtConstans.JWT_KEY_USER_NAME));
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 从token中获取登录用户id
     */
    public static Long getUserIdFromToken(String token) {
        Long userId;
        try {
            Claims claims = getClaimsFromToken(token);
            userId =  ObjectUtils.toLong(claims.get(JwtConstans.JWT_KEY_ID));
        } catch (Exception e) {
            userId = null;
        }
        return userId;
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @return 用户信息
     * @throws Exception
     */
    public static UserInfo getUserInfoFromToken(String token) throws Exception {
        Claims body = getClaimsFromToken(token);
        return new UserInfo(
                ObjectUtils.toLong(body.get(JwtConstans.JWT_KEY_ID)),
                ObjectUtils.toString(body.get(JwtConstans.JWT_KEY_USER_NAME))
        );
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userInfo 从数据库中查询出来的用户信息
     */
    public static boolean validateToken(String token, UserInfo userInfo) {
        String username = getUserNameFromToken(token);
        return username.equals(userInfo.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 根据用户信息生成token
     */
    public static String generateToken(UserInfo userInfo) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtConstans.JWT_KEY_ID, userInfo.getId());
        claims.put(JwtConstans.JWT_KEY_USER_NAME, userInfo.getUsername());
        claims.put(JwtConstans.JWT_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 判断token是否可以被刷新
     */
    public static boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     */
    public static String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(JwtConstans.JWT_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 根据负责生成JWT的token
     */
    private static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private static Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.info("JWT格式验证失败:{}",token);
        }
        return claims;
    }

    /**
     * 生成token的过期时间
     */
    private static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + jwtProperties.getExpiration());
    }

    /**
     * 判断token是否已经失效
     */
    private static boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private static Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }




    //***** 以下是另外一种方式 *****/


    /**
     * 生成token
     *
     * @param userInfo 用户信息
     * @return 加密的token
     */
    public static String createToken(UserInfo userInfo) {
        try {
            Date date = new Date(System.currentTimeMillis() + jwtProperties.getExpiration());
            Algorithm algorithm = Algorithm.HMAC256(userInfo.getId() + jwtProperties.getSecret());
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
     * @param token   token值
     * @param userInfo 用户信息
     * @return 状态值
     */
    public static boolean checkToken(String token, UserInfo userInfo) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(userInfo.getId() + jwtProperties.getSecret());
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
