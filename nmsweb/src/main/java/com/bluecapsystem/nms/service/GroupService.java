package com.bluecapsystem.nms.service;

import java.util.List;

import com.bluecapsystem.nms.dto.GroupTbl;
import com.bluecapsystem.nms.dto.MenuGroupTbl;
import com.bluecapsystem.nms.dto.MenuTbl;
import com.bluecapsystem.nms.dto.UserTbl;

public interface GroupService {	
	//Menu Tree
	boolean getMenuList(String sysId, List<MenuTbl> refMenuList);
	
	
	//Menu Tree
	List<MenuGroupTbl> getMenuId(String groupNm) throws Exception;
	
	
	/**
	 * 권한 리스트 
	 * @param authTbl
	 * @return
	 */
	//spublic boolean getAuth(OutParam<List<AuthTbl>> authTbl);
	
	public boolean regGroupTbl(GroupTbl groupTbl);
	public boolean deleteGroupTbl(GroupTbl groupTbl);
	
	
	
	public boolean regGroupMenu(String groupName, MenuGroupTbl[] groupMenues);
}
