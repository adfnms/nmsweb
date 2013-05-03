package com.bluecapsystem.nms.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bluecapsystem.frm.BaseController;
import com.bluecapsystem.nms.define.Define;


@Controller
public class MenuManagerController extends BaseController
{
	
	@RequestMapping(value = "/menu/menuHide")
	public ModelAndView menuHide(HttpServletRequest request, HttpServletResponse response, HttpSession session)
	{
		ModelAndView model = new ModelAndView();
		try{
		String Id =(String) session.getAttribute(Define.USER_ID_KEY);
		
		System.out.println("--------------Id--------------");
		System.out.println(Id);
		System.out.println("-------------Id---------------");
		
		}catch (Exception e) {
			
			logger.info("");
			
			e.printStackTrace();
		}
		
		model.setViewName("jsonView");
		return model;
	}

	
}
