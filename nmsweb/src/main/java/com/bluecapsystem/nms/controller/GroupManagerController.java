package com.bluecapsystem.nms.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GroupManagerController {
	 
	private static final Logger logger = LoggerFactory.getLogger(loginController.class);
	/** MAIN CONTROLLER USERMANAGER  
	 * @param request
	 * @param response
	 * @param session
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/admin/menuMng", method = RequestMethod.GET)
	public ModelAndView menuManage(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale) {
		
		ModelAndView model =  new ModelAndView();
		
		model.setViewName("/admin/menuMng/menuMngList");
		
		return model;
	}
	
}
