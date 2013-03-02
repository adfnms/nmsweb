package kr.co.adflow.nms.web.vo;

public class PathOutage{


	public String getNodeid() {
		return nodeid;
	}
	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}
	public String getCriticalpathip() {
		return criticalpathip;
	}
	public void setCriticalpathip(String criticalpathip) {
		this.criticalpathip = criticalpathip;
	}
	public String getCriticalpathservicename() {
		return criticalpathservicename;
	}
	public void setCriticalpathservicename(String criticalpathservicename) {
		this.criticalpathservicename = criticalpathservicename;
	}
	
	private String nodeid;
	private String criticalpathip;
	private String criticalpathservicename;


}
