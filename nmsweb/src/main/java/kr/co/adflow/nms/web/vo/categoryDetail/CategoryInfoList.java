package kr.co.adflow.nms.web.vo.categoryDetail;

import java.util.Hashtable;
import java.util.List;

public class CategoryInfoList {

	protected Hashtable<String, CategoryInfo> cateGoryInfo;

	protected int outageTotalCount;
	protected int serviceTotalCount;
	protected double availabiliAv;
	protected List<String> serviceids;

	public Hashtable<String, CategoryInfo> getCateGoryInfo() {
		if (cateGoryInfo == null) {
			cateGoryInfo = new Hashtable<String, CategoryInfo>();
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

	public double getAvailabiliAv() {
		return availabiliAv;
	}

	public void setAvailabiliAv(double availabiliAv) {
		this.availabiliAv = availabiliAv;
	}

	public List<String> getServiceids() {
		return serviceids;
	}

	public void setServiceids(List<String> serviceids) {
		this.serviceids = serviceids;
	}

}
