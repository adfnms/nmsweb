package kr.co.adflow.nms.web.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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

	// private static final String XMLPATH = "d:\\OpenNMS\\etc\\";

	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String Accept = "accept";
	private @Value("#{config['XMLPATH']}")
	String xmlPath;
	private @Value("#{config['NMSURL']}")
	String ipAddr;
	private @Value("#{config['LOGINID']}")
	String loginId;
	private @Value("#{config['LOGINPASS']}")
	String loginPass;
	private static final Logger logger = LoggerFactory
			.getLogger(NotificationsService.class);

	@Autowired
	private Handler handler;

	public String notificationsFilter(String filter) throws HandleException {

		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
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

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
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

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
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

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
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

			ObjectMapper om = new ObjectMapper();

			boolean check = true;

			for (int i = 0; i < size; i++) {

				if (pathName.equals(dPath.getPath().get(i).getName())) {

					path = dPath.getPath().get(i);

					result = om.defaultPrettyPrintingWriter()
							.writeValueAsString(path);
					check = false;
					break;
				}

			}

			if (check) {

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
					break;
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
					break;
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
					break;
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

				String fileName = xmlPath
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
						+ events.getEvent().get(i).getEventLabel() + "\"},");

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

			ObjectMapper om = new ObjectMapper();

			eNoti = (Notifications) ob;
			int size = eNoti.getNotification().size();

			boolean check = true;

			for (int i = 0; i < size; i++) {

				if (notificationName.equals(eNoti.getNotification().get(i)
						.getName())) {

					noti = eNoti.getNotification().get(i);

					result = om.defaultPrettyPrintingWriter()
							.writeValueAsString(noti);

					check = false;
					break;
				}

			}

			if (check) {

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
					break;
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
					break;
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
					break;
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

	public String notificationsConfigStatusPut(String status)
			throws HandleException {

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

	public String notificationsSearchUserCount(String userName)
			throws HandleException {

		StringBuffer result = new StringBuffer();

		Statement stmt = null;
		ResultSet rst = null;
		Connection conn = null;
		String sql = null;

		try {
			Context ctx = new InitialContext();
			if (ctx == null)
				throw new Exception("Boom - No Context");

			// /jdbc/postgres is the name of the resource above
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/postgres");
			if (ds != null) {
				conn = ds.getConnection();

				if (conn != null) {
					stmt = conn.createStatement();
					sql = "SELECT count(notifyid)"
							+ " FROM notifications WHERE answeredBy is null and notifyid in (SELECT DISTINCT usersnotified.notifyid FROM usersnotified WHERE usersnotified.userid='"
							+ userName + "')";

					logger.debug("sql:::" + sql);
					rst = stmt.executeQuery(sql);

					while (rst.next()) {

						result.append("{\"count\":\"" + rst.getInt(1) + "\"}");

					}
					rst.close();
					logger.debug("ResultSet Json:::" + result.toString());
				}
			}
		} catch (Exception e) {
			throw new HandleException(e);
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
		}

		return result.toString();
	}

	public String notificationsSearchUser(String userName, String pagetime,
			int limit) throws HandleException {

		StringBuffer resultTemp = new StringBuffer();
		String result = null;

		Statement stmt = null;
		ResultSet rst = null;
		Connection conn = null;
		String sql = null;

		try {
			Context ctx = new InitialContext();
			if (ctx == null)
				throw new Exception("Boom - No Context");

			// /jdbc/postgres is the name of the resource above
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/postgres");
			if (ds != null) {
				conn = ds.getConnection();

				if (conn != null) {
					stmt = conn.createStatement();
					sql = "SELECT notifyid, textmsg, subject, numericmsg, pagetime, respondtime, answeredby, nodeid, interfaceid, serviceid, queueid, eventid, eventuei, notifconfigname"
							+ " FROM notifications WHERE answeredBy is null and pagetime < '"
							+ pagetime
							+ "' and notifyid in (SELECT DISTINCT usersnotified.notifyid FROM usersnotified WHERE usersnotified.userid='"
							+ userName + "') Order by pagetime desc";

					logger.debug("sql:::" + sql);
					rst = stmt.executeQuery(sql);

					int count = 0;

					resultTemp.append("{\"notifications\":[");
					while (rst.next()) {

						resultTemp.append("{\"notifyid\":\"" + rst.getInt(1)
								+ "\",");
						resultTemp.append("\"textmsg\":\"" + rst.getString(2)
								+ "\",");
						resultTemp.append("\"subject\":\"" + rst.getString(3)
								+ "\",");
						resultTemp.append("\"numericmsg\":\""
								+ rst.getString(4) + "\",");
						resultTemp.append("\"pagetime\":\"" + rst.getString(5)
								+ "\",");
						resultTemp.append("\"respondtime\":\""
								+ rst.getString(6) + "\",");
						resultTemp.append("\"answeredby\":\""
								+ rst.getString(7) + "\",");
						resultTemp.append("\"nodeid\":\"" + rst.getInt(8)
								+ "\",");
						resultTemp.append("\"interfaceid\":\""
								+ rst.getString(9) + "\",");
						resultTemp.append("\"serviceid\":\"" + rst.getInt(10)
								+ "\",");
						resultTemp.append("\"queueid\":\"" + rst.getString(11)
								+ "\",");
						resultTemp.append("\"eventid\":\"" + rst.getInt(12)
								+ "\",");
						resultTemp.append("\"eventuei\":\"" + rst.getString(13)
								+ "\",");
						resultTemp.append("\"notifconfigname\":\""
								+ rst.getString(14) + "\"},");

						count++;
						if (count >= limit) {
							break;
						}

					}

					rst.close();

					// last "&" delete.
					resultTemp.deleteCharAt(resultTemp.length() - 1);
					resultTemp.append("]}");

					result = resultTemp.toString();

					result = result.replaceAll("\n", "\\n")
							.replaceAll("\'", "\\'").replaceAll("\"", "\\\"")
							.replaceAll("\r", "\\r").replaceAll("\t", "\\t")
							.replaceAll("\b", "\\b").replaceAll("\f", "\\f");
					logger.debug("ResultSet Json:::" + result.toString());
				}
			}
		} catch (Exception e) {
			throw new HandleException(e);
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
		}
		if (result.equals("{\"notifications\":]}")) {
			result = "{\"notifications\":\"null\"}";
		}
		return result;
	}

	public String notificationsAllAckCount() throws HandleException {

		StringBuffer result = new StringBuffer();

		Statement stmt = null;
		ResultSet rst = null;
		Connection conn = null;
		String sql = null;

		try {
			Context ctx = new InitialContext();
			if (ctx == null)
				throw new Exception("Boom - No Context");

			// /jdbc/postgres is the name of the resource above
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/postgres");
			if (ds != null) {
				conn = ds.getConnection();

				if (conn != null) {
					stmt = conn.createStatement();
					sql = "SELECT count(notifyid)"
							+ " FROM notifications WHERE answeredBy is not null";

					logger.debug("sql:::" + sql);
					rst = stmt.executeQuery(sql);

					while (rst.next()) {

						result.append("{\"count\":\"" + rst.getInt(1) + "\"}");

					}
					rst.close();
					logger.debug("ResultSet Json:::" + result.toString());
				}
			}
		} catch (Exception e) {
			throw new HandleException(e);
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
		}

		return result.toString();
	}

	public String notificationsAllAck(String pagetime, int limit)
			throws HandleException {

		StringBuffer resultTemp = new StringBuffer();
		String result = null;

		Statement stmt = null;
		ResultSet rst = null;
		Connection conn = null;
		String sql = null;

		try {
			Context ctx = new InitialContext();
			if (ctx == null)
				throw new Exception("Boom - No Context");

			// /jdbc/postgres is the name of the resource above
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/postgres");
			if (ds != null) {
				conn = ds.getConnection();

				if (conn != null) {
					stmt = conn.createStatement();
					sql = "SELECT notifyid, textmsg, subject, numericmsg, pagetime, respondtime, answeredby, nodeid, interfaceid, serviceid, queueid, eventid, eventuei, notifconfigname"
							+ " FROM notifications WHERE answeredBy is not null and pagetime < '"
							+ pagetime + "' Order by pagetime desc";

					logger.debug("sql:::" + sql);
					rst = stmt.executeQuery(sql);

					int count = 0;

					resultTemp.append("{\"notifications\":[");
					while (rst.next()) {

						resultTemp.append("{\"notifyid\":\"" + rst.getInt(1)
								+ "\",");
						resultTemp.append("\"textmsg\":\"" + rst.getString(2)
								+ "\",");
						resultTemp.append("\"subject\":\"" + rst.getString(3)
								+ "\",");
						resultTemp.append("\"numericmsg\":\""
								+ rst.getString(4) + "\",");
						resultTemp.append("\"pagetime\":\"" + rst.getString(5)
								+ "\",");
						resultTemp.append("\"respondtime\":\""
								+ rst.getString(6) + "\",");
						resultTemp.append("\"answeredby\":\""
								+ rst.getString(7) + "\",");
						resultTemp.append("\"nodeid\":\"" + rst.getInt(8)
								+ "\",");
						resultTemp.append("\"interfaceid\":\""
								+ rst.getString(9) + "\",");
						resultTemp.append("\"serviceid\":\"" + rst.getInt(10)
								+ "\",");
						resultTemp.append("\"queueid\":\"" + rst.getString(11)
								+ "\",");
						resultTemp.append("\"eventid\":\"" + rst.getInt(12)
								+ "\",");
						resultTemp.append("\"eventuei\":\"" + rst.getString(13)
								+ "\",");
						resultTemp.append("\"notifconfigname\":\""
								+ rst.getString(14) + "\"},");

						count++;
						if (count >= limit) {
							break;
						}

					}

					rst.close();

					// last "&" delete.
					resultTemp.deleteCharAt(resultTemp.length() - 1);
					resultTemp.append("]}");

					result = resultTemp.toString();

					result = result.replaceAll("\n", "\\n")
							.replaceAll("\'", "\\'").replaceAll("\"", "\\\"")
							.replaceAll("\r", "\\r").replaceAll("\t", "\\t")
							.replaceAll("\b", "\\b").replaceAll("\f", "\\f");

					logger.debug("ResultSet Json:::" + result.toString());
				}
			}
		} catch (Exception e) {
			throw new HandleException(e);
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
		}
		String result2=result.toString();
		if (result2.equals("{\"notifications\":]}")) {
			result2 = "{\"notifications\":\"null\"}";
		}
		return result2;
	}

	public String notificationsAllOutstandCount() throws HandleException {

		StringBuffer result = new StringBuffer();

		Statement stmt = null;
		ResultSet rst = null;
		Connection conn = null;
		String sql = null;

		try {
			Context ctx = new InitialContext();
			if (ctx == null)
				throw new Exception("Boom - No Context");

			// /jdbc/postgres is the name of the resource above
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/postgres");
			if (ds != null) {
				conn = ds.getConnection();

				if (conn != null) {
					stmt = conn.createStatement();
					sql = "SELECT count(notifyid)"
							+ " FROM notifications WHERE answeredBy is null";

					logger.debug("sql:::" + sql);
					rst = stmt.executeQuery(sql);

					while (rst.next()) {

						result.append("{\"count\":\"" + rst.getInt(1) + "\"}");

					}
					rst.close();
					logger.debug("ResultSet Json:::" + result.toString());
				}
			}
		} catch (Exception e) {
			throw new HandleException(e);
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
		}

		return result.toString();
	}

	public String notificationsAllOutstand(String pagetime, int limit)
			throws HandleException {

		StringBuffer resultTemp = new StringBuffer();
		String result = null;

		Statement stmt = null;
		ResultSet rst = null;
		Connection conn = null;
		String sql = null;

		try {
			Context ctx = new InitialContext();
			if (ctx == null)
				throw new Exception("Boom - No Context");

			// /jdbc/postgres is the name of the resource above
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/postgres");
			if (ds != null) {
				conn = ds.getConnection();

				if (conn != null) {
					stmt = conn.createStatement();
					sql = "SELECT notifyid, textmsg, subject, numericmsg, pagetime, respondtime, answeredby, nodeid, interfaceid, serviceid, queueid, eventid, eventuei, notifconfigname"
							+ " FROM notifications WHERE answeredBy is null and pagetime < '"
							+ pagetime + "' Order by pagetime desc";

					logger.debug("sql:::" + sql);
					rst = stmt.executeQuery(sql);

					int count = 0;

					resultTemp.append("{\"notifications\":[");
					while (rst.next()) {

						resultTemp.append("{\"notifyid\":\"" + rst.getInt(1)
								+ "\",");
						resultTemp.append("\"textmsg\":\"" + rst.getString(2)
								+ "\",");
						resultTemp.append("\"subject\":\"" + rst.getString(3)
								+ "\",");
						resultTemp.append("\"numericmsg\":\""
								+ rst.getString(4) + "\",");
						resultTemp.append("\"pagetime\":\"" + rst.getString(5)
								+ "\",");
						resultTemp.append("\"respondtime\":\""
								+ rst.getString(6) + "\",");
						resultTemp.append("\"answeredby\":\""
								+ rst.getString(7) + "\",");
						resultTemp.append("\"nodeid\":\"" + rst.getInt(8)
								+ "\",");
						resultTemp.append("\"interfaceid\":\""
								+ rst.getString(9) + "\",");
						resultTemp.append("\"serviceid\":\"" + rst.getInt(10)
								+ "\",");
						resultTemp.append("\"queueid\":\"" + rst.getString(11)
								+ "\",");
						resultTemp.append("\"eventid\":\"" + rst.getInt(12)
								+ "\",");
						resultTemp.append("\"eventuei\":\"" + rst.getString(13)
								+ "\",");
						resultTemp.append("\"notifconfigname\":\""
								+ rst.getString(14) + "\"},");

						count++;
						if (count >= limit) {
							break;
						}

					}

					rst.close();

					// last "&" delete.
					resultTemp.deleteCharAt(resultTemp.length() - 1);
					resultTemp.append("]}");

					result = resultTemp.toString();

					result = result.replaceAll("\n", "\\n")
							.replaceAll("\'", "\\'").replaceAll("\"", "\\\"")
							.replaceAll("\r", "\\r").replaceAll("\t", "\\t")
							.replaceAll("\b", "\\b").replaceAll("\f", "\\f");

					logger.debug("ResultSet Json:::" + result.toString());
				}
			}
		} catch (Exception e) {
			throw new HandleException(e);
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
		}
		String result2 = result.toString();
		if (result2.equals("{\"notifications\":]}")) {
			result2 = "{\"notifications\":\"null\"}";
		}
		return result2;
	}

}
