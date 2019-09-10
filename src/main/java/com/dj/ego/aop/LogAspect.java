package com.dj.ego.aop;

import com.dj.ego.common.GlobalException;
import com.dj.ego.common.service.ServiceStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author 戴俊明
 * @className LogAspect
 * @description 统一日志处理
 * @date 2019/8/21 16:03
 **/

@Aspect
@Slf4j
@Component
public class LogAspect {

    @Resource(name = "xsssql")
    ObjectMapper mapper;

    @Pointcut("execution(public * com.dj.ego.control..*.*(..))||" +
            "execution(public * com.dj.ego.common.http.BaseControl.*(..))||" +
            "execution(public * com.dj.ego.email.EmailControl.*(..))||" +
            "execution(public * com.dj.ego.shiro.control..*.*(..))")
    public void webLog() {
    }

    /**
     * @return void
     * @author 戴俊明
     * @description 在切点之前织入
     * @date 2019/8/21 16:12
     **/
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        } else {
            throw GlobalException.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .serviceStatus(ServiceStatus.can_not_get_request).build();
        }

        // 打印请求相关参数
        log.info("========================================== Start ==========================================");
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求 url
        log.info("URL            : {}", request.getRequestURL().toString());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteAddr());
        // 打印请求头
        log.info("Request Headers:");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            log.info("               : {} = {}", headerName, request.getHeader(headerName));
        }
        // 打印 Cookies
        log.info("Request Cookies: {}", mapper.writeValueAsString(request.getCookies()));
        // 打印请求入参
        log.info("Request Args   : {}", mapper.writeValueAsString(joinPoint.getArgs()));
    }

    /**
     * @return void
     * @author 戴俊明
     * @description 在切点之后织入
     * @date 2019/8/21 16:16
     **/
    @After("webLog()")
    public void doAfter() {
        log.info("=========================================== End ===========================================");
        // 每个请求之间空一行
        log.info("");
    }

    /**
     * @return java.lang.Object
     * @author 戴俊明
     * @description 打印响应和时间
     * @date 2019/8/21 16:17
     **/
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        log.info("Response Args  : {}", mapper.writeValueAsString(result));
        // 执行耗时
        log.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        return result;
    }

}
