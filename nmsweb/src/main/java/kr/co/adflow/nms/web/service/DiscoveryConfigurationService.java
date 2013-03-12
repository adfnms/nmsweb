package kr.co.adflow.nms.web.service;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import kr.co.adflow.nms.web.DefaultHandlerImpl;
import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.HandlerFactory;
import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.util.XmlUtil;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.adflow.nms.web.vo.DestPath.DestinationPaths;
import kr.co.adflow.nms.web.vo.DestPath.Path;
import kr.co.adflow.nms.web.vo.discoveryConfiguration.DiscoveryConfiguration;
import kr.co.adflow.nms.web.vo.eventconf.Events;
import kr.co.adflow.nms.web.vo.notifdConfiguration.NotifdConfiguration;
import kr.co.adflow.nms.web.vo.notificationCommands.NotificationCommands;
import kr.co.adflow.nms.web.vo.notifications.Notification;
import kr.co.adflow.nms.web.vo.notifications.Notifications;

/**
 * NotificationsProcess
 * 
 * @author kicho@adflow.co.kr
 * @version 1.1
 */
@Service
public class DiscoveryConfigurationService {

	//private static final String XMLPATH = "d:\\OpenNMS\\etc\\";

	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String Accept = "accept";
	private @Value("#{config['XMLPATH']}") String xmlPath;
	private @Value("#{config['NMSURL']}") String ipAddr;
	private @Value("#{config['LOGINID']}") String loginId;
	private @Value("#{config['LOGINPASS']}") String loginPass;
	private @Value("#{config['GROOVYPATH']}") String groovyPath;
	private static final Logger logger = LoggerFactory
			.getLogger(DiscoveryConfigurationService.class);

	@Autowired
	private Handler handler;

	public String discoveryConfiguration() throws HandleException {

		StringBuffer result = new StringBuffer();
		;

		try {
			// xml Read
			XmlUtil xUtil = new XmlUtil();
			String filePath = xmlPath + "discovery-configuration.xml";

			Class<DiscoveryConfiguration> classname = DiscoveryConfiguration.class;
			DiscoveryConfiguration dConfig = new DiscoveryConfiguration();

			Object ob = xUtil.xmlRead(filePath, classname, dConfig);

			dConfig = (DiscoveryConfiguration) ob;
			
			
			
			result.append("{\"threads\":\""+dConfig.getThreads()+"\",");
			result.append("\"packetsPerSecond\":\""+dConfig.getPacketsPerSecond()+"\",");
			result.append("\"initialSleepTime\":\""+dConfig.getInitialSleepTime()+"\",");
			result.append("\"restartSleepTime\":\""+dConfig.getRestartSleepTime()+"\",");
			result.append("\"retries\":\""+dConfig.getRetries()+"\",");
			result.append("\"timeout\":\""+dConfig.getTimeout()+"\",");
			
			
			int sizeSpecific = dConfig.getSpecific().size();
			
			if (sizeSpecific > 0) {

				result.append("\"specific\":[");
				
				for (int i = 0; i < sizeSpecific; i++) {
//					logger.debug("getName::" + dConfig.getPath().get(i).getName());

					result.append("{\"retries\":\"" + dConfig.getSpecific().get(i).getRetries()+"\",");
					result.append("\"timeout\":\"" + dConfig.getSpecific().get(i).getTimeout()+"\",");
					result.append("\"value\":\"" + dConfig.getSpecific().get(i).getValue()+"\"},");

				}
				
				result.deleteCharAt(result.length() - 1);
				result.append("],");
			}
			
			
			int sizeIncludeRange = dConfig.getIncludeRange().size();
			
			if (sizeIncludeRange > 0) {

				result.append("\"includeRange\":[");
				
				for (int i = 0; i < sizeIncludeRange; i++) {

					result.append("{\"retries\":\"" + dConfig.getIncludeRange().get(i).getRetries()+"\",");
					result.append("\"timeout\":\"" + dConfig.getIncludeRange().get(i).getTimeout()+"\",");
					result.append("\"begin\":\"" + dConfig.getIncludeRange().get(i).getBegin()+"\",");
					result.append("\"end\":\"" + dConfig.getIncludeRange().get(i).getEnd()+"\"},");
				}
				
				result.deleteCharAt(result.length() - 1);
				result.append("],");
			}
			
			int sizeExcludeRange = dConfig.getExcludeRange().size();
			
			if (sizeExcludeRange > 0) {

				result.append("\"excludeRange\":[");
				
				for (int i = 0; i < sizeExcludeRange; i++) {

					result.append("{\"begin\":\"" + dConfig.getExcludeRange().get(i).getBegin()+"\",");
					result.append("\"end\":\"" + dConfig.getExcludeRange().get(i).getEnd()+"\"},");
				}
				
				result.deleteCharAt(result.length() - 1);
				result.append("],");
			}
			
			result.deleteCharAt(result.length() - 1);
			
				result.append("}");




			logger.debug("result::" + result.toString());

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result.toString();
	}


	public String discoveryConfigurationPost(DiscoveryConfiguration dConfig)
			throws HandleException {

		String result = null;

		try {
			// xml Read
			XmlUtil xUtil = new XmlUtil();
			
			String filePath = xmlPath + "discovery-configuration.xml";

			Class<DiscoveryConfiguration> classname = DiscoveryConfiguration.class;

			// xml Write
			result = xUtil.xmlWrite(filePath, classname, dConfig);
			
			ClassLoader parent = getClass().getClassLoader();
			GroovyClassLoader loader = new GroovyClassLoader(parent);
			Class groovyClass = loader.parseClass(new File(
					groovyPath+"sendNewEvent.groovy"));

			// let's call some method on an instance
			GroovyObject groovyObject = (GroovyObject) groovyClass
					.newInstance();
			String[] args = {"127.0.0.1"};
			Object ret = groovyObject.invokeMethod("sendNewSuspectEvent", args);
			
			logger.debug("ret :" + ret);
			result ="{\"result\":\"success\"}";
			

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}


}
