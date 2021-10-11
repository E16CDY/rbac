package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.PageParam;
import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.utils.PageResult;

import java.util.List;

public interface IPermissionService {

    //分页查询
    PageResult findByPage(PageParam pageParam);

    //根据id删除数据
    void deleteById(Long id);

    //查询所有
    List<Permission> findAll();

}
