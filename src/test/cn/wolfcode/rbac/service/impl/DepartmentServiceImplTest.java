package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.service.IDepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentServiceImplTest {

    @Autowired
    private IDepartmentService departmentService;

    @Test
    public void saveOrUpdate() {
        // 测试添加操作
        //Department department = new Department(null,"大数据部门","Big-Data");
        //departmentService.saveOrUpdate(department);

        // 测试更新操作
        Department department = new Department(9L,"大数据部门2","Big-Data2");
        departmentService.saveOrUpdate(department);
    }

    @Test
    public void delete() {
        Long id = 8L;
        departmentService.delete(id);
    }

    @Test
    public void findOne() {
        Long id = 9L;
        Department department = departmentService.findOne(id);
        System.out.println("department = " + department);
    }

    @Test
    public void findAll() {
        List<Department> departments = departmentService.findAll();
        System.out.println("departments = " + departments);
    }
}