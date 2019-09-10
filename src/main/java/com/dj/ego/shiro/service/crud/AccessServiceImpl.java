package com.dj.ego.shiro.service.crud;

import com.dj.ego.common.data.BaseDao;
import com.dj.ego.common.service.BaseService;
import com.dj.ego.shiro.annotation.PermissionChange;
import com.dj.ego.shiro.entity.po.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 戴俊明
 * @className AccessServiceImpl
 * @description 权限业务
 * @date 2019/8/24 10:11
 **/

@Service("accessService")
public class AccessServiceImpl extends BaseService<Access> {

    @Autowired
    @Override
    protected void setDao(BaseDao<Access> dao) {
        super.setDao(dao);
    }

    @PermissionChange
    @Override
    public Integer insertRecord(Access model) {
        return super.insertRecord(model);
    }

    @PermissionChange
    @Override
    public Integer insertRecords(List<Access> list) {
        return super.insertRecords(list);
    }

    @PermissionChange
    @Override
    public Integer updateRecord(Access model) {
        return super.updateRecord(model);
    }

    @PermissionChange
    @Override
    public Integer updateRecords(List<Access> list) {
        return super.updateRecords(list);
    }

    @PermissionChange
    @Override
    public Integer deleteById(Integer id) {
        return super.deleteById(id);
    }

    @PermissionChange
    @Override
    public Integer deleteByIds(String ids) {
        return super.deleteByIds(ids);
    }

    @PermissionChange
    @Override
    public Integer deleteByIds(List<Integer> list) {
        return super.deleteByIds(list);
    }

    @PermissionChange
    @Override
    public Integer deleteRecords(List<Access> list) {
        return super.deleteRecords(list);
    }
}
