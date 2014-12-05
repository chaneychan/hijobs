package com.dream.hijobs.service.token;

import java.util.Date;

import com.dream.hijobs.dao.domain.User;

public class Token {

	private String source;
	
	private boolean isExpire;
	
	private long id;
	
    private String mobile;
    
    private String email;
    
    private String openId;
    
    private int openType;

    private long gmtCreated;

    private long gmtExpire;

    public User toUser(){
    	User user = new User();
    	user.setId(id);
    	user.setMobile(mobile);
    	user.setEmail(email);
    	user.setOpenId(openId);
    	user.setOpenType(openType);
    	user.setGmtCreated(new Date(gmtCreated));
    	return user;
    }
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean isExpire() {
		return isExpire;
	}

	public void setExpire(boolean isExpire) {
		this.isExpire = isExpire;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public long getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(long gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public long getGmtExpire() {
		return gmtExpire;
	}

	public void setGmtExpire(long gmtExpire) {
		this.gmtExpire = gmtExpire;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getOpenType() {
		return openType;
	}

	public void setOpenType(int openType) {
		this.openType = openType;
	}

//	@Override
//	public String toString() {
//		if (isExpire) {
//			try {
//				return TokenHelper.generateUserToken(this.toUser());
//			} catch (Exception e) {
//				return "";
//			}
//		}else {
//			return source;
//		}
//	}
}
