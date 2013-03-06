package kr.co.adflow.nms.web;

import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.exception.ValidationException;
import kr.co.adflow.nms.web.service.AcknowledgementsService;
import kr.co.adflow.nms.web.service.AlarmsService;
import kr.co.adflow.nms.web.service.EventsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * AlarmsController
 * 
 * @author kicho@adflow.co.kr
 * @version 1.1
 */
@Controller
public class AlarmsController {

	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";

	private static final Logger logger = LoggerFactory
			.getLogger(AlarmsController.class);
	@Autowired
	private AlarmsService service;
	@Autowired
	private AcknowledgementsService controllAck ;
	@RequestMapping(value = "/alarms", method = RequestMethod.GET)
	public @ResponseBody
	String alarms(HttpServletRequest request) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		// 2013-02-23
		// Parameter check Method 호추 분기
		Enumeration eParam = request.getParameterNames();

		if (eParam.hasMoreElements()) {
			StringBuffer filter = new StringBuffer();

			while (eParam.hasMoreElements()) {
				String pName = (String) eParam.nextElement();
				String pValue = request.getParameter(pName);

				filter.append(pName + "=" + pValue + "&");

			}

			// 마지막 "&"를 삭제.
			filter.deleteCharAt(filter.length() - 1);
			logger.debug("Param:::" + filter.toString());

			try {
				result = (String) service.alarmsFilter(filter.toString());
			} catch (HandleException e) {
				logger.error("Failed in processing", e);
				throw e;
			}

		} else {

			try {
				result = (String) service.alarms();
			} catch (HandleException e) {
				logger.error("Failed in processing", e);
				throw e;
			}

		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/alarms/{id}", method = RequestMethod.GET)
	public @ResponseBody
	String alarms(HttpServletRequest request, @PathVariable String id)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.alarms(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/alarms/count", method = RequestMethod.GET)
	public @ResponseBody
	String alarmsCount(HttpServletRequest request) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.alarmsCount();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/alarms/?comparator=ge&severity=MINOR", method = RequestMethod.GET)
	public @ResponseBody
	String alarmsMINOR(HttpServletRequest request) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.alarmsMINOR();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	// PUT
	// /alarms/{id}?ack=(true|false)
	@RequestMapping(value = "/alarms/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	String alarmsPut(HttpServletRequest request, @PathVariable String id)
			throws HandleException, ValidationException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		// 2013-02-23
		// Parameter check Method 호추 분기
		Enumeration eParam = request.getParameterNames();

		if (eParam.hasMoreElements()) {
			StringBuffer prams = new StringBuffer();

			String pName = (String) eParam.nextElement();

			if (pName.equals("ack")) {

				String pValue = request.getParameter(pName);

				if (pValue.equals("true")) {
					prams.append("alarmId=" + id + "&action=ack");
				} else if (pValue.equals("false")) {
					prams.append("alarmId=" + id + "&action=unack");
				} else {

					logger.error("Must supply the 'ack' parameter, set to either 'true' or 'false'");
					try {
						throw new ValidationException(
								"Must supply the 'ack' parameter, set to either 'true' or 'false'");
					} catch (ValidationException e) {
						throw e;
					}

				}

				try {
					result = (String) controllAck.acksPost(prams.toString());
				} catch (HandleException e) {
					logger.error("Failed in processing", e);
					throw e;
				}

			} else {

				logger.error("Must supply the parameter name, set to either 'ack'");
				try {

					throw new ValidationException(
							"Must supply the parameter name, set to either 'ack'");

				} catch (ValidationException e) {
					throw e;
				}

			}

			logger.debug("Param:::" + prams.toString());

			// try {
			// result = (String) controllAck
			// .acksPost(prams.toString());
			// } catch (HandleException e) {
			// logger.error("Failed in processing", e);
			// throw e;
			// }

		} else {

			try {
				result = (String) controllAck.acksPost("");
			} catch (HandleException e) {
				logger.error("Failed in processing", e);
				throw e;
			}

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

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String processValidationException(ValidationException e) {
		return "{\"code\":\"" + HttpServletResponse.SC_INTERNAL_SERVER_ERROR
				+ "\",\"message\":\"" + e.getMessage() + "\"}";
	}

}