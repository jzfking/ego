package com.dj.ego.common.http;

import com.dj.ego.annotation.Pagination;
import com.dj.ego.common.GlobalException;
import com.dj.ego.common.data.BaseModel;
import com.dj.ego.common.data.DataCheckUtil;
import com.dj.ego.common.service.IService;
import com.dj.ego.common.service.ServiceStatus;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * @author 戴俊明
 * @className BaseControl
 * @description CRUD控制层实现
 * @date 2019/7/25 9:37
 **/
@Validated
public class BaseControl<T extends BaseModel> implements IControl<T> {

    private IService<T> service;

    @ApiOperation("通过id查找一条记录")
    @ApiImplicitParam(name = "id", value = "记录id", required = true, paramType = "path", dataType = "Integer")
    @GetMapping("/id/{id}")
    @Override
    public ResponseEntity selectOneById(@PathVariable Integer id) {
        return ResponseEntity.ok(HttpBodyEntity.builder().data(service.selectOneById(id)).build());
    }

    @ApiOperation("通过对象精确查找属性相同的一条记录")
    @ApiImplicitParam(name = "model", value = "记录样例", required = true, paramType = "body")
    @PostMapping("/search")
    @Override
    public ResponseEntity selectOne(@RequestBody T model) {
        return ResponseEntity.ok(HttpBodyEntity.builder().data(service.selectOne(DataCheckUtil.validateSelect(model))).build());
    }

    @ApiOperation("通过ids查找多条记录")
    @ApiImplicitParam(name = "ids", value = "记录id使用 , 拼接的字符串", required = true, paramType = "path")
    @GetMapping("/ids/{ids}")
    @Override
    public ResponseEntity selectByIds(@PathVariable String ids) {
        return ResponseEntity.ok(HttpBodyEntity.builder().data(service.selectByIds(ids)).build());
    }

    @ApiOperation(value = "通过ids查找多条记录")
    @ApiImplicitParam(name = "ids", value = "记录id列表", required = true, paramType = "body")
    @PostMapping("/list/id")
    @Override
    public ResponseEntity selectByIds(@RequestBody List<Integer> list) {
        return ResponseEntity.ok(HttpBodyEntity.builder().data(service.selectByIds(list)).build());
    }

    @Pagination
    @ApiOperation("通过对象精确查找属性相似的多条记录")
    @ApiImplicitParam(name = "model", value = "记录样例", required = true, paramType = "body")
    @PostMapping("/list/search")
    @Override
    public ResponseEntity select(@RequestBody T model) {
        return ResponseEntity.ok(HttpBodyEntity.builder().data(service.select(DataCheckUtil.validateSelect(model))).build());
    }

    @Pagination
    @ApiOperation("通过对象模糊查找属性相似的多条记录")
    @ApiImplicitParam(name = "model", value = "记录样例", required = true, paramType = "body")
    @PostMapping("/list/like")
    @Override
    public ResponseEntity selectLike(T model) {
        return ResponseEntity.ok(HttpBodyEntity.builder().data(service.selectLike(DataCheckUtil.validateSelect(model))).build());
    }

    @Pagination
    @ApiOperation("查询全部记录")
    @GetMapping("/list")
    @Override
    public ResponseEntity selectAll() {
        return ResponseEntity.ok(HttpBodyEntity.builder().data(service.selectAll()).build());
    }

    @ApiOperation("通过对象精确查找属性相同的记录个数")
    @ApiImplicitParam(name = "model", value = "记录样例", required = true, paramType = "body")
    @PostMapping("/count")
    @Override
    public ResponseEntity selectCount(@RequestBody T model) {
        return ResponseEntity.ok(HttpBodyEntity.builder().data(service.selectCount(DataCheckUtil.validateSelect(model))).build());
    }

    @ApiOperation(value = "插入一条记录", notes = "id和某些非必须属性可为空")
    @ApiImplicitParam(name = "model", value = "记录", required = true, paramType = "body")
    @PostMapping
    @Override
    public ResponseEntity insertRecord(@RequestBody T model) {
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpBodyEntity.builder().data(service.insertRecord(DataCheckUtil.validateInsert(model))).build());
    }

    @ApiOperation(value = "插入多条记录", notes = "id属性可为空")
    @ApiImplicitParam(name = "list", value = "记录样例列表", required = true, paramType = "body")
    @PostMapping("/list")
    @Override
    public ResponseEntity insertRecords(@RequestBody List<T> list) {
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpBodyEntity.builder().data(service.insertRecords(DataCheckUtil.validateInsert(list))).build());
    }

    @ApiOperation(value = "更新一条记录", notes = "id不可为空且必须存在至少一个其它属性")
    @ApiImplicitParam(name = "model", value = "记录", required = true, paramType = "body")
    @PutMapping
    @Override
    public ResponseEntity updateRecord(@RequestBody T model) {
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpBodyEntity.builder().data(service.updateRecord((T) DataCheckUtil.validateUpdate(model))).build());
    }

    @ApiOperation(value = "更新多条记录", notes = "id不可为空且必须存在至少一个其它属性")
    @ApiImplicitParam(name = "list", value = "记录样例列表", required = true, paramType = "body")
    @PutMapping("/list")
    @Override
    public ResponseEntity updateRecords(@RequestBody List<T> list) {
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpBodyEntity.builder().data(service.updateRecords(DataCheckUtil.validateUpdate(list))).build());
    }

    @ApiOperation(value = "通过id删除一条记录")
    @ApiImplicitParam(name = "id", value = "记录id", required = true, paramType = "path")
    @DeleteMapping("/id/{id}")
    @Override
    public ResponseEntity deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "通过ids删除多条记录")
    @ApiImplicitParam(name = "ids", value = "记录id使用 , 拼接的字符串", required = true, paramType = "path")
    @DeleteMapping("/ids/{ids}")
    @Override
    public ResponseEntity deleteByIds(@PathVariable String ids) {
        service.deleteByIds(ids);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "通过ids删除多条记录")
    @ApiImplicitParam(name = "ids", value = "记录id列表", required = true, paramType = "body")
    @DeleteMapping("/list/id")
    @Override
    public ResponseEntity deleteByIds(@RequestBody List<Integer> ids) {
        service.deleteByIds(ids);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "删除多条记录")
    @ApiImplicitParam(name = "list", value = "记录样例列表", required = true, paramType = "body")
    @DeleteMapping("/list")
    @Override
    public ResponseEntity deleteRecords(@RequestBody List<T> list) {
        service.deleteRecords(DataCheckUtil.validateDelete(list));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    @Override
    public HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
    }

    @Override
    public IService<T> getService() {
        return service;
    }

    @Override
    public <M> M getService(Class<M> clazz) {
        M o;
        if (clazz.isInstance(service)) {
            o = (M) service;
        } else {
            throw GlobalException.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .serviceStatus(ServiceStatus.service_can_not_convert).build();
        }
        return o;
    }

    protected void setService(IService<T> service) {
        this.service = service;
    }
}
