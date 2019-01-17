package cn.qqin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService , SocialUserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) {
        //加密
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
        //将查询的结果放入user中，
        // AuthorityUtils.commaSeparatedStringToAuthorityList("admin")表示角色admin（拥有的权限），验证成功那么将相应的赎金封装奥user中
        return new User(username, encode, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

    }

    public SocialUserDetails loadUserByUserId(String userID) {
        //查询表
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
        //将查询的结果放入user中，
        // AuthorityUtils.commaSeparatedStringToAuthorityList("admin")表示角色admin（拥有的权限）
        return new SocialUser(userID, encode, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
