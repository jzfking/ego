package com.dj.ego.shiro.service.impl;

import com.dj.ego.common.GlobalException;
import com.dj.ego.common.data.DataCheckUtil;
import com.dj.ego.common.service.IService;
import com.dj.ego.common.service.ServiceStatus;
import com.dj.ego.shiro.entity.dto.UserAuthenticationInfo;
import com.dj.ego.shiro.entity.po.Role;
import com.dj.ego.shiro.entity.po.User;
import com.dj.ego.shiro.entity.po.UserRole;
import com.dj.ego.shiro.service.AuthUserAccountService;
import com.dj.ego.shiro.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 戴俊明
 * @className AuthUserAccountServiceImpl
 * @description 账户业务
 * @date 2019/8/24 9:38
 **/
@Slf4j
@Validated
@Service
public class AuthUserAccountServiceImpl implements AuthUserAccountService {

    @Autowired
    IService<User> userService;
    @Autowired
    IService<UserRole> userRoleService;
    @Autowired
    IService<Role> roleService;

    @Override
    public String login(@NotNull UserAuthenticationInfo userInfo) {
        UsernamePasswordToken token = UserAuthenticationInfo.getUsernamePasswordToken(userInfo);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        if (subject.isAuthenticated()) {
            User user = DataCheckUtil.get(userService.getDao().selectOne(User.builder()
                    .username(userInfo.getUsername()).password(userInfo.getPassword()).build()), () ->
                    new UnknownAccountException("登录失败"));
            return TokenUtil.createToken(User.getUserClaims(user), false);
        }
        return null;
    }

    @Override
    public User register(User user) {
        User exist = User.builder().username(user.getUsername()).build();
        exist = userService.getDao().selectOne(exist);
        if (exist != null) {
            throw GlobalException.builder().httpStatus(HttpStatus.BAD_REQUEST)
                    .serviceStatus(ServiceStatus.database_has_insert).build();
        } else {
            user.setStatus("0");
            userService.insertRecord(user);
            return user;
        }
    }

    @Override
    public void cancelRegister(Integer id) {
        User user = userService.selectOneById(id);
        if (User.NORMAL.equals(user.getStatus())) {
            throw GlobalException.builder().httpStatus(HttpStatus.BAD_REQUEST)
                    .serviceStatus(ServiceStatus.not_a_account_waiting_for_verification).build();
        } else {
            userService.deleteById(id);
        }
    }

    @Override
    public User activeUser(Integer id) {
        User user = userService.selectOneById(id);
        if (User.NORMAL.equals(user.getStatus())) {
            throw GlobalException.builder().httpStatus(HttpStatus.BAD_REQUEST)
                    .serviceStatus(ServiceStatus.not_a_account_waiting_for_verification).build();
        } else {
            user.setStatus(User.NORMAL);
            userService.updateRecord(user);
            return userService.selectOneById(id);
        }
    }

    @Override
    public boolean[] hasPermissions(List<String> list) {
        Subject subject = SecurityUtils.getSubject();
        return subject.isPermitted(list.stream().distinct().map(WildcardPermission::new).collect(Collectors.toList()));
    }
}
