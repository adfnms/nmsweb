package com.bluecapsystem.nms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluecapsystem.frm.BaseService;
import com.bluecapsystem.nms.dao.AssetsDao;
import com.bluecapsystem.nms.dao.SurveillanceDao;
import com.bluecapsystem.nms.dto.AssetsTbl;
import com.bluecapsystem.nms.dto.CategoryNodeTbl;


@Service(value="SurveillanceService")
public class SurveillanceServiceImpl extends BaseService implements SurveillanceService
{
	
	@Autowired
	private SurveillanceDao surveillanceDao;
	
	
	@Override
	public boolean getNodeId(List<CategoryNodeTbl> nodeId) {

		boolean ret = false;
		
		ret = surveillanceDao.getNodeId(nodeId);
		
		if(ret == false)
		{
			logger.error("fail of get menu Id");
		}
		
		return ret;
	}
	
	

	@Override
	public boolean getRouters(List<CategoryNodeTbl> routersInfo) {
		
		boolean ret = false;
		
		ret = surveillanceDao.getRouters(routersInfo);
		
		if(ret == false)
		{
			logger.error("fail of get menu Id");
		}
		
		return ret;
	}

	@Override
	public boolean getSwitches(List<CategoryNodeTbl> switchesInfo) {
		
		boolean ret = false;
		
		ret = surveillanceDao.getSwitches(switchesInfo);
		
		if(ret == false)
		{
			logger.error("fail of get menu Id");
		}
		
		return ret;
	}

	@Override
	public boolean getServers(List<CategoryNodeTbl> serversInfo) {
		
		boolean ret = false;
		
		ret = surveillanceDao.getServers(serversInfo);
		
		if(ret == false)
		{
			logger.error("fail of get menu Id");
		}
		
		return ret;
	}

	

	/*@Override
	public boolean getAllAssetList(List<AssetsTbl> CatagoryList) {
		
		boolean ret = false;
		
		ret = assetsDao.getAllAssetList(CatagoryList);
		
		if(ret == false)
		{
			logger.error("fail of get menu Id");
		}
		
		return ret;
	}*/

}
