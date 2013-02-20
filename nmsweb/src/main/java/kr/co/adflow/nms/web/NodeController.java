package kr.co.adflow.nms.web;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.co.adflow.nms.web.node.NodeProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * /** Handles requests for the application home page.
 */
@Controller
public class NodeController {

	private static final Logger logger = LoggerFactory
			.getLogger(NodeController.class);

	@RequestMapping(value = "/nodes", method = RequestMethod.GET)
	public String node(Locale locale, Model model) {

		return "nodeList";
	}

	@RequestMapping(value = "/nodesAll", method = RequestMethod.GET)
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

	@RequestMapping(value = "/serchNode", method = RequestMethod.GET)
	public @ResponseBody
	String serchNode(HttpServletRequest request) {

		Map<String, String[]> params = request.getParameterMap();
		NodeProcess controll = NodeProcess.getProcess();
		String result = null;

		String[] id = params.get("id");

		try {
			result = (String) controll.nodes(id[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	

}