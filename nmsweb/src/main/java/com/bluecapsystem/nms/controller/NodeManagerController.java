package com.bluecapsystem.nms.controller;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.adflow.nms.web.NodeController;
import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.exception.ValidationException;
import kr.co.adflow.nms.web.service.NodeService;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bluecapsystem.nms.define.NMSProperties;
import com.bluecapsystem.nms.util.Util;


/**
 * @author byun
 * 운영관리 > 노드관리
 */
@Controller
public class NodeManagerController {
	private static final Logger logger = LoggerFactory.getLogger(loginController.class);

	@Autowired
	private NodeService service; 
	
	@Autowired
	private NodeController nodeController;
	
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
	

	/**
	 * 노드 인터페이스 수정
	 * @author kwy
	 * @param request
	 * @param response
	 * @param session
	 * @param nodeId
	 * @param ipAddress
	 * @return
	 */
	@RequestMapping(value = "/admin/ipinterfacesModify/nodeId/{nodeId}/ipAddress/{ipAddress:.+}")
	public ModelAndView ipinterfacesModify(
			HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@PathVariable String nodeId,@PathVariable String ipAddress) {
		 
		ModelAndView model =  new ModelAndView();
		
		boolean result = true;
		String message = "";
		
		ERROR:
		{
			
		
			if(!request.getParameterMap().containsKey("isManaged"))
			{
				result = false;
				message = "오류";
				break ERROR;
			}

			/**
			 * 노드 수정 요청
			 */
			try {
				
				nodeController.nodesIpInterfacesPut(request,nodeId,ipAddress);
			} catch (HandleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/**
			 * 수정후 비교해서 수정됐는지 확인
			 */
			try {
				
				String jsonStr = nodeController.nodeIdIpinterfaces(request,nodeId);
				
				ObjectMapper om = new ObjectMapper();
				JsonNode jNode = om.readTree(jsonStr);
				
				JsonNode ipInterfaceNode = jNode.get("ipInterface");
				String isManaged = ipInterfaceNode.path("@isManaged").getTextValue();
				if(!request.getParameter("isManaged").equals(isManaged))
				{
					result = false;
					message = "변경 실패";
					break ERROR;
				}
				
				result = true;
				message = "변경 성공";
				break ERROR;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		model.addObject("result",result);
		model.addObject("message",message);
		model.setViewName("jsonView");
		
		return model;
	}
	
	/**
	 * 노드 인터페이스 수정
	 * @author kwy
	 * @param request
	 * @param response
	 * @param session
	 * @param nodeId
	 * @param ipAddress
	 * @return
	 */
	@RequestMapping(value = "/admin/ipServiceModify/nodeId/{nodeId}/ipAddress/{ipAddress}/servicesName/{servicesName:.+}")
	public ModelAndView ipServiceModify(
			HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@PathVariable String nodeId,@PathVariable String ipAddress, @PathVariable String servicesName) {
		 
		ModelAndView model =  new ModelAndView();
		
		boolean result = true;
		String message = "";
		String status = "";
		ERROR:
		{
			if(!request.getParameterMap().containsKey("status"))
			{
				result = false;
				message = "오류";
				break ERROR;
			}

			/**
			 * 노드 수정 요청
			 */
			try {
				
				nodeController.nodesIpInterfacesServicesPut(request,nodeId,ipAddress,servicesName);
			} catch (HandleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/**
			 * 수정후 비교해서 수정됐는지 확인
			 */
			
			try {
				
				String jsonStr = nodeController.nodeIdIpinterfacesService(request,nodeId,ipAddress,servicesName);
				
				ObjectMapper om = new ObjectMapper();
				JsonNode jNode = om.readTree(jsonStr);
				
				status = jNode.path("@status").getTextValue();
	
				if(request.getParameter("status").equals(status.toString()) == false)
				{
					result = false;
					message = "변경 실패";
					break ERROR;
				}
				
				result = true;
				message = "변경 성공";
				break ERROR;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		model.addObject("result",result);
		model.addObject("message",message);
		model.addObject("servicesName",servicesName);
		model.addObject("servicesStatus",status);
		
		model.setViewName("jsonView");
		
		return model;
	}
	
	/**
	 * 노르 레벨 변경
	 * @author kwy
	 * @param request
	 * @param response
	 * @param session
	 * @param nodeId
	 * @param label
	 * @return
	 */
	@RequestMapping(value = "/admin/changeNodeLabel")
	public ModelAndView changeNodeLabel(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "nodeId", required = false)String nodeId,
			@RequestParam(value = "label", required = false)String label) {
		
		ModelAndView model =  new ModelAndView();
		boolean result = true;
		String message = "";
		String jsonStr = "";
		/**
		 * 노드 레벨 변경 요청
		 */
		try {
			jsonStr = (String) service.nodesPut(nodeId, label);
		} catch (Exception e) {
		}
		
		/**
		 * 요청후 변경되었는지 확인
		 */
		try {
			jsonStr = (String)service.nodes(nodeId);
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(jsonStr);
			
			String udatelabel = jNode.path("@label").getTextValue();
			
			if(udatelabel.equals(label))
			{
				result = true;
				message = "변경되었습니다.";
			}
			
			
		} catch (Exception e) {
			result = false;
			message = "변경 실패 하였습니다.";
			logger.error("get Json date false");
			e.printStackTrace();
		}
		
		model.addObject("result",result);
		model.addObject("message",message);
		model.setViewName("jsonView");
		
		return model;
	}

}
