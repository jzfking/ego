package com.dj.ego.aop;

import com.dj.ego.annotation.Pagination;
import com.dj.ego.common.http.HttpBodyEntity;
import com.dj.ego.common.http.RequestHolder;
import com.github.pagehelper.PageInfo;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

/**
 * @author 戴俊明
 * @className PaginationResponseBodyAdvice
 * @description pageHelper修改响应信息
 * @date 2019/7/24 22:28
 **/

@RestControllerAdvice
public class PaginationResponseBodyAdvice implements ResponseBodyAdvice {
    /**
     * @return boolean
     * @author 戴俊明
     * @description 判断是否有Pagination注解
     * @date 2019/8/27 11:38
     **/
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return methodParameter.hasMethodAnnotation(Pagination.class);
    }

    /**
     * @return java.lang.Object
     * @author 戴俊明
     * @description pageHelper截取返回json重写
     * @date 2019/8/16 15:17
     **/
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (RequestHolder.isOpenPage() && o instanceof HttpBodyEntity) {
            HttpBodyEntity entity = (HttpBodyEntity) o;
            Object data = entity.getData();
            if (data instanceof List) {
                entity.setData(PageInfo.of((List<?>) data));
            }
        }
        return o;
    }
}
