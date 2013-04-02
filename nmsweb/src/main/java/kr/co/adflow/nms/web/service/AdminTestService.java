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
	private @Value("#{config['XMLPATH']}")
	String xmlPath;

	
	public String adminPerId(String id) throws HandleException {
		logger.debug("AddDATA::" + id);
		Properties properties = new Properties();
		HashMap hashData = new HashMap();
		ArrayList arr = new ArrayList();
		InputStream is = null;
		String result=null;
		try {
			is = new FileInputStream(xmlPath+"magic-users.properties");
			properties.load(is);

			for (String key : properties.stringPropertyNames()) {
				String value = properties.getProperty(key);
				hashData.put(key, value);
				arr.add(key);
			}

			String hashValue = null;
			for (int i = 0; i < arr.size(); i++) {
				if (arr.get(i).equals(adminKey)) {
				String 	data = (String) hashData.get(adminKey);
					if (data.equals("")&&id!=data) {
						result=id;
						properties.setProperty(adminKey, id);
						properties.store(new FileOutputStream(xmlPath+"magic-users.properties"),
								null);
					} else if(id!=data&&!data.contains(id)) {
						logger.debug("id!=data");
						result=data+","+id;
						properties.setProperty(adminKey, data + "," + id);
						properties.store(new FileOutputStream(xmlPath+"magic-users.properties"),
								null);
					}else if(data.contains(id)){
						logger.debug("else if data...equals");
						throw new HandleException("Id is already...");
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
		logger.debug("setPropertiesData::"+result);
		result = "{\"result\":\"success\"}";
		return result;
	}

	public String dashboardPage(String id) throws HandleException {
		logger.debug("addDATA::" + id);
		Properties properties = new Properties();
		HashMap hashData = new HashMap();
		ArrayList arr = new ArrayList();
		InputStream is = null;
		String result=null;
		try {
			is = new FileInputStream(xmlPath+"magic-users.properties");
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
					if (data.equals("")&&id!=data) {
						result=id;
						properties.setProperty(dashKey, id);
						properties.store(new FileOutputStream(xmlPath+"magic-users.properties"),
								null);
					} else if(id!=data&&!data.contains(id)) {
						result=data+","+id;
						properties.setProperty(dashKey, data + "," + id);
						properties.store(new FileOutputStream(xmlPath+"magic-users.properties"),
								null);
					}else if(data.contains(id)){
						logger.debug("else if data...equals");
						throw new HandleException("Id is already...");
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
		logger.debug("setPropertiesData::"+result);
		result = "{\"result\":\"success\"}";
		return result;
	}

	public String adminDel(String id) throws HandleException {
		logger.debug("delDATA::" + id);
		Properties properties = new Properties();
		HashMap hashData = new HashMap();
		ArrayList arrKey = new ArrayList();
		InputStream is = null;
		String result=null;
		try {
			is = new FileInputStream(xmlPath+"magic-users.properties");
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
				if (arrSplitValue.get(i).equals(id)) {
					arrSplitValue.remove(i);
				}

			}
			if (arrSplitValue.size() == 0) {
				logger.debug("size is 0");
				String empty = "";
				properties.setProperty(adminKey, empty);
				properties.store(new FileOutputStream(xmlPath+"magic-users.properties"), null);
			} else {
				for (int j = 0; j < arrSplitValue.size(); j++) {
					sbuf.append(arrSplitValue.get(j) + ",");
				}
				String deleteData = sbuf.delete(sbuf.length() - 1,
						sbuf.length()).toString();
				logger.debug("setPropertyData::" + deleteData);
				properties.setProperty(adminKey, deleteData);
				properties.store(new FileOutputStream(xmlPath+"magic-users.properties"), null);
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

		result = "{\"result\":\"success\"}";
		return result;
	}

	public String dashBoardDel(String id) throws HandleException {
		logger.debug("delDATA::" + id);
		Properties properties = new Properties();
		HashMap hashData = new HashMap();
		ArrayList arrKey = new ArrayList();
		InputStream is = null;
		String result=null;
		try {
			is = new FileInputStream(xmlPath+"magic-users.properties");
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
				if (arrSplitValue.get(i).equals(id)) {
					arrSplitValue.remove(i);
				}

			}

			if (arrSplitValue.size() == 0) {
				logger.debug("size is 0");
				String empty = "";
				properties.setProperty(dashKey, empty);
				properties.store(new FileOutputStream(xmlPath+"magic-users.properties"), null);
			} else {
				for (int j = 0; j < arrSplitValue.size(); j++) {
				
					sbuf.append(arrSplitValue.get(j) + ",");
				}
				String deleteData = sbuf.delete(sbuf.length() - 1,
						sbuf.length()).toString();
				logger.debug("setPropertyData::" + deleteData);
				properties.setProperty(dashKey, deleteData);
				properties.store(new FileOutputStream(xmlPath+"magic-users.properties"), null);
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

		result = "{\"result\":\"success\"}";
		return result;
	}

	public String adminUser() throws HandleException {

		Properties properties = new Properties();
		HashMap hashData = new HashMap();
		ArrayList arrKey = new ArrayList();
		InputStream is = null;
		String result = null;

		try {
			is = new FileInputStream(xmlPath+"magic-users.properties");
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
		logger.debug("ServiceReturn::"+result);
		return result;
	}

	public String dashBoardUser() throws HandleException {

		Properties properties = new Properties();
		HashMap hashData = new HashMap();
		ArrayList arrKey = new ArrayList();
		InputStream is = null;
		String result = null;
		
		try {
			is = new FileInputStream(xmlPath+"magic-users.properties");
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
		logger.debug("ServiceReturn::"+result);
		return result;
	}

//	public String test() throws IOException {
//		logger.debug("intest");
//		
//		String testPass = (String) configProperties.get("LOGINPASS");
//		logger.debug("testPass::" + testPass);
//		
//		FileSystemResource resource = new FileSystemResource("/WEB-INF/content/somecontent.txt");
//		
//		configProperties.setProperty("LOGINPASS", "ddddd");
//		configProperties.store(new FileOutputStream("file:///WEB-INF/properties/config.properties"), null);
//		return "succed";
//	}

}
