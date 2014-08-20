package kr.co.adflow.nms.web.service;

import java.util.HashMap;

import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.exception.HandleException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GraphService {
	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	
	private @Value("#{config['GRAPHSELECTURL']}")
	String graphSelectUrl;
	private @Value("#{config['GRAPHNODEURL']}")
	String graphNodeUrl;
	private @Value("#{config['GRAPHRESOURCEURL']}")
	String graphResouceUrl;
	
	
	private static final String Accept = "accept";
	private @Value("#{config['LOGINID']}")
	String loginId;
	private @Value("#{config['LOGINPASS']}")
	String loginPass;
	private static final Logger logger = LoggerFactory
			.getLogger(GraphService.class);
	@Autowired
	private Handler handler;

	public String graphList() throws HandleException {
		String result = null;

		try {
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, graphSelectUrl);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		logger.debug("result:::" + result);
		return result;
	}

	// //
	// http://192.168.0.5:8980/opennms/graph/chooseresource.htm?reports=all&parentResourceId=node[53]

	public String graphListNodeId(String nodeid) throws HandleException {
		String result = null;

		try {
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
//			hash.put(URL, graphNodeUrl+"node["+nodeid+"]");
			hash.put(URL, graphNodeUrl+nodeid);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		logger.debug("result:::" + result);
		return result;
	}
	
	//http://192.168.0.5:8980/opennms/graph/results.htm?reports=all&resourceId=

	
	public String graphResourceIdSer(String resourceId) throws HandleException {
		String result = null;

		try {
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, graphResouceUrl+resourceId);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		logger.debug("result:::" + result);
		return result;
	}
	
	public String graphResourceIdSerDay(String resourceId) throws HandleException {
		String result = null;

		try {
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, graphResouceUrl+resourceId+"&relativetime=lastday");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		logger.debug("result:::" + result);
		return result;
	}
	
	public String graphResourceIdSerWeek(String resourceId) throws HandleException {
		String result = null;

		try {
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, graphResouceUrl+resourceId+"&relativetime=lastweek");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		logger.debug("result:::" + result);
		return result;
	}
	
	public String graphResourceIdSerMonth(String resourceId) throws HandleException {
		String result = null;

		try {
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, graphResouceUrl+resourceId+"&relativetime=lastmonth");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		logger.debug("result:::" + result);
		return result;
	}
	
	public String graphResourceIdSerYear(String resourceId) throws HandleException {
		String result = null;

		try {
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, graphResouceUrl+resourceId+"&relativetime=lastyear");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		logger.debug("result:::" + result);
		return result;
	}
	
	public String graphResourceIdSerCustom(String resourceId,String customTime) throws HandleException {
		String result = null;

		try {
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, graphResouceUrl+resourceId+"&relativetime=custom&zoom="+customTime);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		logger.debug("result:::" + result);
		return result;
	}
}









