package cn.qqin.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {
	//qq互联文档指南，网站应用，获取openid，access_token也是获取的在QQServiceProvider类中获取，然后填充
	private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
	//qq互联文档指南，网站应用， 获取userinfo中，access_token也是获取的在QQServiceProvider类中获取，然后填充，这里的access_token会自动挂上
	private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
	
		private String appId;//我们申请的appid
	
	private String openId; //通过accessToken获取
	
	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * @Description :获取openId，需要重写的原因是因为默认会放到头里面
	 * @param accessToken
	 * @param appId
	 * @Return :
	 */
	public QQImpl(String accessToken, String appId) {//accessToken父类中的字段，下面的是直接的挂上去
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		
		this.appId = appId;
		
		String url = String.format(URL_GET_OPENID, accessToken);
		String result = getRestTemplate().getForObject(url, String.class);
		log.info(result);
		//获取openId
		this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
	}
	
	/**
	 * @Description :获取userInfo信息
	 * @param
	 * @Return : cn.qqin.api.QQUserInfo
	 */
	@Override
	public QQUserInfo getUserInfo() {
		
		String url = String.format(URL_GET_USERINFO, appId, openId);
		String result= getRestTemplate().getForObject(url, String.class);
        result= result.replaceAll("\\n", "");
        log.info(result);
		QQUserInfo userInfo = null;
		try {
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            userInfo = objectMapper.readValue(result, QQUserInfo.class);
			userInfo.setOpenId(openId);
			return userInfo;
		} catch (Exception e) {
			throw new RuntimeException("获取用户信息失败", e);
		}
	}
}
