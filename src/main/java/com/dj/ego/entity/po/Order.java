package com.dj.ego.entity.po;

import com.dj.ego.common.data.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_order")
@ApiModel(value = "订单对象")
public class Order extends BaseModel {

    @ApiModelProperty(value = "用户id", name = "userId")
    private Integer userId;
    @ApiModelProperty(value = "地址id", name = "addressId")
    private Integer addressId;
    @ApiModelProperty(value = "详细地址", name = "addressDetail")
    private String addressDetail;
    @ApiModelProperty(value = "商品id", name = "goodId")
    private Integer goodId;
    @ApiModelProperty(value = "商品名称", name = "goodName")
    private String goodName;
    @ApiModelProperty(value = "商品价格", name = "goodPrice")
    private Float goodPrice;
    @ApiModelProperty(value = "数量", name = "amount")
    private Integer amount;
    @ApiModelProperty(value = "下单时间", name = "time")
    private Date time;

    private static final long serialVersionUID = 1L;

}