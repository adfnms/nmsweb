package kr.co.adflow.nms.web.util;

import kr.co.adflow.nms.web.DefaultHandlerImpl;
import kr.co.adflow.nms.web.exception.UtilException;
import kr.co.adflow.nms.web.vo.group.Groupinfo;
import kr.co.adflow.nms.web.vo.user.UserInit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsersUtil {

	private static final String JSONDATA = "jsonData:::";
	private static final String MD5JSONDATA = "md5jsonData:::";
	private static final String PASSWORD = "passWord:::";
	private static final String MD5XMLDATA = "md5XMLData:::";

	private static final Logger logger = LoggerFactory
			.getLogger(UsersUtil.class);

	public static UsersUtil util = new UsersUtil();

	private UsersUtil() {

	}

	public static UsersUtil getInstance() {
		return util;
	}

	
	


	public String XmlParsingUser(UserInit userinit) throws UtilException {
		String data = null;
		try {
			String name = userinit.getFullName();
			String id = userinit.getUserId();
			String pass = userinit.getPassword();
			String comments = userinit.getUserComments();

			StringBuffer bf = new StringBuffer();
			bf.append("<user>");
			bf.append("<user-id>");
			bf.append(id);
			bf.append("</user-id>");
			bf.append("<full-name>");
			bf.append(name);
			bf.append("</full-name>");
			bf.append("<user-comments>");
			bf.append(comments);
			bf.append("</user-comments>");
			bf.append("<password>");
			bf.append(pass);
			bf.append("</password>");
			bf.append("</user>");

			data=bf.toString();
		} catch (Exception e) {
			throw new UtilException(e);
		}
		return data;
	}
	
	
	//<group><name>adflow222</name><comments>The adflow222</comments><user>chan222</user></group>
	
	public String XmlParsingGroup(Groupinfo groupinfo) throws UtilException {
		String data = null;
		try {
			String name = groupinfo.getName();
			String comments = groupinfo.getComments();
			String user = groupinfo.getUser();
		

			StringBuffer bf = new StringBuffer();
			bf.append("<group>");
			bf.append("<name>");
			bf.append(name);
			bf.append("</name>");
			bf.append("<comments>");
			bf.append(comments);
			bf.append("</comments>");
			bf.append("<user>");
			bf.append(user);
			bf.append("</user>");
			bf.append("</group>");

			data=bf.toString();
		} catch (Exception e) {
			throw new UtilException(e);
		}
		return data;
	}

	

	// String Pass md5
	public static String encryptString(String src) throws UtilException {
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
			throw new UtilException(e);
		}
		return upperCase;
	}

}
