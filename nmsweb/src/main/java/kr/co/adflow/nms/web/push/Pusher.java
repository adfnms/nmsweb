package kr.co.adflow.nms.web.push;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Pusher
 * 
 * @author typark@adflow.co.kr
 * @version 1.0
 */
public class Pusher extends WebSocketServlet {

	private static final Logger logger = LoggerFactory.getLogger(Pusher.class);
	private final Set<WebSocketHandler> sockets = new CopyOnWriteArraySet<WebSocketHandler>();

	private static Pusher pusher;

	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest request,
			String protocol) {
		logger.debug("doWebSocketConnect request : " + request);
		return new WebSocketHandler(sockets);
	}

	@Override
	public boolean checkOrigin(HttpServletRequest request, String origin) {
		return super.checkOrigin(request, origin);
	}

	@Override
	public void destroy() {
		super.destroy();
		logger.debug("Pusher destroyed");
	}

	@Override
	public void init() throws ServletException {
		super.init();
		pusher = this;
	}

	public Set<WebSocketHandler> getSockets() {
		return sockets;
	}

	public static Pusher getInstance() {
		return pusher;
	}
}
