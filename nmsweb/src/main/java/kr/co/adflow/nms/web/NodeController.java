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
		NodeProcess controll = new NodeProcess();
		String result = null;

		String[] id = params.get("id");

		try {
			result = (String) controll.nodes(id[0]);
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
	@RequestMapping(value = "/sendNewSuspectEvent", method = RequestMethod.GET)
	public @ResponseBody
	String sendNewSuspectEvent() throws Exception {

		try {

			ClassLoader parent = getClass().getClassLoader();
			GroovyClassLoader loader = new GroovyClassLoader(parent);
			Class groovyClass = loader.parseClass(new File(
					"src/test/groovy/script/sendNewSuspectEvent.groovy"));

			// let's call some method on an instance
			GroovyObject groovyObject = (GroovyObject) groovyClass
					.newInstance();
			String[] args = { "127.0.0.2", "192.168.112.128" };
			Object ret = groovyObject.invokeMethod("sendNewSuspectEvent", args);
			logger.debug("ret :" + ret);
			return "{\"result\":\"success\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"error\":\"{\"code\":\"100\",\"message\":\""
					+ e.getMessage() + "\"}\"}";
		}
	}

}