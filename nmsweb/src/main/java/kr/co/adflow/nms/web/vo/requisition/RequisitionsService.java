package kr.co.adflow.nms.web.vo.requisition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//<monitored-service service-name="ICMP"/>
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "servicename" })
@XmlRootElement(name = "monitored-servic")
public class RequisitionsService {

	public RequisitionsService() {

	}

	@XmlElement(name = "service-name")
	protected String servicename;

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}




}