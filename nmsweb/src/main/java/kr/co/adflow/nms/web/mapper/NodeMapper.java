package kr.co.adflow.nms.web.mapper;

import java.util.ArrayList;
import java.util.Iterator;

import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.vo.AssetRecord;
import kr.co.adflow.nms.web.vo.Category;
import kr.co.adflow.nms.web.vo.IpInterface;
import kr.co.adflow.nms.web.vo.Node;
import kr.co.adflow.nms.web.vo.SnmpInterface;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

/**
 * nodeMapping
 * 
 * @author kicho@adflow.co.kr
 * @version 1.1
 */

@Service
public class NodeMapper {

	public Node nodeMapping(String jdata) throws MapperException {

		JsonFactory f = new JsonFactory();
		Node node = new Node();

		try {
			JsonParser jp = f.createJsonParser(jdata);
			jp.nextToken();
			while (jp.nextToken() != JsonToken.END_OBJECT) {
				String nodeField = jp.getCurrentName();
				jp.nextToken();

				if ("assetRecord".equals(nodeField)) {
					AssetRecord assect = new AssetRecord();
					while (jp.nextToken() != JsonToken.END_OBJECT) {
						String assetField = jp.getCurrentName();
						jp.nextToken();
						if ("category".equals(assetField)) {
							assect.setCategory(jp.getText());
						} else if ("displayCategory".equals(assetField)) {
							assect.setDisplayCategory(jp.getText());
						} else if ("lastModifiedBy".equals(assetField)) {
							assect.setLastModifiedBy(jp.getText());
						} else if ("lastModifiedDate".equals(assetField)) {
							assect.setLastModifiedDate(jp.getText());
						} else if ("node".equals(assetField)) {
							assect.setNode(jp.getText());
						} else if ("notifyCategory".equals(assetField)) {
							assect.setNotifyCategory(jp.getText());
						} else if ("pollerCategory".equals(assetField)) {
							assect.setPollerCategory(jp.getText());
						} else if ("thresholdCategory".equals(assetField)) {
							assect.setThresholdCategory(jp.getText());
						} else {
							throw new IllegalStateException(
									"Unrecognized field '" + assetField + "'!");
						}
					}

					node.setAssetRecord(assect);

				} else if ("@type".equals(nodeField)) {
					node.setType(jp.getText());
				} else if ("@id".equals(nodeField)) {
					node.setId(jp.getText());
				} else if ("@label".equals(nodeField)) {
					node.setLabel(jp.getText());
				} else if ("createTime".equals(nodeField)) {
					node.setCreateTime(jp.getText());
				} else if ("labelSource".equals(nodeField)) {
					node.setLabelSource(jp.getText());
				} else if ("lastCapsdPoll".equals(nodeField)) {
					node.setLastCapsdPoll(jp.getText());
				} else {
					throw new IllegalStateException("Unrecognized field '"
							+ nodeField + "'!");
				}
			}

		} catch (Exception e) {
			throw new MapperException(e);
		}

		return node;
	}

	public Node[] nodeMappingAll(String jdata) throws MapperException {

		Node[] node = null;
		int count = 0;

		try {
			ObjectMapper om = new ObjectMapper();
//			Map<String, Object> m = om.readValue(jdata, new TypeReference<Map<String, Object>>(){});
//			System.out.println("json to object : " + m);
			
			JsonNode jNode = om.readTree(jdata);
			
			System.out.println("b의 값 : " + jNode.path("@count").getTextValue());
			
			count = Integer.parseInt(jNode.path("@count").getTextValue());
			
			node = new Node[count];
			
			
			if (jNode.path("node").isArray()) {
				
				JsonNode temp = null;
				Iterator<JsonNode> it = jNode.path("node").iterator();

				for (int i = 0; i < count; i++) {
					
					
				    node[i] = new Node();
					temp = it.next();
					node[i].setType(temp.path("@type").getTextValue());
					node[i].setId(temp.path("@id").getTextValue());
					node[i].setLabel(temp.path("@label").getTextValue());
					node[i].setCreateTime(temp.path("createTime").getTextValue());
					node[i].setLabelSource(temp.path("labelSource").getTextValue());
					node[i].setLastCapsdPoll(temp.path("lastCapsdPoll").getTextValue());
					
					AssetRecord assect = new AssetRecord();
					
					assect.setCategory(temp.path("assetRecord").path("category").getTextValue());
					assect.setDisplayCategory(temp.path("assetRecord").path("displayCategory").getTextValue());
					assect.setLastModifiedBy(temp.path("assetRecord").path("lastModifiedBy").getTextValue());
					assect.setLastModifiedDate(temp.path("assetRecord").path("lastModifiedDate").getTextValue());
					assect.setNode(temp.path("assetRecord").path("node").getTextValue());
					assect.setNotifyCategory(temp.path("assetRecord").path("notifyCategory").getTextValue());
					assect.setPollerCategory(temp.path("assetRecord").path("pollerCategory").getTextValue());
					assect.setThresholdCategory(temp.path("assetRecord").path("thresholdCategory").getTextValue());
					
					node[i].setAssetRecord(assect);
						
						
					//	System.out.println(temp.path("assetRecord").path("category").getTextValue());
					//	System.out.println("afdaff :" + temp.path("@type").getTextValue());
					
				}
			} else{
				
			    node[0] = new Node();

				node[0].setType(jNode.path("node").path("@type").getTextValue());
				node[0].setId(jNode.path("node").path("@id").getTextValue());
				node[0].setLabel(jNode.path("node").path("@label").getTextValue());
				node[0].setCreateTime(jNode.path("node").path("createTime").getTextValue());
				node[0].setLabelSource(jNode.path("node").path("labelSource").getTextValue());
				node[0].setLastCapsdPoll(jNode.path("node").path("lastCapsdPoll").getTextValue());
				
				AssetRecord assect = new AssetRecord();
				
				assect.setCategory(jNode.path("node").path("assetRecord").path("category").getTextValue());
				assect.setDisplayCategory(jNode.path("node").path("assetRecord").path("displayCategory").getTextValue());
				assect.setLastModifiedBy(jNode.path("node").path("assetRecord").path("lastModifiedBy").getTextValue());
				assect.setLastModifiedDate(jNode.path("node").path("assetRecord").path("lastModifiedDate").getTextValue());
				assect.setNode(jNode.path("node").path("assetRecord").path("node").getTextValue());
				assect.setNotifyCategory(jNode.path("node").path("assetRecord").path("notifyCategory").getTextValue());
				assect.setPollerCategory(jNode.path("node").path("assetRecord").path("pollerCategory").getTextValue());
				assect.setThresholdCategory(jNode.path("node").path("assetRecord").path("thresholdCategory").getTextValue());
				
				node[0].setAssetRecord(assect);
			}
			




		} catch (Exception e) {
			throw new MapperException(e);
		}

		return node;
	}
	
	public IpInterface[] ipInterfaceMappingAll(String jdata) throws MapperException {

		IpInterface[] iface = null;
		int count = 0;

		try {
			ObjectMapper om = new ObjectMapper();
//			Map<String, Object> m = om.readValue(jdata, new TypeReference<Map<String, Object>>(){});
//			System.out.println("json to object : " + m);
			
			JsonNode jNode = om.readTree(jdata);
			
			count = Integer.parseInt(jNode.path("@count").getTextValue());
			
			iface = new IpInterface[count];
			
			if (jNode.path("ipInterface").isArray()) {
				
				JsonNode temp = null;
				Iterator<JsonNode> it = jNode.path("ipInterface").iterator();

				for (int i = 0; i < count; i++) {
					iface[i] = new IpInterface();
					temp = it.next();
					iface[i].setSnmpPrimary(temp.path("@snmpPrimary").getTextValue());
					iface[i].setMonitoredServiceCount(temp.path("@monitoredServiceCount").getTextValue());
					iface[i].setIsManaged(temp.path("@isManaged").getTextValue());
					iface[i].setIsDown(temp.path("@isDown").getTextValue());
					iface[i].setIpAddress(temp.path("ipAddress").getTextValue());
					iface[i].setHostName(temp.path("hostName").getTextValue());
					iface[i].setLastCapsdPoll(temp.path("lastCapsdPoll").getTextValue());
					iface[i].setNodeId(temp.path("nodeId").getTextValue());
				}
			} else{
				iface[0] = new IpInterface();
				iface[0].setSnmpPrimary(jNode.path("ipInterface").path("@snmpPrimary").getTextValue());
				iface[0].setMonitoredServiceCount(jNode.path("ipInterface").path("@monitoredServiceCount").getTextValue());
				iface[0].setIsManaged(jNode.path("ipInterface").path("@isManaged").getTextValue());
				iface[0].setIsDown(jNode.path("ipInterface").path("@isDown").getTextValue());
				iface[0].setIpAddress(jNode.path("ipInterface").path("ipAddress").getTextValue());
				iface[0].setHostName(jNode.path("ipInterface").path("hostName").getTextValue());
				iface[0].setLastCapsdPoll(jNode.path("ipInterface").path("lastCapsdPoll").getTextValue());
				iface[0].setNodeId(jNode.path("ipInterface").path("nodeId").getTextValue());
			}
			
		} catch (Exception e) {
			throw new MapperException(e);
		}
		return iface;
	}
	
	public IpInterface ipInterfaceMapping(String jdata) throws MapperException {

		IpInterface iface = new IpInterface();

		try {
			ObjectMapper om = new ObjectMapper();
//			Map<String, Object> m = om.readValue(jdata, new TypeReference<Map<String, Object>>(){});
//			System.out.println("json to object : " + m);
			
			JsonNode jNode = om.readTree(jdata);
			
			iface.setSnmpPrimary(jNode.path("@snmpPrimary").getTextValue());
			iface.setMonitoredServiceCount(jNode.path("@monitoredServiceCount").getTextValue());
			iface.setIsManaged(jNode.path("@isManaged").getTextValue());
			iface.setIsDown(jNode.path("@isDown").getTextValue());
			iface.setIpAddress(jNode.path("ipAddress").getTextValue());
			iface.setHostName(jNode.path("hostName").getTextValue());
			iface.setLastCapsdPoll(jNode.path("lastCapsdPoll").getTextValue());
			iface.setNodeId(jNode.path("nodeId").getTextValue());
				//	System.out.println(temp.path("assetRecord").path("category").getTextValue());
				//	System.out.println("afdaff :" + temp.path("@type").getTextValue());

		} catch (Exception e) {
			throw new MapperException(e);
		}

		return iface;
	}
	
	
	public kr.co.adflow.nms.web.vo.Service[] serviceMappingAll(String jdata) throws MapperException {

		kr.co.adflow.nms.web.vo.Service[] service = null;
		int count = 0;

		try {
			ObjectMapper om = new ObjectMapper();
//			Map<String, Object> m = om.readValue(jdata, new TypeReference<Map<String, Object>>(){});
//			System.out.println("json to object : " + m);
			
			JsonNode jNode = om.readTree(jdata);
			
			System.out.println("b의 값 : " + jNode.path("@count").getTextValue());
			
			count = Integer.parseInt(jNode.path("@count").getTextValue());
			
			service = new kr.co.adflow.nms.web.vo.Service[count];
			
			
			if (jNode.path("service").isArray()) {
				
				JsonNode temp = null;
				Iterator<JsonNode> it = jNode.path("service").iterator();

				for (int i = 0; i < count; i++) {
					
					System.out.println("111111 : "+ i);
					
					
					service[i] = new kr.co.adflow.nms.web.vo.Service();
					temp = it.next();
					service[i].setStatus(temp.path("@status").getTextValue());
					service[i].setSource(temp.path("@source").getTextValue());
					service[i].setId(temp.path("@id").getTextValue());
					
					System.out.println("222222 : " + temp.path("@id").getTextValue());
					
					service[i].setIpInterfaceId(temp.path("@ipInterfaceId").getTextValue());
					service[i].setNotify(temp.path("notify").getTextValue());
					
					
					kr.co.adflow.nms.web.vo.Service.ServiceType serviceType = new kr.co.adflow.nms.web.vo.Service.ServiceType();
					serviceType.setId(temp.path("serviceType").path("@id").getTextValue());
					serviceType.setId(temp.path("serviceType").path("name").getTextValue());
					
					service[i].setServiceType(serviceType);
					
				}
			} else{
				service[0] = new kr.co.adflow.nms.web.vo.Service();

				service[0].setStatus(jNode.path("service").path("@status").getTextValue());
				service[0].setSource(jNode.path("service").path("@source").getTextValue());
				service[0].setId(jNode.path("service").path("@id").getTextValue());
				service[0].setIpInterfaceId(jNode.path("service").path("@ipInterfaceId").getTextValue());
				service[0].setNotify(jNode.path("service").path("notify").getTextValue());
				
				
				kr.co.adflow.nms.web.vo.Service.ServiceType serviceType = new kr.co.adflow.nms.web.vo.Service.ServiceType();
				serviceType.setId(jNode.path("service").path("serviceType").path("@id").getTextValue());
				serviceType.setId(jNode.path("service").path("serviceType").path("name").getTextValue());
				
				service[0].setServiceType(serviceType);
			}


		} catch (Exception e) {
			throw new MapperException(e);
		}

		return service;
	}
	
	public kr.co.adflow.nms.web.vo.Service serviceMapping(String jdata) throws MapperException {

		kr.co.adflow.nms.web.vo.Service service = new kr.co.adflow.nms.web.vo.Service();

		try {
			ObjectMapper om = new ObjectMapper();
//			Map<String, Object> m = om.readValue(jdata, new TypeReference<Map<String, Object>>(){});
//			System.out.println("json to object : " + m);
			
			JsonNode jNode = om.readTree(jdata);
			
			service.setStatus(jNode.path("@status").getTextValue());
			service.setSource(jNode.path("@source").getTextValue());
			service.setId(jNode.path("@id").getTextValue());
			service.setIpInterfaceId(jNode.path("@ipInterfaceId").getTextValue());
			service.setNotify(jNode.path("notify").getTextValue());
			
			
			kr.co.adflow.nms.web.vo.Service.ServiceType serviceType = new kr.co.adflow.nms.web.vo.Service.ServiceType();
			serviceType.setId(jNode.path("serviceType").path("@id").getTextValue());
			serviceType.setId(jNode.path("serviceType").path("name").getTextValue());
			
			service.setServiceType(serviceType);
				//	System.out.println(temp.path("assetRecord").path("category").getTextValue());
				//	System.out.println("afdaff :" + temp.path("@type").getTextValue());

		} catch (Exception e) {
			throw new MapperException(e);
		}

		return service;
	}
	
	public SnmpInterface[] snmpInterfaceMappingAll(String jdata) throws MapperException {

		SnmpInterface[] snmpInterface = null;
		int count = 0;

		try {
			ObjectMapper om = new ObjectMapper();
//			Map<String, Object> m = om.readValue(jdata, new TypeReference<Map<String, Object>>(){});
//			System.out.println("json to object : " + m);
			
			JsonNode jNode = om.readTree(jdata);
			
			System.out.println("b의 값 : " + jNode.path("@count").getTextValue());
			
			count = Integer.parseInt(jNode.path("@count").getTextValue());
			
			snmpInterface = new SnmpInterface[count];
			
			
			if (jNode.path("snmpInterface").isArray()) {
				
				JsonNode temp = null;
				Iterator<JsonNode> it = jNode.path("snmpInterface").iterator();

				for (int i = 0; i < count; i++) {

					snmpInterface[i] = new SnmpInterface();
					temp = it.next();
					snmpInterface[i].setPoll(temp.path("@poll").getTextValue());
					snmpInterface[i].setPollFlag(temp.path("@pollFlag").getTextValue());
					snmpInterface[i].setIfIndex(temp.path("@ifIndex").getTextValue());
					snmpInterface[i].setId(temp.path("@id").getTextValue());
					snmpInterface[i].setCollect(temp.path("@collect").getTextValue());
					snmpInterface[i].setCollectFlag(temp.path("@collectFlag").getTextValue());
					snmpInterface[i].setIfAdminStatus(temp.path("ifAdminStatus").getTextValue());
					snmpInterface[i].setIfAlias(temp.path("ifAlias").getTextValue());
					snmpInterface[i].setIfDescr(temp.path("ifDescr").getTextValue());
					snmpInterface[i].setIfName(temp.path("ifName").getTextValue());
					snmpInterface[i].setIfOperStatus(temp.path("ifOperStatus").getTextValue());
					snmpInterface[i].setIfSpeed(temp.path("ifSpeed").getTextValue());
					snmpInterface[i].setIfType(temp.path("ifType").getTextValue());
					snmpInterface[i].setIpInterfaces(temp.path("ipInterfaces").getTextValue());
					snmpInterface[i].setNetMask(temp.path("netMask").getTextValue());
					snmpInterface[i].setNodeId(temp.path("nodeId").getTextValue());
					snmpInterface[i].setPhysAddr(temp.path("physAddr").getTextValue());
				}
			} else{
				snmpInterface[0] = new SnmpInterface();

				snmpInterface[0].setPoll(jNode.path("snmpInterface").path("@poll").getTextValue());
				snmpInterface[0].setPollFlag(jNode.path("snmpInterface").path("@pollFlag").getTextValue());
				snmpInterface[0].setIfIndex(jNode.path("snmpInterface").path("@ifIndex").getTextValue());
				snmpInterface[0].setId(jNode.path("snmpInterface").path("@id").getTextValue());
				snmpInterface[0].setCollect(jNode.path("snmpInterface").path("@collect").getTextValue());
				snmpInterface[0].setCollectFlag(jNode.path("snmpInterface").path("@collectFlag").getTextValue());
				snmpInterface[0].setIfAdminStatus(jNode.path("snmpInterface").path("ifAdminStatus").getTextValue());
				snmpInterface[0].setIfAlias(jNode.path("snmpInterface").path("ifAlias").getTextValue());
				snmpInterface[0].setIfDescr(jNode.path("snmpInterface").path("ifDescr").getTextValue());
				snmpInterface[0].setIfName(jNode.path("snmpInterface").path("ifName").getTextValue());
				snmpInterface[0].setIfOperStatus(jNode.path("snmpInterface").path("ifOperStatus").getTextValue());
				snmpInterface[0].setIfSpeed(jNode.path("snmpInterface").path("ifSpeed").getTextValue());
				snmpInterface[0].setIfType(jNode.path("snmpInterface").path("ifType").getTextValue());
				snmpInterface[0].setIpInterfaces(jNode.path("snmpInterface").path("ipInterfaces").getTextValue());
				snmpInterface[0].setNetMask(jNode.path("snmpInterface").path("netMask").getTextValue());
				snmpInterface[0].setNodeId(jNode.path("snmpInterface").path("nodeId").getTextValue());
				snmpInterface[0].setPhysAddr(jNode.path("snmpInterface").path("physAddr").getTextValue());
			}


		} catch (Exception e) {
			throw new MapperException(e);
		}

		return snmpInterface;
	}
	
	public SnmpInterface snmpInterfaceMapping(String jdata) throws MapperException {
		SnmpInterface snmpInterface = new SnmpInterface();

		try {
			ObjectMapper om = new ObjectMapper();
//			Map<String, Object> m = om.readValue(jdata, new TypeReference<Map<String, Object>>(){});
//			System.out.println("json to object : " + m);
			
			JsonNode jNode = om.readTree(jdata);
			
			snmpInterface.setPoll(jNode.path("@poll").getTextValue());
			snmpInterface.setPollFlag(jNode.path("@pollFlag").getTextValue());
			snmpInterface.setIfIndex(jNode.path("@ifIndex").getTextValue());
			snmpInterface.setId(jNode.path("@id").getTextValue());
			snmpInterface.setCollect(jNode.path("@collect").getTextValue());
			snmpInterface.setCollectFlag(jNode.path("@collectFlag").getTextValue());
			snmpInterface.setIfAdminStatus(jNode.path("ifAdminStatus").getTextValue());
			snmpInterface.setIfAlias(jNode.path("ifAlias").getTextValue());
			snmpInterface.setIfDescr(jNode.path("ifDescr").getTextValue());
			snmpInterface.setIfName(jNode.path("ifName").getTextValue());
			snmpInterface.setIfOperStatus(jNode.path("ifOperStatus").getTextValue());
			snmpInterface.setIfSpeed(jNode.path("ifSpeed").getTextValue());
			snmpInterface.setIfType(jNode.path("ifType").getTextValue());
			snmpInterface.setIpInterfaces(jNode.path("ipInterfaces").getTextValue());
			snmpInterface.setNetMask(jNode.path("netMask").getTextValue());
			snmpInterface.setNodeId(jNode.path("nodeId").getTextValue());
			snmpInterface.setPhysAddr(jNode.path("physAddr").getTextValue());

		} catch (Exception e) {
			throw new MapperException(e);
		}

		return snmpInterface;
	}
	
	public Category[] categoriesMappingAll(String jdata) throws MapperException {

		Category[] category = null;
		int count = 0;

		try {
			ObjectMapper om = new ObjectMapper();
//			Map<String, Object> m = om.readValue(jdata, new TypeReference<Map<String, Object>>(){});
//			System.out.println("json to object : " + m);
			
			JsonNode jNode = om.readTree(jdata);
			
			System.out.println("b의 값 : " + jNode.path("@count").getTextValue());
			
			count = Integer.parseInt(jNode.path("@count").getTextValue());
			
			category = new Category[count];
			
			
			if (jNode.path("category").isArray()) {
				
				JsonNode temp = null;
				Iterator<JsonNode> it = jNode.path("category").iterator();

				for (int i = 0; i < count; i++) {

					category[i] = new Category();
					temp = it.next();
					category[i].setName(temp.path("@name").getTextValue());
					category[i].setId(temp.path("@id").getTextValue());
					
				}
			} else{
				category[0] = new Category();

				category[0].setName(jNode.path("@name").getTextValue());
				category[0].setId(jNode.path("@id").getTextValue());
			}


		} catch (Exception e) {
			throw new MapperException(e);
		}

		return category;
	}
	
	public Category categoriesMapping(String jdata) throws MapperException {
		Category category = new Category();

		try {
			ObjectMapper om = new ObjectMapper();
//			Map<String, Object> m = om.readValue(jdata, new TypeReference<Map<String, Object>>(){});
//			System.out.println("json to object : " + m);
			
			JsonNode jNode = om.readTree(jdata);
			
			category.setName(jNode.path("@name").getTextValue());
			category.setId(jNode.path("@id").getTextValue());

		} catch (Exception e) {
			throw new MapperException(e);
		}

		return category;
	}
	
	public AssetRecord assetRecordMapping(String jdata) throws MapperException {
		AssetRecord assetRecord = new AssetRecord();

		try {
			ObjectMapper om = new ObjectMapper();
//			Map<String, Object> m = om.readValue(jdata, new TypeReference<Map<String, Object>>(){});
//			System.out.println("json to object : " + m);
			
			JsonNode jNode = om.readTree(jdata);
			
			assetRecord.setCategory(jNode.path("category").getTextValue());
			assetRecord.setDisplayCategory(jNode.path("displayCategory").getTextValue());
			assetRecord.setLastModifiedBy(jNode.path("lastModifiedBy").getTextValue());
			assetRecord.setLastModifiedDate(jNode.path("lastModifiedDate").getTextValue());
			assetRecord.setNode(jNode.path("node").getTextValue());
			assetRecord.setNotifyCategory(jNode.path("notifyCategory").getTextValue());
			assetRecord.setPollerCategory(jNode.path("pollerCategory").getTextValue());
			assetRecord.setThresholdCategory(jNode.path("thresholdCategory").getTextValue());

		} catch (Exception e) {
			throw new MapperException(e);
		}

		return assetRecord;
	}
	
	public ArrayList idList(String jdata) throws MapperException {
		
		ArrayList idList = new ArrayList();

		try {
			ObjectMapper om = new ObjectMapper();
//			Map<String, Object> m = om.readValue(jdata, new TypeReference<Map<String, Object>>(){});
//			System.out.println("json to object : " + m);
			
			JsonNode jNode = om.readTree(jdata);
			if (jNode.path("category").isArray()) {
				
				Iterator<JsonNode> it = jNode.path("category").iterator();
				
				while (it.hasNext()) {
					JsonNode temp = (JsonNode) it.next();
					idList.add(temp.path("id").getTextValue());
					
				}

			} else{
				
				idList.add(jNode.path("id").getTextValue());

			}


		} catch (Exception e) {
			throw new MapperException(e);
		}

		return idList;
	}
	
	


}
