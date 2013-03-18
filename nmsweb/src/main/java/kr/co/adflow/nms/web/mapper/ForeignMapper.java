package kr.co.adflow.nms.web.mapper;

import java.util.Iterator;

import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.vo.foreign.ForDetector;
import kr.co.adflow.nms.web.vo.foreign.ForParam;
import kr.co.adflow.nms.web.vo.foreign.ForPoliceS;
import kr.co.adflow.nms.web.vo.foreign.ForPutName;
import kr.co.adflow.nms.web.vo.foreign.ForeignInfo;
import kr.co.adflow.nms.web.vo.notifications.Parameter;
import kr.co.adflow.nms.web.vo.requisition.RequisitionsCategory;


import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ForeignMapper {
	private static final Logger logger = LoggerFactory
			.getLogger(ForeignMapper.class);


	// <foreign-source name="testzzzzz"/>
	// {"foreign-source":[{"name":"testzzzzz"}]}
	public ForeignInfo foreignInput(String data) throws MapperException {
		ForeignInfo foreig = new ForeignInfo();
		try {
			logger.debug("mappingdata::" + data);
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			if (jNode.path("foreign-source").isArray()) {
				JsonNode temp = null;

				Iterator<JsonNode> it = jNode.path("foreign-source").iterator();

				while (it.hasNext()) {
					logger.debug("mapper step...3");
					temp = it.next();
					foreig.setName(temp.path("name").getTextValue());

				}

				logger.debug("foreig.getName():::" + foreig.getName());
			}
		} catch (Exception e) {
			throw new MapperException(e);

		}
		return foreig;
	}

	// <detector
	// class="org.opennms.netmgt.provision.detector.simple.HttpDetector"
	// name="chan2"/>
	// foreignSources/{name}/detectors

	public ForDetector foreignDetectorMap(String data) throws MapperException {
		ForDetector detector = new ForDetector();
		try {
			logger.debug("mappingdata::" + data);
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			if (jNode.path("detector").isArray()) {
				JsonNode temp = null;

				Iterator<JsonNode> it = jNode.path("detector").iterator();

				while (it.hasNext()) {
					temp = it.next();
					detector.setIsclass(temp.path("class").getTextValue());
					detector.setName(temp.path("name").getTextValue());

				}

				logger.debug("detector.getName():::" + detector.getName());
			}
		} catch (Exception e) {
			throw new MapperException(e);

		}
		return detector;
	}

	// POST
	// foreignSources/{name}/policies
	// <policy
	// class="org.opennms.netmgt.provision.persist.policies.MatchingIpInterfacePolicy"
	// name="pol2chan">
	// <parameter value="DISABLE_COLLECTION" key="action"/><parameter
	// value="ALL_PARAMETERS" key="matchBehavior"/></policy>

	
	
	public ForPoliceS foreignPolicesMap(String data) throws MapperException {
		ForPoliceS polices = new ForPoliceS();
		try {
			logger.debug("mappingdata::" + data);
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			if (jNode.path("policy").isArray()) {
				JsonNode temp = null;

				Iterator<JsonNode> it = jNode.path("policy").iterator();

				while (it.hasNext()) {
					temp = it.next();
					polices.setIsclass(temp.path("class").getTextValue());
					polices.setName(temp.path("name").getTextValue());

					if (temp.path("parameter").isArray()) {
						JsonNode temp2 = null;

						Iterator<JsonNode> it2 = temp.path("parameter")
								.iterator();
						while (it2.hasNext()) {
							ForParam par=new ForParam();
							temp2 = it2.next();
							
							par.setKey(temp2.path("key").getTextValue());
							par.setValue(temp2.path("value").getTextValue());
							polices.getUser().add(par);
						}
					}
				}

				logger.debug("polices.getName():::" + polices.getName());
				

			}
		} catch (Exception e) {
			throw new MapperException(e);

		}
		return polices;
	}
	//{"name":"chan"}
	public ForPutName foreignPutName(String data) throws MapperException {
		ForPutName putName=new ForPutName();
		try {
			logger.debug("mappingdata::" + data);
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			jNode.path("name");

			
					putName.setName(jNode.path("name").getTextValue());

				logger.debug("putName.getName():::" + putName.getName());
			
		} catch (Exception e) {
			throw new MapperException(e);

		}
		return putName;
	}

}
