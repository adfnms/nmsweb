package com.bluecapsystem.nms.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bluecapsystem.nms.define.Define;
import com.bluecapsystem.nms.define.NMSProperties;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter{
	
	protected final Logger logger = LoggerFactory.getLogger(SessionCheckInterceptor.class);
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{
		
		HttpSession session = request.getSession();
		ModelAndView mnv = new ModelAndView();
		
		String reUrl = request.getRequestURI().toString();
		String loginUtl = "/"+NMSProperties.getNmswebVersion()+"/login.do";
		
		logger.info("reDir >> " + reUrl);
		mnv.addObject("reDir", reUrl);
		mnv.setViewName("/login");
				
		if(reUrl.equals(loginUtl) == true)
		{
			return super.preHandle(request, response, handler);
		}
		
		if (session == null) 
		{
			throw new ModelAndViewDefiningException(mnv);
		}
		
		// login ID check
		String 	userId = (String)session.getAttribute(Define.USER_ID_KEY);
		
		// 로그인 되어있지 않음
		if(userId == null)
		{
			throw new ModelAndViewDefiningException(mnv);
		}
		
		return super.preHandle(request, response, handler);
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mnv) throws Exception 
	{
	}
	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

}
