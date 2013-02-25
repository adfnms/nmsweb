package kr.co.adflow.nms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.process.ForeignSourcesProcess;

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
public class ForeignSourcesController {
	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";
	private static final Logger logger = LoggerFactory
			.getLogger(ForeignSourcesController.class);

	private ForeignSourcesProcess controll = ForeignSourcesProcess.getPrcess();

	// foreignSources
	@RequestMapping(value = "/foreignSources", method = RequestMethod.GET)
	public @ResponseBody
	String foreignSources(HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.foreignSources();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// foreignSources/default
	@RequestMapping(value = "/foreignSources/default", method = RequestMethod.GET)
	public @ResponseBody
	String foreignSourcesDefault(HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.foreignSourcesDefault();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// foreignSources/deployed
	@RequestMapping(value = "/foreignSources/deployed", method = RequestMethod.GET)
	public @ResponseBody
	String foreignSourcesDeployed(HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.foreignSourcesDeployed();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// foreignSources/deployed/count *Exception!!!!!!

	@RequestMapping(value = "/foreignSources/deployed/count", method = RequestMethod.GET)
	public @ResponseBody
	String foreignSourcesDeployedCount(HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.foreignSourcesDeployedCount();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// foreignSources/{name}
	@RequestMapping(value = "/foreignSources/{name}", method = RequestMethod.GET)
	public @ResponseBody
	String foreignSources(@PathVariable String name, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.foreignSources(name);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// foreignSources/{name}/detectors
	@RequestMapping(value = "/foreignSources/{name}/detectors", method = RequestMethod.GET)
	public @ResponseBody
	String foreignSourcesDetectors(@PathVariable String name,
			HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.foreignSourcesDetectors(name);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// foreignSources/{name}/detectors/{detector} Parameter

	@RequestMapping(value = "/foreignSources/{name}/detectors/{detector}", method = RequestMethod.GET)
	public @ResponseBody
	String foreignSourcesDetectors(@PathVariable String name,
			@PathVariable String detector, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.foreignSourcesDetectors(name, detector);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// foreignSources/{name}/policies
	@RequestMapping(value = "/foreignSources/{name}/policies", method = RequestMethod.GET)
	public @ResponseBody
	String foreignSourcesPolicies(@PathVariable String name,
			HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.foreignSourcesPolicies(name);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// foreignSources/{name}/policies/{policy}
	@RequestMapping(value = "/foreignSources/{name}/policies/{policy}", method = RequestMethod.GET)
	public @ResponseBody
	String foreignSourcesPolicies(@PathVariable String name,
			@PathVariable String policy, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.foreignSourcesPolicies(name, policy);
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
