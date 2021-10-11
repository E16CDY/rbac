package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.PageParam;
import cn.wolfcode.rbac.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    //分页查询
    List<Role> selectByPage(PageParam pageParam);

    int selectByCount();

    void deleteByRPId(Long id);

    void saveRP(@Param("roleId") Long roleId,@Param("permissionId") Long permissionId);

}