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
	public boolean regGroupMenu(String menuId, String groupName, MenuGroupTbl menuGroupTbl) {
		
		logger.debug("start service");
		boolean ret = false;

		MenuAuthTransactionCallback transWorker = new MenuAuthTransactionCallback(menuId, groupName, menuGroupTbl); 
		 transWorker.menuGroupTbl = menuGroupTbl;
		 transWorker.menuId = menuId;
		 transWorker.groupName = groupName;
		 ret = this.transactionTemplate.execute(transWorker);
		
		
		logger.debug(String.format("end service result : %b", ret));
		
		return ret;
	}


	class MenuAuthTransactionCallback implements TransactionCallback <Boolean>
	{
		MenuGroupTbl menuGroupTbl = null;
		String menuId = null;
		String groupName = null;

		public MenuAuthTransactionCallback(String menuId, String groupName, MenuGroupTbl menuGroupTbl)
		{
			this.menuGroupTbl = menuGroupTbl;
			this.menuId = menuId;
			this.groupName = groupName;
		}

		@Override
		public Boolean doInTransaction(TransactionStatus status) {
			boolean retTrans = false;
			
			_TRANSACTION : {
				try
				{
					
					
					String[] menuIdsp = null;
					
					String [] groupNmsp = null;
					
					if(menuId == null || menuId.equals("")){
						menuIdsp=new String[0];
					}else{
						
						menuIdsp=menuId.split(",");
					}
					if(groupName == null || groupName.equals("")){
						groupNmsp=new String[0];
					}else{
						groupNmsp=groupName.split(",");
					}
					
					
					for(int i=0;i<groupNmsp.length;i++){
						groupName=groupNmsp[i];
						menuGroupTbl.setGroupNm(groupName);
						
					}
					
					if(groupDao.delMenuAuth(groupName, menuGroupTbl) == false)
						
						break _TRANSACTION;
					
					for(int i=0;i<menuIdsp.length;i++){
						menuId=menuIdsp[i];
						groupName=groupNmsp[i];
						menuGroupTbl.setMenuId(menuId);
						menuGroupTbl.setGroupNm(groupName);
						
						if(groupDao.regMenuAuth(menuGroupTbl) == false)
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
