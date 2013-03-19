package kr.co.adflow.nms.web.vo.foreign;

import java.util.ArrayList;
import java.util.List;



// <policy
// class="org.opennms.netmgt.provision.persist.policies.MatchingIpInterfacePolicy"
// name="pol2chan">
// <parameter value="DISABLE_COLLECTION" key="action"/><parameter
// value="ALL_PARAMETERS" key="matchBehavior"/></policy>



public class ForPoliceS {

	public ForPoliceS() {

	}


	protected String isclass;
	

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
