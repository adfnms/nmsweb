package com.bluecapsystem.nms.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.bluecapsystem.nms.dto.ServicesCategoriesTbl;
import com.bluecapsystem.nms.service.ServicesService;

@Controller
public class ServiceController
{
	@Autowired
	private ServicesService servicesService;
	
	@RequestMapping(value = "/getServiceCategories")
	public ModelAndView getServiceCategories(HttpServletRequest request, HttpServletResponse response)
	{
		boolean isSuccess = false;
		String errorMessage = "";
		ModelAndView model =  new ModelAndView();
		List<ServicesCategoriesTbl> ServicesItem = new ArrayList<ServicesCategoriesTbl>();
		if(servicesService.getServicesName(ServicesItem) == false)
		{
			errorMessage = " ServicesItem 목록 조회 실패";
		}
		model.addObject("ServicesItem", ServicesItem);
		model.setViewName("jsonView");
		isSuccess = true;
		model.addObject("isSuccess", isSuccess);
		model.addObject("errorMessage", errorMessage);
		return model;
	}
}
