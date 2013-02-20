package kr.co.adflow.nms.web;


import java.io.File;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.co.adflow.nms.web.node.NodeProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * /** Handles requests for the application home page.
 */
@Controller
public class NodeController {

	private static final Logger logger = LoggerFactory
			.getLogger(NodeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String node(Locale locale, Model model) {

		return "nodeList";
	}

	@RequestMapping(value = "/nodes", method = RequestMethod.GET)
	public @ResponseBody
	String nodes(Locale locale, Model model) {

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		try {
			result = (String) controll.nodes();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
		
	}

	@RequestMapping(value = "/nodes/{id}", method = RequestMethod.GET)
	public @ResponseBody String nodeId(@PathVariable String id ){

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;
		
		
		System.out.println("ddddd");
		logger.info("daadfd");

		try {
			result = (String) controll.nodes(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/nodes/{id}/ipinterfaces", method = RequestMethod.GET)
	public @ResponseBody String nodeIdIpinterfaces(@PathVariable String id ){

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		try {
			result = (String) controll.nodesIpInterfaces(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	
<<<<<<< HEAD
=======
	@RequestMapping(value = "/nodes/{id}/ipinterfaces/{ipAddress}", method = RequestMethod.GET)
	public @ResponseBody String nodeIdIpinterfaces(@PathVariable String id, @PathVariable String ipAddress ){

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		try {
			result = (String) controll.nodesIpInterfaces(id, ipAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/nodes/{id}/ipinterfaces/{ipAddress}/services", method = RequestMethod.GET)
	public @ResponseBody String nodeIdIpinterfacesService(@PathVariable String id, @PathVariable String ipAddress ){

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		try {
			result = (String) controll.nodesIpInterfacesServices(id, ipAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/nodes/{id}/ipinterfaces/{ipAddress}/services/{serviceName}", method = RequestMethod.GET)
	public @ResponseBody String nodeIdIpinterfacesService(@PathVariable String id, @PathVariable String ipAddress, @PathVariable String serviceName){

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		try {
			result = (String) controll.nodesIpInterfacesServices(id, ipAddress, serviceName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/nodes/{id}/snmpinterfaces", method = RequestMethod.GET)
	public @ResponseBody String nodeSnmpinterfaces(@PathVariable String id){

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		try {
			result = (String) controll.nodesSnmpinterfaces(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	
	@RequestMapping(value = "/nodes/{id}/snmpinterfaces/{ifIndex}", method = RequestMethod.GET)
	public @ResponseBody String nodeSnmpinterfaces(@PathVariable String id, @PathVariable String ifIndex){

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		try {
			result = (String) controll.nodesSnmpinterfaces(id, ifIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/nodes/{id}/categories", method = RequestMethod.GET)
	public @ResponseBody String nodeCategories(@PathVariable String id){

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		try {
			result = (String) controll.nodesCategories(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/nodes/{id}/categories/{categoryName}", method = RequestMethod.GET)
	public @ResponseBody String nodeCategories(@PathVariable String id, @PathVariable String categoryName){

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		try {
			result = (String) controll.nodesCategories(id,categoryName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/nodes/{id}/assetRecord", method = RequestMethod.GET)
	public @ResponseBody String nodeAssetRecord(@PathVariable String id){

		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		try {
			result = (String) controll.nodesCategories(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	
	
	/**
	 * sendNewSuspectEvent
	 * 
	 * @return The value input as a String.
	 * @exception Exception
	 */

>>>>>>> branch 'master' of https://github.com/adfnms/nmsweb.git

}
