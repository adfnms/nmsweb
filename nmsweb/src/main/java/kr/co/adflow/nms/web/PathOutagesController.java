package kr.co.adflow.nms.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.mapper.PathOutagesMapper;
import kr.co.adflow.nms.web.process.PathOutagesProcess;
import kr.co.adflow.nms.web.vo.PathOutage;
import kr.co.adflow.nms.web.vo.SchoedOutage;

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
public class PathOutagesController {

	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";

	private static final Logger logger = LoggerFactory
			.getLogger(PathOutagesController.class);

	private PathOutagesProcess controll = PathOutagesProcess.getProcess();
	private PathOutagesMapper mapper = PathOutagesMapper.getMapper();


	@RequestMapping(value = "/pathOutages", method = RequestMethod.GET)
	public @ResponseBody
	String pathOutages(HttpServletRequest request)
			throws HandleException {

		String result = null;
		
		
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) controll.pathOutages();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	
	//PUT
	@RequestMapping(value = "/pathOutages", method = RequestMethod.PUT)
	public @ResponseBody
	String pathOutagesPUT(HttpServletRequest request, @RequestBody String data)
			throws HandleException, MapperException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		List<PathOutage> pathOutageList = null;
		
		String xml = null;

		// pathOutages가 하나 일때에는"{"nodeid": "8","192.168.1.22": "ICMP","criticalpathservicename": "ICMP"}"
		// 또는 "[{"nodeid": "8","criticalpathip": "192.168.1.22","criticalpathservicename": "ICMP"}]"
		// String data2 =
		// " [{"nodeid": "8","criticalpathip": "192.168.1.22","criticalpathservicename": "ICMP"},{"nodeid": "22","criticalpathip": "192.168.1.30","criticalpathservicename": "ICMP"}]";

		logger.debug("data :::" + data);

		try {

			pathOutageList = mapper.pathOutagesMapper(data);

		} catch (MapperException e) {
			logger.error("Failed in Mapping", e);
			throw e;
		}

		logger.debug("pathOutageList[0] Nodeid:::" + pathOutageList.get(0).getNodeid());

		try {
			result = (String) controll.pathOutagesPut(pathOutageList);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	
	
	//DELETE
	@RequestMapping(value = "/pathOutages/{nodeId}", method = RequestMethod.DELETE)
	public @ResponseBody
	String pathOutagesDelete(HttpServletRequest request, @PathVariable String nodeId)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) controll.pathOutagesDelete(nodeId);
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