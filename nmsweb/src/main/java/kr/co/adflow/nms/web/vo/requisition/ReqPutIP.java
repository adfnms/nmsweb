package kr.co.adflow.nms.web.vo.requisition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


//{"@snmp-primary":"S","@status":"1","@ip-addr":"127.0.0.1","@descr":"dfdfdf"}
public class ReqPutIP {
	
	public ReqPutIP(){
		
	}
	protected String descr;
	protected String snmpPrimary;
	protected String ipaddr;
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
	public String getSnmpPrimary() {
		return snmpPrimary;
	}
	public void setSnmpPrimary(String snmpPrimary) {
		this.snmpPrimary = snmpPrimary;
	}
	
	
	
}

