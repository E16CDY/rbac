package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.PageParam;
import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.utils.PageResult;

import java.util.List;

public interface IRoleService {

    List<Role> selectAll();

    //分页查询
    PageResult findByPage(PageParam pageParam);

    //保存或更新
    void saveOrUpdate(Role role,Long[] perIds);

    //根据id查询
    Role findById(Long id);

    //根据id删除数据
    void deleteById(Long id);

    //根据角色id查询所有权限
    Role findByRid(Long id);
}
