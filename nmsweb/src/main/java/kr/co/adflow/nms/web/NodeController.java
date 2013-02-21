package kr.co.adflow.nms.web;

import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.node.NodeProcess;

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

/**
 * /** Handles requests for the application home page.
 */
@Controller
public class NodeController {

	private static final Logger logger = LoggerFactory
			.getLogger(NodeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String node() {

		return "nodeList";
	}

	// exception templete
	// modified by nadir
	@RequestMapping(value = "/nodes", method = RequestMethod.GET)
	public @ResponseBody
	String nodes() throws HandleException {

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		try {
			result = (String) controll.nodes();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		return result;
	}

	@RequestMapping(value = "/nodes/{id}", method = RequestMethod.GET)
	public @ResponseBody
	String nodeId(@PathVariable String id) {

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		System.out.println("ddddd");
		logger.info("daadfd");

		try {
			result = (String) controll.nodes(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "/nodes/{id}/ipinterfaces", method = RequestMethod.GET)
	public @ResponseBody
	String nodeIdIpinterfaces(@PathVariable String id) {

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		try {
			result = (String) controll.nodesIpInterfaces(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "/nodes/{id}/ipinterfaces/{ipAddress}", method = RequestMethod.GET)
	public @ResponseBody
	String nodeIdIpinterfaces(@PathVariable String id,
			@PathVariable String ipAddress) {

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		try {
			result = (String) controll.nodesIpInterfaces(id, ipAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "/nodes/{id}/ipinterfaces/{ipAddress}/services", method = RequestMethod.GET)
	public @ResponseBody
	String nodeIdIpinterfacesService(@PathVariable String id,
			@PathVariable String ipAddress) {

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		try {
			result = (String) controll.nodesIpInterfacesServices(id, ipAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "/nodes/{id}/ipinterfaces/{ipAddress}/services/{serviceName}", method = RequestMethod.GET)
	public @ResponseBody
	String nodeIdIpinterfacesService(@PathVariable String id,
			@PathVariable String ipAddress, @PathVariable String serviceName) {

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		try {
			result = (String) controll.nodesIpInterfacesServices(id, ipAddress,
					serviceName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "/nodes/{id}/snmpinterfaces", method = RequestMethod.GET)
	public @ResponseBody
	String nodeSnmpinterfaces(@PathVariable String id) {

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		try {
			result = (String) controll.nodesSnmpinterfaces(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "/nodes/{id}/snmpinterfaces/{ifIndex}", method = RequestMethod.GET)
	public @ResponseBody
	String nodeSnmpinterfaces(@PathVariable String id,
			@PathVariable String ifIndex) {

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		try {
			result = (String) controll.nodesSnmpinterfaces(id, ifIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "/nodes/{id}/categories", method = RequestMethod.GET)
	public @ResponseBody
	String nodeCategories(@PathVariable String id) {

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		try {
			result = (String) controll.nodesCategories(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "/nodes/{id}/categories/{categoryName}", method = RequestMethod.GET)
	public @ResponseBody
	String nodeCategories(@PathVariable String id,
			@PathVariable String categoryName) {

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		try {
			result = (String) controll.nodesCategories(id, categoryName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "/nodes/{id}/assetRecord", method = RequestMethod.GET)
	public @ResponseBody
	String nodeAssetRecord(@PathVariable String id) {

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		try {
			result = (String) controll.nodesCategories(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

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