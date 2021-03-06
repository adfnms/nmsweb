package kr.co.adflow.nms.web.mapper;

import java.util.Iterator;

import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.vo.foreign.ForPutName;
import kr.co.adflow.nms.web.vo.group.Groupinfo;
import kr.co.adflow.nms.web.vo.requisition.ReqPutForID;
import kr.co.adflow.nms.web.vo.requisition.ReqPutIP;
import kr.co.adflow.nms.web.vo.requisition.ReqPutName;
import kr.co.adflow.nms.web.vo.requisition.RequisitionsAssets;
import kr.co.adflow.nms.web.vo.requisition.RequisitionsCategory;
import kr.co.adflow.nms.web.vo.requisition.RequisitionsNodes;
import kr.co.adflow.nms.web.vo.requisition.RequisitionsNodesInterface;
import kr.co.adflow.nms.web.vo.requisition.RequisitionsService;
import kr.co.adflow.nms.web.vo.requisition.Requisitionsinfo;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RequisitionsMapper {

	private static final Logger logger = LoggerFactory
			.getLogger(RequisitionsMapper.class);

	// <model-import foreign-source="chandjdjdj"/>
	public Requisitionsinfo requisitionsInfo(String data)
			throws MapperException {
		Requisitionsinfo requisitions = new Requisitionsinfo();
		try {
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			if (jNode.path("model-import").isArray()) {
				JsonNode temp = null;

				Iterator<JsonNode> it = jNode.path("model-import").iterator();

				while (it.hasNext()) {
					temp = it.next();
					requisitions.setForeignsource(temp.path("foreign-source")
							.getTextValue());

				}

				logger.debug("model-import:::"
						+ requisitions.getForeignsource());
			}
		} catch (Exception e) {
			throw new MapperException(e);

		}
		return requisitions;
	}

	// <node node-label="zzzzzzz" foreign-id="13622227" building="test001"/>
	public RequisitionsNodes requisitionsNodes(String data)
			throws MapperException {
		RequisitionsNodes requisiNodes = new RequisitionsNodes();
		try {
			logger.debug("mappingdata::" + data);
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			if (jNode.path("node").isArray()) {
				JsonNode temp = null;

				Iterator<JsonNode> it = jNode.path("node").iterator();

				while (it.hasNext()) {
					temp = it.next();
					requisiNodes.setNodelabel(temp.path("node-label").getTextValue());
					requisiNodes.setForeignid(temp.path("foreign-id").getTextValue());
					requisiNodes.setBuilding(temp.path("building").getTextValue());
				}

				logger.debug("requisiNodes.getBuilding()::"+ requisiNodes.getBuilding());
			}
		} catch (Exception e) {
			throw new MapperException(e);

		}
		return requisiNodes;
	}

	// /requisitions/{name}/nodes/{foreignId}/interfaces !!!POST
	// <interface snmp-primary="S" status="1" ip-addr="61.78.35.200" descr="">
	public RequisitionsNodesInterface requisitionsNodesInterfaces(String data)
			throws MapperException {
		RequisitionsNodesInterface nodesInterface = new RequisitionsNodesInterface();
		try {
			logger.debug("mappingdata::" + data);
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			if (jNode.path("interface").isArray()) {
				JsonNode temp = null;

				Iterator<JsonNode> it = jNode.path("interface").iterator();

				while (it.hasNext()) {
					temp = it.next();
					nodesInterface
							.setStatus(temp.path("status").getTextValue());
					nodesInterface.setIpaddr(temp.path("ip-addr")
							.getTextValue());
					nodesInterface.setSnmpprimary(temp.path("snmp-primary")
							.getTextValue());
					nodesInterface.setDescr(temp.path("descr").getTextValue());
				}

				logger.debug("nodesInterface.getIpaddr():::"
						+ nodesInterface.getIpaddr());
			}
		} catch (Exception e) {
			throw new MapperException(e);

		}
		return nodesInterface;
	}

	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services
	// POST!!!!
	// <monitored-service service-name="ICMP"/>
	public RequisitionsService requisitionsService(String data)
			throws MapperException {
		RequisitionsService service = new RequisitionsService();
		try {
			logger.debug("mappingdata::" + data);
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			if (jNode.path("monitored-service").isArray()) {

				JsonNode temp = null;

				Iterator<JsonNode> it = jNode.path("monitored-service")
						.iterator();

				while (it.hasNext()) {
					temp = it.next();
					service.setServicename(temp.path("service-name")
							.getTextValue());

				}

				logger.debug("service.getServicename():::"
						+ service.getServicename());
			}
		} catch (Exception e) {
			throw new MapperException(e);

		}
		return service;
	}

	// requisitions/{name}/nodes/{foreignId}/categories
	// <category name="Production"/>

	public RequisitionsCategory requisitionsCategory(String data)
			throws MapperException {
		RequisitionsCategory category = new RequisitionsCategory();
		try {
			logger.debug("mappingdata::" + data);
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			if (jNode.path("category").isArray()) {
				JsonNode temp = null;

				Iterator<JsonNode> it = jNode.path("category").iterator();

				while (it.hasNext()) {
					temp = it.next();
					category.setName(temp.path("name").getTextValue());

				}

				logger.debug("category.getName():::" + category.getName());
			}
		} catch (Exception e) {
			throw new MapperException(e);

		}
		return category;
	}

	// Post!!
	// /requisitions/{name}/nodes/{foreignId}/assets
	// <asset value="test" name="admin"/>
	// {"asset":[{"value":"test","name":"admin"}]}

	public RequisitionsAssets requisitionsAssets(String data)
			throws MapperException {
		RequisitionsAssets assets = new RequisitionsAssets();
		try {
			logger.debug("mappingdata::" + data);
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			if (jNode.path("asset").isArray()) {
				JsonNode temp = null;

				Iterator<JsonNode> it = jNode.path("asset").iterator();

				while (it.hasNext()) {
					temp = it.next();
					assets.setName(temp.path("name").getTextValue());
					assets.setValue(temp.path("value").getTextValue());

				}

				logger.debug("assets.getName():::" + assets.getName());
			}
		} catch (Exception e) {
			throw new MapperException(e);

		}
		return assets;
	}

	// //foreign-source=chanhohoho
	public ReqPutName reqPutName(String data) throws MapperException {
		ReqPutName reqPutName = new ReqPutName();
		try {
			logger.debug("mappingdata::" + data);
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			jNode.path("foreign-source");

			reqPutName.setForeignsource(jNode.path("foreign-source")
					.getTextValue());

			logger.debug("reqPutName.getForeignsource():::"
					+ reqPutName.getForeignsource());

		} catch (Exception e) {
			throw new MapperException(e);

		}
		return reqPutName;
	}

	// foreign-id="123455"
	public ReqPutForID reqPutId(String data) throws MapperException {
		ReqPutForID reqPutId = new ReqPutForID();
		try {
			logger.debug("mappingdata::" + data);
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			String charAt2 = String.valueOf(data.charAt(2));
			logger.debug("charAt2:" + charAt2);
			if (charAt2.equals("f")) {
				jNode.path("foreign-id");
				reqPutId.setForeignid(jNode.path("foreign-id").getTextValue());
				logger.debug("reqPutgetForeignid()):::"
						+ reqPutId.getForeignid());
			} else if (charAt2.equals("n")) {
				logger.debug("node-label");
				jNode.path("node-label");
				reqPutId.setNodelabel(jNode.path("node-label").getTextValue());
				logger.debug("getNodelabel()):::" + reqPutId.getNodelabel());
			} else if (charAt2.equals("b")) {
				jNode.path("building");
				reqPutId.setBuilding(jNode.path("building").getTextValue());
				logger.debug("getBuilding()):::" + reqPutId.getBuilding());
			}

		} catch (Exception e) {
			throw new MapperException(e);

		}
		logger.debug("return step...");
		return reqPutId;
	}

	// {"snmp-primary":"S","ip-addr":"127.0.0.1","descr":"dfdfdf"}
	public ReqPutIP reqPutIP(String data) throws MapperException {
		ReqPutIP reqPutIP = new ReqPutIP();
		try {
			logger.debug("mappingdata::" + data);
			String charAt2 = String.valueOf(data.charAt(2));
			logger.debug("charAt2:" + charAt2);
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			if (charAt2.equals("i")) {
				jNode.path("ip-addr");
				reqPutIP.setIpaddr(jNode.path("ip-addr").getTextValue());
				logger.debug("getIpaddr()):::" + reqPutIP.getIpaddr());
			} else if (charAt2.equals("s")) {
				jNode.path("snmp-primary");
				reqPutIP.setSnmpPrimary(jNode.path("snmp-primary")
						.getTextValue());
				logger.debug("getSnmpPrimary()):::" + reqPutIP.getSnmpPrimary());
			} else if (charAt2.equals("d")) {
				jNode.path("descr");
				reqPutIP.setDescr(jNode.path("descr").getTextValue());
				logger.debug("getDescr()):::" + reqPutIP.getDescr());
			}

		} catch (Exception e) {
			throw new MapperException(e);

		}
		return reqPutIP;
	}
}
