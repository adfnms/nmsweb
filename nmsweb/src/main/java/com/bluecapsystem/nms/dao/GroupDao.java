package com.bluecapsystem.nms.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.bluecapsystem.nms.dto.GroupTbl;
import com.bluecapsystem.nms.dto.MenuGroupTbl;
import com.bluecapsystem.nms.dto.MenuTbl;

public interface GroupDao 
{
	boolean selectMenuList(String sysId, List<MenuTbl> refMenuList);
	
	List<MenuGroupTbl> getMenuId(String groupNm) throws DataAccessException;
	
	//public List<AuthTbl> getAuth(Map<String, Object> params);
	public void regGroupTbl(GroupTbl groupTbl);
	
	public void deleteGroupTbl(GroupTbl groupTbl);
	
	public boolean delMenuAuth(String groupNm);
	
	public boolean regMenuAuth(MenuGroupTbl menuGroupTbl);
	
}
