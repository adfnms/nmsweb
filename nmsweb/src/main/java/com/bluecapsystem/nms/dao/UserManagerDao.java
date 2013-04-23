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
	
	/**
	 * USER DELETE GROUP TO DATABASE
	 * @param 
	 */
	public void deleteUserTbl(UserTbl userTbl);
	
	/**
	 * USER REG GROUP TO DATABASE
	 * @param 
	 */
	public void regUserTbl(UserTbl userTbl);
	
	
}
