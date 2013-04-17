package com.bluecapsystem.nms.dao;

import java.util.List;

import com.bluecapsystem.nms.dto.MenuTbl;

public interface SystemDao 
{
	boolean selectMenuList(String sysId, List<MenuTbl> refMenuList);
	
}
