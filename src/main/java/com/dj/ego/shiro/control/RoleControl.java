package com.dj.ego.shiro.control;

import com.dj.ego.common.http.BaseControl;
import com.dj.ego.common.service.IService;
import com.dj.ego.shiro.entity.po.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 戴俊明
 * @className RoleControl
 * @date 2019/8/26 11:21
 **/

@RequestMapping("/role")
@RestController
public class RoleControl extends BaseControl<Role> {

    @Autowired
    @Override
    protected void setService(IService<Role> service) {
        super.setService(service);
    }
}
