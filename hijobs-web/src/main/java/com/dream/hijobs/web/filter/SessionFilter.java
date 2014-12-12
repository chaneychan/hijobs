package com.dream.hijobs.web.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dream.hijobs.service.dto.Result;
import com.dream.hijobs.service.dto.ResultCode;
import com.dream.hijobs.service.token.Token;
import com.dream.hijobs.service.token.TokenHelper;
import com.dream.hijobs.util.constants;
import com.dream.hijobs.web.controller.FileController;

/**
 * Token验证与SessionFilter
 * @author chaney.chan
 * 2014年11月20日
 */
public class SessionFilter implements Filter {
	
    private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);
    private static ThreadLocal<TokenHelper> tokenHelper = new ThreadLocal<TokenHelper>();
    private List<String> list = new ArrayList<String>();  
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        String path = filterConfig.getServletContext().getContextPath();
	    //初始化不需要拦截的路径
        String exclude = filterConfig.getInitParameter("exclude");  
        if (exclude != null) {
        	StringTokenizer st = new StringTokenizer(exclude);  
        	list.clear();  
        	while (st.hasMoreTokens()) {  
        		list.add(st.nextToken());  
        	}  
        }  
         
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        
        String path=((HttpServletRequest)request).getServletPath();  
//      System.out.println("path="+path);  
        for(int i=0;i<list.size();i++){
            if(path.indexOf(list.get(i))!=-1 || "/".equals(path)){//不需要拦截  ,增加根目录不拦截,让用户可以通过域名直接访问
            	chain.doFilter(request, response);
            	return;
            }
        }
        
        Token token = null;
        String tk = req.getParameter(constants.TOKEN_CMD);
        boolean isWeb = false;
        if (tk == null) {//从parameter取TK
            Cookie[] cookies = req.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie ck : cookies) {//Cookie中取。
                    if (constants.TOKEN_CMD.equals(ck.getName())) {
                        tk = ck.getValue();
                        isWeb = true;
                        break;
                    }
                }
            }
//            if (tk == null) {//从Header 中的Referer中取。
//                tk = req.getHeader("Referer");
//            }
        }

        if (tk != null) {//解析TK
            if (isWeb) {//浏览器
                token = parseUserCaller(URLDecoder.decode(tk, "UTF-8"));
            } else {//手机端。
                token = parseUserCaller(tk);
            }
        }
        if (token != null && token.getId() > 0) {//判断
            request.setAttribute(constants.TOKEN_KEY, token);
            chain.doFilter(request, response);
            return;
        }
        logger.info("token failed validation, tk:"+tk);
        response.getWriter().write(JSON.toJSONString(new Result<String>(ResultCode.ILLEGAL_USER), SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.QuoteFieldNames,SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect));
    }

    @Override
    public void destroy() {
        tokenHelper.remove();
        list.clear();
    }

    /**
     * 解析token
     * @param stringToken
     * @return
     */
    private Token parseUserCaller(String stringToken) {
    	Token token = null;
        if (stringToken != null && stringToken.length() > 0) {
        	token = getTokenHelper().parseUserToken(stringToken);
        }
        return token;
    }

    /**
     * tokenHelper 非线程安全
     * @return
     */
    public TokenHelper getTokenHelper() {
        TokenHelper helper = tokenHelper.get();
        if (helper == null) {
            helper = new TokenHelper();
            tokenHelper.set(helper);
        }
        return helper;
    }

}
