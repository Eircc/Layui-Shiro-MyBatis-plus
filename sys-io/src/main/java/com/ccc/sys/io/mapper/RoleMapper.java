package com.ccc.sys.io.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccc.sys.io.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * <a>Title:RoleMapper</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/14 14:06
 * @Version 1.0.0
 */

public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据角色ID删除sys_role_permission
     * @param id
     */
    void deleteRolePermissionByRid(Serializable id);

    /**
     * 根据角色ID删除sys_role_user
     * @param id
     */
    void deleteRoleUserByRid(Serializable id);

    /**
     * 根据角色ID查询当前角色拥有的所有的权限或菜单ID
     * @param roleId
     * @return
     */
    List<Integer> queryRolePermissionIdsByRid(Integer roleId);

    /**
     * 保存角色和菜单权限之间的关系
     * @param rid
     * @param pid
     */
    void saveRolePermission(@Param("rid")Integer rid, @Param("pid")Integer pid);

    /**
     * 根据用户ID删除用户角色中间表的数据
     * @param id
     */
    void deleteRoleUserByUid(@Param("id")Serializable id);

    /**
     * 查询当前用户拥有的角色ID集合
     * @param id
     * @return
     */
    List<Integer> queryUserRoleIdsByUid(Integer id);

    /**
     * 保存角色和用户的关系
     * @param uid
     * @param rid
     */
    void insertUserRole(@Param("uid")Integer uid, @Param("rid")Integer rid);
}
