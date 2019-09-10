package com.dj.ego.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * @author 戴俊明
 * @className OtherConfig
 * @description TODO
 * @date 2019/8/24 12:12
 **/

@Configuration
public class ValidatorConfig {

    @Bean
    @Primary
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        postProcessor.setBeforeExistingAdvisors(true);
        postProcessor.setProxyTargetClass(true);
        postProcessor.setValidator(validator());
        return postProcessor;
    }

    @Bean
    @Primary
    public Validator validator() {
        return Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();
    }

}
