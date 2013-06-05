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
		ret = assetsDao.getAssetInfo(nodeId, AssetInfo);
		
		if(ret == false)
		{
			logger.error("fail of get menu Id");
		}
		
		return ret;
	}

	@Override
	public boolean modifyToAssets(AssetsTbl assetsTbl) {
		boolean ret = false;
		try
		{
			assetsDao.modifyToAssets(assetsTbl);
			
			ret = true;
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			ret = false;
		}finally
		{
			
		}
		return ret;
	}

	@Override
	public boolean fieldSearch(AssetsTbl assetsTbl, List<AssetsTbl>  fieldInfo) {
		boolean ret = false;
		try
		{
			assetsDao.fieldSearch(assetsTbl, fieldInfo);
			ret = true;
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			ret = false;
		}finally
		{
			
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
