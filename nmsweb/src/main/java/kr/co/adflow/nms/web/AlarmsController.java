package kr.co.adflow.nms.web;



import java.util.Locale;

import kr.co.adflow.nms.web.alarms.AlarmsProcess;
import kr.co.adflow.nms.web.events.EventsProcess;
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
public class AlarmsController {

	private static final Logger logger = LoggerFactory
			.getLogger(AlarmsController.class);
	
	private AlarmsProcess controll = AlarmsProcess.getProcess();

	@RequestMapping(value = "/alarms", method = RequestMethod.GET)
	public @ResponseBody
	String alarms() {

		String result = null;

		try {
			result = (String) controll.alarms();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "/alarms/{id}", method = RequestMethod.GET)
	public @ResponseBody String alarms(@PathVariable String id ){

		String result = null;
		
		try {
			result = (String) controll.alarms(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/alarms/count", method = RequestMethod.GET)
	public @ResponseBody String alarmsCount(){

		String result = null;

		try {
			result = (String) controll.alarmsCount();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/alarms/?comparator=ge&severity=MINOR", method = RequestMethod.GET)
	public @ResponseBody String alarmsMINOR(){

		String result = null;

		try {
			result = (String) controll.alarmsMINOR();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	



}