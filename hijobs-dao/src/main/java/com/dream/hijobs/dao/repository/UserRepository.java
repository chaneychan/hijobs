package com.dream.hijobs.dao.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dream.hijobs.dao.domain.User;
import com.dream.hijobs.dao.mapper.UserMapper;
import com.dream.hijobs.dao.param.UserPageQuery;

/**
 * @author chaney.chan
 * 2014年9月5日
 */
@Repository
public class UserRepository {
	
	@Autowired
	private UserMapper mapper;
	
	public long insert(User user){
		user.setGmtCreated(new Date());
		user.setGmtModified(new Date());
		user.setLastLogin(new Date());
		return mapper.insert(user);
	}
	
	public User selectByEmail(String email){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		return mapper.selectByEmail(params);
	}
	
	public User selectByOpen(int openType,String openId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openType", openType);
		params.put("openId", openId);
		return mapper.selectByOpen(params);
	}
	
	public int update(User user){
		return mapper.updateByPrimaryKeySelective(user);
	}
	
	public User selectById(long id){
		return mapper.selectByPrimaryKey(id);
	}
	
	public List<User> getUsers(UserPageQuery userPageQuery){
		return mapper.getUsers(userPageQuery);
	}
	
	public int getUsersCount(UserPageQuery userPageQuery){
		return mapper.getUsersCount(userPageQuery);
	}
}
