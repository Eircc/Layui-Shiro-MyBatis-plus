package com.ccc.sys.io.Vo;

import com.ccc.sys.io.domain.Notice;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <a>Title:NoticeVo</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/11 18:32
 * @Version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NoticeVo extends Notice implements Serializable {

    private Integer page = 1;
    private Integer limit = 10;
    //    批量删除的ids
    private Integer[] ids;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
