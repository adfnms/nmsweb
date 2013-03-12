package kr.co.adflow.nms.web.mapper;

import java.util.Iterator;

import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.vo.discoveryConfiguration.DiscoveryConfiguration;
import kr.co.adflow.nms.web.vo.discoveryConfiguration.ExcludeRange;
import kr.co.adflow.nms.web.vo.discoveryConfiguration.IncludeRange;
import kr.co.adflow.nms.web.vo.discoveryConfiguration.Specific;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * ScheduledOutagesMapper
 * 
 * @author kicho@adflow.co.kr
 * @version 1.1
 */

@Service
public class DiscoveryConfigurationMapper {

	private static final Logger logger = LoggerFactory
			.getLogger(DiscoveryConfigurationMapper.class);

	public DiscoveryConfiguration discoveryConfigurationMapping(String jdata) throws MapperException {

		DiscoveryConfiguration dConfig = new DiscoveryConfiguration();

		try {
			ObjectMapper om = new ObjectMapper();

			JsonNode jNode = om.readTree(jdata);
			
			
			
			logger.debug("int :::"+jNode.path("threads").getIntValue());
			logger.debug("string  :::"+jNode.path("threads").getTextValue());
			
			dConfig.setThreads(Integer.parseInt(jNode.path("threads").getTextValue()));
			dConfig.setPacketsPerSecond(Integer.parseInt(jNode.path("packetsPerSecond").getTextValue()));
			dConfig.setInitialSleepTime(Long.parseLong(jNode.path("initialSleepTime").getTextValue()));
			dConfig.setRestartSleepTime(Long.parseLong(jNode.path("restartSleepTime").getTextValue()));
			dConfig.setRetries(Integer.parseInt(jNode.path("retries").getTextValue()));
			dConfig.setTimeout(Long.parseLong(jNode.path("timeout").getTextValue()));
			
			if (jNode.path("specific").isArray()) {
				
				Iterator<JsonNode> it = jNode.path("specific").iterator();
				
				while (it.hasNext()) {
					JsonNode temp = (JsonNode) it.next();
					Specific spec = new Specific();
					
					
					
					spec.setRetries(Integer.parseInt(temp.path("retries").getTextValue()));
					spec.setTimeout(Long.parseLong(temp.path("timeout").getTextValue()));
					spec.setValue(temp.path("value").getTextValue());
					
					dConfig.getSpecific().add(spec);
					
				}
				
			}
			
			if (jNode.path("includeRange").isArray()) {
				
				Iterator<JsonNode> it = jNode.path("includeRange").iterator();
				
				while (it.hasNext()) {
					JsonNode temp = (JsonNode) it.next();
					IncludeRange inRa = new IncludeRange();
					
					inRa.setRetries(Integer.parseInt(temp.path("retries").getTextValue()));
					inRa.setTimeout(Long.parseLong(temp.path("timeout").getTextValue()));
					inRa.setBegin(temp.path("begin").getTextValue());
					inRa.setEnd(temp.path("end").getTextValue());
					
					dConfig.getIncludeRange().add(inRa);
					
				}
				
			}
			
			if (jNode.path("excludeRange").isArray()) {
				
				Iterator<JsonNode> it = jNode.path("excludeRange").iterator();
				
				while (it.hasNext()) {
					JsonNode temp = (JsonNode) it.next();
					ExcludeRange exRa = new ExcludeRange();
					
					exRa.setBegin(temp.path("begin").getTextValue());
					exRa.setEnd(temp.path("end").getTextValue());
					
					dConfig.getExcludeRange().add(exRa);
					
				}
				
			}
			
	

		} catch (Exception e) {
			throw new MapperException(e);
		}

		return dConfig;
	}
	




}
