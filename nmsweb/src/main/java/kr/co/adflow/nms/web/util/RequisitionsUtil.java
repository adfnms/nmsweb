package kr.co.adflow.nms.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.vo.requisition.ReqPutForID;
import kr.co.adflow.nms.web.vo.requisition.ReqPutIP;
import kr.co.adflow.nms.web.vo.requisition.ReqPutName;
import kr.co.adflow.nms.web.vo.requisition.RequisitionsAssets;
import kr.co.adflow.nms.web.vo.requisition.RequisitionsCategory;
import kr.co.adflow.nms.web.vo.requisition.RequisitionsNodes;
import kr.co.adflow.nms.web.vo.requisition.RequisitionsNodesInterface;
import kr.co.adflow.nms.web.vo.requisition.RequisitionsService;
import kr.co.adflow.nms.web.vo.requisition.Requisitionsinfo;

public class RequisitionsUtil {

	// <model-import foreign-source="chandjdjdj"/>

	private static final Logger logger = LoggerFactory
			.getLogger(RequisitionsUtil.class);

	public static RequisitionsUtil util = new RequisitionsUtil();

	private RequisitionsUtil() {

	}

	public static RequisitionsUtil getInstance() {
		return util;
	}

	public String xmlParsingRequisitions(Requisitionsinfo requisition)
			throws HandleException {
		String data = null;
		try {
			String foreign = requisition.getForeignsource();

			StringBuffer bf = new StringBuffer();
			bf.append("<model-import foreign-source=\"" + foreign + "\"/>");

			data = bf.toString();
			logger.debug("xmldata::" + data);
		} catch (Exception e) {
			throw new HandleException(e);
		}
		return data;
	}

	// <node node-label="zzzzzzz" foreign-id="13622227" building="test001"/>
	// {"node[{"node-label:"zzzzzzz","foreign-id":"13622227","building":"test001"}]};
	public String xmlParsingRequisitionsNodes(RequisitionsNodes nodes)
			throws HandleException {
		String data = null;
		try {
			String nodeLable = nodes.getNodelabel();
			String foreignId = nodes.getForeignid();
			String building = nodes.getBuilding();

			StringBuffer bf = new StringBuffer();
			bf.append("<node node-label=\"" + nodeLable + "\" foreign-id=\""
					+ foreignId + "\" building=\"" + building + "\"/>");

			data = bf.toString();
			logger.debug("xmldata::" + data);
		} catch (Exception e) {
			throw new HandleException(e);
		}
		return data;
	}

	// /requisitions/{name}/nodes/{foreignId}/interfaces !!!POST
	// <interface snmp-primary="P" status="1" ip-addr="59.150.236.73"
	// descr="bcs_test"/>
	public String xmlParsingRequisitionsNodesInterfaces(
			RequisitionsNodesInterface nodeInterface) throws HandleException {
		String data = null;
		try {
			String descr = nodeInterface.getDescr();
			String ipAddr = nodeInterface.getIpaddr();
			String snmpPri = nodeInterface.getSnmpprimary();
			String status = nodeInterface.getStatus();

			StringBuffer bf = new StringBuffer();
			bf.append("<interface snmp-primary=\"" + snmpPri + "\" status=\""
					+ status + "\" ip-addr=\"" + ipAddr + "\" descr=\"" + descr
					+ "\"/>");

			data = bf.toString();
			logger.debug("xmldata::" + data);
		} catch (Exception e) {
			throw new HandleException(e);
		}
		return data;
	}

	// <monitored-service service-name="ICMP"/>
	public String xmlParsingRequisitionsService(RequisitionsService services)
			throws HandleException {
		String data = null;
		try {
			String servicesName = services.getServicename();
			String upperName = servicesName.toUpperCase();

			StringBuffer bf = new StringBuffer();
			bf.append("<monitored-service service-name=\"" + upperName + "\"/>");

			data = bf.toString();
			logger.debug("xmldata::" + data);
		} catch (Exception e) {
			throw new HandleException(e);
		}
		return data;
	}

	// requisitions/{name}/nodes/{foreignId}/categories
	// <category name="Production"/>
	public String xmlParsingReqCategory(RequisitionsCategory category)
			throws HandleException {
		String data = null;
		try {
			String categoryName = category.getName();
			// String upperName=categoryName.toUpperCase();

			StringBuffer bf = new StringBuffer();
			bf.append("<category name=\"" + categoryName + "\"/>");

			data = bf.toString();
			logger.debug("xmldata::" + data);
		} catch (Exception e) {
			throw new HandleException(e);
		}
		return data;
	}
	// Post!!
	// /requisitions/{name}/nodes/{foreignId}/assets 
	// <asset value="test" name="admin"/>
	// {"asset":[{"value":"test","name":"admin"}]}	
	
	public String xmlParsingReqAssets(RequisitionsAssets assets)
			throws HandleException {
		String data = null;
		try {
			String assetsName = assets.getName();
			String assetsValue=assets.getValue();
			// String upperName=categoryName.toUpperCase();

			StringBuffer bf = new StringBuffer();
			bf.append("<asset value=\""+assetsValue+"\" name=\""+assetsName+"\"/>");
			

			data = bf.toString();
			logger.debug("xmldata::" + data);
		} catch (Exception e) {
			throw new HandleException(e);
		}
		return data;
	}
	
	public String ParsingReqNameData(ReqPutName putName)
			throws HandleException {
		String data = null;
		try {
			String foreignSource = putName.getForeignsource();
			
			

			StringBuffer bf = new StringBuffer();
			bf.append("foreign-Source="+foreignSource);
			

			data = bf.toString();
			logger.debug("parSingdata::" + data);
		} catch (Exception e) {
			throw new HandleException(e);
		}
		return data;
	}
	
	// foreign-id="123455"
	public String ParsingReqPutID(ReqPutForID putId)
			throws HandleException {
		String data = null;
		try {
			String foreignId = putId.getForeignid();
			
			

			StringBuffer bf = new StringBuffer();
			bf.append("foreign-id="+foreignId);
			

			data = bf.toString();
			logger.debug("parSingdata::" + data);
		} catch (Exception e) {
			throw new HandleException(e);
		}
		return data;
	}
	
	//ip-addr=127.0.0.2
	public String ParsingReqPutIP(ReqPutIP putIP)
			throws HandleException {
		String data = null;
		try {
			String IPaddr = putIP.getIpaddr();
			
			

			StringBuffer bf = new StringBuffer();
			bf.append("ip-addr="+IPaddr);
			

			data = bf.toString();
			logger.debug("parSingdata::" + data);
		} catch (Exception e) {
			throw new HandleException(e);
		}
		return data;
	}
}






