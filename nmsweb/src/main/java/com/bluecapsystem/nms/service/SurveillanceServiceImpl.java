package com.bluecapsystem.nms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.bluecapsystem.frm.BaseService;
import com.bluecapsystem.nms.dao.AssetsDao;
import com.bluecapsystem.nms.dao.SurveillanceDao;
import com.bluecapsystem.nms.dto.AssetsTbl;
import com.bluecapsystem.nms.dto.CategoriesTbl;
import com.bluecapsystem.nms.dto.CategoryNodeTbl;
import com.bluecapsystem.nms.dto.MenuGroupTbl;
import com.bluecapsystem.nms.service.GroupServiceImpl.MenuAuthTransactionCallback;


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
	public boolean regNodePop(Integer[] categoryid, Integer[] nodeid, CategoryNodeTbl categoryNodeTbl ) {
		logger.debug("start service");
		boolean ret = false;
		
		regNodePopTransactionCallback transWorker = new regNodePopTransactionCallback(categoryid, nodeid, categoryNodeTbl); 
		transWorker.categoryNodeTbl = categoryNodeTbl;
		 transWorker.categoryId = categoryid;
		 transWorker.nodeId = nodeid;
		 ret = this.transactionTemplate.execute(transWorker);
		
		logger.debug(String.format("end service result : %b", ret));
		
		return ret;
	}
	
	class regNodePopTransactionCallback implements TransactionCallback <Boolean>
	{
		CategoryNodeTbl categoryNodeTbl = null;
		Integer[] categoryId = null;
		Integer[] nodeId = null;

		public regNodePopTransactionCallback(Integer[] categoryid, Integer[] nodeid, CategoryNodeTbl categoryNodeTbl)
		{
			this.categoryNodeTbl = categoryNodeTbl;
		/*	this.categoryId = categoryId;
			this.nodeId = nodeId;*/
		}

		@Override
		public Boolean doInTransaction(TransactionStatus status) {
			boolean retTrans = false;
			
			_TRANSACTION : {
				try
				{
					Integer [] categoryIds = null;
					Integer [] nodeIds = null;
					
					for(int i=0; i< categoryId.length; i++){
						
						if(categoryId == null || categoryId.equals("")){
						
							categoryIds=new Integer[0];
						
						}else{
							int categoryid = 	categoryId[i];
							categoryNodeTbl.setCategoryid(categoryid);
						}
						int categoryid = 	categoryId[i];
						if(surveillanceDao.delNodePop(categoryid, categoryNodeTbl) == false)
							
							break _TRANSACTION;
					}
					for(int i=0; i< nodeId.length; i++){
						
						int nodeid = 	nodeId[i];
						int categoryid = 	categoryId[i];
						categoryNodeTbl.setCategoryid(categoryid);
						categoryNodeTbl.setNodeid(nodeid);
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
	public boolean delNodePop(Integer categoryid,CategoryNodeTbl categoryNodeTbl) {
		boolean ret = false;
		try
		{
			surveillanceDao.delNodePop(categoryid,categoryNodeTbl);
			
			
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
	public boolean delCategory(Integer categoryid, CategoriesTbl categoriesTbl) {
		
		boolean ret = false;
		
		categoriesTbl.setCategoryid(categoryid);
		try
		{
			surveillanceDao.delCategory(categoryid,categoriesTbl);
			
			
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
