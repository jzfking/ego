package com.dj.ego.dao;

import com.dj.ego.common.data.BaseDao;
import com.dj.ego.entity.po.Area;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AreaDao extends BaseDao<Area> {
}