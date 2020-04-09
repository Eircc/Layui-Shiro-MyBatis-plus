package com.ccc.sys.io.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccc.sys.io.domain.User;

/**
 * <a>Title:UserService</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/5 18:22
 * @Version 1.0.0
 */
public interface UserService extends IService<User> {

    void saveUserRole(Integer uid, Integer[] ids);

}
