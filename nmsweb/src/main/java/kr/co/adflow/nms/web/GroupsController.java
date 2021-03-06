package kr.co.adflow.nms.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.exception.UtilException;
import kr.co.adflow.nms.web.mapper.UserAndGroupMapper;
import kr.co.adflow.nms.web.service.GroupsService;
import kr.co.adflow.nms.web.util.UsersUtil;
import kr.co.adflow.nms.web.vo.group.Groupinfo;

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
public class GroupsController {
	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";
	private static final String INVALUE = "invalue:::";
	private static final String XMLDATA = "xmlData:::";

	private static final Logger logger = LoggerFactory
			.getLogger(GroupsController.class);
	@Autowired
	private GroupsService service;
	@Autowired
	UserAndGroupMapper tcMapper;
	@Autowired
	UsersUtil ut;

	// groups
	@RequestMapping(value = "/groups", method = RequestMethod.GET)
	public @ResponseBody
	String groups(HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.groups();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// groups POST
	@RequestMapping(value = "/groups", method = RequestMethod.POST)
	public @ResponseBody
	String groupsPost(@RequestBody String data, HttpServletRequest request)
			throws HandleException, MapperException, UtilException {
		logger.info(PATH + request.getRequestURL());
		logger.debug(INVALUE + data);
		String result = null;
		Groupinfo group = null;
		String xmlData = null;

		// <group><name>adflow222</name><comments>The
		// adflow222</comments><user>chan222</user></group>

		group = tcMapper.groupInfo(data);
		logger.debug("group.getComments()::" + group.getComments());
		logger.debug("group.getName()::" + group.getName());
		logger.debug("group.getUser()::" + group.getUser());
		try {
			xmlData = ut.XmlParsingGroup(group);
			logger.debug("xmlData::" + xmlData);
			result = (String) service.groupsPost(xmlData);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		} catch (UtilException e) {
			logger.error("Failed in Util", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// groups PUT groups users add

	@RequestMapping(value = "/groups/{groupname}/users/{username}", method = RequestMethod.PUT)
	public @ResponseBody
	String groupsPut(@PathVariable String groupname,
			@PathVariable String username, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		logger.debug("GroupNAME::" + groupname);
		logger.debug("UserNAME::" + username);
		String result = null;

		try {
			result = (String) service.groupsPut(groupname, username);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// group Del groups /groups/{groupname}

	@RequestMapping(value = "/groups/{groupname}", method = RequestMethod.DELETE)
	public @ResponseBody
	String groupsDelGroup(@PathVariable String groupname,
			HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.groupsDelGroup(groupname);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// group Del group user //groups/{groupname}/users/{username}

	@RequestMapping(value = "/groups/{groupname}/users/{username}", method = RequestMethod.DELETE)
	public @ResponseBody
	String groupsDelGroupUser(@PathVariable String groupname,
			@PathVariable String username, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			groupname = groupname.replaceAll(" ", "%20");
			result = (String) service.groupsDelGroupUsers(groupname, username);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// groups/{groupname}
	@RequestMapping(value = "/groups/{groupname}", method = RequestMethod.GET)
	public @ResponseBody
	String groups(@PathVariable String groupname, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		groupname = groupname.replaceAll(" ", "%20");

		try {
			result = (String) service.groups(groupname);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
					
		logger.debug(RETURNRESULT + result);
		return result;
	}

	/*
	 * 404!!!! // groups/{groupname}/users
	 * 
	 * @RequestMapping(value = "/groups/{groupName}/users", method =
	 * RequestMethod.GET) public @ResponseBody String groupsUsers(@PathVariable
	 * String groupName, HttpServletRequest request) throws HandleException {
	 * logger.info(PATH + request.getRequestURL()); String result = null;
	 * 
	 * try { result = (String) service.groupsUsers(groupName); } catch
	 * (HandleException e) { logger.error("Failed in processing", e); throw e; }
	 * logger.debug(RETURNRESULT + result); return result; }
	 */

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

	@ExceptionHandler(UtilException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String processUtilException(HandleException e) {
		return "{\"code\":\"" + HttpServletResponse.SC_INTERNAL_SERVER_ERROR
				+ "\",\"message\":\"" + e.getMessage() + "\"}";
	}

}
