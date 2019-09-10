package com.dj.ego.common;

import com.dj.ego.common.http.HttpBodyEntity;
import com.dj.ego.common.http.ServerMessage;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.util.Objects;

/**
 * @author 戴俊明
 * @version 1.0
 * @className BaseExceptionHandler
 * @description 全局异常处理
 * @date 2019/5/20 19:05
 **/

@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public static ResponseEntity handle(Exception e) {
        log.info("===================================== Handle Exception =====================================");
        log.info("Exception      : {}", e.toString());
        log.info("message        : {}", e.getMessage());
        if (e instanceof GlobalException) {
            return handleGlobalException((GlobalException) e);
        } else if (e instanceof AuthenticationException) {
            return handleAuthenticationException((AuthenticationException) e);
        } else if (e instanceof AuthorizationException) {
            return handleAuthorizationException((AuthorizationException) e);
        } else if (e instanceof ExpiredJwtException) {
            return handleExpiredJwtException((ExpiredJwtException) e);
        } else if (e instanceof ConstraintViolationException) {
            return handleConstraintViolationException((ConstraintViolationException) e);
        } else if (e instanceof RuntimeException) {
            return handleRuntimeException((RuntimeException) e);
        } else if (e instanceof MethodArgumentNotValidException) {
            return handleMethodArgumentNotValidException((MethodArgumentNotValidException) e);
        } else {
            return handleException(e);
        }
    }

    public static ResponseEntity handleGlobalException(GlobalException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(HttpBodyEntity.builder()
                .status(e.getServiceStatus().getStatus())
                .msg(ServerMessage.builder()
                        .description(e.getServiceStatus().getDescription())
                        .build())
                .build());
    }

    public static ResponseEntity handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(HttpBodyEntity.builder()
                .msg(ServerMessage.builder()
                        .description(e.getMessage())
                        .build())
                .build());
    }

    public static ResponseEntity handleAuthorizationException(AuthorizationException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(HttpBodyEntity.builder()
                .msg(ServerMessage.builder()
                        .description(e.getMessage())
                        .build())
                .build());
    }

    public static ResponseEntity handleExpiredJwtException(ExpiredJwtException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(HttpBodyEntity.builder()
                .msg(ServerMessage.builder()
                        .description("token过期")
                        .build())
                .build());
    }

    public static ResponseEntity handleConstraintViolationException(ConstraintViolationException e) {
        return handleException(e);
    }

    public static ResponseEntity handleRuntimeException(RuntimeException e) {
        return handleException(e);
    }

    public static ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String message = Objects.requireNonNull(error).getDefaultMessage();
        log.info("validator error filed={},message={} ", error.getField(), error.getDefaultMessage(), e);
        return ResponseEntity.badRequest().build();
    }

    public static ResponseEntity handleException(Exception e) {
        return ResponseEntity.badRequest().body(HttpBodyEntity.builder()
                .msg(ServerMessage.builder()
                        .description(e.getMessage())
                        .build())
                .build());
    }
}
