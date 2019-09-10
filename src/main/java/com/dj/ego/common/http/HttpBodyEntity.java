package com.dj.ego.common.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 戴俊明
 * @className HttpBodyEntity
 * @description http响应信息
 * @date 2019/7/14 21:09
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HttpBodyEntity {
    /**
     * @author 戴俊明
     * @description 业务状态码
     * @date 2019/7/25 9:39
     **/
    private Integer status;
    /**
     * @author 戴俊明
     * @description 业务信息
     * @date 2019/7/25 9:40
     **/
    private ServerMessage msg;
    /**
     * @author 戴俊明
     * @description 返回数据
     * @date 2019/7/25 9:40
     **/
    private Object data;
}
