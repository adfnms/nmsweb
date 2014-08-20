package kr.co.adflow.nms.web.service;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.xml.MarkupBuilder;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.exception.ValidationException;
import kr.co.adflow.nms.web.push.Pusher;
import kr.co.adflow.nms.web.push.WebSocketHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * NodeProcess
 * 
 * @author kicho@adflow.co.kr
 * @version 1.2
 */
@Service
public class NodeService {

	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String DATA = "data";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String Accept = "accept";
	
	private @Value("#{config['NMSURL']}")
	String ipAddr;
	private @Value("#{config['LOGINID']}") String loginId;
	private @Value("#{config['LOGINPASS']}") String loginPass;
	
	private @Value("#{config['GROOVYPATH']}") String groovyPath;

	private static final Logger logger = LoggerFactory
			.getLogger(NodeService.class);

	@Autowired
	private Handler handler;

	public String nodesFilter(String filter) throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/nodes?" + filter);
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String nodes() throws HandleException {

		// push sample code
		// Set<WebSocketHandler> sockets = Pusher.getInstance().getSockets();
		//
		// Iterator<WebSocketHandler> it = sockets.iterator();
		//
		// for (WebSocketHandler socket : sockets) {
		// System.out.println("Trying to send to Member!");
		// if (socket.isOpen()) {
		// System.out.println("Sending!");
		// try {
		// socket.sendMessage("Sending a Message to you Guys! "
		// + new Date());
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		// }
		// sample end

		HashMap hash = new HashMap();
		logger.debug("**************************");
		logger.debug("ipADDR" + ipAddr);
		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(URL, ipAddr + "/nodes?limit=0");
		hash.put(Accept, "application/json");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String nodes(String id) throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(URL, ipAddr + "/nodes/" + id);
		hash.put(Accept, "application/json");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String nodesIpInterfaces(String id) throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(URL, ipAddr + "/nodes/" + id + "/ipinterfaces?limit=0");
		hash.put(Accept, "application/json");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String nodesIpInterfaces(String id, String ipAddress)
			throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		logger.debug("nodesIpInterfaces ipAddress ::" + ipAddress);

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/nodes/" + id + "/ipinterfaces/" + ipAddress);
		hash.put(METHOD, "GET");


		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String nodesIpInterfacesServices(String id, String ipAddress)
			throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/nodes/" + id + "/ipinterfaces/" + ipAddress
				+ "/services?limit=0");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String nodesIpInterfacesServices(String id, String ipAddress,
			String service) throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/nodes/" + id + "/ipinterfaces/" + ipAddress
				+ "/services/" + service);
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	// /nodes/{id}/snmpinterfaces
	public String nodesSnmpinterfaces(String id) throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/nodes/" + id + "/snmpinterfaces");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	// /nodes/{id}/snmpinterfaces/{ifIndex}
	public String nodesSnmpinterfaces(String id, String ifIndex)
			throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/nodes/" + id + "/snmpinterfaces/" + ifIndex);
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	// /nodes/{id}/categories
	public String nodesCategories(String id) throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/nodes/" + id + "/categories");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	// /nodes/{id}/categories/{categoryName}
	public String nodesCategories(String id, String categoryName)
			throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/nodes/" + id + "/categories/" + categoryName);
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	// /nodes/{id}/assetRecord
	public String nodesAssetRecord(String id) throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/nodes/" + id + "/assetRecord");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}



	// /DELETE ////////////////
	// nodes/{id}
	public String nodesDelete(String id) throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(URL, ipAddr + "/nodes/" + id);
		hash.put(METHOD, "DELETE");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String nodesIpinterfacesDelete(String id, String ipAddress)
			throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(URL, ipAddr + "/nodes/" + id + "/ipinterfaces/" + ipAddress);
		hash.put(METHOD, "DELETE");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String nodesIpInterfacesServicesDelete(String id, String ipAddress,
			String service) throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(URL, ipAddr + "/nodes/" + id + "/ipinterfaces/" + ipAddress
				+ "/services/" + service);
		hash.put(METHOD, "DELETE");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String nodesSnmpinterfacesDelete(String id, String ifIndex)
			throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(URL, ipAddr + "/nodes/" + id + "/snmpinterfaces/" + ifIndex);
		hash.put(METHOD, "DELETE");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String nodesCategoriesDelete(String id, String categoryName)
			throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(URL, ipAddr + "/nodes/" + id + "/categories/" + categoryName);
		hash.put(METHOD, "DELETE");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	public String nodeIpPost(String ip) throws HandleException {

		String result = null;

		try {
			ClassLoader parent = getClass().getClassLoader();
			GroovyClassLoader loader = new GroovyClassLoader(parent);
			Class groovyClass = loader.parseClass(new File(groovyPath+"sendNewSuspectEvent.groovy"));

			// let's call some method on an instance
			GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
			String[] args = { ip,"127.0.0.1" };
			groovy.xml.MarkupBuilder ret =  (MarkupBuilder)groovyObject.invokeMethod("sendNewSuspectEvent", args);

			logger.debug("ret :" + ret);
			result ="{\"result\":\"success\"}";
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	public String serviceList() throws HandleException {

		StringBuffer result = new StringBuffer();

		Statement stmt = null;
		ResultSet rst = null;
		Connection conn = null;
		String sql = null;

		try {
			logger.debug("aaaaaa:::");
			
			Context ctx = new InitialContext();
			
			logger.debug("bbbbbbb:::");
			
			if (ctx == null)
				throw new Exception("Boom - No Context");

			// /jdbc/postgres is the name of the resource above
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/postgres");
			
			logger.debug("cccccc:::");
			
			if (ds != null) {
				conn = ds.getConnection();

				if (conn != null) {
					stmt = conn.createStatement();
					sql = "SELECT serviceid, servicename FROM service";

					logger.debug("sql:::" + sql);
					rst = stmt.executeQuery(sql);

					result.append("{\"services\":[");
					while (rst.next()) {

						result.append("{\"serviceid\":\"" + rst.getInt(1) + "\",");
						result.append("\"servicename\":\""
								+ rst.getString(2) + "\"},");

					}

					rst.close();

					// last "&" delete.
					result.deleteCharAt(result.length() - 1);
					result.append("]}");
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
	
	public String nodeSearchService(String serviceId) throws HandleException {

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
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/postgres");
			if (ds != null) {
				conn = ds.getConnection();

				if (conn != null) {
					stmt = conn.createStatement();
					sql = "SELECT nodeid, nodelabel FROM node WHERE nodeid in (SELECT nodeid FROM ifservices WHERE status != 'D' AND serviceid = "+serviceId+" GROUP BY nodeid)";

					logger.debug("sql:::" + sql);
					rst = stmt.executeQuery(sql);

					result.append("{\"nodes\":[");
					while (rst.next()) {

						result.append("{\"nodeid\":\"" + rst.getInt(1) + "\",");
						result.append("\"nodelabel\":\""
								+ rst.getString(2) + "\"},");

					}

					rst.close();

					// last "&" delete.
					result.deleteCharAt(result.length() - 1);
					result.append("]}");
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
                 
                if(result.toString().equals("{\"nodes\":]}")){
		      result.delete(0,result.toString().length());
		      result.append("{\"node\":\"null\"}");
		}
		return result.toString();
	}
	
	public String nodeSearchIp(String iplike) throws HandleException {

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
					sql = "SELECT nodeid, nodelabel FROM node WHERE nodeid in (SELECT nodeid  FROM ipinterface WHERE iplike(ipaddr,'"+iplike+"') AND ismanaged = 'M')";

					logger.debug("sql:::" + sql);
					boolean selectCheck = true;
					
					
					rst = stmt.executeQuery(sql);
					
					result.append("{\"nodes\":[");
					while (rst.next()) {

						result.append("{\"nodeid\":\"" + rst.getInt(1) + "\",");
						result.append("\"nodelabel\":\""+ rst.getString(2) + "\"},");
						
						selectCheck = false;

					}
					// last "&" delete.
					result.deleteCharAt(result.length() - 1);
					result.append("]}");
					
					
					if (selectCheck) {
						
//						result = new StringBuffer();
						
						try {
							throw new HandleException("iplike "+iplike+" Not Found");
						} catch (HandleException e) {
							throw e;
						}
						
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
	
	public String nodeSearchNodeIdAndLikeNodelabel(String nodeId,String nodelabellike) throws HandleException {

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
					sql = "SELECT nodeid, nodelabel FROM node WHERE ";
					
					if(nodelabellike != null && !nodelabellike.equals(""))
					{
						sql +="nodelabel like '%"+nodelabellike+"%'";
					}
					
					if(nodeId != null && !nodeId.equals(""))
					{
						if(nodelabellike != null && !nodelabellike.equals(""))
							sql +=" AND ";
						
						sql +="nodeid = "+nodeId;
					}
					
					logger.debug("sql:::" + sql);
					boolean selectCheck = true;
					
					rst = stmt.executeQuery(sql);
					
					result.append("{\"nodes\":[");
					while (rst.next()) {

						result.append("{\"nodeid\":\"" + rst.getInt(1) + "\",");
						result.append("\"nodelabel\":\""
								+ rst.getString(2) + "\"},");
						
						selectCheck = false;

					}
					// last "&" delete.
					result.deleteCharAt(result.length() - 1);
					result.append("]}");
					
					
					if (selectCheck) {
						
//						result = new StringBuffer();
						
						try {
							throw new HandleException( "nodeId "+nodeId+", nodelabellike"+nodelabellike+" Not Found");
						} catch (HandleException e) {
							throw e;
						}
						
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
	
	public String nodesPost(String data)
			throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/xml");
		hash.put(URL, ipAddr + "/nodes");
		hash.put(METHOD, "POST");
		hash.put(DATA, data);


		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	
	public String nodesIpInterfacesPost(String id, String data) throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(URL, ipAddr + "/nodes/" + id + "/ipinterfaces");
		hash.put(Accept, "application/xml");
		hash.put(METHOD, "POST");
		hash.put(DATA, data);

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}



	public String nodesIpInterfacesServicesPost(String id, String ipAddress, String data)
			throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/xml");
		hash.put(URL, ipAddr + "/nodes/" + id + "/ipinterfaces/" + ipAddress
				+ "/services");
		hash.put(METHOD, "POST");
		hash.put(DATA, data);

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	// /nodes/{id}/snmpinterfaces
	public String nodesSnmpinterfacesPost(String id, String data) throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/xml");
		hash.put(URL, ipAddr + "/nodes/" + id + "/snmpinterfaces");
		hash.put(METHOD, "POST");
		hash.put(DATA, data);

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}


	// /nodes/{id}/categories
	public String nodesCategoriesPost(String id, String data) throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/xml");
		hash.put(URL, ipAddr + "/nodes/" + id + "/categories");
		hash.put(METHOD, "POST");
		hash.put(DATA, data);

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	
	public String nodesPut(String id, String label) throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);		
		hash.put(Accept, "application/xml");
		hash.put(URL, ipAddr + "/nodes/" + id + "?label="+label);
		hash.put(METHOD, "PUT");
		
		String result = null;

		try {
			result = (String) handler.handle(hash);
			result = "";
		} catch (Exception e) {
			System.out.println(e.toString());
			throw new HandleException(e);
		}

		return result;
	}
	
	
	public String nodesIpInterfacesPut(String id, String ipAddress, String isManaged) throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(URL, ipAddr + "/nodes/" + id + "/ipinterfaces/"+ ipAddress+"?isManaged="+isManaged);
		hash.put(Accept, "application/xml");
		hash.put(METHOD, "PUT");


		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}



	public String nodesIpInterfacesServicesPut(String id, String ipAddress, String servicesName, String status)
			throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/xml");
		hash.put(URL, ipAddr + "/nodes/" + id + "/ipinterfaces/" + ipAddress
				+ "/services/"+servicesName+"?status="+status);
		hash.put(METHOD, "PUT");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	// /nodes/{id}/snmpinterfaces
	public String nodesSnmpinterfacesPut(String id, String ifIndex, String collect) throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/xml");
		hash.put(URL, ipAddr + "/nodes/" + id + "/snmpinterfaces/"+ifIndex+"?collect="+collect);
		hash.put(METHOD, "PUT");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}


	// /nodes/{id}/categories
	public String nodesCategoriesPut(String id, String data) throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/xml");
		hash.put(URL, ipAddr + "/nodes/" + id + "/categories");
		hash.put(METHOD, "PUT");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	


}
