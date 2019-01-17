/**
 * 
 */
package cn.qqin.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Component
@ConfigurationProperties("spring.social.qq")
public class SocialProperties {
	private String appId;//appid，appsecret，providerId在QQConnectionFactory中使用
	private String appSecret;
	private String signUpUrl ="/signUp.html";
	private String filterProcessesUrl = "/auth";//SocialAuthenticationFilter下的filterProcessesUrl，访问地址
	private String providerId = "qq";//第二段地址
	//private WeixinProperties weixin = new WeixinProperties();
}
