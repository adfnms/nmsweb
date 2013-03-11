package com.bluecapsystem.frm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;

public abstract class BaseDao  extends SqlMapClientDaoSupport 
{
	
	protected final Log logger = LogFactory.getLog(BaseDao.class);
	
	public abstract void setSuperSqlMapClient(SqlMapClient sqlMapClient);
}
