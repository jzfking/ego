package com.dj.ego.common.data;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import com.dj.ego.common.GlobalException;
import com.dj.ego.common.service.ServiceStatus;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author 戴俊明
 * @className DataCheckUtil
 * @description 数据检测工具
 * @date 2019/8/24 9:49
 **/

public class DataCheckUtil {
    /**
     * @return java.lang.Integer
     * @author 戴俊明
     * @description 检查查询记录数量
     * @date 2019/8/27 11:39
     **/
    public static Integer get(Integer count) {
        count = Optional.ofNullable(count).orElseThrow(() ->
                GlobalException.builder()
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .serviceStatus(ServiceStatus.database_error).build());
        if (count < 0) {
            throw GlobalException.builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .serviceStatus(ServiceStatus.database_error).build();
        } else {
            return count;
        }
    }

    /**
     * @return java.lang.Integer
     * @author 戴俊明
     * @description 检查值的范围
     * @date 2019/8/27 11:40
     **/
    public static Integer get(Integer result, ServiceStatus status) {
        result = Optional.ofNullable(result).orElseThrow(() ->
                GlobalException.builder()
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .serviceStatus(status).build());
        if (result <= 0) {
            throw GlobalException.builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .serviceStatus(status).build();
        } else {
            return result;
        }
    }

    /**
     * @return java.lang.Integer
     * @author 戴俊明
     * @description 检查值的范围
     * @date 2019/8/27 11:48
     **/
    public static <X extends Throwable> Integer get(Integer result, int start, Supplier<? extends X> exceptionSupplier) throws X {
        result = Optional.ofNullable(result).orElseThrow(exceptionSupplier);
        if (result < start || result >= Integer.MAX_VALUE) {
            throw exceptionSupplier.get();
        } else {
            return result;
        }
    }

    /**
     * @return java.lang.Integer
     * @author 戴俊明
     * @description 检查值的范围
     * @date 2019/8/27 11:45
     **/
    public static <X extends Throwable> Integer get(Integer result, int start, int end, Supplier<? extends X> exceptionSupplier) throws X {
        result = Optional.ofNullable(result).orElseThrow(exceptionSupplier);
        if (result < start || result >= end) {
            throw exceptionSupplier.get();
        } else {
            return result;
        }
    }

    /**
     * @return M
     * @author 戴俊明
     * @description 检查值不为null
     * @date 2019/8/27 11:41
     **/
    public static <M> M get(M model) {
        return Optional.ofNullable(model).orElseThrow(() ->
                GlobalException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .serviceStatus(ServiceStatus.database_can_not_find).build());
    }

    /**
     * @return M
     * @author 戴俊明
     * @description 检查值不为null
     * @date 2019/8/27 11:43
     **/
    public static <M, X extends Throwable> M get(M model, Supplier<? extends X> exceptionSupplier) throws X {
        return Optional.ofNullable(model).orElseThrow(exceptionSupplier);
    }

    /**
     * @return java.util.List<M>
     * @author 戴俊明
     * @description 检查列表不为空
     * @date 2019/8/27 11:42
     **/
    public static <M> List<M> get(List<M> list) {
        if (list == null) {
            throw GlobalException.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .serviceStatus(ServiceStatus.database_can_not_find).build();
        } else if (list.size() > 0) {
            return list;
        } else {
            throw GlobalException.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .serviceStatus(ServiceStatus.database_can_not_find).build();
        }
    }

    /**
     * @return java.util.List<M>
     * @author 戴俊明
     * @description 检查列表不为空
     * @date 2019/8/27 11:42
     **/
    public static <M, X extends Throwable> List<M> get(List<M> list, Supplier<? extends X> exceptionSupplier) throws X {
        if (list == null) {
            throw exceptionSupplier.get();
        } else if (list.size() > 0) {
            return list;
        } else {
            throw exceptionSupplier.get();
        }
    }

    public static boolean isNone(Object o) {
        return BeanUtil.isEmpty(o);
    }

    public static boolean hasNull(Object o) {
        return BeanUtil.hasNullField(o);
    }

    public static boolean hasId(BaseModel o) {
        if (null == o) {
            return false;
        }
        return o.getId() != null;
    }

    public static boolean hasOtherProperties(BaseModel o) {
        if (null == o) {
            return false;
        }

        for (Field field : ReflectUtil.getFields(o.getClass())) {
            int mod = field.getModifiers();
            if (Modifier.isPrivate(mod) && !Modifier.isStatic(mod)) {
                System.out.println(field);
                if (null != ReflectUtil.getFieldValue(o, field)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static <M> M validateSelect(M o) {
        if (isNone(o)) {
            throw GlobalException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .serviceStatus(ServiceStatus.bean_is_none)
                    .build();
        }
        return o;
    }

    public static <M> List<M> validateSelect(List<M> o) {
        for (M m : o) {
            if (isNone(m)) {
                throw GlobalException.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .serviceStatus(ServiceStatus.bean_is_none)
                        .build();
            }
        }
        return o;
    }

    public static <M> M validateInsert(M o) {
        if (isNone(o)) {
            throw GlobalException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .serviceStatus(ServiceStatus.bean_is_none)
                    .build();
        }
        return o;
    }

    public static <M> List<M> validateInsert(List<M> o) {
        for (M m : o) {
            if (isNone(m)) {
                throw GlobalException.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .serviceStatus(ServiceStatus.bean_is_none)
                        .build();
            }
        }
        return o;
    }

    public static BaseModel validateUpdate(BaseModel o) {
        if (!hasId(o) || !hasOtherProperties(o)) {
            throw GlobalException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .serviceStatus(ServiceStatus.bean_can_not_update)
                    .build();
        }
        return o;
    }

    public static <M> List<M> validateUpdate(List<M> o) {
        for (M model : o) {
            validateUpdate((BaseModel) model);
        }
        return o;
    }

    public static <M> M validateDelete(M o) {
        if (isNone(o)) {
            throw GlobalException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .serviceStatus(ServiceStatus.bean_is_none)
                    .build();
        }
        return o;
    }

    public static <M> List<M> validateDelete(List<M> o) {
        for (M m : o) {
            if (isNone(m)) {
                throw GlobalException.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .serviceStatus(ServiceStatus.bean_is_none)
                        .build();
            }
        }
        return o;
    }

}
