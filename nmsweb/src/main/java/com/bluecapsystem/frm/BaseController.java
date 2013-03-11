package com.bluecapsystem.frm;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseController 
{
	protected final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	
	
	protected Map<String, String[]> requestParamValuesToMap(HttpServletRequest request)
	{
		
		HashMap<String, String[]> paramMap = new HashMap<String, String[]>();
		
		@SuppressWarnings("rawtypes")
		Enumeration em = request.getParameterNames();
		while(em.hasMoreElements())
		{
			String 		key 	= em.nextElement().toString();
			String[] 	values	= request.getParameterValues(key);
			
			paramMap.put(key, values);
		}
		
		return paramMap;
	}
	
	protected Map<String, Object> requestParamToMap(HttpServletRequest request)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		@SuppressWarnings("rawtypes")
		Enumeration em = request.getParameterNames();
		while(em.hasMoreElements())
		{
			String 		key 	= em.nextElement().toString();
			String[] 	values	= request.getParameterValues(key);
			Object 		obj 	= null;
			
			switch(values.length)
			{
			case 0: break;
			case 1:
				if(values[0] == null || values[0].trim().equals(""))
				{
					obj = null;
				}
				else
				{
					obj = values[0];
				}
				break;
			default:
				obj = values;
				break;		
			}
			
			if(obj == null)
			{
				continue;
			}
			paramMap.put(key, obj);
		}
		
		return paramMap;
	}
	
}
