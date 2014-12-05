package com.dream.hijobs.service.memcached;


/**
 * @author chaney.chan
 * 2014年9月7日
 */
public interface MemcachedService {
	
	public void setSmsRegCode(String mobile, String random, int exp);
	
	public String getSmsRegCode(String mobile);
	
	public void setSmsChangePwdCode(String mobile, String random, int exp);
	
	public String getSmsChangePwdCode(String mobile);
	
	public void setSmsChangeMobileCode(String mobile, String random, int exp);
	
	public String getSmsChangeMobileCode(String mobile);

}
