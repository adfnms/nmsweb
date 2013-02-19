package kr.co.adflow.nms.web.node;

import java.util.HashMap;
import java.util.Locale;

import kr.co.adflow.nms.web.DefaultHandlerImpl;
import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.HandlerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class NodeProcess {

	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final Logger logger = LoggerFactory
			.getLogger(NodeProcess.class);

	
	public String nodes() {
//		Handler handler = HandlerFactory.getHandler();
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, "http://localhost:8980/opennms/rest/nodes?limit=0");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public String nodes(String id) {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, "http://localhost:8980/opennms/rest/nodes/"+id);
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	public String nodesIpInterfaces(String id) {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, "http://localhost:8980/opennms/rest/nodes/"+id+"/ipinterfaces?limit=0");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	public String nodesIpInterfaces(String id, String ipAddress) {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, "http://localhost:8980/opennms/rest/nodes/"+id+"/ipinterfaces/"+ipAddress);
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	public String nodesIpInterfacesServices(String id, String ipAddress) {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, "http://localhost:8980/opennms/rest/nodes/"+id+"/ipinterfaces/"+ipAddress+"/services?limit=0");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	public String nodesIpInterfacesServices(String id, String ipAddress, String service) {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, "http://localhost:8980/opennms/rest/nodes/"+id+"/ipinterfaces/"+ipAddress+"/services/"+service);
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	///nodes/{id}/snmpinterfaces
	public String nodesSnmpinterfaces(String id) {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, "http://localhost:8980/opennms/rest/nodes/"+id+"/snmpinterfaces");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	///nodes/{id}/snmpinterfaces/{ifIndex} 
	public String nodesSnmpinterfaces(String id, String ifIndex) {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, "http://localhost:8980/opennms/rest/nodes/"+id+"/snmpinterfaces/"+ifIndex);
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}	
	
	///nodes/{id}/categories 
	public String nodesCategories(String id) {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, "http://localhost:8980/opennms/rest/nodes/"+id+"/categories");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	
	///nodes/{id}/categories/{categoryName}
	public String nodesCategories(String id, String categoryName) {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, "http://localhost:8980/opennms/rest/nodes/"+id+"/categories/"+categoryName);
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	///nodes/{id}/assetRecord
	public String nodesAssetRecord(String id) {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, "http://localhost:8980/opennms/rest/nodes/"+id+"/assetRecord");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	
	
}
