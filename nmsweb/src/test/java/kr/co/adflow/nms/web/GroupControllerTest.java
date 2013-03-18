package kr.co.adflow.nms.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
public class GroupControllerTest extends AbstractContextControllerTests {

	private MockMvc mockMvc;
	Properties properties = new Properties();

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(this.wac).build();
	}

	@Before
	public void configload() {

		InputStream is = null;
		try {
			is = new FileInputStream(
					"src/test/java/properties/group.properties");
			properties.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// /groups
	@Test
	public void groupsGet() throws Exception {
		mockMvc.perform(get("/groups")).andExpect(status().isOk());
	}

	// groups POST
	@Test
	public void groupsPost() throws Exception {
		String data = (String) properties.get("postData");
		mockMvc.perform(
				post("/groups").content(data)
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_XML)).andExpect(
				status().isOk());
	}

	// /groups/{groupname} GET
	@Test
	public void groupNameGet() throws Exception {
		String groupName = (String) properties.get("groupname");
		mockMvc.perform(get("/groups/" + groupName)).andExpect(status().isOk());
	}

	// groups/{groupname}/users/{username} PUT
	@Test
	public void groupNameUserPut() throws Exception {
		String groupName = (String) properties.get("groupname");
		String userName = (String) properties.get("username");
		mockMvc.perform(put("/groups/" + groupName + "/users/" + userName))
				.andExpect(status().isOk());
	}

	// /groups/{groupname}/users/{username}" DEL
	@Test
	public void groupDelUser() throws Exception {
		String groupName = (String) properties.get("groupname");
		String userName = (String) properties.get("username");
		mockMvc.perform(delete("/groups/" + groupName + "/users/" + userName))
				.andExpect(status().isOk());
	}

	// groups/{groupname} DEL
	@Test
	public void groupDel() throws Exception {
		String groupName = (String) properties.get("groupname");
		mockMvc.perform(delete("/groups/" + groupName)).andExpect(
				status().isOk());
	}
}
