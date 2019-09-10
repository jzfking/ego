package com.dj.ego.shiro.service.crud;

import com.dj.ego.common.data.BaseDao;
import com.dj.ego.common.service.BaseService;
import com.dj.ego.shiro.entity.po.EmailCode;
import com.dj.ego.shiro.service.EmailCodeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 戴俊明
 * @className EmailCodeServiceImpl
 * @description 邮箱验证码业务
 * @date 2019/8/24 10:15
 **/
@Service
public class EmailCodeServiceImpl extends BaseService<EmailCode> implements EmailCodeManager {

    @Autowired
    @Override
    protected void setDao(BaseDao<EmailCode> dao) {
        super.setDao(dao);
    }

    @Override
    public void setCode(Integer id, String code) {
        EmailCode emailCode = this.getDao().selectByPrimaryKey(id);
        if (this.selectOneById(id) != null) {
            emailCode.setId(id);
            emailCode.setCode(code);
            this.updateRecord(emailCode);
        } else {
            emailCode.setId(id);
            emailCode.setCode(code);
            this.insertRecord(emailCode);
        }
    }

    @Override
    public void remove(Integer id) {
        this.getDao().deleteByPrimaryKey(id);
    }
}
