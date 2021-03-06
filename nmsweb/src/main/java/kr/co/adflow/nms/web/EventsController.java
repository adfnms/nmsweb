package kr.co.adflow.nms.web;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.exception.ValidationException;
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
 * /** Handles requests for the application home page.
 */
@Controller
public class EventsController {

	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";

	private static final Logger logger = LoggerFactory
			.getLogger(EventsController.class);
	@Autowired
	private EventsService service;

	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public @ResponseBody
	String events(HttpServletRequest request) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		// 2013-02-23
		// Parameter check �� ȣ�� �б�
		Enumeration eParam = request.getParameterNames();

		if (eParam.hasMoreElements()) {
			StringBuffer filter = new StringBuffer();

			while (eParam.hasMoreElements()) {
				String pName = (String) eParam.nextElement();
				String pValue = null;
				if (pName.equals("query")) {
					pValue = URLEncoder.encode(request.getParameter(pName));
				} else {
					pValue = request.getParameter(pName);
				}

				filter.append(pName + "=" + pValue + "&");

			}

			// ������ "&" ����.
			filter.deleteCharAt(filter.length() - 1);
			logger.debug("Param:::" + filter.toString());

			try {

				result = (String) service.eventsFilter(filter.toString());
				// result = (String)
				// service.eventsFilter("query=this_.nodeId%20%3D%20'28'");

			} catch (HandleException e) {
				logger.error("Failed in processing", e);
				throw e;
			}

		} else {

			try {
				result = (String) service.events();
			} catch (HandleException e) {
				logger.error("Failed in processing", e);
				throw e;
			}

		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
	public @ResponseBody
	String events(HttpServletRequest request, @PathVariable String id)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.events(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/events/count", method = RequestMethod.GET)
	public @ResponseBody
	String eventsCount(HttpServletRequest request) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.eventsCount();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/eventquery", method = RequestMethod.GET)
	public @ResponseBody
	String eventquery(HttpServletRequest request) throws HandleException,
			ValidationException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		String eventseverity = request.getParameter("eventseverity");
		String limit = request.getParameter("limit");
		

		if (eventseverity == null|| limit == null) {

			logger.error("Must supply the parameter name, set to either 'eventseverity' and 'limit'");
			try {

				throw new ValidationException(
						"Must supply the parameter name, set to either 'eventseverity' and 'limit'");

			} catch (ValidationException e) {
				throw e;
			}

		}
		
		try {
			result = (String) service.eventquery(eventseverity,limit);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		
		

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	@RequestMapping(value = "/eventqueryCount", method = RequestMethod.GET)
	public @ResponseBody
	String eventqueryCount(HttpServletRequest request) throws HandleException,
			ValidationException {

		String result = null;
		logger.info(PATH + request.getRequestURI());
		
		String eventseverity = request.getParameter("eventseverity");

		if (eventseverity == null) {

			logger.error("Must supply the parameter name, set to either 'eventseverity'");
			try {

				throw new ValidationException(
						"Must supply the parameter name, set to either 'eventseverity'");

			} catch (ValidationException e) {
				throw e;
			}

		}


		try {
			result = (String) service.eventqueryCount(eventseverity);
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