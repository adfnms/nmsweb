package com.bluecapsystem.nms.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.co.adflow.nms.web.vo.requisition.RequisitionsNodes;

import org.springframework.stereotype.Repository;

import com.bluecapsystem.frm.BaseDao;
import com.bluecapsystem.nms.dto.NodeTbl;
import com.ibatis.sqlmap.client.SqlMapClient;

@SuppressWarnings("unchecked")
@Repository(value="NodeDao")
public class NodeDaoImpl extends BaseDao implements NodeDao{
	
	@Override
	@Resource(name="nmsSqlMapClient")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient) 
	{
		super.setSqlMapClient(sqlMapClient);
	}
	
	
	@Override
	public List<NodeTbl> searchNodList(Map<String,Object> paramter )
	{
		
		String sqlMapId = "com.bluecapsystem.nms.system.searchNodeList";
		
		List<NodeTbl> Services = null;
		
		try
		{
			Services = (List<NodeTbl>) getSqlMapClientTemplate().queryForList(sqlMapId,paramter);
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}finally
		{
		}
		
		return Services;
	}
	
	@Override
	public boolean nodeIdUpdate(Map<String,Object> paramter)
	{
		boolean isSucess = true;
		String sqlMapId = "com.bluecapsystem.nms.system.nodeIdUpdate";
		
		try
		{
			getSqlMapClientTemplate().update(sqlMapId,paramter);
			
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
			isSucess = false;
		}finally
		{
		}
		return isSucess;
	}
	
	@Override
	public boolean interfaceNodeIdUpdate(Map<String,Object> paramter)
	{
		boolean isSucess = true;
		String sqlMapId = "com.bluecapsystem.nms.system.interfaceNodeIdUpdate";
		
		try
		{
			getSqlMapClientTemplate().update(sqlMapId,paramter);
			
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
			isSucess = false;
		}finally
		{
		}
		return isSucess;
	}
	
	@Override
	public boolean serviceNodeIdUpdate(Map<String,Object> paramter)
	{
		boolean isSucess = true;
		String sqlMapId = "com.bluecapsystem.nms.system.serviceNodeIdUpdate";
		
		try
		{
			getSqlMapClientTemplate().update(sqlMapId,paramter);
			
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
			isSucess = false;
		}finally
		{
		}
		return isSucess;
	}
	
	@Override
	public boolean nodeRequisitionsUpdate(RequisitionsNodes renodes)
	{
		boolean isSucess = true;
		String sqlMapId = "com.bluecapsystem.nms.system.nodeRequisitionsUpdate";
		
		try
		{
			getSqlMapClientTemplate().update(sqlMapId,renodes);
			
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
			isSucess = false;
		}finally
		{
		}
		return isSucess;
	}
}
