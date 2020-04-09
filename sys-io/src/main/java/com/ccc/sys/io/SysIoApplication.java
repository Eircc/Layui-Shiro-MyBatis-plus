package com.ccc.sys.io;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching  //开启缓存
@SpringBootApplication
@MapperScan(basePackages = {"com.ccc.sys.io.mapper"})
public class SysIoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysIoApplication.class, args);
    }

}
