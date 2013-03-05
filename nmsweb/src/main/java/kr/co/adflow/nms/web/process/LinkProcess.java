package kr.co.adflow.nms.web.process;

import java.util.HashMap;

import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.HandlerFactory;
import kr.co.adflow.nms.web.exception.HandleException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class LinkProcess {

	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String NMSUrl = "http://192.168.0.63:8980/opennms/rest";
	private static final String Accept = "accept";
	private static final Logger logger = LoggerFactory
			.getLogger(LinkProcess.class);

	private LinkProcess() {

	}

	public static LinkProcess process = new LinkProcess();

	public static LinkProcess getPrcess() {
		return process;
	}

	// links
	public String Links() throws HandleException {
		String result = null;
		try {
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/links");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;

	}

	// /links/{id}
	public String Links(String id) throws HandleException {
		String result = null;
		try {
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/links/" + id);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;

	}
}
