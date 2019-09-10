package com.dj.ego.config;

import cn.hutool.core.util.StrUtil;
import com.dj.ego.interceptor.AuthorizationInterceptor;
import com.dj.ego.interceptor.PaginationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 戴俊明
 * @version 1.0
 * @className WebConfig
 * @description 配置拦截器和资源地址
 * @date 2019/5/20 15:37
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    AuthorizationInterceptor authorizationInterceptor;
    @Autowired
    PaginationInterceptor paginationInterceptor;
    @Autowired
    ResourceProperties resourceProperties;

    /**
     * @param registry
     * @author 戴俊明
     * @description 配置拦截器
     * @date 2019/5/20 15:42
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor);
        registry.addInterceptor(paginationInterceptor);
    }

    /**
     * @param registry
     * @author 戴俊明
     * @description 配置资源
     * @date 2019/5/20 15:43
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!StrUtil.isBlank(resourceProperties.getStaticFilesPath())) {
            registry.addResourceHandler("/static-files/**")
                    .addResourceLocations("file:" + resourceProperties.getStaticFilesPath());
        }
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars*//**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
