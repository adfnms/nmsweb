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
	
	@RequestMapping(value = "/admin/notimng/mynoti")
	public ModelAndView showMyNoti(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "user-id", required = false)String userId)
			//@RequestParam(value = "myNotification", required = false)String myNotification,
			//@RequestParam(value = "totalNotification", required = false)String totalNotification,
	{
		String Id =(String) session.getAttribute(Define.USER_ID_KEY);
		ModelAndView model = new ModelAndView();
		
		try{
			
		}catch (Exception e) {
			
			logger.info("regToDb date false");
			e.printStackTrace();
		}
		model.addObject("userId",Id);
		//model.addObject("totalNotification",totalNotification);
		//model.addObject("myNotification",myNotification);
		//model.setViewName("/admin/userSetting/userSetting");
		model.setViewName("/admin/notiMng/myNoti");
		return model;
	}
	@RequestMapping(value = "/admin/notimng/allnoti")
	public ModelAndView showAllNoti(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "user-id", required = false)String userId)
	{
		String Id =(String) session.getAttribute(Define.USER_ID_KEY);
		ModelAndView model = new ModelAndView();
		
		try{
			
		}catch (Exception e) {
			
			logger.info("regToDb date false");
			e.printStackTrace();
		}
		model.addObject("userId",Id);
		model.setViewName("/admin/notiMng/allNoti");
		return model;
	}
	
	@RequestMapping(value = "/admin/notimng/addEvent")
	public ModelAndView addEvent(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("/admin/notiMng/userAddEvent");
		return model;
	}
	
/*	@RequestMapping(value = "/admin/setting/addMessage")
	public ModelAndView addMessage(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("/admin/userSetting/userAddMessage");
		return model;
	}*/
	
	@RequestMapping(value = "/admin/setting/notificationDetali")
	public ModelAndView getNotificationInfo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "notifyid", required = false)String notifyid,
			@RequestParam(value = "eventId", required = false)String eventId)
	
	{
	
		ModelAndView model = new ModelAndView();
		
		model.addObject("notifyid",notifyid);
		model.addObject("eventId",eventId);
		model.setViewName("/admin/userSetting/NotificationInfo");
		return model;
	}
	
	@RequestMapping(value = "/admin/setting/notificationDetali/outages")
	public ModelAndView viewOutage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "nodeId", required = false)String nodeId,
			@RequestParam(value = "nodeLabel", required = false)String nodeLabel)
	{
		ModelAndView model = new ModelAndView();
		model.addObject("nodeId",nodeId);
		model.addObject("nodeLabel",nodeLabel);
		model.setViewName("/admin/userSetting/outage");
		return model;
	}
	@RequestMapping(value = "/admin/notimng/configureNotification")
	public ModelAndView configureNotification(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("/admin/notiMng/configureNotification");
		return model;
	}
	
	@RequestMapping(value = "/admin/notimng/modifyNotification")
	public ModelAndView modifyNotification(HttpServletRequest request, HttpServletResponse response,
	@RequestParam(value = "name", required = false)String name)
	{
		ModelAndView model = new ModelAndView();

		model.addObject("name",name);
		model.setViewName("/admin/notiMng/modifyNotification");
		return model;
	}
	
}
