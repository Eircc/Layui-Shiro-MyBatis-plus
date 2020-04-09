package com.ccc.sys.io.Vo;

import com.ccc.sys.io.domain.Goods;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <a>Title:GoodsVo</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/18 13:41
 * @Version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsVo extends Goods {
    private Integer page = 1;
    private Integer limit = 10;
}
