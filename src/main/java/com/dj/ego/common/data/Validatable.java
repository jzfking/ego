package com.dj.ego.common.data;

/**
 * @author 戴俊明
 * @className Validatable
 * @description 检查数据对象的接口
 * @date 2019/9/5 10:45
 **/

public interface Validatable extends Nullable {

    boolean hasId();

    boolean hasOtherProperties();

    void validateSelect();

    void validateInsert();

    void validateUpdate();

    void validateDelete();
}
