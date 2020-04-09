package com.ccc.sys.io.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <a>Title:Goods</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/18 13:04
 * @Version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Goods implements Serializable {

    private Integer id;

    private String goodsname;

    private String productplace;

    private String size;

    private String goodspackage;

    private String productcode;

    private String promitcode;

    private String description;

    private Double price;

    private Integer number;

    private Integer dangernum;

    private String goodsimg;

    private Integer available;

    private Integer providerid;

    @TableField(exist = false)
    private String providername;
}
