package com.bluecapsystem.nms.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author byun
 * 검색 > 장애 검색
 */
@Controller
public class OutageSearchController
{
	
	/**노드 검색 페이지
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/search/outage")
	public ModelAndView searchNode(HttpServletRequest request, HttpServletResponse response)
	{
		
		ModelAndView model = new ModelAndView();
		model.setViewName("/search/outage/search");
		return model;
	}
	
}