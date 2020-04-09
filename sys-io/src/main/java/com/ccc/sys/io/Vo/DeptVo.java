package com.ccc.sys.io.Vo;

import com.ccc.sys.io.domain.Dept;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <a>Title:DeptVo</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/12 15:18
 * @Version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeptVo extends Dept implements Serializable {
    private Integer page = 1;
    private Integer limit = 10;

}
