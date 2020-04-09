package com.ccc.sys.io.Vo;

import com.ccc.sys.io.domain.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <a>Title:RoleVo</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/14 14:11
 * @Version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleVo extends Role implements Serializable {
    private Integer page = 1;
    private Integer limit = 10;
}
