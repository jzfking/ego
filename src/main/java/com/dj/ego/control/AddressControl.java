package com.dj.ego.control;

import com.dj.ego.common.http.BaseControl;
import com.dj.ego.common.service.IService;
import com.dj.ego.entity.po.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 戴俊明
 * @className AddressControl
 * @description TODO
 * @date 2019/9/5 22:57
 **/
@RequestMapping("/address")
@RestController
public class AddressControl extends BaseControl<Address> {

    @Autowired
    @Override
    protected void setService(IService<Address> service) {
        super.setService(service);
    }
}
