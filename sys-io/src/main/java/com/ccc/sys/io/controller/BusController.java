package com.ccc.sys.io.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <a>Title:BusController</a>
 * <a>Author：<a>
 * <a>Description：<a>
 * <p>
 * 业务路由
 *
 * @Author ccc
 * @Date 2020/3/17 11:17
 * @Version 1.0.0
 */
@Controller
@RequestMapping(value = "/bus")
public class BusController {

    /**
     * 跳转用户管理
     *
     * @return
     */
    @RequestMapping(value = "toCustomerManager")
    public String toCustomerManager() {
        return "business/customer/customerManager";
    }

    /**
     * 跳转进货管理
     *
     * @return
     */
    @RequestMapping(value = "toProviderManager")
    public String toProviderManager() {
        return "business/provider/providerManager";
    }

    /**
     * 跳转学分管理
     *
     * @return
     */
    @RequestMapping(value = "toCreditManager")
    public String toCreditManager() {
        return "business/credit/creditManager";
    }

    /**
     * 商品管理
     *
     * @return
     */
    @RequestMapping(value = "toGoodManager")
    public String toGoodManager() {
        return "business/good/goodManager";
    }

    /**
     * 测试
     *
     * @return
     */
    @RequestMapping(value = "toInportManager")
    public String toInportManager() {
        return "business/newsAdd";
    }

}
