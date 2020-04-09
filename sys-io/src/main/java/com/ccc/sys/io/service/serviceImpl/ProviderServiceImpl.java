package com.ccc.sys.io.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccc.sys.io.domain.Provider;
import com.ccc.sys.io.mapper.ProviderMapper;
import com.ccc.sys.io.service.ProviderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <a>Title:ProviderServiceImpl</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/17 12:21
 * @Version 1.0.0
 */
@Service
@Transactional
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, Provider> implements ProviderService {
}
