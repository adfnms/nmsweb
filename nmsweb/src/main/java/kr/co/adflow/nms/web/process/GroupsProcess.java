package kr.co.adflow.nms.web.process;

import java.util.HashMap;

import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.HandlerFactory;
import kr.co.adflow.nms.web.exception.HandleException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class GroupsProcess {

	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String NMSUrl = "http://192.168.0.63:8980/opennms/rest";
	private static final String Accept = "accept";
	private static final String DATA = "data";
	private static final String CONTENTTYPE = "contentType";

	private static final Logger logger = LoggerFactory
			.getLogger(ForeignSourcesProcess.class);

	private GroupsProcess() {

	}

	public static GroupsProcess process = new GroupsProcess();

	public static GroupsProcess getPrcess() {
		return process;
	}

	// groups
	public String groups() throws HandleException {
		String result = null;
		try {
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/groups");
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
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/groups");
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
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/groups/" + groupName);
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
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/groups/" + groupName + "/users/"
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
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/groups/" + groupName);
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
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/groups/" + groupName + "/users/"
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
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/groups/" + groupName + "/users");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}
}
