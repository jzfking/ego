package com.dj.ego.shiro.entity.po;

import com.dj.ego.common.data.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 戴俊明
 * @version 1.0
 * @className Access
 * @description 权限
 * @date 2019/8/23 22:49
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_access")
@ApiModel(value = "权限对象")
public class Access extends BaseModel {

    @ApiModelProperty(value = "权限的编码", name = "code")
    private String code;
    @ApiModelProperty(value = "权限的编码id", name = "codeId")
    private Integer codeId;
    @ApiModelProperty(value = "访问地址", name = "url")
    private String url;
    @ApiModelProperty(value = "权限详细信息", name = "detail")
    private String detail;
    @ApiModelProperty(value = "类型", name = "type", notes = "1:菜单menu 2:资源element 3:接口")
    private Integer type;
    @ApiModelProperty(value = "访问方式", name = "method", notes = "1:GET 2:POST 3:PUT 4:DELETE")
    private Integer method;
    @ApiModelProperty(value = "父权限的编码id", name = "parentCodeId")
    private Integer parentCodeId;

    private static final long serialVersionUID = 1L;

    public static final Map<Integer, String> METHODS = new HashMap<>();

    static {
        METHODS.put(1, "GET");
        METHODS.put(2, "POST");
        METHODS.put(3, "PUT");
        METHODS.put(4, "DELETE");
    }
}