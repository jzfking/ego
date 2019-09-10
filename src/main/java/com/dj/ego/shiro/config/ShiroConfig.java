package com.dj.ego.shiro.config;

import cn.hutool.crypto.digest.MD5;
import com.dj.ego.common.service.IService;
import com.dj.ego.shiro.entity.po.Access;
import com.dj.ego.shiro.entity.po.User;
import com.dj.ego.shiro.filter.AuthenticateFilter;
import com.dj.ego.shiro.filter.AuthorizeFilter;
import com.dj.ego.shiro.realm.UserRealm;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author 戴俊明
 * @className ShiroConfig
 * @description shiro配置
 * @date 2019/8/26 10:36
 **/

@Configuration
public class ShiroConfig {

    @Bean
    public SecurityManager securityManager(Authenticator authenticator, Authorizer authorizer) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(authenticator);
        securityManager.setAuthorizer(authorizer);
        return securityManager;
    }

    @Bean
    public FilterRegistrationBean<DelegatingFilterProxy> delegatingFilterProxy() {
        FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBean = new FilterRegistrationBean<>();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

    @Bean
    public Authenticator authenticator(UserRealm userRealm) {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setRealms(Arrays.asList(userRealm));
        authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        return authenticator;
    }

    @Bean
    Authorizer authorizer(UserRealm userRealm) {
        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();

        authorizer.setRealms(Arrays.asList(userRealm));
        authorizer.setPermissionResolver(new WildcardPermissionResolver());
        return authorizer;
    }

    private AuthenticateFilter authenticateFilter(IService<User> userService) {
        return new AuthenticateFilter(userService);
    }

    private AuthorizationFilter authorizationFilter(IService<Access> accessService) {
        return new AuthorizeFilter(accessService);
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager,
                                             ShiroPermissionConfig shiroPermissionConfig,
                                             IService<User> userService,
                                             IService<Access> accessService) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        shiroFilterFactoryBean.setLoginUrl("/auth/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/auth/403");

        Map<String, Filter> filterMap = shiroFilterFactoryBean.getFilters();
        filterMap.put("authcToken", authenticateFilter(userService));
        filterMap.put("access", authorizationFilter(accessService));
        shiroFilterFactoryBean.setFilters(filterMap);

        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroPermissionConfig.getUrlPermsMap());
        return shiroFilterFactoryBean;
    }

    @Bean
    public MD5 md5() {
        return new MD5();
    }

    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator() {
        DefaultWebSessionStorageEvaluator sessionStorageEvaluator = new DefaultWebSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setUsePrefix(true);
        return creator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
