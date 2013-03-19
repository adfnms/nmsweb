package com.bluecapsystem.nms.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bluecapsystem.nms.define.Define;
import com.bluecapsystem.nms.dto.UserTbl;
import com.bluecapsystem.nms.service.UserManagerService;
import com.bluecapsystem.nms.util.OutParam;


@Controller
public class UserSettingController
{
	@Autowired
	private UserManagerService userManagerService;
	
	private static final Logger logger = LoggerFactory.getLogger(loginController.class);
	
	@RequestMapping(value = "/admin/setting")
	public ModelAndView setting(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "user-id", required = false)String userId)
	{
		String Id =(String) session.getAttribute(Define.USER_ID_KEY);
		ModelAndView model = new ModelAndView();
		UserTbl userInfo = null;
		
		try{
			OutParam<UserTbl> UserTbl = new OutParam<UserTbl>();
			
			System.out.println("----------------------------------------");
			System.out.println("-------------session-Id--------------"+Id);
			System.out.println("----------------------------------------");
		
			
			if(userManagerService.selectToDb(userId,UserTbl )==false){
				logger.error("User info select to database error");
			}
			
			userInfo = UserTbl.get();
	
			model.addObject("userInfo",userInfo);
			
			
			
		}catch (Exception e) {
			
			logger.info("regToDb date false");
			e.printStackTrace();
		}
		model.addObject("userId",Id);
		
		model.setViewName("/admin/userSetting/userSetting");
		return model;
	}
	
	@RequestMapping(value = "/admin/setting/addEvent")
	public ModelAndView addEvent(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("/admin/userSetting/userAddEvent");
		return model;
	}
	
	@RequestMapping(value = "/admin/setting/addMessage")
	public ModelAndView addMessage(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("/admin/userSetting/userAddMessage");
		return model;
	}
	
	@RequestMapping(value = "/admin/setting/detailNodeInfo")
	public ModelAndView getNodeIdDetailInfo(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("/admin/userSetting/nodeInfo");
		return model;
	}
	
}
