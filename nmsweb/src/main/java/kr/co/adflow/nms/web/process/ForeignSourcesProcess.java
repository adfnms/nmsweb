package kr.co.adflow.nms.web.process;

import java.util.HashMap;

import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.HandlerFactory;
import kr.co.adflow.nms.web.exception.HandleException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class ForeignSourcesProcess {
	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String NMSUrl = "http://192.168.0.63:8980/opennms/rest";
	private static final String Accept = "accept";

	private static final Logger logger = LoggerFactory
			.getLogger(ForeignSourcesProcess.class);

	private ForeignSourcesProcess() {

	}

	public static ForeignSourcesProcess process = new ForeignSourcesProcess();

	public static ForeignSourcesProcess getPrcess() {
		return process;
	}

	// foreignSources
	public String foreignSources() throws HandleException {
		try {
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/foreignSources");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			String result = null;

			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException();
		}

	}

	// foreignSources/default
	public String foreignSourcesDefault() throws HandleException {
		try {
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/foreignSources/default");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			String result = null;

			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

	}

	// foreignSources/deployed
	public String foreignSourcesDeployed() throws HandleException {
		try {
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/foreignSources/deployed");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			String result = null;

			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

	}

	// foreignSources/deployed/count
	public String foreignSourcesDeployedCount() throws HandleException {
		try {
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/foreignSources/deployed/count");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			String result = null;

			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

	}

	// foreignSources/{name}
	public String foreignSources(String name) throws HandleException {
		try {
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/foreignSources/" + name);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");
			String result = null;

			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

	}

	// foreignSources/{name}/detectors
	public String foreignSourcesDetectors(String name) throws HandleException {
		try {
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/foreignSources/" + name + "/detectors");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			String result = null;

			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

	}

	// foreignSources/{name}/detectors/{detector}
	public String foreignSourcesDetectors(String name, String detector)
			throws HandleException {
		try {
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/foreignSources/" + name + "/detectors/"
					+ detector);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			String result = null;

			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

	}

	// foreignSources/{name}/policies
	public String foreignSourcesPolicies(String name) throws HandleException{
		try {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();
		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, NMSUrl + "/foreignSources/" + name + "/policies");
		hash.put(Accept, "application/json");
		hash.put(METHOD, "GET");

		String result = null;

		
			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

		

	}

	// foreignSources/{name}/policies/{policy}
	public String foreignSourcesPolicies(String name, String policy) throws HandleException{
		try {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();
		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, NMSUrl + "/foreignSources/" + name + "/policies/"
				+ policy);
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
