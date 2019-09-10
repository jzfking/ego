package com.dj.ego.shiro.control;

import com.dj.ego.common.http.BaseControl;
import com.dj.ego.common.service.IService;
import com.dj.ego.shiro.entity.po.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 戴俊明
 * @className AccessControl
 * @date 2019/9/5 8:47
 **/
@RequestMapping("/access")
@RestController
public class AccessControl extends BaseControl<Access> {

    @Autowired
    @Override
    protected void setService(IService<Access> service) {
        super.setService(service);
    }
}
