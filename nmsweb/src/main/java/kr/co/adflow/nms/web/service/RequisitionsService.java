package kr.co.adflow.nms.web.service;

import java.util.HashMap;

import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.HandlerFactory;
import kr.co.adflow.nms.web.exception.HandleException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class RequisitionsService {
	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private @Value("#{config['NMSURL']}") String ipAddr;
	private @Value("#{config['LOGINID']}") String loginId;
	private @Value("#{config['LOGINPASS']}") String loginPass;
	private static final String Accept = "accept";
	private static final String DATA = "data";
	private static final String CONTENTTYPE = "contentType";
	private static final Logger logger = LoggerFactory
			.getLogger(RequisitionsService.class);

	@Autowired
	private Handler handler;

	// requisitions
	public String requisitions() throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// requisitions/count
	public String requisitionsCount() throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/count");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// requisitions/deployed
	public String requisitionsDeployed() throws HandleException {
		String result = null;
		try {
			
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/deployed");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// requisitions/deployed/count
	public String requisitionsDeployedCount() throws HandleException {
		String result = null;
		try {

			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/deployed/count");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// requisitions/{name}
	public String requisitions(String name) throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// /requisitions/{name}/nodes

	public String requisitionsNodes(String name) throws HandleException {
		String result = null;
		try {
	
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/nodes");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// requisitions/{name}/nodes/{foreignId}
	public String requisitionsNodes(String name, String foreignId)
			throws HandleException {
		String result = null;
		try {
	;
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/nodes/"
					+ foreignId);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// /requisitions/{name}/nodes/{foreignId}/interfaces
	public String requisitionsNodesInterfaces(String name, String foreignId)
			throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/nodes/"
					+ foreignId + "/interfaces");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// /requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}
	public String requisitionsNodesInterfaces(String name, String foreignId,
			String ipAddress) throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/nodes/"
					+ foreignId + "/interfaces/" + ipAddress);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services
	public String requisitionsNodesInterfacesServices(String name,
			String foreignId, String ipAddress) throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/nodes/"
					+ foreignId + "/interfaces/" + ipAddress + "/services");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services/{service}

	public String requisitionsNodesInterfacesServices(String name,
			String foreignId, String ipAddress, String service)
			throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/nodes/"
					+ foreignId + "/interfaces/" + ipAddress + "/services/"
					+ service);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// requisitions/{name}/nodes/{foreignId}/categories
	public String requisitionsNodesCategories(String name, String foreignId)
			throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/nodes/"
					+ foreignId + "/categories");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// requisitions/{name}/nodes/{foreignId}/categories/{categoryName}
	public String requisitionsNodesCategories(String name, String foreignId,
			String categoryName) throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/nodes/"
					+ foreignId + "/categories/" + categoryName);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// requisitions/{name}/nodes/{foreignId}/assets
	public String requisitionsNodesAssets(String name, String foreignId)
			throws HandleException {
		String result = null;
		try {
			
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/nodes/"
					+ foreignId + "/assets");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// requisitions/ POST
	public String requisitionsPostPro(String xmlData) throws HandleException {
		String result = null;
		try {
			
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "POST");
			hash.put(CONTENTTYPE, "application/xml");
			hash.put(DATA, xmlData);
			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// requisitions/{name}/nodes POST
	public String requisitionsPostNodesPro(String xmlData, String name)
			throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/nodes");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "POST");
			hash.put(CONTENTTYPE, "application/xml");
			hash.put(DATA, xmlData);
			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// <interface snmp-primary="S" status="1" ip-addr="61.78.35.200" descr="">
	// /requisitions/{name}/nodes/{foreignId}/interfaces !!!POST
	public String requisitionsPostNodesInterfacesPro(String xmlData,
			String name, String foreignId) throws HandleException {
		String result = null;
		try {
			
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/nodes/"
					+ foreignId + "/interfaces");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "POST");
			hash.put(CONTENTTYPE, "application/xml");
			hash.put(DATA, xmlData);
			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services
	// POST!
	// <monitored-service service-name="ICMP"/>
	public String requisitionServicesPro(String xmlData, String name,
			String foreignId, String ipAddress) throws HandleException {
		String result = null;
		try {
			
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/nodes/"
					+ foreignId + "/interfaces/" + ipAddress + "/services");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "POST");
			hash.put(CONTENTTYPE, "application/xml");
			hash.put(DATA, xmlData);
			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// Post!!
	// requisitions/{name}/nodes/{foreignId}/categories
	// <category name="Production"/>
	public String requisitionCategorysPro(String xmlData, String name,
			String foreignId) throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/nodes/"
					+ foreignId + "/categories");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "POST");
			hash.put(CONTENTTYPE, "application/xml");
			hash.put(DATA, xmlData);
			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// Post!!
	// /requisitions/{name}/nodes/{foreignId}/assets
	// <asset value="test" name="admin"/>
	// {"asset":[{"value":"test","name":"admin"}]}

	public String requisitionAssetsPro(String xmlData, String name,
			String foreignId) throws HandleException {
		String result = null;
		try {
	
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/nodes/"
					+ foreignId + "/assets");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "POST");
			hash.put(CONTENTTYPE, "application/xml");
			hash.put(DATA, xmlData);
			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// PUT!!!
	// /requisitions/{name}/import
	public String requisitionImport(String name) throws HandleException {
		String result = null;
		try {
			
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/import");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "PUT");
			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// PUT
	// requisitions/{name}/import?rescanExisting=false
	public String requisitionImportRescanP(String name) throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name
					+ "/import?rescanExisting=false");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "PUT");
			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// PUT
	// requisitions/{name}
	public String requisitioUpdate(String name, String data)
			throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "PUT");
			hash.put(DATA, data);
			hash.put(CONTENTTYPE, "application/x-www-form-urlencoded");
			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// PUt
	// /requisitions/{name}/nodes/{foreignId}
	public String requisitionNameUpdate(String name, String foreignId,
			String data) throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/nodes/"
					+ foreignId);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "PUT");
			hash.put(DATA, data);
			hash.put(CONTENTTYPE, "application/x-www-form-urlencoded");
			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// PUT
	// /requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}
	public String requisitionInterUpdate(String name, String foreignId,
			String ipAddress,String data) throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/nodes/"
					+ foreignId + "/interfaces/" + ipAddress);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "PUT");
			hash.put(DATA, data);
			hash.put(CONTENTTYPE, "application/x-www-form-urlencoded");
			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// DELETE
	// /requisitions/{name}
	public String reqDelete(String name) throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "DELETE");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// DELETE
	// requisitions/deployed/{name}
	public String reqDeployDeletePro(String name) throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/deployed/" + name);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "DELETE");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// DELETE
	// requisitions/{name}/nodes/{foreignId}
	public String reqNodesDelPro(String name, String foreignId)
			throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/nodes/"
					+ foreignId);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "DELETE");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// DELETE
	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}

	public String reqInterDelPro(String name, String foreignId, String ipAddress)
			throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/nodes/"
					+ foreignId + "/interfaces/" + ipAddress);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "DELETE");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// DELETE
	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services/{service}

	public String reqServiceDelPro(String name, String foreignId,
			String ipAddress, String service) throws HandleException {
		String result = null;
		try {
	
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/nodes/"
					+ foreignId + "/interfaces/" + ipAddress + "/services/"
					+ service);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "DELETE");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// DELETE
	// requisitions/{name}/nodes/{foreignId}/categories/{category}

	public String reqCategoryDelPro(String name, String foreignId,
			String category) throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/nodes/"
					+ foreignId + "/categories/" + category);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "DELETE");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// DELETE
	// requisitions/{name}/nodes/{foreignId}/assets/{field}

	public String reqAssetDelPro(String name, String foreignId, String field)
			throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/requisitions/" + name + "/nodes/"
					+ foreignId + "/assets/" + field);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "DELETE");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

}
