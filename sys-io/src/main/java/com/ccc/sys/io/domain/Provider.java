package com.ccc.sys.io.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <a>Title:Provider</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/17 12:17
 * @Version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Provider implements Serializable {
    private Integer id;
    private String providername;
    private String zip;
    private String address;
    private String telephone;
    private String connectionperson;
    private String phone;
    private String bank;
    private String account;
    private String email;
    private String fax;
    private Integer available;
}
