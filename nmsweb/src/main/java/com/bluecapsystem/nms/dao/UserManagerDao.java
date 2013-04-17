package com.bluecapsystem.nms.dao;

import java.util.Map;

import com.bluecapsystem.nms.dto.UserTbl;

public interface UserManagerDao {

	
	/**
	 * USER REGISTER TO DATABASE
	 * @param 
	 */
	public void regToDb(UserTbl userTbl);
	
	/**
	 * USER MODIFY TO DATABASE
	 * @param 
	 */
	public void modifyToDb(UserTbl userTbl);
	
	/**
	 * USER DELETE TO DATABASE
	 * @param 
	 */
	public void deleteToDb(UserTbl userTbl);
	
	
}
