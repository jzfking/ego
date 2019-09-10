package com.dj.ego.shiro.service.crud;

import com.dj.ego.common.GlobalException;
import com.dj.ego.common.data.BaseDao;
import com.dj.ego.common.service.BaseService;
import com.dj.ego.common.service.IService;
import com.dj.ego.shiro.entity.po.Role;
import com.dj.ego.shiro.entity.po.UserRole;
import com.dj.ego.shiro.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 戴俊明
 * @className UserRoleServiceImpl
 * @date 2019/8/24 10:13
 **/
@Service
public class UserRoleServiceImpl extends BaseService<UserRole> implements IUserRoleService {

    @Autowired
    @Override
    protected void setDao(BaseDao<UserRole> dao) {
        super.setDao(dao);
    }

    @Autowired
    IService<Role> roleService;

    @Override
    public Set<Role> searchUserRoles(Integer id) {
        try {
            List<UserRole> list = super.select(UserRole.builder().userId(id).build());
            List<Integer> roleIds = list.stream().map(UserRole::getRoleId).collect(Collectors.toList());
            List<Role> roleList = roleService.selectByIds(roleIds);
            return new HashSet<>(roleList);
        } catch (GlobalException | ConstraintViolationException ignore) {
            return new HashSet<>();
        }
    }
}
