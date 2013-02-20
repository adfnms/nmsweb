package kr.co.adflow.nms.web;



import java.util.Locale;

import kr.co.adflow.nms.web.outages.OutagesProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * /** Handles requests for the application home page.
 */
@Controller
public class OutagesController {

	private static final Logger logger = LoggerFactory
			.getLogger(OutagesController.class);
	
	private OutagesProcess controll = OutagesProcess.getProcess();

	@RequestMapping(value = "/outages", method = RequestMethod.GET)
	public @ResponseBody
	String outages() {

		String result = null;

		try {
			result = (String) controll.outages();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "/outages/{id}", method = RequestMethod.GET)
	public @ResponseBody String outages(@PathVariable String id ){

		String result = null;
		
		try {
			result = (String) controll.outages(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/outages/count", method = RequestMethod.GET)
	public @ResponseBody String outagesCount(){

		String result = null;

		try {
			result = (String) controll.outagesCount();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/outages/forNode/{nodeId}", method = RequestMethod.GET)
	public @ResponseBody String outagesForNode(@PathVariable String nodeId ){

		String result = null;
		
		try {
			result = (String) controll.outagesForNode(nodeId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}



}