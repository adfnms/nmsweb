package kr.co.adflow.nms.web.process;

import java.util.HashMap;
import java.util.Locale;

import kr.co.adflow.nms.web.DefaultHandlerImpl;
import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.HandlerFactory;
import kr.co.adflow.nms.web.exception.HandleException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * scheduledOutagesProcess
 * 
 * @author kicho@adflow.co.kr
 * @version 1.2
 */
@Controller
public class ScheduledOutagesProcess {

	private static final String ContentType = "contentType";
	private static final String DATA = "data";
	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String Accept = "accept";
//	private static final String NMSUrl = "http://localhost:8980/opennms/rest";
	private static final String NMSUrl = "http://112.223.76.74:8980/opennms/rest";
	private static final Logger logger = LoggerFactory
			.getLogger(ScheduledOutagesProcess.class);

	private ScheduledOutagesProcess() {
	}

	/**
	 * singleton
	 * 
	 */
	public static ScheduledOutagesProcess process = new ScheduledOutagesProcess();

	public static ScheduledOutagesProcess getProcess() {
		return process;
	}
	
	public String schedOutagesFilter(String filter) throws HandleException {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(Accept, "application/json");
		hash.put(URL, NMSUrl + "/sched-outages?"+filter);
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String schedOutages() throws HandleException {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(Accept, "application/json");
		hash.put(URL, NMSUrl + "/sched-outages");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	public String schedOutages(String outageName) throws HandleException {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(Accept, "application/json");
		hash.put(URL, NMSUrl + "/sched-outages/"+outageName);
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	//
	public String schedOutagesPost(String data) throws HandleException {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(ContentType, "application/xml");
		hash.put(URL, NMSUrl + "/sched-outages");
		hash.put(METHOD, "POST");
		hash.put(DATA, data);
		

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	
	///sched-outages/{outageName}/notifd 
	public String schedOutagesNotifdPut(String outageName) throws HandleException {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(ContentType, "application/x-www-form-urlencoded");
		hash.put(URL, NMSUrl + "/sched-outages/"+outageName+"/notifd");
		hash.put(METHOD, "PUT");
		

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	
	///sched-outages/{outageName}/collectd/{package} 
	public String schedOutagesCollectdPut(String outageName, String packageName) throws HandleException {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(ContentType, "application/x-www-form-urlencoded");
		hash.put(URL, NMSUrl + "/sched-outages/"+outageName+"/collectd/"+packageName);
		hash.put(METHOD, "PUT");
		

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	
	///sched-outages/{outageName}/pollerd/{package} 
	public String schedOutagesPollerdPut(String outageName, String packageName) throws HandleException {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(ContentType, "application/x-www-form-urlencoded");
		hash.put(URL, NMSUrl + "/sched-outages/"+outageName+"/pollerd/"+packageName);
		hash.put(METHOD, "PUT");
		

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	
	///sched-outages/{outageName}/threshd/{package} 
	public String schedOutagesThreshdPut(String outageName, String packageName) throws HandleException {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(ContentType, "application/x-www-form-urlencoded");
		hash.put(URL, NMSUrl + "/sched-outages/"+outageName+"/threshd/"+packageName);
		hash.put(METHOD, "PUT");
		

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	
	///sched-outages/{outageName}
	public String schedOutagesDelete(String outageName) throws HandleException {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, NMSUrl + "/sched-outages/"+outageName);
		hash.put(METHOD, "DELETE");
		

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	///sched-outages/{outageName}/notifd 
	public String schedOutagesNotifdDelete(String outageName) throws HandleException {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, NMSUrl + "/sched-outages/"+outageName+"/notifd");
		hash.put(METHOD, "DELETE");
		

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	
	///sched-outages/{outageName}/collectd/{package} 
	public String schedOutagesCollectdDelete(String outageName, String packageName) throws HandleException {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, NMSUrl + "/sched-outages/"+outageName+"/collectd/"+packageName);
		hash.put(METHOD, "DELETE");
		

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	
	///sched-outages/{outageName}/pollerd/{package} 
	public String schedOutagesPollerdDelete(String outageName, String packageName) throws HandleException {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, NMSUrl + "/sched-outages/"+outageName+"/pollerd/"+packageName);
		hash.put(METHOD, "DELETE");
		

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	
	///sched-outages/{outageName}/threshd/{package} 
	public String schedOutagesThreshdDelete(String outageName, String packageName) throws HandleException {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, NMSUrl + "/sched-outages/"+outageName+"/threshd/"+packageName);
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
