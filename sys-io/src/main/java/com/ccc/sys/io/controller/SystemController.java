package com.ccc.sys.io.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

/**
 * <a>Title:SystemController</a>
 * <a>Author：<a>
 * <a>Description：<a>
 * <p>
 * 路由
 *
 * @Author ccc
 * @Date 2020/3/5 18:30
 * @Version 1.0.0
 */
@Controller
@RequestMapping(value = "/sys")
public class SystemController {

    /**
     * 跳转到登陆页面
     *
     * @return
     */
    @RequestMapping(value = "toLogin")
    public String toLogin() {
        return "system/index/login";
    }

    /**
     * 登陆成功，跳转首页
     *
     * @return
     */
    @RequestMapping(value = "index")
    public String index() {
        return "system/index/index";
    }

    /**
     * 跳转到后台首页
     *
     * @return
     */
    @RequestMapping(value = "toDeskManager")
    public String toDeskManager() {
        return "system/index/deskManager";
    }

    /**
     * 跳转到登录日志页面
     */
    @RequestMapping(value = "toLoginfoManager")
    public String toLogInfoManager() {
        return "system/loginfo/loginfoManager";
    }

    /**
     * 跳转公告
     *
     * @return
     */
    @RequestMapping(value = "toNoticeManager")
    public String toNoticeManager() {
        return "system/notice/noticeManager";
    }

    /**
     * 跳转到部门管理
     *
     * @return
     */
    @RequestMapping(value = "toDeptManager")
    public String toDeptManager() {
        return "system/dept/deptManager";
    }

    /**
     * 跳转到部门管理 Left
     *
     * @return
     */
    @RequestMapping(value = "toDeptLeft")
    public String toDeptLeft() {
        return "system/dept/deptLeft";
    }

    /**
     * 跳转到部门管理 Rigth
     *
     * @return
     */
    @RequestMapping(value = "toDeptRight")
    public String toDeptRight() {
        return "system/dept/deptRight";
    }

    /**
     * 跳转菜单首页
     *
     * @return
     */
    @RequestMapping(value = "toMenuManager")
    public String toMenuManager() {
        return "system/menu/menuManager";
    }

    /**
     * 跳转菜单管理 Left
     *
     * @return
     */
    @RequestMapping(value = "toMenuLeft")
    public String toMenuLeft() {
        return "system/menu/menuLeft";
    }

    /**
     * 跳转菜单管理 Right
     *
     * @return
     */
    @RequestMapping(value = "toMenuRight")
    public String toMenuRight() {
        return "system/menu/menuRight";
    }

    /**
     * 跳转到权限管理
     *
     * @return
     */
    @RequestMapping(value = "toPermissionManager")
    public String toPermissionManager() {
        return "system/permission/permissionManager";
    }

    /**
     * 跳转到权限管理 Left
     *
     * @return
     */
    @RequestMapping(value = "toPermissionLeft")
    public String toPermissionLeft() {
        return "system/permission/permissionLeft";
    }

    /**
     * 跳转到权限管理 Right
     *
     * @return
     */
    @RequestMapping(value = "toPermissionRight")
    public String toPermissionRight() {
        return "system/permission/permissionRight";
    }

    /**
     * 跳转到角色管理
     *
     * @return
     */
    @RequestMapping(value = "toRoleManager")
    public String toRoleManager() {
        return "system/role/roleManager";
    }

    /**
     * 跳转用户管理
     * @return
     */
    @RequestMapping(value = "toUserManager")
    public String toUserManager(){
        return "system/user/userManager";
    }

}
