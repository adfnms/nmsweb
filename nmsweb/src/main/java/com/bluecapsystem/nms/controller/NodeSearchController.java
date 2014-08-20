package com.bluecapsystem.nms.controller;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.NodeController;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bluecapsystem.nms.dto.NodeTbl;
import com.bluecapsystem.nms.service.NodeService;

/**
 * @author byun
 * 검색 > 노드 검색
 */
@Controller
public class NodeSearchController
{

	private static final Logger logger = LoggerFactory.getLogger(NodeController.class);
	
	@Autowired
	private NodeService service; 
	
	/**노드 검색 페이지
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/search/node")
	public ModelAndView searchNode(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = false)String nodeId,
			@RequestParam(value = "label", required = false)String nodeLabel,
			@RequestParam(value = "serviceId", required = false)String serviceId,
			@RequestParam(value = "ipAddress", required = false)String ipAddress)
	{
		
		ModelAndView model = new ModelAndView();
		model.addObject("nodeId",nodeId);
		model.addObject("nodeLabel",nodeLabel);
		model.addObject("serviceId",serviceId);
		model.addObject("ipAddress",ipAddress);
		
		model.setViewName("/search/node/search");
		return model;
	}
	
	/**노드 검색 페이지
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/search/seachNodeList")
	public ModelAndView seachNodeList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = false)Integer nodeId,
			@RequestParam(value = "label", required = false)String nodeLabel,
			@RequestParam(value = "serviceId", required = false)Integer serviceId,
			@RequestParam(value = "ipAddress", required = false)String ipAddress)
	{
		
		Boolean success = false;
		String message = "";
			
		ModelAndView model = new ModelAndView();
		
		HashMap<String,Object> paramter = new HashMap<String,Object>();
		
		paramter.put("nodeId", nodeId);      
		paramter.put("nodeLabel",nodeLabel);
		paramter.put("serviceId",serviceId);
		paramter.put("ipAddress",ipAddress);
		
		
		List<NodeTbl> nodeList= service.searchNodList(paramter);
		
		ERROR:
		{
			if(nodeList == null || nodeList.size() == 0)
			{
				success = false;
				message ="검색 실패";
				break ERROR;
			}
			
			success = true;
			message ="검색 성공";
			
			model.addObject("nodeList",nodeList);
		}
		
		model.addObject("success",success);
		model.addObject("message",message);
		
//		if(ipAddress != null && !ipAddress.equals(""))
//		{
//			logger.info(PATH + request.getRequestURI());
//	
//			try {
//				resultSearchIp = (String) service.nodeSearchIp(ipAddress);
//			} catch (HandleException e) {
//				logger.error("Failed in processing", e);
//			}
//	
//			logger.debug(RETURNRESULT + resultSearchIp);
//		}
//
//		if(serviceId != null && !serviceId.equals(""))
//		{
//			try {
//				resultSearchService = (String) service.nodeSearchService(serviceId);
//			} catch (HandleException e) {
//				logger.error("Failed in processing", e);
//			}
//	
//			logger.debug(RETURNRESULT + resultSearchService);
//		
//		}
//		
//		if( (nodeLabel!=null && !nodeLabel.equals("") )||( nodeId!=null && !nodeId.equals("")))
//		{
//			try {
//				resultSearchNodeIdAndnodeLabel = (String) service.nodeSearchNodeIdAndLikeNodelabel(nodeId,nodeLabel);
//			} catch (HandleException e) {
//				logger.error("Failed in processing", e);
//			}
//
//			logger.debug(RETURNRESULT + resultSearchNodeIdAndnodeLabel);
//		}
		
		
		model.setViewName("jsonView");
		
		return model;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/search/node/nodeDesc")
	public ModelAndView nodeDescription(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "nodeId", required = false)String nodeId)
	{
		ModelAndView model = new ModelAndView();

		model.addObject("nodeId",nodeId);
		model.setViewName("/search/node/nodeDesc");
		return model;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/search/node/interfaceDesc")
	public ModelAndView interfaceDescription(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "nodeId", required = false)String nodeId,
			@RequestParam(value = "intf", required = false)String intf)
	{
		ModelAndView model = new ModelAndView();

		model.addObject("intf",intf);
		model.addObject("nodeId",nodeId);
		model.setViewName("/search/node/interface/interfaceDesc");
		return model;
	}
	
	/**
	 * @param request
	 * @param response
	 * @param eventId
	 * @return
	 */
	@RequestMapping(value = "/search/event/eventDesc")
	public ModelAndView eventDescription(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "eventId", required = false)String eventId)
	{
		ModelAndView model = new ModelAndView();
		
		model.addObject("eventId",eventId);
		model.setViewName("/search/event/eventDesc");
		
		return model;
	}
	
	/**
	 * @param request
	 * @param response
	 * @param eventId
	 * @return
	 */
	@RequestMapping(value = "/search/outage/outageDesc")
	public ModelAndView outageDescription(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "outageId", required = false)String outageId)
	{
		ModelAndView model = new ModelAndView();
		
		model.addObject("outageId",outageId);
		model.setViewName("/search/outage/outageDesc");
		
		return model;
	}
	
	/**
	 * @param request
	 * @param response
	 * @param nodeId
	 * @param intf
	 * @param serviceId
	 * @return
	 */
	@RequestMapping(value = "/search/service/serviceDesc")
	public ModelAndView serviceDescription(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "nodeId", required = false)String nodeId,
			@RequestParam(value = "intf", required = false)String intf,
			@RequestParam(value = "serviceNm", required = false)String serviceNm)
	{
		ModelAndView model = new ModelAndView();
		
		model.addObject("nodeId",nodeId);
		model.addObject("intf",intf);
		model.addObject("serviceNm",serviceNm);
		
		model.setViewName("/search/node/interface/service/serviceDesc");
		
		return model;
	}
}
