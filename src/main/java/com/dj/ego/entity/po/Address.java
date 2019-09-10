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
@Table(name = "tb_address")
@ApiModel(value = "地址对象")
public class Address extends BaseModel {

    @ApiModelProperty(value = "地区id", name = "areaId")
    private Integer areaId;
    @ApiModelProperty(value = "具体定位", name = "remain")
    private String remain;
    @ApiModelProperty(value = "详细地址", name = "detail")
    private String detail;

    private static final long serialVersionUID = 1L;
}