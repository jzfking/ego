package com.dj.ego.dao;

import com.dj.ego.common.data.BaseDao;
import com.dj.ego.entity.po.Address;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AddressDao extends BaseDao<Address> {
}