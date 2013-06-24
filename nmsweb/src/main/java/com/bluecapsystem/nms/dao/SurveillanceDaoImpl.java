package com.bluecapsystem.nms.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bluecapsystem.frm.BaseDao;
import com.bluecapsystem.nms.dto.CategoryNodeTbl;
import com.ibatis.sqlmap.client.SqlMapClient;


@SuppressWarnings("unchecked")
@Repository(value="SurveillanceDao")
public class SurveillanceDaoImpl extends BaseDao implements SurveillanceDao
{
	@Override
	@Resource(name="nmsSqlMapClient")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient) 
	{
		super.setSqlMapClient(sqlMapClient);
	}

	
	@Override
	public boolean getNodeId(List<CategoryNodeTbl> nodeId) {
		
		boolean ret = false;
		
		String sqlMapId = "com.bluecapsystem.nms.surveillance.getNodeId";
		
		List<CategoryNodeTbl> nodeIds 	= null;
		
		try
		{
			nodeIds = (List<CategoryNodeTbl>) super.getSqlMapClientTemplate().queryForList(sqlMapId);
			ret = true;
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
			ret = false;
		}finally
		{
			if(nodeIds != null)
			{
				nodeId.addAll(nodeIds);
			}
		}
		return ret;
	}

	
	
	
	
	
	
	@Override
	public boolean getRouters(List<CategoryNodeTbl> routersInfo) {
		return false;
	}

	@Override
	public boolean getSwitches(List<CategoryNodeTbl> switchesInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getServers(List<CategoryNodeTbl> serversInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	




	


	/*@Override
	public boolean fieldSearch(List<AssetsTbl> CatagoryList) {
		boolean ret = false;
		String sqlMapId = "com.bluecapsystem.nms.assets.getAllAssetList";
		
		List<AssetsTbl> CatagoryLists 	= null;
		
		try
		{
			CatagoryLists = (List<AssetsTbl>) super.getSqlMapClientTemplate().queryForList(sqlMapId);
			ret = true;
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
			ret = false;
		}finally
		{
			if(CatagoryLists != null)
			{
				CatagoryList.addAll(CatagoryLists);
			}
		}
		return ret;
	}*/



	

	
	
}

