package kr.co.adflow.nms.web.vo.categoryDetail;

import org.springframework.stereotype.Service;


@Service
public class CategoryInfo {

	protected boolean outage;

	protected int serviceCount;

	protected double availabili;
	
	protected int nodeId;

	protected int serviceId;

	public boolean getOutage() {
		return outage;
	}

	public void setOutage(boolean outage) {
		this.outage = outage;
	}

	public double getAvailabili() {
		return availabili;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public void setAvailabili(double availabili) {
		this.availabili = availabili;
	}

	public int getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public int getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(int serviceCount) {
		this.serviceCount = serviceCount;
	}
	
	
	

}
