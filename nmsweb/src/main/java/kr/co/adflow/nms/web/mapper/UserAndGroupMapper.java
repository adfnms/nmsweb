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
import org.springframework.stereotype.Service;
@Service
public class UserAndGroupMapper {
	private static final Logger logger = LoggerFactory
			.getLogger(UserAndGroupMapper.class);



	public User userInfoMapping(String data) throws MapperException {

		User user = null;
		try {
		
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			if (jNode.path("user").isArray()) {
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
					if (temp.path("dutySchedule").isArray()) {
						Iterator<JsonNode> it2 = temp.path("dutySchedule")
								.iterator();
						while (it2.hasNext()) {
							temp2 = it2.next();
							user.getDutySchedule().add(temp2.getTextValue());
						}
					}

					if (temp.path("contact").isArray()) {
						Iterator<JsonNode> it3 = temp.path("contact")
								.iterator();
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

			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			if (jNode.path("user").isArray()) {
			JsonNode temp = null;

			Iterator<JsonNode> it = jNode.path("user").iterator();

			while (it.hasNext()) {
				temp = it.next();
				init.setFullName(temp.path("full-name").getTextValue());
				init.setPassword(temp.path("password").getTextValue());
				init.setUserComments(temp.path("user-comments").getTextValue());
				init.setUserId(temp.path("user-id").getTextValue());

			}
			
			logger.debug("init.getPassword():::"+init.getPassword());
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

			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			if (jNode.path("group").isArray()) {

			JsonNode temp = null;

			Iterator<JsonNode> it = jNode.path("group").iterator();

			while (it.hasNext()) {
	
				temp = it.next();
				grou.setComments(temp.path("comments").getTextValue());
				grou.setName(temp.path("name").getTextValue());
				grou.setUser(temp.path("user").getTextValue());
				

			}
			
			logger.debug("grou.getComments():::"+grou.getComments());
			logger.debug("grou.getName():::"+grou.getName());
			logger.debug("grou.getUser():::"+grou.getUser());
			
			}
		} catch (Exception e) {
			throw new MapperException(e);
		}

		return grou;

	}
	

}
