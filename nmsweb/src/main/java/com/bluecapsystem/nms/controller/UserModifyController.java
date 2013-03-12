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
public class UserModifyController {
	
	private static final Logger logger = LoggerFactory.getLogger(loginController.class);
	/**
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param locale
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/admin/userMng/modify",  method = RequestMethod.POST)
	public ModelAndView getUserDetailInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
						@RequestParam(value = "user-id", required = false)String userId)
	{
		System.out.println("--------------userId----------------"+userId);
		
		ModelAndView model =  new ModelAndView();
		
		model.setViewName("/admin/userMng/userModify");
		
		return model;
	}
	
	
}
