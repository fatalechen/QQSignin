/**
 * 
 */
package cn.qqin.connet;

import cn.qqin.api.QQ;
import cn.qqin.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class QQAdapter implements ApiAdapter<QQ> {

	@Override
	public boolean test(QQ api) {
		return true;//测试qq是否是通的
	}

	@Override
	//放入在SocialConfig的UsersConnectionRepository中
	public void setConnectionValues(QQ api, ConnectionValues values) {
		QQUserInfo userInfo = api.getUserInfo();
		
		values.setDisplayName(userInfo.getNickname()); //显示名称
		values.setImageUrl(userInfo.getFigureurl_qq_1());//头像
		values.setProfileUrl(null);//qq没用，因为没有qq主页，微博主页的url写在这
		values.setProviderUserId(userInfo.getOpenId());//OpenId设置到ProviderUserId
//		SocialConfigurerAdapter
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {//标准结构的信息
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {//微博才用，例如发一个信息
		//do noting
	}

}
