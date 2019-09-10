package com.dj.ego.shiro.aop;

import com.dj.ego.shiro.util.ShiroStateManager;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 戴俊明
 * @className PermissionChangeAspect
 * @description 处理权限变动
 * @date 2019/9/7 14:17
 **/

@Aspect
@Slf4j
@Component
public class PermissionChangeAspect {

    @Pointcut("@annotation(com.dj.ego.shiro.annotation.PermissionChange)")
    public void change() {
    }

    @Autowired
    ShiroStateManager manager;

    @AfterReturning(value = "change()", returning = "returnValue")
    public void after(JoinPoint jp, Object returnValue) {
        if (returnValue instanceof Integer) {
            Integer result = (Integer) returnValue;
            if (result >= 1) {
                manager.updateFilterChain();
            }
        }
    }

}
