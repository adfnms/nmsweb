package com.bluecapsystem.nms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluecapsystem.frm.BaseService;
import com.bluecapsystem.nms.dao.UserManagerDao;
import com.bluecapsystem.nms.dto.UserTbl;


@Service(value = "UserManagerService")
public class UserManagerServiceImpl  extends BaseService implements UserManagerService{

	
	@Autowired
	private UserManagerDao userManagerDao;	
	
	/**
	 *  USER REGISTER TO DATABASE
	 */
	@Override
	public boolean regToDb(UserTbl userTbl) {
		boolean ret = false;
		try
		{
			userManagerDao.regToDb(userTbl);
			
			ret = true;
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			ret = false;
		}finally
		{
			
		}
		return ret;
	}

	/**
	 *  USER MODIFY TO DATABASE
	 */
	@Override
	public boolean modifyToDb(UserTbl userTbl) {
		boolean ret = false;
		try
		{
			userManagerDao.modifyToDb(userTbl);
			
			ret = true;
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			ret = false;
		}finally
		{
			
		}
		return ret;
	}

	/**
	 *  USER DELETE TO DATABASE
	 */
	@Override
	public boolean deleteToDb(UserTbl userTbl) {
		boolean ret = false;
		try
		{
			userManagerDao.deleteToDb(userTbl);
			
			ret = true;
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			ret = false;
		}finally
		{
			
		}
		return ret;
	}

	
	

}
