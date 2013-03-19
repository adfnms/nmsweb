package kr.co.adflow.nms.web.vo.requisition;


//<interface snmp-primary="S" status="1" ip-addr="61.78.35.200" descr="">


public class RequisitionsNodesInterface {

	public RequisitionsNodesInterface() {

	}

	protected String snmpprimary;
	
	protected String status;
	
	protected String ipaddr;

	protected String descr;
	public String getSnmpprimary() {
		return snmpprimary;
	}
	public void setSnmpprimary(String snmpprimary) {
		this.snmpprimary = snmpprimary;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIpaddr() {
		return ipaddr;
	}
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}



}