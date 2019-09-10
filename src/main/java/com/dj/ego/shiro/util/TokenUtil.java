package com.dj.ego.shiro.util;

import cn.hutool.core.bean.BeanUtil;
import com.dj.ego.shiro.entity.dto.UserClaims;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 戴俊明
 * @className TokenUtil
 * @description 账户Token工具类
 * @date 2019/8/23 16:03
 **/

public class TokenUtil {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "?::4343fdf4fdf6cvf):";
    private static final String ISSUER = "戴俊明";
    /**
     * @author 戴俊明
     * @description 10小时
     * @date 2019/8/23 19:42
     **/
    private static final long EXPIRATION = 36000L;
    /**
     * @author 戴俊明
     * @description 一周
     * @date 2019/8/23 19:43
     **/
    private static final long EXPIRATION_REMEMBER = 604800L;

    /**
     * @return java.lang.String
     * @author 戴俊明
     * @description 创建jwt
     * @date 2019/8/27 14:40
     **/
    public static String createToken(UserClaims user, boolean isRememberMe) {
        Map<String, Object> userClaims = new HashMap<>(1);
        userClaims.put("user", user);
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setClaims(userClaims)
                .setIssuer(ISSUER)
                .setId(String.valueOf(user.getId()))
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setNotBefore(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    /**
     * @return com.service.cboot.security.shiro.entity.dto.UserClaims
     * @author 戴俊明
     * @description 获取用户签证
     * @date 2019/8/27 14:40
     **/
    public static UserClaims getUserClaims(String token) {
        Claims claims = getTokenBody(token);
        LinkedHashMap map = claims.get("user", LinkedHashMap.class);
        return BeanUtil.mapToBean(map, UserClaims.class, false);
    }

    /**
     * @return boolean
     * @author 戴俊明
     * @description 检查是否过期
     * @date 2019/8/27 14:40
     **/
    public static boolean isExpiration(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }

    /**
     * @return io.jsonwebtoken.Claims
     * @author 戴俊明
     * @description 获取jwt主体
     * @date 2019/8/27 14:40
     **/
    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
