package kr.co.adflow.nms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.exception.UtilException;
import kr.co.adflow.nms.web.mapper.UserAndGroupMapper;
import kr.co.adflow.nms.web.service.NodeService;
import kr.co.adflow.nms.web.service.UserService;
import kr.co.adflow.nms.web.util.UsersUtil;
import kr.co.adflow.nms.web.vo.user.User;
import kr.co.adflow.nms.web.vo.user.UserInit;

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

@Controller
public class UsersController {

	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";

	private static final Logger logger = LoggerFactory
			.getLogger(UsersController.class);
	@Autowired
	private UserService service;
	@Autowired
	UserAndGroupMapper tcmapper;
	
	private static final String INVALUE = "invalue:::";
	private static final String XMLDATA = "xmlData:::";
	private UsersUtil ut = UsersUtil.getInstance();

	// users
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public @ResponseBody
	String users(HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.Users();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// user POST
	//testPost
	//123123
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public @ResponseBody
	String usersPost(@RequestBody String data, HttpServletRequest request)
			throws HandleException, MapperException, UtilException {

		logger.info(PATH + request.getRequestURL());
		logger.debug(INVALUE + data);
		String result = null;
		UserInit userinit = null;
		String xmlData = null;
		try {

			userinit = tcmapper.initUser(data);
			logger.debug("password:" + userinit.getPassword());
			String pass = userinit.getPassword();
			String md5 = ut.encryptString(pass);
			logger.debug("md5pass:" + md5);
			userinit.setPassword(md5);
			xmlData = ut.XmlParsingUser(userinit);
			logger.debug("xmlDATA::"+xmlData);

		} catch (MapperException e) {
			logger.error("Failed in processing", e);
			throw e;
		} catch (UtilException e) {
			logger.error("Failed in util..", e);
			throw e;
		}

		try {
			result = (String) service.UsersPost(xmlData);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		return result;
	}

	// user POST
	@RequestMapping(value = "/users/detail/{id}", method = RequestMethod.POST)
	public @ResponseBody
	String usersPostDetail(@PathVariable String id,@RequestBody String data, HttpServletRequest request)
			throws HandleException, MapperException {
		logger.info(PATH + request.getRequestURL());
		logger.debug(INVALUE + data);
		String result = null;
		User user = new User();

		try {
			user = tcmapper.userInfoMapping(data);
		} catch (MapperException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		try {
			result = (String) service.userPostDetail(id,user);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		return "succed";
	}
	
	// user POST
		@RequestMapping(value = "/users/detail/del/{id}", method = RequestMethod.POST)
		public @ResponseBody
		String usersPostDetailDel(@PathVariable String id,@RequestBody String data, HttpServletRequest request)
				throws HandleException, MapperException {
			logger.info(PATH + request.getRequestURL());
			logger.debug(INVALUE + data);
			String result = null;
			User user = new User();

			try {
				user = tcmapper.userInfoMapping(data);
			} catch (MapperException e) {
				logger.error("Failed in processing", e);
				throw e;
			}

			try {
				result = (String) service.userPostDetailDel(id,user);
			} catch (HandleException e) {
				logger.error("Failed in processing", e);
				throw e;
			}
			return "succed";
		}
	

	// users/{username} Delete
	@RequestMapping(value = "/users/{username}", method = RequestMethod.DELETE)
	public @ResponseBody
	String usersDelete(@PathVariable String username, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.UsersDelete(username);
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
			result = (String) service.Users(username);
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

	@ExceptionHandler(UtilException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String processUtilException(HandleException e) {
		return "{\"code\":\"" + HttpServletResponse.SC_INTERNAL_SERVER_ERROR
				+ "\",\"message\":\"" + e.getMessage() + "\"}";
	}

}
