package kr.co.adflow.nms.web.service;

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

/**
 * EventsProcess
 * 
 * @author kicho@adflow.co.kr
 * @version 1.1
 */
@Service
public class EventsService {

	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String Accept = "accept";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private @Value("#{config['NMSURL']}") String ipAddr;
	private @Value("#{config['LOGINID']}") String loginId;
	private @Value("#{config['LOGINPASS']}") String loginPass;
	private static final Logger logger = LoggerFactory
			.getLogger(EventsService.class);

	@Autowired
	private Handler handler;
	
	public String eventsFilter(String filter) throws HandleException {
	
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/events?"+filter);
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String events() throws HandleException {
	
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/events?limit=8");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String eventsCount() throws HandleException {
	
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(URL, ipAddr + "/events/count");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	public String events(String id) throws HandleException {
	
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/events/"+id);
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	public String eventquery(String eventseverity, String limit) throws HandleException {

		String result = null;
		StringBuffer resultTemp = new StringBuffer();
		StringBuffer resultTemp2 = new StringBuffer();

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
					sql = "SELECT eventid, eventuei, nodeid, eventtime, eventdescr, eventlogmsg, eventseverity"+
						  " FROM events where eventseverity "+ eventseverity +" order by eventtime desc limit "+limit;

					logger.debug("sql:::" + sql);
					rst = stmt.executeQuery(sql);

					while (rst.next()) {

						resultTemp.append("{\"eventid\":\"" + rst.getInt(1) + "\"," +
								"\"eventuei\":\"" + rst.getString(2) + "\","+
								"\"nodeid\":\"" + rst.getInt(3) + "\","+
								"\"eventtime\":\"" + rst.getString(4) + "\","+
								"\"eventdescr\":\"" + rst.getString(5) + "\","+
								"\"eventlogmsg\":\"" + rst.getString(6) + "\","+
								"\"eventseverity\":\"" + rst.getInt(7) + "\""+
								"},");

					}
					rst.close();
					
					if (resultTemp.equals(null)) {
						
					}else {
						
						// last "," delete.
						resultTemp.deleteCharAt(resultTemp.length() - 1);
						
						resultTemp2.append("{\"event\":[");
						resultTemp2.append(resultTemp);
						resultTemp2.append("]}");
						
						
//						result = resultTemp2.toString();
						result = resultTemp2.toString().replaceAll("\n", "\\n")
								.replaceAll("\'", "\\'")
								.replaceAll("\"", "\\\"")
								.replaceAll("\r", "\\r")
								.replaceAll("\t", "\\t")
								.replaceAll("\b", "\\b")
								.replaceAll("\f", "\\f");
						
					}
					
					logger.debug("ResultSet2 Json:::" + result);
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

		return result;
	}
	
	public String eventqueryCount(String eventseverity) throws HandleException {

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
					sql = "SELECT count(eventid) "+
						  " FROM events where eventseverity "+ eventseverity;

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



}
