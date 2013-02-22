package kr.co.adflow.nms.web;

/**
 * Handler Interface
 * 
 * @author typark@adflow.co.kr
 * @version 1.0
 */
public interface Handler<A, B> {

	A handle(B map) throws Exception;
}
