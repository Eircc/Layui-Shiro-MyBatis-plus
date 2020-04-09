package com.ccc.sys.io.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccc.sys.io.domain.Dept;
import com.ccc.sys.io.mapper.DeptMapper;
import com.ccc.sys.io.service.DeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <a>Title:DepyServiceImpl</a>
 * <a>Author：<a>
 * <a>Description：<a>
 * <p>
 * 缓存
 *
 * @Author ccc
 * @Date 2020/3/12 14:32
 * @Version 1.0.0
 */
@Service
@Transactional
public class DepyServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    //    缓存数据，查询先从缓存查，缓存没有再从数据库查
//    更新，删除操作的时候，缓存里面的数据也要同步更新，删除
    @Override
    public Dept getById(Serializable id) {
        return super.getById(id);
    }


    @Override
    public boolean updateById(Dept entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean save(Dept entity) {
        return super.save(entity);
    }
}
