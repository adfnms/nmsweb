package com.bluecapsystem.nms.dto;

import java.util.Date;

public class MenuTbl 
{
	private Integer menuId;
	private Integer menuLev;
	private Integer menuOrd;
	private Integer upperMenuId;
	private String	menuNm;
	private String	url;
	private String	menuDesc;
	private String 	openYn;
	private String	regrId;
	private Date	regDt;
	private String	modrId;
	private Date	modDt;
	/*private Integer authId; // 권한 ID
*/	
	private String useYn;
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Integer getMenuLev() {
		return menuLev;
	}
	public void setMenuLev(Integer menuLev) {
		this.menuLev = menuLev;
	}
	public Integer getMenuOrd() {
		return menuOrd;
	}
	public void setMenuOrd(Integer menuOrd) {
		this.menuOrd = menuOrd;
	}
	public Integer getUpperMenuId() {
		return upperMenuId;
	}
	public void setUpperMenuId(Integer upperMenuId) {
		this.upperMenuId = upperMenuId;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMenuDesc() {
		return menuDesc;
	}
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}
	public String getOpenYn() {
		return openYn;
	}
	public void setOpenYn(String openYn) {
		this.openYn = openYn;
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
