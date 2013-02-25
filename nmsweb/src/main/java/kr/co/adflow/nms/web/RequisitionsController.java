package kr.co.adflow.nms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.process.RequisitionsProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class RequisitionsController {
	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";
	private static final Logger logger = LoggerFactory
			.getLogger(RequisitionsController.class);

	private RequisitionsProcess controll = RequisitionsProcess.getPrcess();

	// requisitions
	@RequestMapping(value = "/requisitions", method = RequestMethod.GET)
	public @ResponseBody
	String requisitions(HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		try {
			result = controll.requisitions();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;

	}

	// requisitions/count
	@RequestMapping(value = "/requisitions/count", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsCount(HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		try {
			result = controll.requisitionsCount();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;

	}

	// requisitions/deployed
	@RequestMapping(value = "/requisitions/deployed", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsDeployed(HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		try {
			result = controll.requisitionsDeployed();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;

	}

	// requisitions/deployed/count

	@RequestMapping(value = "/requisitions/deployed/count", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsDeployedCount(HttpServletRequest request)
			throws HandleException {

		logger.info(PATH + request.getRequestURL());
		String result = null;
		try {
			result = controll.requisitionsDeployedCount();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;

	}

	// requisitions/{name}

	@RequestMapping(value = "/requisitions/{name}", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsName(@PathVariable String name,
			HttpServletRequest request) throws HandleException {

		logger.info(PATH + request.getRequestURL());
		String result = null;
		try {
			result = (String) controll.requisitions(name);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// /requisitions/{name}/nodes

	@RequestMapping(value = "/requisitions/{name}/nodes", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsNodes(@PathVariable String name,
			HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.requisitionsNodes(name);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;

	}

	// requisitions/{name}/nodes/{foreignId}
	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsNodes(@PathVariable String name,
			@PathVariable String foreignId, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.requisitionsNodes(name, foreignId);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// /requisitions/{name}/nodes/{foreignId}/interfaces

	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/interfaces", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsNodesInterfaces(@PathVariable String name,
			@PathVariable String foreignId, HttpServletRequest request)
			throws HandleException {

		String result = null;

		logger.info(PATH + request.getRequestURL());

		try {
			result = (String) controll.requisitionsNodesInterfaces(name,
					foreignId);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// /requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}

	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsNodesInterfaces(@PathVariable String name,
			@PathVariable String foreignId, @PathVariable String ipAddress,
			HttpServletRequest request) throws HandleException {

		String result = null;

		logger.info(PATH + request.getRequestURL());

		try {
			result = (String) controll.requisitionsNodesInterfaces(name,
					foreignId, ipAddress);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services

	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsNodesInterfacesServices(@PathVariable String name,
			@PathVariable String foreignId, @PathVariable String ipAddress,
			HttpServletRequest request) throws HandleException {

		String result = null;

		logger.info(PATH + request.getRequestURL());

		try {
			result = (String) controll.requisitionsNodesInterfacesServices(
					name, foreignId, ipAddress);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services/{service}

	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services/{service}", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsNodesInterfacesServices(@PathVariable String name,
			@PathVariable String foreignId, @PathVariable String ipAddress,
			@PathVariable String service, HttpServletRequest request)
			throws HandleException {

		String result = null;

		logger.info(PATH + request.getRequestURL());

		try {
			result = (String) controll.requisitionsNodesInterfacesServices(
					name, foreignId, ipAddress, service);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// requisitions/{name}/nodes/{foreignId}/categories
	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/categories", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsNodesCategories(@PathVariable String name,
			@PathVariable String foreignId, HttpServletRequest request)
			throws HandleException {

		String result = null;

		logger.info(PATH + request.getRequestURL());

		try {
			result = (String) controll.requisitionsNodesCategories(name,
					foreignId);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// requisitions/{name}/nodes/{foreignId}/categories/{categoryName}
	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/categories/{categoryName}", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsNodesCategories(@PathVariable String name,
			@PathVariable String foreignId, @PathVariable String categoryName,
			HttpServletRequest request) throws HandleException {

		String result = null;

		logger.info(PATH + request.getRequestURL());

		try {
			result = (String) controll.requisitionsNodesCategories(name,
					foreignId, categoryName);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// requisitions/{name}/nodes/{foreignId}/assets

	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/assets", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsNodesAssets(@PathVariable String name,
			@PathVariable String foreignId,HttpServletRequest request) throws HandleException {

		String result = null;

		logger.info(PATH + request.getRequestURL());

		try {
			result = (String) controll.requisitionsNodesAssets(name, foreignId);
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
