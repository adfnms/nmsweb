package com.bluecapsystem.nms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.adflow.nms.web.vo.requisition.RequisitionsNodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.bluecapsystem.frm.BaseService;
import com.bluecapsystem.nms.dao.NodeDao;
import com.bluecapsystem.nms.dto.CategoryNodeTbl;
import com.bluecapsystem.nms.dto.NodeTbl;
import com.bluecapsystem.nms.service.SurveillanceServiceImpl.regNodePopTransactionCallback;

@Service(value="NodeService")
public class NodeServiceImpl extends BaseService implements NodeService{
	
	@Autowired
	private NodeDao dao;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	@Override
	public List<NodeTbl> searchNodList(Map<String,Object> paramter)
	{
		return dao.searchNodList(paramter);
	}
	
	@Override
	public boolean nodeRequisitionsUpdate(RequisitionsNodes renodes)
	{
		return dao.nodeRequisitionsUpdate(renodes);
	}
	
	@Override
	public boolean nodeIdUpdate(Integer nodeid,Integer oldNodeid)
	{
		logger.debug("start service");
		boolean ret = false;
		
		nodeIdUpdateTransactionCallback transWorker = new nodeIdUpdateTransactionCallback(nodeid,oldNodeid); 
		ret = this.transactionTemplate.execute(transWorker);
		
		logger.debug(String.format("end service result : %b", ret));
		
		return ret;
	}

	class nodeIdUpdateTransactionCallback implements TransactionCallback <Boolean>
	{
		Integer nodeId = null;
		Integer OldNodeId = null;

		public nodeIdUpdateTransactionCallback(Integer nodeid,Integer OldNodeId)
		{
			this.nodeId = nodeid;
			this.OldNodeId = OldNodeId;
		}

		@Override
		public Boolean doInTransaction(TransactionStatus status) {
			boolean retTrans = false;
			
			_TRANSACTION : {
				try
				{
					if( this.nodeId==null)
						break _TRANSACTION;
					
					Map<String,Object> paramter = new HashMap<String,Object>();
					paramter.put("oldNodeid", OldNodeId);
					paramter.put("nodeid", this.nodeId);
					
					if(dao.nodeIdUpdate(paramter) == false)
						break _TRANSACTION;
					
					if(dao.interfaceNodeIdUpdate(paramter) == false)
						break _TRANSACTION;
					
					if(dao.serviceNodeIdUpdate(paramter) == false)
						break _TRANSACTION;
					
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

}
