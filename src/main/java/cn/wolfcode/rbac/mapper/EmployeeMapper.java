package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.domain.EmployeeParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    //条件查询
    List<Employee> selectByCondition(EmployeeParam employeeParam);

    //分页查询
    List<Employee> selectByPage(EmployeeParam employeeParam);

    //查询总数据量
    int selectCount(EmployeeParam employeeParam);

    //存储员工角色信息
    void saveEmpRole(@Param("empId") Long empId,@Param("roleId") Long roleId);

    //根据员工id删除员工角色
    void deleteByEmpId(Long empId);
}