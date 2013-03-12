package kr.co.adflow.nms.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.exception.ValidationException;
import kr.co.adflow.nms.web.mapper.DiscoveryConfigurationMapper;
import kr.co.adflow.nms.web.mapper.NotificationMapper;
import kr.co.adflow.nms.web.service.DiscoveryConfigurationService;
import kr.co.adflow.nms.web.service.NotificationsService;
import kr.co.adflow.nms.web.vo.DestPath.Path;
import kr.co.adflow.nms.web.vo.discoveryConfiguration.DiscoveryConfiguration;
import kr.co.adflow.nms.web.vo.notifications.Notification;

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
public class DiscoveryConfigurationController {

	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";

	@Autowired
	private DiscoveryConfigurationMapper mapper;

	private static final Logger logger = LoggerFactory
			.getLogger(DiscoveryConfigurationController.class);
	
	@Autowired
	private DiscoveryConfigurationService service;

	
	@RequestMapping(value = "/discoveryConfiguration", method = RequestMethod.GET)
	public @ResponseBody
	String discoveryConfiguration(HttpServletRequest request) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.discoveryConfiguration();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}


	// /// POST /////
	@RequestMapping(value = "/discoveryConfiguration", method = RequestMethod.POST)
	public @ResponseBody
	String discoveryConfigurationPost(HttpServletRequest request,
			@RequestBody String data) throws HandleException, MapperException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		DiscoveryConfiguration dConfig = null;
		String xml = null;

		// String data2 =
		// "{"name": "TESTPath3","initial-delay": "0s","target": [{"interval": "0s","name": "admin","autoNotify": "on","command": ["GCMSend","GtalkSend","HybridGCMSend","callHomePhone"]},{"interval": "0m","name": "Admin","autoNotify": "on","command": "javaEmail"},{"interval": "0s","name": "kicho@adflow.co.kr","autoNotify": "on","command": "email"}]}";
		// String data3 =
		// "{\"name\": \"TESTPath2\",\"initial-delay\": \"0s\",\"target\": [{\"interval\": \"0s\",\"name\": \"admin\",\"autoNotify\": \"on\",\"command\": [\"GCMSend\",\"GtalkSend\",\"HybridGCMSend\",\"callHomePhone\"]},{\"interval\": \"0m\",\"name\": \"Admin\",\"autoNotify\": \"on\",\"command\": \"javaEmail\"},{\"interval\": \"0s\",\"name\": \"kicho@adflow.co.kr\",\"autoNotify\": \"on\",\"command\": \"email\"}]}";

		logger.debug("Post Data:::" + data);

		try {

			dConfig = mapper.discoveryConfigurationMapping(data);

			// xml = (String) mapper.schoedOutagePostMapping(schoedOutage);

		} catch (MapperException e) {
			logger.error("Failed Mapping", e);
			throw e;
		}

		// logger.debug("adf:::" + path.getTarget().get(0).getName());
		// logger.debug("adf222:::" +
		// path.getTarget().get(0).getCommand().get(0).toString());

		try {
			result = (String) service.discoveryConfigurationPost(dConfig);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		result = "{\"result\":\"" + result + "\"}";

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