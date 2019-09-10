package com.dj.ego.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 戴俊明
 * @className ResourceProperties
 * @description 资源路径
 * @date 2019/8/22 22:03
 **/
@Component
@ConfigurationProperties(prefix = "file")
@Data
public class ResourceProperties {

    private String staticFilesPath;
    private String dynamicFilesPath;
}
