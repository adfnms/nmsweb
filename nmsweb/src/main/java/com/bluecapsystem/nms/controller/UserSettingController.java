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
	
	@RequestMapping(value = "/admin/setting/notificationDetali")
	public ModelAndView getNotificationInfo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "notifyid", required = false)String notifyid,
			@RequestParam(value = "eventId", required = false)String eventId)
	{
		
		/*System.out.println("----------------------------------------");
		System.out.println("-------------notifyid--------------"+notifyid);
		System.out.println("-------------eventId--------------"+eventId);
		System.out.println("----------------------------------------");*/
		
		ModelAndView model = new ModelAndView();
		
		model.addObject("notifyid",notifyid);
		model.addObject("eventId",eventId);
		model.setViewName("/admin/userSetting/NotificationInfo");
		return model;
	}
	
	
}
