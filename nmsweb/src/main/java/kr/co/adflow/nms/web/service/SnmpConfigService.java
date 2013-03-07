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
public class SnmpConfigService {
	
	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private @Value("#{config['NMSURL']}") String ipAddr;
	private @Value("#{config['LOGINID']}") String loginId;
	private @Value("#{config['LOGINPASS']}") String loginPass;
	private static final String Accept = "accept";
	private static final Logger logger = LoggerFactory
			.getLogger(SnmpConfigService.class);

	@Autowired
	private Handler handler;
	
	
	//snmpConfig/{ipAddress} 
		public String SnmpConfig(String ipAddress) throws HandleException{
			try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr+"/snmpConfig/"+ipAddress);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			String result = null;

		
				result = (String) handler.handle(hash);
				return result;
			} catch (Exception e) {
				throw new HandleException(e);
			}

			

		}
		
		//PUT////!!!!!!
		//snmpConfig/{ipAddress} 
				public String SnmpConfigPut(String ipAddress) throws HandleException{
					try {
				
					HashMap hash = new HashMap();
					hash.put(USERNAME, loginId);
					hash.put(PASSWORD, loginPass);
					hash.put(URL, ipAddr+"/snmpConfig/"+ipAddress);
					hash.put(Accept, "application/json");
					hash.put(METHOD, "PUT");

					String result = null;

				
						result = (String) handler.handle(hash);
						return result;
					} catch (Exception e) {
						throw new HandleException(e);
					}

					

				}

}



















