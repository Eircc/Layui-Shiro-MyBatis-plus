package com.ccc.sys.io.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccc.sys.io.Vo.LoginfoVo;
import com.ccc.sys.io.Vo.NoticeVo;
import com.ccc.sys.io.commons.baseResult.BaseResult;
import com.ccc.sys.io.commons.constants.Constants;
import com.ccc.sys.io.commons.utils.DataGridView;
import com.ccc.sys.io.commons.utils.WebUtils;
import com.ccc.sys.io.domain.Notice;
import com.ccc.sys.io.domain.User;
import com.ccc.sys.io.service.NoticeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * <a>Title:NoticeController</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/11 18:34
 * @Version 1.0.0
 */
@RestController
@RequestMapping(value = "/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "loadAllNotice")
    public DataGridView loadAllNotice(NoticeVo noticeVo) {
        IPage<Notice> page = new Page<>(noticeVo.getPage(), noticeVo.getLimit());
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNoneBlank(noticeVo.getTitle()), "title", noticeVo.getTitle());
        queryWrapper.like(StringUtils.isNoneBlank(noticeVo.getOpername()), "opername", noticeVo.getOpername());
        queryWrapper.ge(noticeVo.getStartTime() != null, "createtime", noticeVo.getStartTime());
        queryWrapper.le(noticeVo.getEndTime() != null, "createtime", noticeVo.getEndTime());
//       倒叙排序
        queryWrapper.orderByDesc("createtime");
        this.noticeService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加
     *
     * @param noticeVo
     * @return
     */
    @RequestMapping(value = "addNotice")
    public BaseResult addNotice(NoticeVo noticeVo) {
        try {
            noticeVo.setCreatetime(new Date());
            User user = (User) WebUtils.getSession().getAttribute("user");
            noticeVo.setOpername(user.getName());
            this.noticeService.save(noticeVo);
            return BaseResult.success(Constants.ADD_MESSAGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.ADD_MESSAGE_FAIL);
        }
    }


    @RequestMapping(value = "updateNotice")
    public BaseResult updateNotice(NoticeVo noticeVo) {
        try {
            this.noticeService.updateById(noticeVo);
            return BaseResult.success(Constants.UPDATE_MESSAGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.UPDATE_MESSAGE_FAIL);
        }
    }

    /**
     * 单个删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteNotice")
    public BaseResult deleteNotice(Integer id) {
        try {
            this.noticeService.removeById(id);
            return BaseResult.success(Constants.DELECE_MESSAGE_SUCCESS, Constants.STATUS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.DELECE_MESSAGE_FAIL, Constants.STATS_FAIL);
        }
    }

    /**
     * 批量删除
     *
     * @param noticeVo
     * @return
     */
    @RequestMapping(value = "batchDeleteNotice")
    public BaseResult batchDeleteNotice(NoticeVo noticeVo) {
        try {
            //定义集合
            Collection<Serializable> idList = new ArrayList<>();
//            遍历id数组
            for (Integer id : noticeVo.getIds()) {
//                放到集合里
                idList.add(id);
            }
            this.noticeService.removeByIds(idList);
            return BaseResult.success(Constants.DELECE_MESSAGE_SUCCESS, Constants.STATUS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.DELECE_MESSAGE_FAIL, Constants.STATS_FAIL);
        }
    }
}
