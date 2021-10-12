package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.PageParam;
import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.mapper.PermissionMapper;
import cn.wolfcode.rbac.mapper.RoleMapper;
import cn.wolfcode.rbac.service.IRoleService;
import cn.wolfcode.rbac.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }


    @Override
    public PageResult findByPage(PageParam pageParam) {
        int totalCount = roleMapper.selectByCount();
        if (totalCount == 0) {
            return new PageResult(pageParam.getPageSize(),pageParam.getCurrentPage());
        }
        List<Role> list = roleMapper.selectByPage(pageParam);
        return new PageResult(list,totalCount,pageParam.getPageSize(),pageParam.getCurrentPage());
    }

    @Override
    public void saveOrUpdate(Role role,Long[] perIds) {
        if (role.getId() != null) {
            //更新
            roleMapper.updateByPrimaryKey(role);
            roleMapper.deleteByRPId(role.getId());
        } else {
            //添加
            roleMapper.insert(role);
        }
        //保存
        if (perIds != null) {
            for (Long perId : perIds) {
                roleMapper.saveRP(role.getId(),perId);
            }
        }
    }

    @Override
    public Role findById(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Long id) {
        roleMapper.deleteByPrimaryKey(id);
        roleMapper.deleteByRPId(id);
    }

    @Override
    public Role findByRid(Long id) {
            Role role = roleMapper.selectByPrimaryKey(id);
        try {
            List<Permission> permissions = permissionMapper.selectByRid(id);
            role.setPermissions(permissions);
        } catch (Exception e) {
        }
        return role;

    }
}
