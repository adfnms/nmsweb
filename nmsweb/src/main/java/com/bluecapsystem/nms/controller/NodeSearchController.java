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
 * 검색 > 노드 검색
 */
@Controller
public class NodeSearchController
{
	
	/**노드 검색 페이지
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/monitering/node/search", method = RequestMethod.GET)
	public ModelAndView searchNode(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("/monitering/node/search");
		return model;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/monitering/node/nodeDesc", method = RequestMethod.GET)
	public ModelAndView nodeDescription(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "nodeId", required = false)String nodeId)
	{
		ModelAndView model = new ModelAndView();

		System.out.println("==== Node Description : nodeId ["+nodeId+"] ====");
		
		model.addObject("nodeId",nodeId);
		model.setViewName("/monitering/node/nodeDesc");
		return model;
	}
	
}
