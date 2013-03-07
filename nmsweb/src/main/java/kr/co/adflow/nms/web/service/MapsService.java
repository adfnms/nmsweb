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
public class MapsService {

	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private @Value("#{config['NMSURL']}") String ipAddr;
	private @Value("#{config['LOGINID']}") String loginId;
	private @Value("#{config['LOGINPASS']}") String loginPass;
	private static final String Accept = "accept";
	private static final Logger logger = LoggerFactory
			.getLogger(MapsService.class);

	@Autowired
	private Handler handler;

	// maps

	public String Maps() throws HandleException {
		String result = null;
		try {
			
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/maps");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// maps/{id}
	public String Maps(String id) throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/maps/" + id);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

	// maps/{id}/mapElements
	public String MapsMapElements(String id) throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/maps/" + id + "/mapElements");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return result;
	}

}
