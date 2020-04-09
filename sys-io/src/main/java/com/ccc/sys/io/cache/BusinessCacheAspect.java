package com.ccc.sys.io.cache;

import com.ccc.sys.io.Vo.CustomerVo;
import com.ccc.sys.io.domain.Customer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <a>Title:BusinessCacheAspect</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/17 11:58
 * @Version 1.0.0
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class BusinessCacheAspect {
/*
    *//**
     * 日志
     *//*
    private Log log = LogFactory.getLog(CacheAspect.class);

    *//**
     * 缓存对象
     *//*
    private static final String CACHE_CUSTOMER_PROFIX = "customer:";

    *//**//**
     * 缓存对象
     *//**//*
    private static final String CACHE_USER_PROFIX = "user:";*//*

    //    声明一个线程安全的缓存容器
    private Map<String, Object> CACHE_CONTAINER = Collections.synchronizedMap(new HashMap<String, Object>());

    //    声明切面表达式
    private static final String POINTCUT_CUSTOMER_ADD = "execution(* com.ccc.sys.io.service.serviceImpl.CustomerServiceImpl.save(..))";
    private static final String POINTCUT_CUSTOMER_UPDATE = "execution(* com.ccc.sys.io.service.serviceImpl.CustomerServiceImpl.updateById(..))";
    private static final String POINTCUT_CUSTOMER_GET = "execution(* com.ccc.sys.io.service.serviceImpl.CustomerServiceImpl.getById(..))";
    private static final String POINTCUT_CUSTOMER_REMOVE = "execution(* com.ccc.sys.io.service.serviceImpl.CustomerServiceImpl.removeById(..))";
    private static final String POINTCUT_CUSTOMER_BATCHDELETE = "execution(* com.ccc.sys.io.service.serviceImpl.CustomerServiceImpl.removeByIds(..))";

    *//**
     * 用户添加切入
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     *//*
    @Around(value = POINTCUT_CUSTOMER_ADD)
    public Object cacheCustomerAdd(ProceedingJoinPoint joinPoint) throws Throwable {
//        取出第一个参数
        Customer object = (Customer) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res) {
            CACHE_CONTAINER.put(CACHE_CUSTOMER_PROFIX + object.getId(), object);
        }
        return res;
    }


    *//**
     * 查询的切入
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     *//*
    @Around(value = POINTCUT_CUSTOMER_GET)
    public Object cacheCustomerGet(ProceedingJoinPoint joinPoint) throws Throwable {
//        取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
//        从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_CUSTOMER_PROFIX + object);
        if (res1 != null) {
            log.info("已从缓存里面找到用户对象" + CACHE_CUSTOMER_PROFIX + object);
//            如果没有 则覆盖
            return res1;
        } else {
//            如果有，执行目标方法
            Customer res2 = (Customer) joinPoint.proceed();
//            放入缓存
            CACHE_CONTAINER.put(CACHE_CUSTOMER_PROFIX + res2.getId(), res2);
            log.info("未从缓存里面找到用户对象，去数据库查询并放到缓存" + CACHE_CUSTOMER_PROFIX + res2.getId());
            return res2;
        }
    }

    *//**
     * 更新切入
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     *//*
    @Around(value = POINTCUT_CUSTOMER_UPDATE)
    public Object cacheCustomerUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
//        取出第一个参数
        CustomerVo customerVo = (CustomerVo) joinPoint.getArgs()[0];
//        执行目标方法
        Boolean isSuccess = (Boolean) joinPoint.proceed();
//        如果成功
        if (isSuccess) {
//            更新缓存
            Customer customer = (Customer) CACHE_CONTAINER.get(CACHE_CUSTOMER_PROFIX + customerVo.getId());
            if (null == customer) {
//                如果失败
                customer = new Customer();
//                将CustomerVo copy 到 Customer
                BeanUtils.copyProperties(customerVo, customer);
                log.info("用户对象缓存已更新" + CACHE_CUSTOMER_PROFIX + customerVo.getId());
                CACHE_CONTAINER.put(CACHE_CUSTOMER_PROFIX + customer.getId(), customer);

//                放进缓存
                CACHE_CONTAINER.put(CACHE_CUSTOMER_PROFIX + customer.getId(), customer);
            }
        }
        return isSuccess;
    }

    *//**
     * 删除切入
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     *//*
    @Around(value = POINTCUT_CUSTOMER_REMOVE)
    public Object cacheCustomerRemove(ProceedingJoinPoint joinPoint) throws Throwable {
//        取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
//        执行目标方法
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
//            删除缓存
            CACHE_CONTAINER.remove(id);
            log.info("用户对象缓存已删除" + CACHE_CUSTOMER_PROFIX + id);
        }
        return isSuccess;
    }

    *//**
     * 删除切入
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     *//*
    @Around(value = POINTCUT_CUSTOMER_BATCHDELETE)
    public Object cacheCustomerBatchDelete(ProceedingJoinPoint joinPoint) throws Throwable {
//        取出第一个参数
        @SuppressWarnings("unchecked")
        Collection<Serializable> idList = (Collection<Serializable>) joinPoint.getArgs()[0];
//        执行目标方法
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            for (Serializable id : idList) {
//            删除缓存
                CACHE_CONTAINER.remove(id);
                log.info("用户对象缓存已删除" + CACHE_CUSTOMER_PROFIX + id);
            }
        }
        return isSuccess;
    }*/
}
