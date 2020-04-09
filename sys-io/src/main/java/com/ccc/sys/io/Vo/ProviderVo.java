package com.ccc.sys.io.Vo;

import com.ccc.sys.io.domain.Provider;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <a>Title:ProviderVo</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/17 12:23
 * @Version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProviderVo extends Provider implements Serializable {
    private Integer page = 1;
    private Integer limit = 10;
    private Integer[] ids;
}
