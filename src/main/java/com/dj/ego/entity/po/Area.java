package com.dj.ego.entity.po;

import com.dj.ego.common.data.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_area")
@ApiModel(value = "地区对象")
public class Area extends BaseModel {

    @ApiModelProperty(value = "父地区id", name = "parent")
    private Integer parent;
    @ApiModelProperty(value = "地区名称", name = "name")
    private String name;
    @ApiModelProperty(value = "地区级别", name = "level")
    private Byte level;

    private static final long serialVersionUID = 1L;
}