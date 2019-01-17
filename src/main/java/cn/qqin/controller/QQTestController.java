package cn.qqin.controller;

import cn.qqin.domain.SocialUserInfo;
import cn.qqin.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:cgz
 * @Description:
 * @Date: create in 10:24 2019/1/16
 * @Version:
 * @Modified by:
 */
@Controller
@Slf4j
public class QQTestController {
    @Autowired
    ProviderSignInUtils providerSignInUtils;


    @RequestMapping("/test1")
    public  SocialUserInfo  signUp1(Model model, HttpServletRequest request){
        SocialUserInfo userInfo = new SocialUserInfo();
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadimg(connection.getImageUrl());
        log.info(userInfo.toString());
        model.addAttribute("user", userInfo);
        return userInfo;
    }

    @RequestMapping("/regist")
    //将设置的用户名与其绑定
    public  String  regist(User user, HttpServletRequest request){
        String userId = user.getUsername();
        //我们自己实现的数据库，SocialConfig的UsersConnectionRepository方法
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
        return  "hello";
    }

}
