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
 * 모니터링 > 노드 목록
 */
@Controller
public class NodeListController
{
	
	/**노드 목록 페이지
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/monitoring/nodelist")
	public ModelAndView searchNode(HttpServletRequest request, HttpServletResponse response)
	{
		
		ModelAndView model = new ModelAndView();
		model.setViewName("/monitoring/node/nodeList");
		return model;
	}
}