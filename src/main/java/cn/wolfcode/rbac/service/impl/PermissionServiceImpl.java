package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.PageParam;
import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.mapper.PermissionMapper;
import cn.wolfcode.rbac.service.IPermissionService;
import cn.wolfcode.rbac.utils.PageResult;
import cn.wolfcode.rbac.utils.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private ApplicationContext applicationContext;

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

    @Override
    public void reload() {
        List<Permission> permissionList = findAll();

        Map<String, Object> map = applicationContext.getBeansWithAnnotation(Controller.class);
        Collection<Object> collections = map.values();
        for (Object collection : collections) {
            Method[] methods = collection.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(RequiredPermission.class)) {
                    RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
                    String[] value = annotation.value();
                    String name = value[0];
                    String expression = value[1];
                    boolean flag = false;
                    for (Permission permission : permissionList) {
                        if (name.equals(permission.getName()) && expression.equals(permission.getExpression())) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag == false) {
                        Permission permission = new Permission();
                        permission.setName(name);
                        permission.setExpression(expression);
                        permissionMapper.insert(permission);
                    }
                }
            }
        }

    }


}
