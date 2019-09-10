package com.dj.ego.shiro.entity.po;

import com.dj.ego.common.data.BaseModel;
import com.dj.ego.shiro.entity.dto.UserClaims;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author 戴俊明
 * @version 1.0
 * @className User
 * @description 用户
 * @date 2019/8/23 23:15
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_user")
@ApiModel(value = "用户对象")
public class User extends BaseModel {

    @ApiModelProperty(value = "用户名", name = "username")
    private String username;
    @ApiModelProperty(value = "密码", name = "password")
    private String password;
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
    @Length(min = 1, max = 1)
    @ApiModelProperty(value = "状态", name = "status", notes = "0:无法使用 1:正常")
    private String status;

    public static final String MALE = "0";
    public static final String FEMALE = "1";
    public static final String INVALID = "0";
    public static final String NORMAL = "1";

    private static final long serialVersionUID = 1L;

    public static UserClaims getUserClaims(User user) {
        UserClaims userClaims = UserClaims.builder()
                .username(user.getUsername())
                .gender(user.getGender())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .build();
        userClaims.setId(user.getId());
        return userClaims;
    }
}