package com.bluecapsystem.nms.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bluecapsystem.frm.BaseDao;
import com.bluecapsystem.nms.dto.UserTbl;
import com.ibatis.sqlmap.client.SqlMapClient;


@SuppressWarnings("unchecked")
@Repository(value="MenuManagerDao")
public class MenuManagerDaoImpl extends BaseDao implements MenuManagerDao
{
	@Override
	@Resource(name="nmsSqlMapClient")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient) 
	{
		super.setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<UserTbl> showMenu(Map<String, Object> params) {
		return getSqlMapClientTemplate().queryForList("com.bluecapsystem.nms.system.showMenu", params);
	}
}

