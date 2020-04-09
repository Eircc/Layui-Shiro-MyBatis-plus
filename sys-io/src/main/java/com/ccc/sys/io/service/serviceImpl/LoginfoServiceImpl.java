package com.ccc.sys.io.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccc.sys.io.domain.Loginfo;
import com.ccc.sys.io.mapper.LoginfoMapper;
import com.ccc.sys.io.service.LoginfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <a>Title:LogInfoServiceImpl</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/10 18:34
 * @Version 1.0.0
 */
@Service
@Transactional
public class LoginfoServiceImpl extends ServiceImpl<LoginfoMapper, Loginfo> implements LoginfoService {
}
