package com.ccc.sys.io.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccc.sys.io.domain.Goods;
import com.ccc.sys.io.mapper.GoodsMapper;
import com.ccc.sys.io.service.GoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <a>Title:GoodsServiceimpl</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/18 13:17
 * @Version 1.0.0
 */
@Service
@Transactional
public class GoodsServiceimpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
}
