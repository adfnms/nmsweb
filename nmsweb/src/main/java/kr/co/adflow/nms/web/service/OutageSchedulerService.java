package kr.co.adflow.nms.web.service;

import java.util.Timer;

import javax.annotation.PostConstruct;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import kr.co.adflow.nms.web.exception.UtilException;
import kr.co.adflow.nms.web.util.OutagesCheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OutageSchedulerService {
	private static final Logger logger = LoggerFactory
			.getLogger(OutageSchedulerService.class);
	
	private @Value("#{config['OUTAGESCHEDULERINTERVAL']}") int outageInterval;

	@Autowired
	private DashBoardService service;

	@PostConstruct
	public void outageCheck() {
		

		logger.debug("OutageSchedulerService start");
		try {
			Timer timer = new Timer();

			OutagesCheck outageCheck = new OutagesCheck(
					timer, service);

			int firstStart = 1000;
			int interval = 1000 * outageInterval;
			timer.schedule(outageCheck, firstStart, interval);

		} catch (Exception e) {
			logger.error(e.toString());
		}

	}

}
