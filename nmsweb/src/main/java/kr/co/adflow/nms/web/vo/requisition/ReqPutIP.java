package kr.co.adflow.nms.web.vo.requisition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


//ip-addr=127.0.0.2
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "ipaddr"})
@XmlRootElement(name = "ip-addr")
public class ReqPutIP {
	
	public ReqPutIP(){
		
	}
	@XmlElement(name = "ip-addr")
	protected String ipaddr;
	public String getIpaddr() {
		return ipaddr;
	}
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	
}

