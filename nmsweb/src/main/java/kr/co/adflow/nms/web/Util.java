package kr.co.adflow.nms.web;

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

	public String jsonConvertXml(String data) throws HandleException {
		String xml = null;
		logger.info(MD5JSONDATA + data);
		try {
			JSONObject o = new JSONObject(data);
			xml = org.json.XML.toString(o);
			System.out.println(xml);
		} catch (JSONException e) {
			throw new HandleException(e);
		}
		return xml;
	}

	public String passWordPar(String data) throws HandleException {
		String passWord = null;
		logger.info(JSONDATA + data);

		int pass = data.indexOf("password:");
		passWord = data.substring(data.indexOf("password:") + 9,
				data.length() - 2);
		String md5 = encryptString(passWord);
		String md5Json = data.replaceAll(passWord, md5);
		
		String finalJsonData = jsonConvertXml(md5Json);
		logger.info(MD5XMLDATA + finalJsonData);
		return finalJsonData;
	}

	public static String encryptString(String src) {
		java.security.MessageDigest md5 = null;
		try {
			md5 = java.security.MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			return "";
		}

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
		String upperCase = temp.toUpperCase();
		return upperCase;
	}

}
