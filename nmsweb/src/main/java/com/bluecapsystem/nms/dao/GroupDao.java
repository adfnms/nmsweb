package com.bluecapsystem.nms.dao;

import java.util.List;

import com.bluecapsystem.nms.dto.GroupTbl;
import com.bluecapsystem.nms.dto.MenuGroupTbl;
import com.bluecapsystem.nms.dto.MenuTbl;

public interface GroupDao 
{
	boolean selectMenuList(String sysId, List<MenuTbl> refMenuList);
	
	boolean getMenuId(String groupNm, List<MenuGroupTbl> menuIds);
	//public List<AuthTbl> getAuth(Map<String, Object> params);
	public void regGroupTbl(GroupTbl groupTbl);
	
	public void deleteGroupTbl(GroupTbl groupTbl);
	
	public boolean delMenuAuth(String groupName, MenuGroupTbl menuGroupTbl);
	
	public boolean regMenuAuth(MenuGroupTbl menuGroupTbl);
	
}
