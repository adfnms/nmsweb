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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bluecapsystem.nms.define.NMSProperties;
import com.bluecapsystem.nms.dto.UserTbl;
import com.bluecapsystem.nms.service.UserManagerService;
import com.bluecapsystem.nms.util.Util;

@Controller
public class UserManagerController {
	
	@Autowired
	private UserManagerService userManagerService;
	 
	private static final Logger logger = LoggerFactory.getLogger(loginController.class);
	/** MAIN CONTROLLER USERMANAGER  
	 * @param request
	 * @param response
	 * @param session
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/admin/userMng/userMng", method = RequestMethod.GET)
	public ModelAndView userManage(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale) {
		
		ModelAndView model =  new ModelAndView();
		
		model.setViewName("/admin/userMng/userMng");
		
		return model;
	}
	
	/**
	 * USER-ID USER CHECK 
	 * @param request
	 * @param response
	 * @param session
	 * @param locale
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/admin/userMng/checkUserId", method = RequestMethod.POST)
	public ModelAndView checkUserId(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
			@RequestParam(value = "user-Id", required = false)String userId)
			
		{
		 
		boolean result = false;
		String message = "";
		ModelAndView model = new ModelAndView();
	   
		System.out.println("---------------------------------------");
	    System.out.println("-------checkUserId---user-Id---------------"+userId);
	    System.out.println("---------------------------------------");
	    
		//get userId Info
		//String dataUrl = "http://localhost:8080/v1/users/"+userId;
		String dataUrl = "http://"+request.getServerName()+":"+request.getServerPort()+"/"+NMSProperties.getNmswebVersion()+"/users/"+userId;
		String jsonStr = "";
		
		_CHECKID:
			
			if(userId== null || userId  == "" ){// Id null check
				
				logger.error("USER ID EMPTY! PLEASE USER ID CHECK ");
				
				result = false;
				
				message = "USER ID EMPTY! PLEASE ENTER A USER ID.";
				
				break _CHECKID;
			
				}else{
					try {
						jsonStr = Util.getJsonStrToUrl(dataUrl); //GET JSON STRING TO USER INFO URL(UTIL) 
						
						ObjectMapper om = new ObjectMapper();
						JsonNode jNode = om.readTree(jsonStr);
						
						String Id = jNode.path("user-id").getTextValue();
						
						System.out.println("-----------------------------");
						System.out.println("-------checkUserId-----get-Id-----------"+Id);
						System.out.println("-----------------------------");
						
						if(Id. equals(userId)){//USERID CHECK PROCESS
							result = false;
							
							logger.error(userId+"IS ALREADY IN USE.");
							
							message = userId+"IS ALREADY IN USE. PLEASE ENTER A DIFFERENT USER ID.";
							
							break  _CHECKID;
							
						}else{
						
						result = true;
						
						}
						
					} catch (IOException e) {
						
						result = false;
						logger.info("get Json date false");
						e.printStackTrace();
					}
					
				}	
		
			model.addObject("result",result);
			model.addObject("message",message);
			model.setViewName("jsonView");
			
			return model;
			}
	
	/**
	 * USER REGISTER TO DATABASE
	 * @param request
	 * @param response
	 * @param session
	 * @param locale
	 * @param userTbl
	 * @param userId
	 * @param regrId
	 * @param modrId
	 * @param userNm
	 * @return
	 */
	@RequestMapping(value = "/admin/userMng/regToDb")
	public ModelAndView  regToDb(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
			@ModelAttribute("UserTbl") UserTbl userTbl,
			@RequestParam(value = "user-Id", required = false)String userId,
			@RequestParam(value = "regrId", required = false)String regrId,
			@RequestParam(value = "modrId", required = false)String modrId,
			@RequestParam(value = "fullName", required = false)String userNm)
			
		{
			ModelAndView model = new ModelAndView();
			try{
				
				
				System.out.println("---------------------------------------");
			    System.out.println("-----regToDb-----user-Id---------------"+userId);
			    System.out.println("-----regToDb-----regrId---------------"+regrId);
			    System.out.println("-----regToDb-----modrId---------------"+modrId);
			    System.out.println("-----regToDb-----fullName---------------"+userNm);
			    System.out.println("---------------------------------------");
			    
			   
			    userTbl.setUseYn("Y");
				
				    if(userManagerService.regToDb (userTbl)==false){
						
						logger.error("USER INFO REGISTER TO DATABASE ERROR ");
					}
				}catch (Exception e) {
					
					logger.info("regToDb date false");
					e.printStackTrace();
				}
			
			
			return model;
			}
	
	/**
	 * USER MODIFY TO DATABASE
	 * @param request
	 * @param response
	 * @param session
	 * @param userTbl
	 * @return
	 */
	@RequestMapping(value = "/admin/userMng/modifyToDb")
	public ModelAndView modifyToDb(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("UserTbl") UserTbl userTbl)
	{
		
		ModelAndView model = new ModelAndView();
		try{
			
			userTbl.setUseYn("Y");
			
			if(userManagerService.modifyToDb(userTbl)==false){
				logger.error("USER INFO MODIFY TO DATABASE ERROR");
			}
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		model.setViewName("/admin/userMng/userMng");
		return model;
	}
	/**
	 * USER DELETE TO DATABASE
	 * @param request
	 * @param response
	 * @param session
	 * @param locale
	 * @param userTbl
	 * @return
	 */
	@RequestMapping(value = "/admin/userMng/deleteToDb", method = RequestMethod.POST)
	public ModelAndView deleteToDb(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
			@ModelAttribute("UserTbl") UserTbl userTbl)
	{
		ModelAndView model = new ModelAndView();
		try{
			
			userTbl.setUseYn("N");
			
			if(userManagerService.deleteToDb(userTbl)==false){
				logger.error("USER INFO DELETE TO DATABASE ERROR");
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		model.setViewName("/admin/userMng/userMng");
		
		return model;
	}
}
