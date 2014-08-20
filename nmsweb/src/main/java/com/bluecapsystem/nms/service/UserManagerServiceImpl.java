package com.bluecapsystem.nms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluecapsystem.frm.BaseService;
import com.bluecapsystem.nms.dao.UserManagerDao;
import com.bluecapsystem.nms.dto.UserTbl;
import com.bluecapsystem.nms.util.OutParam;


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

	@Override
	public boolean deleteUserTbl(UserTbl userTbl) {
		boolean ret = false;
		try
		{
			userManagerDao.deleteUserTbl(userTbl);
			
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

	@Override
	public boolean regUserTbl(UserTbl userTbl) {
		boolean ret = false;
		try
		{
			userManagerDao.regUserTbl(userTbl);
			
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
	
	@Override
	public UserTbl userInfo(Map<String, Object> params)
	{
		UserTbl ret = null;
		try
		{
			ret = userManagerDao.userInfo(params);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			
		}
		
		return ret;
	}
}

	
	


