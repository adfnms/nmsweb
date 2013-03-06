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


@Controller
public class UserModifyController {
	
	private static final Logger logger = LoggerFactory.getLogger(loginController.class);
	/** 
	 * @param request
	 * @param response
	 * @param session
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/admin/userMng/userModify")
	public ModelAndView userManage(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
						
						@RequestParam(value = "user-id", required = false)String userId)
	{
		System.out.println("--------------userId----------------"+userId);
		
		boolean result = false;
		
		String message = "";
		
		
		ModelAndView model =  new ModelAndView();
		
		//get userId Info
		String dataUrl = "http://localhost:8080/v1/users/"+userId;
		
		String jsonStr = "";
		
		try {
			//jsonStr = Util.getJsonStrToUrl(dataUrl); //GET JSON STRING TO USER INFO URL(UTIL) 
			jsonStr = "{\"user-id\":\"adflow\",\"full-name\":\"adflow\",\"user-comments\":\"\",\"password\":\"21232F297A57A5A743894A0E4A801FC3\"}";
			
			
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(jsonStr);
			
			String Id = jNode.path("user-id").getTextValue();
			String fullName = jNode.path("full-name").getTextValue();
			String userComments = jNode.path("user-comments").getTextValue();
			String password = jNode.path("password").getTextValue();
			
			model.addObject("Id",Id);
			model.addObject("fullName",fullName);
			model.addObject("userComments",userComments);
			model.addObject("password",password);
			
			System.out.println("----------Id-----------"+Id);
			System.out.println("----------fullName-----------"+fullName);
			System.out.println("------------userComments---------"+userComments);
			System.out.println("---------password------------"+password);
			
			
		} catch (IOException e) {
			
			result = false;
			logger.info("get Json date false");
			e.printStackTrace();
		}
		
		model.addObject("result",result);
		model.addObject("message",message);
		//model.setViewName("jsonView");
		model.setViewName("/admin/userMng/userModify");
		
		return model;
	}
	
	
	@RequestMapping(value = "/admin/userMng/getUserDetail", method = RequestMethod.GET)
	public ModelAndView getUserDetailInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
						@RequestParam(value = "user-id", required = false)String userId)
	{
		System.out.println("--------------userId----------------"+userId);
		
		ModelAndView model =  new ModelAndView();
		
		model.setViewName("/admin/userMng/userModify");
		
		return model;
	}
	
	
}
