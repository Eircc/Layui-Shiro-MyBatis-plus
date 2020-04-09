package com.ccc.sys.io.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccc.sys.io.domain.Customer;
import com.ccc.sys.io.mapper.CustomerMapper;
import com.ccc.sys.io.service.CustomerService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

/**
 * <a>Title:CustomerServiceIpml</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/17 11:11
 * @Version 1.0.0
 */
@Service
public class CustomerServiceIpml extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Override
    public Customer getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean updateById(Customer entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean save(Customer entity) {
        return super.save(entity);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }
}
