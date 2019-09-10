package com.dj.ego.shiro.entity.dto;

import com.dj.ego.common.data.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author 戴俊明
 * @className UserClaims
 * @description 用户签证(jwt携带对象)
 * @date 2019/8/27 14:27
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户签证")
public class UserClaims extends BaseModel {

    @ApiModelProperty(value = "用户名", name = "username")
    private String username;
    @Length(min = 1, max = 1)
    @ApiModelProperty(value = "性别", name = "gender", notes = "0:男 1:女")
    private String gender;
    @ApiModelProperty(value = "昵称", name = "nickname")
    private String nickname;
    @ApiModelProperty(value = "联系电话", name = "phone")
    private String phone;
    @NotNull
    @ApiModelProperty(value = "电子邮箱", name = "email")
    private String email;
    @ApiModelProperty(value = "头像", name = "avatar")
    private String avatar;

}
