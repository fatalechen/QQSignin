/**
 * 
 */
package cn.qqin.service;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

@Component
//注册一个DemoConnectionSignUp，用户自动的创建用户
public class DemoConnectionSignUp implements ConnectionSignUp {

	/*创建用户，并返回唯一标识*/
	@Override
	public String execute(Connection<?> connection) {
		//根据社交用户信息默认创建用户并返回用户唯一标识
		return connection.getDisplayName();//get只能是第三方信息
	}

}
