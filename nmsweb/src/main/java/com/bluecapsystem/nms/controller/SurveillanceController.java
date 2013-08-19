package com.bluecapsystem.nms.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bluecapsystem.nms.dto.CategoriesTbl;
import com.bluecapsystem.nms.service.SurveillanceService;


@Controller
public class SurveillanceController
{
	
	@Autowired
	private SurveillanceService surveillanceService;
	
	@RequestMapping(value = "/surveillance", method = RequestMethod.GET)
	public ModelAndView surveillance(HttpServletRequest request, HttpServletResponse response)
			
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("/surveillance/surveillanceView");
		
		return model;
	}
	
	
	
	@RequestMapping(value = "/getSurveillanceCategories")
	public ModelAndView getSurveillanceCategories(HttpServletRequest request, HttpServletResponse response)
	{
		
		boolean isSuccess = false;
		String errorMessage = "";
		
		ModelAndView model =  new ModelAndView();
		
		List<CategoriesTbl> CategoriesItem = new ArrayList<CategoriesTbl>();
		
		if(surveillanceService.getCategoriesName(CategoriesItem) == false)
		{
			errorMessage = " CategoriesItem 목록 조회 실패";
		}
		model.addObject("CategoriesItem", CategoriesItem);
		
		model.setViewName("jsonView");
		
		isSuccess = true;

		model.addObject("isSuccess", isSuccess);
		model.addObject("errorMessage", errorMessage);
		
		return model;
	}
	
	
	@RequestMapping(value = "/Categories/getCount")
	public ModelAndView getCount(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "categoryid", required = false)Integer categoryId) 
	{
		
		boolean isSuccess = false;
		String errorMessage = "";
		
		ModelAndView model =  new ModelAndView();
		
		
		
		System.out.println("-----------categoryid------------");
		System.out.println(categoryId);
		
		Integer categoriesCount = surveillanceService.getCount(categoryId);
		
		if(categoriesCount == null)
		{
			errorMessage = " CategoriesCount 목록 조회 실패";
		}
		model.addObject("CategoriesCount", categoriesCount);
		
		model.setViewName("jsonView");
		
		isSuccess = true;

		model.addObject("isSuccess", isSuccess);
		model.addObject("errorMessage", errorMessage);
		
		return model;
	}
	
	
	
	
	
	
}
