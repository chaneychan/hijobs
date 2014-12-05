package com.dream.hijobs.service.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dream.hijobs.service.dto.Result;
import com.dream.hijobs.service.dto.ResultCode;
import com.dream.hijobs.util.RandomCode;

/**
 * 短信发送服务
 * @author chaney.chan
 * 2014年9月3日
 */
@Service
public class SmsServiceImpl extends SmsBase implements SmsService {
	
    private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);
	
	@SuppressWarnings("rawtypes")
	public Result sendRegCode(String mobile ,int length){
		Result result = new Result();
		String random = RandomCode.createRandom(true, length);
		//TODO 短信模板 id  到时候需要修改
		boolean success = sendSMS(mobile, "1" ,random);
		if (success) {
			memcachedService.setSmsRegCode(mobile, random, Integer.valueOf(getTimeout()));
		}else {
			logger.info("send regCode error -- mobile: {} , random :{}",mobile,random);
			result.setRc(ResultCode.SMS_SEND_ERROR);
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public Result sendChangePwdCode(String mobile ,int length){
		Result result = new Result();
		String random = RandomCode.createRandom(true, length);
		//TODO 短信模板 id  到时候需要修改
		boolean success = sendSMS(mobile, "2" ,random);
		if (success) {
			memcachedService.setSmsChangePwdCode(mobile, random, Integer.valueOf(getTimeout()));
		}else {
			logger.info("send ChangePwdCode error -- mobile: {} , random :{}",mobile,random);
			result.setRc(ResultCode.SMS_SEND_ERROR);
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public Result sendChangeMoblieCode(String mobile, int length) {
		Result result = new Result();
		String random = RandomCode.createRandom(true, length);
		//TODO 短信模板 id  到时候需要修改
		boolean success = sendSMS(mobile, "2" ,random);
		if (success) {
			memcachedService.setSmsChangeMobileCode(mobile, random, Integer.valueOf(getTimeout()));
		}else {
			logger.info("send ChangeMobileCode error -- mobile: {} , random :{}",mobile,random);
			result.setRc(ResultCode.SMS_SEND_ERROR);
		}
		return result;
	}
	
	public boolean verifyRegCode(String mobile ,String code){
		String cacheCode = memcachedService.getSmsRegCode(mobile);
		if (cacheCode == null || !code.equals(cacheCode)) {
			return false;
		}
		return true;
	}
	
	public boolean verifyChangePwdCode(String mobile ,String code){
		String cacheCode = memcachedService.getSmsChangePwdCode(mobile);
		if (cacheCode == null || !code.equals(cacheCode)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean verifyChangeMobileCode(String mobile, String code) {
		String cacheCode = memcachedService.getSmsChangeMobileCode(mobile);
		if (cacheCode == null || !code.equals(cacheCode)) {
			return false;
		}
		return true;
	}
}
