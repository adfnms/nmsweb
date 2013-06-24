package com.bluecapsystem.nms.service;

import java.util.List;

import com.bluecapsystem.nms.dto.AssetsTbl;
import com.bluecapsystem.nms.dto.CategoryNodeTbl;

public interface SurveillanceService {

	boolean getNodeId(List<CategoryNodeTbl> nodeId);	
	
	boolean getRouters(List<CategoryNodeTbl> routersInfo);	

	boolean getSwitches(List<CategoryNodeTbl> switchesInfo);

	boolean getServers(List<CategoryNodeTbl> serversInfo);
		
}
