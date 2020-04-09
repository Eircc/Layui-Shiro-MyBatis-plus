package com.ccc.sys.io.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <a>Title:credit</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/17 14:15
 * @Version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Credit implements Serializable {
    private Integer id;
    private String name;
    private Integer num;
    private Integer subj1;
    private Integer subj2;
    private Integer subj3;
    private Integer subj4;
    private Integer subj5;
    private Integer subj6;
    private Integer subj7;
    private Integer sum;
    private Integer average;
}
