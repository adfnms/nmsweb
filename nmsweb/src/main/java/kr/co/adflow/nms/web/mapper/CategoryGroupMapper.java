package kr.co.adflow.nms.web.mapper;

import java.util.Iterator;

import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.vo.resultcategory.CategoryJsonGroup;
import kr.co.adflow.nms.web.vo.resultcategory.DNSDHCPServer;
import kr.co.adflow.nms.web.vo.resultcategory.DataBaseServer;
import kr.co.adflow.nms.web.vo.resultcategory.EmailServers;
import kr.co.adflow.nms.web.vo.resultcategory.JMXServers;
import kr.co.adflow.nms.web.vo.resultcategory.NetWorkInterfaces;
import kr.co.adflow.nms.web.vo.resultcategory.OtherServer;
import kr.co.adflow.nms.web.vo.resultcategory.WebServers;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CategoryGroupMapper {

	private static final Logger logger = LoggerFactory
			.getLogger(CategoryGroupMapper.class);

	public CategoryJsonGroup cateJson(String data) throws MapperException {

		CategoryJsonGroup categoryJson = new CategoryJsonGroup();

		try {

			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			if (jNode.path("result").isArray()) {
				JsonNode temp = null;
				JsonNode temp2 = null;
				Iterator<JsonNode> it = jNode.path("result").iterator();

				while (it.hasNext()) {
					temp = it.next();
					if (temp.path("Network Interfaces").isArray()) {
						Iterator<JsonNode> it2 = temp
								.path("Network Interfaces").iterator();
						while (it2.hasNext()) {
							NetWorkInterfaces network = new NetWorkInterfaces();
							temp2 = it2.next();
							network.setNetWorkServers(temp2.getTextValue());
							categoryJson.netWorkService().add(network);
						}
					}
					if (temp.path("Email Servers").isArray()) {
						Iterator<JsonNode> it2 = temp.path("Email Servers")
								.iterator();
						while (it2.hasNext()) {
							EmailServers emailServer = new EmailServers();
							temp2 = it2.next();
							emailServer.setEmailServer(temp2.getTextValue());
							categoryJson.emailServers().add(emailServer);
						}
					}
					if (temp.path("Web Servers").isArray()) {
						Iterator<JsonNode> it2 = temp.path("Web Servers")
								.iterator();
						while (it2.hasNext()) {
							WebServers webServers = new WebServers();
							temp2 = it2.next();
							webServers.setWebServers(temp2.getTextValue());
							categoryJson.webServers().add(webServers);
						}
					}
					if (temp.path("JMX Servers").isArray()) {
						Iterator<JsonNode> it2 = temp.path("JMX Servers")
								.iterator();
						while (it2.hasNext()) {
							JMXServers jmxServer = new JMXServers();
							temp2 = it2.next();
							jmxServer.setJmxServers(temp2.getTextValue());
							categoryJson.jmxServers().add(jmxServer);
						}
					}
					if (temp.path("DNS and DHCP Servers").isArray()) {
						Iterator<JsonNode> it2 = temp.path(
								"DNS and DHCP Servers").iterator();
						while (it2.hasNext()) {
							DNSDHCPServer dnserver = new DNSDHCPServer();
							temp2 = it2.next();
							dnserver.setDnsDhcpServer(temp2.getTextValue());
							categoryJson.dnsDhcpServers().add(dnserver);
						}
					}
					if (temp.path("Database Servers").isArray()) {
						Iterator<JsonNode> it2 = temp.path("Database Servers")
								.iterator();
						while (it2.hasNext()) {
							DataBaseServer dbserver = new DataBaseServer();
							temp2 = it2.next();
							dbserver.setDataBaseServer(temp2.getTextValue());
							categoryJson.dataBaseServer().add(dbserver);
						}
					}

					if (temp.path("Other Servers").isArray()) {
						Iterator<JsonNode> it2 = temp.path("Other Servers")
								.iterator();
						while (it2.hasNext()) {
							OtherServer otServer = new OtherServer();
							temp2 = it2.next();
							otServer.setOtherServers(temp2.getTextValue());
							categoryJson.otherServers().add(otServer);
						}
					}

				}

			}
		} catch (Exception e) {
			throw new MapperException(e);
		}

		return categoryJson;

	}

}
