package com.bluecapsystem.nms.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	@RequestMapping(value = "/admin/userMng/userReg", method = RequestMethod.GET)
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
	@RequestMapping(value = "/nms/admin/user/checkUserId")
	public ModelAndView checkUserId(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
			@RequestParam(value = "user-Id", required = false)String userId)
			
		{
		
		System.out.println("-------------userId-----------"+userId);
		
		boolean result = false;
		String message = "";
		ModelAndView model = new ModelAndView();
		
		//get userId Info
		String dataUrl = "http://localhost:8080/v1/users/"+userId;
		
		String strJson = "";
		
		_CHECKID:
			
			if(userId== null || userId  == "" ){// Id null check
				
				logger.error("사용자 등록 ID 데이터 없음");
				
				result = false;
				
				message = "아이디를 입력헤 주세요.";
				
				break _CHECKID;
			
				}else{
					try {
						strJson = Util.getJsonStrToUrl(dataUrl); //GET JSON STRING TO USER INFO URL(UTIL) 
						
						/*Object obj = JSONValue.parse(strJson);
						
						JSONObject Object =(JSONObject)obj;*/
						
						String Id = "";
					
						if(Id. equals(userId)){//USERID CHECK PROCESS
							result = false;
							
							logger.error("기존 아이디 존재. 아이디 변경 요구");
							
							message = userId+"는 사용중입니다. 다른 아이디를 입력해주세요.";
							
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
	
	
}
