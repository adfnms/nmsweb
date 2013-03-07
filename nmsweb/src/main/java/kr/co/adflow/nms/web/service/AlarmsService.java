package kr.co.adflow.nms.web.service;

import java.util.HashMap;
import java.util.Locale;

import kr.co.adflow.nms.web.DefaultHandlerImpl;
import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.HandlerFactory;
import kr.co.adflow.nms.web.exception.HandleException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * AlarmsProcess
 * 
 * @author kicho@adflow.co.kr
 * @version 1.1
 */
@Service
public class AlarmsService {

	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String Accept = "accept";
	private @Value("#{config['NMSURL']}") String ipAddr;
	private @Value("#{config['LOGINID']}") String loginId;
	private @Value("#{config['LOGINPASS']}") String loginPass;
	private static final Logger logger = LoggerFactory
			.getLogger(AlarmsService.class);
	@Autowired
	private Handler handler;

	
	public String alarmsFilter(String filter) throws HandleException {
		
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/alarms?"+filter);
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String alarms() throws HandleException {
		
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/alarms?limit=0");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	public String alarms(String id) throws HandleException {
		
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/alarms/"+id);
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

	public String alarmsCount() throws HandleException {
	
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(URL, ipAddr + "/alarms/count");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}
	
	public String alarmsMINOR() throws HandleException {
		
		HashMap hash = new HashMap();

		hash.put(USERNAME, loginId);
		hash.put(PASSWORD, loginPass);
		hash.put(Accept, "application/json");
		hash.put(URL, ipAddr + "/alarms?comparator=ge&severity=MINOR");
		hash.put(METHOD, "GET");

		String result = null;

		try {
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}



}
