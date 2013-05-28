package com.bluecapsystem.nms.dao;

import java.util.List;

import com.bluecapsystem.nms.dto.AssetsTbl;

public interface AssetsDao 
{
	
	boolean getSearchAssets(String category, List<AssetsTbl> CatagoryList);
	boolean getAssetInfo(Integer nodeId, List<AssetsTbl> AssetInfo);
	
	//boolean getAllAssetList(List<AssetsTbl> CatagoryList);
}
