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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bluecapsystem.nms.define.Define;
import com.bluecapsystem.nms.dto.UserInfoTbl;
import com.bluecapsystem.nms.util.Util;

/**
 * @author KTH
 *	LogIn Controller
 */
@Controller
public class loginController {
	
	private static final Logger logger = LoggerFactory.getLogger(loginController.class);
	
	
	/**LogIn Main Controller
	 * @param locale
	 * @param model
	 * @return	logIn
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String logIn(Locale locale, Model model) {
		
		return "login";
	}
	
	/** user LogIn Controller
	 * @param request
	 * @param response
	 * @param session
	 * @param locale
	 * @param userId  		����� ���̵�
	 * @param passWord		�н�����	
	 * @param userInfoTbl	����� Bean
	 * @return model
	 */
	@RequestMapping(value = "/admin/user/login")
	public ModelAndView memberLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
			@RequestParam(value = "user-id", required = false)String userId,
			@RequestParam(value = "password", required = false)String passWord,
			@ModelAttribute("UserInfoTbl") UserInfoTbl userInfoTbl)
			 {
		
		boolean result = false;
		String message = "";
		ModelAndView model = new ModelAndView();
		System.out.println("--------userId--------"+userId);
		//get userId Info
		String dataUrl = "http://localhost:8080/nmsweb/users/"+userId;
		String jsonStr = "";
		
		_LOGIN:
		
			if(userId== null || userId  == "" || passWord == null || passWord  == ""){// id , password null check
			
			logger.error("�α��� ������ ����");
			
			result = false;
			
			message = "���̵� Ȥ�� ��й�ȣ�� Ȯ���� �ּ���.";
			
			break _LOGIN;
		
			}else{
				
				try {
					jsonStr = Util.getJsonStrToUrl(dataUrl);//get Json String to user Info Url(util) 
					
					ObjectMapper om = new ObjectMapper();
					JsonNode jNode = om.readTree(jsonStr);
					
					String Id = jNode.path("user-id").getTextValue();
					String pwd	= jNode.path("password").getTextValue();
					String name = jNode.path("full-name").getTextValue();
					
					//-------------------------------------------------------
					
					//----------------password decoding later----------------
					
					//-------------------------------------------------------
					
					if(Id. equals(userId) && pwd.equals(passWord) ){//logIn Success and Session Process
						
						
						//userInfoTbl.setUserId(Id);
						//userInfoTbl.setPassword(pwd);
						
						//--------------------------���� ����-----------------------------
						
						request.getSession().setAttribute(Define.USER_ID_KEY, Id); 
						request.getSession().setAttribute(Define.FULL_NAME_KEY, name);	
						
						//--------------------------���� ����-----------------------------
						
						
						
						System.out.println(message);
						result = true;
						
					}else{
						
						logger.error("���̵� �� ��й�ȣ ��ġ ���� ����");
						
						result = false;
						
						message = "���̵� �� ��й�ȣ�� Ȯ���� �ּ���.";
						
						break _LOGIN;
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




