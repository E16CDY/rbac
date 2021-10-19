package cn.wolfcode.rbac.controller;

import cn.wolfcode.rbac.domain.PageParam;
import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.service.IPermissionService;
import cn.wolfcode.rbac.utils.PageResult;
import cn.wolfcode.rbac.utils.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/list")
    @RequiredPermission({"权限列表","permission:list"})
    public String list(Model model, PageParam pageParam) {
        PageResult pageResult = permissionService.findByPage(pageParam);
        model.addAttribute("pageResult",pageResult);
        return "permission/list";
    }

    @RequestMapping("/reload")
    @RequiredPermission({"权限加载","permission:reload"})
    public String reload() {
        permissionService.reload();
        return "redirect:/permission/list";
    }

    @RequestMapping("delete")
    public String delete(Long id) {
        permissionService.deleteById(id);
        return "redirect:/permission/list";
    }
}
