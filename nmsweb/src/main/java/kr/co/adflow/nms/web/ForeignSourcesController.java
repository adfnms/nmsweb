package kr.co.adflow.nms.web;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.mapper.ForeignMapper;
import kr.co.adflow.nms.web.mapper.RequisitionsMapper;
import kr.co.adflow.nms.web.mapper.UserAndGroupMapper;
import kr.co.adflow.nms.web.process.ForeignSourcesProcess;
import kr.co.adflow.nms.web.util.ForeignUtil;
import kr.co.adflow.nms.web.util.RequisitionsUtil;
import kr.co.adflow.nms.web.vo.requisition.RequisitionsAssets;
import kr.co.adflow.nms.web.vo.user.UserInit;
import kr.co.adflow.web.vo.foreign.ForDetector;
import kr.co.adflow.web.vo.foreign.ForPoliceS;
import kr.co.adflow.web.vo.foreign.ForPutName;

import kr.co.adflow.web.vo.foreign.ForeignInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ForeignSourcesController {
	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";
	private static final String INVALUE = "invlalue::";
	private static final Logger logger = LoggerFactory
			.getLogger(ForeignSourcesController.class);

	private ForeignSourcesProcess controll = ForeignSourcesProcess.getPrcess();
	private static final String DATA="data::";

	// foreignSources
	@RequestMapping(value = "/foreignSources", method = RequestMethod.GET)
	public @ResponseBody
	String foreignSources(HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.foreignSources();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// foreignSources/default
	@RequestMapping(value = "/foreignSources/default", method = RequestMethod.GET)
	public @ResponseBody
	String foreignSourcesDefault(HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.foreignSourcesDefault();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// foreignSources/deployed
	@RequestMapping(value = "/foreignSources/deployed", method = RequestMethod.GET)
	public @ResponseBody
	String foreignSourcesDeployed(HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.foreignSourcesDeployed();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// foreignSources/deployed/count *Exception!!!!!!

	@RequestMapping(value = "/foreignSources/deployed/count", method = RequestMethod.GET)
	public @ResponseBody
	String foreignSourcesDeployedCount(HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.foreignSourcesDeployedCount();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// foreignSources/{name}
	@RequestMapping(value = "/foreignSources/{name}", method = RequestMethod.GET)
	public @ResponseBody
	String foreignSources(@PathVariable String name, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.foreignSources(name);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// foreignSources/{name}/detectors
	@RequestMapping(value = "/foreignSources/{name}/detectors", method = RequestMethod.GET)
	public @ResponseBody
	String foreignSourcesDetectors(@PathVariable String name,
			HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.foreignSourcesDetectors(name);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// foreignSources/{name}/detectors/{detector} Parameter

	@RequestMapping(value = "/foreignSources/{name}/detectors/{detector}", method = RequestMethod.GET)
	public @ResponseBody
	String foreignSourcesDetectors(@PathVariable String name,
			@PathVariable String detector, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.foreignSourcesDetectors(name, detector);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// foreignSources/{name}/policies
	@RequestMapping(value = "/foreignSources/{name}/policies", method = RequestMethod.GET)
	public @ResponseBody
	String foreignSourcesPolicies(@PathVariable String name,
			HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.foreignSourcesPolicies(name);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// foreignSources/{name}/policies/{policy}
	@RequestMapping(value = "/foreignSources/{name}/policies/{policy}", method = RequestMethod.GET)
	public @ResponseBody
	String foreignSourcesPolicies(@PathVariable String name,
			@PathVariable String policy, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) controll.foreignSourcesPolicies(name, policy);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// POST
	// /foreignSources
	// <foreign-source name="testzzzzz"/>
	// {"foreign-source":[{"name":"testzzzzz"}]}
	@RequestMapping(value = "/foreignSources", method = RequestMethod.POST)
	public @ResponseBody
	String foreignPost(@RequestBody String data, HttpServletRequest request)
			throws HandleException, MapperException {
		logger.info(PATH + request.getRequestURL());
		logger.debug(INVALUE + data);
		String result = null;
		ForeignInfo forInfo = null;
		String xmlData = null;
		ForeignMapper mapper = ForeignMapper.getMapper();

		try {
			forInfo = mapper.foreignInput(data);

		} catch (MapperException e) {
			logger.error("Failed in mapping", e);
			throw e;
		}
		try {
			ForeignUtil ut = ForeignUtil.getInstance();
			xmlData = ut.xmlParsingForeign(forInfo);
			result = (String) controll.foreignPostPro(xmlData);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// POST
	// <detector
	// class="org.opennms.netmgt.provision.detector.simple.HttpDetector"
	// name="chan2"/>
	//{"detector":[{"class":"org.opennms.netmgt.provision.detector.simple.HttpDetector","name":"chan2"}]}
	// foreignSources/{name}/detectors

	@RequestMapping(value = "/foreignSources/{name}/detectors", method = RequestMethod.POST)
	public @ResponseBody
	String foreignDetector(@RequestBody String data, @PathVariable String name,
			HttpServletRequest request) throws HandleException, MapperException {
		logger.info(PATH + request.getRequestURL());
		logger.debug(INVALUE + data);
		String result = null;
		ForDetector detector = null;
		String xmlData = null;
		ForeignMapper mapper = ForeignMapper.getMapper();

		try {
			detector = mapper.foreignDetectorMap(data);

		} catch (MapperException e) {
			logger.error("Failed in mapping", e);
			throw e;
		}
		try {
			ForeignUtil ut = ForeignUtil.getInstance();
			xmlData = ut.xmlParsingDetector(detector);
			result = (String) controll.foreignDecPro(xmlData, name);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}
	//POST
	//foreignSources/{name}/policies 
	//<policy class="org.opennms.netmgt.provision.persist.policies.MatchingIpInterfacePolicy" name="pol2chan">
	//<parameter value="DISABLE_COLLECTION" key="action"/><parameter value="ALL_PARAMETERS" key="matchBehavior"/></policy>
	
	@RequestMapping(value = "/foreignSources/{name}/policies", method = RequestMethod.POST)
	public @ResponseBody
	String foreignPolicies(@RequestBody String data, @PathVariable String name,
			HttpServletRequest request) throws HandleException, MapperException {
		logger.info(PATH + request.getRequestURL());
		logger.debug(INVALUE + data);
		String result = null;
	    ForPoliceS polices=null;
		String xmlData = null;
		ForeignMapper mapper = ForeignMapper.getMapper();

		try {
			polices = mapper.foreignPolicesMap(data);

		} catch (MapperException e) {
			logger.error("Failed in mapping", e);
			throw e;
		}
		try {
			ForeignUtil ut = ForeignUtil.getInstance();
			xmlData = ut.xmlParsingPolices(polices);
			result = (String) controll.foreignPolicesPro(xmlData, name);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	//foreignSources/{name} 	
	// PUT
	//{"name":"chan"}
	@RequestMapping(value = "/foreignSources/{name}", method = RequestMethod.PUT)
	public @ResponseBody
	String foreignPutName(@RequestBody String data,@PathVariable String name,
			HttpServletRequest request) throws HandleException,MapperException {

		logger.info(PATH + request.getRequestURL());
		logger.info(DATA + name);
		logger.info(DATA + data);
		ForPutName putName=null;
		ForeignMapper mapper = ForeignMapper.getMapper();
		try{
			putName = mapper.foreignPutName(data);
			
		}catch(MapperException e){
			logger.error("Failed in mapping", e);
			throw e;
		}
		
		String result = null;
		
		try {
			ForeignUtil ut = ForeignUtil.getInstance();
			String 	convertdata=ut.ParsingPutName(putName);
			result = (String) controll.foreignPutNamePro(name,convertdata);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}
	//DELETE
	//foreignSources/{name} 
	@RequestMapping(value = "/foreignSources/{name}", method = RequestMethod.DELETE)
	public @ResponseBody
	String foreignDeleteName(@PathVariable String name,
			HttpServletRequest request) throws HandleException {

		logger.info(PATH + request.getRequestURL());
		logger.info(DATA + name);
		String result = null;
		
		try {

			result = (String) controll.foreignDelNamePro(name);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}
	//DEL
	//foreignSources/{name}/detectors/{detector} 
	
	@RequestMapping(value = "/foreignSources/{name}/detectors/{detector}", method = RequestMethod.DELETE)
	public @ResponseBody
	String foreignDeleteDec(@PathVariable String name,@PathVariable String detector,
			HttpServletRequest request) throws HandleException {

		logger.info(PATH + request.getRequestURL());
		logger.info(DATA + name);
		String result = null;
		
		try {

			result = (String) controll.foreignDelDecPro(name, detector);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}
	//DEL
	//foreignSources/{name}/policies/{policy} 
	@RequestMapping(value = "/foreignSources/{name}/policies/{policy}", method = RequestMethod.DELETE)
	public @ResponseBody
	String foreignDeletePoli(@PathVariable String name,@PathVariable String policy,
			HttpServletRequest request) throws HandleException {

		logger.info(PATH + request.getRequestURL());
		logger.info(DATA + name);
		String result = null;
		
		try {

			result = (String) controll.foreignDelPolPro(name, policy);
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
