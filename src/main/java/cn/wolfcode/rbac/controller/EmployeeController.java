package cn.wolfcode.rbac.controller;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.domain.Employee;
import cn.wolfcode.rbac.domain.EmployeeParam;
import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.service.IDepartmentService;
import cn.wolfcode.rbac.service.IEmployeeService;
import cn.wolfcode.rbac.service.IRoleService;
import cn.wolfcode.rbac.utils.PageResult;
import cn.wolfcode.rbac.utils.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping("list")
    @RequiredPermission({"员工列表","employee:list"})
    public String list(Model model, @ModelAttribute("qo") EmployeeParam employeeParam) {
        //分页查询
        PageResult pageResult = employeeService.findByPage(employeeParam);
        model.addAttribute("pageResult", pageResult);

        //查询所有的部门信息
        List<Department> departmentList = departmentService.findAll();
        model.addAttribute("departmentList", departmentList);

        //跳转employee下list.jsp
        return "/employee/list";
    }

    @RequestMapping("/input")
    @RequiredPermission({"员工编辑","employee:input"})
    public String input(Model model, Long id) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        List<Role> roles = roleService.selectAll();
        model.addAttribute("roles", roles);
        return "/employee/input";
    }

    @RequestMapping("/saveOrUpdate")
    @RequiredPermission({"员工保存或更新","employee:saveOrUpdate"})
    public String saveOrUpdate(Employee employee, Long[] roleIds) {
        employeeService.saveOrUpdate(employee, roleIds);
        return "redirect:/employee/list";
    }

    @RequestMapping("/delete")
    @RequiredPermission({"员工删除","employee:delete"})
    public String delete(Long id) {
        employeeService.deleteById(id);
        return "redirect:/employee/list";
    }
}
