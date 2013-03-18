package kr.co.adflow.nms.web;

import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.exception.ValidationException;
import kr.co.adflow.nms.web.mapper.NotificationMapper;
import kr.co.adflow.nms.web.service.NotificationsService;
import kr.co.adflow.nms.web.vo.DestPath.Path;
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
public class NotificationsController {

	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";

	@Autowired
	private NotificationMapper mapper;

	private static final Logger logger = LoggerFactory
			.getLogger(NotificationsController.class);
	@Autowired
	private NotificationsService service;

	@RequestMapping(value = "/notifications", method = RequestMethod.GET)
	public @ResponseBody
	String notifications(HttpServletRequest request) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		// 2013-02-23
		// Parameter check �� ȣ�� �б�
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

			// ������ "&" ����.
			filter.deleteCharAt(filter.length() - 1);
			logger.debug("Param:::" + filter.toString());

			try {
				result = (String) service.notificationsFilter(filter
						.toString());
			} catch (HandleException e) {
				logger.error("Failed in processing", e);
				throw e;
			}

		} else {

			try {
				result = (String) service.notifications();
			} catch (HandleException e) {
				logger.error("Failed in processing", e);
				throw e;
			}

		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/notifications/{id}", method = RequestMethod.GET)
	public @ResponseBody
	String notifications(HttpServletRequest request, @PathVariable String id)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.notifications(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/notifications/count", method = RequestMethod.GET)
	public @ResponseBody
	String notificationsCount(HttpServletRequest request)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.notificationsCount();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/notifications/destinationPaths/{pathName}", method = RequestMethod.GET)
	public @ResponseBody
	String notificationsDestincationPaths(HttpServletRequest request,
			@PathVariable String pathName) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = service.notificationDestinationPaths(pathName);

		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;

		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/notifications/destinationPaths", method = RequestMethod.GET)
	public @ResponseBody
	String notificationsDestincationPaths(HttpServletRequest request)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = service.notificationDestinationPaths();

		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;

		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/notifications/commands", method = RequestMethod.GET)
	public @ResponseBody
	String notificationsCommands(HttpServletRequest request)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = service.notificationCommands();

		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;

		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/notifications/events", method = RequestMethod.GET)
	public @ResponseBody
	String notificationsEvents(HttpServletRequest request)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = service.notificationEvents();

		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;

		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/notifications/eventNotifications", method = RequestMethod.GET)
	public @ResponseBody
	String notificationsEventNotifications(HttpServletRequest request)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = service.notificationEventNotifications();

		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;

		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/notifications/eventNotifications/{notificationName}", method = RequestMethod.GET)
	public @ResponseBody
	String notificationsEventNotifications(HttpServletRequest request,
			@PathVariable String notificationName) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = service.notificationEventNotifications(notificationName);

		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;

		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	
	@RequestMapping(value = "/notifications/searchUser/{userName}", method = RequestMethod.GET)
	public @ResponseBody
	String notificationsSearchUser(HttpServletRequest request, @PathVariable String userName)
			throws HandleException, ValidationException {

		String result = null;
		
		
		logger.info(PATH + request.getRequestURI());
		
		String pagetime = null;
		Integer limit = null;

		Enumeration eParam = request.getParameterNames();	

		try {
			
			if (eParam.hasMoreElements()) {
				StringBuffer filter = new StringBuffer();

				while (eParam.hasMoreElements()) {
					String pName = (String) eParam.nextElement();

					if(pName.equals("limit")){
						limit = Integer.parseInt(request.getParameter(pName));
						
					}else if(pName.equals("pagetime")) {
						pagetime = request.getParameter(pName);
						logger.debug("pagetime ::;"+pagetime);
					}

				}
			}
			
			if(pagetime.equals(null) || limit==null){
				
				logger.error("Must supply the 'pagetime' and 'limit' parameter");
				try {
					throw new ValidationException(
							"Must supply the 'pagetime' and 'limit'  parameter");
				} catch (ValidationException e) {
					throw e;
				}
			}else {
				result = (String) service.notificationsSearchUser(userName, pagetime, limit);
			}
			
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	@RequestMapping(value = "/notifications/searchUser/{userName}/count", method = RequestMethod.GET)
	public @ResponseBody
	String notificationsSearchUserCount(HttpServletRequest request, @PathVariable String userName)
			throws HandleException, ValidationException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.notificationsSearchUserCount(userName);
			
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	@RequestMapping(value = "/notifications/allOutstand", method = RequestMethod.GET)
	public @ResponseBody
	String notificationsAllOutstand(HttpServletRequest request)
			throws HandleException {

		String result = null;
		
		
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.notificationsAllOutstand();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	@RequestMapping(value = "/notifications/allAck", method = RequestMethod.GET)
	public @ResponseBody
	String notificationsAllAck(HttpServletRequest request)
			throws HandleException {

		String result = null;
		
		
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.notificationsAllAck();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	

	// /// POST /////
	@RequestMapping(value = "/notifications/destinationPaths", method = RequestMethod.POST)
	public @ResponseBody
	String notificationsDestincationPathsPost(HttpServletRequest request,
			@RequestBody String data) throws HandleException, MapperException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		Path path = null;
		String xml = null;

		// String data2 =
		// "{"name": "TESTPath3","initial-delay": "0s","target": [{"interval": "0s","name": "admin","autoNotify": "on","command": ["GCMSend","GtalkSend","HybridGCMSend","callHomePhone"]},{"interval": "0m","name": "Admin","autoNotify": "on","command": "javaEmail"},{"interval": "0s","name": "kicho@adflow.co.kr","autoNotify": "on","command": "email"}]}";
		// String data3 =
		// "{\"name\": \"TESTPath2\",\"initial-delay\": \"0s\",\"target\": [{\"interval\": \"0s\",\"name\": \"admin\",\"autoNotify\": \"on\",\"command\": [\"GCMSend\",\"GtalkSend\",\"HybridGCMSend\",\"callHomePhone\"]},{\"interval\": \"0m\",\"name\": \"Admin\",\"autoNotify\": \"on\",\"command\": \"javaEmail\"},{\"interval\": \"0s\",\"name\": \"kicho@adflow.co.kr\",\"autoNotify\": \"on\",\"command\": \"email\"}]}";

		logger.debug("Post Data:::" + data);

		try {

			path = mapper.destinationPathMapping(data);

			// xml = (String) mapper.schoedOutagePostMapping(schoedOutage);

		} catch (MapperException e) {
			logger.error("Failed Mapping", e);
			throw e;
		}

		// logger.debug("adf:::" + path.getTarget().get(0).getName());
		// logger.debug("adf222:::" +
		// path.getTarget().get(0).getCommand().get(0).toString());

		try {
			result = (String) service.notificationDestinationPathsPost(path);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		result = "{\"result\":\"" + result + "\"}";

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/notifications/eventNotifications", method = RequestMethod.POST)
	public @ResponseBody
	String notificationsEventNotificationsPost(HttpServletRequest request,
			@RequestBody String data) throws HandleException, MapperException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		Notification noti = null;
		String xml = null;

		// String data2 =
		// "{"name": "TEST3","description": "Notification Des","subject": "Login
		// Error Hybrid GCM Subject
		// ","status": "on","parameter": [],"rule": "(IPADDR IPLIKE
		// *.*.*.*)","varbind": {"vbname": "testName","vbvalue": "testName"},"writeable": "yes","uei": "uei.opennms.org/internal/authentication/failure","noticeQueue": null,"destinationPath": "GTalk","textMessage": "Login
		// Error Hybrid GCMText Message","numericMessage": "Login Error Short
		// Message Hybrid GCM","eventSeverity": null}"
		// String data3 =
		// "{\"name\": \"TEST3\",\"description\": \"Notification Des\",\"subject\": \"Login Error Hybrid GCM Subject \",\"status\": \"on\",\"parameter\": [],\"rule\": \"(IPADDR IPLIKE *.*.*.*)\",\"varbind\": {\"vbname\": \"testName\",\"vbvalue\": \"testName\"},\"writeable\": \"yes\",\"uei\": \"uei.opennms.org/internal/authentication/failure\",\"noticeQueue\": null,\"destinationPath\": \"GTalk\",\"textMessage\": \"Login Error Hybrid GCMText Message\",\"numericMessage\": \"Login Error Short Message Hybrid GCM\",\"eventSeverity\": null}"
		logger.debug("Post Data:::" + data);

		try {

			noti = mapper.eventNotificationMapping(data);

			// xml = (String) mapper.schoedOutagePostMapping(schoedOutage);

		} catch (MapperException e) {
			logger.error("Failed Mapping", e);
			throw e;
		}

		logger.debug("adf:::" + noti.getName());
		logger.debug("adf222:::" + noti.getVarbind().getVbname());

		try {
			result = (String) service
					.notificationsEventNotificationsPost(noti);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		result = "{\"result\":\"" + result + "\"}";

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	

	

	// /// PUT /////
	@RequestMapping(value = "/notifications/destinationPaths", method = RequestMethod.PUT)
	public @ResponseBody
	String notificationsDestincationPathsPut(HttpServletRequest request,
			@RequestBody String data) throws HandleException, MapperException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		Path path = null;
		String xml = null;

		logger.debug("Post Data:::" + data);

		try {

			path = mapper.destinationPathMapping(data);

		} catch (MapperException e) {
			logger.error("Failed Mapping", e);
			throw e;
		}

		try {
			result = (String) service.notificationDestinationPathsPut(path);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		result = "{\"result\":\"" + result + "\"}";

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/notifications/eventNotifications", method = RequestMethod.PUT)
	public @ResponseBody
	String notificationsEventNotificationsPut(HttpServletRequest request,
			@RequestBody String data) throws HandleException, MapperException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		Notification noti = null;
		String xml = null;

		logger.debug("Notification Data:::" + data);

		try {

			noti = mapper.eventNotificationMapping(data);

		} catch (MapperException e) {
			logger.error("Failed Mapping", e);
			throw e;
		}

		try {
			result = (String) service.notificationsEventNotificationsPut(noti);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		result = "{\"result\":\"" + result + "\"}";

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/notifications/eventNotifications/{notificationName}", method = RequestMethod.PUT)
	public @ResponseBody
	String notificationsEventNotificationsStatusPut(HttpServletRequest request,
			@PathVariable String notificationName) throws HandleException,
			MapperException, ValidationException {

		String result = null;
		String status = null;
		logger.info(PATH + request.getRequestURI());

		// 2013-02-23
		// Parameter check Method 호추 분기
		Enumeration eParam = request.getParameterNames();

		if (eParam.hasMoreElements()) {
			StringBuffer prams = new StringBuffer();

			String pName = (String) eParam.nextElement();

			if (pName.equals("status")) {

				String pValue = request.getParameter(pName);

				if (pValue.equals("on") || pValue.equals("off")) {
					status = pValue;
				} else {

					logger.error("Must supply the 'status' parameter, set to either 'on' or 'off'");
					try {
						throw new ValidationException(
								"Must supply the 'status' parameter, set to either 'on' or 'off'");
					} catch (ValidationException e) {
						throw e;
					}

				}

				try {
					result = (String) service
							.notificationsEventNotificationsStatusPut(
									notificationName, status);
				} catch (HandleException e) {
					logger.error("Failed in processing", e);
					throw e;
				}

			} else {

				logger.error("Must supply the parameter name, set to either 'status'");
				try {

					throw new ValidationException(
							"Must supply the parameter name, set to either 'status'");

				} catch (ValidationException e) {
					throw e;
				}

			}

			logger.debug("Param:::" + prams.toString());

		} else {

			logger.error("Must supply the parameter name, set to either 'status'");
			try {

				throw new ValidationException(
						"Must supply the parameter name, set to either 'status'");

			} catch (ValidationException e) {
				throw e;
			}

		}

		result = "{\"result\":\"" + result + "\"}";

		logger.debug(RETURNRESULT + result);
		return result;
	}

	// Notification Status Update
	@RequestMapping(value = "/notificationConfig", method = RequestMethod.PUT)
	public @ResponseBody
	String notificationsConfigStatusPut(HttpServletRequest request)
			throws HandleException, MapperException, ValidationException {

		String result = null;
		String status = null;
		logger.info(PATH + request.getRequestURI());

		// 2013-02-28
		// Parameter check
		Enumeration eParam = request.getParameterNames();

		if (eParam.hasMoreElements()) {
			StringBuffer prams = new StringBuffer();

			String pName = (String) eParam.nextElement();

			if (pName.equals("status")) {

				String pValue = request.getParameter(pName);

				if (pValue.equals("on") || pValue.equals("off")) {
					status = pValue;
				} else {

					logger.error("Must supply the 'status' parameter, set to either 'on' or 'off'");
					try {
						throw new ValidationException(
								"Must supply the 'status' parameter, set to either 'on' or 'off'");
					} catch (ValidationException e) {
						throw e;
					}

				}

				try {
					result = (String) service
							.notificationsConfigStatusPut(status);
				} catch (HandleException e) {
					logger.error("Failed in processing", e);
					throw e;
				}

			} else {

				logger.error("Must supply the parameter name, set to either 'status'");
				try {

					throw new ValidationException(
							"Must supply the parameter name, set to either 'status'");

				} catch (ValidationException e) {
					throw e;
				}

			}

			logger.debug("Param:::" + prams.toString());

		} else {

			logger.error("Must supply the parameter name, set to either 'status'");
			try {

				throw new ValidationException(
						"Must supply the parameter name, set to either 'status'");

			} catch (ValidationException e) {
				throw e;
			}

		}

		result = "{\"result\":\"" + result + "\"}";

		logger.debug(RETURNRESULT + result);
		return result;
	}

	// /// DELETE /////
	@RequestMapping(value = "/notifications/destinationPaths/{pathName}", method = RequestMethod.DELETE)
	public @ResponseBody
	String notificationsDestincationPathsDelete(HttpServletRequest request,
			@PathVariable String pathName) throws HandleException,
			MapperException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		logger.debug("pathName::" + pathName);

		try {
			result = (String) service
					.notificationDestinationPathsDelete(pathName);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		result = "{\"result\":\"" + result + "\"}";

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/notifications/eventNotifications/{notificationName}", method = RequestMethod.DELETE)
	public @ResponseBody
	String notificationsEventNotificationsDelete(HttpServletRequest request,
			@PathVariable String notificationName) throws HandleException,
			MapperException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		logger.debug("notificationName::" + notificationName);

		try {
			result = (String) service
					.notificationsEventNotificationsDelete(notificationName);
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