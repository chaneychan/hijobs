package com.dream.hijobs.service.user;

import com.dream.hijobs.dao.domain.User;
import com.dream.hijobs.dao.param.UserPageQuery;
import com.dream.hijobs.service.dto.Result;
import com.dream.hijobs.service.dto.UserPageDTO;
import com.dream.hijobs.service.token.Token;

/**
 * 统一账户服务
 * @author chaney.chan
 * 2014年9月5日
 */
public interface UserService {
	
	/**
	 * 提供给控制台
	 * @return
	 */
	public Result<UserPageDTO> getUsers(UserPageQuery userPageQuery);

	/**
	 * 注册
	 * @param user 
	 * @param code 验证码
	 * @return token
	 */
	public Result<String> register(User user);
	
	/**
	 * 通过第三方注册
	 * @param user 
	 * @param code 验证码
	 * @return token
	 */
	public Result<String> registerByOpen(User user);
	
	/**
	 * 登陆
	 * @param userName
	 * @param pwd
	 * @param devId
	 * @param devType
	 * @param latitude
	 * @param longitude
	 * @return token
	 */
	public Result<String> load(User user);
	

	/**
	 * 通过第三方登陆
	 * @param openId
	 * @param openType
	 * @param devId
	 * @param devType
	 * @param latitude
	 * @param longitude
	 * @return token
	 */
	public Result<String> loadByOpen(User user);
//	
//	/**
//	 * 修改密码
//	 * @param token
//	 * @param pwd
//	 * @param code
//	 * @return token
//	 */
//	public Result<String> changePwd(Token token ,String pwd ,String code);
//	
//	/**
//	 * 忘记密码
//	 * @param userName
//	 * @param pwd，修改后的密码
//	 * @param mobile
//	 * @param code
//	 * @return token
//	 */
//	public Result<String> forgetPwd(String userName ,String pwd ,String mobile ,String code);
	
	public Result<String> update(Token token ,User user);
	
	/**
	 * 跟新最近一次登录时间
	 * @param token
	 */
	public void updateLastLogin(Token token);

}
