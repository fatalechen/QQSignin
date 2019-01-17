/**
 * 
 */
package cn.qqin.config;

import cn.qqin.service.MySpringSocialConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableSocial
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class SocialConfig extends SocialConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	@Autowired
	private SocialProperties socialProperties;
	
	@Autowired(required = false)
	private ConnectionSignUp connectionSignUp;

	//connectionFactoryLocator查找
	//放入数据库
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		//dataSource  数据源   connectionFactoryLocator查找相应的factory对数据库的数据进行加解密
		//Encryptors.noOpText()  加解密 noOpText 表示不进行加解密
		/*JdbcUsersConnectionRepository旁边有相应的建表sql，
		userid 用户id ，privodeid服务提供商的id，
		privodeuserid是opneid，其他一些字段对用我们设置的值，accesstoken 令牌,
		refreshtoken,eipiretime过期时间
		 */
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
				connectionFactoryLocator, Encryptors.noOpText());
	//		repository.setTablePrefix("test");//前缀
		if(connectionSignUp != null) {
			repository.setConnectionSignUp(connectionSignUp);
		}
		return repository;
	}

	//实现了SecurityConfigurerAdapter
	//在config方法中使用apply，添加相应的配置，也就是加一个过滤器
	@Bean
	public SpringSocialConfigurer springSocialConfigurer() {
		String filterProcessesUrl = socialProperties.getFilterProcessesUrl();
		MySpringSocialConfigurer configurer = new MySpringSocialConfigurer(filterProcessesUrl);
		configurer.signupUrl(socialProperties.getSignUpUrl());
		return configurer;
	}

	@Bean
	//拿用户信息，这个信息是在SocialAuthenticationFilter的doAuthentication方法中放入的
	/* Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
     * userInfo.setProviderId(connection.getKey().getProviderId());
	 */
	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
		return new ProviderSignInUtils(connectionFactoryLocator,
				getUsersConnectionRepository(connectionFactoryLocator)) {
		};
	}

	@Bean
	//zh
	public UsersConnectionRepository usersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
				connectionFactoryLocator, Encryptors.noOpText());
		//		repository.setTablePrefix("test");//前缀
		//这里免注册时调用，当connectionSignUp不为null是才执行
		if(connectionSignUp != null) {
			repository.setConnectionSignUp(connectionSignUp);
		}
		return repository;
	}

}
