package com.dream.hijobs.service.oss;

import java.io.InputStream;

import com.dream.hijobs.service.dto.Result;

/**
 * @author chaney.chan
 * 2014年9月4日
 */
public interface OssService {

	/**
	 * <li>获取文件
	 * <li>用完记得把流关闭，inputStream.close();
	 * @param url
	 * @return
	 */
	public InputStream getFile(String url);
	

	/**
	 * 存储文件
	 * @param fileName 文件名
	 * @param inputStream 文件流
	 * @param contentLength 内容长度
	 * @return 文件url
	 */
	public Result<String> putFile(String fileName ,InputStream inputStream ,long contentLength);
	
	/**
	 * 删除文件
	 * @param url
	 * @return
	 */
	public boolean deleteFile(String url);
}
