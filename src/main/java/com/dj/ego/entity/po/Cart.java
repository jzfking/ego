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
@Table(name = "tb_cart")
@ApiModel(value = "购物车对象")
public class Cart extends BaseModel {

    @ApiModelProperty(value = "用户id", name = "userId")
    private Integer userId;
    @ApiModelProperty(value = "商品id", name = "goodId")
    private Integer goodId;
    @ApiModelProperty(value = "商品名称", name = "goodName")
    private String goodName;
    @ApiModelProperty(value = "数量", name = "amount")
    private Integer amount;

    private static final long serialVersionUID = 1L;
}