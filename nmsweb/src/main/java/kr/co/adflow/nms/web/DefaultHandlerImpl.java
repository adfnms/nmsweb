package kr.co.adflow.nms.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import kr.co.adflow.nms.web.exception.HandleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DefaultHandler Implements
 * 
 * @author typark@adflow.co.kr
 * @version 1.0
 */
public class DefaultHandlerImpl implements Handler<String, HashMap> {

	private static final Logger logger = LoggerFactory
			.getLogger(DefaultHandlerImpl.class);

	/**
	 * singleton
	 * 
	 */
	public static DefaultHandlerImpl handler = new DefaultHandlerImpl();

	private DefaultHandlerImpl() {
	}

	public static Handler getInstance() {
		return handler;
	}

	/**
	 * 123456789011
	 */
	@Override
	public String handle(HashMap map) throws HandleException {
		HttpURLConnection conn = null;
		try {
			URL url = new URL((String) map.get("url"));
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod((String) map.get("method"));
			
			if (map.containsKey("accept")) {
				conn.setRequestProperty("Accept", (String) map.get("accept"));
			}
			
			//2013-02-22
			//kicho@adflow.co.kr 
			//"Content-Type" 추가
			if (map.containsKey("contentType")) {
				conn.setRequestProperty("Content-Type", (String) map.get("contentType"));
			}
			
			String userPassword = map.get("username") + ":"
					+ map.get("password");
			String encoding = new sun.misc.BASE64Encoder().encode(userPassword
					.getBytes());
			conn.setRequestProperty("Authorization", "Basic " + encoding);

			
			//2013-02-21
			//kicho@adflow.co.kr 
			//POST, PUT Data 추가
			if (map.containsKey("data")) {
				
				conn.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream (conn.getOutputStream ());
				wr.writeBytes ((String) map.get("data"));
			}

			logger.debug("conn :" + conn);

			if (conn.getResponseCode() != 200) {
				throw new HandleException("HTTPErrorCode :"
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			StringBuffer result = new StringBuffer();
			String buf;
			while ((buf = br.readLine()) != null) {
				result.append(buf);
			}

			// if (true) {
			// throw new Exception("TestException");
			// }

			logger.debug("receivedData :" + result.toString());
			return result.toString();
		} catch (Exception e) {
			throw new HandleException(e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}
}
