package com.ccc.sys.io.commons.activerUser;

import com.ccc.sys.io.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <a>Title:ActiverUser</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/5 19:03
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiverUser {
    private User user;
    private List<String> roals;
    private List<String> permissions;
}
