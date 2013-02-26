package kr.co.adflow.nms.web;

import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.mapper.DestinationPathMapper;
import kr.co.adflow.nms.web.mapper.ScheduledOutagesMapper;
import kr.co.adflow.nms.web.process.AlarmsProcess;
import kr.co.adflow.nms.web.process.EventsProcess;
import kr.co.adflow.nms.web.process.NotificationsProcess;
import kr.co.adflow.nms.web.vo.SchoedOutage;
import kr.co.adflow.nms.web.vo.DestPath.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	private DestinationPathMapper mapper = DestinationPathMapper.getMapper();

	private static final Logger logger = LoggerFactory
			.getLogger(NotificationsController.class);

	private NotificationsProcess controll = NotificationsProcess.getProcess();

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
				String pValue = request.getParameter(pName);

				filter.append(pName + "=" + pValue + "&");

			}

			// ������ "&" ����.
			filter.deleteCharAt(filter.length() - 1);
			logger.debug("Param:::" + filter.toString());

			try {
				result = (String) controll
						.notificationsFilter(filter.toString());
			} catch (HandleException e) {
				logger.error("Failed in processing", e);
				throw e;
			}

		} else {

			try {
				result = (String) controll.notifications();
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
			result = (String) controll.notifications(id);
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
			result = (String) controll.notificationsCount();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	
	
    ///// POST /////
	@RequestMapping(value = "/sched-outages/DestinationPaths", method = RequestMethod.POST)
	public @ResponseBody
	String notificationsDestincationPathsPost(HttpServletRequest request, @RequestBody String data)
			throws HandleException, MapperException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		Path path = null;
		String xml = null;

		// String data2 =
		// "<outage type=\"specific\" name=\"test4\"><time ends=\"20-Feb-2013 23:59:59\" begins=\"20-Feb-2013 21:00:00\"/><node id=\"16\"/></outage>";
		 String data3 =
		 "{\"name\": \"TESTPath2\",\"initial-delay\": \"0s\",\"target\": [{\"interval\": \"0s\",\"name\": \"admin\",\"autoNotify\": \"on\",\"command\": [\"GCMSend\",\"GtalkSend\",\"HybridGCMSend\",\"callHomePhone\"]},{\"interval\": \"0m\",\"name\": \"Admin\",\"autoNotify\": \"on\",\"command\": \"javaEmail\"},{\"interval\": \"0s\",\"name\": \"kicho@adflow.co.kr\",\"autoNotify\": \"on\",\"command\": \"email\"}]}";

		logger.debug("Post Data:::" + data);

		try {

			path = mapper.destinationPathMapping(data3);
			
//			xml = (String) mapper.schoedOutagePostMapping(schoedOutage);

		} catch (MapperException e) {
			logger.error("Failed Mapping", e);
			throw e;
		}

		logger.debug("adf:::" + path.getTarget().get(0).getName());
		logger.debug("adf222:::" + path.getTarget().get(0).getCommand().get(0).toString());
		

		try {
			result = (String) controll.notificationDestinationPathsPost(path);
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