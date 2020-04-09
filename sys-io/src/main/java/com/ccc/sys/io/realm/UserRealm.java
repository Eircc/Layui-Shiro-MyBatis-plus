package com.ccc.sys.io.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccc.sys.io.commons.activerUser.ActiverUser;
import com.ccc.sys.io.commons.constants.Constants;
import com.ccc.sys.io.domain.Permission;
import com.ccc.sys.io.domain.User;
import com.ccc.sys.io.mapper.UserMapper;
import com.ccc.sys.io.service.PermissionService;
import com.ccc.sys.io.service.RoleService;
import com.ccc.sys.io.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * <a>Title:UserRealm</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/5 19:11
 * @Version 1.0.0
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    @Lazy //只有在使用的时候才会加载
    private UserService userService;

    @Autowired
    @Lazy
    private PermissionService permissionService;

    @Autowired
    @Lazy
    private RoleService roleService;

    /**
     * 重写 getName() 方法
     *
     * @return
     */
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        获取用户名称
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        用名称到数据库进行查询
        User user = userService.getOne(queryWrapper.eq("loginname", authenticationToken.getPrincipal()));
//        查看userservice是否被动态代理
//        System.out.println(this.userService.getClass().getSimpleName());
//        如果存在
        if (user != null) {
//            将查询对象放到  activerUser.setUser(user); 中
            ActiverUser activerUser = new ActiverUser();
            activerUser.setUser(user);
//            根据用户ID查询percode
//            查询所有菜单
            QueryWrapper<Permission> wrapper = new QueryWrapper<>();
//            设置只能查询菜单
            wrapper.eq("type", Constants.TYPE_PERMISSION);
            wrapper.eq("available", Constants.AVALIABLE_TRUE);

            Integer userId = user.getId();
//            根据用户ID查询角色
            List<Integer> currentUserRoleIds = roleService.queryUserRoleIdsByUid(userId);
//            根据角色ID取到权限和菜单ID
            Set<Integer> pids = new HashSet<>();
            for (Integer rid : currentUserRoleIds) {
                List<Integer> permissionIds = roleService.queryRolePermissionIdsByRid(rid);
                pids.addAll(permissionIds);
            }
            List<Permission> list = new ArrayList<>();
//            根据角色ID查询权限
            if (pids.size() > 0) {
                wrapper.in("id", pids);
                list = permissionService.list(wrapper);
            }
            List<String> percodes = new ArrayList<>();
            for (Permission permission : list) {
                percodes.add(permission.getPercode());
            }
//            放到activerUser里
            activerUser.setPermissions(percodes);
//            取出 盐 UUID
            ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());

//            返回 simpleAuthenticationInfo
//                    注意：
//                            第一个可以填任何名称，第二个是用户从数据库中查询出的密码，第三个是加的 盐 ，第四个是类名
//                                    盐是在查询数据库用户时的字段 user.getSalt() 随机生成的一个UUID
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activerUser, user.getPwd(), credentialsSalt, this.getName());
            return simpleAuthenticationInfo;
        }
        return null;


    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        获取身份
        ActiverUser activerUser = (ActiverUser) principalCollection.getPrimaryPrincipal();
//        取出user
        User user = activerUser.getUser();
//        取出权限
        List<String> permissions = activerUser.getPermissions();
//        如果是超级管理员
        if (user.getType() == Constants.USER_TYPE_SUPER) {
//            拥有所有按钮的权限
            simpleAuthorizationInfo.addStringPermission("*:*");
        } else {
//            如果权限不为空
            if (null != permissions && permissions.size() > 0) {
//                授予相应权限
                simpleAuthorizationInfo.addStringPermissions(permissions);
            }
            return simpleAuthorizationInfo;
        }
        return null;
    }
}
