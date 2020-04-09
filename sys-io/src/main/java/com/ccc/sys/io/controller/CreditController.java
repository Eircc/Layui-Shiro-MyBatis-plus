package com.ccc.sys.io.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccc.sys.io.Vo.CreditVo;
import com.ccc.sys.io.Vo.CustomerVo;
import com.ccc.sys.io.commons.baseResult.BaseResult;
import com.ccc.sys.io.commons.constants.Constants;
import com.ccc.sys.io.commons.utils.DataGridView;
import com.ccc.sys.io.domain.Credit;
import com.ccc.sys.io.service.CreditService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;

/**
 * <a>Title:CreditController</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/17 14:20
 * @Version 1.0.0
 */
@RestController
@RequestMapping(value = "/credit")
public class CreditController {

    @Autowired
    private CreditService creditService;

    /**
     * 查询所有
     *
     * @param creditVo
     * @return
     */
    @RequestMapping(value = "loadAllCredit")
    public DataGridView loadAllCredit(CreditVo creditVo) {
        IPage<Credit> page = new Page<>(creditVo.getPage(), creditVo.getLimit());
        QueryWrapper<Credit> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNoneBlank(creditVo.getName()), "name", creditVo.getName());
        queryWrapper.like(creditVo.getNum() != null, "num", creditVo.getNum());
        this.creditService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加
     *
     * @param creditVo
     * @return
     */
    @RequestMapping(value = "addCredit")
    public BaseResult addCredit(CreditVo creditVo) {
        try {
            Integer subj1 = creditVo.getSubj1();
            Integer subj2 = creditVo.getSubj2();
            Integer subj3 = creditVo.getSubj3();
            Integer subj4 = creditVo.getSubj4();
            Integer subj5 = creditVo.getSubj5();
            Integer subj6 = creditVo.getSubj6();
            Integer subj7 = creditVo.getSubj7();
            Integer numb = subj1 + subj2 + subj3 + subj4 + subj5 + subj6 + subj7;
            Integer ave = (numb / 7);
            creditVo.setSum(numb);
            creditVo.setAverage(ave);
            this.creditService.save(creditVo);
            return BaseResult.success(Constants.ADD_MESSAGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.ADD_MESSAGE_FAIL);
        }
    }


    @RequestMapping(value = "updateCredit")
    public BaseResult updateCredit(CreditVo creditVo) {
        try {
            Integer subj1 = creditVo.getSubj1();
            Integer subj2 = creditVo.getSubj2();
            Integer subj3 = creditVo.getSubj3();
            Integer subj4 = creditVo.getSubj4();
            Integer subj5 = creditVo.getSubj5();
            Integer subj6 = creditVo.getSubj6();
            Integer subj7 = creditVo.getSubj7();
            Integer numb = subj1 + subj2 + subj3 + subj4 + subj5 + subj6 + subj7;
            Integer ave = (numb / 7);
            creditVo.setSum(numb);
            creditVo.setAverage(ave);
            this.creditService.updateById(creditVo);
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
    @RequestMapping(value = "deleteCredit")
    public BaseResult deleteCredit(Integer id) {
        try {
            this.creditService.removeById(id);
            return BaseResult.success(Constants.DELECE_MESSAGE_SUCCESS, Constants.STATUS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.DELECE_MESSAGE_FAIL, Constants.STATS_FAIL);
        }
    }

    /**
     * 批量删除
     *
     * @param creditVo
     * @return
     */
    @RequestMapping(value = "batchDeleteCredit")
    public BaseResult batchDeleteCredit(CreditVo creditVo) {
        try {
            //定义集合
            Collection<Serializable> idList = new ArrayList<>();
//            遍历id数组
            for (Integer id : creditVo.getIds()) {
//                放到集合里
                idList.add(id);
            }
            this.creditService.removeByIds(idList);
            return BaseResult.success(Constants.DELECE_MESSAGE_SUCCESS, Constants.STATUS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.DELECE_MESSAGE_FAIL, Constants.STATS_FAIL);
        }
    }
}
