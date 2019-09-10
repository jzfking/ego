package com.dj.ego.service.crud;

import com.dj.ego.common.data.BaseDao;
import com.dj.ego.common.service.BaseService;
import com.dj.ego.entity.po.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 戴俊明
 * @className GoodServiceImpl
 * @description TODO
 * @date 2019/9/5 22:55
 **/
@Service
public class GoodServiceImpl extends BaseService<Good> {

    @Autowired
    @Override
    protected void setDao(BaseDao<Good> dao) {
        super.setDao(dao);
    }
}
