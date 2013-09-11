package com.bluecapsystem.nms.dao;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.bluecapsystem.frm.BaseDao;
import com.bluecapsystem.nms.dto.ServicesCategoriesTbl;
import com.ibatis.sqlmap.client.SqlMapClient;

@SuppressWarnings("unchecked")
@Repository(value="ServiceDao")
public class ServiceDaoImpl extends BaseDao implements ServiceDao
{
	@Override
	@Resource(name="nmsSqlMapClient")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient) 
	{
		super.setSqlMapClient(sqlMapClient);
	}

	@Override
	public boolean getServicesName(List<ServicesCategoriesTbl> ServicesItem) {
		
		boolean ret = false;
		
		String sqlMapId = "com.bluecapsystem.nms.service.getServicesName";
		
		List<ServicesCategoriesTbl> Services = null;
		
		try
		{
			Services = (List<ServicesCategoriesTbl>) super.getSqlMapClientTemplate().queryForList(sqlMapId);
			ret = true;
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
			ret = false;
		}finally
		{
			if(Services != null)
			{
				ServicesItem.addAll(Services);
			}
		}
		return ret;
	}

}

