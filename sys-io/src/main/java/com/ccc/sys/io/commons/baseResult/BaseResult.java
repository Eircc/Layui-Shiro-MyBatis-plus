package com.ccc.sys.io.commons.baseResult;

import com.ccc.sys.io.commons.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <a>Title:BaseResult</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/5 18:50
 * @Version 1.0.0
 * 封装状态信息json
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult {
    private Integer code;
    private String msg;
    private Object data;
    //    成功
    public static BaseResult success() {
        return BaseResult.creatResult(Constants.STATUS_SUCCESS, Constants.MESSAGE_SUCCESS, null);
    }

    public static BaseResult success(String msg) {
        return BaseResult.creatResult(Constants.STATUS_SUCCESS, msg, null);
    }

    public static BaseResult success(String msg, Object data) {
        return BaseResult.creatResult(Constants.STATUS_SUCCESS, msg, data);
    }

    //    失败
    public static BaseResult fail() {
        return BaseResult.creatResult(Constants.STATS_FAIL, Constants.MESSAGE_FAIL, null);
    }

    public static BaseResult fail(String msg) {
        return BaseResult.creatResult(Constants.STATS_FAIL, msg, null);
    }

    public static BaseResult fail(String msg, Object data) {
        return BaseResult.creatResult(Constants.STATS_FAIL, msg, data);
    }

    //    提取封装
    private static BaseResult creatResult(Integer code, String msg, Object data) {
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(code);
        baseResult.setMsg(msg);
        baseResult.setData(data);
        return baseResult;
    }



}
