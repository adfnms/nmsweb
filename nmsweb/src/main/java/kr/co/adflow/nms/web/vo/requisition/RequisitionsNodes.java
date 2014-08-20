package kr.co.adflow.nms.web.vo.requisition;


//<node node-label="zzzzzzz" foreign-id="13622227" building="test001"/>
//{"node[{"node-label:"zzzzzzz","foreign-id":"13622227","building":"test001"}]};

public class RequisitionsNodes {

	public RequisitionsNodes() {

	}


	
	protected String nodelabel;
	
	protected String foreignid;
	
	protected String building;

	protected Integer nodeid;
	
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
	public Integer getNodeid() {
		return nodeid;
	}
	public void setNodeid(Integer nodeid) {
		this.nodeid = nodeid;
	}

	

}