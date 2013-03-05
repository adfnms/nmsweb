package kr.co.adflow.web.vo.foreign;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


//{"name":"chan"}
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "name"})
@XmlRootElement(name = "name")
public class ForPutName {
	
	public ForPutName(){
		
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

	
