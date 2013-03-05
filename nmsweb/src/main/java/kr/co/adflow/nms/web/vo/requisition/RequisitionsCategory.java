package kr.co.adflow.nms.web.vo.requisition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//<category name="Production"/>

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "name" })
@XmlRootElement(name = "category")
public class RequisitionsCategory {

	public RequisitionsCategory() {

	}

	@XmlElement(name = "name")
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}