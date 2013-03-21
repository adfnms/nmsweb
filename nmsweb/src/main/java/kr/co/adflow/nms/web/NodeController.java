package kr.co.adflow.nms.web;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.exception.ValidationException;
import kr.co.adflow.nms.web.mapper.NodeMapper;
import kr.co.adflow.nms.web.mapper.PathOutagesMapper;
import kr.co.adflow.nms.web.service.MapsService;
import kr.co.adflow.nms.web.service.NodeService;
import kr.co.adflow.nms.web.vo.SchoedOutage;

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

/**
 * /** Handles requests for the application home page.
 */
@Controller
public class NodeController {

	private static final String RETURNRESULT = "result:::";
	private static final String PATH = "path:::";
	private static final Logger logger = LoggerFactory
			.getLogger(NodeController.class);

	@Autowired
	private NodeService service; 
	
	@Autowired
	private NodeMapper mapper;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String node() {

		return "nodeList";
	}

	@RequestMapping(value = "/nodes", method = RequestMethod.GET)
	public @ResponseBody
	String nodes(HttpServletRequest request) throws HandleException {

		String result = null;

		logger.info(PATH + request.getRequestURI());

		// 2013-02-23
		// Parameter check �� ȣ�� �б�
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

			// ������ "&" ����.
			filter.deleteCharAt(filter.length() - 1);
			logger.debug("Param:::" + filter.toString());

			try {
				result = (String) service.nodesFilter(filter.toString());
			} catch (HandleException e) {
				logger.error("Failed in processing", e);
				throw e;
			}

		} else {

			try {
				result = (String) service.nodes();
			} catch (HandleException e) {
				logger.error("Failed in processing", e);
				throw e;
			}

		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}", method = RequestMethod.GET)
	public @ResponseBody
	String nodeId(HttpServletRequest request, @PathVariable String id)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.nodes(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/ipinterfaces", method = RequestMethod.GET)
	public @ResponseBody
	String nodeIdIpinterfaces(HttpServletRequest request,
			@PathVariable String id) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.nodesIpInterfaces(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/ipinterfaces/{ipAddress:.+}", method = RequestMethod.GET)
	public @ResponseBody
	String nodeIdIpinterfaces(HttpServletRequest request,
			@PathVariable String id, @PathVariable String ipAddress)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		logger.debug("AAAAAA::::" + ipAddress);

		try {
			result = (String) service.nodesIpInterfaces(id, ipAddress);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/ipinterfaces/{ipAddress}/services", method = RequestMethod.GET)
	public @ResponseBody
	String nodeIdIpinterfacesService(HttpServletRequest request,
			@PathVariable String id, @PathVariable String ipAddress)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());
		logger.debug("AAAAAA::::" + ipAddress);

		try {
			result = (String) service.nodesIpInterfacesServices(id, ipAddress);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/ipinterfaces/{ipAddress}/services/{serviceName}", method = RequestMethod.GET)
	public @ResponseBody
	String nodeIdIpinterfacesService(HttpServletRequest request,
			@PathVariable String id, @PathVariable String ipAddress,
			@PathVariable String serviceName) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.nodesIpInterfacesServices(id, ipAddress,
					serviceName);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/snmpinterfaces", method = RequestMethod.GET)
	public @ResponseBody
	String nodeSnmpinterfaces(HttpServletRequest request,
			@PathVariable String id) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.nodesSnmpinterfaces(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/snmpinterfaces/{ifIndex}", method = RequestMethod.GET)
	public @ResponseBody
	String nodeSnmpinterfaces(HttpServletRequest request,
			@PathVariable String id, @PathVariable String ifIndex)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.nodesSnmpinterfaces(id, ifIndex);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/categories", method = RequestMethod.GET)
	public @ResponseBody
	String nodeCategories(HttpServletRequest request, @PathVariable String id)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.nodesCategories(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/categories/{categoryName}", method = RequestMethod.GET)
	public @ResponseBody
	String nodeCategories(HttpServletRequest request, @PathVariable String id,
			@PathVariable String categoryName) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.nodesCategories(id, categoryName);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/assetRecord", method = RequestMethod.GET)
	public @ResponseBody
	String nodeAssetRecord(HttpServletRequest request, @PathVariable String id)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.nodesAssetRecord(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	@RequestMapping(value = "/serviceList", method = RequestMethod.GET)
	public @ResponseBody
	String serviceList(HttpServletRequest request)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.serviceList();
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	@RequestMapping(value = "/nodes/searchService/{serviceId}", method = RequestMethod.GET)
	public @ResponseBody
	String nodeSearchService(HttpServletRequest request, @PathVariable String serviceId)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.nodeSearchService(serviceId);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	@RequestMapping(value = "/nodes/searchIp/{iplike:.+}", method = RequestMethod.GET)
	public @ResponseBody
	String nodeSearchIp(HttpServletRequest request, @PathVariable String iplike)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.nodeSearchIp(iplike);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	
	
	
	// /// POST /////
	@RequestMapping(value = "/nodes/scan/{ip:.+}", method = RequestMethod.POST)
	public @ResponseBody
	String nodeIpPost(HttpServletRequest request, @PathVariable String ip)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.nodeIpPost(ip);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}
	
	
//	@RequestMapping(value = "/sched-outages", method = RequestMethod.POST)
//	public @ResponseBody
//	String schedOutagesPost(HttpServletRequest request, @RequestBody String data)
//			throws HandleException, MapperException {
//
//		String result = null;
//		logger.info(PATH + request.getRequestURI());
//
//		SchoedOutage schoedOutage = null;
//		String xml = null;
//
//		// String data2 =
//		// "<outage type=\"specific\" name=\"test4\"><time ends=\"20-Feb-2013 23:59:59\" begins=\"20-Feb-2013 21:00:00\"/><node id=\"16\"/></outage>";
//		// String data3 =
//		// "{\"@type\":\"specific\",\"@name\":\"test4\",\"time\":{\"@ends\":\"20-Feb-2013 23:59:59\",\"@begins\":\"20-Feb-2013 21:00:00\"},\"node\":{\"@id\":\"16\"}}";
//
//		logger.debug("fdfdfe:::" + data);
//
//		try {
//
//			schoedOutage = mapper.schoedOutageMapping(data);
//
//			xml = (String) mapper.schoedOutagePostMapping(schoedOutage);
//
//		} catch (MapperException e) {
//			logger.error("Failed in processing", e);
//			throw e;
//		}
//
//		logger.debug("adf:::" + xml);
//
//		try {
//			result = (String) service.schedOutagesPost(xml);
//		} catch (HandleException e) {
//			logger.error("Failed in processing", e);
//			throw e;
//		}
//
//		logger.debug(RETURNRESULT + result);
//		return result;
//	}

	
	// PUT
	// /nodes/{id}/snmpinterfaces/{ifIndex}?collect=(UC|UD|Default)
	@RequestMapping(value = "/nodes/{id}/snmpinterfaces/{ifIndex}", method = RequestMethod.PUT)
	public @ResponseBody
	String nodesSnmpinterfacesPut(HttpServletRequest request, @PathVariable String id,@PathVariable String ifIndex)
			throws HandleException, ValidationException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		// 2013-02-23
		// Parameter check Method 호추 분기
		Enumeration eParam = request.getParameterNames();

		if (eParam.hasMoreElements()) {
			StringBuffer prams = new StringBuffer();

			String pName = (String) eParam.nextElement();

			if (pName.equals("collect")) {

				String collect = request.getParameter(pName);

				if (collect.equals("UC")||collect.equals("UN")||collect.equals("D")) {
					
					try {
						result = (String) service.nodesSnmpinterfacesPut(id, ifIndex, collect);
					} catch (HandleException e) {
						logger.error("Failed in processing", e);
						throw e;
					}

					
				} else {

					logger.error("Must supply the 'collect' parameter, set to either 'D' or 'UC' or 'UN'");
					try {
						throw new ValidationException(
								"Must supply the 'collect' parameter, set to either 'D' or 'UC' or 'UN'");
					} catch (ValidationException e) {
						throw e;
					}

				}


			} else {

				logger.error("Must supply the parameter name, set to either 'collect'");
				try {

					throw new ValidationException(
							"Must supply the parameter name, set to either 'collect'");

				} catch (ValidationException e) {
					throw e;
				}

			}

			logger.debug("Param:::" + prams.toString());


		} else {

			logger.error("Must supply the parameter name, set to either 'collect'");
			try {

				throw new ValidationException(
						"Must supply the parameter name, set to either 'collect'");

			} catch (ValidationException e) {
				throw e;
			}

		}

		logger.debug(RETURNRESULT + result);
		return result;

	}

	
	// /nodes/{id}/ipinterfaces/{ipAddress}/services/{servicesName}?status=(R|S)
	@RequestMapping(value = "/nodes/{id}/ipinterfaces/{ipAddress:.+}/services/{servicesName}", method = RequestMethod.PUT)
	public @ResponseBody
	String nodesIpInterfacesServicesPut(HttpServletRequest request, @PathVariable String id,@PathVariable String ipAddress,@PathVariable String servicesName)
			throws HandleException, ValidationException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		// 2013-02-23
		// Parameter check Method 호추 분기
		Enumeration eParam = request.getParameterNames();

		if (eParam.hasMoreElements()) {
			StringBuffer prams = new StringBuffer();

			String pName = (String) eParam.nextElement();

			if (pName.equals("status")) {

				String status = request.getParameter(pName);

				if (status.equals("R")||status.equals("S")) {
					
					try {
						result = (String) service.nodesIpInterfacesServicesPut(id, ipAddress, servicesName, status);
					} catch (HandleException e) {
						logger.error("Failed in processing", e);
						throw e;
					}

					
				} else {

					logger.error("Must supply the 'collect' parameter, set to either 'R' or 'S'");
					try {
						throw new ValidationException(
								"Must supply the 'collect' parameter, set to either 'R' or 'S'");
					} catch (ValidationException e) {
						throw e;
					}

				}


			} else {

				logger.error("Must supply the parameter name, set to either 'status'");
				try {

					throw new ValidationException(
							"Must supply the parameter name, set to either 'status'");

				} catch (ValidationException e) {
					throw e;
				}

			}

			logger.debug("Param:::" + prams.toString());


		} else {

			logger.error("Must supply the parameter name, set to either 'status'");
			try {

				throw new ValidationException(
						"Must supply the parameter name, set to either 'status'");

			} catch (ValidationException e) {
				throw e;
			}

		}

		logger.debug(RETURNRESULT + result);
		return result;

	}

	// /nodes/{id}/ipinterfaces/{ipAddress}?isManaged=(M|F)
	@RequestMapping(value = "/nodes/{id}/ipinterfaces/{ipAddress:.+}", method = RequestMethod.PUT)
	public @ResponseBody
	String nodesIpInterfacesPut(HttpServletRequest request, @PathVariable String id,@PathVariable String ipAddress)
			throws HandleException, ValidationException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		// 2013-02-23
		// Parameter check Method 호추 분기
		Enumeration eParam = request.getParameterNames();

		if (eParam.hasMoreElements()) {
			StringBuffer prams = new StringBuffer();

			String pName = (String) eParam.nextElement();

			if (pName.equals("isManaged")) {

				String isManaged = request.getParameter(pName);

				if (isManaged.equals("M")||isManaged.equals("F")) {
					
					try {
						result = (String) service.nodesIpInterfacesPut(id, ipAddress, isManaged);
					} catch (HandleException e) {
						logger.error("Failed in processing", e);
						throw e;
					}

					
				} else {

					logger.error("Must supply the 'isManaged' parameter, set to either 'M' or 'F'");
					try {
						throw new ValidationException(
								"Must supply the 'isManaged' parameter, set to either 'M' or 'F'");
					} catch (ValidationException e) {
						throw e;
					}

				}


			} else {

				logger.error("Must supply the parameter name, set to either 'isManaged'");
				try {

					throw new ValidationException(
							"Must supply the parameter name, set to either 'isManaged'");

				} catch (ValidationException e) {
					throw e;
				}

			}

			logger.debug("Param:::" + prams.toString());


		} else {

			logger.error("Must supply the parameter name, set to either 'isManaged'");
			try {

				throw new ValidationException(
						"Must supply the parameter name, set to either 'isManaged'");

			} catch (ValidationException e) {
				throw e;
			}

		}

		logger.debug(RETURNRESULT + result);
		return result;

	}	
	
	
	// /nodes/{id}?label=(label Name)
	@RequestMapping(value = "/nodes/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	String nodesPut(HttpServletRequest request, @PathVariable String id)
			throws HandleException, ValidationException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		// 2013-02-23
		// Parameter check Method 호추 분기
		Enumeration eParam = request.getParameterNames();

		if (eParam.hasMoreElements()) {
			StringBuffer prams = new StringBuffer();

			String pName = (String) eParam.nextElement();

			if (pName.equals("label")) {

				String label = request.getParameter(pName);

									
				try {
					result = (String) service.nodesPut(id, label);
				} catch (HandleException e) {
					logger.error("Failed in processing", e);
					throw e;
				}

					
				


			} else {

				logger.error("Must supply the parameter name, set to either 'label'");
				try {

					throw new ValidationException(
							"Must supply the parameter name, set to either 'label'");

				} catch (ValidationException e) {
					throw e;
				}

			}

			logger.debug("Param:::" + prams.toString());


		} else {

			logger.error("Must supply the parameter name, set to either 'label'");
			try {

				throw new ValidationException(
						"Must supply the parameter name, set to either 'label'");

			} catch (ValidationException e) {
				throw e;
			}

		}

		logger.debug(RETURNRESULT + result);
		return result;

	}	

	// /// DELETE /////
	@RequestMapping(value = "/nodes/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	String nodesDelete(HttpServletRequest request, @PathVariable String id)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.nodesDelete(id);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/ipinterfaces/{ipAddress}", method = RequestMethod.DELETE)
	public @ResponseBody
	String nodesIpinterfacesDelete(HttpServletRequest request,
			@PathVariable String id, @PathVariable String ipAddress)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.nodesIpinterfacesDelete(id, ipAddress);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/ipInterfaces/{ipAddress}/services/{serviceid}", method = RequestMethod.DELETE)
	public @ResponseBody
	String nodesIpInterfacesServicesDelete(HttpServletRequest request,
			@PathVariable String id, @PathVariable String ipAddress,
			@PathVariable String serviceid) throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.nodesIpInterfacesServicesDelete(id,
					ipAddress, serviceid);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/snmpinterfaces/{ifIndex}", method = RequestMethod.DELETE)
	public @ResponseBody
	String nodesSnmpinterfacesDelete(HttpServletRequest request,
			@PathVariable String id, @PathVariable String ifIndex)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.nodesSnmpinterfacesDelete(id, ifIndex);
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		}

		logger.debug(RETURNRESULT + result);
		return result;
	}

	@RequestMapping(value = "/nodes/{id}/categories/{categoryName}", method = RequestMethod.DELETE)
	public @ResponseBody
	String nodesCategoriesDelete(HttpServletRequest request,
			@PathVariable String id, @PathVariable String categoryName)
			throws HandleException {

		String result = null;
		logger.info(PATH + request.getRequestURI());

		try {
			result = (String) service.nodesCategoriesDelete(id, categoryName);
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
