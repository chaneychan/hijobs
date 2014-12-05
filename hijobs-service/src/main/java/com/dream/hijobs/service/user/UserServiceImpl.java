package com.dream.hijobs.service.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StringUtils;

import com.dream.hijobs.dao.domain.User;
import com.dream.hijobs.dao.param.UserPageQuery;
import com.dream.hijobs.dao.repository.UserRepository;
import com.dream.hijobs.service.dto.Result;
import com.dream.hijobs.service.dto.ResultCode;
import com.dream.hijobs.service.dto.UserDTO;
import com.dream.hijobs.service.dto.UserPageDTO;
import com.dream.hijobs.service.enums.OpenType;
import com.dream.hijobs.service.memcached.MemcachedService;
import com.dream.hijobs.service.sms.SmsService;
import com.dream.hijobs.service.token.Token;
import com.dream.hijobs.service.token.TokenHelper;
import com.dream.hijobs.util.Coder;
import com.dream.hijobs.util.Geohash;

/**
 * 用户
 * @author chaney.chan
 * 2014年9月5日
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
	@Autowired
	private UserRepository  userRepository;
	@Autowired
	private SmsService smsService;
	@Autowired
	private MemcachedService memcachedService;
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	public Result<String> register(User user){
		Result<String> result = new Result<String>();
		if (user == null) {
			result.setRc(ResultCode.USERNAME_PWD_NULL);
			return result;
		}
		if (StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getNick()) || StringUtils.isEmpty(user.getPwd())) {
			result.setRc(ResultCode.USERNAME_PWD_NULL);
			return result;
		}
		User thisUser = userRepository.selectByEmail(user.getEmail());
		if (thisUser != null) {
			result.setRc(ResultCode.EMAIL_EXISTS);
			return result;
		}

		String geoHash = Geohash.encode(user.getLatitude(), user.getLongitude());
		user.setGeohash(geoHash);
		
		try {
			user.setIsDel(false);
			user.setLastLogin(new Date());
			user.setPwd(new String(Coder.Md5Encode(user.getPwd().getBytes())));
			userRepository.insert(user);
		} catch (Exception e) {
			logger.error("register error", e);
			result.setRc(ResultCode.REGISTER_ERROR);
			return result;
		}
		
		try {
			String token = new TokenHelper().generateUserToken(user);
			result.setValue(token);
		} catch (Exception e) {
			result.setRc(ResultCode.TOKEN_GENERATE_ERROR);
			logger.error("generate token failed", e);
		}
		return result;
	}


	public Result<String> registerByOpen(User user) {
		Result<String> result = new Result<String>();
		if (user == null) {
			result.setRc(ResultCode.USERNAME_PWD_NULL);
			return result;
		}
		if (StringUtils.isEmpty(user.getOpenId()) || StringUtils.isEmpty(user.getOpenType())) {
			result.setRc(ResultCode.OPENID_NULL);
			return result;
		}
		User thisUser = userRepository.selectByOpen(user.getOpenType(),user.getOpenId());
		if (thisUser != null) {
			result.setRc(ResultCode.OPENID_EXISTS);
			return result;
		}

		String geoHash = Geohash.encode(user.getLatitude(), user.getLongitude());
		user.setGeohash(geoHash);
		
		try {
			user.setIsDel(false);
			user.setLastLogin(new Date());
			userRepository.insert(user);
		} catch (Exception e) {
			logger.error("register error", e);
			result.setRc(ResultCode.REGISTER_ERROR);
			return result;
		}
		
		try {
			String token = new TokenHelper().generateUserToken(user);
			result.setValue(token);
		} catch (Exception e) {
			result.setRc(ResultCode.TOKEN_GENERATE_ERROR);
			logger.error("generate token failed", e);
		}
		return result;

	}
	
	public Result<String> load(User user){
		logger.info("load, email:{},pwd:{}",user.getEmail(),user.getPwd());
		Result<String> result = new Result<String>();
		User thisUser = userRepository.selectByEmail(user.getEmail());
		if (thisUser == null) {
			result.setRc(ResultCode.USER_NOT_FIND);
			return result;
		}
		try {
			if (!thisUser.getPwd().trim().equals(Coder.Md5Encode(user.getPwd().trim().getBytes()))){
				result.setRc(ResultCode.PWD_ERROR);
				return result;
			}
		} catch (Exception e1) {
			result.setRc(ResultCode.PWD_ERROR);
			logger.error("encryptMD5 failed", e1);
			return result;
		}
		thisUser.setLastLogin(new Date());
		thisUser.setLatitude(user.getLatitude());
		thisUser.setLongitude(user.getLongitude());
		String geoHash = Geohash.encode(user.getLatitude(), user.getLongitude());
		thisUser.setGeohash(geoHash);
		thisUser.setDevId(user.getDevId());
		thisUser.setDevType(user.getDevType());
		userRepository.update(thisUser);
		try {
			String token = new TokenHelper().generateUserToken(thisUser);
			result.setValue(token);
		} catch (Exception e) {
			result.setRc(ResultCode.TOKEN_GENERATE_ERROR);
			logger.error("generate token failed", e);
		}
		return result;
	}
	

	@Override
	public Result<String> loadByOpen(User user) {
		logger.info("load openId :{},openType :{}",user.getOpenId(),user.getOpenType());
		Result<String> result = new Result<String>();
		User thisUser = userRepository.selectByOpen(user.getOpenType(), user.getOpenId());
		if (thisUser != null) {
			thisUser.setLastLogin(new Date());
			thisUser.setLatitude(user.getLatitude());
			thisUser.setLongitude(user.getLongitude());
			String geoHash = Geohash.encode(user.getLatitude(), user.getLongitude());
			thisUser.setGeohash(geoHash);
			thisUser.setDevId(user.getDevId());
			thisUser.setDevType(user.getDevType());
			userRepository.update(thisUser);
			try {
				String token = new TokenHelper().generateUserToken(thisUser);
				result.setValue(token);
			} catch (Exception e) {
				result.setRc(ResultCode.TOKEN_GENERATE_ERROR);
				logger.error("generate token failed", e);
			}
		}else {
			result.setRc(ResultCode.USER_NOT_FIND);
			logger.info("user not find");
		}
		return result;
	}
//	
//	public Result<String> changePwd(Token token ,String pwd ,String code){
//		Result<String> result = new Result<String>();
//		boolean b = smsService.verifyChangePwdCode(token.getMobile(), code);
////		boolean b =true;
//		if (!b) {
//			result.setRc(ResultCode.VERIFY_CODE_ERROR);
//			return result;
//		}
//		User user = token.toUser();
//		user.setPwd(pwd);
//		user.setGmtModified(new Date());
//		user.setLastLogin(new Date());
//		updateUser(user);
//		return result;
//	}
//	
//	public Result<String> forgetPwd(String email ,String pwd ,String mobile ,String code){
//		Result<String> result = new Result<String>();
//		boolean b = smsService.verifyChangePwdCode(mobile, code);
////		boolean b =true;
//		if (!b) {
//			result.setRc(ResultCode.VERIFY_CODE_ERROR);
//			return result;
//		}
//		User user = getUser(userName);
//		if (user == null) {
//			result.setRc(ResultCode.USER_NOT_FIND);
//			logger.info("user not find");
//			return result;
//		}
//		user.setPwd(pwd);
//		user.setGmtModified(new Date());
//		user.setLastLogin(new Date());
//		updateUser(user);
//		return result;
//	}


	@Override
	public void updateLastLogin(Token token) {
		User user = new User();
		user.setId(token.getId());
		user.setLastLogin(new Date());
		userRepository.update(user);
	}

	@Override
	public Result<UserPageDTO> getUsers(UserPageQuery userPageQuery) {
		Result<UserPageDTO> result = new Result<UserPageDTO>();
		UserPageDTO dto = new UserPageDTO();
		List<User> users = userRepository.getUsers(userPageQuery);
		int totalCount = userRepository.getUsersCount(userPageQuery);
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for (User user : users) {
			if (user != null) {
				UserDTO userDTO = new UserDTO();
				BeanUtils.copyProperties(user, userDTO);
				userDTOs.add(userDTO);
			}
		}
		dto.setList(userDTOs);
		dto.setTotalCount(totalCount);
		result.setValue(dto);
		return result;
	}


	@Override
	public Result<String> update(Token token, User user) {
		logger.info("update user, openType :{},userId :{},mobile :{}",token.getOpenType(),token.getId(),user.getMobile());
		Result<String> result = new Result<String>();
		
		User thisUser = null;
		if (OpenType.OWN.isEqual(token.getOpenType())) {
			thisUser = userRepository.selectByEmail(token.getEmail());
		}else {
			thisUser = userRepository.selectByOpen(token.getOpenType(),token.getOpenId());
		}
		if (thisUser != null) {
			BeanUtils.copyProperties(user,thisUser);
			user.setGmtModified(new Date());
			user.setLastLogin(new Date());
			userRepository.update(user);
		}else {
			result.setRc(ResultCode.USER_NOT_FIND);
			logger.info("user not find");
			return result;
		}
		
		try {
			String userToken = new TokenHelper().generateUserToken(thisUser);
			result.setValue(userToken);
		} catch (Exception e) {
			logger.error("generate token failed", e);
		}
		return result;
	}

}
