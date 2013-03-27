package kr.co.adflow.nms.web.mapper;

import java.util.HashMap;
import java.util.Iterator;

import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.vo.graph.GraphNodeList;
import kr.co.adflow.nms.web.vo.graph.GraphNodeVO;
import kr.co.adflow.nms.web.vo.servicesid.ServiceIdNameVo;
import kr.co.adflow.nms.web.vo.servicesid.ServiceVo;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ServiceIdNameMapper {

	private static final Logger logger = LoggerFactory
			.getLogger(ServiceIdNameMapper.class);

	public HashMap serviceInfo(String data) throws MapperException {

		ServiceVo serviceVo = new ServiceVo();
		HashMap map = new HashMap();
		try {
			logger.debug("mappingdata::" + data);
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			if (jNode.path("services").isArray()) {
				logger.debug("if..");
				JsonNode temp = null;
				Iterator<JsonNode> it = jNode.path("services").iterator();

				while (it.hasNext()) {
					ServiceIdNameVo vo = new ServiceIdNameVo();
					temp = it.next();
					vo.setServiceId(temp.path("serviceid").getTextValue());
					vo.setServiceName(temp.path("servicename").getTextValue());
					logger.debug("serviceID::"
							+ temp.path("serviceid").getTextValue());
					logger.debug("servicename::"
							+ temp.path("servicename").getTextValue());

					map.put(temp.path("serviceid").getTextValue(),
							temp.path("servicename").getTextValue());
				}
				logger.debug("test");
				// info.getServiceIdNameList().get(0).getServiceId();
			}
		} catch (Exception e) {
			throw new MapperException(e);

		}
		return map;
	}

	public ServiceVo serviceInfo2(String data) throws MapperException {

		ServiceVo serviceVo = new ServiceVo();
		HashMap map = new HashMap();
		try {
			logger.debug("mappingdata::" + data);
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			if (jNode.path("services").isArray()) {

				JsonNode temp = null;
				Iterator<JsonNode> it = jNode.path("services").iterator();

				while (it.hasNext()) {
					ServiceIdNameVo vo = new ServiceIdNameVo();
					temp = it.next();
					vo.setServiceId(temp.path("serviceid").getTextValue());
					vo.setServiceName(temp.path("servicename").getTextValue());

					serviceVo.getServiceList().add(vo);
				}

				// info.getServiceIdNameList().get(0).getServiceId();
			}
		} catch (Exception e) {
			throw new MapperException(e);

		}
		return serviceVo;
	}

}
