package com.bluecapsystem.nms.dao;

import java.util.List;

import com.bluecapsystem.nms.dto.AssetsTbl;
import com.bluecapsystem.nms.dto.CategoryNodeTbl;

public interface SurveillanceDao 
{
	
	
	boolean getRouters(List<CategoryNodeTbl> routersInfo);	

	boolean getSwitches(List<CategoryNodeTbl> switchesInfo);

	boolean getServers(List<CategoryNodeTbl> serversInfo);
	
	boolean getNodeId(List<CategoryNodeTbl> nodeId);	
}
