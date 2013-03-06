package kr.co.adflow.nms.web.vo.requisition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//<node node-label="zzzzzzz" foreign-id="13622227" building="test001"/>
//{"node[{"node-label:"zzzzzzz","foreign-id":"13622227","building":"test001"}]};
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "nodelabel", "foreignid", "building" })
@XmlRootElement(name = "node")
public class RequisitionsNodes {

	public RequisitionsNodes() {

	}


	@XmlElement(name = "node-label")
	protected String nodelabel;
	@XmlElement(name = "foreign-id")
	protected String foreignid;
	@XmlElement(name = "building")
	protected String building;

	public String getNodelabel() {
		return nodelabel;
	}
	public void setNodelabel(String nodelabel) {
		this.nodelabel = nodelabel;
	}
	public String getForeignid() {
		return foreignid;
	}
	public void setForeignid(String foreignid) {
		this.foreignid = foreignid;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}

	

}