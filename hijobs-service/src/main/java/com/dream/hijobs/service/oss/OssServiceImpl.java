package com.dream.hijobs.service.oss;

import java.io.InputStream;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dream.hijobs.service.dto.Result;
import com.dream.hijobs.service.dto.ResultCode;
import com.dream.hijobs.util.Coder;

/**
 * @author chaney.chan
 * 2014年9月3日
 */
@Service
public class OssServiceImpl extends OssBase implements OssService {

	private static final Logger logger = LoggerFactory.getLogger(OssServiceImpl.class);
	
    private static String ossGenerate(String url) throws Exception {
    	String src = "oss-" + new Date().getTime()+ url;
    	return Coder.base64UrlSafeEncode(src.getBytes());
    }
    
	public Result<String> putFile(String fileName ,InputStream inputStream ,long contentLength){
		Result<String> result = new Result<String>();
		String key = "";
		try {
			key = ossGenerate(fileName);
		} catch (Exception e) {
			result.setRc(ResultCode.OSS_PUT_ERROR);
			logger.error("oss generate error",e);
			return result;
		}
		boolean b = putObject(key, inputStream, contentLength);
		if (b) {
			result.setValue(key);
		} else {
			result.setRc(ResultCode.OSS_PUT_ERROR);
		}
		return result;
	}
	
	public InputStream getFile(String key){
		logger.info("key : "+key);
		InputStream inputStream = getObject(key);
		//TODO 关闭流
		return inputStream;
	}
	
	public static void main(String[] args) {
		
	}
}
