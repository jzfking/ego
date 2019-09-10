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
@Table(name = "tb_good")
@ApiModel(value = "商品对象")
public class Good extends BaseModel {

    @ApiModelProperty(value = "商品名称", name = "name")
    private String name;
    @ApiModelProperty(value = "价格", name = "price")
    private Float price;
    @ApiModelProperty(value = "商品加入时间", name = "time")
    private Date time;
    @ApiModelProperty(value = "商品图片", name = "picture")
    private byte[] picture;

    private static final long serialVersionUID = 1L;

}