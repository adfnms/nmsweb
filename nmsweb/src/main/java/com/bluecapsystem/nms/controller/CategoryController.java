package com.bluecapsystem.nms.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CategoryController
{
	
	@RequestMapping(value = "/category/nodeList", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "cateNm", required = false)String cateNm) 
	{
		/*System.out.println("----------------------------------");
		System.out.println(cateNm);
		System.out.println("----------------------------------");*/
		
		ModelAndView model = new ModelAndView();
		model.addObject("cateNm",cateNm);
		model.setViewName("/category/nodeList");
		
		return model;
	}

	
}
