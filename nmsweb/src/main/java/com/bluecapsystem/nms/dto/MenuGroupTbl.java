package com.bluecapsystem.nms.dto;

import java.util.Date;



public class MenuGroupTbl {

	
	private String menuId;	
	private String groupNm;
	private String	regrId;
	private Date	regDt;
	
	
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("MenuGroupTbl[");
		
		sb.append(String.format("menuId:%s,", getMenuId()));
		sb.append(String.format("groupNm:%s,", getGroupNm()));
		sb.append(String.format("regrId:%s,", getRegrId()));
		sb.append(String.format("regDt:%d,", getRegDt()));
		 
		sb.append("]");
		
		return sb.toString();
	}


	public String getMenuId() {
		return menuId;
	}


	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}


	public String getGroupNm() {
		return groupNm;
	}


	public void setGroupNm(String groupNm) {
		this.groupNm = groupNm;
	}


	public String getRegrId() {
		return regrId;
	}


	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}


	public Date getRegDt() {
		return regDt;
	}


	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}


	
	
	
	
}
