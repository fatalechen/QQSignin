/**
 * 
 */
package cn.qqin.connet;


import cn.qqin.api.QQ;
import cn.qqin.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
//不能是单列，每次需要产生一个新的，否则成员变量有线程安全问题
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

	private String appId;
	//qq互联文档指南，网站应用，获取access_token，向认证服务器的url
	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
	//qq互联文档指南，网站应用，获取access_token，申请令牌的地址
	private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

	//appId ，appSecret我们申请的id与秘钥
	public QQServiceProvider(String appId, String appSecret) {
		super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
		this.appId = appId;
	}
	
	@Override
	public QQ getApi(String accessToken) {
		return new QQImpl(accessToken, appId);
	}

}
