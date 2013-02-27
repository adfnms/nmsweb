package kr.co.adflow.nms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.process.UsersProcess;
import kr.co.adflow.nms.web.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class UsersController {

	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";

	private static final Logger logger = LoggerFactory
			.getLogger(UsersController.class);
	private UsersProcess controll = UsersProcess.getPrcess();
	private static final String INVALUE = "invalue:::";
	private static final String XMLDATA = "xmlData:::";
	private Util ut = Util.getInstance();

	// users
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public @ResponseBody
	String users(HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.Users();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// user POST
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public @ResponseBody
	String usersPost(@RequestBody String data, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		logger.debug(INVALUE + data);
		String result = null;
		String xmlData = ut.passWordPar(data);
		logger.debug(XMLDATA + xmlData);

		try {
			result = (String) controll.UsersPost(xmlData);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		return result;
	}

	// users/{username} Delete
	@RequestMapping(value = "/users/{username}", method = RequestMethod.DELETE)
	public @ResponseBody
	String usersDelete(@PathVariable String username, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.UsersDelete(username);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// users/{username}
	@RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
	public @ResponseBody
	String users(@PathVariable String username, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.Users(username);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	// user POST
	@RequestMapping(value = "/users/detail", method = RequestMethod.POST)
	public @ResponseBody
	String usersPostDetail(@RequestBody String data, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		logger.debug(INVALUE + data);
		String result = null;
		//String xmlData = ut.passWordPar(data);
		//ogger.debug(XMLDATA + xmlData);
		String dataExample="";
		try {
			result = (String) controll.UsersPost(dataExample);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
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
