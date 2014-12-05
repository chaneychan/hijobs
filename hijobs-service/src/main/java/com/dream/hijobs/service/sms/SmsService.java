package com.dream.hijobs.service.sms;

import com.dream.hijobs.service.dto.Result;

/**
 * 短信发送服务
 * @author chaney.chan
 * 2014年9月4日
 */
public interface SmsService {

	/**
	 * 发送 注册码短信
	 * @param mobile
	 * @param length
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Result sendRegCode(String mobile ,int length);
	
	/**
	 * 发送 修改密码 短信
	 * @param mobile
	 * @param length
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Result sendChangePwdCode(String mobile ,int length);
	
	/**
	 * 发送 修改手机 短信
	 * @param mobile
	 * @param length
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Result sendChangeMoblieCode(String mobile ,int length);
	
	/**
	 * 验证 注册 的验证码
	 * @param mobile
	 * @param code
	 * @return
	 */
	public boolean verifyRegCode(String mobile ,String code);
	
	/**
	 * 验证 修改密码 的验证码
	 * @param mobile
	 * @param code
	 * @return
	 */
	public boolean verifyChangePwdCode(String mobile ,String code);
	
	/**
	 * 验证 修改手机 的验证码
	 * @param mobile
	 * @param code
	 * @return
	 */
	public boolean verifyChangeMobileCode(String mobile ,String code);
}
