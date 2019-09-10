package com.dj.ego.shiro.dao;

import com.dj.ego.common.data.BaseDao;
import com.dj.ego.shiro.entity.po.EmailCode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 戴俊明
 * @version 1.0
 * @className EmailCodeDao
 * @description 邮箱验证码操作类
 * @date 2019/8/23 23:24
 **/
@Mapper
@Repository
public interface EmailCodeDao extends BaseDao<EmailCode> {
}