package com.dream.hijobs.service.sms;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

//import com.cloopen.rest.sdk.CCPRestSDK;
import com.dream.hijobs.service.memcached.MemcachedService;

/**
 * 短信基类
 * @author chaney.chan
 * 2014年9月4日
 */
public class SmsBase {
	@Value("${dream.hijobs.sms.serverIP}")
	private String serverIP;
	@Value("${dream.hijobs.sms.serverPort}")
	private String serverPort;
	@Value("${dream.hijobs.sms.accountSid}")
	private String accountSid;
	@Value("${dream.hijobs.sms.accountToken}")
	private String accountToken;
	@Value("${dream.hijobs.sms.appId}")
	private String appId;
	@Value("${dream.hijobs.sms.timeout}")
	private String timeout;
	
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}
	
	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}
	
	public void setAccountToken(String accountToken) {
		this.accountToken = accountToken;
	}
	
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
	
	public String getTimeout() {
		return this.timeout;
	}
	
    private static final Logger logger = LoggerFactory.getLogger(SmsBase.class);
	
    @Autowired
    protected MemcachedService memcachedService;	
//    private CCPRestSDK restAPI;
    
    
    @SuppressWarnings("unused")
	private void init(){
//    	restAPI = new CCPRestSDK();
//		restAPI.init(serverIP, serverPort);// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
//		restAPI.setAccount(accountSid, accountToken);// 初始化主帐号名称和主帐号令牌
//		restAPI.setAppId(appId);// 初始化应用ID
    }
    
    protected boolean sendSMS(String mobile ,String templateId ,String code){
		HashMap<String, Object> apiResult = null;

		try {
//			apiResult = restAPI.sendTemplateSMS(mobile,templateId ,new String[]{code,timeout});
		} catch (Throwable t) {
			logger.error("invoke restApi error", t);
			return false;
		}
		
		if(!"000000".equals(apiResult.get("statusCode"))){
			//异常返回输出错误码和错误信息
			logger.error("错误码=" + apiResult.get("statusCode") +" 错误信息= "+apiResult.get("statusMsg"));
			return false;
		}
		return true;
	}
}
