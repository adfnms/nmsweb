package com.bluecapsystem.nms.controller;

import java.io.UnsupportedEncodingException;
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

import com.bluecapsystem.frm.BaseController;
import com.bluecapsystem.nms.define.Define;
import com.bluecapsystem.nms.dto.GroupTbl;
import com.bluecapsystem.nms.dto.MenuGroupTbl;
import com.bluecapsystem.nms.dto.MenuTbl;
import com.bluecapsystem.nms.dto.UserTbl;
import com.bluecapsystem.nms.service.GroupService;
import com.bluecapsystem.nms.service.UserManagerService;

@Controller
public class GroupManagerController  extends BaseController {
	 
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private UserManagerService userManagerService;
	
	@RequestMapping(value = "/admin/groupMng", method = RequestMethod.GET)
	public ModelAndView groupManage(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale) {
		
		ModelAndView model =  new ModelAndView();
		
		
		model.setViewName("/admin/groupMng/groupMng");
		
		return model;
	}
	@RequestMapping(value = "/admin/groupMng/modifyGroup")
	public ModelAndView modifyGroup(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
			//@RequestParam(value = "sysId", required = false) String sysId,
			@RequestParam(value = "name", required = false)String name)
	{
		boolean isSuccess = false;
		String errorMessage = "";
		
		
		ModelAndView model =  new ModelAndView();
		//List<MenuTbl> menuItems = new ArrayList<MenuTbl>();
		///if(groupService.getMenuList(sysId, menuItems) == false)
		{
			errorMessage = "메뉴 목록 조회 실패";
		}
		
	//	model.addObject("menuItems", menuItems);
		model.addObject("isSuccess", isSuccess);
		model.addObject("errorMessage", errorMessage);
		//model.setViewName("jsonView");
		
		isSuccess = true;
		
		model.addObject("name",name);
		model.setViewName("/admin/groupMng/modifyGroup");
		
		return model;
	}
	
	
	//메뉴트리 갖고오기
	@RequestMapping(value = "/admin/groupMng/getMenuTree")
	public ModelAndView getMenuTree(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
			@RequestParam(value = "sysId", required = false) String sysId,
			@RequestParam(value = "name", required = false)String name) 
	{
		boolean isSuccess = false;
		String errorMessage = "";
		
		
		ModelAndView model =  new ModelAndView();
		List<MenuTbl> menuItems = new ArrayList<MenuTbl>();
		if(groupService.getMenuList(sysId, menuItems) == false)
		{
			errorMessage = "메뉴 목록 조회 실패";
		}
		
		model.addObject("menuItems", menuItems);
		
		model.setViewName("jsonView");
		
		isSuccess = true;
		
		
		model.addObject("isSuccess", isSuccess);
		model.addObject("errorMessage", errorMessage);
		//model.addObject("name",name);
		//model.setViewName("/admin/groupMng/modifyGroup");
		
		return model;
	}
	
	
	
	@RequestMapping(value = "/admin/groupMng/selectMeunId")
	public ModelAndView modifyGroup(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "groupNm", required = false)String groupNm,
			@ModelAttribute("MenuGroupTbl") MenuGroupTbl menuGroupTbl) 
	{
		boolean isSuccess = false;
		String errorMessage = "";
		
		ModelAndView model =  new ModelAndView();
		List<MenuGroupTbl> menuIds = new ArrayList<MenuGroupTbl>();
		
		model.setViewName("jsonView");
		
		try
		{
			menuIds = groupService.getMenuId(groupNm);
			isSuccess = true;
		}catch(Exception ex)
		{
			errorMessage = "설정 값 불러오기 실패";
			logger.error(ex.getMessage());
		}
		
		
		model.addObject("menuItems", menuIds);
		
		model.addObject("isSuccess", isSuccess);
		model.addObject("errorMessage", errorMessage);
		
		
		return model;
	}
	
	
	
	
	@RequestMapping(value = "/admin/groupMng/regGroup", method = RequestMethod.GET)
	public ModelAndView regGroup(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
			@RequestParam(value = "groupName", required = false)String groupName,
			@RequestParam(value = "comments", required = false)String comments,
			@ModelAttribute("GroupTbl") GroupTbl groupTbl) 
	{
		ModelAndView model =  new ModelAndView();
		
		try{
			String Id =(String) session.getAttribute(Define.USER_ID_KEY);
			groupTbl.setModrId(Id);
			groupTbl.setRegrId(Id);
			groupTbl.setUseYn("Y");
			groupTbl.setGroupComments(comments);
			groupTbl.setGroupNm(groupName);
			
			
			if(groupService.regGroupTbl(groupTbl)==false){
				logger.error("그룹 추가 실패");
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		model.setViewName("/admin/groupMng/groupMng");
		
		return model;
	}
	
	@RequestMapping(value = "/admin/groupMng/deleteGroupTbl", method = RequestMethod.GET)
	public ModelAndView deleteGroupTbl(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
			@RequestParam(value = "groupName", required = false)String groupName,
			@ModelAttribute("GroupTbl") GroupTbl groupTbl) 
	{
		ModelAndView model =  new ModelAndView();
		
		try{
			//String Id =(String) session.getAttribute(Define.USER_ID_KEY);
			//groupTbl.setModrId(Id);
			//groupTbl.setRegrId(Id);
			//groupTbl.setUseYn("N");
			groupTbl.setGroupNm(groupName);
			
			
			if(groupService.deleteGroupTbl(groupTbl)==false){
				logger.error("그룹 삭제 실패");
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		model.setViewName("/admin/groupMng/groupMng");
		
		return model;
	}
	
	@RequestMapping(value = "/admin/userMng/regUserTbl", method = RequestMethod.GET)
	public ModelAndView regUserTbl(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
			@RequestParam(value = "userName", required = false)String userName,
			@RequestParam(value = "groupName", required = false)String groupName,
			@ModelAttribute("UserTbl") UserTbl userTbl)
	{
		ModelAndView model = new ModelAndView();
		
		System.out.println("userName : "+userName);
		System.out.println("groupName : "+groupName);
		
		try{
			String Id =(String) session.getAttribute(Define.USER_ID_KEY);
			
			
			userTbl.setUserId(userName);
			userTbl.setModrId(Id);
		    userTbl.setRegrId(Id);
			userTbl.setUseYn("Y");
			userTbl.setGroupNm(groupName);
			
			if(userManagerService.regUserTbl(userTbl)==false){
				logger.error("USER INFO regUserTbl ERROR");
			}
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		model.setViewName("/admin/userMng/userMng");
		
		
		return model;
	}
	
	@RequestMapping(value = "/admin/userMng/deleteUserTbl", method = RequestMethod.GET)
	public ModelAndView deleteUserTbl(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale,
			@RequestParam(value = "userName", required = false)String userName,
			@RequestParam(value = "groupName", required = false)String groupName,
			@ModelAttribute("UserTbl") UserTbl userTbl)
	{
		ModelAndView model = new ModelAndView();
		
		System.out.println("userName : "+userName);
		System.out.println("groupName : "+groupName);
		
		try{
			String Id =(String) session.getAttribute(Define.USER_ID_KEY);
			
			
			userTbl.setUserId(userName);
			userTbl.setModrId(Id);
		    userTbl.setRegrId(Id);
			userTbl.setUseYn("Y");
			//userTbl.setGroupNm("");
			
			if(userManagerService.deleteUserTbl(userTbl)==false){
				logger.error("USER INFO deleteUserTbl ERROR");
			}
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		model.setViewName("/admin/userMng/userMng");
		
		
		return model;
	}
	
	
	
	
	@RequestMapping(value = "/admin/groupMng/regGroupMenu")
	public ModelAndView regGroupMenu(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "groupName", required = false)String groupName,
			@RequestParam(value = "menuId", required = false)Integer[] menuId) 
	{
		
		boolean isSuccess = false;
		String errorMessage = "권한 그룹 메뉴 등록 성공";
		
		ModelAndView model =  new ModelAndView();
		
		String userId =(String) session.getAttribute(Define.USER_ID_KEY);
		_LOAD_MENU :
		{
			
			if(menuId == null)
			{
				menuId = new Integer[0];
			}
			
			MenuGroupTbl[] menuGroupTbl = new MenuGroupTbl[menuId.length];
			
			for(int i = 0; i < menuId.length; i++)
			{
				
				menuGroupTbl[i] = new MenuGroupTbl();
				menuGroupTbl[i].setMenuId(menuId[i]);
				menuGroupTbl[i].setGroupNm(groupName);
				menuGroupTbl[i].setRegrId(userId);
			}
			
			
			try
			{
				if(groupService.regGroupMenu(groupName, menuGroupTbl) == false)
				{
					errorMessage = "권한 그룹 메뉴 등록 실패";
					break _LOAD_MENU;
				}else
				{
					isSuccess = true;
				}
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
		}
		model.setViewName("jsonView");
		model.addObject("isSuccess", isSuccess);
		model.addObject("errorMessage", errorMessage);
		
		return model;
	}
	

	
	
}

