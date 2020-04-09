package com.ccc.sys.io.controller;

import com.ccc.sys.io.commons.activerUser.ActiverUser;
import com.ccc.sys.io.commons.baseResult.BaseResult;
import com.ccc.sys.io.commons.constants.Constants;
import com.ccc.sys.io.commons.utils.WebUtils;
import com.ccc.sys.io.domain.Loginfo;
import com.ccc.sys.io.service.LoginfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <a>Title:LoginController</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/5 18:30
 * @Version 1.0.0
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private LoginfoService loginfoService;

    @RequestMapping(value = "login")
    public BaseResult login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);
            ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
            WebUtils.getSession().setAttribute("user", activerUser.getUser());
//            记录登录日志
            Loginfo entity = new Loginfo();
            entity.setLoginname(activerUser.getUser().getName() + "-" + activerUser.getUser().getLoginname());
            entity.setLoginip(WebUtils.getRequset().getRemoteAddr());
            entity.setLogintime(new Date());
            loginfoService.save(entity);
            return BaseResult.success(Constants.LOGIN_SUCCESS);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.LOGIN_FAIL);
        }

    }
}
