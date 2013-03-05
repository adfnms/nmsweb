package kr.co.adflow.nms.web.push;

import java.io.IOException;
import java.util.Set;

import org.eclipse.jetty.websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * WebSocketHandler
 * 
 * @author typark@adflow.co.kr
 * @version 1.0
 */
public class WebSocketHandler implements WebSocket.OnTextMessage {

	private static final Logger logger = LoggerFactory
			.getLogger(WebSocketHandler.class);
	private Set<WebSocketHandler> sockets;
	private Connection conn;

	public WebSocketHandler(Set<WebSocketHandler> sockets) {
		this.sockets = sockets;
	}

	@Override
	public void onClose(int closeCode, String message) {
		logger.debug("webSocket closeCode : " + closeCode);
		logger.debug("webSocket message : " + message);
		sockets.remove(this);
	}

	@Override
	public void onOpen(Connection conn) {
		logger.debug("webSocket open : " + conn);
		conn.setMaxIdleTime(0);
		logger.debug("webSocket maxIdle : " + conn.getMaxIdleTime());

		sockets.add(this);
		this.conn = conn;
		// try {
		// conn.sendMessage("Server received Web Socket upgrade and added it to Receiver List.");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	@Override
	public void onMessage(String data) {
		logger.debug("Received : " + data);
	}

	public void sendMessage(String data) throws IOException {
		conn.sendMessage(data);
		logger.debug("message send : " + data);
	}

	public boolean isOpen() {
		return conn.isOpen();
	}
}
