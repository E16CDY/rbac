package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.domain.PageParam;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

    //分页查询
    List<Department> selectByPage(PageParam pageParam);

    //查询总数据量
    int selectCount();
}