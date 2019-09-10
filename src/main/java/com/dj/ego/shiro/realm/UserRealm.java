package com.dj.ego.shiro.realm;

import com.dj.ego.common.data.DataCheckUtil;
import com.dj.ego.common.service.IService;
import com.dj.ego.shiro.entity.po.Access;
import com.dj.ego.shiro.entity.po.Role;
import com.dj.ego.shiro.entity.po.User;
import com.dj.ego.shiro.service.IRoleAccessService;
import com.dj.ego.shiro.service.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 戴俊明
 * @className UserRealm
 * @description 数据库域
 * @date 2019/8/26 10:45
 **/
@Slf4j
@Component("userRealm")
public class UserRealm extends AuthorizingRealm {

    @Autowired
    IService<User> userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public String getName() {
        return "userRealm";
    }

    @Autowired
    IUserRoleService userRoleService;

    @Autowired
    IRoleAccessService roleAccessService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        log.info("UserRealm::doGetAuthorizationInfo::Username = {}", username);
        User user = DataCheckUtil.get(userService.getDao().selectOne(User.builder()
                .username(username).build()));
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<Role> roleSet = userRoleService.searchUserRoles(user.getId());
        info.setRoles(roleSet.stream().map(Role::getCode).collect(Collectors.toSet()));

        Set<Integer> ids = roleSet.stream()
                .map(Role::getId).collect(Collectors.toSet());
        Set<Access> accesses = roleAccessService.searchRolesAccesses(ids);
        info.setStringPermissions(accesses.stream()
                .map(Access::getCode).collect(Collectors.toSet()));
        log.info("UserRealm::doGetAuthorizationInfo::AuthorizationInfo = [{}]", info);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        char[] pwd = (char[]) authenticationToken.getCredentials();
        String password = String.valueOf(pwd);
        log.info("UserRealm::doGetAuthenticationInfo::AuthenticationToken = [{}:{}]", username, password);

        User user = DataCheckUtil.get(userService.getDao().selectOne(User.builder()
                .username(username).password(password).build()), () ->
                new UnknownAccountException("登录失败"));
        if (User.INVALID.equals(user.getStatus())) {
            throw new DisabledAccountException("登录失败");
        }
        log.info("UserRealm::doGetAuthenticationInfo::AuthenticationInfo = [{}:{}]", user.getUsername(), user.getPassword());
        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
    }
}
