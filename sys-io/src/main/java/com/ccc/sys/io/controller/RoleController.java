package com.ccc.sys.io.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccc.sys.io.Vo.RoleVo;
import com.ccc.sys.io.commons.baseResult.BaseResult;
import com.ccc.sys.io.commons.constants.Constants;
import com.ccc.sys.io.commons.tree.TreeNode;
import com.ccc.sys.io.commons.utils.DataGridView;
import com.ccc.sys.io.domain.Permission;
import com.ccc.sys.io.domain.Role;
import com.ccc.sys.io.service.PermissionService;
import com.ccc.sys.io.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a>Title:RoleController</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/14 14:14
 * @Version 1.0.0
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "loadAllRole")
    public DataGridView loadAllRole(RoleVo roleVo) {
        IPage<Role> page = new Page<>(roleVo.getPage(), roleVo.getLimit());
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNoneBlank(roleVo.getName()), "name", roleVo.getName());
        queryWrapper.like(StringUtils.isNoneBlank(roleVo.getRemark()), "remark", roleVo.getRemark());
        queryWrapper.eq(roleVo.getAvailable() != null, "available", roleVo.getAvailable());
//       倒叙排序
        queryWrapper.orderByDesc("createtime");
        this.roleService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加
     *
     * @param roleVo
     * @return
     */
    @RequestMapping(value = "addRole")
    public BaseResult addRole(RoleVo roleVo) {
        try {
            roleVo.setCreatetime(new Date());
            this.roleService.save(roleVo);
            return BaseResult.success(Constants.ADD_MESSAGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.ADD_MESSAGE_FAIL);
        }
    }


    @RequestMapping(value = "updateRole")
    public BaseResult updateRole(RoleVo roleVo) {
        try {
            this.roleService.updateById(roleVo);
            return BaseResult.success(Constants.UPDATE_MESSAGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.UPDATE_MESSAGE_FAIL);
        }
    }

    /**
     * 单个删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteRole")
    public BaseResult deleteRole(Integer id) {
        try {
            this.roleService.removeById(id);
            return BaseResult.success(Constants.DELECE_MESSAGE_SUCCESS, Constants.STATUS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.DELECE_MESSAGE_FAIL, Constants.STATS_FAIL);
        }
    }

    @RequestMapping(value = "initPermissionByRoleId")
    public DataGridView initPermissionByRoleId(Integer roleId,RoleVo roleVo) {
//        查询所有可用的菜单和权限
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("available", Constants.AVALIABLE_TRUE);
        List<Permission> allAvailablePermissions = permissionService.list(queryWrapper);
        List<Integer> currentRolePermissions = this.roleService.queryRolePermissionIdsByRid(roleId);
        List<Permission> currentPermission = null;
        if (currentRolePermissions.size() > 0){
//        根据角色ID查询当前角色拥有的权限或菜单ID
            queryWrapper.in( "id", currentRolePermissions);
            currentPermission = permissionService.list(queryWrapper);
        }else {
            currentPermission=new ArrayList<>();
        }
//        构造treenode
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Permission permission : allAvailablePermissions) {
            String checkArr = "0";
            for (Permission permission1 : currentPermission) {
                if (permission.getId() == permission1.getId()) {
                    checkArr = "1";
                    break;
                }
            }
            Boolean spread = (permission.getOpen() == null || permission.getOpen() == 1) ? true : false;
            treeNodes.add(new TreeNode(permission.getId(), permission.getPid(), permission.getTitle(), spread, checkArr));
        }
        return new DataGridView(treeNodes);
    }

    /**
     * 保存角色和菜单之间的关系
     * @param rid
     * @param ids
     * @return
     */
    @RequestMapping(value = "saveRolePermission")
    public BaseResult saveRolePermission(Integer rid,Integer[] ids){

        try {
            this.roleService.saveRolePermission(rid,ids);
            return BaseResult.success(Constants.DISPATCH_MESSAGE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResult.fail(Constants.DISPATCH_MESSAGE_FAIL);
        }
    }

}
