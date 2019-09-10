package com.dj.ego.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author 戴俊明
 * @className XssSqlJsonConfig
 * @description json转换器
 * @date 2019/8/23 9:50
 **/
@Configuration
public class XssSqlJsonConfig {

    @Bean(name = "xsssql")
    @Primary
    public ObjectMapper xssObjectMapper(Jackson2ObjectMapperBuilder builder) {
        // 解析器
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        // 注册XSS SQL 解析器
        SimpleModule xssModule = new SimpleModule("XssStringJsonSerializer");
        xssModule.addSerializer(new XssSqlStringJsonSerializer());
        objectMapper.registerModule(xssModule);
        return objectMapper;
    }

}
