package kr.co.adflow.web.vo.foreign;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


//<detector class="org.opennms.netmgt.provision.detector.simple.HttpDetector" name="chan2"/>
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "isclass" ,"name"})
@XmlRootElement(name = "detector")
public class ForDetector {

	public ForDetector() {

	}

	@XmlElement(name = "isclass")
	protected String isclass;
	
	@XmlElement(name = "name")
	protected String name;

	public String getIsclass() {
		return isclass;
	}

	public void setIsclass(String isclass) {
		this.isclass = isclass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
