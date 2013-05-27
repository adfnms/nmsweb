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

import com.bluecapsystem.nms.dto.AssetsTbl;
import com.bluecapsystem.nms.service.AssetsService;



@Controller
public class AssetsController


{
	
	@Autowired
	private AssetsService assetsService;
	
	@RequestMapping(value = "/assets", method = RequestMethod.GET)
	public ModelAndView assetsMamager(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("/assets/assets");
		return model;
	}

	@RequestMapping(value = "/assets/searchAssets", method = RequestMethod.GET)
	public ModelAndView searchAssets(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "category", required = false)String category)
	{
		
		ModelAndView model = new ModelAndView();
		model.addObject("category", category);
		model.setViewName("/assets/searchAssets");
		return model;
	}
	
	@RequestMapping(value = "/assets/modifyAssets", method = RequestMethod.GET)
	public ModelAndView modifyAssets(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("/assets/modifyAssets");
		return model;
	}
	
	
	@RequestMapping(value = "/assets/selectSearchAssets", method = RequestMethod.GET)
	public ModelAndView getSearchAssets(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "category", required = false)String category)
	{
		
		System.out.println("-------category-----"+category);
		
		boolean isSuccess = false;
		String errorMessage = "";
		
		ModelAndView model =  new ModelAndView();
		
		List<AssetsTbl> CatagoryList = new ArrayList<AssetsTbl>();
		if(assetsService.getSearchAssets(category, CatagoryList) == false)
		{
			errorMessage = "Catagory 검색 실패";
		}
		
		model.addObject("CatagoryList", CatagoryList);
		
		isSuccess = true;
		
		model.addObject("isSuccess", isSuccess);
		model.addObject("errorMessage", errorMessage);
		model.setViewName("jsonView");
		return model;
	}
	

	
	
	
	


	
}
