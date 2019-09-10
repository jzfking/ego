package com.dj.ego.common.service;

import cn.hutool.core.util.ReflectUtil;
import com.dj.ego.common.GlobalException;
import com.dj.ego.common.data.BaseDao;
import com.dj.ego.common.data.BaseModel;
import com.dj.ego.common.data.DataCheckUtil;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 戴俊明
 * @className BaseService
 * @description CRUD业务层实现
 * @date 2019/7/25 9:27
 **/
@Validated
public class BaseService<T extends BaseModel> implements IService<T> {

    private BaseDao<T> dao;

    @Override
    public T selectOneById(Integer id) {
        return DataCheckUtil.get(dao.selectByPrimaryKey(id));
    }

    @Override
    public T selectOne(T model) {
        return DataCheckUtil.get(dao.selectOne(model));
    }

    @Override
    public List<T> selectByIds(String ids) {
        return DataCheckUtil.get(dao.selectByIds(ids));
    }

    @Override
    public List<T> selectByIds(List<Integer> list) {
        return selectByIds(list.stream()
                .map(String::valueOf).collect(Collectors.joining(",")));
    }

    @Override
    public List<T> select(T model) {
        return DataCheckUtil.get(dao.select(model));
    }

    @Override
    public List<T> selectLike(T model) {
        @SuppressWarnings("unchecked")
        Class<T> TClass = (Class<T>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        Example example = new Example(TClass);
        Example.Criteria criteria = example.createCriteria();

        for (Field field : ReflectUtil.getFields(model.getClass())) {
            int mod = field.getModifiers();
            if (!Modifier.isStatic(mod)) {
                System.out.println(field);
                Object value = ReflectUtil.getFieldValue(model, field);
                if (value != null && !"".equals(value.toString())) {
                    criteria.andLike(field.getName(), "%" + value.toString() + "%");
                }
            }
        }

        return DataCheckUtil.get(dao.selectByExample(example));
    }

    @Override
    public List<T> selectAll() {
        return DataCheckUtil.get(dao.selectAll());
    }

    @Override
    public Integer selectCount(T model) {
        return DataCheckUtil.get(dao.selectCount(model));
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Integer insertRecord(T model) {
        return DataCheckUtil.get(dao.insertSelective(model), ServiceStatus.database_insert_error);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Integer insertRecords(List<T> list) {
        int result = 0;
        for (T record : list) {
            result += dao.insertSelective(record);
        }
        return result;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Integer updateRecord(T model) {
        return dao.updateByPrimaryKeySelective(model);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Integer updateRecords(List<T> list) {
        int result = 0;
        for (T record : list) {
            result += dao.updateByPrimaryKeySelective(record);
        }
        return result;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Integer deleteById(Integer id) {
        return DataCheckUtil.get(dao.deleteByPrimaryKey(id), ServiceStatus.database_delete_error);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Integer deleteByIds(String ids) {
        return DataCheckUtil.get(dao.deleteByIds(ids), ServiceStatus.database_delete_error);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Integer deleteByIds(List<Integer> list) {
        return deleteByIds(list.stream()
                .map(String::valueOf).collect(Collectors.joining(",")));
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Integer deleteRecords(List<T> list) {
        int result = 0;
        for (T record : list) {
            result += dao.delete(record);
        }
        return result;
    }


    @Override
    public BaseDao<T> getDao() {
        return dao;
    }

    @Override
    public <M> M getDao(Class<M> clazz) {
        M o;
        if (clazz.isInstance(dao)) {
            o = (M) dao;
        } else {
            throw GlobalException.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .serviceStatus(ServiceStatus.mapper_can_not_convert).build();
        }
        return o;
    }

    protected void setDao(BaseDao<T> dao) {
        this.dao = dao;
    }
}
