package kr.co.adflow.nms.web.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.HandlerFactory;
import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.util.XmlUtil;
import kr.co.adflow.nms.web.vo.DestPath.DestinationPaths;
import kr.co.adflow.nms.web.vo.user.User;
import kr.co.adflow.nms.web.vo.user.Userinfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

public class UsersProcess {

	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String NMSUrl = "http://192.168.0.63:8980/opennms/rest";
	// private static final String NMSUrl =
	// "http://localhost:8980/opennms/rest";

	private static final String Accept = "accept";
	private static final String DATA = "data";
	private static final String CONTENTTYPE = "contentType";
	private static final Logger logger = LoggerFactory
			.getLogger(UsersProcess.class);

	public UsersProcess() {

	}

	public static UsersProcess process = new UsersProcess();

	public static UsersProcess getPrcess() {
		return process;
	}

	// users
	public String Users() throws HandleException {
		try {
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/users");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			String result = null;

			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

	}

	// users
	public String UsersPost(String xmlData) throws HandleException {
		try {
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/users");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "POST");
			hash.put(CONTENTTYPE, "application/xml");
			hash.put(DATA, xmlData);

			String result = null;

			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

	}

	/*
	 * // users public String UsersPostDeatil(Users users) throws
	 * HandleException { try {
	 * 
	 * String result = null;
	 * 
	 * 
	 * return result; } catch (Exception e) { throw new HandleException(e); }
	 * 
	 * }
	 */

	// users/{username}
	public String Users(String username) throws HandleException {
		try {
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/users/" + username);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");

			String result = null;

			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

	}

	// users/{username} Delete
	public String UsersDelete(String username) throws HandleException {
		try {
			Handler handler = HandlerFactory.getHandler();
			HashMap hash = new HashMap();
			hash.put(USERNAME, "admin");
			hash.put(PASSWORD, "admin");
			hash.put(URL, NMSUrl + "/users/" + username);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "DELETE");

			String result = null;

			result = (String) handler.handle(hash);
			return result;
		} catch (Exception e) {
			throw new HandleException(e);
		}

	}

	// users/detail
	public String userPostDetail(kr.co.adflow.nms.web.vo.user.User user)
			throws HandleException {
		String result = null;
		kr.co.adflow.nms.web.vo.user.Users users = new kr.co.adflow.nms.web.vo.user.Users();
		logger.debug("postDetail");

		try {
			XmlUtil xUtil = new XmlUtil();
			String filePath = "C:\\temp\\users.xml";
			Class<Userinfo> classname = Userinfo.class;
			Userinfo info = new Userinfo();
			Object ob = xUtil.xmlRead(filePath, classname, info);
			info = (Userinfo) ob;
			int size = info.getUsers().getUser().size();
			boolean idCheak = false;
			for (int i = 0; i < size; i++) {
				if (user.getUserId().equals(
						info.getUsers().getUser().get(i).getUserId())) {
					idCheak = true;
				}
			}
			if (idCheak) {
				info.getUsers().getUser().add(user);
				String filePath2 = "C:\\temp\\users.xml";
				result = xUtil.xmlWrite(filePath2, classname, info);
			} else {
				logger.error("User :: Id Not Found");
				throw new HandleException(
						"User :: Id Not Found");
			}

		} catch (Exception e) {

			throw new HandleException(e);
		}
		return result;
	}

}
