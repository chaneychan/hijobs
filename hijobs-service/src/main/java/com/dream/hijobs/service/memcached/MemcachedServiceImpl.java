package com.dream.hijobs.service.memcached;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MemcachedServiceImpl extends MemcachedBase implements MemcachedService {

    private static final Logger logger = LoggerFactory.getLogger(MemcachedServiceImpl.class);
    
	/**
	 * 修改密码生成的验证码 key
	 * @param mobile
	 * @return
	 */
    private static String generateSmsChangePwd(String mobile) {
        return "sms-change-pwd-" + mobile;
    }
    
    /**
     * 注册账号生成的验证码 key
     * @param mobile
     * @return
     */
    private static String generateSmsRegKey(String mobile) {
    	return "sms-reg-" + mobile;
    }
    
    /**
     * 修改手机生成的验证码 key
     * @param mobile
     * @return
     */
    private static String generateSmsChangeMobileKey(String mobile) {
    	return "sms-change-mobile-" + mobile;
    }
    
    /**
     * 公司账户key
     * @param companyAccountId
     * @return
     */
    private static String generateCompanyAccountKey(Long companyAccountId) {
    	return "co-account-" + companyAccountId;
    }
    
    /**
     * 求职者账户key
     * @param applicantId
     * @return
     */
    private static String generateApplicantKey(Long applicantId) {
    	return "applicant-" + applicantId;
    }
    
	@Override
	public void setSmsRegCode(String mobile, String random, int exp) {
		if (StringUtils.isNotBlank(mobile)) {
			cacheObject(generateSmsRegKey(mobile), random, exp);
		}else {
			logger.warn("memcached cache SmsRegCode ,mobile is null");
		}
	}

	@Override
	public String getSmsRegCode(String mobile) {
		return loadObject(generateSmsRegKey(mobile));
	}

	@Override
	public void setSmsChangePwdCode(String mobile, String random, int exp) {
		if (StringUtils.isNotBlank(mobile)) {
			cacheObject(generateSmsChangePwd(mobile), random, exp);
		}else {
			logger.warn("memcached cache SmsChangePwdCode ,mobile is null");
		}
	}

	@Override
	public String getSmsChangePwdCode(String mobile) {
		return loadObject(generateSmsChangePwd(mobile));
	}
	
	@Override
	public void setSmsChangeMobileCode(String mobile, String random, int exp) {
		if (StringUtils.isNotBlank(mobile)) {
			cacheObject(generateSmsChangeMobileKey(mobile), random, exp);
		}else {
			logger.warn("memcached cache SmsChangeMobileCode ,mobile is null");
		}
	}
	
	@Override
	public String getSmsChangeMobileCode(String mobile) {
		return loadObject(generateSmsChangeMobileKey(mobile));
	}

}
