package com.dj.ego.common.http;

import com.dj.ego.common.LocalHolder;

/**
 * @author 戴俊明
 * @className RequestHolder
 * @description PageHelper本地变量
 * @date 2019/7/25 10:49
 **/

public class RequestHolder extends LocalHolder {

    public static void openPage(Boolean isOpenPage) {
        setValue(isOpenPage);
    }

    public static Boolean isOpenPage() {
        return (Boolean) getValue();
    }

    public static void remove() {
        clear();
    }

}
