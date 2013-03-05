package kr.co.adflow.nms.web;

import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.process.OutagesProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class OutagesController {

	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";

	private static final Logger logger = LoggerFactory
			.getLogger(OutagesController.class);

	private OutagesProcess controll = OutagesProcess.getProcess();

	@RequestMapping(value = "/outages", method = RequestMethod.GET)
	public @ResponseBody
	String outages(HttpServletRequest request) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		// 2013-02-23
		// Parameter check �� ȣ�� �б�
		Enumeration eParam = request.getParameterNames();

		if (eParam.hasMoreElements()) {
			StringBuffer filter = new StringBuffer();

			while (eParam.hasMoreElements()) {
				String pName = (String) eParam.nextElement();
				String pValue = request.getParameter(pName);

				filter.append(pName + "=" + pValue + "&");

			}

			// ������ "&" ����.
			filter.deleteCharAt(filter.length() - 1);
			logger.debug("Param:::" + filter.toString());

			try {
				result = (String) controll
						.outagesFilter(filter.toString());
			} catch (HandleException e) {
				logger.error("Failed in processing", e);
				throw e;
			}

		} else {

			try {
				result = (String) controll.outages();
			} catch (HandleException e) {
				logger.error("Failed in processing", e);
				throw e;
			}

		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/outages/{id}", method = RequestMethod.GET)
	public @ResponseBody
	String outages(HttpServletRequest request, @PathVariable String id)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) controll.outages(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/outages/count", method = RequestMethod.GET)
	public @ResponseBody
	String outagesCount(HttpServletRequest request) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) controll.outagesCount();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/outages/forNode/{nodeId}", method = RequestMethod.GET)
	public @ResponseBody
	String outagesForNode(HttpServletRequest request,
			@PathVariable String nodeId) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) controll.outagesForNode(nodeId);
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