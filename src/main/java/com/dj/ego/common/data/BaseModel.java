package com.dj.ego.common.data;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author 戴俊明
 * @className BaseModel
 * @description 基础实体类
 * @date 2019/8/21 17:19
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseModel implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    @Min(value = 1, message = "id最小不能小于1")
    @ApiModelProperty(value = "id", name = "id", notes = "自增")
    protected Integer id;

}
