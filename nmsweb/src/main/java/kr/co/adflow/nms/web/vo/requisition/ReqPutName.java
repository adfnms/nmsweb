package kr.co.adflow.nms.web.vo.requisition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//foreign-source=chanhohoho
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "foreignsource"})
@XmlRootElement(name = "foreign-source")
public class ReqPutName {
	
	public ReqPutName(){
		
	}
	@XmlElement(name = "foreign-source")
	protected String foreignsource;
	public String getForeignsource() {
		return foreignsource;
	}
	public void setForeignsource(String foreignsource) {
		this.foreignsource = foreignsource;
	}
	
}