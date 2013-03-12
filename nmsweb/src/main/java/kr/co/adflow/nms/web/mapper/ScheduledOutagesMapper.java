package kr.co.adflow.nms.web.mapper;

import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.vo.SchoedOutage;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * ScheduledOutagesMapper
 * 
 * @author kicho@adflow.co.kr
 * @version 1.2
 */

public class ScheduledOutagesMapper {
	
	private static final Logger logger = LoggerFactory
			.getLogger(ScheduledOutagesMapper.class);
	

	public SchoedOutage schoedOutageMapping(String jdata) throws MapperException {

		SchoedOutage schoedOutage = new SchoedOutage();

		try {
			ObjectMapper om = new ObjectMapper();
			
			JsonNode jNode = om.readTree(jdata);
			
			schoedOutage.setType(jNode.path("@type").getTextValue());
			schoedOutage.setName(jNode.path("@name").getTextValue());
			
			SchoedOutage.Time sTime = new SchoedOutage.Time();
			
			sTime.setEnds(jNode.path("time").path("@ends").getTextValue());
			sTime.setBegins(jNode.path("time").path("@begins").getTextValue());
			
			schoedOutage.setTime(sTime);
			
			
			SchoedOutage.Node sNode = new SchoedOutage.Node();
			
			sNode.setId(jNode.path("node").path("@id").getTextValue());

			schoedOutage.setNode(sNode);

		} catch (Exception e) {
			throw new MapperException(e);
		}

		return schoedOutage;
	}
	
	public String schoedOutagePostMapping(SchoedOutage schoedOutage) throws MapperException {

		StringBuffer xml = new StringBuffer();

		try {
			xml.append("<outage type=\"");
			xml.append(schoedOutage.getType());
			xml.append("\" name=\"");
			xml.append(schoedOutage.getName());
			xml.append("\"><time ends=\"");
			xml.append(schoedOutage.getTime().getEnds());
			xml.append("\"  begins=\"");
			xml.append(schoedOutage.getTime().getEnds());
			xml.append("\"/><node id=\"");
			xml.append(schoedOutage.getNode().getId());
			xml.append("\"/></outage>");
			


		} catch (Exception e) {
			throw new MapperException(e);
		}
		
		return xml.toString();
	}
	
	

}
