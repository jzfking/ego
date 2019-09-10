package com.dj.ego.shiro.service.crud;

import com.dj.ego.common.data.BaseDao;
import com.dj.ego.common.service.BaseService;
import com.dj.ego.shiro.entity.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 戴俊明
 * @className UserServiceImpl
 * @description 用户业务
 * @date 2019/8/24 10:09
 **/

@Service(value = "userService")
public class UserServiceImpl extends BaseService<User> {

    @Autowired
    @Override
    protected void setDao(BaseDao<User> dao) {
        super.setDao(dao);
    }
}
