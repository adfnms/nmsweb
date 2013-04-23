package com.bluecapsystem.nms.service;

import com.bluecapsystem.nms.dto.UserTbl;

public interface UserManagerService {
	
	/**
	 * USER REGISTER TO DATABASE
	 * @param userTbl
	 * @return
	 */
	public boolean regToDb(UserTbl userTbl);

	/**
	 * USER MODIFY TO DATABASE
	 * @param userTbl
	 * @return
	 */
	public boolean modifyToDb(UserTbl userTbl);
	
	/**
	 * USER DELETE TO DATABASE
	 * @param userTbl
	 * @return
	 */
	public boolean deleteToDb(UserTbl userTbl);
	
	/**
	 * USER DELETE GROUP TO DATABASE
	 * @param userTbl
	 * @return
	 */
	public boolean deleteUserTbl(UserTbl userTbl);
	
	/**
	 * USER REG GROUP TO DATABASE
	 * @param userTbl
	 * @return
	 */
	public boolean regUserTbl(UserTbl userTbl);
	
	
}
