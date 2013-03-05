package kr.co.adflow.web.vo.foreign;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



// <policy
// class="org.opennms.netmgt.provision.persist.policies.MatchingIpInterfacePolicy"
// name="pol2chan">
// <parameter value="DISABLE_COLLECTION" key="action"/><parameter
// value="ALL_PARAMETERS" key="matchBehavior"/></policy>


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "isclass" ,"name","parameter"})
@XmlRootElement(name = "policy")
public class ForPoliceS {

	public ForPoliceS() {

	}

	@XmlElement(name = "isclass")
	protected String isclass;
	
	@XmlElement(name = "name")
	protected String name;

	
	protected List<ForParam> parameter;
	
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
	public List<ForParam> getUser() {
        if (parameter == null) {
        	parameter = new ArrayList<ForParam>();
        }
        return this.parameter;
    }
	
}
