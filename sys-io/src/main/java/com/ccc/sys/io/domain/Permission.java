package com.ccc.sys.io.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <a>Title:Permission</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/7 20:41
 * @Version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
public class Permission implements Serializable {

    private Integer id;

    private Integer pid;

    /**
     * 权限类型[menu/permission]
     */
    private String type;

    private String title;

    /**
     * 权限编码[只有type= permission才有  user:view]
     */
    private String percode;

    private String icon;

    private String href;

    private String target;

    private Integer open;

    private Integer ordernum;

    /**
     * 状态【0不可用1可用】
     */
    private Integer available;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return pid
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取权限类型[menu/permission]
     *
     * @return type - 权限类型[menu/permission]
     */
    public String getType() {
        return type;
    }

    /**
     * 设置权限类型[menu/permission]
     *
     * @param type 权限类型[menu/permission]
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取权限编码[只有type= permission才有  user:view]
     *
     * @return percode - 权限编码[只有type= permission才有  user:view]
     */
    public String getPercode() {
        return percode;
    }

    /**
     * 设置权限编码[只有type= permission才有  user:view]
     *
     * @param percode 权限编码[只有type= permission才有  user:view]
     */
    public void setPercode(String percode) {
        this.percode = percode;
    }

    /**
     * @return icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return href
     */
    public String getHref() {
        return href;
    }

    /**
     * @param href
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * @return target
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * @return open
     */
    public Integer getOpen() {
        return open;
    }

    /**
     * @param open
     */
    public void setOpen(Integer open) {
        this.open = open;
    }

    /**
     * @return ordernum
     */
    public Integer getOrdernum() {
        return ordernum;
    }

    /**
     * @param ordernum
     */
    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    /**
     * 获取状态【0不可用1可用】
     *
     * @return available - 状态【0不可用1可用】
     */
    public Integer getAvailable() {
        return available;
    }

    /**
     * 设置状态【0不可用1可用】
     *
     * @param available 状态【0不可用1可用】
     */
    public void setAvailable(Integer available) {
        this.available = available;
    }
}
