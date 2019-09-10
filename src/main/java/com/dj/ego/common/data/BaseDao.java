package com.dj.ego.common.data;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.base.select.SelectCountMapper;

/**
 * @author 戴俊明
 * @className BaseDao
 * @description 通用Mapper的基础接口类
 * @date 2019/7/24 14:31
 **/

public interface BaseDao<T> extends Mapper<T>, SelectCountMapper<T>, MySqlMapper<T>, IdsMapper<T> {
}
