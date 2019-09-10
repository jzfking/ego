package com.dj.ego.service.crud;

import com.dj.ego.common.data.BaseDao;
import com.dj.ego.common.service.BaseService;
import com.dj.ego.entity.po.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 戴俊明
 * @className AreaServiceImpl
 * @description TODO
 * @date 2019/9/5 22:53
 **/
@Service
public class AreaServiceImpl extends BaseService<Area> {

    @Autowired
    @Override
    protected void setDao(BaseDao<Area> dao) {
        super.setDao(dao);
    }
}
