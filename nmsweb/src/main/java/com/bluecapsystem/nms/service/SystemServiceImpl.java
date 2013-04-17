package com.bluecapsystem.nms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluecapsystem.frm.BaseService;
import com.bluecapsystem.nms.dao.SystemDao;
import com.bluecapsystem.nms.dto.MenuTbl;


@Service(value="SystemService")
public class SystemServiceImpl extends BaseService implements SystemService
{
	
	@Autowired
	private SystemDao dao;
	
	
	public boolean getMenuList(String sysId, List<MenuTbl> refMenuList)  
	{	
		boolean ret = false;
		
		
		ret = dao.selectMenuList(sysId, refMenuList);
		
		System.out.println("--------------refMenuList----SystemService----------"+refMenuList);
		if(ret == false)
		{
			logger.error("fail of get menu list");
		}
		
		return ret;
	}


	

}
