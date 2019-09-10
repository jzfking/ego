package com.dj.ego.common;

import com.dj.ego.common.service.ServiceStatus;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * @author 戴俊明
 * @version 1.0
 * @className GlobalException
 * @description 全局异常
 * @date 2019/5/20 19:03
 **/
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class GlobalException extends RuntimeException {
    /**
     * @author 戴俊明
     * @description 业务状态
     * @date 2019/7/26 14:31
     **/
    private ServiceStatus serviceStatus;

    /**
     * @author 戴俊明
     * @description HTTP状态
     * @date 2019/7/26 14:31
     **/
    private HttpStatus httpStatus;

}
