package com.bluecapsystem.nms.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bluecapsystem.frm.BaseDao;
import com.bluecapsystem.nms.dto.AssetsTbl;
import com.bluecapsystem.nms.dto.CategoriesTbl;
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
	public boolean getCategoriesName(List<CategoriesTbl> CategoriesItem) {
		
		boolean ret = false;
		
		String sqlMapId = "com.bluecapsystem.nms.surveillance.getCategoriesName";
		
		List<CategoriesTbl> Categories 	= null;
		
		try
		{
			Categories = (List<CategoriesTbl>) super.getSqlMapClientTemplate().queryForList(sqlMapId);
			ret = true;
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
			ret = false;
		}finally
		{
			if(Categories != null)
			{
				CategoriesItem.addAll(Categories);
			}
		}
		return ret;
	}

	@Override
	public Integer getCount(Integer categoryId) 
	{
		Integer count = null;
		
		String sqlMapId = "com.bluecapsystem.nms.surveillance.getCount";
		
		Map<String, Object>	params = new HashMap<String,Object>();
		
		
		params.put("categoryid", categoryId);
		
		try
		{
			count = (Integer) super.getSqlMapClientTemplate().queryForObject(sqlMapId, params);
			
			logger.debug(String.format("getCount result ==> %d", count));
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
			count = null;
		}
		return count;
	}

	@Override
	public boolean getRegNodeList(Integer categoryId, List<CategoriesTbl> RegNodeItems) {
boolean ret = false;
		
		String sqlMapId = "com.bluecapsystem.nms.surveillance.getRegNodeList";
		
		List<CategoriesTbl> RegNodeList 	= null;
		
		Map<String, Object>	params = new HashMap<String,Object>();
		
		params.put("categoryid", categoryId);
		
		try
		{
			RegNodeList = (List<CategoriesTbl>) super.getSqlMapClientTemplate().queryForList(sqlMapId,params);
			ret = true;
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
			ret = false;
		}finally
		{
			if(RegNodeList != null)
			{
				RegNodeItems.addAll(RegNodeList);
			}
		}
		return ret;
	}
	
}

