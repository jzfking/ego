package com.dj.ego.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 戴俊明
 * @version 1.0
 * @className NoAuthorization
 * @description 带有此注释的方法不会被拦截器拦截
 * @date 2019/5/20 15:21
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoAuthorization {
}
