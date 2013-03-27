package com.bluecapsystem.nms.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bluecapsystem.nms.define.Define;
import com.bluecapsystem.nms.define.NMSProperties;
import com.bluecapsystem.nms.dto.UserTbl;
import com.bluecapsystem.nms.service.UserManagerService;
import com.bluecapsystem.nms.util.OutParam;
import com.bluecapsystem.nms.util.Util;

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
	@RequestMapping(value = "/admin/groupMng", method = RequestMethod.GET)
	public ModelAndView groupManage(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale) {
		
		ModelAndView model =  new ModelAndView();
		
		model.setViewName("/admin/groupMng/groupMng");
		
		return model;
	}
	@RequestMapping(value = "/admin/groupMng/modifyGroup", method = RequestMethod.GET)
	public ModelAndView modifyGroup(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
			@RequestParam(value = "name", required = false)String name) 
	{
		
		System.out.println("---------------name--------------"+name);
		ModelAndView model =  new ModelAndView();
		
		
		model.addObject("name",name);
		model.setViewName("/admin/groupMng/modifyGroup");
		
		return model;
	}
	
}
