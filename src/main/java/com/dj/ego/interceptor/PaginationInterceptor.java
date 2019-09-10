package com.dj.ego.interceptor;

import cn.hutool.core.util.StrUtil;
import com.dj.ego.annotation.Pagination;
import com.dj.ego.common.GlobalException;
import com.dj.ego.common.http.RequestHolder;
import com.dj.ego.common.service.ServiceStatus;
import com.github.pagehelper.PageHelper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author 戴俊明
 * @className PaginationInterceptor
 * @description pageHelper插件拦截器
 * @date 2019/7/24 18:16
 **/

@Component
public class PaginationInterceptor extends HandlerInterceptorAdapter {
    /**
     * @author 戴俊明
     * @description 当前页
     * @date 2019/8/27 11:51
     **/
    private static final String PAGE_NUM = "pageNum";
    /**
     * @author 戴俊明
     * @description 页记录数
     * @date 2019/8/27 11:51
     **/
    private static final String PAGE_SIZE = "pageSize";
    /**
     * @author 戴俊明
     * @description 不分页
     * @date 2019/8/27 11:51
     **/
    private static final String NO_PAGE = "noPage";

    /**
     * @return boolean
     * @author 戴俊明
     * @description 请求拦截
     * @date 2019/8/27 11:52
     **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws RuntimeException {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.getAnnotation(Pagination.class) == null) {
            return true;
        }
        String nopage = request.getHeader(NO_PAGE);
        if (StrUtil.isEmpty(nopage)) {
            RequestHolder.openPage(true);
            String pageNum = request.getHeader(PAGE_NUM);
            String pageSize = request.getHeader(PAGE_SIZE);
            if (StrUtil.isEmpty(pageNum) || StrUtil.isEmpty(pageSize)) {
                RequestHolder.openPage(false);
            } else {
                try {
                    int num = Integer.valueOf(pageNum);
                    int size = Integer.valueOf(pageSize);
                    PageHelper.startPage(num, size);
                } catch (NumberFormatException e) {
                    throw GlobalException.builder().serviceStatus(ServiceStatus.page_param_error).httpStatus(HttpStatus.BAD_REQUEST).build();
                }
            }
        } else {
            RequestHolder.openPage(false);
        }
        return true;
    }

    /**
     * @return void
     * @author 戴俊明
     * @description 清空本地变量
     * @date 2019/8/27 11:52
     **/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        PageHelper.clearPage();
        RequestHolder.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
