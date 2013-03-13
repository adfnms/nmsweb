package kr.co.adflow.nms.web.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import kr.co.adflow.nms.web.exception.HandleException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AdminTestService {

	private static final Logger logger = LoggerFactory
			.getLogger(AdminTestService.class);

	// ADMINPAGE=role.admin.users
	// DASHPAGE=role.dashboard.users
	private @Value("#{config['ADMINPAGE']}")
	String adminKey;
	private @Value("#{config['DASHPAGE']}")
	String dashKey;
	private @Value("#{config['PERMISSIONURL']}")
	String permissionUrl;

	public String adminPerId(String id) throws HandleException {
		logger.debug("DATA::" + id);
		Properties properties = new Properties();
		HashMap hashData = new HashMap();
		ArrayList arr = new ArrayList();
		InputStream is = null;
		try {
			is = new FileInputStream(permissionUrl);
			properties.load(is);

			for (String key : properties.stringPropertyNames()) {
				String value = properties.getProperty(key);
				hashData.put(key, value);
				arr.add(key);
			}

			String hashValue = null;
			for (int i = 0; i < arr.size(); i++) {
				if (arr.get(i).equals(adminKey)) {
					String data = (String) hashData.get(adminKey);
					if (data.equals("")) {
						properties.setProperty(adminKey, id);
						properties.store(new FileOutputStream(permissionUrl),
								null);
					} else {
						properties.setProperty(adminKey, data + "," + id);
						properties.store(new FileOutputStream(permissionUrl),
								null);
					}
				}
			}
		} catch (Exception e) {
			throw new HandleException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					throw new HandleException(e);
				}
			}

		}

		return "succed";
	}

	public String dashboardPage(String id) throws HandleException {
		logger.debug("DATA::" + id);
		Properties properties = new Properties();
		HashMap hashData = new HashMap();
		ArrayList arr = new ArrayList();
		InputStream is = null;
		try {
			is = new FileInputStream(permissionUrl);
			properties.load(is);

			for (String key : properties.stringPropertyNames()) {
				String value = properties.getProperty(key);
				hashData.put(key, value);
				arr.add(key);
			}

			String hashValue = null;
			for (int i = 0; i < arr.size(); i++) {
				if (arr.get(i).equals(dashKey)) {
					String data = (String) hashData.get(dashKey);
					if (data.equals("")) {
						properties.setProperty(dashKey, id);
						properties.store(new FileOutputStream(permissionUrl),
								null);
					} else {
						properties.setProperty(dashKey, data + "," + id);
						properties.store(new FileOutputStream(permissionUrl),
								null);
					}
				}
			}
		} catch (Exception e) {
			throw new HandleException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					throw new HandleException(e);
				}
			}

		}

		return "succed";
	}

	public String adminDel(String id) throws HandleException {
		logger.debug("DATA::" + id);
		Properties properties = new Properties();
		HashMap hashData = new HashMap();
		ArrayList arrKey = new ArrayList();
		InputStream is = null;
		try {
			is = new FileInputStream(permissionUrl);
			properties.load(is);

			for (String key : properties.stringPropertyNames()) {
				String value = properties.getProperty(key);
				hashData.put(key, value);
				arrKey.add(key);
			}

			String[] values = null;
			ArrayList arrSplitValue = new ArrayList();
			StringBuffer sbuf = new StringBuffer();
			for (int i = 0; i < arrKey.size(); i++) {
				if (arrKey.get(i).equals(adminKey)) {
					String data = (String) hashData.get(adminKey);
					values = data.split(",");
				}
			}

			for (int i = 0; i < values.length; i++) {
				arrSplitValue.add(values[i]);

			}

			for (int i = 0; i < arrSplitValue.size(); i++) {
				logger.debug("arrSplitValueBefore:::" + arrSplitValue.get(i));
				if (arrSplitValue.get(i).equals(id)) {
					System.out.println("if..");
					arrSplitValue.remove(i);
				}

			}
			if (arrSplitValue.size() == 0) {
				logger.debug("size is 0");
				String empty = "";
				properties.setProperty(adminKey, empty);
				properties.store(new FileOutputStream(permissionUrl), null);
			} else {
				for (int j = 0; j < arrSplitValue.size(); j++) {
					logger.debug("arrSplitValueAfter::" + arrSplitValue.get(j));
					sbuf.append(arrSplitValue.get(j) + ",");
				}
				String deleteData = sbuf.delete(sbuf.length() - 1,
						sbuf.length()).toString();
				logger.debug("deleteData::" + deleteData);
				properties.setProperty(adminKey, deleteData);
				properties.store(new FileOutputStream(permissionUrl), null);
			}

		} catch (Exception e) {
			throw new HandleException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					throw new HandleException(e);
				}
			}

		}

		return "succed";
	}

	public String dashBoardDel(String id) throws HandleException {
		logger.debug("DATA::" + id);
		Properties properties = new Properties();
		HashMap hashData = new HashMap();
		ArrayList arrKey = new ArrayList();
		InputStream is = null;
		try {
			is = new FileInputStream(permissionUrl);
			properties.load(is);

			for (String key : properties.stringPropertyNames()) {
				String value = properties.getProperty(key);
				hashData.put(key, value);
				arrKey.add(key);
			}

			String[] values = null;
			ArrayList arrSplitValue = new ArrayList();
			StringBuffer sbuf = new StringBuffer();
			for (int i = 0; i < arrKey.size(); i++) {
				if (arrKey.get(i).equals(dashKey)) {
					String data = (String) hashData.get(dashKey);
					values = data.split(",");
				}
			}

			for (int i = 0; i < values.length; i++) {
				arrSplitValue.add(values[i]);

			}

			for (int i = 0; i < arrSplitValue.size(); i++) {
				logger.debug("arrSplitValueBefore:::" + arrSplitValue.get(i));
				if (arrSplitValue.get(i).equals(id)) {
					System.out.println("if..");
					arrSplitValue.remove(i);
				}

			}

			if (arrSplitValue.size() == 0) {
				logger.debug("size is 0");
				String empty = "";
				properties.setProperty(dashKey, empty);
				properties.store(new FileOutputStream(permissionUrl), null);
			} else {
				for (int j = 0; j < arrSplitValue.size(); j++) {
					logger.debug("arrSplitValueAfter::" + arrSplitValue.get(j));
					sbuf.append(arrSplitValue.get(j) + ",");
				}
				String deleteData = sbuf.delete(sbuf.length() - 1,
						sbuf.length()).toString();
				logger.debug("deleteData::" + deleteData);
				properties.setProperty(dashKey, deleteData);
				properties.store(new FileOutputStream(permissionUrl), null);
			}

		} catch (Exception e) {
			throw new HandleException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					throw new HandleException(e);
				}
			}

		}

		return "succed";
	}

	public String adminUser() throws HandleException {

		Properties properties = new Properties();
		HashMap hashData = new HashMap();
		ArrayList arrKey = new ArrayList();
		InputStream is = null;
		String result = null;
		try {
			is = new FileInputStream(permissionUrl);
			properties.load(is);

			for (String key : properties.stringPropertyNames()) {
				String value = properties.getProperty(key);
				hashData.put(key, value);
				arrKey.add(key);
			}

			String[] values = null;
			ArrayList arrSplitValue = new ArrayList();
			StringBuffer sbuf = new StringBuffer();
			for (int i = 0; i < arrKey.size(); i++) {
				if (arrKey.get(i).equals(adminKey)) {
					String data = (String) hashData.get(adminKey);
					values = data.split(",");
				}
			}

			for (int i = 0; i < values.length; i++) {
				arrSplitValue.add(values[i]);

			}
			sbuf.append("{");
			for (int i = 0; i < arrSplitValue.size(); i++) {
				logger.debug("arrSplitValue:::" + arrSplitValue.get(i));
				sbuf.append("\"adminUser" + i + "\":\"");
				sbuf.append(arrSplitValue.get(i) + "\"");
				sbuf.append(",");
			}
			sbuf.delete(sbuf.length() - 1, sbuf.length());
			sbuf.append("}");
			result = sbuf.toString();
		} catch (Exception e) {
			throw new HandleException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					throw new HandleException(e);
				}
			}

		}

		return result;
	}

	public String dashBoardUser() throws HandleException {

		Properties properties = new Properties();
		HashMap hashData = new HashMap();
		ArrayList arrKey = new ArrayList();
		InputStream is = null;
		String result = null;
		try {
			is = new FileInputStream(permissionUrl);
			properties.load(is);

			for (String key : properties.stringPropertyNames()) {
				String value = properties.getProperty(key);
				hashData.put(key, value);
				arrKey.add(key);
			}

			String[] values = null;
			ArrayList arrSplitValue = new ArrayList();
			StringBuffer sbuf = new StringBuffer();
			for (int i = 0; i < arrKey.size(); i++) {
				if (arrKey.get(i).equals(dashKey)) {
					String data = (String) hashData.get(dashKey);
					values = data.split(",");
				}
			}

			for (int i = 0; i < values.length; i++) {
				arrSplitValue.add(values[i]);

			}
			sbuf.append("{");
			for (int i = 0; i < arrSplitValue.size(); i++) {
				logger.debug("arrSplitValue:::" + arrSplitValue.get(i));
				sbuf.append("\"dashUser" + i + "\":\"");
				sbuf.append(arrSplitValue.get(i) + "\"");
				sbuf.append(",");
			}
			sbuf.delete(sbuf.length() - 1, sbuf.length());
			sbuf.append("}");
			result = sbuf.toString();
		} catch (Exception e) {
			throw new HandleException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					throw new HandleException(e);
				}
			}

		}

		return result;
	}

}
