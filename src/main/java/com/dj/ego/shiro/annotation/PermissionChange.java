package com.dj.ego.shiro.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 戴俊明
 * @className PermissionChange
 * @description 对权限有该动的方法加上此注释，一旦改动，将自动更新过滤器的URL匹配机制
 * @date 2019/9/7 14:14
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionChange {
}
