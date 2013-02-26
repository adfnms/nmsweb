package kr.co.adflow.nms.web.util;

import kr.co.adflow.nms.web.DefaultHandlerImpl;
import kr.co.adflow.nms.web.exception.HandleException;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {

	private static final String JSONDATA = "jsonData:::";
	private static final String MD5JSONDATA = "md5jsonData:::";
	private static final String PASSWORD = "passWord:::";
	private static final String MD5XMLDATA = "md5XMLData:::";

	private static final Logger logger = LoggerFactory
			.getLogger(DefaultHandlerImpl.class);

	public static Util util = new Util();

	private Util() {

	}

	public static Util getInstance() {
		return util;
	}

	// json convert Xml
	public String jsonConvertXml(String data) throws HandleException {
		try {
			String xml = null;
			logger.info(MD5JSONDATA + data);

			JSONObject o = new JSONObject(data);
			xml = org.json.XML.toString(o);
			System.out.println(xml);
			return xml;
		} catch (JSONException e) {
			throw new HandleException(e);
		}

	}

	// md5 Json Data
	public String passWordPar(String data) throws HandleException {
		String finalJsonData = null;
		try {

			String first = null;
			String seconds = null;
			String passWord = null;
			logger.info(JSONDATA + data);
			first = data.substring(data.indexOf("{"),
					data.indexOf("password:") - 1);
			seconds = data.substring(data.indexOf("password:") - 1,
					data.length());
			passWord = data.substring(data.indexOf("password:") + 9,
					data.length() - 2);
			logger.debug("first::" + first);
			logger.debug("seconds::" + seconds);
			logger.debug("passWord::" + passWord);
			String md5 = encryptString(passWord);
			String md5Pass = seconds.replaceAll(passWord, md5);

			StringBuffer bf = new StringBuffer();
			bf.append(first);
			bf.append(md5Pass);
			logger.debug("md5JsonData:::" + bf.toString());
			finalJsonData = jsonConvertXml(bf.toString());
			logger.info(MD5XMLDATA + finalJsonData);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return finalJsonData;
	}

	// String Pass md5
	public static String encryptString(String src) throws HandleException {
		String upperCase = null;
		try {
			java.security.MessageDigest md5 = null;

			md5 = java.security.MessageDigest.getInstance("MD5");

			String eip;
			byte[] bip;
			String temp = "";
			String tst = src;

			bip = md5.digest(tst.getBytes());
			for (int i = 0; i < bip.length; i++) {
				eip = "" + Integer.toHexString((int) bip[i] & 0x000000ff);
				if (eip.length() < 2)
					eip = "0" + eip;
				temp = temp + eip;
			}
			upperCase = temp.toUpperCase();

		} catch (Exception e) {
			throw new HandleException(e);
		}
		return upperCase;
	}

}
