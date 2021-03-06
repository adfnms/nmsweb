package kr.co.adflow.nms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.exception.UtilException;
import kr.co.adflow.nms.web.mapper.RequisitionsMapper;
import kr.co.adflow.nms.web.service.RequisitionsService;
import kr.co.adflow.nms.web.util.RequisitionsUtil;
import kr.co.adflow.nms.web.vo.requisition.ReqPutForID;
import kr.co.adflow.nms.web.vo.requisition.ReqPutIP;
import kr.co.adflow.nms.web.vo.requisition.ReqPutName;
import kr.co.adflow.nms.web.vo.requisition.RequisitionsAssets;
import kr.co.adflow.nms.web.vo.requisition.RequisitionsCategory;
import kr.co.adflow.nms.web.vo.requisition.RequisitionsNodes;
import kr.co.adflow.nms.web.vo.requisition.RequisitionsNodesInterface;

import kr.co.adflow.nms.web.vo.requisition.Requisitionsinfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class RequisitionsController {
	private static final String RETURNRESULT = "Controllerresult:::";
	private static final String PATH = "path:::";
	private static final String DATA = "data:::";
	private static final Logger logger = LoggerFactory
			.getLogger(RequisitionsController.class);
	@Autowired
	private RequisitionsService service;
	@Autowired
	RequisitionsMapper mapper;
	@Autowired
	RequisitionsUtil ut;

	// requisitions
	@RequestMapping(value = "/requisitions", method = RequestMethod.GET)
	public @ResponseBody
	String requisitions(HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		try {
			result = service.requisitions();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;

	}

	// requisitions/count
	@RequestMapping(value = "/requisitions/count", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsCount(HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		try {
			result = service.requisitionsCount();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;

	}

	// requisitions/deployed
	@RequestMapping(value = "/requisitions/deployed", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsDeployed(HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;
		try {
			result = service.requisitionsDeployed();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;

	}

	// requisitions/deployed/count

	@RequestMapping(value = "/requisitions/deployed/count", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsDeployedCount(HttpServletRequest request)
			throws HandleException {

		logger.info(PATH + request.getRequestURL());
		String result = null;
		try {
			result = service.requisitionsDeployedCount();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;

	}

	// requisitions/{name}

	@RequestMapping(value = "/requisitions/{name}", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsName(@PathVariable String name,
			HttpServletRequest request) throws HandleException {

		logger.info(PATH + request.getRequestURL());
		String result = null;
		try {
			result = (String) service.requisitions(name);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// /requisitions/{name}/nodes

	@RequestMapping(value = "/requisitions/{name}/nodes", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsNodes(@PathVariable String name,
			HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.requisitionsNodes(name);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;

	}

	// requisitions/{name}/nodes/{foreignId}
	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsNodes(@PathVariable String name,
			@PathVariable String foreignId, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.requisitionsNodes(name, foreignId);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// /requisitions/{name}/nodes/{foreignId}/interfaces

	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/interfaces", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsNodesInterfaces(@PathVariable String name,
			@PathVariable String foreignId, HttpServletRequest request)
			throws HandleException {

		String result = null;

		logger.info(PATH + request.getRequestURL());

		try {
			result = (String) service.requisitionsNodesInterfaces(name,
					foreignId);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// /requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}

	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress:.+}", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsNodesInterfaces(@PathVariable String name,
			@PathVariable String foreignId, @PathVariable String ipAddress,
			HttpServletRequest request) throws HandleException {

		String result = null;

		logger.info(PATH + request.getRequestURL());

		try {
			result = (String) service.requisitionsNodesInterfaces(name,
					foreignId, ipAddress);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services

	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsNodesInterfacesServices(@PathVariable String name,
			@PathVariable String foreignId, @PathVariable String ipAddress,
			HttpServletRequest request) throws HandleException {

		String result = null;

		logger.info(PATH + request.getRequestURL());

		try {
			result = (String) service.requisitionsNodesInterfacesServices(
					name, foreignId, ipAddress);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services/{service}

	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services/{service1}", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsNodesInterfacesServices(@PathVariable String name,
			@PathVariable String foreignId, @PathVariable String ipAddress,
			@PathVariable String service1, HttpServletRequest request)
			throws HandleException {

		String result = null;

		logger.info(PATH + request.getRequestURL());

		try {
			result = (String) service.requisitionsNodesInterfacesServices(
					name, foreignId, ipAddress, service1);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// requisitions/{name}/nodes/{foreignId}/categories
	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/categories", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsNodesCategories(@PathVariable String name,
			@PathVariable String foreignId, HttpServletRequest request)
			throws HandleException {

		String result = null;

		logger.info(PATH + request.getRequestURL());

		try {
			result = (String) service.requisitionsNodesCategories(name,
					foreignId);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// requisitions/{name}/nodes/{foreignId}/categories/{categoryName}
	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/categories/{categoryName}", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsNodesCategories(@PathVariable String name,
			@PathVariable String foreignId, @PathVariable String categoryName,
			HttpServletRequest request) throws HandleException {

		String result = null;

		logger.info(PATH + request.getRequestURL());

		try {
			result = (String) service.requisitionsNodesCategories(name,
					foreignId, categoryName);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// requisitions/{name}/nodes/{foreignId}/assets

	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/assets", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionsNodesAssets(@PathVariable String name,
			@PathVariable String foreignId, HttpServletRequest request)
			throws HandleException {

		String result = null;

		logger.info(PATH + request.getRequestURL());

		try {
			result = (String) service.requisitionsNodesAssets(name, foreignId);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// requisitions/{name}/nodes/{foreignId}/assets
	// <model-import foreign-source="chandjdjdj"/> !!Post!!
	// !!!POST
	@RequestMapping(value = "/requisitions", method = RequestMethod.POST)
	public @ResponseBody
	String requisitionsPost(@RequestBody String data, HttpServletRequest request)
			throws HandleException, MapperException,UtilException {

		Requisitionsinfo requisitions = null;
		String xmlData = null;
		logger.info(PATH + request.getRequestURL());
		logger.info(DATA + data);
		String result = null;
		try {

			requisitions = mapper.requisitionsInfo(data);
		} catch (MapperException e) {
			logger.error("Failed in Mapping", e);
			throw e;
		}

		try {
			xmlData = ut.xmlParsingRequisitions(requisitions);
			result = (String) service.requisitionsPostPro(xmlData);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// <node node-label="testCaseNode" foreign-id="13622227" building="test001"/>
	// {"node[{"node-label:"testCaseNode","foreign-id":"13622227","building":"test001"}]};
	/// requisitions/{name}/nodes
	// !!!POST
	@RequestMapping(value = "/requisitions/{name}/nodes ", method = RequestMethod.POST)
	public @ResponseBody
	String requisitionsPostNodes(@PathVariable String name,
			@RequestBody String data, HttpServletRequest request)
			throws HandleException, MapperException {

		RequisitionsNodes renodes = null;
		String xmlData = null;
		logger.info(PATH + request.getRequestURL());
		logger.info(DATA + data);
		String result = null;
		try {

			renodes = mapper.requisitionsNodes(data);
		} catch (MapperException e) {
			logger.error("Failed in Mapping", e);
			throw e;
		}

		try {
		
			xmlData = ut.xmlParsingRequisitionsNodes(renodes);
			result = (String) service.requisitionsPostNodesPro(xmlData, name);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}catch(UtilException e){
			
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// <interface snmp-primary="S" status="1" ip-addr="61.78.35.200" descr="">
	// /requisitions/{name}/nodes/{foreignId}/interfaces !!!POST
	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/interfaces ", method = RequestMethod.POST)
	public @ResponseBody
	String requisitionsPostNodesInterfaces(@PathVariable String name,
			@PathVariable String foreignId, @RequestBody String data,
			HttpServletRequest request) throws HandleException, MapperException,UtilException {

		RequisitionsNodesInterface nodeInterface = null;
		String xmlData = null;
		logger.info(PATH + request.getRequestURL());
		logger.info(DATA + data);
		String result = null;
		try {

			nodeInterface = mapper.requisitionsNodesInterfaces(data);
		} catch (MapperException e) {
			logger.error("Failed in Mapping", e);
			throw e;
		}

		try {
			
			xmlData = ut.xmlParsingRequisitionsNodesInterfaces(nodeInterface);
			result = (String) service.requisitionsPostNodesInterfacesPro(
					xmlData, name, foreignId);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}catch(UtilException e){
			logger.error("Failed in util..", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services
	// POST!!!!
	// <monitored-service service-name="ICMP"/>
	// {"monitored-service":[{"service-name":"ICMP"}]}
	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services", method = RequestMethod.POST)
	public @ResponseBody
	String requisitionServices(@PathVariable String name,
			@PathVariable String foreignId, @PathVariable String ipAddress,
			@RequestBody String data, HttpServletRequest request)
			throws HandleException, MapperException ,UtilException{

		kr.co.adflow.nms.web.vo.requisition.RequisitionsService rservice = null;
		String xmlData = null;
		logger.info(PATH + request.getRequestURL());
		logger.info(DATA + data);
		String result = null;
		try {

			rservice = mapper.requisitionsService(data);
		} catch (MapperException e) {
			logger.error("Failed in Mapping", e);
			throw e;
		}

		try {
		
			xmlData = ut.xmlParsingRequisitionsService(rservice);
			result = (String) service.requisitionServicesPro(xmlData, name, foreignId, ipAddress);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}catch (UtilException e){
			logger.error("Failed in util..", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// Post!!
	// requisitions/{name}/nodes/{foreignId}/categories
	// <category name="Production"/>
	// {"category":[{"name":"Production"}]}
	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/categories", method = RequestMethod.POST)
	public @ResponseBody
	String requisitionCategories(@PathVariable String name,
			@PathVariable String foreignId, @RequestBody String data,
			HttpServletRequest request) throws HandleException, MapperException,UtilException {

		RequisitionsCategory category = null;
		String xmlData = null;
		logger.info(PATH + request.getRequestURL());
		logger.info(DATA + data);
		String result = null;
		try {

			category = mapper.requisitionsCategory(data);
		} catch (MapperException e) {
			logger.error("Failed in Mapping", e);
			throw e;
		}

		try {
		
			xmlData = ut.xmlParsingReqCategory(category);
			result = (String) service.requisitionCategorysPro(xmlData, name,
					foreignId);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}catch(UtilException e){
			logger.error("Failed in util..", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// Post!!
	// /requisitions/{name}/nodes/{foreignId}/assets
	// <asset value="test" name="admin"/>
	// {"asset":[{"value":"test","name":"admin"}]}

	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/assets", method = RequestMethod.POST)
	public @ResponseBody
	String requisitionAssets(@PathVariable String name,
			@PathVariable String foreignId, @RequestBody String data,
			HttpServletRequest request) throws HandleException, MapperException,UtilException {

		RequisitionsAssets assets = null;
	
		String xmlData = null;
		logger.info(PATH + request.getRequestURL());
		logger.info(DATA + data);
		String result = null;
		try {

			assets = mapper.requisitionsAssets(data);
		} catch (MapperException e) {
			logger.error("Failed in Mapping", e);
			throw e;
		}

		try {
		
			xmlData = ut.xmlParsingReqAssets(assets);
			result = (String) service.requisitionAssetsPro(xmlData, name,
					foreignId);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}catch(UtilException e){
			logger.error("Failed in util..", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// PUT
	// /requisitions/{name}/import
	@RequestMapping(value = "/requisitions/{name}/import", method = RequestMethod.PUT)
	public @ResponseBody
	String requisitionImport(@PathVariable String name,
			HttpServletRequest request) throws HandleException {

		logger.info(PATH + request.getRequestURL());
		logger.info(DATA + name);
		String result = null;

		try {

			result = (String) service.requisitionImport(name);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
//			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// PUT
	// requisitions/{name}/import?rescanExisting=false
	@RequestMapping(value = "/requisitions/{name}/import?rescanExisting=false", method = RequestMethod.PUT)
	public @ResponseBody
	String requisitionImportreScan(@PathVariable String name,
			HttpServletRequest request) throws HandleException {

		logger.info(PATH + request.getRequestURL());
		logger.info(DATA + name);
		String result = null;

		try {

			result = (String) service.requisitionImportRescanP(name);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// PUT
	// requisitions/{name}
	// {"foreign-source":"chanhohoho"}
	@RequestMapping(value = "/requisitions/{name}", method = RequestMethod.PUT)
	public @ResponseBody
	String requisitionNameUpdate(@PathVariable String name,
			@RequestBody String data, HttpServletRequest request)
			throws HandleException, MapperException,UtilException {

		logger.info(PATH + request.getRequestURL());
		logger.info(DATA + name);
		logger.info("bodyData::" + data);
		String result = null;

		ReqPutName reqPutName = null;
		
		try {
			reqPutName = mapper.reqPutName(data);
		} catch (MapperException e) {
			logger.error("Failed in Mapping", e);
			throw e;
		}
		try {
		
			String parSingData = ut.ParsingReqNameData(reqPutName);
			result = (String) service.requisitioUpdate(name, parSingData);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}catch(UtilException e){
			logger.error("Failed in util..", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// PUt
	// /requisitions/{name}/nodes/{foreignId}
	//node-label=test1djdjdj foreign-id=1363657923 building=324234234
	// foreign-id="123455"
	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}", method = RequestMethod.PUT)
	public @ResponseBody
	String requisitionNodesUpdate(@PathVariable String name,
			@PathVariable String foreignId, @RequestBody String data,
			HttpServletRequest request) throws HandleException, MapperException,UtilException {

		logger.info(PATH + request.getRequestURL());
		logger.info(DATA + name);
		logger.info("bodyData::" + data);
		String result = null;
		ReqPutForID putForId = null;
	
		try {
			putForId = mapper.reqPutId(data);
		} catch (MapperException e) {
			logger.error("Failed in Mapping", e);
			throw e;
		}

		try {
		
			String parSingData = ut.ParsingReqPutID(putForId);

			result = (String) service.requisitionNameUpdate(name, foreignId,
					parSingData);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}catch(UtilException e){
			logger.error("Failed in util..", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// PUT
	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}

	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress:.+}", method = RequestMethod.PUT)
	public @ResponseBody
	String requisitionInterUpdate(@PathVariable String name,
			@PathVariable String foreignId, @PathVariable String ipAddress,@RequestBody String data,
			HttpServletRequest request) throws HandleException, MapperException,UtilException{

		logger.info(PATH + request.getRequestURL());
		logger.info(DATA + name);
		logger.info("bodyData::" + data);
		String result = null;
		ReqPutIP putIP = null;
		try {
			putIP = mapper.reqPutIP(data);
		} catch (MapperException e) {
			logger.error("Failed in Mapping", e);
			throw e;
		}
		try {
			
			String parSingData = ut.ParsingReqPutIP(putIP);
			result = (String) service.requisitionInterUpdate(name, foreignId,
					ipAddress,parSingData);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}catch(UtilException e){
			logger.error("Failed in util..", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;

	}

	// DELETE
	// /requisitions/{name}

	@RequestMapping(value = "/requisitions/{name}", method = RequestMethod.DELETE)
	public @ResponseBody
	String ReqDelete(@PathVariable String name, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.reqDelete(name);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// DELETE
	// requisitions/deployed/{name}

	@RequestMapping(value = "/requisitions/deployed/{name}", method = RequestMethod.DELETE)
	public @ResponseBody
	String ReqDeployDelete(@PathVariable String name, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.reqDeployDeletePro(name);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// DELETE
	// requisitions/{name}/nodes/{foreignId}

	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}", method = RequestMethod.DELETE)
	public @ResponseBody
	String ReqNodesDel(@PathVariable String name,
			@PathVariable String foreignId, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.reqNodesDelPro(name, foreignId);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// DELETE
	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}
	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress:.+} ", method = RequestMethod.DELETE)
	public @ResponseBody
	String ReqInterfaceDel(@PathVariable String name,
			@PathVariable String foreignId, @PathVariable String ipAddress,
			HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.reqInterDelPro(name, foreignId,
					ipAddress);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// DELETE
	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services/{service}
	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services/{service2}", method = RequestMethod.DELETE)
	public @ResponseBody
	String ReqInterfaceDel(@PathVariable String name,
			@PathVariable String foreignId, @PathVariable String ipAddress,
			@PathVariable String service2, HttpServletRequest request)
			throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.reqServiceDelPro(name, foreignId,
					ipAddress, service2);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// DELETE
	// requisitions/{name}/nodes/{foreignId}/categories/{category}

	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/categories/{category}", method = RequestMethod.DELETE)
	public @ResponseBody
	String ReqCategoryDel(@PathVariable String name,
			@PathVariable String foreignId, @PathVariable String category,
			HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.reqCategoryDelPro(name, foreignId,
					category);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}
		logger.debug(RETURNRESULT + result);
		return result;
	}

	// DELETE
	// requisitions/{name}/nodes/{foreignId}/assets/{field}

	@RequestMapping(value = "/requisitions/{name}/nodes/{foreignId}/assets/{field}", method = RequestMethod.DELETE)
	public @ResponseBody
	String ReqAssetsDel(@PathVariable String name,
			@PathVariable String foreignId, @PathVariable String field,
			HttpServletRequest request) throws HandleException {
		logger.info(PATH + request.getRequestURL());
		String result = null;

		try {
			result = (String) service.reqAssetDelPro(name, foreignId, field);
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
	@ExceptionHandler(UtilException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String processUtilException(HandleException e) {
		return "{\"code\":\"" + HttpServletResponse.SC_INTERNAL_SERVER_ERROR
				+ "\",\"message\":\"" + e.getMessage() + "\"}";
	}

}
