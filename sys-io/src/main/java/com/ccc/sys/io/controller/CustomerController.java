package com.ccc.sys.io.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccc.sys.io.Vo.CustomerVo;
import com.ccc.sys.io.commons.baseResult.BaseResult;
import com.ccc.sys.io.commons.constants.Constants;
import com.ccc.sys.io.commons.utils.DataGridView;
import com.ccc.sys.io.commons.utils.WebUtils;
import com.ccc.sys.io.domain.Customer;
import com.ccc.sys.io.domain.User;
import com.ccc.sys.io.service.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * <a>Title:CustomerController</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/17 11:20
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    /**
     * 查询所有
     * @param customerVo
     * @return
     */
    @RequestMapping(value = "loadAllCustomer")
    public DataGridView loadAllCustomer(CustomerVo customerVo) {
        IPage<Customer> page = new Page<>(customerVo.getPage(), customerVo.getLimit());
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNoneBlank(customerVo.getCustomername()), "customername", customerVo.getCustomername());
        queryWrapper.like(StringUtils.isNoneBlank(customerVo.getPhone()), "phone", customerVo.getPhone());
        queryWrapper.like(StringUtils.isNoneBlank(customerVo.getConnectionperson()), "connectionperson", customerVo.getConnectionperson());
        this.customerService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加
     *
     * @param customerVo
     * @return
     */
    @RequestMapping(value = "addCustomer")
    public BaseResult addCustomer(CustomerVo customerVo) {
        try {
            this.customerService.save(customerVo);
            return BaseResult.success(Constants.ADD_MESSAGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.ADD_MESSAGE_FAIL);
        }
    }


    @RequestMapping(value = "updateCustomer")
    public BaseResult updateCustomer(CustomerVo customerVo) {
        try {
            this.customerService.updateById(customerVo);
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
    @RequestMapping(value = "deleteCustomer")
    public BaseResult deleteCustomer(Integer id) {
        try {
            this.customerService.removeById(id);
            return BaseResult.success(Constants.DELECE_MESSAGE_SUCCESS, Constants.STATUS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.DELECE_MESSAGE_FAIL, Constants.STATS_FAIL);
        }
    }

    /**
     * 批量删除
     *
     * @param customerVo
     * @return
     */
    @RequestMapping(value = "batchDeleteCustomer")
    public BaseResult batchDeleteCustomer(CustomerVo customerVo) {
        try {
            //定义集合
            Collection<Serializable> idList = new ArrayList<>();
//            遍历id数组
            for (Integer id : customerVo.getIds()) {
//                放到集合里
                idList.add(id);
            }
            this.customerService.removeByIds(idList);
            return BaseResult.success(Constants.DELECE_MESSAGE_SUCCESS, Constants.STATUS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.DELECE_MESSAGE_FAIL, Constants.STATS_FAIL);
        }
    }
}
