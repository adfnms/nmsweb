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
import kr.co.adflow.nms.web.process.AlarmsProcess;
import kr.co.adflow.nms.web.vo.AssetRecord;
import kr.co.adflow.nms.web.vo.Category;
import kr.co.adflow.nms.web.vo.IpInterface;
import kr.co.adflow.nms.web.vo.Node;
import kr.co.adflow.nms.web.vo.SchoedOutage;
import kr.co.adflow.nms.web.vo.Service;
import kr.co.adflow.nms.web.vo.SnmpInterface;
import kr.co.adflow.nms.web.vo.DestPath.*;

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
 * @version 1.1
 */

public class DestinationPathMapper {

	private static final Logger logger = LoggerFactory
			.getLogger(AlarmsProcess.class);

	/**
	 * singleton
	 * 
	 */
	private DestinationPathMapper() {
	}

	public static DestinationPathMapper mapper = new DestinationPathMapper();

	public static DestinationPathMapper getMapper() {
		return mapper;
	}

	public Path destinationPathMapping(String jdata) throws MapperException {

		Path path = new Path();

		try {
			ObjectMapper om = new ObjectMapper();

			JsonNode jNode = om.readTree(jdata);

			logger.debug("name::" + jNode.path("name").getTextValue());

			path.setName(jNode.path("name").getTextValue());
			path.setInitialDelay(jNode.path("initial-delay").getTextValue());

			if (jNode.path("target").isArray()) {

				JsonNode temp = null;
				JsonNode temp2 = null;
				Iterator<JsonNode> it = jNode.path("target").iterator();

				int size = jNode.path("target").size();

				for (int i = 0; i < size; i++) {

					Target target = new Target();
					temp = it.next();
					target.setInterval(temp.path("interval").getTextValue());
					target.setAutoNotify(temp.path("autoNotify").getTextValue());
					target.setName(temp.path("name").getTextValue());

					if (temp.path("command").isArray()) {

						int size2 = temp.path("command").size();

						Iterator<JsonNode> it2 = temp.path("command").iterator();

						for (int j = 0; j < size2; j++) {

							temp2 = it2.next();
							target.getCommand().add(temp2.getTextValue());
							logger.debug("command33::" + temp2.getTextValue());

						}

					} else {
						target.getCommand().add(
								temp.path("command").getTextValue());
						logger.debug("command::"
								+ temp.path("command").getTextValue());
					}

					path.getTarget().add(target);
				}

			}

		} catch (Exception e) {
			throw new MapperException(e);
		}

		return path;
	}

	public String schoedOutagePostMapping(SchoedOutage schoedOutage)
			throws MapperException {

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
