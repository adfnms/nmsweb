package kr.co.adflow.nms.web.process;

import java.util.HashMap;

import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.HandlerFactory;
import kr.co.adflow.nms.web.exception.HandleException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class SnmpConfigProcess {
	
	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String NMSUrl = "http://192.168.0.63:8980/opennms/rest";
	private static final String Accept = "accept";
	private static final Logger logger = LoggerFactory
			.getLogger(SnmpConfigProcess.class);

	private SnmpConfigProcess() {

	}

	public static SnmpConfigProcess process = new SnmpConfigProcess();

	public static SnmpConfigProcess getPrcess() {
		return process;
	}
	
	
	//snmpConfig/{ipAddress} 
		public String SnmpConfig(String ipAddress) throws HandleException{
			try {
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl+"/snmpConfig/"+ipAddress);
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



















