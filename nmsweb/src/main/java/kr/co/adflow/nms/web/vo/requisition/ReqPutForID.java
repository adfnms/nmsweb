package kr.co.adflow.nms.web.vo.requisition;


// foreign-id="123455"
//building=test1djdjdj foreign-id=1363657923 node-label=324234234

public class ReqPutForID {

	public ReqPutForID() {

	}

	protected String foreignid;

	protected String building;

	protected String nodelabel;

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

	public String getNodelabel() {
		return nodelabel;
	}

	public void setNodelabel(String nodelabel) {
		this.nodelabel = nodelabel;
	}

}
