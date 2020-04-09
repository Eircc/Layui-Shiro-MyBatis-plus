package com.ccc.sys.io.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccc.sys.io.Vo.LoginfoVo;
import com.ccc.sys.io.commons.baseResult.BaseResult;
import com.ccc.sys.io.commons.constants.Constants;
import com.ccc.sys.io.commons.utils.DataGridView;
import com.ccc.sys.io.domain.Loginfo;
import com.ccc.sys.io.service.LoginfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.expression.Ids;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * <a>Title:LogInfoController</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/10 18:36
 * @Version 1.0.0
 */
@RestController
@RequestMapping(value = "/loginfo")
public class LogInfoController {

    @Autowired
    private LoginfoService logInfoService;

    /**
     * 全部查询 登录日志
     *
     * @param logInfoVo
     * @return
     */
    @RequestMapping(value = "loadAllLogInfo")
    public DataGridView loadAllLogInfo(LoginfoVo logInfoVo) {
        IPage<Loginfo> page = new Page<>(logInfoVo.getPage(), logInfoVo.getLimit());
        QueryWrapper<Loginfo> queryWrapper = new QueryWrapper<>();
//        构造条件
//                用户名不为空，且 loginname 等于登录名
        queryWrapper.like(StringUtils.isNoneBlank(logInfoVo.getLoginname()), "loginname", logInfoVo.getLoginname());
//                用户ip不为空，且 loginip 等于 登录ip
        queryWrapper.like(StringUtils.isNoneBlank(logInfoVo.getLoginip()), "loginip", logInfoVo.getLoginip());
//                登陆时间不为空， 且 logintime 等于 开始时间
        queryWrapper.ge(logInfoVo.getStartTime() != null, "logintime", logInfoVo.getStartTime());
//                推出时间不为空，且 logintime 等于 推出时间
        queryWrapper.ge(logInfoVo.getEndTime() != null, "logintime", logInfoVo.getEndTime());
//                时间排序，先登陆的排在后面
        queryWrapper.orderByDesc("logintime");
        this.logInfoService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 单个删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteLoginfo")
    public BaseResult deleteLoginfo(Integer id) {
        try {
            this.logInfoService.removeById(id);
            return BaseResult.success(Constants.DELECE_MESSAGE_SUCCESS, Constants.STATUS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.DELECE_MESSAGE_FAIL, Constants.STATS_FAIL);
        }
    }

    /**
     * 批量删除
     *
     * @param loginfoVo
     * @return
     */
    @RequestMapping(value = "batchDeleteLoginfo")
    public BaseResult batchDeleteLoginfo(LoginfoVo loginfoVo) {
        try {
            //定义集合
            Collection<Serializable> idList = new ArrayList<>();
//            遍历id数组
            for (Integer id : loginfoVo.getIds()) {
//                放到集合里
                idList.add(id);
            }
            this.logInfoService.removeByIds(idList);
            return BaseResult.success(Constants.DELECE_MESSAGE_SUCCESS, Constants.STATUS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.DELECE_MESSAGE_FAIL, Constants.STATS_FAIL);
        }
    }
}
