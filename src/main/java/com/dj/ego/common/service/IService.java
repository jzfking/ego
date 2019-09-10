package com.dj.ego.common.service;

import com.dj.ego.common.data.BaseDao;
import com.dj.ego.common.data.BaseModel;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 戴俊明
 * @className IService
 * @description TODO
 * @date 2019/7/25 9:17
 **/

public interface IService<T extends BaseModel> {

    T selectOneById(@NotNull Integer id);

    T selectOne(@NotNull T model);

    List<T> selectByIds(@NotNull String ids);

    List<T> selectByIds(@NotEmpty List<Integer> list);

    List<T> select(@NotNull T model);

    List<T> selectLike(@NotNull T model);

    List<T> selectAll();

    Integer selectCount(@NotNull T model);

    Integer insertRecord(@NotNull @Valid T model);

    Integer insertRecords(@NotEmpty List<T> list);

    Integer updateRecord(@NotNull T model);

    Integer updateRecords(@NotEmpty List<T> list);

    Integer deleteById(@NotNull Integer id);

    Integer deleteByIds(@NotNull String ids);

    Integer deleteByIds(@NotEmpty List<Integer> list);

    Integer deleteRecords(@NotEmpty List<T> list);

    BaseDao<T> getDao();

    <M> M getDao(@NotNull Class<M> clazz);

    /*
     * @param dao
     * @return void
     * @author 戴俊明
     * @description 继承BaseService的子类必须重写该方法
     * @date 2019/8/3 10:41
     **/
//    void setDao(BaseDao<T> dao);
    /*
     * 1. 加注解 @Service(value = "xxxService")
     * 2. 继承 BaseService
     * 3. 重写 void setDao(BaseDao<T> dao);
     * */
}
