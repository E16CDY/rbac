package cn.wolfcode.rbac.controller;


import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.domain.PageParam;
import cn.wolfcode.rbac.service.IDepartmentService;
import cn.wolfcode.rbac.utils.PageResult;
import cn.wolfcode.rbac.utils.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("/list")
    @RequiredPermission({"部门列表","department:list"})
    public String list(Model model, PageParam pageParam){

        // step 1: 从数据库中把所有部门查询出来
        //分页查询部门信息
        PageResult pageResult = departmentService.findByPage(pageParam);
        model.addAttribute("pageResult",pageResult);

        return "department/list";
    }

    @RequestMapping("/input")
    @RequiredPermission({"部门添加","department:input"})
    public String input(Long id,Model model){

        // step 1： 查询指定id的部门
        Department department = departmentService.findOne(id);
        // step 2: 把查询出来的部门信息注入到model中
        model.addAttribute("department",department);
        // step 3: 回显部门信息
        return "department/input";
    }

    @RequestMapping("/saveOrUpdate")
    @RequiredPermission({"部门保存或更新","department:saveOrUpdate"})
    public String saveOrUpdate(Department department) {
        // step 1：添加操作 或者 更新操作
        departmentService.saveOrUpdate(department);
        // step 2: 刷新list页面（让页面重定向到list即可）
        return "redirect:/department/list";
    }

    @RequestMapping("/delete")
    @RequiredPermission({"部门删除","department:delete"})
    public String delete(Long id){
        // step 1: 删除指定id的部门
        departmentService.delete(id);
        // step 2:刷新list页面
        return "redirect:/department/list";
    }

}
