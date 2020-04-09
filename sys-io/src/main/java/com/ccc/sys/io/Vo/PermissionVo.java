package com.ccc.sys.io.Vo;

import com.ccc.sys.io.domain.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <a>Title:PermissionVo</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/7 20:59
 * @Version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionVo extends Permission implements Serializable {

    private Integer page = 1;
    private Integer limit = 10;

}
