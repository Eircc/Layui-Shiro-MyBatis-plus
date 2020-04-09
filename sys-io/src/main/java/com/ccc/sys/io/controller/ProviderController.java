package com.ccc.sys.io.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccc.sys.io.Vo.ProviderVo;
import com.ccc.sys.io.commons.baseResult.BaseResult;
import com.ccc.sys.io.commons.constants.Constants;
import com.ccc.sys.io.commons.utils.DataGridView;
import com.ccc.sys.io.domain.Provider;
import com.ccc.sys.io.service.ProviderService;
import com.mysql.cj.QueryResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <a>Title:ProviderController</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/17 12:24
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/provider")
public class ProviderController {


    @Autowired
    private ProviderService providerService;

    /**
     * 查询所有
     *
     * @param providerVo
     * @return
     */
    @RequestMapping(value = "loadAllProvider")
    public DataGridView loadAllProvider(ProviderVo providerVo) {
        IPage<Provider> page = new Page<>(providerVo.getPage(), providerVo.getLimit());
        QueryWrapper<Provider> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNoneBlank(providerVo.getProvidername()), "providername", providerVo.getProvidername());
        queryWrapper.like(StringUtils.isNoneBlank(providerVo.getPhone()), "phone", providerVo.getPhone());
        queryWrapper.like(StringUtils.isNoneBlank(providerVo.getConnectionperson()), "connectionperson", providerVo.getConnectionperson());
        this.providerService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加
     *
     * @param providerVo
     * @return
     */
    @RequestMapping(value = "addProvider")
    public BaseResult addProvider(ProviderVo providerVo) {
        try {
            this.providerService.save(providerVo);
            return BaseResult.success(Constants.ADD_MESSAGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.ADD_MESSAGE_FAIL);
        }
    }


    @RequestMapping(value = "updateProvider")
    public BaseResult updateProvider(ProviderVo providerVo) {
        try {
            this.providerService.updateById(providerVo);
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
    @RequestMapping(value = "deleteProvider")
    public BaseResult deleteProvider(Integer id) {
        try {
            this.providerService.removeById(id);
            return BaseResult.success(Constants.DELECE_MESSAGE_SUCCESS, Constants.STATUS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.DELECE_MESSAGE_FAIL, Constants.STATS_FAIL);
        }
    }

    /**
     * 批量删除
     *
     * @param providerVo
     * @return
     */
    @RequestMapping(value = "batchDeleteProvider")
    public BaseResult batchDeleteProvider(ProviderVo providerVo) {
        try {
            //定义集合
            Collection<Serializable> idList = new ArrayList<>();
//            遍历id数组
            for (Integer id : providerVo.getIds()) {
//                放到集合里
                idList.add(id);
            }
            this.providerService.removeByIds(idList);
            return BaseResult.success(Constants.DELECE_MESSAGE_SUCCESS, Constants.STATUS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.DELECE_MESSAGE_FAIL, Constants.STATS_FAIL);
        }
    }

    @RequestMapping(value = "loadAllProviderForSelect")
    public DataGridView loadAllProviderForSelect(){
        QueryWrapper<Provider> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("available",Constants.AVALIABLE_TRUE);
        List<Provider> list = this.providerService.list(queryWrapper);
        return new DataGridView(list);
    }
}
