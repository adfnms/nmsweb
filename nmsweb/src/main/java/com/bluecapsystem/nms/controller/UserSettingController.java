package com.bluecapsystem.nms.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserSettingController
{
	
	@RequestMapping(value = "/admin/setting")
	public ModelAndView setting(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView();
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
