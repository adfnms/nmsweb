package kr.co.adflow.nms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.process.MapsProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class MapsController {
	private static final Logger logger = LoggerFactory
			.getLogger(MapsController.class);

	private MapsProcess controll = MapsProcess.getPrcess();
	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";

	// maps
	@RequestMapping(value = "/maps", method = RequestMethod.GET)
	public @ResponseBody
	String maps(HttpServletRequest request) throws HandleException {

		logger.info(PATH + request.getRequestURL());

		String result = null;
		try {
			result = (String) controll.Maps();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// maps/{id}
	@RequestMapping(value = "/maps/{id}", method = RequestMethod.GET)
	public @ResponseBody
	String maps(@PathVariable String id, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.Maps(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// maps/{id}/mapElements
	@RequestMapping(value = "/maps/{id}/mapElements", method = RequestMethod.GET)
	public @ResponseBody
	String mapsMapElements(@PathVariable String id, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.MapsMapElements(id);
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
