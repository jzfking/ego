package com.dj.ego.service.crud;

import com.dj.ego.common.data.BaseDao;
import com.dj.ego.common.service.BaseService;
import com.dj.ego.entity.po.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 戴俊明
 * @className AddressServiceImpl
 * @description TODO
 * @date 2019/9/5 22:50
 **/
@Service
public class AddressServiceImpl extends BaseService<Address> {

    @Autowired
    @Override
    protected void setDao(BaseDao<Address> dao) {
        super.setDao(dao);
    }
}
