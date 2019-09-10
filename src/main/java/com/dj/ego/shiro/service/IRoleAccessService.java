package com.dj.ego.shiro.service;

import com.dj.ego.shiro.entity.po.Access;

import java.util.Collection;
import java.util.Set;

/**
 * @author 戴俊明
 * @className IRoleAccessService
 * @description 角色业务接口
 * @date 2019/9/7 8:38
 **/

public interface IRoleAccessService {

    Set<Access> searchRoleAccesses(Integer id);

    Set<Access> searchRolesAccesses(Collection<Integer> ids);
}
