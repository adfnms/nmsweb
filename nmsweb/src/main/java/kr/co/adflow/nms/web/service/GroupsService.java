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
public class GroupsService {

	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";;
	private @Value("#{config['NMSURL']}") String ipAddr;
	private @Value("#{config['LOGINID']}") String loginId;
	private @Value("#{config['LOGINPASS']}") String loginPass;
	private static final String Accept = "accept";
	private static final String DATA = "data";
	private static final String CONTENTTYPE = "contentType";

	private static final Logger logger = LoggerFactory
			.getLogger(ForeignSourcesService.class);

	@Autowired
	private Handler handler;

	// groups
	public String groups() throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/groups");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// groupsPost

	public String groupsPost(String xmlData) throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/groups");
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

	// groups/{groupname}
	public String groups(String groupName) throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/groups/" + groupName);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// groups PUT groups users add

	public String groupsPut(String groupName, String userName)
			throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/groups/" + groupName + "/users/"
					+ userName);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "PUT");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// group Del group del //groups/{groupname}

	public String groupsDelGroup(String groupName) throws HandleException {
		String result = null;
		try {
			
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/groups/" + groupName);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "DELETE");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// group Del group user del //groups/{groupname}/users/{username}
	public String groupsDelGroupUsers(String groupName, String userName)
			throws HandleException {
		String result = null;
		try {
			
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/groups/" + groupName + "/users/"
					+ userName);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "DELETE");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// groups/{groupname}/users

	public String groupsUsers(String groupName) throws HandleException {
		String result = null;
		try {
	
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/groups/" + groupName + "/users");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}
}
