package com.ccc.sys.io.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccc.sys.io.domain.Permission;
import com.ccc.sys.io.mapper.PermissionMapper;
import com.ccc.sys.io.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <a>Title:PermissionServiceImpl</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/7 20:47
 * @Version 1.0.0
 */
@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper,Permission> implements PermissionService{


/*    @Override
    public boolean removeById(Serializable id) {
//        根据权限菜单ID删除权限表里和各角色的关系表里的数据
        PermissionMapper permissionMapper = this.getBaseMapper();
        permissionMapper.deleteRolePermissionByPid(id);
        return super.removeById(id);
    }*/
}
