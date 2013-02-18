package kr.co.adflow.nms.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.exception.HandleException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handles requests for the application.
 * 
 * @author typark@adflow.co.kr
 * @version 1.0
 */
@Controller
public class NodeController {

	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final Logger logger = LoggerFactory
			.getLogger(NodeController.class);

	/**
	 * getNodes
	 * 
	 * @return The value input as a String.
	 * @exception Exception
	 */
	@RequestMapping(value = "/nodes", method = RequestMethod.GET)
	public @ResponseBody
	String getNodes() throws Exception {
		Handler handler = HandlerFactory.getHandler();
		HashMap hash = new HashMap();
		hash.put(USERNAME, "admin");
		hash.put(PASSWORD, "admin");
		hash.put(URL, "http://192.168.112.128:8980/opennms/rest/nodes");
		hash.put(METHOD, "GET");

		logger.debug("requestMap::" + hash);

		String result = null;

		try {
			result = (String) handler.handle(hash);
			logger.debug("sendData::" + result);
		} catch (HandleException e) {
			logger.error("Failed::HandleException", e);
			// return error (json format)
			// return
			// "{\"error\":\"{\"code\":\"100\",\"message\":\"FailedGetNodesProcess\"}\"}";
			throw e;
		} catch (Exception e) {
			logger.error("Failed::Exception", e);
			// return error (json format)
			return "{\"error\":\"{\"code\":\"100\",\"message\":\"FailedGetNodesProcess\"}\"}";
		} finally {

		}
		return result;
	}

	// @ExceptionHandler(HandleException.class)
	// @ResponseBody
	// public String handleException(HandleException e) {
	// return e.getMessage();
	// }

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String handleException(Exception e, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return e.getMessage();
	}

	@ExceptionHandler(HandleException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handleCustomized4Exception(HandleException ex) {

		return ex.getMessage();

	}
}
