package kr.co.adflow.nms.web;



import java.util.Locale;

import kr.co.adflow.nms.web.alarms.AlarmsProcess;
import kr.co.adflow.nms.web.events.EventsProcess;
import kr.co.adflow.nms.web.scheduledOutages.scheduledOutagesProcess;

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
public class ScheduledOutagesController {

	private static final Logger logger = LoggerFactory
			.getLogger(ScheduledOutagesController.class);
	
	private scheduledOutagesProcess controll = scheduledOutagesProcess.getProcess();

	@RequestMapping(value = "/sched-outages", method = RequestMethod.GET)
	public @ResponseBody
	String events() {

		String result = null;

		try {
			result = (String) controll.schedOutages();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "/sched-outages/{outageName}", method = RequestMethod.GET)
	public @ResponseBody String events(@PathVariable String outageName ){

		String result = null;
		
		try {
			result = (String) controll.schedOutages(outageName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	



}