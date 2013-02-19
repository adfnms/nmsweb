package kr.co.adflow.nms.web.vo;

public class ipInterface {
	
	
	public String getSnmpPrimary() {
		return snmpPrimary;
	}
	public void setSnmpPrimary(String snmpPrimary) {
		this.snmpPrimary = snmpPrimary;
	}
	public String getMonitoredServiceCount() {
		return monitoredServiceCount;
	}
	public void setMonitoredServiceCount(String monitoredServiceCount) {
		this.monitoredServiceCount = monitoredServiceCount;
	}
	public String getIsManaged() {
		return isManaged;
	}
	public void setIsManaged(String isManaged) {
		this.isManaged = isManaged;
	}
	public String getIsDown() {
		return isDown;
	}
	public void setIsDown(String isDown) {
		this.isDown = isDown;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getLastCapsdPoll() {
		return lastCapsdPoll;
	}
	public void setLastCapsdPoll(String lastCapsdPoll) {
		this.lastCapsdPoll = lastCapsdPoll;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	
	private String snmpPrimary;
	private String monitoredServiceCount;
	private String isManaged;
	private String isDown;
	private String ipAddress;
	private String hostName;
	private String lastCapsdPoll;
	private String nodeId;
	

}
