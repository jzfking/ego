package com.dj.ego.shiro.entity.po;

import com.dj.ego.common.data.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Table;
import javax.validation.constraints.Pattern;

/**
 * @author 戴俊明
 * @version 1.0
 * @className Role
 * @description 角色
 * @date 2019/8/23 23:02
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_role")
@ApiModel(value = "角色对象")
public class Role extends BaseModel {

    @Pattern(regexp = EMAIL_PATTERN)
    @ApiModelProperty(value = "角色编码", name = "code")
    private String code;
    @ApiModelProperty(value = "角色详细信息", name = "detail")
    private String detail;

    private static final long serialVersionUID = 1L;

    public static final String EMAIL_PATTERN = "^ROLE_[A-Z]*";

}