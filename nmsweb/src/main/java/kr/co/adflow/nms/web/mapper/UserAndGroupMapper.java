package kr.co.adflow.nms.web.mapper;

import java.util.Iterator;

import javax.swing.text.AbstractDocument.Content;

import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.vo.group.Groupinfo;
import kr.co.adflow.nms.web.vo.user.Contact;
import kr.co.adflow.nms.web.vo.user.User;
import kr.co.adflow.nms.web.vo.user.UserInit;
import kr.co.adflow.nms.web.vo.user.Users;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserAndGroupMapper {
	private static final Logger logger = LoggerFactory
			.getLogger(UserAndGroupMapper.class);

	/**
	 * singleton
	 * 
	 */
	private UserAndGroupMapper() {
	}

	public static UserAndGroupMapper mapper = new UserAndGroupMapper();

	public static UserAndGroupMapper getMapper() {
		return mapper;
	}

	public User userInfoMapping(String data) throws MapperException {

		User user = null;
		try {
		
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			if (jNode.path("user").isArray()) {
				logger.debug("mapper step...2");
				JsonNode temp = null;
				JsonNode temp2 = null;
				JsonNode temp3 = null;
				Iterator<JsonNode> it = jNode.path("user").iterator();

				while (it.hasNext()) {
				
					user = new User();
					temp = it.next();
					user.setFullName(temp.path("full-name").getTextValue());
					user.setPassword(temp.path("password").getTextValue());
					user.setReadOnly(temp.path("read-only").getBooleanValue());
					user.setTuiPin(temp.path("tuiPin").getTextValue());
					user.setUserComments(temp.path("user-comments")
							.getTextValue());
					user.setUserId(temp.path("user-id").getTextValue());
					logger.debug("mapper step 4....");
					if (temp.path("dutySchedule").isArray()) {
						logger.debug("step...duty..");
						Iterator<JsonNode> it2 = temp.path("dutySchedule")
								.iterator();
						while (it2.hasNext()) {
							System.out.println("mapper step...4");
							temp2 = it2.next();
							user.getDutySchedule().add(temp2.getTextValue());
						}
					}

					if (temp.path("contact").isArray()) {
						logger.debug("step...contact");
						Iterator<JsonNode> it3 = temp.path("contact")
								.iterator();
						System.out.println("mapper contact...5");
						while (it3.hasNext()) {
							Contact contact = new Contact();
							temp3 = it3.next();
							contact.setInfo(temp3.path("info").getTextValue());
							contact.setServiceProvider(temp3.path(
									"serviceProvider").getTextValue());
							contact.setType(temp3.path("type").getTextValue());
							user.getContact().add(contact);
						}
					}
					// users.getUser().add(user);
				}

			}
		} catch (Exception e) {
			throw new MapperException(e);
		}

		return user;

	}

	public UserInit initUser(String data) throws MapperException {
		UserInit init = new UserInit();

		try {
			logger.debug("mapper step...1");
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			if (jNode.path("user").isArray()) {
			logger.debug("if..");
			JsonNode temp = null;

			Iterator<JsonNode> it = jNode.path("user").iterator();

			while (it.hasNext()) {
				logger.debug("mapper step...3");
				temp = it.next();
				init.setFullName(temp.path("full-name").getTextValue());
				init.setPassword(temp.path("password").getTextValue());
				init.setUserComments(temp.path("user-comments").getTextValue());
				init.setUserId(temp.path("user-id").getTextValue());

			}
			
			logger.debug("intit:::"+init.getPassword());
			}
		} catch (Exception e) {
			throw new MapperException(e);
		}

		return init;

	}
	//<group><name>adflow222</name><comments>The adflow222</comments><user>chan222</user></group>
	public Groupinfo groupInfo(String data) throws MapperException {
		Groupinfo grou= new Groupinfo();

		try {
			logger.debug("mapper step...1");
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			if (jNode.path("group").isArray()) {
			logger.debug("if..");
			JsonNode temp = null;

			Iterator<JsonNode> it = jNode.path("group").iterator();

			while (it.hasNext()) {
				logger.debug("mapper step...3");
				temp = it.next();
				grou.setComments(temp.path("comments").getTextValue());
				grou.setName(temp.path("name").getTextValue());
				grou.setUser(temp.path("user").getTextValue());
				

			}
			
			logger.debug("grou:::"+grou.getComments());
			logger.debug("grou:::"+grou.getName());
			logger.debug("grou:::"+grou.getUser());
			
			}
		} catch (Exception e) {
			throw new MapperException(e);
		}

		return grou;

	}
	

}
