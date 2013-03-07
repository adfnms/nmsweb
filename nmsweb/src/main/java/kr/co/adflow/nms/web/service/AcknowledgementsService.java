package kr.co.adflow.nms.web.service;

import java.util.HashMap;

import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.exception.HandleException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * AcknowledgementsProcess
 * 
 * @author kicho@adflow.co.kr
 * @version 1.1
 */
@Service
public class AcknowledgementsService {

	private static final String ContentType = "contentType";
	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String Accept = "accept";
	private @Value("#{config['NMSURL']}") String ipAddr;
	private @Value("#{config['LOGINID']}") String loginId;
	private @Value("#{config['LOGINPASS']}") String loginPass;
	
	private static final Logger logger = LoggerFactory
			.getLogger(AcknowledgementsService.class);
	@Autowired
	private Handler handler;


	
	public String acksFilter(String filter) throws HandleException {
		
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/acks?"+filter);
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String acks() throws HandleException {
	
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/acks?limit=0");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	public String acks(String id) throws HandleException {
	
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/acks/"+id);
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String acksCount() throws HandleException {
		
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(URL, ipAddr + "/acks/count");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	
	/// POST
	///acks 
	public String acksPost(String prams) throws HandleException {
		
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/json");
		hash.put(ContentType, "application/x-www-form-urlencoded");
		hash.put(URL, ipAddr + "/acks?"+prams);
		hash.put(METHOD, "POST");
		

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	


}
