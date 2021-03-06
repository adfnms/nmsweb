package kr.co.adflow.nms.web;

import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.mapper.ScheduledOutagesMapper;
import kr.co.adflow.nms.web.service.AlarmsService;
import kr.co.adflow.nms.web.service.EventsService;
import kr.co.adflow.nms.web.service.ScheduledOutagesService;
import kr.co.adflow.nms.web.vo.SchoedOutage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * ScheduledOutagesController
 * 
 * @author kicho@adflow.co.kr
 * @version 1.2
 */
@Controller
public class ScheduledOutagesController {

	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";

	private static final Logger logger = LoggerFactory
			.getLogger(ScheduledOutagesController.class);
	@Autowired
	private ScheduledOutagesService service;

	@Autowired
	private ScheduledOutagesMapper mapper;

	@RequestMapping(value = "/sched-outages", method = RequestMethod.GET)
	public @ResponseBody
	String schedOutages(HttpServletRequest request) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		// 2013-02-23
		// Parameter check method 분기
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

			// last "&" delete.
			filter.deleteCharAt(filter.length() - 1);
			logger.debug("Param:::" + filter.toString());

			try {
				result = (String) service
						.schedOutagesFilter(filter.toString());
			} catch (HandleException e) {
				logger.error("Failed in processing", e);
				throw e;
			}

		} else {

			try {
				result = (String) service.schedOutages();
			} catch (HandleException e) {
				logger.error("Failed in processing", e);
				throw e;
			}

		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/sched-outages/{outageName}", method = RequestMethod.GET)
	public @ResponseBody
	String schedOutages(HttpServletRequest request,
			@PathVariable String outageName) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.schedOutages(outageName);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	
    ///// POST /////
	@RequestMapping(value = "/sched-outages", method = RequestMethod.POST)
	public @ResponseBody
	String schedOutagesPost(HttpServletRequest request, @RequestBody String data)
			throws HandleException, MapperException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		SchoedOutage schoedOutage = null;
		String xml = null;

		// String data2 =
		// "<outage type=\"specific\" name=\"test4\"><time ends=\"20-Feb-2013 23:59:59\" begins=\"20-Feb-2013 21:00:00\"/><node id=\"16\"/></outage>";
		// String data3 =
		// "{\"@type\":\"specific\",\"@name\":\"test4\",\"time\":{\"@ends\":\"20-Feb-2013 23:59:59\",\"@begins\":\"20-Feb-2013 21:00:00\"},\"node\":{\"@id\":\"16\"}}";

		logger.debug("fdfdfe:::" + data);

		try {

			schoedOutage = mapper.schoedOutageMapping(data);

			xml = (String) mapper.schoedOutagePostMapping(schoedOutage);

		} catch (MapperException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug("adf:::" + xml);

		try {
			result = (String) service.schedOutagesPost(xml);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	
	
	///// PUT /////
	@RequestMapping(value = "/sched-outages/{outageName}/notifd", method = RequestMethod.PUT)
	public @ResponseBody
	String schedOutagesNotifdPut(HttpServletRequest request,
			@PathVariable String outageName) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.schedOutagesNotifdPut(outageName);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	@RequestMapping(value = "/sched-outages/{outageName}/collectd/{packageName}", method = RequestMethod.PUT)
	public @ResponseBody
	String schedOutagesCollectdPut(HttpServletRequest request,
			@PathVariable String outageName, @PathVariable String packageName) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.schedOutagesCollectdPut(outageName, packageName);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	@RequestMapping(value = "/sched-outages/{outageName}/pollerd/{packageName}", method = RequestMethod.PUT)
	public @ResponseBody
	String schedOutagesPollerdPut(HttpServletRequest request,
			@PathVariable String outageName, @PathVariable String packageName) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.schedOutagesPollerdPut(outageName, packageName);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	@RequestMapping(value = "/sched-outages/{outageName}/threshd/{packageName}", method = RequestMethod.PUT)
	public @ResponseBody
	String schedOutagesThreshdPut(HttpServletRequest request,
			@PathVariable String outageName, @PathVariable String packageName) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.schedOutagesThreshdPut(outageName, packageName);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	
	
	///// DELETE /////
	@RequestMapping(value = "/sched-outages/{outageName}", method = RequestMethod.DELETE)
	public @ResponseBody
	String schedOutagesDelete(HttpServletRequest request,
			@PathVariable String outageName) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.schedOutagesDelete(outageName);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	@RequestMapping(value = "/sched-outages/{outageName}/notifd", method = RequestMethod.DELETE)
	public @ResponseBody
	String schedOutagesNotifdDelete(HttpServletRequest request,
			@PathVariable String outageName) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.schedOutagesNotifdDelete(outageName);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	@RequestMapping(value = "/sched-outages/{outageName}/collectd/{packageName}", method = RequestMethod.DELETE)
	public @ResponseBody
	String schedOutagesCollectdDelete(HttpServletRequest request,
			@PathVariable String outageName, @PathVariable String packageName) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.schedOutagesCollectdDelete(outageName, packageName);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	@RequestMapping(value = "/sched-outages/{outageName}/pollerd/{packageName}", method = RequestMethod.DELETE)
	public @ResponseBody
	String schedOutagesPollerdDelete(HttpServletRequest request,
			@PathVariable String outageName, @PathVariable String packageName) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.schedOutagesPollerdDelete(outageName, packageName);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	@RequestMapping(value = "/sched-outages/{outageName}/threshd/{packageName}", method = RequestMethod.DELETE)
	public @ResponseBody
	String schedOutagesThreshdDelete(HttpServletRequest request,
			@PathVariable String outageName, @PathVariable String packageName) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.schedOutagesThreshdDelete(outageName, packageName);
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

	@ExceptionHandler(MapperException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String processMapperException(MapperException e) {
		return "{\"code\":\"" + HttpServletResponse.SC_INTERNAL_SERVER_ERROR
				+ "\",\"message\":\"" + e.getMessage() + "\"}";
	}

}