package com.bluecapsystem.nms.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Util {

	public static String getJsonStrToUrl(String urlStr) throws IOException {

		String dataUrl = urlStr;
		URL url;
		URLConnection connection;
		InputStream is;
		InputStreamReader isr;
		BufferedReader br;
		String strJson = new String();
		try {
			url = new URL(dataUrl);
			connection = url.openConnection();
			is = connection.getInputStream();
			isr = new InputStreamReader(is, "utf-8");
			br = new BufferedReader(isr);
			String buf = null;

			while (true) {
				buf = br.readLine();
				if (buf == null) {
					break;
				} else {
					strJson += buf + "\n";
				}
			}
		} catch (MalformedURLException mue) {

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return strJson;

	}

}
