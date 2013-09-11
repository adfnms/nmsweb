package com.bluecapsystem.nms.dto;

public class ServicesCategoriesTbl
{
	
	private Integer serviceid;	
	private String servicename;
	
	public Integer getServiceid(){
		return serviceid;
	}
	public void setServiceid(Integer serviceid){
		this.serviceid = serviceid;
	}
	public String getServicename(){
		return servicename;
	}
	public void setServicename(String servicename){
		this.servicename = servicename;
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		
		sb.append("ServicesCategoriesTbl[");
		sb.append(String.format("serviceid:%s,", getServiceid()));
		sb.append(String.format("servicename:%s,", getServicename()));
		sb.append("]");
		return sb.toString();
	}
	
}
