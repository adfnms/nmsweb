package com.bluecapsystem.nms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluecapsystem.frm.BaseService;
import com.bluecapsystem.nms.dao.MenuManagerDao;
import com.bluecapsystem.nms.dto.UserTbl;
import com.bluecapsystem.nms.util.OutParam;


@Service(value="MenuManagerService")
public class MenuManagerServiceImpl extends BaseService implements MenuManagerService
{
	
	
	@Autowired
	private MenuManagerDao menuManagerDao;
	
	//get Menu id
	@Override
	public boolean showMenu(String userId, OutParam<List<UserTbl>> userTbl) {
		boolean ret = false;
		userTbl.set(null);
		
		List<UserTbl> UserTbl = new ArrayList<UserTbl>();
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("userId",userId);
		
		try
		{
			UserTbl = menuManagerDao.showMenu(params);
			userTbl.set(UserTbl);
			ret = true;
		}catch(Exception ex)
			{
				ex.printStackTrace();
				ret = false;
			}finally{
				}
		return ret;
	}
}
