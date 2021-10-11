package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.domain.PageParam;
import cn.wolfcode.rbac.mapper.DepartmentMapper;
import cn.wolfcode.rbac.service.IDepartmentService;
import cn.wolfcode.rbac.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DepartmentServiceImpl implements IDepartmentService {
    // 选中DepartmentServiceImpl 按ctrl+shift+t

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public void saveOrUpdate(Department department) {
        if( department.getId() != null ) {
            // 如果department对象提供了id，那么执行更新操作
            departmentMapper.updateByPrimaryKey(department);
        } else {
            // 如果department对象没有提供了id，那么执行添加操作
            departmentMapper.insert(department);
        }
    }

    @Override
    public void delete(Long id) {
        departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Department findOne(Long id) {
        Department department = departmentMapper.selectByPrimaryKey(id);
        return department;
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments = departmentMapper.selectAll();
        return departments;
    }

    @Override
    public PageResult findByPage(PageParam pageParam) {
        //查询表总数据量
        int totalCount = departmentMapper.selectCount();
        //如果totalcount <= 0  没有数据
        if(totalCount <= 0){
            return new PageResult(pageParam.getPageSize(),pageParam.getCurrentPage());
        }
        //有数据
        //分页查询数据
        List<Department> list = departmentMapper.selectByPage(pageParam);

        return new PageResult(list,totalCount,pageParam.getPageSize(),pageParam.getCurrentPage());
    }
}
