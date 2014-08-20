package com.bluecapsystem.nms.dto;

import java.sql.Timestamp;

public class NodeTbl {
	private Integer nodeid;
	
	private String dpname;
	private Integer nodeparentid;
	private String nodetype;
	private String nodesysoid;
	private String nodesysname;
	private String nodesysdescription;
	private String nodesyslocation;
	private String nodesyscontact;
	private String nodelabel;
	private Timestamp nodecreatetime;
	
	private String nodelabelsource;
	private String nodenetbiosname;
	private String nodedomainname;
	private String operatingsystem;
	private String foreignsource;
	private String foreignid;
	  
	public Integer getNodeid() {
		return nodeid;
	}
	public void setNodeid(Integer nodeid) {
		this.nodeid = nodeid;
	}
	public String getNodelabel() {
		return nodelabel;
	}
	public void setNodelabel(String nodelabel) {
		this.nodelabel = nodelabel;
	}
	public Timestamp getNodecreatetime() {
		return nodecreatetime;
	}
	public void setNodecreatetime(Timestamp nodecreatetime) {
		this.nodecreatetime = nodecreatetime;
	}
	  
	  
}
