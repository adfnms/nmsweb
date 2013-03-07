package kr.co.adflow.nms.web.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.push.Pusher;
import kr.co.adflow.nms.web.push.WebSocketHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * AcknowledgementsProcess
 * 
 * @author kicho@adflow.co.kr
 * @version 1.1
 */
@Service
public class PushService {

	private static final String ContentType = "contentType";
	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String Accept = "accept";
	private @Value("#{config['NMSURL']}")
	String ipAddr;
	private static final Logger logger = LoggerFactory
			.getLogger(PushService.class);
	@Autowired
	private Handler handler;

	public String pushPost(String msg) throws HandleException {

		String result = null;

		try {
			// push sample code
			Set<WebSocketHandler> sockets = Pusher.getInstance().getSockets();

			Iterator<WebSocketHandler> it = sockets.iterator();

			for (WebSocketHandler socket : sockets) {
				logger.debug("Trying to send to Member!");
				if (socket.isOpen()) {
					logger.debug("Sending!");
					try {
						socket.sendMessage(msg);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			// sample end

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result;
	}

}
