/**
 * 
 */
package cn.qqin.service;

import cn.qqin.handler.MyUserDetailsSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Description :覆盖原来的路径，SocialAuthenticationFilter下的filterProcessesUrl
 */
public class MySpringSocialConfigurer extends SpringSocialConfigurer {
	@Autowired
	MyUserDetailsSuccess myUserDetailsSuccess;

	private String filterProcessesUrl;//修改登录页
	
	public MySpringSocialConfigurer(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	//这个方法是比较重要的，可以的到相应的SocialAuthenticationFilter的过滤器，这样我们可以设置相应的filter的拦截器
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
		filter.setAuthenticationSuccessHandler(myUserDetailsSuccess);
		filter.setFilterProcessesUrl(filterProcessesUrl);
		return (T) filter;
	}

}
