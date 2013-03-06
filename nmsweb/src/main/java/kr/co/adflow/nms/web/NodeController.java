package kr.co.adflow.nms.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.process.NodeProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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
public class NodeController {

	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";
	private static final Logger logger = LoggerFactory
			.getLogger(NodeController.class);

	private NodeProcess controll = NodeProcess.getProcess();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String node() {

		return "nodeList";
	}

	@RequestMapping(value = "/nodes", method = RequestMethod.GET)
	public @ResponseBody
	String nodes(HttpServletRequest request) throws HandleException {

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
				result = (String) controll.nodesFilter(filter.toString());
			} catch (HandleException e) {
				logger.error("Failed in processing", e);
				throw e;
			}

		} else {

			try {
				result = (String) controll.nodes();
			} catch (HandleException e) {
				logger.error("Failed in processing", e);
				throw e;
			}

		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}", method = RequestMethod.GET)
	public @ResponseBody
	String nodeId(HttpServletRequest request, @PathVariable String id)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) controll.nodes(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/ipinterfaces", method = RequestMethod.GET)
	public @ResponseBody
	String nodeIdIpinterfaces(HttpServletRequest request,
			@PathVariable String id) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) controll.nodesIpInterfaces(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/ipinterfaces/{ipAddress}", method = RequestMethod.GET)
	public @ResponseBody
	String nodeIdIpinterfaces(HttpServletRequest request,
			@PathVariable String id, @PathVariable String ipAddress)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());
		
		logger.debug("AAAAAA::::" + ipAddress);

		try {
			result = (String) controll.nodesIpInterfaces(id, ipAddress);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/ipinterfaces/{ipAddress}/services", method = RequestMethod.GET)
	public @ResponseBody
	String nodeIdIpinterfacesService(HttpServletRequest request,
			@PathVariable String id, @PathVariable String ipAddress)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());
		logger.debug("AAAAAA::::" + ipAddress);
 
		try {
			result = (String) controll.nodesIpInterfacesServices(id, ipAddress);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/ipinterfaces/{ipAddress}/services/{serviceName}", method = RequestMethod.GET)
	public @ResponseBody
	String nodeIdIpinterfacesService(HttpServletRequest request,
			@PathVariable String id, @PathVariable String ipAddress,
			@PathVariable String serviceName) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) controll.nodesIpInterfacesServices(id, ipAddress,
					serviceName);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/snmpinterfaces", method = RequestMethod.GET)
	public @ResponseBody
	String nodeSnmpinterfaces(HttpServletRequest request,
			@PathVariable String id) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) controll.nodesSnmpinterfaces(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/snmpinterfaces/{ifIndex}", method = RequestMethod.GET)
	public @ResponseBody
	String nodeSnmpinterfaces(HttpServletRequest request,
			@PathVariable String id, @PathVariable String ifIndex)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) controll.nodesSnmpinterfaces(id, ifIndex);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/categories", method = RequestMethod.GET)
	public @ResponseBody
	String nodeCategories(HttpServletRequest request, @PathVariable String id)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) controll.nodesCategories(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/categories/{categoryName}", method = RequestMethod.GET)
	public @ResponseBody
	String nodeCategories(HttpServletRequest request, @PathVariable String id,
			@PathVariable String categoryName) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) controll.nodesCategories(id, categoryName);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/assetRecord", method = RequestMethod.GET)
	public @ResponseBody
	String nodeAssetRecord(HttpServletRequest request, @PathVariable String id)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) controll.nodesCategories(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	
	///// DELETE /////
	@RequestMapping(value = "/nodes/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	String nodesDelete(HttpServletRequest request,
			@PathVariable String id) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) controll.nodesDelete(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	
	@RequestMapping(value = "/nodes/{id}/ipinterfaces/{ipAddress}", method = RequestMethod.DELETE)
	public @ResponseBody
	String nodesIpinterfacesDelete(HttpServletRequest request,
			@PathVariable String id, @PathVariable String ipAddress) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) controll.nodesIpinterfacesDelete(id, ipAddress);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	@RequestMapping(value = "/nodes/{id}/ipInterfaces/{ipAddress}/services/{service}", method = RequestMethod.DELETE)
	public @ResponseBody
	String nodesIpInterfacesServicesDelete(HttpServletRequest request,
			@PathVariable String id, @PathVariable String ipAddress, @PathVariable String service) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) controll.nodesIpInterfacesServicesDelete(id, ipAddress, service);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	@RequestMapping(value = "/nodes/{id}/snmpinterfaces/{ifIndex}", method = RequestMethod.DELETE)
	public @ResponseBody
	String nodesSnmpinterfacesDelete(HttpServletRequest request,
			@PathVariable String id, @PathVariable String ifIndex) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) controll.nodesSnmpinterfacesDelete(id, ifIndex);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	@RequestMapping(value = "/nodes/{id}/categories/{categoryName}", method = RequestMethod.DELETE)
	public @ResponseBody
	String nodesCategoriesDelete(HttpServletRequest request,
			@PathVariable String id, @PathVariable String categoryName) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) controll.nodesCategoriesDelete(id, categoryName);
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