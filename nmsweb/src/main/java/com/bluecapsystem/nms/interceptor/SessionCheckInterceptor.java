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

public class SessionCheckInterceptor extends HandlerInterceptorAdapter{
	
	protected final Logger logger = LoggerFactory.getLogger(SessionCheckInterceptor.class);
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{
		logger.info("in preHandle");
		HttpSession session = request.getSession();
		ModelAndView mnv = new ModelAndView();
		
		String reUrl = request.getRequestURI().toString();
		
		logger.info("reDir >> " + reUrl);
		mnv.addObject("reDir", reUrl);
		mnv.setViewName(Define.CMS_LOGIN_URL);
				
		if(reUrl.equals(Define.CMS_LOGIN_URL) == true)
		{
			return super.preHandle(request, response, handler);
		}
		
		if (session == null) 
		{
			throw new ModelAndViewDefiningException(mnv);
		}
		
		// login 상태 check
		String 	userId = (String)session.getAttribute(Define.USER_ID_KEY);
		
		// 로그인 안되어 있다면...
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
