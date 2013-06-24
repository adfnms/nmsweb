package com.bluecapsystem.nms.dto;

import java.util.Date;



public class CategoryNodeTbl {

	private Integer categoryid;
	private Integer nodeid;	
	
	
	
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("Category_NodeTbl[");
		sb.append(String.format("categoryid:%s,", getCategoryid()));
		sb.append(String.format("nodeid:%s,", getNodeid()));
		sb.append("]");
		
		return sb.toString();
	}



	public Integer getCategoryid() {
		return categoryid;
	}



	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}



	public Integer getNodeid() {
		return nodeid;
	}



	public void setNodeid(Integer nodeid) {
		this.nodeid = nodeid;
	}


	
	
	
	
	
}
