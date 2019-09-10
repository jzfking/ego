package com.dj.ego.common.http;

import com.dj.ego.common.data.BaseModel;
import com.dj.ego.common.service.IService;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 戴俊明
 * @className IControl
 * @description Control层接口
 * @date 2019/7/24 14:39
 **/

public interface IControl<T extends BaseModel> {

    ResponseEntity selectOneById(@Min(value = 1, message = "id最小不能小于1") Integer id);

    ResponseEntity selectOne(@NotNull T model);

    ResponseEntity selectByIds(@Length(min = 1) String ids);

    ResponseEntity selectByIds(@NotEmpty List<Integer> list);

    ResponseEntity select(@NotNull T model);

    ResponseEntity selectLike(@NotNull T model);

    ResponseEntity selectAll();

    ResponseEntity selectCount(@NotNull T model);

    ResponseEntity insertRecord(@NotNull T model);

    ResponseEntity insertRecords(@NotEmpty List<T> list);

    ResponseEntity updateRecord(@NotNull T model);

    ResponseEntity updateRecords(@NotEmpty List<T> list);

    ResponseEntity deleteById(@Min(value = 1, message = "id最小不能小于1") Integer id);

    ResponseEntity deleteByIds(@Length(min = 1) String ids);

    ResponseEntity deleteByIds(@NotEmpty List<Integer> ids);

    ResponseEntity deleteRecords(@NotEmpty List<T> list);

    HttpServletRequest getRequest();

    HttpServletResponse getResponse();

    IService<T> getService();

    <M> M getService(@NotNull Class<M> clazz);

    /*
     * @param service
     * @return void
     * @author 戴俊明
     * @description 继承BaseControl的子类必须重写该方法
     * @date 2019/8/3 10:17
     **/
//    void setService(BaseService<T> service);
    /*
     * 1. 加注解
     * @RequestMapping("xxx")
     * @RestController
     * 2. 继承 BaseControl
     * 3. 重写 void setService(BaseService<T> service);
     * 4.
     * @Autowired
     * @Qualifier(value = "xxxService")
     * */
}
