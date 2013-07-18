package com.bluecapsystem.nms.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class StartScreenController
{
	
	@RequestMapping(value = "/startscreen")
	public ModelAndView StartScreen(HttpServletRequest request, HttpServletResponse response)
	{
		
		ModelAndView model = new ModelAndView();
		model.setViewName("/startscreen");
		return model;
	}
	
}