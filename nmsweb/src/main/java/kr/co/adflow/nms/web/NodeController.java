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
	String nodesAll(Locale locale, Model model) {

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
	public @ResponseBody String serchNode(@PathVariable String id ){

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

	/**
	 * sendNewSuspectEvent
	 * 
	 * @return The value input as a String.
	 * @exception Exception
	 */


}