package com.bluecapsystem.nms.service;

import java.util.List;

import com.bluecapsystem.nms.dto.MenuTbl;

public interface SystemService {	
	
	boolean getMenuList(String sysId, List<MenuTbl> refMenuList);
	
}
