package com.ccc.sys.io.commons.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <a>Title:DataGridView</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * 接口
 *
 * @Author ccc
 * @Date 2020/3/7 20:54
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGridView {
    private Integer code = 0;
    private String message = "";
    private Long count = 0L;
    private Object data;

    public DataGridView(Long count, Object data) {
        super();
        this.count = count;
        this.data = data;
    }

    public DataGridView(Object data) {
        super();
        this.data = data;
    }
}
