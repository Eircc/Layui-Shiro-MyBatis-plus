package com.ccc.sys.io.commons.tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * <a>Title:TreeNode</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/10 17:07
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {
    //构建导航栏json
    //公用部门管理的tree
    private Integer id;

    @JsonProperty("parentId")
    private Integer pid;
    private String title;
    private String icon;
    private String href;
    private Boolean spread;

//    0代表不选择 1代表选中 默认不选中
    private String checkArr = "0";

    private List<TreeNode> children = new ArrayList<TreeNode>();

    /**
     * 后台首页左侧导航栏树形结构构造器
     * @param id
     * @param pid
     * @param title
     * @param icon
     * @param href
     * @param spread
     */
    public TreeNode(Integer id, Integer pid, String title, String icon, String href, Boolean spread) {
        super();
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.spread = spread;
    }

    /**
     * dtree的数据格式 部门管理的tree
     * @param id
     * @param pid
     * @param title
     * @param spread
     */
    public TreeNode(Integer id, Integer pid, String title, Boolean spread) {
        super();
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.spread = spread;
    }

    /**
     * 权限管理 授权框 dtree 复选树
     * @param id
     * @param pid
     * @param title
     * @param spread
     * @param checkArr
     */
    public TreeNode(Integer id, Integer pid, String title, Boolean spread, String checkArr) {
        super();
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.spread = spread;
        this.checkArr = checkArr;
    }

}
