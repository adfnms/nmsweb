package kr.co.adflow.nms.web.vo.categoryDetail;

public class CategoryInfo {

	protected int outageCount;

	protected int serviceCount;

	protected double availabili;

	protected int nodeId;



	protected String nodeLabel;
	protected String ipAddress;

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getNodeLabel() {
		return nodeLabel;
	}

	public void setNodeLabel(String nodeLabel) {
		this.nodeLabel = nodeLabel;
	}

	public int getOutageCount() {
		return outageCount;
	}

	public void setOutageCount(int outageCount) {
		this.outageCount = outageCount;
	}

	public double getAvailabili() {
		return availabili;
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
