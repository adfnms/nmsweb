package com.bluecapsystem.nms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluecapsystem.frm.BaseService;
import com.bluecapsystem.nms.dao.AssetsDao;
import com.bluecapsystem.nms.dto.AssetsTbl;


@Service(value="AssetsService")
public class AssetsServiceImpl extends BaseService implements AssetsService
{
	
	@Autowired
	private AssetsDao assetsDao;

	@Override
	public boolean getSearchAssets(String category, List<AssetsTbl> CatagoryList) {
		boolean ret = false;
		
		ret = assetsDao.getSearchAssets(category, CatagoryList);
		
		if(ret == false)
		{
			logger.error("fail of get menu Id");
		}
		
		return ret;
	}

	@Override
	public boolean getAssetInfo(Integer nodeId, List<AssetsTbl> AssetInfo) {
		boolean ret = false;
		System.out.println("---------AssetsService------"+nodeId);
		ret = assetsDao.getAssetInfo(nodeId, AssetInfo);
		
		if(ret == false)
		{
			logger.error("fail of get menu Id");
		}
		
		return ret;
	}

	/*@Override
	public boolean getAllAssetList(List<AssetsTbl> CatagoryList) {
		
		boolean ret = false;
		
		ret = assetsDao.getAllAssetList(CatagoryList);
		
		if(ret == false)
		{
			logger.error("fail of get menu Id");
		}
		
		return ret;
	}*/

}
