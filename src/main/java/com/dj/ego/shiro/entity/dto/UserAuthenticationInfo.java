package com.dj.ego.shiro.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author 戴俊明
 * @className UserAuthenticationInfo
 * @description 用户身份验证信息(Control传输对象)
 * @date 2019/8/26 11:07
 **/
@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ApiModel(value = "用户身份验证信息")
public class UserAuthenticationInfo {

    private String username;
    private String password;
    private boolean rememberMe;

    public static UsernamePasswordToken getUsernamePasswordToken(UserAuthenticationInfo userAuthenticationInfo) {
        return new UsernamePasswordToken(userAuthenticationInfo.getUsername(), userAuthenticationInfo.getPassword(), userAuthenticationInfo.isRememberMe());
    }

}
