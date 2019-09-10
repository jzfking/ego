package com.dj.ego.common.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 戴俊明
 * @className HttpUtil
 * @description TODO
 * @date 2019/8/28 17:19
 **/

public class HttpUtil {

    public static ObjectMapper getJackson() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setLocale(new Locale("zh_CN"));
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
    }

    public static HttpServletRequest toHttp(ServletRequest request) {
        return (HttpServletRequest) request;
    }

    public static HttpServletResponse toHttp(ServletResponse response) {
        return (HttpServletResponse) response;
    }

    public static void writeResponse(ServletResponse response, ResponseEntity entity) throws IOException {
        writeResponse(toHttp(response), entity);
    }

    public static void writeResponse(HttpServletResponse response, ResponseEntity entity) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(entity.getStatusCodeValue());
        for (Map.Entry<String, List<String>> entry : entity.getHeaders().entrySet()) {
            response.setHeader(entry.getKey(), String.join(",", entry.getValue()));
        }

        if (entity.getBody() != null) {
            String body = getJackson().writeValueAsString(entity.getBody());
            response.getWriter().print(body);
        }
        response.flushBuffer();
    }

}
