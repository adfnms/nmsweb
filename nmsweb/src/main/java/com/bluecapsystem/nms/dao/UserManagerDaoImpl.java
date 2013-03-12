package com.bluecapsystem.nms.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bluecapsystem.frm.BaseDao;
import com.bluecapsystem.nms.dto.UserTbl;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository(value="userManagerDao")
public class UserManagerDaoImpl extends BaseDao implements UserManagerDao{

	
	

	@Override
	@Resource(name="nmsSqlMapClient")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
		
	}
	/**
	 * USER REGISTER TO DATABASE
	 */
	@Override
	public void regToDb(UserTbl userTbl) {

		try{
			
			super.getSqlMapClientTemplate().insert("com.bluecapsystem.nms.userManager.regToDb", userTbl);
			
			
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return ;
	}

	/**
	 * USER MODIFY TO DATABASE
	 */
	
	@Override
	public void modifyToDb(UserTbl userTbl) {
try{
			
			super.getSqlMapClientTemplate().update("com.bluecapsystem.nms.userManager.modifyToDb", userTbl);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return ;
		
	}
	
	/**
	 * USER DELETE TO DATABASE
	 */
	@Override
	public void deleteToDb(UserTbl userTbl) {
try{
			
			super.getSqlMapClientTemplate().update("com.bluecapsystem.nms.userManager.deleteToDb", userTbl);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return ;
		
	}
	
	@Override
	public UserTbl selectToDb(Map<String, Object> params) {
		System.out.println("------------3-UserManagerDaoImpl-selectToDb------------");
		return (UserTbl) getSqlMapClientTemplate().queryForObject("com.bluecapsystem.nms.userManager.selectToDb", params);
		
	}

}
