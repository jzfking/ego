package com.dj.ego.shiro.filter;

import com.dj.ego.common.GlobalExceptionHandler;
import com.dj.ego.common.SpringBeanUtil;
import com.dj.ego.common.http.HttpUtil;
import com.dj.ego.common.service.IService;
import com.dj.ego.shiro.entity.po.Access;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author 戴俊明
 * @version 1.0
 * @className AuthorizeFilter
 * @description 权限认证
 * @date 2019/9/7 23:33
 **/
@Slf4j
public class AuthorizeFilter extends PermissionsAuthorizationFilter {

    private static IService<Access> accessService = null;

    public static IService<Access> getAccessService() {
        if (accessService == null) {
            log.info("重新注入accessService");
            accessService = (IService<Access>) SpringBeanUtil.getBean("accessService");
        }
        return accessService;
    }

    public AuthorizeFilter(IService<Access> service) {
        accessService = service;
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
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        log.info("===================================== AuthorizeFilter =====================================");
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        ResponseEntity entity = GlobalExceptionHandler.handle(new AuthorizationException("权限认证失败"));
        HttpUtil.writeResponse(response, entity);
        return false;
    }

}