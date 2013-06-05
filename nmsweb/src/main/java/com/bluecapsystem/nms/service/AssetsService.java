package com.bluecapsystem.nms.service;

import java.util.List;

import com.bluecapsystem.nms.dto.AssetsTbl;

public interface AssetsService {	
	//Menu Tree
		boolean getSearchAssets(String category, List<AssetsTbl> CatagoryList);
		boolean getAssetInfo(Integer nodeId, List<AssetsTbl> AssetInfo);
		boolean modifyToAssets(AssetsTbl assetsTbl);
		boolean fieldSearch(AssetsTbl assetsTbl ,List<AssetsTbl> fieldInfo);
}
