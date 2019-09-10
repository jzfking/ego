package com.dj.ego.control;

import com.dj.ego.common.http.BaseControl;
import com.dj.ego.common.service.IService;
import com.dj.ego.entity.po.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 戴俊明
 * @className AreaControl
 * @description TODO
 * @date 2019/9/5 22:58
 **/
@RequestMapping("/area")
@RestController
public class AreaControl extends BaseControl<Area> {

    @Autowired
    @Override
    protected void setService(IService<Area> service) {
        super.setService(service);
    }
}
