package com.ccc.sys.io.commons.constants;

/**
 * <a>Title:Constants</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/5 18:51
 * @Version 1.0.0
 * 系统常量
 */
public interface Constants {

    //    状态信息status
    public static final Integer STATUS_SUCCESS = 200;
    public static final Integer STATS_FAIL = -1;
    public static final String MESSAGE_SUCCESS = "操作成功!";
    public static final String MESSAGE_FAIL = "操作失败!";

    public static final String LOGIN_SUCCESS="登录成功!";
    public static final String LOGIN_FAIL="用户名或密码错误,请重新输入!";
    public static final String UPDATE_MESSAGE_SUCCESS = "更新操作成功！";
    public static final String UPDATE_MESSAGE_FAIL = "更新操作失败！";
    public static final String ADD_MESSAGE_SUCCESS = "添加操作成功!";
    public static final String ADD_MESSAGE_FAIL = "添加操作失败！";
    public static final String DELECE_MESSAGE_SUCCESS = "刪除操作成功!";
    public static final String DELECE_MESSAGE_FAIL = "刪除操作失败!";
    public static final String RESET_MESSAGE_SUCCESS = "重置操作成功!";
    public static final String RESET_MESSAGE_FAIL = "重置操作失败!";
    public static final String DISPATCH_MESSAGE_SUCCESS = "分配操作成功!";
    public static final String DISPATCH_MESSAGE_FAIL = "分配操作失败!";

    /**
     * 菜单权限类型
     */
    public static final String TYPE_MENU = "menu";
    public static final String TYPE_PERMISSION = "permission";

    /**
     * 可行状态
     */
    public static final Object AVALIABLE_TRUE = 1;
    public static final Object AVALIABLE_FALSE = 0;

    /**
     * 用户权限类型 管理员/普通用户
     */
    public static final Integer USER_TYPE_SUPER = 0;
    public static final Integer USER_TYPE_NORMAL = 1;

    /**
     * json树形节点判断是否展开
     */
    public static final Integer OPEN_TRUE = 1;
    public static final Integer OPEN_FALSE = 0;

    /**
     * 初始密码
     */
    public static final String USER_DEFAULT_PWD = "123456";

    /**
     * 上传图片时的默认图片
     */
    public static final String DEFAULT_IMAGES="images/awesome.jpg";

}

