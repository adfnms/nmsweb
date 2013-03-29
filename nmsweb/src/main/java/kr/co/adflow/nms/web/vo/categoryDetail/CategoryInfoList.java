package kr.co.adflow.nms.web.vo.categoryDetail;

import java.util.Hashtable;
import java.util.List;

public class CategoryInfoList {

	protected Hashtable<Integer, CategoryInfo> cateGoryInfo;

	protected int outageTotalCount;
	protected int serviceTotalCount;
	protected double availabili;
	protected String serviceids;

	public String getServiceids() {
		return serviceids;
	}

	public void setServiceids(String serviceids) {
		this.serviceids = serviceids;
	}

	public Hashtable<Integer, CategoryInfo> getCateGoryInfo() {
		if (cateGoryInfo == null) {
			cateGoryInfo = new Hashtable<Integer, CategoryInfo>();
		}
		return this.cateGoryInfo;
	}

	public int getOutageTotalCount() {
		return outageTotalCount;
	}

	public void setOutageTotalCount(int outageTotalCount) {
		this.outageTotalCount = outageTotalCount;
	}

	public int getServiceTotalCount() {
		return serviceTotalCount;
	}

	public void setServiceTotalCount(int serviceTotalCount) {
		this.serviceTotalCount = serviceTotalCount;
	}

	public double getAvailabili() {
		return availabili;
	}

	public void setAvailabiliAv(double availabili) {
		this.availabili = availabili;
	}

	

}
