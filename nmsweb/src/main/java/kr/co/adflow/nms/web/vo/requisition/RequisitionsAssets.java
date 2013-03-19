package kr.co.adflow.nms.web.vo.requisition;


//Post!!
// /requisitions/{name}/nodes/{foreignId}/assets 
// <asset value="test" name="admin"/>
// {"asset":[{"value":"test","name":"admin"}]}


public class RequisitionsAssets {

	public RequisitionsAssets() {

	}


	protected String name;

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
