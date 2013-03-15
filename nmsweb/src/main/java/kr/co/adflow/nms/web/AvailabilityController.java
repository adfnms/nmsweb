package kr.co.adflow.nms.web;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.exception.ValidationException;
import kr.co.adflow.nms.web.mapper.NodeMapper;
import kr.co.adflow.nms.web.mapper.PathOutagesMapper;
import kr.co.adflow.nms.web.service.AvailabilityService;
import kr.co.adflow.nms.web.service.MapsService;
import kr.co.adflow.nms.web.service.NodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * /** Handles requests for the application home page.
 */
@Controller
public class AvailabilityController {

	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";
	private static final Logger logger = LoggerFactory
			.getLogger(AvailabilityController.class);

	@Autowired
	private AvailabilityService service; 
	


	
	@RequestMapping(value = "/availability/node", method = RequestMethod.GET)
	public @ResponseBody
	String nodeAvailability(HttpServletRequest request)
			throws HandleException, ValidationException {
		ArrayList<Integer> nodeIds = new ArrayList();
		
		String result = null;

		logger.info(PATH + request.getRequestURI());
		
		String[] ids =null;
		
		Enumeration eParam = request.getParameterNames();
		
		if (eParam.hasMoreElements()) {
			StringBuffer prams = new StringBuffer();

			String pName = (String) eParam.nextElement();

			if (pName.equals("nodeId")) {

				ids = request.getParameterValues(pName);
				
				for (int i = 0; i < ids.length; i++) {
					nodeIds.add(Integer.parseInt(ids[i]));
				}
				
			}else {

				logger.error("Must supply the parameter name, set to either 'nodeId'");
				try {

					throw new ValidationException(
							"Must supply the parameter name, set to either 'nodeId'");

				} catch (ValidationException e) {
					throw e;
				}
			}

		} else {

			logger.error("Must supply the parameter name, set to either 'nodeId'");
			try {

				throw new ValidationException(
						"Must supply the parameter name, set to either 'nodeId'");

			} catch (ValidationException e) {
				throw e;
			}

		}
		

		logger.debug("idList id:::" + nodeIds.get(0).toString());

		try {
			result = (String) service.nodeAvailability(nodeIds);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		
		return result;
	}
	
	
	@RequestMapping(value = "/availability/node/{nodeId}/interface", method = RequestMethod.GET)
	public @ResponseBody
	String interfaceAvailability(HttpServletRequest request, @PathVariable int nodeId)
			throws HandleException, ValidationException {
		ArrayList<String> ipAddrs = new ArrayList();
		
		String result = null;

		logger.info(PATH + request.getRequestURI());
		
		String[] ids =null;
		
		Enumeration eParam = request.getParameterNames();
		
		if (eParam.hasMoreElements()) {
			StringBuffer prams = new StringBuffer();

			String pName = (String) eParam.nextElement();

			if (pName.equals("ipAddr")) {

				ids = request.getParameterValues(pName);
				
				for (int i = 0; i < ids.length; i++) {
					ipAddrs.add(ids[i]);
				}
				
			}else {

				logger.error("Must supply the parameter name, set to either 'ipAddr'");
				try {

					throw new ValidationException(
							"Must supply the parameter name, set to either 'ipAddr'");

				} catch (ValidationException e) {
					throw e;
				}
			}

		} else {

			logger.error("Must supply the parameter name, set to either 'ipAddr'");
			try {

				throw new ValidationException(
						"Must supply the parameter name, set to either 'ipAddr'");

			} catch (ValidationException e) {
				throw e;
			}

		}
		

		logger.debug("idList id:::" + ipAddrs.get(0).toString());

		try {
			result = (String) service.interfaceAvailability(nodeId, ipAddrs);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		
		return result;
	}
	
	@RequestMapping(value = "/availability/node/{nodeId}/interface/{ipAddr}/service", method = RequestMethod.GET)
	public @ResponseBody
	String serviceAvailability(HttpServletRequest request, @PathVariable int nodeId, @PathVariable String ipAddr)
			throws HandleException, ValidationException {
		ArrayList<Integer> serviceIds = new ArrayList();
		
		String result = null;

		logger.info(PATH + request.getRequestURI());
		
		String[] ids =null;
		
		Enumeration eParam = request.getParameterNames();
		
		if (eParam.hasMoreElements()) {
			StringBuffer prams = new StringBuffer();

			String pName = (String) eParam.nextElement();

			if (pName.equals("serviceId")) {

				ids = request.getParameterValues(pName);
				
				for (int i = 0; i < ids.length; i++) {
					serviceIds.add(Integer.parseInt(ids[i]));
				}
				
			}else {

				logger.error("Must supply the parameter name, set to either 'serviceId'");
				try {

					throw new ValidationException(
							"Must supply the parameter name, set to either 'serviceId'");

				} catch (ValidationException e) {
					throw e;
				}
			}

		} else {

			logger.error("Must supply the parameter name, set to either 'serviceId'");
			try {

				throw new ValidationException(
						"Must supply the parameter name, set to either 'serviceId'");

			} catch (ValidationException e) {
				throw e;
			}

		}
		

		logger.debug("idList id:::" + serviceIds.get(0).toString());

		try {
			result = (String) service.serviceAvailability(nodeId, ipAddr, serviceIds);
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
