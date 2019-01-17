package cn.qqin.config;
import cn.qqin.connet.QQConnectionFactory;
import cn.qqin.service.SocialAutoConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix = "spring.social.qq", name = "appId")
//需要在配置中配置了app-id有值才会配置该类
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

	@Autowired
	private SocialProperties socialProperties;

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		return new QQConnectionFactory(socialProperties.getProviderId(), socialProperties.getAppId(), socialProperties.getAppSecret());
	}
}
