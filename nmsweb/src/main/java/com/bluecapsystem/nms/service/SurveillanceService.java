package com.bluecapsystem.nms.service;

import java.util.List;

import com.bluecapsystem.nms.dto.CategoriesTbl;
import com.bluecapsystem.nms.dto.CategoryNodeTbl;
import com.bluecapsystem.nms.dto.MenuGroupTbl;

public interface SurveillanceService {

	boolean getCategoriesName(List<CategoriesTbl> CategoriesItem);
	
	Integer getCount(Integer categoryId);	
	
	boolean getRegNodeList(Integer categoryId, List<CategoriesTbl> RegNodeItems);	
	
	boolean regNodePop(Integer[] categoryid, Integer[] nodeid , CategoryNodeTbl categoryNodeTbl);
	
}
