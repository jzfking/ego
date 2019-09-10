package com.dj.ego.common;

import com.dj.ego.common.service.ServiceStatus;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author 戴俊明
 * @className SpringBeanUtil
 * @description TODO
 * @date 2019/9/5 0:06
 **/
@Component
public class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) {
        return Optional.ofNullable(applicationContext.getBean(name)).orElseThrow(() ->
                GlobalException.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .serviceStatus(ServiceStatus.spring_can_not_find_bean).build()
        );
    }

    public static <T> T getBean(Class<T> clazz) {
        return Optional.ofNullable(applicationContext.getBean(clazz)).orElseThrow(() ->
                GlobalException.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .serviceStatus(ServiceStatus.spring_can_not_find_bean).build()
        );
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return Optional.ofNullable(applicationContext.getBean(name, clazz)).orElseThrow(() ->
                GlobalException.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .serviceStatus(ServiceStatus.spring_can_not_find_bean).build()
        );
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringBeanUtil.applicationContext == null) {
            SpringBeanUtil.applicationContext = applicationContext;
        }
    }
}