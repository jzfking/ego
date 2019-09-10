package com.dj.ego.email;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author 戴俊明
 * @className Email
 * @description 邮件实体
 * @date 2019/8/20 14:45
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "电子邮件对象")
public class Email {
    @NonNull
    @ApiModelProperty(value = "接收者的邮箱", name = "receiver")
    private String receiver;
    @ApiModelProperty(value = "邮件的标题", name = "title")
    private String title;
    @ApiModelProperty(value = "邮件的内容", name = "content")
    private String content;
}
