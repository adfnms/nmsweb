package com.bluecapsystem.nms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bluecapsystem.nms.dto.MenuTbl;
import com.bluecapsystem.nms.service.SystemService;

@Controller
public class MenuManagerController {
	
	
	@Autowired
	private SystemService sysService;
	
	@RequestMapping(value = "/admin/groupMng", method = RequestMethod.GET)
	public ModelAndView groupManage(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale) {
		
		ModelAndView model =  new ModelAndView();
		
		model.setViewName("/admin/groupMng/groupMng");
		
		return model;
	}
	@RequestMapping(value = "/admin/groupMng/modifyGroup", method = RequestMethod.GET)
	public ModelAndView modifyGroup(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
			@RequestParam(value = "sysId", required = false) String sysId,
			@RequestParam(value = "name", required = false)String name) 
	{
		boolean isSuccess = false;
		String errorMessage = "";
		System.out.println("---------------name--------------"+name);
		
		
		ModelAndView model =  new ModelAndView();
		List<MenuTbl> menuItems = new ArrayList<MenuTbl>();
		if(sysService.getMenuList(sysId, menuItems) == false)
		{
			errorMessage = "메뉴 목록 조회 실패";
		}
		
		System.out.println(menuItems.get(0).getMenuId());
		System.out.println(menuItems.get(0).getMenuLev());
		System.out.println(menuItems.get(0).getMenuOrd());
		System.out.println(menuItems.get(0).getUpperMenuId());
		
		model.addObject("menuItems", menuItems);
		
		isSuccess = true;
		
		
		
		
		model.addObject("name",name);
		model.setViewName("/admin/groupMng/modifyGroup");
		
		return model;
	}
	
}
