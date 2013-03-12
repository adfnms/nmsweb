package kr.co.adflow.nms.web.mapper;

import java.util.Iterator;

import kr.co.adflow.nms.web.exception.MapperException;

import kr.co.adflow.nms.web.vo.DestPath.Path;
import kr.co.adflow.nms.web.vo.DestPath.Target;
import kr.co.adflow.nms.web.vo.notifications.Notification;
import kr.co.adflow.nms.web.vo.notifications.Parameter;
import kr.co.adflow.nms.web.vo.notifications.Varbind;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * ScheduledOutagesMapper
 * 
 * @author kicho@adflow.co.kr
 * @version 1.2
 */

@Service
public class NotificationMapper {

	private static final Logger logger = LoggerFactory
			.getLogger(NotificationMapper.class);


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
	
	public Notification eventNotificationMapping(String jdata) throws MapperException {

		Notification noti = new Notification();

		try {
			ObjectMapper om = new ObjectMapper();

			JsonNode jNode = om.readTree(jdata);

			logger.debug("name::" + jNode.path("name").getTextValue());

			noti.setName(jNode.path("name").getTextValue());
			noti.setDescription(jNode.path("description").getTextValue());
			noti.setSubject(jNode.path("subject").getTextValue());
			noti.setStatus(jNode.path("status").getTextValue());
			noti.setRule(jNode.path("rule").getTextValue());
			noti.setWriteable(jNode.path("writeable").getTextValue());
			noti.setUei(jNode.path("uei").getTextValue());
			noti.setNoticeQueue(jNode.path("noticeQueue").getTextValue());
			noti.setDestinationPath(jNode.path("destinationPath").getTextValue());
			noti.setTextMessage(jNode.path("textMessage").getTextValue());
			noti.setNumericMessage(jNode.path("numericMessage").getTextValue());
			noti.setEventSeverity(jNode.path("eventSeverity").getTextValue());

			

			if (jNode.path("parameter").isArray()) {

				JsonNode temp = null;
				JsonNode temp2 = null;
				Iterator<JsonNode> it = jNode.path("parameter").iterator();

				int size = jNode.path("parameter").size();

				for (int i = 0; i < size; i++) {

					Parameter parameter = new Parameter();
					temp = it.next();
					
					parameter.setName(temp.path("name").getTextValue());
					parameter.setValue(temp.path("value").getTextValue());
					noti.getParameter().add(parameter);
				}

			}
			
			Varbind varbind = new Varbind();
			
			varbind.setVbname(jNode.path("varbind").path("vbname").getTextValue());
			varbind.setVbvalue(jNode.path("varbind").path("vbvalue").getTextValue());
			
			noti.setVarbind(varbind);
			
			

		} catch (Exception e) {
			throw new MapperException(e);
		}

		return noti;
	}



}
