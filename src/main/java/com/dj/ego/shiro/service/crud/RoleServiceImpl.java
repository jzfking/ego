package com.dj.ego.shiro.service.crud;

import com.dj.ego.common.data.BaseDao;
import com.dj.ego.common.service.BaseService;
import com.dj.ego.shiro.entity.po.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 戴俊明
 * @className RoleServiceImpl
 * @description 角色业务
 * @date 2019/8/24 10:10
 **/

@Service(value = "roleService")
public class RoleServiceImpl extends BaseService<Role> {

    @Autowired
    @Override
    protected void setDao(BaseDao<Role> dao) {
        super.setDao(dao);
    }
}
