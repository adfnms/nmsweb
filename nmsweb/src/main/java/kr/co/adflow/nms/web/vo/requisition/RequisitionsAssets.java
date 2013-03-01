package kr.co.adflow.nms.web.vo.requisition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//Post!!
// /requisitions/{name}/nodes/{foreignId}/assets 
// <asset value="test" name="admin"/>
// {"asset":[{"value":"test","name":"admin"}]}

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "value", "name" })
@XmlRootElement(name = "asset")
public class RequisitionsAssets {

	public RequisitionsAssets() {

	}

	@XmlElement(name = "name")
	protected String name;

	@XmlElement(name = "value")
	protected String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
