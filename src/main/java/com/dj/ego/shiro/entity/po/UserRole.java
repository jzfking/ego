package com.dj.ego.shiro.entity.po;

import com.dj.ego.common.data.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Table;
import javax.validation.constraints.Min;

/**
 * @author 戴俊明
 * @version 1.0
 * @className UserRole
 * @description 用户-角色关系表
 * @date 2019/8/23 23:20
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_user_role")
@ApiModel(value = "用户-角色对象")
public class UserRole extends BaseModel {

    @Min(value = 1, message = "id最小不能小于1")
    @ApiModelProperty(value = "用户id", name = "userId")
    private Integer userId;
    @Min(value = 1, message = "id最小不能小于1")
    @ApiModelProperty(value = "角色id", name = "roleId")
    private Integer roleId;

    private static final long serialVersionUID = 1L;
}