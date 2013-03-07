package kr.co.adflow.nms.web.service;

import java.util.HashMap;

import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.util.ReportUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private @Value("#{config['REPORTURL']}")
	String reportUrl;
	private @Value("#{config['LOGINID']}") String loginId;
	private @Value("#{config['LOGINPASS']}") String loginPass;

	private static final String Accept = "accept";
	private static final String DATA = "data";
	private static final String CONTENTTYPE = "contentType";
	private static final Logger logger = LoggerFactory
			.getLogger(UserService.class);
	@Autowired
	private Handler handler;
	@Autowired
	private ReportUtil util;

	// node[53].nodeSnmp[]
	public String reportNode(String nodeid) throws HandleException {
		String result = null;
		String jsonResult = null;
		try {

			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, reportUrl + "node[" + nodeid + "].nodeSnmp[]");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

			jsonResult = util.graphUrl(result);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		logger.debug("JSONRESULT:::" + jsonResult);
		return jsonResult;
	}
	
	// node[53].nodeSnmp[]
	
	//&relativetime=lastday
	public String reportNodeDay(String nodeid) throws HandleException {
		String result = null;
		String jsonResult = null;
		try {

			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, reportUrl + "node[" + nodeid + "].nodeSnmp[]&relativetime=lastday");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

			jsonResult = util.graphUrl(result);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		logger.debug("JSONRESULT:::" + jsonResult);
		return jsonResult;
	}
	
	// node[53].nodeSnmp[]
	//&relativetime=lastweek
	public String reportNodeWeek(String nodeid) throws HandleException {
		String result = null;
		String jsonResult = null;
		try {

			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, reportUrl + "node[" + nodeid + "].nodeSnmp[]&relativetime=lastweek");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

			jsonResult = util.graphUrl(result);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		logger.debug("JSONRESULT:::" + jsonResult);
		return jsonResult;
	}
	
	// node[53].nodeSnmp[]
	//&relativetime=lastmonth    
	public String reportNodeMonth(String nodeid) throws HandleException {
		String result = null;
		String jsonResult = null;
		try {

			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, reportUrl + "node[" + nodeid + "].nodeSnmp[]&relativetime=lastmonth  ");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

			jsonResult = util.graphUrl(result);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		logger.debug("JSONRESULT:::" + jsonResult);
		return jsonResult;
	}
	
	// node[53].nodeSnmp[]
	//&relativetime=lastyear
	public String reportNodeYear(String nodeid) throws HandleException {
		String result = null;
		String jsonResult = null;
		try {

			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, reportUrl + "node[" + nodeid + "].nodeSnmp[]&relativetime=lastyear");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

			jsonResult = util.graphUrl(result);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		logger.debug("JSONRESULT:::" + jsonResult);
		return jsonResult;
	}

	public String resportResponse(String nodeid, String ipaddr)
			throws HandleException {
		String result = null;
		String jsonResult = null;

		try {
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, reportUrl + "node[" + nodeid + "].responseTime["
					+ ipaddr + "]");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");
			result = (String) handler.handle(hash);
			jsonResult = util.graphUrl(result);
		} catch (Exception e) {
			throw new HandleException(e);
		}
		return jsonResult;
	}
	
	//lastweek
	public String resportWeek(String nodeid, String ipaddr)
			throws HandleException {
		String result = null;
		String jsonResult = null;

		try {
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, reportUrl + "node[" + nodeid + "].responseTime["
					+ ipaddr + "]&relativetime=lastweek");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");
			result = (String) handler.handle(hash);
			jsonResult = util.graphUrl(result);
		} catch (Exception e) {
			throw new HandleException(e);
		}
		return jsonResult;
	}
	
	//lastmonth
	public String resportMonth(String nodeid, String ipaddr)
			throws HandleException {
		String result = null;
		String jsonResult = null;

		try {
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, reportUrl + "node[" + nodeid + "].responseTime["
					+ ipaddr + "]&relativetime=lastmonth");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");
			result = (String) handler.handle(hash);
			jsonResult = util.graphUrl(result);
		} catch (Exception e) {
			throw new HandleException(e);
		}
		return jsonResult;
	}
	//,lastyear
	public String resportYear(String nodeid, String ipaddr)
			throws HandleException {
		String result = null;
		String jsonResult = null;

		try {
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, reportUrl + "node[" + nodeid + "].responseTime["
					+ ipaddr + "]&relativetime=lastyear");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");
			result = (String) handler.handle(hash);
			jsonResult = util.graphUrl(result);
		} catch (Exception e) {
			throw new HandleException(e);
		}
		return jsonResult;
	}
	
	//lastday
	public String resportDay(String nodeid, String ipaddr)
			throws HandleException {
		String result = null;
		String jsonResult = null;

		try {
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, reportUrl + "node[" + nodeid + "].responseTime["
					+ ipaddr + "]&relativetime=lastday");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");
			result = (String) handler.handle(hash);
			jsonResult = util.graphUrl(result);
		} catch (Exception e) {
			throw new HandleException(e);
		}
		return jsonResult;
	}

}
