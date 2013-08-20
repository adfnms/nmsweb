package com.bluecapsystem.nms.dto;



public class CategoriesTbl {

	
	private Integer categoryid;	
	private String categoryname;
	private String nodelabel;
	private String nodeid;
	private String categorydescription;
	
	
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("CategoriesTbl[");
		sb.append(String.format("categoryid:%s,", getCategoryid()));
		sb.append(String.format("nodelabel:%s,", getNodelabel()));
		sb.append(String.format("nodeid:%s,", getNodeid()));
		sb.append(String.format("categoryname:%s,", getCategoryname()));
		sb.append(String.format("categorydescription:%s,", getCategorydescription()));
		sb.append("]");
		return sb.toString();
	}
	
	
	public String getNodelabel() {
		return nodelabel;
	}


	public void setNodelabel(String nodelabel) {
		this.nodelabel = nodelabel;
	}


	public String getNodeid() {
		return nodeid;
	}


	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}


	public Integer getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public String getCategorydescription() {
		return categorydescription;
	}
	public void setCategorydescription(String categorydescription) {
		this.categorydescription = categorydescription;
	}
	
	
	
}
