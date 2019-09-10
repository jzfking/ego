package com.dj.ego.shiro.service;

import com.dj.ego.shiro.entity.po.Role;

import java.util.Set;

/**
 * @author 戴俊明
 * @className IUserRoleService
 * @description 用户业务接口
 * @date 2019/9/7 8:37
 **/

public interface IUserRoleService {

    Set<Role> searchUserRoles(Integer id);

}
