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

import com.bluecapsystem.nms.define.Define;
import com.bluecapsystem.nms.define.NMSProperties;
import com.bluecapsystem.nms.dto.UserTbl;
import com.bluecapsystem.nms.service.UserManagerService;
import com.bluecapsystem.nms.util.OutParam;
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
	@RequestMapping(value = "/admin/userMng", method = RequestMethod.GET)
	public ModelAndView userManage(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale) {
		
		ModelAndView model =  new ModelAndView();
		
		model.setViewName("/admin/userMng/userMng");
		
		return model;
	}
	
	@RequestMapping(value = "/admin/userMng/userReg")
	public ModelAndView userReg(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
						@RequestParam(value = "user-id", required = false)String userId)
	{
		
		ModelAndView model =  new ModelAndView();
		
		model.setViewName("/admin/userMng/userReg");
		
		return model;
	}
	
	@RequestMapping(value = "/admin/userMng/modify",  method = RequestMethod.POST)
	public ModelAndView modify(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
						@RequestParam(value = "user-id", required = false)String userId)
	{
		ModelAndView model =  new ModelAndView();
		
		model.setViewName("/admin/userMng/userModify");
		
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
	   
		
		//get userId Info
		//String dataUrl = "http://localhost:8080/v1/users/"+userId;
		String dataUrl = "http://"+request.getServerName()+":"+request.getServerPort()+"/"+NMSProperties.getNmswebVersion()+"/users/"+userId;
		String jsonStr = "";
		
		_CHECKID:
			
			if(userId== null || userId  == "" ){// Id null check
				
				logger.error("User id empty! Please user id check ");
				
				result = false;
				
				message = "User id empty! Please enter a user id.";
				
				break _CHECKID;
			
				}else{
					try {
						jsonStr = Util.getJsonStrToUrl(dataUrl); //GET JSON STRING TO USER INFO URL(UTIL) 
						
						System.out.println("-----------------jsonStr---------------"+jsonStr);
						ObjectMapper om = new ObjectMapper();
						JsonNode jNode = om.readTree(jsonStr);
						
						String Id = jNode.path("user-id").getTextValue();
						
						if(Id. equals(userId)){//USERID CHECK PROCESS
							result = false;
							
							logger.error(userId+" is already in use.");
							
							message = "<"+userId+"> is already in use. Please enter a different user id.";
							
							break  _CHECKID;
							
						}else{
						
						result = true;
						//------------------
						//   아이디 체크 성콩시 데이터 값을 갖고 오지 못해서 
						//   오류가 나지만 등록은 문제되지 않는다. 후에 조정 필요
						//------------------
						}
						
					} catch (IOException e) {
						
						result = false;
						logger.info("get Json date false");
						//e.printStackTrace();
					}
					
				}	
		
			model.addObject("result",result);
			model.addObject("message",message);
			model.setViewName("jsonView");
			
			return model;
			}
	
	/** 
	 * @param request
	 * @param response
	 * @param session
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/admin/userMng/userModify", method = RequestMethod.POST)
	public ModelAndView userManage(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
						@RequestParam(value = "user-id", required = false)String userId)
	{
		
		boolean result = false;
		
		String message = "";
		
		ModelAndView model =  new ModelAndView();
		
		UserTbl userInfo = null;
		OutParam<UserTbl> UserTbl = new OutParam<UserTbl>();
		
		//get userId Info
		String dataUrl = "http://localhost:8080/v1/users/"+userId;
		
		String jsonStr = "";
		
		try {
			jsonStr = Util.getJsonStrToUrl(dataUrl); //GET JSON STRING TO USER INFO URL(UTIL) 
			//jsonStr = "{\"user-id\":\"adflow\",\"full-name\":\"adflow\",\"user-comments\":\"\",\"password\":\"21232F297A57A5A743894A0E4A801FC3\"}";
			
			
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(jsonStr);
			
			String Id = jNode.path("user-id").getTextValue();
			String fullName = jNode.path("full-name").getTextValue();
			String userComments = jNode.path("user-comments").getTextValue();
			String password = jNode.path("password").getTextValue();
			
			if(userManagerService.selectToDb(userId,UserTbl )==false){
				logger.error("User info select to database error");
			}
			
			userInfo = UserTbl.get();
	
			model.addObject("userInfo",userInfo);
			model.addObject("Id",Id);
			model.addObject("fullName",fullName);
			model.addObject("userComments",userComments);
			model.addObject("password",password);
			
			
			
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
			@RequestParam(value = "fullName", required = false)String userNm)
			
		{
			ModelAndView model = new ModelAndView();
			try{
				
				String Id =(String) session.getAttribute(Define.USER_ID_KEY);
			    
			    userTbl.setUserId(userId);
			    userTbl.setUserNm(userNm);
			    userTbl.setModrId(Id);
			    userTbl.setRegrId(Id);
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
			String Id =(String) session.getAttribute(Define.USER_ID_KEY);
			
			userTbl.setModrId(Id);
		    userTbl.setRegrId(Id);
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
			String Id =(String) session.getAttribute(Define.USER_ID_KEY);
			
			userTbl.setModrId(Id);
		    userTbl.setRegrId(Id);
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
