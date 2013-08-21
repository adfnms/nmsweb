package com.bluecapsystem.nms.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bluecapsystem.frm.BaseController;
import com.bluecapsystem.nms.define.Define;
import com.bluecapsystem.nms.dto.UserTbl;
import com.bluecapsystem.nms.service.MenuManagerService;
import com.bluecapsystem.nms.util.OutParam;


@Controller
public class MenuManagerController extends BaseController
{
	@Autowired
	private MenuManagerService menuManagerService;
	
	//@SuppressWarnings("unused")
	@RequestMapping(value = "/menu/showMenu")
	public ModelAndView showMenu(HttpServletRequest request, HttpServletResponse response, HttpSession session)
	{
		ModelAndView model = new ModelAndView();
		
		List<UserTbl> userList = null; 
		String groupNm =null;
		OutParam<List<UserTbl>> userTbl =new OutParam<List<UserTbl>>();
		
		try{
			 
			String userId =(String) session.getAttribute(Define.USER_ID_KEY);

			if(menuManagerService.showMenu(userId, userTbl)==false)
		 	{
				logger.error("로그인 권한 해당 메뉴 리스트 실패");
			}
		
			userList =userTbl.get();
			
			
			if(userList.size() !=0){
				
				groupNm =userList.get(0).getGroupNm();
				
			}else{
				
				groupNm = "visitor";
			
			}
			
			
			
			
			
		}catch (Exception e) {
			
			logger.info("");
			
			e.printStackTrace();
		}
		model.addObject("groupNm", groupNm);
		model.addObject("userList", userList);
		model.setViewName("jsonView");
		//model.setViewName("/monitoring/node/nodeList");
		
		return model;
	}
}
