package com.ccc.sys.io.configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <a>Title:MyBatisPlusConfig</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * 分页插件
 * @Author ccc
 * @Date 2020/3/11 15:26
 * @Version 1.0.0
 */
@Configuration
@ConditionalOnClass(value = {PaginationInterceptor.class})
public class MyBatisPlusConfig {
    /**
     * 分页配置
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


}
