package com.bluecapsystem.nms.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bluecapsystem.nms.dto.CategoryNodeTbl;
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
	
	
	
	@RequestMapping(value = "/getSurveillance", method = RequestMethod.GET)
	public ModelAndView getSurveillance(HttpServletRequest request, HttpServletResponse response)
	{
		
		boolean isSuccess = false;
		String errorMessage = "";
		
		ModelAndView model =  new ModelAndView();
		
		/*List<CategoryNodeTbl> routersInfo = new ArrayList<CategoryNodeTbl>();
		List<CategoryNodeTbl> switchesInfo = new ArrayList<CategoryNodeTbl>();
		List<CategoryNodeTbl> serversInfo = new ArrayList<CategoryNodeTbl>();
		
		if(surveillanceService.getRouters(routersInfo)==false){
				errorMessage = "Surveillance 검색 실패";
			
			isSuccess = true;
		}
		if(surveillanceService.getSwitches(switchesInfo)==false){
			errorMessage = "Surveillance 검색 실패";
	
			isSuccess = true;
		}
		if(surveillanceService.getServers(serversInfo)==false){
			errorMessage = "Surveillance 검색 실패";
		
			isSuccess = true;
		}*/
		
		
		List<CategoryNodeTbl> nodeId = new ArrayList<CategoryNodeTbl>();
		
		if(surveillanceService.getNodeId(nodeId)==false){
			errorMessage = "nodeId 검색 실패";
		}
		
		System.out.println("---------------------------------------");
		for( int i=0 ; i < nodeId.size(); i++)
    	{
			System.out.println(nodeId.get(i).getNodeid());
    	}
		System.out.println("---------------------------------------");
		
		model.addObject("nodeId", nodeId);
		isSuccess = true;
		
		model.addObject("isSuccess", isSuccess);
		model.addObject("errorMessage", errorMessage);
		model.setViewName("jsonView");
		
		return model;
	}
	
	

	
}
