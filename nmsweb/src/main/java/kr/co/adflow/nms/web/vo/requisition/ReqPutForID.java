package kr.co.adflow.nms.web.vo.requisition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



// foreign-id="123455"
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "foreignid"})
@XmlRootElement(name = "foreign-id")
public class ReqPutForID {
	
	public ReqPutForID(){
		
	}
	@XmlElement(name = "foreign-id")
	protected String foreignid;
	public String getForeignid() {
		return foreignid;
	}
	public void setForeignid(String foreignid) {
		this.foreignid = foreignid;
	}
	
}
