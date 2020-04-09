package com.ccc.sys.io.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccc.sys.io.Vo.PermissionVo;
import com.ccc.sys.io.commons.baseResult.BaseResult;
import com.ccc.sys.io.commons.constants.Constants;
import com.ccc.sys.io.commons.tree.TreeNode;
import com.ccc.sys.io.commons.utils.DataGridView;
import com.ccc.sys.io.domain.Permission;
import com.ccc.sys.io.service.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a>Title:RolePermissionController</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/14 13:32
 * @Version 1.0.0
 */
@RestController
@RequestMapping(value = "/permission")
public class RolePermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 左侧的 json 树
     *
     * @param permissionVo
     * @return
     */
    @RequestMapping("loadPermissionManagerLeftTreeJson")
    public DataGridView loadPermissionManagerLeftTreeJson(PermissionVo permissionVo) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", Constants.TYPE_MENU);
        List<Permission> list = this.permissionService.list(queryWrapper);
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Permission permission : list) {
            Boolean spread = permission.getOpen() == 1 ? true : false;
            treeNodes.add(new TreeNode(permission.getId(), permission.getPid(), permission.getTitle(), spread));
        }
        return new DataGridView(treeNodes);
    }

    /**
     * 查询所有
     */
    @RequestMapping("loadAllPermission")
    public DataGridView loadAllPermission(PermissionVo permissionVo) {
        IPage<Permission> page = new Page<>(permissionVo.getPage(), permissionVo.getLimit());
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", Constants.TYPE_PERMISSION);// 只能查询权限
        queryWrapper.like(StringUtils.isNotBlank(permissionVo.getTitle()), "title", permissionVo.getTitle());
        queryWrapper.like(StringUtils.isNotBlank(permissionVo.getPercode()), "percode", permissionVo.getPercode());
        queryWrapper.eq(permissionVo.getId() != null, "pid", permissionVo.getId());
        queryWrapper.orderByAsc("ordernum");
        this.permissionService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加
     *
     * @param permissionVo
     * @return
     */
    @RequestMapping(value = "addPermission")
    public BaseResult addPermission(PermissionVo permissionVo) {
        try {
//            设置类型为 菜单
            permissionVo.setType(Constants.TYPE_PERMISSION);
            this.permissionService.save(permissionVo);
            return BaseResult.success(Constants.ADD_MESSAGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.ADD_MESSAGE_FAIL);
        }
    }

    /**
     * 加载最大排序码
     *
     * @return
     */
    @RequestMapping(value = "loadPermissionMaxOrderNum")
    public Map<String, Object> loadPermissionMaxOrderNum() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
//        倒叙查到最大的那个
        queryWrapper.orderByDesc("ordernum");
        List<Permission> list = this.permissionService.list(queryWrapper);
//        如果有，取出第一个
        if (list.size() > 0) {
            map.put("value", list.get(0).getOrdernum() + 1);
        } else {
//            如果没有，赋值为 1
            map.put("value", 1);
        }
        return map;
    }

    /**
     * 更新用户管理信息
     *
     * @param permissionVo
     * @return
     */
    @RequestMapping(value = "updatePermission")
    public BaseResult updatePermission(PermissionVo permissionVo) {
        try {
            this.permissionService.updateById(permissionVo);
            return BaseResult.success(Constants.UPDATE_MESSAGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.UPDATE_MESSAGE_FAIL);
        }
    }

    /**
     * 删除部门
     *
     * @param permissionVo
     * @return
     */
    @RequestMapping(value = "deletePermission")
    public BaseResult deletePermission(PermissionVo permissionVo) {
        try {
            this.permissionService.removeById(permissionVo.getId());
            return BaseResult.success(Constants.DELECE_MESSAGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.DELECE_MESSAGE_FAIL);
        }
    }

}
