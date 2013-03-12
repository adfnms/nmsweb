package kr.co.adflow.nms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.exception.UtilException;
import kr.co.adflow.nms.web.service.GraphService;
import kr.co.adflow.nms.web.util.GraphUtil;

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
public class GraphController {

	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";

	private static final Logger logger = LoggerFactory
			.getLogger(GraphController.class);
	@Autowired
	private GraphService service;
	@Autowired
	private GraphUtil gutil;
	private static final String INVALUE = "invalue:::";

	// resport Graph
	@RequestMapping(value = "/graph", method = RequestMethod.GET)
	public @ResponseBody
	String graphNode(HttpServletRequest request) throws HandleException,
			MapperException, UtilException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		String dataJsonResult = null;

		try {
			result = (String) service.graphList();

			String parsingData = gutil.graphNode(result);
			logger.debug("parsingDATA::" + parsingData);
			dataJsonResult = gutil.graphNodeJson(parsingData);

		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		} catch (UtilException e) {
			logger.error("Failed in Util", e);
			throw e;
		} catch (MapperException e) {
			logger.error("Failed in mapper", e);
			throw e;
		}
		logger.debug(RETURNRESULT + dataJsonResult);
		return dataJsonResult;
	}

	// http://192.168.0.5:8980/opennms/graph/chooseresource.htm?reports=all&parentResourceId=node[53]

	@RequestMapping(value = "/graph/{nodeid}", method = RequestMethod.GET)
	public @ResponseBody
	String graphNodeSelect(@PathVariable String nodeid,
			HttpServletRequest request) throws HandleException,
			MapperException, UtilException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		String jsonData = null;
		try {
			result = (String) service.graphListNodeId(nodeid);
			String parsingData = gutil.graphDetail(result);
			logger.debug("parsingData::" + parsingData);
			jsonData = gutil.graphNodeJson(parsingData);
			logger.debug("jsonData::" + jsonData);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		} catch (UtilException e) {
			logger.error("Failed in Util", e);
			throw e;
		} catch (MapperException e) {
			logger.error("Failed in mapper", e);
			throw e;
		}
		logger.debug(RETURNRESULT + jsonData);
		return jsonData;
	}

	// http://192.168.0.5:8980/opennms/graph/results.htm?reports=all&resourceId=

	@RequestMapping(value = "/graph/resource/{resourceId:.+}", method = RequestMethod.GET)
	public @ResponseBody
	String graphResourceSelect(@PathVariable String resourceId,
			HttpServletRequest request) throws HandleException,
			MapperException, UtilException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		String graphUrl = null;
	
		try {
			result = (String) service.graphResourceIdSer(resourceId);
			graphUrl = gutil.graphUrl(result);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		} catch (UtilException e) {
			logger.error("Failed in Util", e);
			throw e;
		} 
		logger.debug(RETURNRESULT + graphUrl);
		return graphUrl;
	}

	// day,// &relativetime=lastday, // lastweek// // ,lastmonth//// ,lastyear
	@RequestMapping(value = "/graph/resource/{resourceId:.+}/day", method = RequestMethod.GET)
	public @ResponseBody
	String graphResourceSelectDay(@PathVariable String resourceId,
			HttpServletRequest request) throws HandleException,
			MapperException, UtilException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		String graphUrl = null;

		try {
			result = (String) service.graphResourceIdSerDay(resourceId);
			graphUrl = gutil.graphUrl(result);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}catch (UtilException e) {
			logger.error("Failed in Util", e);
			throw e;
		}
		logger.debug(RETURNRESULT + graphUrl);
		return graphUrl;
	}

	@RequestMapping(value = "/graph/resource/{resourceId:.+}/week", method = RequestMethod.GET)
	public @ResponseBody
	String graphResourceSelectWeek(@PathVariable String resourceId,
			HttpServletRequest request) throws HandleException,
			MapperException, UtilException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		String graphUrl = null;
	
		try {
			result = (String) service.graphResourceIdSerWeek(resourceId);
			graphUrl = gutil.graphUrl(result);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + graphUrl);
		return graphUrl;
	}

	@RequestMapping(value = "/graph/resource/{resourceId:.+}/month", method = RequestMethod.GET)
	public @ResponseBody
	String graphResourceSelectMonth(@PathVariable String resourceId,
			HttpServletRequest request) throws HandleException,
			MapperException, UtilException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		String graphUrl = null;
	
		try {
			result = (String) service.graphResourceIdSerMonth(resourceId);
			graphUrl = gutil.graphUrl(result);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + graphUrl);
		return graphUrl;
	}

	@RequestMapping(value = "/graph/resource/{resourceId:.+}/year", method = RequestMethod.GET)
	public @ResponseBody
	String graphResourceSelectYear(@PathVariable String resourceId,
			HttpServletRequest request) throws HandleException,
			MapperException, UtilException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		String graphUrl = null;
	
		try {
			result = (String) service.graphResourceIdSerYear(resourceId);
			graphUrl = gutil.graphUrl(result);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + graphUrl);
		return graphUrl;
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
