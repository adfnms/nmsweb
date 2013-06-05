package com.bluecapsystem.nms.dao;

import java.util.List;

import com.bluecapsystem.nms.dto.AssetsTbl;

public interface AssetsDao 
{
	
	boolean getSearchAssets(String category, List<AssetsTbl> CatagoryList);
	boolean getAssetInfo(Integer nodeId, List<AssetsTbl> AssetInfo);
	void modifyToAssets(AssetsTbl assetsTbl);
	boolean fieldSearch(AssetsTbl assetsTbl, List<AssetsTbl> fieldInfo);
	
	//boolean fieldSearch(List<AssetsTbl> assetsTbl);
}
