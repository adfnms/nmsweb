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
public class EventsController {

	private static final Logger logger = LoggerFactory
			.getLogger(EventsController.class);
	
	private EventsProcess controll = EventsProcess.getProcess();

	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public @ResponseBody
	String events() {

		String result = null;

		try {
			result = (String) controll.events();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
	public @ResponseBody String events(@PathVariable String id ){

		String result = null;
		
		try {
			result = (String) controll.events(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/events/count", method = RequestMethod.GET)
	public @ResponseBody String eventsCount(){

		String result = null;

		try {
			result = (String) controll.eventsCount();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}


}