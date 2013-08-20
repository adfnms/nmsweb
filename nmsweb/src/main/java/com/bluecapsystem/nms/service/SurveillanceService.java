package com.bluecapsystem.nms.service;

import java.util.List;

import com.bluecapsystem.nms.dto.CategoriesTbl;

public interface SurveillanceService {

	boolean getCategoriesName(List<CategoriesTbl> CategoriesItem);
	
	Integer getCount(Integer categoryId);	
	
	boolean getRegNodeList(Integer categoryId, List<CategoriesTbl> RegNodeItems);	
}
