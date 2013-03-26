package kr.co.adflow.nms.web.vo;

public class Outage{


	public Integer getOutageid() {
		return outageid;
	}
	public void setOutageid(Integer outageid) {
		this.outageid = outageid;
	}

	public String getIflostservice() {
		return iflostservice;
	}
	public void setIflostservice(String iflostservice) {
		this.iflostservice = iflostservice;
	}
	public Integer getNodeid() {
		return nodeid;
	}
	public void setNodeid(Integer nodeid) {
		this.nodeid = nodeid;
	}
	public Integer getServiceid() {
		return serviceid;
	}
	public void setServiceid(Integer serviceid) {
		this.serviceid = serviceid;
	}
	public String getIpaddr() {
		return ipaddr;
	}
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	
	private Integer  outageid;
	private String iflostservice;
	private Integer  nodeid;
	private Integer  serviceid;
	private String ipaddr;
	


}
