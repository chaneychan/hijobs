package com.dream.hijobs.dao.mapper;

import java.util.List;
import java.util.Map;

import com.dream.hijobs.dao.domain.User;
import com.dream.hijobs.dao.param.UserPageQuery;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectByEmail(Map<String, Object> params);
    
    User selectByOpen(Map<String, Object> params);
    
    List<User> getUsers(UserPageQuery userPageQuery);
    
    int getUsersCount(UserPageQuery userPageQuery);
}