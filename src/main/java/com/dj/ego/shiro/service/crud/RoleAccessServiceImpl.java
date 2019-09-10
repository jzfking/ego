package com.dj.ego.shiro.service.crud;

import com.dj.ego.common.GlobalException;
import com.dj.ego.common.data.BaseDao;
import com.dj.ego.common.service.BaseService;
import com.dj.ego.common.service.IService;
import com.dj.ego.shiro.entity.po.Access;
import com.dj.ego.shiro.entity.po.RoleAccess;
import com.dj.ego.shiro.service.IRoleAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 戴俊明
 * @className RoleAccessServiceImpl
 * @date 2019/8/24 10:14
 **/
@Service
public class RoleAccessServiceImpl extends BaseService<RoleAccess> implements IRoleAccessService {

    @Autowired
    @Override
    protected void setDao(BaseDao<RoleAccess> dao) {
        super.setDao(dao);
    }

    @Autowired
    IService<Access> accessService;

    @Override
    public Set<Access> searchRoleAccesses(Integer id) {
        try {
            List<RoleAccess> list = super.select(RoleAccess.builder().roleId(id).build());
            List<Integer> accessCodeIds = list.stream()
                    .map(RoleAccess::getAccessCodeId)
                    .distinct()
                    .collect(Collectors.toList());
            List<Access> accessList = accessCodeIds.stream().map(codeId ->
                    accessService.getDao().select(Access.builder().codeId(codeId).build()))
                    .reduce((s1, s2) -> {
                        s1.addAll(s2);
                        return s1;
                    }).orElseGet(ArrayList::new);
            List<Access> total = accessList.stream().map(access ->
                    accessService.getDao().select(Access.builder().parentCodeId(access.getId()).build()))
                    .reduce((s1, s2) -> {
                        s1.addAll(s2);
                        return s1;
                    }).orElseGet(ArrayList::new);
            total.addAll(accessList);
            return new HashSet<>(total);
        } catch (GlobalException | ConstraintViolationException ignore) {
            return new HashSet<>();
        }
    }

    @Override
    public Set<Access> searchRolesAccesses(Collection<Integer> ids) {
        try {
            List<RoleAccess> list = ids.stream()
                    .map(id -> super.select(RoleAccess.builder().roleId(id).build()))
                    .reduce((s1, s2) -> {
                        s1.addAll(s2);
                        return s1;
                    }).orElseGet(ArrayList::new);
            List<Integer> accessCodeIds = list.stream()
                    .map(RoleAccess::getAccessCodeId)
                    .distinct()
                    .collect(Collectors.toList());
            List<Access> accessList = accessCodeIds.stream().map(codeId ->
                    accessService.getDao().select(Access.builder().codeId(codeId).build()))
                    .reduce((s1, s2) -> {
                        s1.addAll(s2);
                        return s1;
                    }).orElseGet(ArrayList::new);
            List<Access> total = accessList.stream().map(access ->
                    accessService.getDao().select(Access.builder().parentCodeId(access.getId()).build()))
                    .reduce((s1, s2) -> {
                        s1.addAll(s2);
                        return s1;
                    }).orElseGet(ArrayList::new);
            total.addAll(accessList);
            return new HashSet<>(total);
        } catch (GlobalException | ConstraintViolationException ignore) {
            return new HashSet<>();
        }
    }
}
