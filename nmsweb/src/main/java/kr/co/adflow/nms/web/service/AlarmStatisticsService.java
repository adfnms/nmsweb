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
public class AlarmStatisticsService {

	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
//	private static final String NMSUrl = "http://192.168.0.63:8980/opennms/rest";
	private @Value("#{config['NMSURL']}") String ipAddr;
	private static final String Accept = "accept";
	private static final Logger logger = LoggerFactory
			.getLogger(AlarmStatisticsService.class);
	@Autowired
	private Handler handler;

	// stats/alarms
	public String statsAlarms() throws HandleException {
		String result = null;
		try {
			
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, ipAddr + "/stats/alarms");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;

	}

	// stats/alarms/by-severity
	public String statsAlarmsBySeverity() throws HandleException {
		String result = null;
		try {
		
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, ipAddr + "/stats/alarms/by-severity");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;

	}

}
