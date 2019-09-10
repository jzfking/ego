package com.dj.ego.common.service;

import com.dj.ego.shiro.entity.po.Role;

/**
 * @author 戴俊明
 * @version 1.0
 * @className ServiceStatus
 * @description 业务状态码
 * @date 2019/8/16 15:21
 **/
public enum ServiceStatus {
    /**
     * @author 戴俊明
     * @description TODO
     * @date 2019/8/16 15:24
     **/
    page_param_error(401, "分页参数异常"),

    can_not_get_request(402, "无法获取请求"),

    role_is_error(500, "角色错误"),

    mail_can_not_init(501, "无法初始化邮件"),

    file_can_not_find(502, "无法找到文件"),

    token_time_Expired(503, "token过期"),

    token_invalid(504, "token无效"),

    not_a_account_waiting_for_verification(504, "这不是一个等待验证的账号"),

    id_invalid(505, "id不能为空或小于1"),

    role_code_invalid(506, "role.code需要匹配正则表达式" + Role.EMAIL_PATTERN),

    login_failure(507, "登录失败"),

    bean_is_none(580, "bean为空对象"),

    bean_can_not_update(581, "无法根据该bean更新"),

    database_has_insert(590, "已经存在该记录"),

    database_can_not_find(591, "无法找到记录"),

    database_insert_error(592, "无法插入记录"),

    database_update_error(593, "无法更新记录"),

    database_delete_error(594, "无法删除记录"),

    database_error(595, "数据库异常"),

    mapper_can_not_convert(691, "无法转化Mapper实例"),

    service_can_not_convert(692, "无法转化service实例"),

    spring_can_not_find_bean(693, "无法获取bean实例");

    ServiceStatus(Integer code, String description) {
        this.status = code;
        this.description = description;
    }

    /**
     * @author 戴俊明
     * @description 业务状态码
     * @date 2019/8/16 15:23
     **/
    private Integer status;
    /**
     * @author 戴俊明
     * @description 业务状态描述
     * @date 2019/8/16 15:23
     **/
    private String description;

    public Integer getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

}
