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
 * @className RoleAccess
 * @description 角色-权限关系表
 * @date 2019/8/23 23:11
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_role_access")
@ApiModel(value = "角色-权限对象")
public class RoleAccess extends BaseModel {

    @Min(value = 1, message = "id最小不能小于1")
    @ApiModelProperty(value = "角色id", name = "roleId")
    private Integer roleId;
    @ApiModelProperty(value = "权限的编码id", name = "accessCodeId")
    private Integer accessCodeId;

    private static final long serialVersionUID = 1L;
}