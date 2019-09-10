package com.dj.ego.shiro.entity.po;

import com.dj.ego.common.data.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Table;

/**
 * @author 戴俊明
 * @version 1.0
 * @className EmailCode
 * @description 邮件验证
 * @date 2019/8/23 22:57
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_email_code")
@ApiModel(value = "邮件验证对象")
public class EmailCode extends BaseModel {

    @Length(min = 6, max = 6)
    @ApiModelProperty(value = "邮件验证码", name = "code")
    private String code;

    private static final long serialVersionUID = 1L;
}