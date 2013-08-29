package com.bluecapsystem.nms.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bluecapsystem.nms.define.Define;
import com.bluecapsystem.nms.dto.CategoriesTbl;
import com.bluecapsystem.nms.dto.CategoryNodeTbl;
import com.bluecapsystem.nms.dto.MenuGroupTbl;
import com.bluecapsystem.nms.service.SurveillanceService;


@Controller
public class SurveillanceController
{
	
	@Autowired
	private SurveillanceService surveillanceService;
	
	@RequestMapping(value = "/surveillanceNode")
	public ModelAndView surveillance(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "categoryid", required = false)Integer categoryId,
			@RequestParam(value = "categoryname", required = false)String categoryname)
			
	{
		
		ModelAndView model = new ModelAndView();
		model.addObject("categoryId", categoryId);
		model.addObject("categoryname", categoryname);
		model.setViewName("/surveillance/suveillanceNode");
		
		return model;
	}
	
	@RequestMapping(value = "/surveillancetotal")
	public ModelAndView surveillanceTotal(HttpServletRequest request, HttpServletResponse response)
			
	{
		
		ModelAndView model = new ModelAndView();
		model.setViewName("/surveillance/surveillanceView");
		
		return model;
	}
	
	
	
	
	
	
	@RequestMapping(value = "/getRegNodeList")
	public ModelAndView getRegNodeList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "categoryid", required = false)Integer categoryId)
	{
		
		boolean isSuccess = false;
		String errorMessage = "";
		
		ModelAndView model =  new ModelAndView();
		
		List<CategoriesTbl> RegNodeItems = new ArrayList<CategoriesTbl>();
		
		if(surveillanceService.getRegNodeList(categoryId,RegNodeItems) == false)
		{
			errorMessage = " CategoriesItem 목록 조회 실패";
		}
		model.addObject("RegNodeItems", RegNodeItems);
		
		model.setViewName("jsonView");
		
		isSuccess = true;

		model.addObject("isSuccess", isSuccess);
		model.addObject("errorMessage", errorMessage);
		
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
	
	@RequestMapping(value = "/regNodePop")
	public ModelAndView regNodePop(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
			@RequestParam(value = "categoryid", required = false)Integer[] categoryid,
			@RequestParam(value = "nodeid", required = false)Integer[] nodeid,
			@ModelAttribute("CategoryNodeTbl") CategoryNodeTbl categoryNodeTbl) 
			
	{
		
		boolean isSuccess = false;
		String errorMessage = "";
		
		ModelAndView model =  new ModelAndView();
		
		 _REG_NODE :
		{
				try{
					if(surveillanceService.regNodePop(categoryid, nodeid, categoryNodeTbl) == false)
					{
						errorMessage = "surveillance 등록 실패";
						break _REG_NODE;
					}
					
					isSuccess = true;
					}
				
				catch (Exception e){
					e.printStackTrace();
				}
			}
		model.setViewName("jsonView");
		model.addObject("isSuccess", isSuccess);
		model.addObject("errorMessage", errorMessage);
		//model.setViewName("/admin/groupMng/groupMng");
		
		return model;
	}

	
	@RequestMapping(value = "/regSurveillenceName")
	public ModelAndView regSurveillenceName(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
			@RequestParam(value = "categoryname", required = false)String categoryname,
			@ModelAttribute("CategoriesTbl") CategoriesTbl categoriesTbl) 
			
	{
		
		boolean isSuccess = false;
		String errorMessage = "";
		
		ModelAndView model =  new ModelAndView();
		
		 _REG_Name :
		{
				try{
					categoriesTbl.setCategoryname(categoryname);
					
					if(surveillanceService.regSurveillenceName(categoriesTbl) == false)
					{
						errorMessage = "surveillanceName 등록 실패";
						break _REG_Name ;
					}
					
					isSuccess = true;
					}
				
				catch (Exception e){
					e.printStackTrace();
				}
			}
		model.setViewName("jsonView");
		model.addObject("isSuccess", isSuccess);
		model.addObject("errorMessage", errorMessage);
		
		return model;
	}
	
	@RequestMapping(value = "/delCategory")
	public ModelAndView delCategory(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
			@RequestParam(value = "categoryid", required = false)Integer categoryid,
			@ModelAttribute("CategoriesTbl") CategoriesTbl categoriesTbl,
			@ModelAttribute("CategoryNodeTbl") CategoryNodeTbl categoryNodeTbl) 
			
	{
		
		boolean isSuccess = false;
		String errorMessage = "";
		
		ModelAndView model =  new ModelAndView();
		
		 _REG_NODE :
		{
				try{
					if(surveillanceService.delNodePop(categoryid, categoryNodeTbl) == false)
					{
						errorMessage = "surveillance 등록 실패";
						break _REG_NODE;
					}
					
					if(surveillanceService.delCategory(categoryid, categoriesTbl) == false)
					{
						errorMessage = "surveillance 등록 실패";
						break _REG_NODE;
					}
					
					isSuccess = true;
					}
				
				catch (Exception e){
					e.printStackTrace();
				}
			}
		model.setViewName("jsonView");
		model.addObject("isSuccess", isSuccess);
		model.addObject("errorMessage", errorMessage);
		//model.setViewName("/admin/groupMng/groupMng");
		
		return model;
	}
	
	
	
	
}
