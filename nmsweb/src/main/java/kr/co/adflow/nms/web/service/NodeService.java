package kr.co.adflow.nms.web.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.exception.HandleException;
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
 * @version 1.1
 */
@Service
public class NodeService {

	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String Accept = "accept";
	// private static final String NMSUrl =
	// "http://localhost:8980/opennms/rest";
	// private static final String NMSUrl =
	// "http://112.223.76.74:8980/opennms/rest";
	private @Value("#{config['NMSURL']}")
	String ipAddr;

	private static final Logger logger = LoggerFactory
			.getLogger(NodeService.class);

	@Autowired
	private Handler handler;

	public String nodesFilter(String filter) throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
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
		Set<WebSocketHandler> sockets = Pusher.getInstance().getSockets();

		Iterator<WebSocketHandler> it = sockets.iterator();

		for (WebSocketHandler socket : sockets) {
			System.out.println("Trying to send to Member!");
			if (socket.isOpen()) {
				System.out.println("Sending!");
				try {
					socket.sendMessage("Sending a Message to you Guys! "
							+ new Date());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// sample end

		HashMap hash = new HashMap();
		logger.debug("**************************");
		logger.debug("ipADDR" + ipAddr);
		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
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

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
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

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
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

		logger.debug("aaaaa::" + ipAddress);

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/nodes/" + id + "/ipinterfaces/" + ipAddress);
		hash.put(METHOD, "GET");

		logger.debug("bbbbb::" + hash.get("URL").toString());

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

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
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

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
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

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
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

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
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

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
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

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
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

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
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

	public String nodesCategoriesPost(String id) throws HandleException {
		// Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/nodes/" + id + "/categories");
		hash.put(METHOD, "POST");

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

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
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

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
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

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
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

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
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

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
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

}
