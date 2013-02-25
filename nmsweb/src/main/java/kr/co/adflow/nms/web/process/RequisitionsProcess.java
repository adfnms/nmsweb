package kr.co.adflow.nms.web.process;

import java.util.HashMap;

import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.HandlerFactory;
import kr.co.adflow.nms.web.exception.HandleException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class RequisitionsProcess {
	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String NMSUrl = "http://192.168.0.63:8980/opennms/rest";
	private static final String Accept = "accept";
	private static final Logger logger = LoggerFactory
			.getLogger(RequisitionsProcess.class);

	private RequisitionsProcess() {

	}

	public static RequisitionsProcess process = new RequisitionsProcess();

	public static RequisitionsProcess getPrcess() {
		return process;
	}

	// requisitions
	public String requisitions() throws HandleException {
		try {
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/requisitions");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			String result = null;

			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

	}

	// requisitions/count
	public String requisitionsCount() throws HandleException {
		try {
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/requisitions/count");
			hash.put(METHOD, "GET");

			String result = null;

			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

	}

	// requisitions/deployed
	public String requisitionsDeployed() throws HandleException {
		try {
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/requisitions/deployed");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			String result = null;

			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

	}

	// requisitions/deployed/count
	public String requisitionsDeployedCount() throws HandleException{
		try {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();
		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, NMSUrl + "/requisitions/deployed/count");
		hash.put(METHOD, "GET");

		String result = null;

		
			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

		

	}

	// requisitions/{name}
	public String requisitions(String name) throws HandleException {
		try {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();
		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, NMSUrl + "/requisitions/" + name);
		hash.put(Accept, "application/json");
		hash.put(METHOD, "GET");

		String result = null;

	
			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

		

	}

	// /requisitions/{name}/nodes

	public String requisitionsNodes(String name) throws HandleException{
		try {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();
		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, NMSUrl + "/requisitions/" + name + "/nodes");
		hash.put(Accept, "application/json");
		hash.put(METHOD, "GET");

		String result = null;

		
			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

		

	}

	// requisitions/{name}/nodes/{foreignId}
	public String requisitionsNodes(String name, String foreignId) throws HandleException {
		try {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();
		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, NMSUrl + "/requisitions/" + name + "/nodes/" + foreignId);
		hash.put(Accept, "application/json");
		hash.put(METHOD, "GET");

		String result = null;
		
	
			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

		

	}

	// /requisitions/{name}/nodes/{foreignId}/interfaces
	public String requisitionsNodesInterfaces(String name, String foreignId) throws HandleException{
		try {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();
		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, NMSUrl + "/requisitions/" + name + "/nodes/" + foreignId
				+ "/interfaces");
		hash.put(Accept, "application/json");
		hash.put(METHOD, "GET");

		String result = null;

		
			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}



	}

	// /requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}
	public String requisitionsNodesInterfaces(String name, String foreignId,
			String ipAddress) throws HandleException {
		try {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();
		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, NMSUrl + "/requisitions/" + name + "/nodes/" + foreignId
				+ "/interfaces/" + ipAddress);
		hash.put(Accept, "application/json");
		hash.put(METHOD, "GET");

		String result = null;

		
			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

	

	}

	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services
	public String requisitionsNodesInterfacesServices(String name,
			String foreignId, String ipAddress) throws HandleException {
		try {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();
		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, NMSUrl + "/requisitions/" + name + "/nodes/" + foreignId
				+ "/interfaces/" + ipAddress + "/services");
		hash.put(Accept, "application/json");
		hash.put(METHOD, "GET");

		String result = null;

		
			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

		

	}

	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services/{service}

	public String requisitionsNodesInterfacesServices(String name,
			String foreignId, String ipAddress, String service) throws HandleException{
		try {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();
		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, NMSUrl + "/requisitions/" + name + "/nodes/" + foreignId
				+ "/interfaces/" + ipAddress + "/services/" + service);
		hash.put(Accept, "application/json");
		hash.put(METHOD, "GET");

		String result = null;

		
			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

		

	}

	// requisitions/{name}/nodes/{foreignId}/categories
	public String requisitionsNodesCategories(String name, String foreignId) throws HandleException {
		try {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();
		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, NMSUrl + "/requisitions/" + name + "/nodes/" + foreignId
				+ "/categories");
		hash.put(Accept, "application/json");
		hash.put(METHOD, "GET");

		String result = null;

		
			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
		   throw new HandleException(e);
		}

		

	}

	// requisitions/{name}/nodes/{foreignId}/categories/{categoryName}
	public String requisitionsNodesCategories(String name, String foreignId,
			String categoryName) throws HandleException{
		try {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();
		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, NMSUrl + "/requisitions/" + name + "/nodes/" + foreignId
				+ "/categories/" + categoryName);
		hash.put(Accept, "application/json");
		hash.put(METHOD, "GET");

		String result = null;

		
			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

		

	}

	// requisitions/{name}/nodes/{foreignId}/assets
	public String requisitionsNodesAssets(String name, String foreignId) throws HandleException{
		try {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();
		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, NMSUrl + "/requisitions/" + name + "/nodes/" + foreignId
				+ "/assets");
		hash.put(Accept, "application/json");
		hash.put(METHOD, "GET");

		String result = null;

		
			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

		

	}

}
