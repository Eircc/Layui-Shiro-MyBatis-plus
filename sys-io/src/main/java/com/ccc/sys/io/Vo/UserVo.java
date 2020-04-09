package com.ccc.sys.io.Vo;

import com.ccc.sys.io.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <a>Title:UserVo</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/15 14:23
 * @Version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserVo extends User implements Serializable {

    private Integer page = 1;
    private Integer limit = 10;
}
