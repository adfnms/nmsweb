package com.bluecapsystem.nms.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author byun
 * 운영관리 > 노드관리
 */
@Controller
public class NodeManagerController {
	 

	@RequestMapping(value = "/admin/node")
	public ModelAndView nodeManageList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		ModelAndView model =  new ModelAndView();
		
		model.setViewName("/admin/nodeMng/nodeMngList");
		
		return model;
	}
	
	@RequestMapping(value = "/admin/node/nodeMng")
	public ModelAndView nodeManage(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "nodeId", required = false)String nodeId) {
		
		ModelAndView model =  new ModelAndView();
		
		model.addObject("nodeId",nodeId);
		model.setViewName("/admin/nodeMng/nodeMng");
		
		return model;
	}
	
	@RequestMapping(value = "/admin/addNode")
	public ModelAndView addNode(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		ModelAndView model =  new ModelAndView();
		
		model.setViewName("/admin/nodeMng/addNode");
		
		return model;
	}
	
	@RequestMapping(value = "/admin/addNodeList")
	public ModelAndView addNodeList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		ModelAndView model =  new ModelAndView();
		
		model.setViewName("/admin/nodeMng/addNodeList");
		
		return model;
	}
	
	@RequestMapping(value = "/admin/changeNodeLabel")
	public ModelAndView changeNodeLabel(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "nodeId", required = false)String nodeId) {
		
		ModelAndView model =  new ModelAndView();
		
		model.addObject("nodeId",nodeId);
		model.setViewName("/admin/nodeMng/changeNodeLabel");
		
		return model;
	}
}
