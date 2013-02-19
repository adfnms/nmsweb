package kr.co.adflow.nms.web;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.co.adflow.nms.web.node.NodeProcess;

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

	@RequestMapping(value = "/nodes", method = RequestMethod.GET)
	public String node(Locale locale, Model model) {

		return "nodeList";
	}

	@RequestMapping(value = "/nodesAll", method = RequestMethod.GET)
	public @ResponseBody
	String nodesAll(Locale locale, Model model) {

		NodeProcess controll = new NodeProcess();
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

}