package com.bluecapsystem.nms.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bluecapsystem.nms.util.Util;

@Controller
public class UserRegController {
	
	private static final Logger logger = LoggerFactory.getLogger(loginController.class);
	
	/**USER REGISTRATION MAIN 
	 * @param request
	 * @param response
	 * @param session
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/admin/userMng/userReg")
	public ModelAndView userManage(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale) {
		
		ModelAndView model =  new ModelAndView();
		
		model.setViewName("/admin/userMng/userReg");
		
		return model;
	}
	
	/**
	 * USER-ID CHECK 
	 * @param request
	 * @param response
	 * @param session
	 * @param locale
	 * @param userId
	 * @return
	 */
	
	
}
