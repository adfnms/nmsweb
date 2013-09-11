package com.bluecapsystem.nms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bluecapsystem.frm.BaseService;
import com.bluecapsystem.nms.dao.ServiceDao;
import com.bluecapsystem.nms.dto.ServicesCategoriesTbl;

@Service(value = "ServicesService")
public class ServicesServiceImpl  extends BaseService implements ServicesService{
	
	@Autowired
	private ServiceDao serviceDao;	
	
	@Override
	public boolean getServicesName(List<ServicesCategoriesTbl> ServicesItem){
		boolean ret = false;
		ret = serviceDao.getServicesName(ServicesItem);
		if(ret == false){
			logger.error("fail of get Service Name");
		}
		return ret;
	}

}

	
	


