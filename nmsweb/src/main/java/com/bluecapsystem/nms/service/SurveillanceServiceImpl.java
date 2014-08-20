package com.bluecapsystem.nms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.bluecapsystem.frm.BaseService;
import com.bluecapsystem.nms.dao.SurveillanceDao;
import com.bluecapsystem.nms.dto.CategoriesTbl;
import com.bluecapsystem.nms.dto.CategoryNodeTbl;


@Service(value="SurveillanceService")
public class SurveillanceServiceImpl extends BaseService implements SurveillanceService
{
	
	@Autowired
	private SurveillanceDao surveillanceDao;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	@Override
	public boolean getCategoriesName(List<CategoriesTbl> CategoriesItem) {
		
		boolean ret = false;
		
		ret = surveillanceDao.getCategoriesName(CategoriesItem);
		
		if(ret == false)
		{
			logger.error("fail of get CategoriesName");
		}
		
		return ret;
	}



	@Override
	public Integer getCount(Integer categoryId) 
	{
		
		return surveillanceDao.getCount(categoryId);
		
	}

	@Override
	public boolean getRegNodeList(Integer categoryId, List<CategoriesTbl> RegNodeItems) {

		boolean ret = false;
		
		ret = surveillanceDao.getRegNodeList(categoryId, RegNodeItems);
		
		if(ret == false)
		{
			logger.error("fail of get RegNodeList");
		}
		
		return ret;
	
	}



	@Override
	public boolean regNodePop(Integer categoryid, Integer[] nodeid) {
		logger.debug("start service");
		boolean ret = false;
		
		regNodePopTransactionCallback transWorker = new regNodePopTransactionCallback(categoryid, nodeid); 
		ret = this.transactionTemplate.execute(transWorker);
		
		logger.debug(String.format("end service result : %b", ret));
		
		return ret;
	}
	
	class regNodePopTransactionCallback implements TransactionCallback <Boolean>
	{
		CategoryNodeTbl categoryNodeTbl = null;
		Integer categoryId = null;
		Integer[] nodeId = null;

		public regNodePopTransactionCallback(Integer categoryid, Integer[] nodeid)
		{
			this.categoryNodeTbl = new CategoryNodeTbl();
			this.categoryId = categoryid;
			this.nodeId = nodeid;
		}

		@Override
		public Boolean doInTransaction(TransactionStatus status) {
			boolean retTrans = false;
			
			_TRANSACTION : {
				try
				{
					if(categoryId == null || categoryId == -1)
						break _TRANSACTION;
					
					if( this.nodeId==null || this.nodeId.length == 0)
						break _TRANSACTION;
					
					this.categoryNodeTbl.setCategoryid(categoryId);
					
					if(surveillanceDao.delNodePop(this.categoryNodeTbl) == false)
						break _TRANSACTION;
					
					for(int i=0; i< nodeId.length; i++){
						this.categoryNodeTbl.setNodeid(nodeId[i]);
						if(surveillanceDao.regNodePop(categoryNodeTbl) == false)
							break _TRANSACTION;
					}
					
					retTrans = true;
				}catch(DataAccessException ex)
				{
					ex.printStackTrace();
					logger.error(ex.getMessage());
				}
			}
			
			if(retTrans == false)
			{
				status.setRollbackOnly();
			}else
			{
				status.flush();
			}
			
			return retTrans;
		}
	}

	@Override
	public boolean regSurveillenceName(CategoriesTbl categoriesTbl) {
		boolean ret = false;
		try
		{
			surveillanceDao.regSurveillenceName(categoriesTbl);
			
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
	public boolean delNodePop(Integer categoryid, Integer[] nodeid)
	{
		logger.debug("start service");
		boolean ret = false;
		
		delNodePopTransactionCallback transWorker = new delNodePopTransactionCallback(categoryid, nodeid); 
		ret = this.transactionTemplate.execute(transWorker);
		
		logger.debug(String.format("end service result : %b", ret));
		
		return ret;
	}	
	
	class delNodePopTransactionCallback implements TransactionCallback <Boolean>
	{
		CategoryNodeTbl categoryNodeTbl = null;
		Integer categoryId = null;
		Integer[] nodeId = null;

		public delNodePopTransactionCallback(Integer categoryid, Integer[] nodeid)
		{
			this.categoryNodeTbl = new CategoryNodeTbl();
			this.categoryId = categoryid;
			this.nodeId = nodeid;
		}

		@Override
		public Boolean doInTransaction(TransactionStatus status) {
			boolean retTrans = false;
			
			_TRANSACTION : {
				try
				{
					
					if(categoryId == null || categoryId == -1)
						break _TRANSACTION;
					categoryNodeTbl.setCategoryid(this.categoryId);
					for(int i=0; i< this.nodeId.length; i++){
						
						categoryNodeTbl.setNodeid(this.nodeId[i]);
						
						if(surveillanceDao.delNodePop(categoryNodeTbl) == false)
							break _TRANSACTION;
					}
					
					retTrans = true;
				}catch(DataAccessException ex)
				{
					ex.printStackTrace();
					logger.error(ex.getMessage());
				}
			}
			
			if(retTrans == false)
			{
				status.setRollbackOnly();
			}else
			{
				status.flush();
			}
			
			return retTrans;
		}
	}
	
	@Override
	public boolean delNodePop(Integer categoryid,CategoryNodeTbl categoryNodeTbl) {
		boolean ret = false;
		try
		{
			surveillanceDao.delNodePop(categoryNodeTbl);
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
	public boolean delCategory(CategoriesTbl categoriesTbl) {
		
		boolean ret = false;
		
		try
		{
			surveillanceDao.delCategory(categoriesTbl);
			
			
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
