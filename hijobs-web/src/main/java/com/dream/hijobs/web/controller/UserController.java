package com.dream.hijobs.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dream.hijobs.dao.domain.User;
import com.dream.hijobs.dao.param.UserPageQuery;
import com.dream.hijobs.service.dto.Result;
import com.dream.hijobs.service.dto.ResultCode;
import com.dream.hijobs.service.dto.UserDTO;
import com.dream.hijobs.service.dto.UserPageDTO;
import com.dream.hijobs.service.sms.SmsService;
import com.dream.hijobs.service.token.Token;
import com.dream.hijobs.service.user.UserService;
import com.dream.hijobs.util.constants;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
	@Autowired
	private UserService userService;
	@Autowired
	private SmsService smsService;

	
	@RequestMapping(value="load")
	public Result<String> load(HttpServletRequest request,UserDTO userDTO) {
		Result<String> result = new Result<String>();
		if (userDTO == null) {
			result.setRc(ResultCode.USER_NOT_FIND);
			logger.info("user is null");
		}
		User user = new User();
		BeanUtils.copyProperties(userDTO ,user);
		result = userService.load(user);
		return result;
	}
	
	@RequestMapping("loadOpen")
	public Result<String> loadByOpen(HttpServletRequest request,UserDTO userDTO) {
		Result<String> result = new Result<String>();
		if (userDTO == null) {
			result.setRc(ResultCode.USER_NOT_FIND);
			logger.info("user is null");
		}
		User user = new User();
		BeanUtils.copyProperties(userDTO ,user);
		result = userService.loadByOpen(user);
		return result;
	}
	
	@RequestMapping(value="reg",method=RequestMethod.POST)
	public Result<String> reg(HttpServletRequest request,UserDTO userDTO) {
		User user = new User();
		BeanUtils.copyProperties(userDTO ,user);
		return userService.register(user);
	}
	
	@RequestMapping(value="regOpen")
	public Result<String> regByOpen(HttpServletRequest request,UserDTO userDTO) {
		User user = new User();
		BeanUtils.copyProperties(userDTO ,user);
		return userService.registerByOpen(user);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public Result<String> update(HttpServletRequest request,UserDTO userDTO){
		Token token = (Token) request.getAttribute(constants.TOKEN_KEY);
		User user = new User();
		BeanUtils.copyProperties(userDTO ,user);
		return userService.update(token,user);
	}
	
	@RequestMapping("page/{page}")
	public Result<UserPageDTO> getUsers(@PathVariable String page, HttpServletRequest request){
		String mobile = request.getParameter("key");
		logger.info("user search key:{},page:{}",mobile,page);
		int pageNo = 1;
		try {
			pageNo = Integer.valueOf(page);
		} catch (Exception e) {
			// TODO: handle exception
		}
		UserPageQuery userPageQuery = new UserPageQuery();
		userPageQuery.setPageNo(pageNo);
		userPageQuery.setMobile(mobile);
		return userService.getUsers(userPageQuery);
	}
}
