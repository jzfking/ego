package com.dj.ego.common.http;

import lombok.*;

/**
 * @author 戴俊明
 * @className ServerMessage
 * @description 业务信息
 * @date 2019/7/24 10:03
 **/

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ServerMessage {
    /**
     * @author 戴俊明
     * @description 业务描述
     * @date 2019/7/25 9:40
     **/
    private String description;
    /**
     * @author 戴俊明
     * @description 具体细节
     * @date 2019/7/25 9:41
     **/
    private String detail;

}
