package com.dj.ego.shiro.service;

import com.dj.ego.shiro.entity.dto.UserAuthenticationInfo;
import com.dj.ego.shiro.entity.po.User;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 戴俊明
 * @className AuthUserAccountService
 * @description 账户业务接口
 * @date 2019/8/24 14:10
 **/

public interface AuthUserAccountService {

    String login(@NotNull UserAuthenticationInfo user);

    User register(@NotNull @Valid User user);

    void cancelRegister(@Min(value = 1, message = "id最小不能小于1") Integer id);

    User activeUser(@Min(value = 1, message = "id最小不能小于1") Integer id);

    boolean[] hasPermissions(List<String> list);
}
