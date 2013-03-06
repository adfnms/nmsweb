package kr.co.adflow.nms.web.vo.requisition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//<interface snmp-primary="S" status="1" ip-addr="61.78.35.200" descr="">

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "snmpprimary", "status","ipaddr","descr"})
@XmlRootElement(name = "interface")
public class RequisitionsNodesInterface {

	public RequisitionsNodesInterface() {

	}

	protected String snmpprimary;
	@XmlElement(name = "status")
	protected String status;
	@XmlElement(name = "ip-addr")
	protected String ipaddr;
	@XmlElement(name = "descrr")
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