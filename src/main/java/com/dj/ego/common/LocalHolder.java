package com.dj.ego.common;

/**
 * @author 戴俊明
 * @className LocalHolder
 * @description 本地变量基础类
 * @date 2019/8/27 10:48
 **/

public class LocalHolder {
    /**
     * @author 戴俊明
     * @description 线程的本地变量
     * @date 2019/8/27 11:49
     **/
    private final static ThreadLocal<Object> HOLDER = new ThreadLocal<>();

    /**
     * @return void
     * @author 戴俊明
     * @description 存储
     * @date 2019/8/27 11:49
     **/
    protected static void setValue(Object value) {
        HOLDER.set(value);
    }

    /**
     * @return java.lang.Object
     * @author 戴俊明
     * @description 获取
     * @date 2019/8/27 11:50
     **/
    protected static Object getValue() {
        return HOLDER.get();
    }

    /**
     * @return void
     * @author 戴俊明
     * @description 清除
     * @date 2019/8/27 11:50
     **/
    protected static void clear() {
        HOLDER.remove();
    }

}
