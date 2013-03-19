package kr.co.adflow.nms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.exception.UtilException;
import kr.co.adflow.nms.web.service.ReportService;
import kr.co.adflow.nms.web.util.ReportUtil;
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
	@Autowired
	private ReportUtil rutil;
	@Autowired
	UsersUtil ut;
	private static final String INVALUE = "invalue:::";

	// resport nodeSnmp
	@RequestMapping(value = "/report/{nodeid}/nodesnmp", method = RequestMethod.GET)
	public @ResponseBody
	String reportNodeSnmp(@PathVariable String nodeid,
			HttpServletRequest request) throws HandleException, UtilException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		String jsonResult = null;
		try {
			result = (String) service.reportNode(nodeid);

			jsonResult = rutil.graphUrl(result);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		} catch (UtilException e) {
			logger.error("Failed in Util..", e);
			throw e;
		}
		logger.debug(RETURNRESULT + jsonResult);
		return jsonResult;
	}

	// resport nodeSnmp
	// &relativetime=lastday
	@RequestMapping(value = "/report/{nodeid}/nodesnmp/day", method = RequestMethod.GET)
	public @ResponseBody
	String reportNodeSnmpDay(@PathVariable String nodeid,
			HttpServletRequest request) throws HandleException, UtilException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		String jsonResult = null;
		try {
			result = (String) service.reportNodeDay(nodeid);
			jsonResult = rutil.graphUrl(result);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		} catch (UtilException e) {
			logger.error("Failed in util", e);
			throw e;
		}
		logger.debug(RETURNRESULT + jsonResult);
		return jsonResult;
	}

	// resport nodeSnmp
	// lastweek
	@RequestMapping(value = "/report/{nodeid}/nodesnmp/week", method = RequestMethod.GET)
	public @ResponseBody
	String reportNodeSnmpWeek(@PathVariable String nodeid,
			HttpServletRequest request) throws HandleException, UtilException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		String jsonResult = null;
		try {
			result = (String) service.reportNodeWeek(nodeid);
			jsonResult = rutil.graphUrl(result);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		} catch (UtilException e) {
			logger.error("Failed in util", e);
			throw e;
		}
		logger.debug(RETURNRESULT + jsonResult);
		return jsonResult;
	}

	// resport nodeSnmp
	// ,lastmonth
	@RequestMapping(value = "/report/{nodeid}/nodesnmp/month", method = RequestMethod.GET)
	public @ResponseBody
	String reportNodeSnmpMonth(@PathVariable String nodeid,
			HttpServletRequest request) throws HandleException, UtilException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		String jsonResult = null;

		try {
			result = (String) service.reportNodeMonth(nodeid);
			jsonResult = rutil.graphUrl(result);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		} catch (UtilException e) {
			logger.error("Failed in util", e);
			throw e;
		}
		logger.debug(RETURNRESULT + jsonResult);
		return jsonResult;
	}

	// resport nodeSnmp
	// ,lastyear
	@RequestMapping(value = "/report/{nodeid}/nodesnmp/year", method = RequestMethod.GET)
	public @ResponseBody
	String reportNodeSnmpYear(@PathVariable String nodeid,
			HttpServletRequest request) throws HandleException, UtilException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		String jsonResult = null;
		try {
			result = (String) service.reportNodeYear(nodeid);
			jsonResult = rutil.graphUrl(result);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		} catch (UtilException e) {
			logger.error("Failed in util", e);
			throw e;
		}
		logger.debug(RETURNRESULT + jsonResult);
		return jsonResult;
	}

	// resport ResponseTime
	@RequestMapping(value = "/report/{nodeid}/responseTime/{ipaddr:.+}", method = RequestMethod.GET)
	public @ResponseBody
	String reportNodeReponseTime(@PathVariable String nodeid,
			@PathVariable String ipaddr, HttpServletRequest request)
			throws HandleException, UtilException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		String jsonResult = null;
		try {
			result = (String) service.resportResponse(nodeid, ipaddr);
			jsonResult = rutil.graphUrl(result);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		} catch (UtilException e) {
			logger.error("Failed in util", e);
			throw e;
		}
		logger.debug(RETURNRESULT + jsonResult);
		return jsonResult;
	}

	// &reports=all&relativetime=lastday
	@RequestMapping(value = "/report/{nodeid}/responseTime/{ipaddr}/day", method = RequestMethod.GET)
	public @ResponseBody
	String reportNodeDay(@PathVariable String nodeid,
			@PathVariable String ipaddr, HttpServletRequest request)
			throws HandleException, UtilException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		String jsonResult = null;
		try {
			result = (String) service.resportDay(nodeid, ipaddr);
			jsonResult = rutil.graphUrl(result);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		} catch (UtilException e) {
			logger.error("Failed in util", e);
			throw e;
		}
		logger.debug(RETURNRESULT + jsonResult);
		return jsonResult;
	}

	// lastweek
	@RequestMapping(value = "/report/{nodeid}/responseTime/{ipaddr}/week", method = RequestMethod.GET)
	public @ResponseBody
	String reportNodeWeek(@PathVariable String nodeid,
			@PathVariable String ipaddr, HttpServletRequest request)
			throws HandleException, UtilException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		String jsonResult = null;

		try {
			result = (String) service.resportWeek(nodeid, ipaddr);
			jsonResult = rutil.graphUrl(result);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		} catch (UtilException e) {
			logger.error("Failed in util", e);
			throw e;
		}
		logger.debug(RETURNRESULT + jsonResult);
		return jsonResult;
	}

	// lastmonth
	@RequestMapping(value = "/report/{nodeid}/responseTime/{ipaddr}/month", method = RequestMethod.GET)
	public @ResponseBody
	String reportNodeMonth(@PathVariable String nodeid,
			@PathVariable String ipaddr, HttpServletRequest request)
			throws HandleException, UtilException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		String jsonResult = null;
		try {
			result = (String) service.resportMonth(nodeid, ipaddr);
			jsonResult = rutil.graphUrl(result);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		} catch (UtilException e) {
			logger.error("Failed in util", e);
			throw e;
		}
		logger.debug(RETURNRESULT + jsonResult);
		return jsonResult;
	}

	// lastyear
	@RequestMapping(value = "/report/{nodeid}/responseTime/{ipaddr}/year", method = RequestMethod.GET)
	public @ResponseBody
	String reportNodeYear(@PathVariable String nodeid,
			@PathVariable String ipaddr, HttpServletRequest request)
			throws HandleException, UtilException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		String jsonResult = null;

		try {
			result = (String) service.resportYear(nodeid, ipaddr);
			jsonResult = rutil.graphUrl(result);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		} catch (UtilException e) {
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

	@ExceptionHandler(UtilException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String processUtilException(HandleException e) {
		return "{\"code\":\"" + HttpServletResponse.SC_INTERNAL_SERVER_ERROR
				+ "\",\"message\":\"" + e.getMessage() + "\"}";
	}

}
