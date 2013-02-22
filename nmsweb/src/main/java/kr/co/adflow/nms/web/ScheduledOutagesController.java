package kr.co.adflow.nms.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.mapper.ScheduledOutagesMapper;
import kr.co.adflow.nms.web.process.AlarmsProcess;
import kr.co.adflow.nms.web.process.EventsProcess;
import kr.co.adflow.nms.web.process.ScheduledOutagesProcess;
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
 * ScheduledOutagesController
 * 
 * @author kicho@adflow.co.kr
 * @version 1.1
 */
@Controller
public class ScheduledOutagesController {

	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";

	private static final Logger logger = LoggerFactory
			.getLogger(ScheduledOutagesController.class);

	private ScheduledOutagesProcess controll = ScheduledOutagesProcess
			.getProcess();
	
	private ScheduledOutagesMapper mapper =  ScheduledOutagesMapper
			.getMapper();

	@RequestMapping(value = "/sched-outages", method = RequestMethod.GET)
	public @ResponseBody
	String schedOutages(HttpServletRequest request) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) controll.schedOutages();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
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
			result = (String) controll.schedOutages(outageName);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	
	@RequestMapping(value = "/sched-outages", method = RequestMethod.POST)
	public @ResponseBody
	String schedOutagesPost(HttpServletRequest request, @RequestBody String data) throws HandleException, MapperException {

		String result = null;
		logger.info(PATH + request.getRequestURI());
		
		SchoedOutage schoedOutage = null;
		String xml =null;
		
//		String data2 = "<outage type=\"specific\" name=\"test4\"><time ends=\"20-Feb-2013 23:59:59\" begins=\"20-Feb-2013 21:00:00\"/><node id=\"16\"/></outage>";
//		String data3 = "{\"@type\":\"specific\",\"@name\":\"test4\",\"time\":{\"@ends\":\"20-Feb-2013 23:59:59\",\"@begins\":\"20-Feb-2013 21:00:00\"},\"node\":{\"@id\":\"16\"}}";
		
		try {
			
			schoedOutage = mapper.schoedOutageMapping(data);
			
			xml = (String) mapper.schoedOutagePostMapping(schoedOutage);
			
		} catch (MapperException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		
		logger.debug("adf:::"+xml);

		try {
			result = (String) controll.schedOutagesPost(xml);
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