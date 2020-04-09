package com.ccc.sys.io.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ccc.sys.io.domain.Permission;
import com.ccc.sys.io.domain.Role;

import java.util.List;

/**
 * <a>Title:RoleService</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/14 14:07
 * @Version 1.0.0
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据角色ID查询当前角色拥有的权限或菜单ID
     * @param roleId
     * @return
     */
    List<Integer> queryRolePermissionIdsByRid(Integer roleId);

    /**
     * 保存角色和菜单之间的关系
     * @param rid
     * @param ids
     */
    void saveRolePermission(Integer rid, Integer[] ids);

    List<Integer> queryUserRoleIdsByUid(Integer id);
}
