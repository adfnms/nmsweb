package com.bluecapsystem.nms.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserModifyController {
	
	/** 
	 * @param request
	 * @param response
	 * @param session
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/admin/userMng/userModify", method = RequestMethod.GET)
	public ModelAndView userManage(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale) {
		
		ModelAndView model =  new ModelAndView();
		
		model.setViewName("/admin/userMng/userModify");
		
		return model;
	}
	
	
	
	
}
