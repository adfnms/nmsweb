package com.bluecapsystem.nms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.bluecapsystem.frm.BaseService;
import com.bluecapsystem.nms.dao.GroupDao;
import com.bluecapsystem.nms.dto.GroupTbl;
import com.bluecapsystem.nms.dto.MenuGroupTbl;
import com.bluecapsystem.nms.dto.MenuTbl;


@Service(value="GroupService")
public class GroupServiceImpl extends BaseService implements GroupService
{
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	@Autowired
	private GroupDao groupDao;
	
	
	public boolean getMenuList(String sysId, List<MenuTbl> refMenuList)  
	{	
		boolean ret = false;
		
		
		ret = groupDao.selectMenuList(sysId, refMenuList);
		
		//System.out.println("--------------refMenuList----SystemService----------"+refMenuList);
		if(ret == false)
		{
			logger.error("fail of get menu list");
		}
		
		return ret;
	}

	@Override
	public boolean regGroupTbl(GroupTbl groupTbl) {
		boolean ret = false;
		try
		{
			groupDao.regGroupTbl(groupTbl);
			
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
	public boolean deleteGroupTbl(GroupTbl groupTbl) {
		
		boolean ret = false;
		try
		{
			groupDao.deleteGroupTbl(groupTbl);
			
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
	public List<MenuGroupTbl> getMenuId(String groupNm) throws Exception
	{
		
		logger.error("Start service");
		return groupDao.getMenuId(groupNm);
	}

	

	@Override
	public boolean regGroupMenu(String groupName, MenuGroupTbl[] groupMenues)
	{
		boolean retValue = false;
		
		logger.debug("Start regist menu group");
		
		MenuAuthTransactionCallback transWorker = new MenuAuthTransactionCallback(groupName, groupMenues); 
		retValue = this.transactionTemplate.execute(transWorker);
		
		logger.debug(String.format("end service result : %b", retValue));
		return retValue;
	}


	class MenuAuthTransactionCallback implements TransactionCallback <Boolean>
	{
		private MenuGroupTbl[] groupMenues = null;
		private String groupName = null;
		
		public MenuAuthTransactionCallback(String groupName, MenuGroupTbl[] groupMenues)
		{
			this.groupName = groupName;
			this.groupMenues = groupMenues;
		}

		@Override
		public Boolean doInTransaction(TransactionStatus status) 
		{
			boolean retTrans = false;
			
			_TRANSACTION : 
			{
				try
				{
					if(groupDao.delMenuAuth(groupName) == false)
					{
						break _TRANSACTION;
					}
					
					for(int i = 0; i < groupMenues.length; i++)
					{
						if(groupDao.regMenuAuth(groupMenues[i]) == false)
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


	

}
