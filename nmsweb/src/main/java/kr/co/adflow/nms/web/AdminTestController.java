package kr.co.adflow.nms.web;

import javax.servlet.http.HttpServletRequest;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.service.AdminTestService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminTestController {
	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";

	private static final Logger logger = LoggerFactory
			.getLogger(GraphController.class);
	
	@Autowired
	AdminTestService service;
		@RequestMapping(value = "/admin/{id}", method = RequestMethod.PUT)
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
		
		@RequestMapping(value = "/dashboard/{id}", method = RequestMethod.PUT)
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
		
		@RequestMapping(value = "/admin/del/{id}", method = RequestMethod.DELETE)
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
		
		@RequestMapping(value = "/dashboard/del/{id}", method = RequestMethod.DELETE)
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
		String adminUser(HttpServletRequest request)
				throws HandleException {
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
		
		@RequestMapping(value = "/dashuser", method = RequestMethod.GET)
		public @ResponseBody
		String dashUser(HttpServletRequest request)
				throws HandleException {
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

}


