package com.bluecapsystem.nms.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.bluecapsystem.frm.BaseDao;
import com.bluecapsystem.nms.dto.GroupTbl;
import com.bluecapsystem.nms.dto.MenuGroupTbl;
import com.bluecapsystem.nms.dto.MenuTbl;
import com.ibatis.sqlmap.client.SqlMapClient;


@SuppressWarnings("unchecked")
@Repository(value="GroupDao")
public class GroupDaoImpl extends BaseDao implements GroupDao
{
	@Override
	@Resource(name="nmsSqlMapClient")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient) 
	{
		super.setSqlMapClient(sqlMapClient);
	}


	@Override
	public boolean selectMenuList(String sysId, List<MenuTbl> refMenuList) {
		boolean ret = false;
		String sqlMapId = "com.bluecapsystem.nms.system.selectMenu";
		
		Map<String, Object>	params 		= new HashMap<String,Object>();
		List<MenuTbl> 		menuList 	= null;
		
		params.put("sysId", sysId);
		
		try
		{
			menuList = (List<MenuTbl>) super.getSqlMapClientTemplate().queryForList(sqlMapId, params);
			ret = true;
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
			ret = false;
		}finally
		{
			if(menuList != null)
			{
				refMenuList.addAll(menuList);
			}
			//logger.info(String.format("sqlMapId : %s, sysId: %s, ret : %b", sqlMapId, sysId, ret));
		}
		return ret;
	}
	
	
	
	@Override
	public List<MenuGroupTbl> getMenuId(String groupNm) throws DataAccessException
	{
		String sqlMapId = "com.bluecapsystem.nms.system.getMenuGroupId";
		Map<String, Object>	params 		= new HashMap<String,Object>();
		params.put("groupNm", groupNm);
		
		List<MenuGroupTbl> retGroupMenues = (List<MenuGroupTbl>) super.getSqlMapClientTemplate().queryForList(sqlMapId, params);
		return retGroupMenues;
	}

	@Override
	public void regGroupTbl(GroupTbl groupTbl) {
		try{
			
			super.getSqlMapClientTemplate().insert("com.bluecapsystem.nms.system.regGroupTbl", groupTbl);
			
			
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return ;
		
		
	}


	@Override
	public void deleteGroupTbl(GroupTbl groupTbl) {
		try{

			super.getSqlMapClientTemplate().update("com.bluecapsystem.nms.system.deleteGroupTbl", groupTbl);
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return ;
		
	}


	@Override
	public boolean delMenuAuth(String groupNm) 
	{
		
		try{
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("groupNm", groupNm);
			
			super.getSqlMapClientTemplate().delete("com.bluecapsystem.nms.system.delMenuAuth", params);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public boolean regMenuAuth(MenuGroupTbl menuGroupTbl) {
		
		try{
			
			super.getSqlMapClientTemplate().insert("com.bluecapsystem.nms.system.regMenuAuth", menuGroupTbl);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
		return true;
	}


	

	
	
}

