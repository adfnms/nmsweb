package com.bluecapsystem.nms.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bluecapsystem.nms.define.Define;


@Controller
public class UserSettingController
{
	private static final Logger logger = LoggerFactory.getLogger(loginController.class);
	
	@RequestMapping(value = "/admin/setting")
	public ModelAndView setting(HttpServletRequest request, HttpServletResponse response, HttpSession session)
	{
		String Id =(String) session.getAttribute(Define.USER_ID_KEY);
		ModelAndView model = new ModelAndView();
		try{
			
			System.out.println("----------------------------------------");
			System.out.println("-------------session-Id--------------"+Id);
			System.out.println("----------------------------------------");
		
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
