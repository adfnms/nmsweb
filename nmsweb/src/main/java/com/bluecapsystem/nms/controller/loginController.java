package com.bluecapsystem.nms.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.util.UsersUtil;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bluecapsystem.nms.define.Define;
import com.bluecapsystem.nms.dto.UserInfoTbl;
import com.bluecapsystem.nms.dto.UserTbl;
import com.bluecapsystem.nms.service.UserManagerService;
import com.bluecapsystem.nms.util.Util;

import com.bluecapsystem.nms.define.NMSProperties;

/**
 * @author KTH
 *	LogIn Controller
 */
@Controller
public class loginController {
	
	private static final Logger logger = LoggerFactory.getLogger(loginController.class);
	
	@Autowired
	private UserManagerService userManagerService;
	
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
	 * @param userId  		로그인 ID
	 * @param passWord		로그인 PW	
	 * @param userInfoTbl	
	 * @return model
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView memberLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
			@RequestParam(value = "user-id", required = false)String userId,
			@RequestParam(value = "password", required = false)String passWord)
			 {
		
		boolean result = false;
		String message = "";
		ModelAndView model = new ModelAndView();
		
		//user info json url
		String dataUrl = "http://"+request.getServerName()+":"+request.getServerPort()+"/"+NMSProperties.getNmswebVersion()+"/users/"+userId;
		String jsonStr = "";
		
		_LOGIN:
		{
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("userId", userId);
			UserTbl userInfo =  userManagerService.userInfo(params);
			
			if(userInfo == null)
			{
				
				logger.error("사용자 ID 및 PW가 없음");
				result = false;
				message = "아이디 또는 패스워드를 확인하세요.";
				break _LOGIN;
			}
			
			if(userId== null || userId  == "" || passWord == null || passWord  == ""){// id , password null check
			
			logger.error("사용자 ID 및 PW가 없음");
			
			result = false;
			
			message = "아이디 또는 패스워드를 확인하세요.";
			
			break _LOGIN;
		
			}else{
				
				try {
					
					jsonStr = Util.getJsonStrToUrl(dataUrl);//get Json String to user Info Url(util) 
					
					ObjectMapper om = new ObjectMapper();
					JsonNode jNode = om.readTree(jsonStr);
					
					String Id = jNode.path("user-id").getTextValue();
					String pwd	= jNode.path("password").getTextValue();
					String name = jNode.path("full-name").getTextValue();
					
					//비밀번호 인코딩
					passWord = UsersUtil.encryptString(passWord);

					if(Id.equals(userId) == false)
					{
						logger.error("아이디가 없습니다.");
						result = false;
						message = "아이디가 없습니다.";
						break _LOGIN;
					}
					
					if(pwd.equals(passWord)  == false)
					{
						logger.error("비밀번호가 일치 하지 않습니다.");
						result = false;
						message = "비밀번호가 일치 하지 않습니다.";
						break _LOGIN;
					}
					
						
					//--------------------------세션 생성-----------------------------
					request.getSession().setAttribute(Define.USER_ID_KEY, Id); 
					request.getSession().setAttribute(Define.FULL_NAME_KEY, name);
					request.getSession().setAttribute(Define.GROUP_NM, userInfo.getGroupNm());
					//--------------------------세션 생성-----------------------------
					result = true;
					
				} catch (Exception e) {
					result = false;
					message = "아이디 및 비밀번호를 확인하세요.";
					logger.error("get Json date false");
					e.printStackTrace();
				}
			}
		}
		model.addObject("result",result);
		model.addObject("message",message);
		model.setViewName("jsonView");
		
		return model;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String loginout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		session.invalidate();
		return "redirect:/login.do";
	}
}