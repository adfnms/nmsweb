package kr.co.adflow.nms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.service.AlarmStatisticsService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class AlarmStatisticsController {

	private static final Logger logger = LoggerFactory
			.getLogger(RequisitionsController.class);
	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";
	@Autowired
	private AlarmStatisticsService service;

	// stats/alarms
	@RequestMapping(value = "/stats/alarms", method = RequestMethod.GET)
	public @ResponseBody
	String statsAlarms(HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.statsAlarms();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// stats/alarms/by-severity

	@RequestMapping(value = "/stats/alarms/by-severity", method = RequestMethod.GET)
	public @ResponseBody
	String statsAlarmsBySeverity(HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.statsAlarmsBySeverity();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String processException(Exception e, HttpServletResponse response) {
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
