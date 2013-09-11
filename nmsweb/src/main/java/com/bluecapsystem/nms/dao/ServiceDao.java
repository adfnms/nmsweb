package com.bluecapsystem.nms.dao;

import java.util.List;

import com.bluecapsystem.nms.dto.ServicesCategoriesTbl;

public interface ServiceDao 
{
	boolean getServicesName(List<ServicesCategoriesTbl> ServicesItem);	
}
