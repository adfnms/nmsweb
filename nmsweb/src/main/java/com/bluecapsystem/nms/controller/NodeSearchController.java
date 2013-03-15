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
	public ModelAndView searchNode(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = false)String nodeId,
			@RequestParam(value = "label", required = false)String nodeLabel,
			@RequestParam(value = "serviceId", required = false)String serviceId,
			@RequestParam(value = "ipAddress", required = false)String ipAddress)
	{
		
		System.out.println("==============");
		
		System.out.println("nodeId: "+nodeId);
		System.out.println("nodeLabel: "+nodeLabel);
		System.out.println("serviceId: "+serviceId);
		System.out.println("ipAddress: "+ipAddress);
		
		System.out.println("==============");
		
		ModelAndView model = new ModelAndView();
		model.addObject("nodeId",nodeId);
		model.addObject("nodeLabel",nodeLabel);
		model.addObject("serviceId",serviceId);
		model.addObject("ipAddress",ipAddress);
		
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

		model.addObject("nodeId",nodeId);
		model.setViewName("/monitering/node/nodeDesc");
		return model;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/monitering/node/interfaceDesc", method = RequestMethod.GET)
	public ModelAndView interfaceDescription(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "nodeId", required = false)String nodeId,
			@RequestParam(value = "intf", required = false)String intf)
	{
		ModelAndView model = new ModelAndView();

		model.addObject("intf",intf);
		model.addObject("nodeId",nodeId);
		model.setViewName("/monitering/node/interface/interfaceDesc");
		return model;
	}
}
