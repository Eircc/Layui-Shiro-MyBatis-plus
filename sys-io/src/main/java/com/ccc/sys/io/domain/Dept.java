package com.ccc.sys.io.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <a>Title:Dept</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/12 14:25
 * @Version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Dept implements Serializable {
    private Integer id;
    private Integer pid;
    private String title;
    private Integer open;
    private String remark;
    private String address;
    private Integer available;
    private Integer ordernum;
    private Date createtime;
}
