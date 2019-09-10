package com.dj.ego.control;

import com.dj.ego.common.http.BaseControl;
import com.dj.ego.common.service.IService;
import com.dj.ego.entity.po.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 戴俊明
 * @className GoodControl
 * @description TODO
 * @date 2019/9/5 23:01
 **/
@RequestMapping("/good")
@RestController
public class GoodControl extends BaseControl<Good> {

    @Autowired
    @Override
    protected void setService(IService<Good> service) {
        super.setService(service);
    }
}
