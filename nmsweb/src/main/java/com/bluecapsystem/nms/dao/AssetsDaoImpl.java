package com.bluecapsystem.nms.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bluecapsystem.frm.BaseDao;
import com.bluecapsystem.nms.dto.AssetsTbl;
import com.bluecapsystem.nms.dto.MenuGroupTbl;
import com.ibatis.sqlmap.client.SqlMapClient;


@SuppressWarnings("unchecked")
@Repository(value="AssetsDao")
public class AssetsDaoImpl extends BaseDao implements AssetsDao
{
	@Override
	@Resource(name="nmsSqlMapClient")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient) 
	{
		super.setSqlMapClient(sqlMapClient);
	}


	@Override
	public boolean getSearchAssets(String category, List<AssetsTbl> CatagoryList) {
		
		boolean ret = false;
		String sqlMapId = "com.bluecapsystem.nms.assets.getSearchAssets";
		
		Map<String, Object>	params 		= new HashMap<String,Object>();
		List<AssetsTbl> 		CatagoryLists 	= null;
		
		params.put("category", category);
		
		try
		{
			CatagoryLists = (List<AssetsTbl>) super.getSqlMapClientTemplate().queryForList(sqlMapId, params);
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
	}


	@Override
	public boolean getAssetInfo(Integer nodeId, List<AssetsTbl> AssetInfo) {
		boolean ret = false;
		
		
		String sqlMapId = "com.bluecapsystem.nms.assets.getAssetsInfo";
		
		Map<String, Object>	params 		= new HashMap<String,Object>();
		List<AssetsTbl> 		CatagoryLists 	= null;
		System.out.println("---------AssetsDao------"+nodeId);
		String nodeid= Integer.toString(nodeId); 
		params.put("nodeid", nodeid);
		System.out.println("---------AssetsDao-params-----"+nodeid);
		try
		{
			CatagoryLists = (List<AssetsTbl>) super.getSqlMapClientTemplate().queryForList(sqlMapId, params);
			ret = true;
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
			ret = false;
		}finally
		{
			if(CatagoryLists != null)
			{
				AssetInfo.addAll(CatagoryLists);
			}
		}
		return ret;
	}


	/*@Override
	public boolean getAllAssetList(List<AssetsTbl> CatagoryList) {
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

