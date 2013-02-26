package kr.co.adflow.nms.web.process;

import java.util.HashMap;
import java.util.Locale;

import kr.co.adflow.nms.web.DefaultHandlerImpl;
import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.HandlerFactory;
import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.util.XmlUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.adflow.nms.web.vo.DestPath.DestinationPaths;
import kr.co.adflow.nms.web.vo.DestPath.Path;

/**
 * NotificationsProcess
 * 
 * @author kicho@adflow.co.kr
 * @version 1.1
 */
@Controller
public class NotificationsProcess {

	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String Accept = "accept";
	private static final String NMSUrl = "http://localhost:8980/opennms/rest";
//	private static final String NMSUrl = "http://112.223.76.78:8980/opennms/rest";
	private static final Logger logger = LoggerFactory
			.getLogger(NotificationsProcess.class);

	private NotificationsProcess() {
	}

	/**
	 * singleton
	 * 
	 */
	public static NotificationsProcess process = new NotificationsProcess();

	public static NotificationsProcess getProcess() {
		return process;
	}
	
	public String notificationsFilter(String filter) throws HandleException {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(Accept, "application/json");
		hash.put(URL, NMSUrl + "/notifications?"+filter);
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String notifications() throws HandleException {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(Accept, "application/json");
		hash.put(URL, NMSUrl + "/notifications?limit=0");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	public String notifications(String id) throws HandleException {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(Accept, "application/json");
		hash.put(URL, NMSUrl + "/notifications/"+id);
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String notificationsCount() throws HandleException {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();

		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, NMSUrl + "/notifications/count");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	
	public String notificationDestinationPathsPost(Path path) throws HandleException {

		String result = null;

		try {
			// xml Read
			XmlUtil xUtil = new XmlUtil();
			String filePath = "d:\\OpenNMS\\etc\\destinationPaths.xml";
			
			Class<DestinationPaths> classname = DestinationPaths.class;
			DestinationPaths dPath = new DestinationPaths();
			
			Object ob = xUtil.xmlRead(filePath, classname, dPath);
			
			dPath = (DestinationPaths)ob;
			int size = dPath.getPath().size();

			boolean dupCheak = true;
			
			for (int i = 0; i < size; i++) {
				
				if(path.getName().equals(dPath.getPath().get(i).getName())){
					
					dupCheak = false;
				}
				
			}
			
			
			if (dupCheak) {
				
				dPath.getPath().add(path);
				
				// xml Write
//				filePath = "d:\\OpenNMS\\etc\\destinationPaths3.xml";
				result = xUtil.xmlWrite(filePath, classname, dPath);
				
			} else {
				logger.error("Noficatil :: Path name Duplicate Error");
				throw new HandleException("Noficatil :: Path name Duplicate Error");
			}
			
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
}
