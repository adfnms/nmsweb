package kr.co.adflow.nms.web;

import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.exception.HandleException;
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
 * /** Handles requests for the application home page.
 */
@Controller
public class AcknowledgementsController {

	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";

	private static final Logger logger = LoggerFactory
			.getLogger(AcknowledgementsController.class);
	@Autowired
	private AcknowledgementsService service;

	@RequestMapping(value = "/acks", method = RequestMethod.GET)
	public @ResponseBody
	String acks(HttpServletRequest request) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());
		
		// 2013-02-23
		// Parameter check Method 호추 분기
		Enumeration eParam = request.getParameterNames();

		if (eParam.hasMoreElements()) {
			StringBuffer filter = new StringBuffer();

			while (eParam.hasMoreElements()) {
				String pName = (String) eParam.nextElement();
				String pValue = null;
				if(pName.equals("query")){
					pValue = URLEncoder.encode(request.getParameter(pName));
				}else {
					pValue = request.getParameter(pName);
				}

				filter.append(pName + "=" + pValue + "&");

			}

			// 마지막 "&"를 삭제.
			filter.deleteCharAt(filter.length() - 1);
			logger.debug("Param:::" + filter.toString());

			try {
				result = (String) service
						.acksFilter(filter.toString());
			} catch (HandleException e) {
				logger.error("Failed in processing", e);
				throw e;
			}

		} else {

			try {
				result = (String) service.acks();
			} catch (HandleException e) {
				logger.error("Failed in processing", e);
				throw e;
			}

		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/acks/{id}", method = RequestMethod.GET)
	public @ResponseBody
	String acks(HttpServletRequest request, @PathVariable String id)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.acks(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/acks/count", method = RequestMethod.GET)
	public @ResponseBody
	String acksCount(HttpServletRequest request) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.acksCount();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	
	@RequestMapping(value = "/acks", method = RequestMethod.POST)
	public @ResponseBody
	String acksPost(HttpServletRequest request) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());
		
		// 2013-02-23
		// Parameter check Method 호추 분기
		Enumeration eParam = request.getParameterNames();

		if (eParam.hasMoreElements()) {
			StringBuffer prams = new StringBuffer();

			while (eParam.hasMoreElements()) {
				String pName = (String) eParam.nextElement();
				String pValue = request.getParameter(pName);

				prams.append(pName + "=" + pValue + "&");

			}

			// 마지막 "&"를 삭제.
			prams.deleteCharAt(prams.length() - 1);
			logger.debug("Param:::" + prams.toString());

			try {
				result = (String) service
						.acksPost(prams.toString());
			} catch (HandleException e) {
				logger.error("Failed in processing", e);
				throw e;
			}


		} else {

			try {
				result = (String) service.acksPost("");
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

}