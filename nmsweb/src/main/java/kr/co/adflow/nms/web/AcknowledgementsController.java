package kr.co.adflow.nms.web;



import java.util.Locale;

import kr.co.adflow.nms.web.acknowledgements.AcknowledgementsProcess;
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
public class AcknowledgementsController {

	private static final Logger logger = LoggerFactory
			.getLogger(AcknowledgementsController.class);
	
	private AcknowledgementsProcess controll = AcknowledgementsProcess.getProcess();

	@RequestMapping(value = "/acks", method = RequestMethod.GET)
	public @ResponseBody
	String acks() {

		String result = null;

		try {
			result = (String) controll.acks();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "/acks/{id}", method = RequestMethod.GET)
	public @ResponseBody String acks(@PathVariable String id ){

		String result = null;
		
		try {
			result = (String) controll.acks(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/acks/count", method = RequestMethod.GET)
	public @ResponseBody String acksCount(){

		String result = null;

		try {
			result = (String) controll.acksCount();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}


}