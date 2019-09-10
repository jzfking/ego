package com.dj.ego.shiro.dao;

import com.dj.ego.common.data.BaseDao;
import com.dj.ego.shiro.entity.po.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 戴俊明
 * @version 1.0
 * @className RoleDao
 * @description 角色操作类
 * @date 2019/8/23 23:26
 **/
@Mapper
@Repository
public interface RoleDao extends BaseDao<Role> {
}