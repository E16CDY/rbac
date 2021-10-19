package cn.wolfcode.rbac.controller;

import cn.wolfcode.rbac.domain.PageParam;
import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.service.IPermissionService;
import cn.wolfcode.rbac.service.IRoleService;
import cn.wolfcode.rbac.utils.PageResult;
import cn.wolfcode.rbac.utils.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/list")
    @RequiredPermission({"角色列表","role:list"})
    public String list(PageParam pageParam, Model model) {
        PageResult pageResult = roleService.findByPage(pageParam);
        model.addAttribute("pageResult",pageResult);
        return "/role/list";
    }

    @RequestMapping("/input")
    @RequiredPermission({"角色编辑","role:input"})
    public String input(Model model,Long id) {

        //角色和权限
        Role role = roleService.findByRid(id);
        model.addAttribute("role",role);

        //所有权限信息
        List<Permission> permissions = permissionService.findAll();
        model.addAttribute("permissions",permissions);
        return "/role/input";
    }

    @RequestMapping("/saveOrUpdate")
    @RequiredPermission({"角色保存或更新","role:saveOrUpdate"})
    public String saveOrUpdate(Role role,Long[] perIds) {
        roleService.saveOrUpdate(role,perIds);
        return "redirect:/role/list";
    }

    @RequestMapping("/delete")
    @RequiredPermission({"角色删除","role:delete"})
    public String delete(Long id) {
        roleService.deleteById(id);
        return "redirect:/role/list";
    }
}
