package com.ccc.sys.io.controller;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccc.sys.io.Vo.UserVo;
import com.ccc.sys.io.commons.baseResult.BaseResult;
import com.ccc.sys.io.commons.constants.Constants;
import com.ccc.sys.io.commons.utils.DataGridView;
import com.ccc.sys.io.commons.utils.PingYinUtils;
import com.ccc.sys.io.domain.Dept;
import com.ccc.sys.io.domain.Role;
import com.ccc.sys.io.domain.User;
import com.ccc.sys.io.service.DeptService;
import com.ccc.sys.io.service.RoleService;
import com.ccc.sys.io.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <a>Title:UserController</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/5 18:30
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private RoleService roleService;

    /**
     * 查询全部用户
     *
     * @param userVo
     * @return
     */
    @RequestMapping(value = "loadAllUser")
    public DataGridView loadAllUser(UserVo userVo) {
        IPage<User> page = new Page<>(userVo.getPage(), userVo.getLimit());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(userVo.getName()), "loginname", userVo.getName()).or()
                .eq(StringUtils.isNotBlank(userVo.getName()), "name", userVo.getName());
        queryWrapper.eq(StringUtils.isNotBlank(userVo.getAddress()), "address", userVo.getAddress());
        queryWrapper.eq("type", Constants.USER_TYPE_NORMAL); //查询系统用户
        queryWrapper.eq(userVo.getDeptid() != null, "deptid", userVo.getDeptid());
        this.userService.page(page, queryWrapper);
        List<User> list = page.getRecords();
        for (User user : list) {
            Integer deptid = user.getDeptid();
            if (deptid != null) {
                Dept one = deptService.getById(deptid);
                user.setDeptname(one.getTitle());
            }
            Integer mgr = user.getMgr();
            if (mgr != null) {
                User one = this.userService.getById(mgr);
                user.setLeadername(one.getName());
            }
        }
        return new DataGridView(page.getTotal(), list);
    }

    /**
     * 获取最大排序码
     *
     * @return
     */
    @RequestMapping(value = "loadUserMaxOrderNum")
    public Map<String, Object> loadUserMaxOrderNum() {
        Map<String, Object> map = Collections.synchronizedMap(new HashMap<String, Object>());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("ordernum");
        IPage<User> page = new Page<>(1, 1);
        List<User> list = this.userService.page(page, queryWrapper).getRecords();
        if (list.size() > 0) {
            map.put("value", list.get(0).getOrdernum() + 1);
        } else {
            map.put("value", 1);
        }
        return map;
    }

    /**
     * 根据部门ID查询用户
     *
     * @param deptId
     * @return
     */
    @RequestMapping(value = "loadUsersByDeptId")
    public DataGridView loadUsersByDeptId(Integer deptId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(deptId != null, "deptid", deptId);
        queryWrapper.eq("available", Constants.AVALIABLE_TRUE);
        queryWrapper.eq("type", Constants.USER_TYPE_NORMAL);
        List<User> list = this.userService.list(queryWrapper);
        return new DataGridView(list);
    }

    /**
     * 将用户名转换为拼音
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "changeChineseToPinyin")
    public Map<String, Object> changeChineseToPinyin(String username) {
        Map<String, Object> map = Collections.synchronizedMap(new HashMap<String, Object>());
        if (null != null) {
            map.put("value", PingYinUtils.getPingYin(username));
        } else {
            map.put("value", "");
        }
        return map;
    }

    /**
     * 新增用户
     *
     * @param userVo
     * @return
     */
    @RequestMapping(value = "addUser")
    public BaseResult addUser(UserVo userVo) {
        try {
            //用户类型
            userVo.setType(Constants.USER_TYPE_NORMAL);
            userVo.setHiredate(new Date());
            //uuid 盐
            String salt = IdUtil.simpleUUID().toUpperCase();
            userVo.setSalt(salt);
//            设置密码
//            密码 盐 散列次数
            userVo.setPwd(new Md5Hash(Constants.USER_DEFAULT_PWD, salt, 2).toString());
            this.userService.save(userVo);
            return BaseResult.success(Constants.ADD_MESSAGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.ADD_MESSAGE_FAIL);
        }
    }

    /**
     * 根据用户ID查询一个用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "loadUserById")
    public DataGridView loadUserById(Integer id) {
        return new DataGridView(this.userService.getById(id));
    }

    /**
     * 更新用户
     *
     * @param userVo
     * @return
     */
    @RequestMapping(value = "updateUser")
    public BaseResult updateUser(UserVo userVo) {
        try {
            this.userService.updateById(userVo);
            return BaseResult.success(Constants.UPDATE_MESSAGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.UPDATE_MESSAGE_FAIL);
        }
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteUser")
    public BaseResult deleteUser(Integer id) {
        try {
            this.userService.removeById(id);
            return BaseResult.success(Constants.DELECE_MESSAGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.DELECE_MESSAGE_FAIL);
        }
    }

    @RequestMapping(value = "resetPwd")
    public BaseResult resetPwd(Integer id) {
        try {
            User user = new User();
            user.setId(id);
            String salt = IdUtil.simpleUUID().toUpperCase();
            user.setSalt(salt);
            user.setPwd(new Md5Hash(Constants.USER_DEFAULT_PWD, salt, 2).toString());
            this.userService.updateById(user);
            return BaseResult.success(Constants.RESET_MESSAGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.RESET_MESSAGE_FAIL);
        }
    }

    /**
     * 根据用户ID查询角色并选中已拥有的角色
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "initRoleByUserId")
    public DataGridView initRoleByUserId(Integer id) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("available", Constants.AVALIABLE_TRUE);
//        使用map 直接往里面put  k , v
        List<Map<String, Object>> listMaps = this.roleService.listMaps(queryWrapper);
        List<Integer> currentUserRoleIds = this.roleService.queryUserRoleIdsByUid(id);
        for (Map<String, Object> map : listMaps) {
//            布尔类型的选中属性
            Boolean LAY_CHECKED = false;
            Integer roleId = (Integer) map.get("id");
            for (Integer rid : currentUserRoleIds) {
                if (rid == roleId) {
                    LAY_CHECKED = true;
                    break;
                }
            }
            map.put("LAY_CHECKED", LAY_CHECKED);
        }
        return new DataGridView(Long.valueOf(listMaps.size()), listMaps);
    }

    /**
     * 保存用户和角色的关系
     *
     * @param uid
     * @param ids
     * @return
     */
    @RequestMapping(value = "saveUserRole")
    public BaseResult saveUserRole(Integer uid, Integer[] ids) {
        try {
            this.userService.saveUserRole(uid, ids);
            return BaseResult.success(Constants.DISPATCH_MESSAGE_SUCCESS);
        } catch (Exception e) {
            return BaseResult.fail(Constants.DISPATCH_MESSAGE_FAIL);
        }
    }

}
