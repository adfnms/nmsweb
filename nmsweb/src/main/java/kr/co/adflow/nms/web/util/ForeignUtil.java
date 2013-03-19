package kr.co.adflow.nms.web.util;

import kr.co.adflow.nms.web.exception.UtilException;
import kr.co.adflow.nms.web.vo.foreign.ForDetector;
import kr.co.adflow.nms.web.vo.foreign.ForPoliceS;
import kr.co.adflow.nms.web.vo.foreign.ForPutName;
import kr.co.adflow.nms.web.vo.foreign.ForeignInfo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ForeignUtil {
	

	private static final Logger logger = LoggerFactory
			.getLogger(ForeignUtil.class);

	//POST
	///foreignSources 
	// <foreign-source name="testzzzzz"/>
	//{"foreign-source":[{"name":"testzzzzz"}]}
	public String xmlParsingForeign(ForeignInfo forInfo)
			throws UtilException {
		String data = null;
		try {
			String name = forInfo.getName();
			StringBuffer bf = new StringBuffer();
			bf.append("<foreign-source name=\""+name+"\"/>");
			data = bf.toString();
			logger.debug("xmldata::" + data);
		} catch (Exception e) {
			throw new UtilException(e);
		}
		return data;
	}
	
	
	//<detector class="org.opennms.netmgt.provision.detector.simple.HttpDetector" name="chan2"/>
	//foreignSources/{name}/detectors 
	
	public String xmlParsingDetector(ForDetector detector)
			throws UtilException {
		String data = null;
		try {
			String name = detector.getName();
			String isClass=detector.getIsclass();
			StringBuffer bf = new StringBuffer();
			bf.append("<detector class=\""+isClass+"\" name=\""+name+"\"/>");
			data = bf.toString();
			logger.debug("xmldata::" + data);
		} catch (Exception e) {
			throw new UtilException(e);
		}
		return data;
	}
	
	//POST
		//foreignSources/{name}/policies 
		//<policy class="org.opennms.netmgt.provision.persist.policies.MatchingIpInterfacePolicy" name="pol2chan">
		//<parameter value="DISABLE_COLLECTION" key="action"/><parameter value="ALL_PARAMETERS" key="matchBehavior"/></policy>
		

	public String xmlParsingPolices(ForPoliceS polices)
			throws UtilException {
		String data = null;
		try {
			String name = polices.getName();
			String isClass=polices.getIsclass();
			String key=polices.getUser().get(0).getKey();
			String key2=polices.getUser().get(1).getKey();
			String value=polices.getUser().get(0).getValue();
			String value2=polices.getUser().get(1).getValue();
			
			StringBuffer bf = new StringBuffer();
			bf.append("<policy class=\""+isClass+"\" name=\""+name+"\"><parameter value=\""+value+"\" key=\""+key+"\"/><parameter value=\""+value2+"\" key=\""+key2+"\"/></policy>");
			data = bf.toString();
			logger.debug("xmldata::" + data);
		} catch (Exception e) {
			throw new UtilException(e);
		}
		return data;
	}
	//{"name":"data"}
	public String ParsingPutName(ForPutName putName)
			throws UtilException {
		String data = null;
		try {
			String name = putName.getName();
		
			
			StringBuffer bf = new StringBuffer();
			bf.append("name="+name);
			data = bf.toString();
			logger.debug("convertApplication::" + data);
		} catch (Exception e) {
			throw new UtilException(e);
		}
		return data;
	}
	
}
