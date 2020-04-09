package com.ccc.sys.io.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccc.sys.io.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <a>Title:UserMapper</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/5 18:16
 * @Version 1.0.0
 */

public interface UserMapper extends BaseMapper<User> {
    User queryByName(String username);

    void saveUserRole(@Param("uid") Integer uid, @Param("rid") Integer[] rid);

    /**
     * 保存角色与用户之间的关系
     * @param uid
     * @param rid
     */
    void insertUserRole(@Param("uid") Integer uid,@Param("rid") Integer rid);
}
