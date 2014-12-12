package com.dream.hijobs.service.oss;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;

/**
 * oss基础类
 * @author chaney.chan
 * 2014年9月4日
 */
public class OssBase {
	@Value("${dream.hijobs.oss.accessKeyId}")
	private String accessKeyId;
	@Value("${dream.hijobs.oss.accessKeySecret}")
	private String accessKeySecret;
	@Value("${dream.hijobs.oss.bucketName}")
	private String bucketName;
	
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	private static final Logger logger = LoggerFactory.getLogger(OssBase.class);
    
    private OSSClient client;
    
	private OSSClient getClient(){
        // 初始化一个OSSClient
		//TODO ossclient增加多线程
		if (client == null) {
			client = new OSSClient(accessKeyId, accessKeySecret);
		}
		return client;
	}
    
	protected boolean putObject(String key, InputStream inputStream ,long contentLength) {

	    // 创建上传Object的Metadata
	    ObjectMetadata meta = new ObjectMetadata();

	    // 必须设置ContentLength
	    meta.setContentLength(contentLength);

	    try {
	    	// 上传Object
	    	getClient().putObject(bucketName, key, inputStream, meta);
			logger.info("file Object key: [" + key + "]");
		} catch (Throwable t) {
			logger.error("oss putObject error", t);
			return false;
		} finally {
			try {
				inputStream.close();
			} catch (IOException ex) {
				logger.error(" oss putObject inputStream close error", ex);
			}
		}
	    return true;
	}
	
	
	protected InputStream getObject(String key) {
		OSSObject object = null;
		try {
			// 获取Object，返回结果为OSSObject对象
			object = getClient().getObject(bucketName, key);
		} catch (Throwable t) {
			logger.error("oss getObject error", t);
		}
		if (object != null) {
			return object.getObjectContent();
		}
		return null;
	}
	
	protected boolean deleteObject(String key) {
		try {
			getClient().deleteObject(bucketName, key);
		} catch (Throwable t) {
			logger.error("oss delete error", t);
			return false;
		}
		return true;
	}
}
