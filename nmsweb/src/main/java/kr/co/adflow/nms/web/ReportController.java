package kr.co.adflow.nms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.service.ReportService;
import kr.co.adflow.nms.web.util.UsersUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ReportController {

	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";

	private static final Logger logger = LoggerFactory
			.getLogger(UsersController.class);
	@Autowired
	private ReportService service;
	private static final String INVALUE = "invalue:::";
	private UsersUtil ut = UsersUtil.getInstance();

	// resport nodeSnmp
	@RequestMapping(value = "/report/{nodeid}/nodesnmp", method = RequestMethod.GET)
	public @ResponseBody
	String reportNodeSnmp(@PathVariable String nodeid,
			HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.reportNode(nodeid);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}
	

	// resport nodeSnmp 
	//&relativetime=lastday
	@RequestMapping(value = "/report/{nodeid}/nodesnmp/day", method = RequestMethod.GET)
	public @ResponseBody
	String reportNodeSnmpDay(@PathVariable String nodeid,
			HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.reportNodeDay(nodeid);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// resport nodeSnmp
	//lastweek    
	@RequestMapping(value = "/report/{nodeid}/nodesnmp/week", method = RequestMethod.GET)
	public @ResponseBody
	String reportNodeSnmpWeek(@PathVariable String nodeid,
			HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.reportNodeWeek(nodeid);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// resport nodeSnmp
	//,lastmonth    
	@RequestMapping(value = "/report/{nodeid}/nodesnmp/month", method = RequestMethod.GET)
	public @ResponseBody
	String reportNodeSnmpMonth(@PathVariable String nodeid,
			HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.reportNodeMonth(nodeid);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// resport nodeSnmp
	//,lastyear
	@RequestMapping(value = "/report/{nodeid}/nodesnmp/year", method = RequestMethod.GET)
	public @ResponseBody
	String reportNodeSnmpYear(@PathVariable String nodeid,
			HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.reportNodeYear(nodeid);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// resport ResponseTime
	@RequestMapping(value = "/report/{nodeid}/responseTime/{ipaddr:.+}", method = RequestMethod.GET)
	public @ResponseBody
	String reportNodeReponseTime(@PathVariable String nodeid,
			@PathVariable String ipaddr, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.resportResponse(nodeid, ipaddr);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	//&reports=all&relativetime=lastday
	@RequestMapping(value = "/report/{nodeid}/responseTime/{ipaddr}/day", method = RequestMethod.GET)
	public @ResponseBody
	String reportNodeDay(@PathVariable String nodeid,
			@PathVariable String ipaddr, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.resportDay(nodeid, ipaddr);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	
	//lastweek
	@RequestMapping(value = "/report/{nodeid}/responseTime/{ipaddr}/week", method = RequestMethod.GET)
	public @ResponseBody
	String reportNodeWeek(@PathVariable String nodeid,
			@PathVariable String ipaddr, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.resportWeek(nodeid, ipaddr);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	//lastmonth
	@RequestMapping(value = "/report/{nodeid}/responseTime/{ipaddr}/month", method = RequestMethod.GET)
	public @ResponseBody
	String reportNodeMonth(@PathVariable String nodeid,
			@PathVariable String ipaddr, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.resportMonth(nodeid, ipaddr);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	//lastyear
	@RequestMapping(value = "/report/{nodeid}/responseTime/{ipaddr}/year", method = RequestMethod.GET)
	public @ResponseBody
	String reportNodeYear(@PathVariable String nodeid,
			@PathVariable String ipaddr, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.resportYear(nodeid, ipaddr);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String processException(Exception e, HttpServletResponse response)
			throws HandleException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return "{\"code\":\"" + HttpServletResponse.SC_INTERNAL_SERVER_ERROR
				+ "\",\"message\":\"" + e.getMessage() + "\"}";
	}

	@ExceptionHandler(HandleException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String processHandleException(HandleException e) {
		return "{\"code\":\"" + HttpServletResponse.SC_INTERNAL_SERVER_ERROR
				+ "\",\"message\":\"" + e.getMessage() + "\"}";
	}

}
