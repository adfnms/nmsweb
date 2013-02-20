package kr.co.adflow.nms.web.events;

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
public class EventsProcess {

	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String Accept = "accept";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String NMSUrl = "http://localhost:8980/opennms/rest";
//	private static final String NMSUrl = "http://112.223.76.78:8980/opennms/rest";
	private static final Logger logger = LoggerFactory
			.getLogger(EventsProcess.class);

	private EventsProcess() {
	}

	/**
	 * singleton
	 * 
	 */
	public static EventsProcess process = new EventsProcess();

	public static EventsProcess getProcess() {
		return process;
	}

	public String events() {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(Accept, "application/json");
		hash.put(URL, NMSUrl + "/events?limit=8");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public String eventsCount() {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, NMSUrl + "/events/count");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public String events(String id) {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(Accept, "application/json");
		hash.put(URL, NMSUrl + "/events/"+id);
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
