package com.dream.hijobs.web.controller;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dream.hijobs.service.dto.Result;
import com.dream.hijobs.service.dto.ResultCode;
import com.dream.hijobs.service.oss.OssService;

@RestController
@RequestMapping(value="/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    
    @Autowired
    private OssService ossService;
	
	@RequestMapping(method = RequestMethod.POST)
    public String upload(HttpServletRequest request) {
		Result<String> result = new Result<String>();
		try {
	        FileItemFactory factory = new DiskFileItemFactory();
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        List<FileItem> items = upload.parseRequest(request);

            for (FileItem item : items) {
                if (!item.isFormField()) {
                  //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中  
//                  String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
//                  这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
//                  FileUtils.copyInputStreamToFile(item.getInputStream(), new File(realPath, item.getName()));  
                	result= ossService.putFile(item.getName(),item.getInputStream(),item.getSize());
                	if (result.getRc().isSuccess()) {
                		logger.info(result.getValue());
						return result.getValue();
					}
                }
            }
		} catch (Exception e) {
			result.setRc(ResultCode.FILE_UPLOAD_FAIL);
			logger.error("file upload fail",e);
		} 
		return "";
    }
	
	/**
	 * 需要改成域名直接访问，不需要验证
	 * @param url
	 * @param request
	 * @param resp
	 */
	@RequestMapping(value="{url}",method = RequestMethod.GET)
    public void download(@PathVariable String url,HttpServletRequest request,HttpServletResponse resp) {
		try {
//			String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
//			FileUtils.copyFile(new File(realPath, url), resp.getOutputStream());
			InputStream inputStream = ossService.getFile(url);
			if (inputStream != null) {
				FileCopyUtils.copy(inputStream, resp.getOutputStream());
			}
			//TODO  需要改成域名直接访问，不需要验证
		} catch (Exception e) {
			logger.error("file download fail",e);
		}
    }
	
	@RequestMapping(value="{url}",method = RequestMethod.DELETE)
	public boolean delete(@PathVariable String url,HttpServletRequest request,HttpServletResponse resp) {
		logger.info("delete file url:{}",url);
		return ossService.deleteFile(url);
	}
}
