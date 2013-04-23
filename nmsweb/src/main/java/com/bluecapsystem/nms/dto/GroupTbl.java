package com.bluecapsystem.nms.dto;

import java.util.Date;



public class GroupTbl {

	
	private String groupId;	
	private String groupNm;
	private String groupComments;
	private String	regrId;
	private Date	regDt;
	private String	modrId;
	private Date	modDt;
	private String useYn;
	
	
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("GroupTbl[");
		
		sb.append(String.format("groupId:%s,", getGroupId()));
		sb.append(String.format("groupNm:%s,", getGroupNm()));
		sb.append(String.format("regrId:%s,", getRegrId()));
		sb.append(String.format("regDt:%d,", getRegDt()));
		sb.append(String.format("modrId:%d,", getModrId()));
		sb.append(String.format("modDt:%s,", getModDt()));
		sb.append(String.format("useYn:%s,", getUseYn()));
		 
		sb.append("]");
		
		return sb.toString();
	}


	public String getGroupId() {
		return groupId;
	}


	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}


	public String getGroupNm() {
		return groupNm;
	}


	public void setGroupNm(String groupNm) {
		this.groupNm = groupNm;
	}


	public String getGroupComments() {
		return groupComments;
	}


	public void setGroupComments(String groupComments) {
		this.groupComments = groupComments;
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


	public String getModrId() {
		return modrId;
	}


	public void setModrId(String modrId) {
		this.modrId = modrId;
	}


	public Date getModDt() {
		return modDt;
	}


	public void setModDt(Date modDt) {
		this.modDt = modDt;
	}


	public String getUseYn() {
		return useYn;
	}


	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	
	
	
	
	
	
}
