package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.PageParam;
import cn.wolfcode.rbac.domain.Permission;
import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    //分页查询
    List<Permission> selectByPage(PageParam pageParam);

    //数据条数
    int selectCount();

    //根据角色id查询权限
    List<Permission> selectByRid(Long id);

}