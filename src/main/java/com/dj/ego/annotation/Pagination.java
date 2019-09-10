package com.dj.ego.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 戴俊明
 * @className Pagination
 * @description 拥有此注释的会被pageHelper拦截
 * @date 2019/7/24 18:15
 **/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Pagination {
}
