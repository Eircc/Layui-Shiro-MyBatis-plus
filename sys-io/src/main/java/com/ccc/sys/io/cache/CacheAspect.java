package com.ccc.sys.io.cache;

import com.ccc.sys.io.Vo.DeptVo;
import com.ccc.sys.io.Vo.UserVo;
import com.ccc.sys.io.domain.Dept;
import com.ccc.sys.io.domain.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <a>Title:CacheAspect</a>
 * <a>Author：<a>
 * <a>Description：<a>
 * <p>
 * 缓存
 *
 * @Author ccc
 * @Date 2020/3/12 19:46
 * @Version 1.0.0
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class CacheAspect {

    /**
     * 日志
     */
    private Log log = LogFactory.getLog(CacheAspect.class);

    /**
     * 缓存对象
     */
    private static final String CACHE_DEPT_PROFIX = "dept:";

    /**
     * 缓存对象
     */
    private static final String CACHE_USER_PROFIX = "user:";

    //    声明一个线程安全的缓存容器
    private Map<String, Object> CACHE_CONTAINER = Collections.synchronizedMap(new HashMap<String, Object>());

    //    声明切面表达式
    private static final String POINTCUT_DEPT_ADD = "execution(* com.ccc.sys.io.service.serviceImpl.DepyServiceImpl.save(..))";
    private static final String POINTCUT_DEPT_UPDATE = "execution(* com.ccc.sys.io.service.serviceImpl.DepyServiceImpl.updateById(..))";
    private static final String POINTCUT_DEPT_GET = "execution(* com.ccc.sys.io.service.serviceImpl.DepyServiceImpl.getById(..))";
    private static final String POINTCUT_DEPT_REMOVE = "execution(* com.ccc.sys.io.service.serviceImpl.DepyServiceImpl.removeById(..))";

    //    用户缓存切面表达式
    private static final String POINTCUT_USER_ADD = "execution(* com.ccc.sys.io.service.serviceImpl.UserServiceImpl.save(..))";
    private static final String POINTCUT_USER_UPDATE = "execution(* com.ccc.sys.io.service.serviceImpl.UserServiceImpl.updateById(..))";
    private static final String POINTCUT_USER_GET = "execution(* com.ccc.sys.io.service.serviceImpl.UserServiceImpl.getById(..))";
    private static final String POINTCUT_USER_REMOVE = "execution(* com.ccc.sys.io.service.serviceImpl.UserServiceImpl.removeById(..))";

    /**
     * 部门添加切入
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = POINTCUT_DEPT_ADD)
    public Object cacheDeptAdd(ProceedingJoinPoint joinPoint) throws Throwable {
//        取出第一个参数
        Dept object = (Dept) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res) {
            CACHE_CONTAINER.put(CACHE_DEPT_PROFIX + object.getId(), object);
        }
        return res;
    }


    /**
     * 查询的切入
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = POINTCUT_DEPT_GET)
    public Object cacheDeptGet(ProceedingJoinPoint joinPoint) throws Throwable {
//        取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
//        从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_DEPT_PROFIX + object);
        if (res1 != null) {
            log.info("已从缓存里面找到部门对象" + CACHE_DEPT_PROFIX + object);
//            如果没有 则覆盖
            return res1;
        } else {
//            如果有，执行目标方法
            Dept res2 = (Dept) joinPoint.proceed();
//            放入缓存
            CACHE_CONTAINER.put(CACHE_DEPT_PROFIX + res2.getId(), res2);
            log.info("未从缓存里面找到部门对象，去数据库查询并放到缓存" + CACHE_DEPT_PROFIX + res2.getId());
            return res2;
        }
    }

    /**
     * 更新切入
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = POINTCUT_DEPT_UPDATE)
    public Object cacheDeptUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
//        取出第一个参数
        DeptVo deptVo = (DeptVo) joinPoint.getArgs()[0];
//        执行目标方法
        Boolean isSuccess = (Boolean) joinPoint.proceed();
//        如果成功
        if (isSuccess) {
//            更新缓存
            Dept dept = (Dept) CACHE_CONTAINER.get(CACHE_DEPT_PROFIX + deptVo.getId());
            if (null == dept) {
//                如果失败
                dept = new Dept();
//                将deptVo copy 到 dept
                BeanUtils.copyProperties(deptVo, dept);
                log.info("部门对象缓存已更新" + CACHE_DEPT_PROFIX + deptVo.getId());
                CACHE_CONTAINER.put(CACHE_DEPT_PROFIX + dept.getId(), dept);

//                放进缓存
                CACHE_CONTAINER.put(CACHE_DEPT_PROFIX + dept.getId(), dept);
            }
        }
        return isSuccess;
    }

    /**
     * 删除切入
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = POINTCUT_DEPT_REMOVE)
    public Object cacheDeptRemove(ProceedingJoinPoint joinPoint) throws Throwable {
//        取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
//        执行目标方法
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
//            删除缓存
            CACHE_CONTAINER.remove(id);
            log.info("用户对象缓存已删除" + CACHE_DEPT_PROFIX + id);
        }
        return isSuccess;
    }

    /*==================================================用户缓存=================================================*/

    /**
     * 部门添加切入
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = POINTCUT_USER_ADD)
    public Object cacheUSERAdd(ProceedingJoinPoint joinPoint) throws Throwable {
//        取出第一个参数
        User object = (User) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res) {
            CACHE_CONTAINER.put(CACHE_USER_PROFIX + object.getId(), object);
        }
        return res;
    }


    /**
     * 查询的切入
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = POINTCUT_USER_GET)
    public Object cacheUSERGet(ProceedingJoinPoint joinPoint) throws Throwable {
//        取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
//        从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_USER_PROFIX + object);
        if (res1 != null) {
            log.info("已从缓存里面找到用户对象" + CACHE_USER_PROFIX + object);
//            如果有 则返回
            return res1;
        } else {
//            如果有，执行目标方法
            User res2 = (User) joinPoint.proceed();
//            放入缓存
            CACHE_CONTAINER.put(CACHE_USER_PROFIX + res2.getId(), res2);
            log.info("未从缓存里面找到用户对象，去数据库查询并放到缓存" + CACHE_USER_PROFIX + res2.getId());
            return res2;
        }
    }

    /**
     * 更新切入
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = POINTCUT_USER_UPDATE)
    public Object cacheUSERUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
//        取出第一个参数
        User userVo = (User) joinPoint.getArgs()[0];
//        执行目标方法
        Boolean isSuccess = (Boolean) joinPoint.proceed();
//        如果成功
        if (isSuccess) {
//            更新缓存
            User user = (User) CACHE_CONTAINER.get(CACHE_USER_PROFIX + userVo.getId());
            if (null == user) {
//                如果失败
                user = new User();
//                将deptVo copy 到 dept
                BeanUtils.copyProperties(userVo, user);
                log.info("用户对象缓存已更新" + CACHE_USER_PROFIX + userVo.getId());
                CACHE_CONTAINER.put(CACHE_USER_PROFIX + user.getId(), user);

//                放进缓存
                CACHE_CONTAINER.put(CACHE_USER_PROFIX + user.getId(), user);
            }
        }
        return isSuccess;
    }

    /**
     * 删除切入
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = POINTCUT_USER_REMOVE)
    public Object cacheUSERRemove(ProceedingJoinPoint joinPoint) throws Throwable {
//        取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
//        执行目标方法
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
//            删除缓存
            CACHE_CONTAINER.remove(id);
            log.info("用户对象缓存已删除" + CACHE_USER_PROFIX + id);
        }
        return isSuccess;
    }


}
