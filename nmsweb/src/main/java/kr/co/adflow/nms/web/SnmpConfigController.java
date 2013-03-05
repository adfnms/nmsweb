package kr.co.adflow.nms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.process.SnmpConfigProcess;

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
public class SnmpConfigController {
	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";
	private static final Logger logger = LoggerFactory
			.getLogger(SnmpConfigController.class);
	private SnmpConfigProcess controll = SnmpConfigProcess.getPrcess();

	// snmpConfig/{ipAddress}
	@RequestMapping(value = "/snmpConfig/{ipAddress}", method = RequestMethod.GET)
	public @ResponseBody
	String snmpConfig(@PathVariable String ipAddress, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.SnmpConfig(ipAddress);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}
	//PUT
	// snmpConfig / {IPADDRESS} 
	
	@RequestMapping(value = "/snmpConfig/{ipAddress}", method = RequestMethod.PUT)
	public @ResponseBody
	String snmpConfigPUT(@PathVariable String ipAddress, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.SnmpConfigPut(ipAddress);
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
