package com.bluecapsystem.nms.dao;

import java.util.List;

import com.bluecapsystem.nms.dto.AssetsTbl;
import com.bluecapsystem.nms.dto.CategoriesTbl;
import com.bluecapsystem.nms.dto.CategoryNodeTbl;

public interface SurveillanceDao 
{
	boolean getCategoriesName(List<CategoriesTbl> CategoriesItem);	
	
	Integer getCount(Integer categoryId);

	boolean getRegNodeList(Integer categoryId, List<CategoriesTbl> RegNodeItems);
	
	
	boolean delNodePop(Integer categoryId,CategoryNodeTbl categoryNodeTbl);
	
	boolean delCategory(Integer categoryId,CategoriesTbl categoriesTbl);
	
	boolean regNodePop(CategoryNodeTbl categoryNodeTbl);
	
	boolean regSurveillenceName(CategoriesTbl categoriesTbl);
}
