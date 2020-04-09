package com.ccc.sys.io.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccc.sys.io.domain.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * <a>Title:PermissionMapper</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/7 20:46
 * @Version 1.0.0
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 根据权限菜单ID删除权限表里和各角色的关系表里的数据
     * @param id
     */
    void deleteRolePermissionByPid(@Param("id") Serializable id);
}
