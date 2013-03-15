package kr.co.adflow.nms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.service.AdminTestService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class AdminTestController {
	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";

	private static final Logger logger = LoggerFactory
			.getLogger(AdminTestController.class);

	@Autowired
	AdminTestService service;

	@RequestMapping(value = "/adminuser/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	String adminPage(@PathVariable String id, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.adminPerId(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/dashboarduser/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	String dashBoardPage(@PathVariable String id, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.dashboardPage(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/adminuser/del/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	String adminPageDel(@PathVariable String id, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.adminDel(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/dashboarduser/del/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	String dashBoardPageDel(@PathVariable String id, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.dashBoardDel(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/adminuser", method = RequestMethod.GET)
	public @ResponseBody
	String adminUser(HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.adminUser();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	

	
	
	
/*	@RequestMapping(value = "/testchan", method = RequestMethod.GET)
	public @ResponseBody
	String testChan(HttpServletRequest request) throws HandleException, IOException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		result=service.test();
		logger.debug(RETURNRESULT + result);
		return result;
	}*/
	
//	
//	BeanDefinition def = ctx.getBeanFactory().getBeanDefinition("props");
//	String location = def.getPropertyValues().getPropertyValue("location").getValue();
//	File file = ctx.getResource(location).getFile();

	@RequestMapping(value = "/dashboarduser", method = RequestMethod.GET)
	public @ResponseBody
	String dashUser(HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.dashBoardUser();
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
