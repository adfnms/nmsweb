package kr.co.adflow.web.vo.foreign;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "key" ,"value"})
@XmlRootElement(name = "parameter")
public class ForParam {
	
	public ForParam(){
		
	}
	@XmlElement(name = "key")
	protected String key;
	
	@XmlElement(name = "value")
	protected String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
