package com.bluecapsystem.nms.dto;

import java.util.Date;

public class SystemTbl 
{
	
	private String	sysId;
	private String	sysNm;
	private String	sysNic;
	private String	regrId;
	private Date	regDt;
	private String	modrId;
	private Date	modDt;
	private String  useYn;
	
	
	
	
	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public SystemTbl()
	{
		setSysId(null);
		setSysNm(null);
		setSysNic(null);
		
		setRegrId(null);
		setRegDt(null);
		setModrId(null);
		setModDt(null);
	}
	
	@Override
	public String toString()
	{
		String retStr = String.format("sysId:%s, sysNm:%s, sysNic:%s", sysId, sysNm, sysNic);
		return retStr;
	}


	public String getSysId() {
		return sysId;
	}


	public void setSysId(String sysId) {
		this.sysId = sysId;
	}


	public String getSysNm() {
		return sysNm;
	}


	public void setSysNm(String sysNm) {
		this.sysNm = sysNm;
	}


	public String getSysNic() {
		return sysNic;
	}


	public void setSysNic(String sysNic) {
		this.sysNic = sysNic;
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
	
	

}
