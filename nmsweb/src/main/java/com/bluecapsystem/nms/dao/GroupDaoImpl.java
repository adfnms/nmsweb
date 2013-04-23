package com.bluecapsystem.nms.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
		
		//logger.debug(String.format("sqlMapId : %s, sysId: %s", sqlMapId, sysId));
		
		System.out.println("--------------refMenuList----SystemDaoImpl----------"+refMenuList);
		
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

	
	/*@Override
	public List<AuthTbl> getAuth(Map<String, Object> params) {
		return (List<AuthTbl>) getSqlMapClientTemplate().queryForList("com.bluecapsystem.nms.system.getAuth", params);
	}*/




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
	public boolean delMenuAuth(String groupName, MenuGroupTbl menuGroupTbl) {
		
		try{
			
			super.getSqlMapClientTemplate().delete("com.bluecapsystem.nms.system.delMenuAuth", menuGroupTbl);
			
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

