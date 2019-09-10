package com.dj.ego.shiro.dao;

import com.dj.ego.common.data.BaseDao;
import com.dj.ego.shiro.entity.po.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 戴俊明
 * @version 1.0
 * @className UserRoleDao
 * @description 用户-角色操作类
 * @date 2019/8/23 23:22
 **/
@Mapper
@Repository
public interface UserRoleDao extends BaseDao<UserRole> {
}