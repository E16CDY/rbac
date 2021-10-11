package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.domain.EmployeeParam;
import cn.wolfcode.rbac.utils.PageResult;

import java.util.List;

public interface IEmployeeService {
    //查询所有
    List<Employee> findAll();

    //条件查询
    List<Employee> findByCondition(EmployeeParam employeeParam);

    //分页查询
    PageResult findByPage(EmployeeParam employeeParam);

    //根据id查询
    Employee findById(Long id);

    //更新或添加
    void saveOrUpdate(Employee employee,Long[] roleIds);

    //根据id删除
    void deleteById(Long id);

}
