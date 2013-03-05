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
public class ForeignSourcesService {
	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String DATA = "data";
	private static final String CONTENTTYPE = "contentType";
	private @Value("#{config['NMSURL']}") String ipAddr;
	private static final String Accept = "accept";

	private static final Logger logger = LoggerFactory
			.getLogger(ForeignSourcesService.class);

	@Autowired
	private Handler handler;

	// foreignSources
	public String foreignSources() throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, ipAddr + "/foreignSources");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException();
		}
		return result;
	}

	// foreignSources/default
	public String foreignSourcesDefault() throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, ipAddr + "/foreignSources/default");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// foreignSources/deployed
	public String foreignSourcesDeployed() throws HandleException {
		String result = null;
		try {

			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, ipAddr + "/foreignSources/deployed");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// foreignSources/deployed/count
	public String foreignSourcesDeployedCount() throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, ipAddr + "/foreignSources/deployed/count");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// foreignSources/{name}
	public String foreignSources(String name) throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, ipAddr + "/foreignSources/" + name);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// foreignSources/{name}/detectors
	public String foreignSourcesDetectors(String name) throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, ipAddr + "/foreignSources/" + name + "/detectors");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// foreignSources/{name}/detectors/{detector}
	public String foreignSourcesDetectors(String name, String detector)
			throws HandleException {
		String result = null;
		try {
			
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, ipAddr + "/foreignSources/" + name + "/detectors/"
					+ detector);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// foreignSources/{name}/policies
	public String foreignSourcesPolicies(String name) throws HandleException {
		String result = null;
		try {
	
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, ipAddr + "/foreignSources/" + name + "/policies");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;

	}

	// foreignSources/{name}/policies/{policy}
	public String foreignSourcesPolicies(String name, String policy)
			throws HandleException {
		String result = null;
		try {
	
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, ipAddr + "/foreignSources/" + name + "/policies/"
					+ policy);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;

	}

	// POST
	// /foreignSources
	// <foreign-source name="testzzzzz"/>
	// {"foreign-source":[{"name":"testzzzzz"}]}

	public String foreignPostPro(String xmlData) throws HandleException {
		String result = null;
		try {
			
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, ipAddr + "/foreignSources");
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
	
	//POST
	//<detector class="org.opennms.netmgt.provision.detector.simple.HttpDetector" name="chan2"/>
	//foreignSources/{name}/detectors 
	public String foreignDecPro(String xmlData,String name) throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, ipAddr + "/foreignSources/"+name+"/detectors");
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
	
	//POST
		//foreignSources/{name}/policies 
		//<policy class="org.opennms.netmgt.provision.persist.policies.MatchingIpInterfacePolicy" name="pol2chan">
		//<parameter value="DISABLE_COLLECTION" key="action"/><parameter value="ALL_PARAMETERS" key="matchBehavior"/></policy>
	public String foreignPolicesPro(String xmlData,String name) throws HandleException {
		String result = null;
		try {

			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, ipAddr + "/foreignSources/"+name+"/policies");
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
	
	//foreignSources/{name} 	
	// PUT
	
	
	public String foreignPutNamePro(String name,String data) throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, ipAddr + "/foreignSources/"+name);
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
	
	
	//DELETE
		//foreignSources/{name} 
	
	public String foreignDelNamePro(String name) throws HandleException {
		String result = null;
		try {
			
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, ipAddr + "/foreignSources/"+name);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "DELETE");
			
			
			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;

	}
	
	//DEL
	//foreignSources/{name}/detectors/{detector} 
	
	public String foreignDelDecPro(String name,String dec) throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, ipAddr + "/foreignSources/"+name+"/detectors/"+dec);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "DELETE");
			
			
			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;

	}

	//DEL
	//foreignSources/{name}/policies/{policy} 
	public String foreignDelPolPro(String name,String policy) throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, ipAddr + "/foreignSources/"+name+"/policies/"+policy);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "DELETE");
			
			
			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;

	}
	
}
