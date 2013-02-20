package kr.co.adflow.nms.web;



import java.util.Locale;

import kr.co.adflow.nms.web.alarms.AlarmsProcess;
import kr.co.adflow.nms.web.events.EventsProcess;
import kr.co.adflow.nms.web.notifications.NotificationsProcess;

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
public class NotificationsController {

	private static final Logger logger = LoggerFactory
			.getLogger(NotificationsController.class);
	
	private NotificationsProcess controll = NotificationsProcess.getProcess();

	@RequestMapping(value = "/notifications", method = RequestMethod.GET)
	public @ResponseBody
	String notifications() {

		String result = null;

		try {
			result = (String) controll.notifications();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "/notifications/{id}", method = RequestMethod.GET)
	public @ResponseBody String notifications(@PathVariable String id ){

		String result = null;
		
		try {
			result = (String) controll.notifications(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/notifications/count", method = RequestMethod.GET)
	public @ResponseBody String notificationsCount(){

		String result = null;

		try {
			result = (String) controll.notificationsCount();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}


}