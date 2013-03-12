package kr.co.adflow.nms.web.mapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.exception.MapperException;

import kr.co.adflow.nms.web.vo.AssetRecord;
import kr.co.adflow.nms.web.vo.Category;
import kr.co.adflow.nms.web.vo.IpInterface;
import kr.co.adflow.nms.web.vo.Node;
import kr.co.adflow.nms.web.vo.PathOutage;
import kr.co.adflow.nms.web.vo.SchoedOutage;
import org.springframework.stereotype.Service;
import kr.co.adflow.nms.web.vo.SnmpInterface;
import kr.co.adflow.nms.web.vo.DestPath.Path;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * ScheduledOutagesMapper
 * 
 * @author kicho@adflow.co.kr
 * @version 1.2
 */

@Service
public class PathOutagesMapper {
	
	private static final Logger logger = LoggerFactory
			.getLogger(PathOutagesMapper.class);
	

	public List<PathOutage> pathOutagesMapper(String jdata) throws MapperException {

		
		
		List<PathOutage> pathOutageList = new ArrayList<PathOutage>();

		try {
			ObjectMapper om = new ObjectMapper();
			
			JsonNode jNode = om.readTree(jdata);
			
			
			
			
			if (jNode.isArray()) {
				
				JsonNode temp = null;
				Iterator<JsonNode> it = jNode.iterator();
				int count = jNode.size();
				
				logger.debug("count::"+count);
				
				while (it.hasNext()) {
					
					PathOutage pathOutage = new PathOutage();
					temp = it.next();
					
					pathOutage.setNodeid(temp.path("nodeid").getTextValue());
					pathOutage.setCriticalpathip(temp.path("criticalpathip").getTextValue());
					pathOutage.setCriticalpathservicename(temp.path("criticalpathservicename").getTextValue());
					
					pathOutageList.add(pathOutage);
					
					logger.debug("pathOutage[] NodeId::"+pathOutage.getNodeid());
					
				}
				
			} else {
				PathOutage pathOutage = new PathOutage();
				pathOutage.setNodeid(jNode.path("nodeid").getTextValue());
				pathOutage.setCriticalpathip(jNode.path("criticalpathip").getTextValue());
				pathOutage.setCriticalpathservicename(jNode.path("criticalpathservicename").getTextValue());
				
				pathOutageList.add(pathOutage);
				
				logger.debug("pathOutage NodeId::"+pathOutage.getNodeid());

			}


		} catch (Exception e) {
			throw new MapperException(e);
		}

		return pathOutageList;
	}
	

}
