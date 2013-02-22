package kr.co.adflow.nms.web;

/**
 * HandlerFactory class
 * 
 * @author typark@adflow.co.kr
 * @version 1.0
 */
public class HandlerFactory {

	public static Handler<?, ?> getHandler() {
		return DefaultHandlerImpl.getInstance();
	}

}
