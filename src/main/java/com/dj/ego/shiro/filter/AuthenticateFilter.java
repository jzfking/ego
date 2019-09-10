package com.dj.ego.shiro.filter;

import cn.hutool.core.util.StrUtil;
import com.dj.ego.common.GlobalExceptionHandler;
import com.dj.ego.common.SpringBeanUtil;
import com.dj.ego.common.data.DataCheckUtil;
import com.dj.ego.common.http.HttpUtil;
import com.dj.ego.common.service.IService;
import com.dj.ego.shiro.entity.dto.UserClaims;
import com.dj.ego.shiro.entity.po.User;
import com.dj.ego.shiro.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author 戴俊明
 * @className AuthenticateFilter
 * @description 登录验证
 * @date 2019/8/28 15:08
 **/
@Slf4j
public class AuthenticateFilter extends AuthenticatingFilter {

    private static final String AUTHORIZATION = "Authorization";

    private static IService<User> userService = null;

    private static IService<User> getUserService() {
        if (userService == null) {
            log.info("重新注入userService");
            userService = (IService<User>) SpringBeanUtil.getBean("userService");
        }
        return userService;
    }

    public AuthenticateFilter(IService<User> service) {
        userService = service;
    }

    @Override
    protected boolean pathsMatch(String path, ServletRequest request) {
        String url = this.getPathWithinApplication(request);
        if (url.lastIndexOf("/") == url.length() - 1) {
            url = url.substring(0, url.length() - 1);
        }
        String[] strings = path.split("==");

        if (strings.length <= 1) {
            return super.pathsMatch(strings[0], url);
        } else {
            String httpMethod = WebUtils.toHttp(request).getMethod().toUpperCase();
            return httpMethod.equals(strings[1].toUpperCase()) && super.pathsMatch(strings[0], url);
        }
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws IOException {
        String jwt = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        jwt = jwt.replaceFirst("Bearer ", "");
        if (StrUtil.isNotBlank(jwt) && !TokenUtil.isExpiration(jwt)) {

            UserClaims userClaims = TokenUtil.getUserClaims(jwt);
            User user = DataCheckUtil.get(getUserService().getDao().selectByPrimaryKey(userClaims.getId()), () ->
                    new UnknownAccountException("登录失败"));

            return new UsernamePasswordToken(user.getUsername(), user.getPassword(), false);
        } else {
            return null;
        }
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.info("=================================== AuthenticateFilter ====================================");
        if (this.isLoginRequest(request, response)) {
            return true;
        }
        boolean allowed;
        try {
            allowed = executeLogin(request, response);
        } catch (Exception e) {
            return false;
        }
        return allowed || super.isPermissive(mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        ResponseEntity entity = GlobalExceptionHandler.handle(new IncorrectCredentialsException("登录失败"));
        HttpUtil.writeResponse(response, entity);
        return false;
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        //TODO:更新token
        return true;
    }

}
