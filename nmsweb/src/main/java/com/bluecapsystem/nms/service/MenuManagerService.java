package com.bluecapsystem.nms.service;

import java.util.List;

import com.bluecapsystem.nms.dto.UserTbl;
import com.bluecapsystem.nms.util.OutParam;

public interface MenuManagerService {	
	//get Menu id
	public boolean showMenu(String userId, OutParam<List<UserTbl>> userTbl);
}
