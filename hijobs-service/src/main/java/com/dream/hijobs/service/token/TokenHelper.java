package com.dream.hijobs.service.token;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dream.hijobs.dao.domain.User;
import com.dream.hijobs.util.AESCoder;

/**
 * 处理使用DES秘钥加密用户信息而产生的token
 * @author chaney.chan
 * 2014年9月5日
 */
public class TokenHelper {
    private static final Logger logger                   = LoggerFactory.getLogger(TokenHelper.class);
    private static final String VALUE_SPLIT_REGEX        = "\\|";
    
    /**
     * 生成token
     * @param user
     * @return
     * @throws Exception 
     */
    public String generateUserToken(User user) throws Exception{
    	String token = "";
    	token += user.getId() + "|";
    	token += user.getMobile() + "|";
    	token += user.getEmail() + "|";
    	token += user.getDevId() + "|";
    	token += user.getDevType() + "|";
    	token += new Date().getTime() + "|";
    	token += DateUtils.addMonths(new Date(), 1).getTime() + "|";
    	token = AESCoder.encrypt(token);
    	return token;
    }


    /**
     * 解析token
     * <li>如果token过期会重新生成
     * @param token
     * @return
     */
    public Token parseUserToken(String source) {
    	Token token = new Token();
        String[] sourceData;
		try {
			sourceData = AESCoder.decrypt(source).split(VALUE_SPLIT_REGEX);
		} catch (Exception e) {
			logger.error("parse token error",e);
			return null;
		}
        if (sourceData.length != 7) {
            logger.error("invalid token,source data is not enough!");
            return null;
        }
        if (sourceData[0] != null && !sourceData[0].isEmpty()) {
        	token.setId(Long.parseLong(sourceData[0].trim()));
        }
        if (sourceData[1] != null && !sourceData[1].isEmpty()) {
        	token.setMobile(sourceData[1].trim());
        }
        if (sourceData[2] != null && !sourceData[2].isEmpty()) {
        	token.setEmail(sourceData[2].trim());
        }
        if (sourceData[3] != null && !sourceData[3].isEmpty()) {
        	token.setOpenId(sourceData[3].trim());
        }
        if (sourceData[4] != null && !sourceData[4].isEmpty()) {
        	token.setOpenType(Integer.parseInt(sourceData[4].trim()));
        }
        if (sourceData[5] != null && !sourceData[5].isEmpty()) {
        	token.setGmtCreated(Long.parseLong(sourceData[5].trim()));
        }
        if (sourceData[6] != null && !sourceData[6].isEmpty()) {
        	token.setGmtExpire(Long.parseLong(sourceData[6].trim()));
        }
        token.setSource(source);
        if (new Date(token.getGmtExpire()).before(new Date())) {
        	token.setExpire(true);
        	token.setGmtExpire(new Date().getTime());
		}
        return token;
    }
}