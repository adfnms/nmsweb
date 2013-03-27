package kr.co.adflow.nms.web;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ListModel;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.exception.UtilException;
import kr.co.adflow.nms.web.mapper.ServiceIdNameMapper;
import kr.co.adflow.nms.web.mapper.CategoryGroupMapper;
import kr.co.adflow.nms.web.service.DashBoardService;
import kr.co.adflow.nms.web.util.CategoryUtil;
import kr.co.adflow.nms.web.vo.categoryDetail.CategoryInfo;
import kr.co.adflow.nms.web.vo.categoryDetail.CategoryInfoList;
import kr.co.adflow.nms.web.vo.resultcategory.CategoryJsonGroup;
import kr.co.adflow.nms.web.vo.servicesid.ServiceVo;

import org.codehaus.groovy.tools.shell.util.NoExitSecurityManager;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class DashBoardController {
	private static final Logger logger = LoggerFactory
			.getLogger(DashBoardController.class);
	private static final String INVALUE = "invalue:::";
	private static final String XMLDATA = "xmlData:::";
	private static final String RETURNRESULT = "Controllerresult:::";
	private static final String PATH = "path:::";
	@Autowired
	ServiceIdNameMapper serviceMapper;
	@Autowired
	ServiceVo serviceVo;
	@Autowired
	CategoryGroupMapper cateMapper;
	@Autowired
	CategoryJsonGroup group;
	@Autowired
	CategoryUtil cateUtil;
	@Autowired
	private DashBoardService service;

	// dashBoard
	@RequestMapping(value = "/dashboardtest", method = RequestMethod.GET)
	public @ResponseBody
	String dashboardGroup(@RequestBody String data, HttpServletRequest request)
			throws HandleException, MapperException, UtilException,
			JsonGenerationException, JsonMappingException, IOException {
		logger.info(PATH + request.getRequestURL());
		logger.debug(INVALUE + data);
		String categoryString = null;
		String serviceString = null;
		try {
			service.init();
			categoryString = (String) service.categoryJsonXml();
			serviceString = (String) service.serviceIDandNameJson();
			serviceVo = serviceMapper.serviceInfo2(serviceString);

			group = cateMapper.cateJson(categoryString);

			// netWork
			int netWorkSize = group.netWorkService().size();
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < netWorkSize; i++) {

				buf.append(group.netWorkService().get(i).getNetWorkServers());
			}
			String netWorkInterfaces = buf.toString();
			logger.debug("netWorkInterfaces::" + netWorkInterfaces);
			logger.debug("netWorkInterfaces::" + netWorkInterfaces);
			logger.debug("netWorkInterfaces::" + netWorkInterfaces);
			logger.debug("netWorkInterfaces::" + netWorkInterfaces);
			logger.debug("netWorkInterfaces::" + netWorkInterfaces);

			// database
			int dataBaseServerSize = group.dataBaseServer().size();
			StringBuffer buf1 = new StringBuffer();
			for (int i = 0; i < dataBaseServerSize; i++) {

				buf1.append(group.dataBaseServer().get(i).getDataBaseServer());
			}
			String dataBaseServer = buf1.toString();

			// dnsserver
			int dnsSize = group.dnsDhcpServers().size();
			StringBuffer buf2 = new StringBuffer();
			for (int i = 0; i < dnsSize; i++) {

				buf2.append(group.dnsDhcpServers().get(i).getDnsDhcpServer());
			}
			String dnsDhcpServers = buf2.toString();

			// eamilserver
			int emailSize = group.emailServers().size();
			StringBuffer buf3 = new StringBuffer();
			for (int i = 0; i < emailSize; i++) {

				buf3.append(group.emailServers().get(i).getEmailServer());
			}
			String emailServers = buf3.toString();

			// jmxserver
			int jmxSize = group.jmxServers().size();
			StringBuffer buf4 = new StringBuffer();
			for (int i = 0; i < jmxSize; i++) {

				buf4.append(group.jmxServers().get(i).getJmxServers());
			}
			String jmxServers = buf4.toString();

			// otherServer
			int otherSize = group.otherServers().size();
			StringBuffer buf5 = new StringBuffer();
			for (int i = 0; i < otherSize; i++) {

				buf5.append(group.otherServers().get(i).getOtherServers());
			}
			String otherServers = buf5.toString();

			// webServer
			int websize = group.webServers().size();
			StringBuffer buf6 = new StringBuffer();
			for (int i = 0; i < websize; i++) {

				buf6.append(group.webServers().get(i).getWebServers());
			}
			String webServers = buf6.toString();

			// dataBaseId
			String dataBaseServerID = null;
			String dataBaseServergroup = "DatabaseServer";
			dataBaseServerID = cateUtil.categoriesId(dataBaseServer, serviceVo);
			logger.debug("dataBaseServerID::" + dataBaseServerID);
			CategoryInfoList infoListDatabase = new CategoryInfoList();
			infoListDatabase = service.getCategoryNodeIdServiceID(
					dataBaseServerID, dataBaseServergroup);
			String dataBaseServerJson = cateUtil
					.cateGoryJackSon(infoListDatabase);
			logger.debug("dataBaseServerJson::" + dataBaseServerJson);

			// netWorkInterfacesID
			String netWorkInterfacesID = null;
			String netWorkInterfacesGroup = "NetWorkInterfaces";
			netWorkInterfacesID = cateUtil.categoriesId(netWorkInterfaces,
					serviceVo);
			logger.debug("netWorkInterfacesID::" + netWorkInterfacesID);
			CategoryInfoList infoListNetWorkInterfaces = new CategoryInfoList();
			infoListNetWorkInterfaces = service.getCategoryNodeIdServiceID(
					netWorkInterfacesID, netWorkInterfacesGroup);
			String netWorkInterfacesJson = cateUtil
					.cateGoryJackSon(infoListNetWorkInterfaces);
			logger.debug("netWorkInterfacesJson::" + netWorkInterfacesJson);

			// dnsDhcpServersID
			String dnsDhcpServersID = null;
			String dnsDhcpServersGroup = "DnsDhcpServers";
			dnsDhcpServersID = cateUtil.categoriesId(dnsDhcpServers, serviceVo);
			logger.debug("dnsDhcpServersID::" + dnsDhcpServersID);
			CategoryInfoList infoListDnsDhcpServers = new CategoryInfoList();
			infoListDnsDhcpServers = service.getCategoryNodeIdServiceID(
					dnsDhcpServersID, dnsDhcpServersGroup);
			String DhcpServersJson = cateUtil
					.cateGoryJackSon(infoListDnsDhcpServers);
			logger.debug("DhcpServersJson::" + DhcpServersJson);

			// emailServersID
			String emailServersID = null;
			String emailServersGroup = "EmailServers";
			emailServersID = cateUtil.categoriesId(emailServers, serviceVo);
			logger.debug("emailServersID::" + emailServersID);

			CategoryInfoList infoListEmailServersID = new CategoryInfoList();
			infoListEmailServersID = service.getCategoryNodeIdServiceID(
					emailServersID, emailServersGroup);
			String emailServersJson = cateUtil
					.cateGoryJackSon(infoListEmailServersID);
			logger.debug("emailServersJson::" + emailServersJson);

			// otherServersID
			String otherServersID = null;
			String otherServersGroup = "OtherServers";
			otherServersID = cateUtil.categoriesId(otherServers, serviceVo);
			logger.debug("otherServersID::" + otherServersID);

			CategoryInfoList infoListotherServersID = new CategoryInfoList();
			infoListotherServersID = service.getCategoryNodeIdServiceID(
					otherServersID, otherServersGroup);
			String otherServersJson = cateUtil
					.cateGoryJackSon(infoListotherServersID);
			logger.debug("otherServersJson::" + otherServersJson);

			// jmxServersID
			String jmxServersID = null;
			String jmxServersGroup = "JmxServer";
			jmxServersID = cateUtil.categoriesId(jmxServers, serviceVo);
			logger.debug("jmxServersID::" + jmxServersID);

			CategoryInfoList infoListjmxServersID = new CategoryInfoList();
			infoListotherServersID = service.getCategoryNodeIdServiceID(
					jmxServersID, jmxServersGroup);
			String jmxServersJson = cateUtil
					.cateGoryJackSon(infoListjmxServersID);
			logger.debug("jmxServersJson::" + jmxServersJson);

			// webServersID
			String webServersID = null;
			webServersID = cateUtil.categoriesId(webServers, serviceVo);
			logger.debug("webServersID::" + webServersID);
			
			
			
			

			// /////
		} catch (HandleException e) {
			logger.error("Failed in processing", e);
			throw e;
		} catch (UtilException e) {
			logger.error("Filed in Util...", e);
			throw e;
		} catch (MapperException e) {
			logger.error("Failed in Mapper...", e);
			throw e;
		}
		logger.debug(RETURNRESULT + categoryString);
		return categoryString;
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
