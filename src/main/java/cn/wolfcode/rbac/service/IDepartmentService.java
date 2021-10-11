package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.domain.PageParam;
import cn.wolfcode.rbac.utils.PageResult;

import java.util.List;

public interface IDepartmentService {

    void saveOrUpdate(Department department);
    void delete(Long id);
    Department findOne(Long id);
    List<Department> findAll();

    //分页查询操作
    PageResult findByPage(PageParam pageParam);
}
