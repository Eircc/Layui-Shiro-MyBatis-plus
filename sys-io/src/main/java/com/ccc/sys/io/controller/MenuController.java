package com.ccc.sys.io.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccc.sys.io.Vo.DeptVo;
import com.ccc.sys.io.Vo.PermissionVo;
import com.ccc.sys.io.commons.baseResult.BaseResult;
import com.ccc.sys.io.commons.constants.Constants;
import com.ccc.sys.io.commons.tree.JsonTreeNodeBulider;
import com.ccc.sys.io.commons.tree.TreeNode;
import com.ccc.sys.io.commons.utils.DataGridView;
import com.ccc.sys.io.commons.utils.WebUtils;
import com.ccc.sys.io.domain.Dept;
import com.ccc.sys.io.domain.Permission;
import com.ccc.sys.io.domain.User;
import com.ccc.sys.io.service.PermissionService;
import com.ccc.sys.io.service.RoleService;
import com.ccc.sys.io.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.ls.LSInput;

import java.util.*;

/**
 * <a>Title:MenuController</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/7 20:49
 * @Version 1.0.0
 */
@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "loadIndexLeftMenuJson")
    public DataGridView  loadIndexLeftMenuJson(PermissionVo permissionVo) {
        //查询所有菜单
        QueryWrapper<Permission> queryWrapper=new QueryWrapper<>();
        //设置只能查询菜单
        queryWrapper.eq("type",Constants.TYPE_MENU);
        queryWrapper.eq("available", Constants.AVALIABLE_TRUE);

        User user = (User) WebUtils.getSession().getAttribute("user");
        List<Permission> list=null;
        if(user.getType()==Constants.USER_TYPE_SUPER) {
            list = permissionService.list(queryWrapper);
        }else {
            //根据用户ID+角色+权限去查询
            Integer userId=user.getId();
            //根据用户ID查询角色
            List<Integer> currentUserRoleIds = roleService.queryUserRoleIdsByUid(userId);
            //根据角色ID取到权限和菜单ID
            Set<Integer> pids=new HashSet<>();
            for (Integer rid : currentUserRoleIds) {
                List<Integer> permissionIds = roleService.queryRolePermissionIdsByRid(rid);
                pids.addAll(permissionIds);
            }

            //根据角色ID查询权限
            if(pids.size()>0) {
                queryWrapper.in("id", pids);
                list=permissionService.list(queryWrapper);
            }else {
                list =new ArrayList<>();
            }
        }
        List<TreeNode> treeNodes=new ArrayList<>();
        for (Permission p : list) {
            Integer id=p.getId();
            Integer pid=p.getPid();
            String title=p.getTitle();
            String icon=p.getIcon();
            String href=p.getHref();
            Boolean spread=p.getOpen()==Constants.OPEN_TRUE?true:false;
            treeNodes.add(new TreeNode(id, pid, title, icon, href, spread));
        }
        //构造层级关系
        List<TreeNode> list2 = JsonTreeNodeBulider.build(treeNodes, 1);
        return new DataGridView(list2);
    }


    /*====================================菜单管理开始=================================*/

    /**
     * 加载菜单管理左边的菜单树的json
     *
     * @param permissionVo
     * @return
     */
    @RequestMapping(value = "loadMenuManagerLeftTreeJson")
    public DataGridView loadMenuManagerLeftTreeJson(PermissionVo permissionVo) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
//        只能查询菜单
        queryWrapper.eq("type", Constants.TYPE_MENU);
        List<Permission> list = this.permissionService.list(queryWrapper);
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Permission permission : list) {
            Boolean spared = permission.getOpen() == 1 ? true : false;
            treeNodes.add(new TreeNode(permission.getId(), permission.getPid(), permission.getTitle(), spared));
        }
        return new DataGridView(treeNodes);
    }

    /**
     * 查询数据
     *
     * @param permissionVo
     * @return
     */
    @RequestMapping(value = "loadAllMenu")
    public DataGridView loadAllMenu(PermissionVo permissionVo) {
        IPage<Permission> page = new Page<>(permissionVo.getPage(), permissionVo.getLimit());
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(permissionVo.getId() != null, "id", permissionVo.getId()).or().eq(permissionVo.getId() != null, "pid", permissionVo.getId());
        //        只能查询菜单
        queryWrapper.eq("type", Constants.TYPE_MENU);
        queryWrapper.like(StringUtils.isNotBlank(permissionVo.getTitle()), "title", permissionVo.getTitle());
//           正序排序
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
    @RequestMapping(value = "addMenu")
    public BaseResult addMenu(PermissionVo permissionVo) {
        try {
//            设置类型为 菜单
            permissionVo.setType(Constants.TYPE_MENU);
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
    @RequestMapping(value = "loadMenuMaxOrderNum")
    public Map<String, Object> loadMenuMaxOrderNum() {
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
    @RequestMapping(value = "updateMenu")
    public BaseResult updateMenu(PermissionVo permissionVo) {
        try {
            this.permissionService.updateById(permissionVo);
            return BaseResult.success(Constants.UPDATE_MESSAGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.UPDATE_MESSAGE_FAIL);
        }
    }

    /**
     * 查询当前部门有无子部门
     *
     * @param permissionVo
     * @return
     */
    @RequestMapping(value = "checkMenuHasChildrenNode")
    public Map<String, Object> checkMenuHasChildrenNode(PermissionVo permissionVo) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", permissionVo.getId());
        List<Permission> list = this.permissionService.list(queryWrapper);
        if (list.size() > 0) {
            map.put("value", true);
        } else {
            map.put("value", false);
        }
        return map;
    }

    /**
     * 删除部门
     *
     * @param permissionVo
     * @return
     */
    @RequestMapping(value = "deleteMenu")
    public BaseResult deleteMenu(PermissionVo permissionVo) {
        try {
            this.permissionService.removeById(permissionVo.getId());
            return BaseResult.success(Constants.DELECE_MESSAGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.DELECE_MESSAGE_FAIL);
        }
    }


    /*====================================菜单管理结束=================================*/


}
