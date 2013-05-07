package com.bluecapsystem.nms.dao;

import java.util.List;
import java.util.Map;

import com.bluecapsystem.nms.dto.UserTbl;

public interface MenuManagerDao 
{
	public List<UserTbl> showMenu(Map<String, Object> params);
	
}
