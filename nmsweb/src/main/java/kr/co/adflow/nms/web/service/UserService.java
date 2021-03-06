package kr.co.adflow.nms.web.service;

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
import kr.co.adflow.nms.web.vo.user.Contact;
import kr.co.adflow.nms.web.vo.user.User;
import kr.co.adflow.nms.web.vo.user.Userinfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private @Value("#{config['NMSURL']}")
	String ipAddr;
	private @Value("#{config['XMLPATH']}")
	String xmlPath;
	private @Value("#{config['LOGINID']}")
	String loginId;
	private @Value("#{config['LOGINPASS']}")
	String loginPass;
	private static final String Accept = "accept";
	private static final String DATA = "data";
	private static final String CONTENTTYPE = "contentType";
	private static final Logger logger = LoggerFactory
			.getLogger(UserService.class);
	@Autowired
	private Handler handler;

	// users
	public String Users() throws HandleException {
		String result = null;
		try {
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/users");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");
			
			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		logger.debug("ServiceRetunData::" + result);
		return result;
	}

	// users
	public String UsersPost(String xmlData) throws HandleException {
		String result = null;
		try {

			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/users");
			hash.put(Accept, "application/json");
			hash.put(METHOD, "POST");
			hash.put(CONTENTTYPE, "application/xml");
			hash.put(DATA, xmlData);

			result = (String) handler.handle(hash);

		} catch (Exception e) {
			throw new HandleException(e);
		}
		logger.debug("ServiceRetunData::" + result);
		return result;
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
		String result = null;
		try {
			HashMap<String, String> hash = new HashMap<String, String>();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/users/" + username);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "GET");
			result = (String) handler.handle(hash);
		} catch (Exception e) {
			throw new HandleException(e);
		}
		logger.debug("ServiceRetunData::" + result);
		return result;
	}

	// users/{username} Delete
	public String UsersDelete(String username) throws HandleException {
		String result = null;
		try {
			
			HashMap hash = new HashMap();
			hash.put(USERNAME, loginId);
			hash.put(PASSWORD, loginPass);
			hash.put(URL, ipAddr + "/users/" + username);
			hash.put(Accept, "application/json");
			hash.put(METHOD, "DELETE");

			result = (String) handler.handle(hash);
			result = "{\"result\":\"success\"}";
		} catch (Exception e) {
			throw new HandleException(e);
		}
		logger.debug("ServiceRetunData::" + result);
		return result;
	}

	// users/detail
	public String userPostDetail(String id,
			kr.co.adflow.nms.web.vo.user.User user) throws HandleException {
		String result = null;
		kr.co.adflow.nms.web.vo.user.Users users = new kr.co.adflow.nms.web.vo.user.Users();
		try {
			logger.debug("serviceId::" + id);
			XmlUtil xUtil = new XmlUtil();
			String filePath = xmlPath + "users.xml";
			Class<Userinfo> classname = Userinfo.class;
			Userinfo info = new Userinfo();
			Object ob = xUtil.xmlRead(filePath, classname, info);
			info = (Userinfo) ob;
			int size = info.getUsers().getUser().size();
			for (int i = 0; i < size; i++) {
				int contentSize = info.getUsers().getUser().get(i).getContact()
						.size();
				for (int j = 0; j < contentSize; j++) {
					for (int k = 0; k < user.getContact().size(); k++) {
						if (user.getContact()
								.get(k)
								.getType()
								.equals(info.getUsers().getUser().get(i)
										.getContact().get(j).getType())
								&& id.equals(info.getUsers().getUser().get(i)
										.getUserId())) {
							throw new HandleException("Contact already Type...");
						}
					}

				}
			}

			for (int i = 0; i < size; i++) {
				if (id.equals(info.getUsers().getUser().get(i).getUserId())) {
					for (int j = 0; j < user.getContact().size(); j++) {
						info.getUsers().getUser().get(i).getContact()
								.add(user.getContact().get(j));
					}

				}

			}

			String filePath2 = xmlPath + "users.xml";
			result = xUtil.xmlWrite(filePath2, classname, info);
			logger.debug("xUtil.xmlWriteResult::" + result);
			result = "{\"result\":\"success\"}";
		} catch (Exception e) {

			throw new HandleException(e);
		}

		logger.debug("ServiceRetunData::" + result);

		return result;
	}

	// users/detail
	public String userPostDetailDel(String id,
			kr.co.adflow.nms.web.vo.user.User user) throws HandleException {
		String result = null;
		kr.co.adflow.nms.web.vo.user.Users users = new kr.co.adflow.nms.web.vo.user.Users();
		try {
			logger.debug("serviceId::" + id);
			XmlUtil xUtil = new XmlUtil();
			String filePath = xmlPath + "users.xml";
			Class<Userinfo> classname = Userinfo.class;
			Userinfo info = new Userinfo();
			Object ob = xUtil.xmlRead(filePath, classname, info);
			info = (Userinfo) ob;
			int size = info.getUsers().getUser().size();
			for (int i = 0; i < size; i++) {
				if (id.equals(info.getUsers().getUser().get(i).getUserId())) {
					for (int j = 0; j < info.getUsers().getUser().get(i)
							.getContact().size(); j++) {
						for (int k = 0; k < user.getContact().size(); k++) {
							if (user.getContact()
									.get(k)
									.getType()
									.equals(info.getUsers().getUser().get(i)
											.getContact().get(j).getType())) {
								info.getUsers().getUser().get(i).getContact()
										.remove(j);
							}
						}
					}
				}
			}

			String filePath2 = xmlPath + "users.xml";
			result = xUtil.xmlWrite(filePath2, classname, info);

		} catch (Exception e) {

			throw new HandleException(e);
		}
		logger.debug("ServiceRetunData::" + result);
		result = "{\"result\":\"success\"}";
		return result;
	}
}
