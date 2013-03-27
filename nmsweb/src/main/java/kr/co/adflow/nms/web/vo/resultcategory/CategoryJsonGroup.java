package kr.co.adflow.nms.web.vo.resultcategory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class CategoryJsonGroup {
	
	protected List<NetWorkInterfaces> netWorkInterfaces;
	protected List<WebServers> webServers;
	protected List<OtherServer> otherServers;
	protected List<JMXServers> jmxServers;
	protected List<EmailServers> emailServers;
	protected List<DNSDHCPServer> dnsDhcpServers;
	protected List<DataBaseServer> dataBaseServer;
	
	
	public List<NetWorkInterfaces> netWorkService() {
		if (netWorkInterfaces == null) {
			netWorkInterfaces = new ArrayList<NetWorkInterfaces>();
		}
		return this.netWorkInterfaces;
	}
	public List<WebServers> webServers() {
		if (webServers == null) {
			webServers = new ArrayList<WebServers>();
		}
		return this.webServers;
	}
	public List<OtherServer> otherServers() {
		if (otherServers == null) {
			otherServers = new ArrayList<OtherServer>();
		}
		return this.otherServers;
	}
	public List<JMXServers> jmxServers() {
		if (jmxServers == null) {
			jmxServers = new ArrayList<JMXServers>();
		}
		return this.jmxServers;
	}
	public List<EmailServers> emailServers() {
		if (emailServers == null) {
			emailServers = new ArrayList<EmailServers>();
		}
		return this.emailServers;
	}
	public List<DNSDHCPServer> dnsDhcpServers() {
		if (dnsDhcpServers == null) {
			dnsDhcpServers = new ArrayList<DNSDHCPServer>();
		}
		return this.dnsDhcpServers;
	}
	public List<DataBaseServer> dataBaseServer() {
		if (dataBaseServer == null) {
			dataBaseServer = new ArrayList<DataBaseServer>();
		}
		return this.dataBaseServer;
	}

}
