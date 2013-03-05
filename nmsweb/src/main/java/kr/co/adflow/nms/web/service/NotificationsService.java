package kr.co.adflow.nms.web.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import kr.co.adflow.nms.web.DefaultHandlerImpl;
import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.HandlerFactory;
import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.util.XmlUtil;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.adflow.nms.web.vo.DestPath.DestinationPaths;
import kr.co.adflow.nms.web.vo.DestPath.Path;
import kr.co.adflow.nms.web.vo.eventconf.Events;
import kr.co.adflow.nms.web.vo.notifdConfiguration.NotifdConfiguration;
import kr.co.adflow.nms.web.vo.notificationCommands.NotificationCommands;
import kr.co.adflow.nms.web.vo.notifications.Notification;
import kr.co.adflow.nms.web.vo.notifications.Notifications;

/**
 * NotificationsProcess
 * 
 * @author kicho@adflow.co.kr
 * @version 1.1
 */
@Service
public class NotificationsService {

	//private static final String XMLPATH = "d:\\OpenNMS\\etc\\";

	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String Accept = "accept";
	private @Value("#{config['XMLPATH']}") String xmlPath;
	private @Value("#{config['NMSURL']}") String ipAddr;
	private static final Logger logger = LoggerFactory
			.getLogger(NotificationsService.class);

	@Autowired
	private Handler handler;

	public String notificationsFilter(String filter) throws HandleException {
	
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/notifications?" + filter);
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String notifications() throws HandleException {
	
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/notifications?limit=0");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String notifications(String id) throws HandleException {
		
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/notifications/" + id);
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String notificationsCount() throws HandleException {
	
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, ipAddr + "/notifications/count");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String notificationDestinationPaths() throws HandleException {

		StringBuffer result = new StringBuffer();
		;

		try {
			// xml Read
			XmlUtil xUtil = new XmlUtil();
			String filePath = xmlPath + "destinationPaths.xml";

			Class<DestinationPaths> classname = DestinationPaths.class;
			DestinationPaths dPath = new DestinationPaths();

			Object ob = xUtil.xmlRead(filePath, classname, dPath);

			dPath = (DestinationPaths) ob;
			int size = dPath.getPath().size();

			logger.debug("size::" + size);

			result.append("{\"path\":[");

			for (int i = 0; i < size; i++) {
				logger.debug("getName::" + dPath.getPath().get(i).getName());

				result.append("{\"name\":\"" + dPath.getPath().get(i).getName()
						+ "\"},");

			}

			// last "," delete
			if (size > 0) {
				result.deleteCharAt(result.length() - 1);
			}

			result.append("]}");

			logger.debug("result::" + result.toString());

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result.toString();
	}

	public String notificationDestinationPaths(String pathName)
			throws HandleException {

		String result = null;
		Path path = new Path();

		try {
			// xml Read
			XmlUtil xUtil = new XmlUtil();
			String filePath = xmlPath + "destinationPaths.xml";

			Class<DestinationPaths> classname = DestinationPaths.class;
			DestinationPaths dPath = new DestinationPaths();

			Object ob = xUtil.xmlRead(filePath, classname, dPath);

			dPath = (DestinationPaths) ob;
			int size = dPath.getPath().size();

			boolean dupCheak = true;

			for (int i = 0; i < size; i++) {

				if (pathName.equals(dPath.getPath().get(i).getName())) {

					path = dPath.getPath().get(i);
				}

			}

			if (dupCheak) {

				ObjectMapper om = new ObjectMapper();

				try {

					result = om.defaultPrettyPrintingWriter()
							.writeValueAsString(path);
					logger.debug("result ::" + result);

				} catch (JsonGenerationException e) {
					throw new HandleException(e);
				} catch (JsonMappingException e) {
					throw new HandleException(e);
				} catch (IOException e) {
					throw new HandleException(e);
				}

			} else {
				logger.error("Nofi :: Path name(" + pathName + ") Not Found");
				throw new HandleException("Nofi :: Path name(" + pathName
						+ ") Not Found");
			}

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String notificationDestinationPathsPost(Path path)
			throws HandleException {

		String result = null;

		try {
			// xml Read
			XmlUtil xUtil = new XmlUtil();
			String filePath = xmlPath + "destinationPaths.xml";

			Class<DestinationPaths> classname = DestinationPaths.class;
			DestinationPaths dPath = new DestinationPaths();

			Object ob = xUtil.xmlRead(filePath, classname, dPath);

			dPath = (DestinationPaths) ob;
			int size = dPath.getPath().size();

			boolean dupCheak = true;

			for (int i = 0; i < size; i++) {

				if (path.getName().equals(dPath.getPath().get(i).getName())) {

					dupCheak = false;
				}

			}

			if (dupCheak) {

				dPath.getPath().add(path);

				// xml Write
				// filePath = "d:\\OpenNMS\\etc\\destinationPaths3.xml";
				result = xUtil.xmlWrite(filePath, classname, dPath);

			} else {
				logger.error("Nofi :: Path name(" + path.getName()
						+ ") Duplicate Error");
				throw new HandleException("Nofi :: Path name(" + path.getName()
						+ ") Duplicate Error");
			}

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String notificationDestinationPathsPut(Path path)
			throws HandleException {

		String result = null;

		try {
			// xml Read
			XmlUtil xUtil = new XmlUtil();
			String filePath = xmlPath + "destinationPaths.xml";

			Class<DestinationPaths> classname = DestinationPaths.class;
			DestinationPaths dPath = new DestinationPaths();

			Object ob = xUtil.xmlRead(filePath, classname, dPath);

			dPath = (DestinationPaths) ob;
			int size = dPath.getPath().size();

			boolean dupCheak = false;

			for (int i = 0; i < size; i++) {

				if (path.getName().equals(dPath.getPath().get(i).getName())) {

					dPath.getPath().remove(i);
					dPath.getPath().add(path);

					logger.debug("put:::" + dPath.getPath().get(i).getName());

					dupCheak = true;
					continue;
				}

			}

			if (dupCheak) {
				// xml Write
				// filePath = "d:\\OpenNMS\\etc\\destinationPaths3.xml";
				result = xUtil.xmlWrite(filePath, classname, dPath);

			} else {
				logger.error("Nofi :: Path name(" + path.getName()
						+ ") Not found");
				throw new HandleException("Nofi :: Path name(" + path.getName()
						+ ") Not found");
			}

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String notificationDestinationPathsDelete(String pathName)
			throws HandleException {

		String result = null;

		try {
			// xml Read
			XmlUtil xUtil = new XmlUtil();
			String filePath = xmlPath + "destinationPaths.xml";

			Class<DestinationPaths> classname = DestinationPaths.class;
			DestinationPaths dPath = new DestinationPaths();

			Object ob = xUtil.xmlRead(filePath, classname, dPath);

			dPath = (DestinationPaths) ob;
			int size = dPath.getPath().size();

			boolean dupCheak = false;

			for (int i = 0; i < size; i++) {

				if (pathName.equals(dPath.getPath().get(i).getName())) {

					logger.debug("delete:::" + dPath.getPath().get(i).getName());

					dPath.getPath().remove(i);
					dupCheak = true;
					continue;
				}

			}

			if (dupCheak) {
				result = xUtil.xmlWrite(filePath, classname, dPath);

			} else {
				logger.error("Nofi :: Path name(" + pathName + ") Not found");
				throw new HandleException("Nofi :: Path name(" + pathName
						+ ") Not found");
			}

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String notificationCommands() throws HandleException {

		StringBuffer result = new StringBuffer();
		;

		try {
			// xml Read
			XmlUtil xUtil = new XmlUtil();
			String filePath = xmlPath + "notificationCommands.xml";

			Class<NotificationCommands> classname = NotificationCommands.class;
			NotificationCommands nComm = new NotificationCommands();

			Object ob = xUtil.xmlRead(filePath, classname, nComm);

			nComm = (NotificationCommands) ob;
			int size = nComm.getCommand().size();

			logger.debug("size::" + size);

			result.append("{\"command\":[");

			for (int i = 0; i < size; i++) {
				logger.debug("getName::" + nComm.getCommand().get(i).getName());

				result.append("{\"name\":\""
						+ nComm.getCommand().get(i).getName() + "\"},");

			}

			// last "," delete
			if (size > 0) {
				result.deleteCharAt(result.length() - 1);
			}

			result.append("]}");

			logger.debug("result::" + result.toString());

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result.toString();
	}

	public String notificationEvents() throws HandleException {

		StringBuffer result = new StringBuffer();
		;

		try {
			// xml Read
			XmlUtil xUtil = new XmlUtil();
			String filePath = xmlPath + "eventconf.xml";

			Class<Events> classname = Events.class;
			Events events = new Events();

			Object ob = xUtil.xmlRead(filePath, classname, events);

			events = (Events) ob;

			int fileSize = events.getEventFile().size();

			for (int i = 0; i < fileSize; i++) {

				String fileName = "d:\\OpenNMS\\etc\\"
						+ events.getEventFile().get(i).toString();

				logger.debug("Event sub file::" + fileName);

				Object fileOb = xUtil.xmlRead(fileName, classname, events);

				Events eventSub = (Events) fileOb;

				events.getEvent().addAll(eventSub.getEvent());

			}

			int size = events.getEvent().size();

			logger.debug("Event size::" + size);

			result.append("{\"event\":[");

			for (int i = 0; i < size; i++) {
				// logger.debug("getUei::"+events.getEvent().get(i).getUei());

				result.append("{\"uei\":\"" + events.getEvent().get(i).getUei()
						+ "\",");
				result.append("\"event-label\":\""
						+ events.getEvent().get(i).getUei() + "\"},");

			}

			// last "," delete
			if (size > 0) {
				result.deleteCharAt(result.length() - 1);
			}

			result.append("]}");

			logger.debug("result::" + result.toString());

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result.toString();
	}

	public String notificationEventNotifications() throws HandleException {

		String result = null;

		try {
			// xml Read
			XmlUtil xUtil = new XmlUtil();
			String filePath = xmlPath + "notifications.xml";

			Class<Notifications> classname = Notifications.class;
			Notifications noti = new Notifications();

			Object ob = xUtil.xmlRead(filePath, classname, noti);

			noti = (Notifications) ob;

			ObjectMapper om = new ObjectMapper();

			try {

				result = om.defaultPrettyPrintingWriter().writeValueAsString(
						noti.getNotification());

				logger.debug("result ::" + result);

			} catch (JsonGenerationException e) {
				throw new HandleException(e);
			} catch (JsonMappingException e) {
				throw new HandleException(e);
			} catch (IOException e) {
				throw new HandleException(e);
			}

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String notificationEventNotifications(String notificationName)
			throws HandleException {

		String result = null;
		Notification noti = new Notification();

		try {
			// xml Read
			XmlUtil xUtil = new XmlUtil();
			String filePath = xmlPath + "notifications.xml";

			Class<Notifications> classname = Notifications.class;
			Notifications eNoti = new Notifications();

			Object ob = xUtil.xmlRead(filePath, classname, eNoti);

			eNoti = (Notifications) ob;
			int size = eNoti.getNotification().size();

			boolean dupCheak = true;

			for (int i = 0; i < size; i++) {

				if (notificationName.equals(eNoti.getNotification().get(i)
						.getName())) {

					noti = eNoti.getNotification().get(i);
				}

			}

			if (dupCheak) {

				ObjectMapper om = new ObjectMapper();

				try {

					result = om.defaultPrettyPrintingWriter()
							.writeValueAsString(noti);
					logger.debug("result ::" + result);

				} catch (JsonGenerationException e) {
					throw new HandleException(e);
				} catch (JsonMappingException e) {
					throw new HandleException(e);
				} catch (IOException e) {
					throw new HandleException(e);
				}

			} else {
				logger.error("Nofi :: Event Notifications name("
						+ notificationName + ") Not Found");
				throw new HandleException("Nofi :: Event Notifications name("
						+ notificationName + ") Not Found");
			}

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String notificationsEventNotificationsPost(Notification noti)
			throws HandleException {

		String result = null;

		try {
			// xml Read
			XmlUtil xUtil = new XmlUtil();
			String filePath = xmlPath + "notifications.xml";

			Class<Notifications> classname = Notifications.class;
			Notifications eNotis = new Notifications();

			Object ob = xUtil.xmlRead(filePath, classname, eNotis);

			eNotis = (Notifications) ob;
			int size = eNotis.getNotification().size();

			boolean dupCheak = true;

			for (int i = 0; i < size; i++) {

				if (noti.getName().equals(
						eNotis.getNotification().get(i).getName())) {

					dupCheak = false;
				}

			}

			if (dupCheak) {

				eNotis.getNotification().add(noti);

				// xml Write
				// filePath = "d:\\OpenNMS\\etc\\destinationPaths3.xml";
				result = xUtil.xmlWrite(filePath, classname, eNotis);

			} else {
				logger.error("Nofi :: Event Notification name("
						+ noti.getName() + ") Duplicate Error");
				throw new HandleException("Nofi :: Event Notification name("
						+ noti.getName() + ") Duplicate Error");
			}

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String notificationsEventNotificationsPut(Notification noti)
			throws HandleException {

		String result = null;

		try {
			// xml Read
			XmlUtil xUtil = new XmlUtil();
			String filePath = xmlPath + "notifications.xml";

			Class<Notifications> classname = Notifications.class;
			Notifications eNotis = new Notifications();

			Object ob = xUtil.xmlRead(filePath, classname, eNotis);

			eNotis = (Notifications) ob;
			int size = eNotis.getNotification().size();

			boolean dupCheak = false;

			for (int i = 0; i < size; i++) {

				if (noti.getName().equals(
						eNotis.getNotification().get(i).getName())) {

					eNotis.getNotification().remove(i);
					eNotis.getNotification().add(noti);

					logger.debug("put:::"
							+ eNotis.getNotification().get(i).getName());

					dupCheak = true;
					continue;
				}

			}

			if (dupCheak) {
				// xml Write
				// filePath = "d:\\OpenNMS\\etc\\destinationPaths3.xml";
				result = xUtil.xmlWrite(filePath, classname, eNotis);

			} else {
				logger.error("Nofi :: Event Notification name("
						+ noti.getName() + ") Not found");
				throw new HandleException("Nofi :: Event Notification name("
						+ noti.getName() + ") Not found");
			}

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String notificationsEventNotificationsDelete(String notificationName)
			throws HandleException {

		String result = null;

		try {
			// xml Read
			XmlUtil xUtil = new XmlUtil();
			String filePath = xmlPath + "notifications.xml";

			Class<Notifications> classname = Notifications.class;
			Notifications eNotis = new Notifications();

			Object ob = xUtil.xmlRead(filePath, classname, eNotis);

			eNotis = (Notifications) ob;
			int size = eNotis.getNotification().size();

			boolean dupCheak = false;

			for (int i = 0; i < size; i++) {

				if (notificationName.equals(eNotis.getNotification().get(i)
						.getName())) {

					logger.debug("delete:::"
							+ eNotis.getNotification().get(i).getName());

					eNotis.getNotification().remove(i);
					dupCheak = true;
					continue;
				}

			}

			if (dupCheak) {
				result = xUtil.xmlWrite(filePath, classname, eNotis);

			} else {
				logger.error("Nofi :: Event Notification name("
						+ notificationName + ") Not found");
				throw new HandleException("Nofi :: Event Notification name("
						+ notificationName + ") Not found");
			}

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String notificationsEventNotificationsStatusPut(
			String notificationName, String status) throws HandleException {

		String result = null;

		try {
			// xml Read
			XmlUtil xUtil = new XmlUtil();
			String filePath = xmlPath + "notifications.xml";

			Class<Notifications> classname = Notifications.class;
			Notifications eNotis = new Notifications();

			Object ob = xUtil.xmlRead(filePath, classname, eNotis);

			eNotis = (Notifications) ob;
			int size = eNotis.getNotification().size();

			boolean dupCheak = false;

			for (int i = 0; i < size; i++) {

				if (notificationName.equals(eNotis.getNotification().get(i)
						.getName())) {

					eNotis.getNotification().get(i).setStatus(status);

					logger.debug("status:::"
							+ eNotis.getNotification().get(i).getStatus());

					dupCheak = true;
					continue;
				}

			}

			if (dupCheak) {
				// xml Write
				// filePath = "d:\\OpenNMS\\etc\\destinationPaths3.xml";
				result = xUtil.xmlWrite(filePath, classname, eNotis);

			} else {
				logger.error("Nofi :: Event Notification name("
						+ notificationName + ") Not found");
				throw new HandleException("Nofi :: Event Notification name("
						+ notificationName + ") Not found");
			}

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String notificationsConfigStatusPut(String status) throws HandleException {

		String result = null;

		try {
			// xml Read
			XmlUtil xUtil = new XmlUtil();
			String filePath = xmlPath + "notifd-configuration.xml";

			Class<NotifdConfiguration> classname = NotifdConfiguration.class;
			NotifdConfiguration notiConfig = new NotifdConfiguration();

			Object ob = xUtil.xmlRead(filePath, classname, notiConfig);

			notiConfig = (NotifdConfiguration) ob;

			notiConfig.setStatus(status);

			// xml Write
			// filePath = "d:\\OpenNMS\\etc\\destinationPaths3.xml";
			result = xUtil.xmlWrite(filePath, classname, notiConfig);

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

}
