package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.domain.EmployeeParam;
import cn.wolfcode.rbac.mapper.EmployeeMapper;
import cn.wolfcode.rbac.service.IEmployeeService;
import cn.wolfcode.rbac.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> findAll() {
        //调用mapper查询所有方法
        return employeeMapper.selectAll();
    }

    @Override
    public List<Employee> findByCondition(EmployeeParam employeeParam) {

        return employeeMapper.selectByCondition(employeeParam);
    }

    @Override
    public PageResult findByPage(EmployeeParam employeeParam) {
        int totalCount = employeeMapper.selectCount(employeeParam);
        if (totalCount<0){
            return new PageResult(employeeParam.getPageSize(),employeeParam.getCurrentPage());
        }
        List<Employee> list = employeeMapper.selectByPage(employeeParam);
        return new PageResult(list,totalCount,employeeParam.getPageSize(),employeeParam.getCurrentPage());
    }

    @Override
    public Employee findById(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveOrUpdate(Employee employee, Long[] roleIds) {
        //加密密码
        byte[] bytes = employee.getPassword().getBytes();
        String password = DigestUtils.md5DigestAsHex(bytes);
        employee.setPassword(password);

        if(employee.getId() != null) {
            //更新
            employeeMapper.updateByPrimaryKey(employee);
            employeeMapper.deleteByEmpId(employee.getId());
        } else {
            //添加
            employeeMapper.insert(employee);
        }
        //保存
        if (roleIds != null) {
            for (Long roleId : roleIds) {
                employeeMapper.saveEmpRole(employee.getId(),roleId);
            }
        }

    }

    @Override
    public void deleteById(Long id) {
        employeeMapper.deleteByPrimaryKey(id);
        employeeMapper.deleteByEmpId(id);
    }
}
