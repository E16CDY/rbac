package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.PageParam;
import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.mapper.PermissionMapper;
import cn.wolfcode.rbac.service.IPermissionService;
import cn.wolfcode.rbac.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public PageResult findByPage(PageParam pageParam) {
        int totalCount = permissionMapper.selectCount();
        if (totalCount == 0) {
            return new PageResult(pageParam.getPageSize(),pageParam.getCurrentPage());
        }
        List<Permission> list = permissionMapper.selectByPage(pageParam);
        return new PageResult(list,totalCount,pageParam.getPageSize(),pageParam.getCurrentPage());
    }

    @Override
    public void deleteById(Long id) {
        permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Permission> findAll() {
        return permissionMapper.selectAll();
    }


}
