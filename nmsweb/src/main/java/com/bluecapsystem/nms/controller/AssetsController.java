package com.bluecapsystem.nms.controller;


import java.util.ArrayList;
import java.util.List;

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

import com.bluecapsystem.nms.define.Define;
import com.bluecapsystem.nms.dto.AssetsTbl;
import com.bluecapsystem.nms.dto.UserTbl;
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
	@RequestMapping(value = "/assets/searchField", method = RequestMethod.GET)
	public ModelAndView searchField(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "fieldName", required = false)String fieldName,
			@RequestParam(value = "fieldValue", required = false)String fieldValue)
	{
		
		ModelAndView model = new ModelAndView();
		model.addObject("fieldName", fieldName);
		model.addObject("fieldValue", fieldValue);
		model.setViewName("/assets/searchField");
		return model;
	}
	
	@RequestMapping(value = "/assets/modifyAssets", method = RequestMethod.GET)
	public ModelAndView modifyAssets(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "nodeId", required = false)String nodeId,
			@RequestParam(value = "nodeLabel", required = false)String nodeLabel)
	{
		
		ModelAndView model = new ModelAndView();
		
		model.addObject("nodeId", nodeId);
		model.addObject("nodeLabel", nodeLabel);
		model.setViewName("/admin/nodeMng/nodeMng");
		/*model.setViewName("/assets/modifyAssets"); */ /*해당페이지 나중에 삭제*/
		return model;
	}
	
	
	@RequestMapping(value = "/assets/selectSearchAssets", method = RequestMethod.GET)
	public ModelAndView getSearchAssets(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "category", required = false)String category)
	{
		
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
	
	
	
	@RequestMapping(value = "/assets/getAssetInfo", method = RequestMethod.GET)
	public ModelAndView getAssetInfo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "nodeId", required = false) Integer nodeId)
	{
		boolean isSuccess = false;
		String errorMessage = "";
		String nodeLavel = "";
		ModelAndView model =  new ModelAndView();
		
		List<AssetsTbl> AssetInfo = new ArrayList<AssetsTbl>();
		if(assetsService.getAssetInfo(nodeId, AssetInfo) == false)
		{
			errorMessage = "Catagory 검색 실패";
		}
		
		model.addObject("AssetInfo", AssetInfo);
		model.addObject("nodeLavel", nodeLavel);
		isSuccess = true;
		model.addObject("isSuccess", isSuccess);
		model.addObject("errorMessage", errorMessage);
		model.setViewName("jsonView");
		return model;
	}
	
	
	@RequestMapping(value = "/assets/regAssets")
	public ModelAndView regAssets(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@ModelAttribute("AssetsTbl") AssetsTbl assetsTbl)
	{
		ModelAndView model =  new ModelAndView();
		try{
			String Id =(String) session.getAttribute(Define.USER_ID_KEY);
			
			assetsTbl.setUserlastmodified(Id);
			
			
			if(assetsService.modifyToAssets(assetsTbl)==false){
			}
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		model.setViewName("/admin/userMng/userMng");
		return model;
	}
	
	@RequestMapping(value = "/assets/fieldSearch")
	public ModelAndView fieldSearch(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@ModelAttribute("AssetsTbl") AssetsTbl assetsTbl)
	{
		
		boolean isSuccess = false;
		String errorMessage = "";
		
		ModelAndView model =  new ModelAndView();
		List<AssetsTbl> fieldInfo = new ArrayList<AssetsTbl>();
		if(assetsService.fieldSearch(assetsTbl, fieldInfo)==false){
				errorMessage = "Catagory 검색 실패";
			
			isSuccess = true;
		}
		
		model.addObject("fieldData", fieldInfo);
		model.addObject("isSuccess", isSuccess);
		model.addObject("errorMessage", errorMessage);
		model.setViewName("jsonView");
		
		return model;
	
	}
}
