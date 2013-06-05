package com.bluecapsystem.nms.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bluecapsystem.frm.BaseDao;
import com.bluecapsystem.nms.dto.AssetsTbl;
import com.bluecapsystem.nms.dto.MenuGroupTbl;
import com.ibatis.sqlmap.client.SqlMapClient;


@SuppressWarnings("unchecked")
@Repository(value="AssetsDao")
public class AssetsDaoImpl extends BaseDao implements AssetsDao
{
	@Override
	@Resource(name="nmsSqlMapClient")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient) 
	{
		super.setSqlMapClient(sqlMapClient);
	}


	@Override
	public boolean getSearchAssets(String category, List<AssetsTbl> CatagoryList) {
		
		boolean ret = false;
		String sqlMapId = "com.bluecapsystem.nms.assets.getSearchAssets";
		
		Map<String, Object>	params 		= new HashMap<String,Object>();
		List<AssetsTbl> 		CatagoryLists 	= null;
		
		params.put("category", category);
		
		try
		{
			CatagoryLists = (List<AssetsTbl>) super.getSqlMapClientTemplate().queryForList(sqlMapId, params);
			ret = true;
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
			ret = false;
		}finally
		{
			if(CatagoryLists != null)
			{
				CatagoryList.addAll(CatagoryLists);
			}
		}
		return ret;
	}


	@Override
	public boolean getAssetInfo(Integer nodeId, List<AssetsTbl> AssetInfo) {
		boolean ret = false;
		
		
		String sqlMapId = "com.bluecapsystem.nms.assets.getSearchAssets";
		
		Map<String, Object>	params 		= new HashMap<String,Object>();
		List<AssetsTbl> 		CatagoryLists 	= null;
		String nodeid= Integer.toString(nodeId); 
		params.put("nodeid", nodeid);
		try
		{
			CatagoryLists = (List<AssetsTbl>) super.getSqlMapClientTemplate().queryForList(sqlMapId, params);
			ret = true;
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
			ret = false;
		}finally
		{
			if(CatagoryLists != null)
			{
				AssetInfo.addAll(CatagoryLists);
			}
		}
		return ret;
	}


	@Override
	public void modifyToAssets(AssetsTbl assetsTbl) {
		
		try{
			
			super.getSqlMapClientTemplate().update("com.bluecapsystem.nms.assets.modifyToAssets", assetsTbl);
			
			
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return ;
		
	}


	@Override
	public boolean fieldSearch(AssetsTbl assetsTbl, List<AssetsTbl> fieldInfo) {
		boolean ret = false;
		String sqlMapId = "com.bluecapsystem.nms.assets.fieldSearch";
		Map<String, Object>	params 		= new HashMap<String,Object>();
		
		if(assetsTbl.getCity() != null || assetsTbl.getCity()==""){params.put("assetsTbl", assetsTbl.getCity());}
		else if(assetsTbl.getHdd1() != null || assetsTbl.getHdd1()==""){params.put("assetsTbl", assetsTbl.getHdd1());}
		else if(assetsTbl.getHdd2() != null || assetsTbl.getHdd2()==""){params.put("assetsTbl", assetsTbl.getHdd2());}
		else if(assetsTbl.getHdd3() != null || assetsTbl.getHdd3()==""){params.put("assetsTbl", assetsTbl.getHdd3());}
		else if(assetsTbl.getHdd4() != null || assetsTbl.getHdd4()==""){params.put("assetsTbl", assetsTbl.getHdd4());}
		else if(assetsTbl.getHdd5() != null || assetsTbl.getHdd5()==""){params.put("assetsTbl", assetsTbl.getHdd5());}
		else if(assetsTbl.getHdd6() != null || assetsTbl.getHdd6()==""){params.put("assetsTbl", assetsTbl.getHdd6());}
		
		else if(assetsTbl.getAddress1() != null || assetsTbl.getAddress1()==""){params.put("assetsTbl", assetsTbl.getAddress1());}
		else if(assetsTbl.getAddress2() != null || assetsTbl.getAddress2()==""){params.put("assetsTbl", assetsTbl.getAddress2());}
		else if(assetsTbl.getAssetnumber() != null || assetsTbl.getAssetnumber()==""){params.put("assetsTbl", assetsTbl.getAssetnumber());}
		else if(assetsTbl.getBuilding() != null || assetsTbl.getBuilding()==""){params.put("assetsTbl", assetsTbl.getBuilding());}
		else if(assetsTbl.getCircuitid() != null || assetsTbl.getCircuitid()==""){params.put("assetsTbl", assetsTbl.getCircuitid());}
		else if(assetsTbl.getComment() != null || assetsTbl.getComment()==""){params.put("assetsTbl", assetsTbl.getComment());}
		else if(assetsTbl.getDateinstalled() != null || assetsTbl.getDateinstalled()==""){params.put("assetsTbl", assetsTbl.getDateinstalled());}
		else if(assetsTbl.getDepartment() != null || assetsTbl.getDepartment()==""){params.put("assetsTbl", assetsTbl.getDepartment());}
		else if(assetsTbl.getDescription() != null || assetsTbl.getDescription()==""){params.put("assetsTbl", assetsTbl.getDescription());}
		else if(assetsTbl.getDisplaycategory() != null || assetsTbl.getDisplaycategory()==""){params.put("assetsTbl", assetsTbl.getDisplaycategory());}
		
		else if(assetsTbl.getDivision() != null || assetsTbl.getDivision()==""){params.put("assetsTbl", assetsTbl.getDivision());}
		else if(assetsTbl.getFloor() != null || assetsTbl.getFloor()==""){params.put("assetsTbl", assetsTbl.getFloor());}
		else if(assetsTbl.getLease() != null || assetsTbl.getLease()==""){params.put("assetsTbl", assetsTbl.getLease());}
		else if(assetsTbl.getLeaseexpires() != null || assetsTbl.getLeaseexpires()==""){params.put("assetsTbl", assetsTbl.getLeaseexpires());}
		else if(assetsTbl.getMaintcontractexpires() != null || assetsTbl.getMaintcontractexpires()==""){params.put("assetsTbl", assetsTbl.getMaintcontractexpires());}
		else if(assetsTbl.getMaintcontract() != null || assetsTbl.getMaintcontract()==""){params.put("assetsTbl", assetsTbl.getMaintcontract());}
		else if(assetsTbl.getSupportphone() != null || assetsTbl.getSupportphone()==""){params.put("assetsTbl", assetsTbl.getSupportphone());}
		else if(assetsTbl.getManufacturer() != null || assetsTbl.getManufacturer()==""){params.put("assetsTbl", assetsTbl.getManufacturer());}
		else if(assetsTbl.getModelnumber() != null || assetsTbl.getModelnumber()==""){params.put("assetsTbl", assetsTbl.getModelnumber());}
		else if(assetsTbl.getNotifycategory() != null || assetsTbl.getNotifycategory()==""){params.put("assetsTbl", assetsTbl.getNotifycategory());}
		
		else if(assetsTbl.getOperatingsystem() != null || assetsTbl.getOperatingsystem()==""){params.put("assetsTbl", assetsTbl.getOperatingsystem());}
		else if(assetsTbl.getPort() != null || assetsTbl.getPort()==""){params.put("assetsTbl", assetsTbl.getPort());}
		else if(assetsTbl.getPollercategory() != null || assetsTbl.getPollercategory()==""){params.put("assetsTbl", assetsTbl.getPollercategory());}
		else if(assetsTbl.getRack() != null || assetsTbl.getRack()==""){params.put("assetsTbl", assetsTbl.getRack());}
		else if(assetsTbl.getRegion() != null || assetsTbl.getRegion()==""){params.put("assetsTbl", assetsTbl.getRegion());}
		else if(assetsTbl.getRoom() != null || assetsTbl.getRoom()==""){params.put("assetsTbl", assetsTbl.getRoom());}
		else if(assetsTbl.getSerialnumber() != null || assetsTbl.getSerialnumber()==""){params.put("assetsTbl", assetsTbl.getSerialnumber());}
		else if(assetsTbl.getSlot() != null || assetsTbl.getSlot()==""){params.put("assetsTbl", assetsTbl.getSlot());}
		else if(assetsTbl.getState() != null || assetsTbl.getState()==""){params.put("assetsTbl", assetsTbl.getState());}
		else if(assetsTbl.getThresholdcategory() != null || assetsTbl.getThresholdcategory()==""){params.put("assetsTbl", assetsTbl.getThresholdcategory());}

		else if(assetsTbl.getUserlastmodified() != null || assetsTbl.getUserlastmodified()==""){params.put("assetsTbl", assetsTbl.getUserlastmodified());}
		else if(assetsTbl.getVendor() != null || assetsTbl.getVendor()==""){params.put("assetsTbl", assetsTbl.getVendor());}
		else if(assetsTbl.getVendorassetnumber() != null || assetsTbl.getVendorassetnumber()==""){params.put("assetsTbl", assetsTbl.getVendorassetnumber());}
		else if(assetsTbl.getVendorfax() != null || assetsTbl.getVendorfax()==""){params.put("assetsTbl", assetsTbl.getVendorfax());}
		else if(assetsTbl.getVendorphone() != null || assetsTbl.getVendorphone()==""){params.put("assetsTbl", assetsTbl.getVendorphone());}
		else if(assetsTbl.getZip() != null || assetsTbl.getZip()==""){params.put("assetsTbl", assetsTbl.getZip());}
		else if(assetsTbl.getUsername() != null || assetsTbl.getUsername()==""){params.put("assetsTbl", assetsTbl.getUsername());}
		else if(assetsTbl.getPassword() != null || assetsTbl.getPassword()==""){params.put("assetsTbl", assetsTbl.getPassword());}
		else if(assetsTbl.getEnable() != null || assetsTbl.getEnable()==""){params.put("assetsTbl", assetsTbl.getEnable());}
		else if(assetsTbl.getConnection() != null || assetsTbl.getConnection()==""){params.put("assetsTbl", assetsTbl.getConnection());}
		
		else if(assetsTbl.getAutoenable() != null || assetsTbl.getAutoenable()==""){params.put("assetsTbl", assetsTbl.getAutoenable());}
		else if(assetsTbl.getCpu() != null || assetsTbl.getCpu()==""){params.put("assetsTbl", assetsTbl.getCpu());}
		else if(assetsTbl.getRam() != null || assetsTbl.getRam()==""){params.put("assetsTbl", assetsTbl.getRam());}
		else if(assetsTbl.getStoragectrl() != null || assetsTbl.getStoragectrl()==""){params.put("assetsTbl", assetsTbl.getStoragectrl());}
		else if(assetsTbl.getNumpowersupplies() != null || assetsTbl.getNumpowersupplies()==""){params.put("assetsTbl", assetsTbl.getNumpowersupplies());}
		else if(assetsTbl.getInputpower() != null || assetsTbl.getInputpower()==""){params.put("assetsTbl", assetsTbl.getInputpower());}
		else if(assetsTbl.getAdditionalhardware() != null || assetsTbl.getAdditionalhardware()==""){params.put("assetsTbl", assetsTbl.getAdditionalhardware());}
		else if(assetsTbl.getAdmin() != null || assetsTbl.getAdmin()==""){params.put("assetsTbl", assetsTbl.getAdmin());}
		else if(assetsTbl.getSnmpcommunity() != null || assetsTbl.getSnmpcommunity()==""){params.put("assetsTbl", assetsTbl.getSnmpcommunity());}
		else if(assetsTbl.getRackunitheight() != null || assetsTbl.getRackunitheight()==""){params.put("assetsTbl", assetsTbl.getRackunitheight());}
		
		List<AssetsTbl> FieldLists 	= null;
		
		try
		{
			FieldLists = (List<AssetsTbl>) super.getSqlMapClientTemplate().queryForList(sqlMapId,params);
			ret = true;
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
			ret = false;
		}finally
		{
			if(FieldLists != null)
			{
				fieldInfo.addAll(FieldLists);
			}
		}
		return ret;
		
	}


	


	/*@Override
	public boolean fieldSearch(List<AssetsTbl> CatagoryList) {
		boolean ret = false;
		String sqlMapId = "com.bluecapsystem.nms.assets.getAllAssetList";
		
		List<AssetsTbl> CatagoryLists 	= null;
		
		try
		{
			CatagoryLists = (List<AssetsTbl>) super.getSqlMapClientTemplate().queryForList(sqlMapId);
			ret = true;
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
			ret = false;
		}finally
		{
			if(CatagoryLists != null)
			{
				CatagoryList.addAll(CatagoryLists);
			}
		}
		return ret;
	}*/



	

	
	
}

