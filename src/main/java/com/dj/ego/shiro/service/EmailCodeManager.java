package com.dj.ego.shiro.service;

/**
 * @author 戴俊明
 * @className EmailCodeManager
 * @description 邮箱验证码业务接口
 * @date 2019/9/5 9:11
 **/

public interface EmailCodeManager {

    public void setCode(Integer id, String code);

    public void remove(Integer id);

}
