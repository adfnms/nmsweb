package com.bluecapsystem.nms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluecapsystem.frm.BaseService;
import com.bluecapsystem.nms.dao.AssetsDao;
import com.bluecapsystem.nms.dao.SurveillanceDao;
import com.bluecapsystem.nms.dto.AssetsTbl;
import com.bluecapsystem.nms.dto.CategoriesTbl;
import com.bluecapsystem.nms.dto.CategoryNodeTbl;


@Service(value="SurveillanceService")
public class SurveillanceServiceImpl extends BaseService implements SurveillanceService
{
	
	@Autowired
	private SurveillanceDao surveillanceDao;
	
	
	
	@Override
	public boolean getCategoriesName(List<CategoriesTbl> CategoriesItem) {
		
		boolean ret = false;
		
		ret = surveillanceDao.getCategoriesName(CategoriesItem);
		
		if(ret == false)
		{
			logger.error("fail of get CategoriesName");
		}
		
		return ret;
	}



	@Override
	public Integer getCount(Integer categoryId) 
	{
		
		return surveillanceDao.getCount(categoryId);
		
	}



	@Override
	public boolean getRegNodeList(Integer categoryId, List<CategoriesTbl> RegNodeItems) {

		boolean ret = false;
		
		ret = surveillanceDao.getRegNodeList(categoryId, RegNodeItems);
		
		if(ret == false)
		{
			logger.error("fail of get RegNodeList");
		}
		
		return ret;
	
	}

}
