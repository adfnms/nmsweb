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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest extends AbstractContextControllerTests {
	private MockMvc mockMvc;
	HashMap hashData = new HashMap();

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(this.wac).build();
	}

	@Before
	public void configload() {

		Properties properties = new Properties();

		InputStream is = null;
		try {
			is = new FileInputStream("src/test/java/properties/user.properties");
			properties.load(is);

			for (String key : properties.stringPropertyNames()) {
				String value = properties.getProperty(key);
				hashData.put(key, value);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// /users GET
	@Test
	public void usersGet() throws Exception {
		mockMvc.perform(get("/users")).andExpect(status().isOk());
	}

	// user Post
	@Test
	public void userPost() throws Exception {
		String data = (String) hashData.get("userdata");
		mockMvc.perform(post("/users").content(data))
				.andExpect(status().isOk());
	}

	// users/{username}
	
	// /users GET
	@Test
	public void usersGetName() throws Exception {
		String data=(String)hashData.get("delUser");
		mockMvc.perform(get("/users/"+data)).andExpect(status().isOk());
	}


	// // users/{username} Delete
	//
	@Test
	public void usersDelete() throws Exception {
		String data = (String) hashData.get("delUser");
		mockMvc.perform(delete("/users/" + data)).andExpect(status().isOk());
	}

	// /users/detail etc xml modify Post
	@Test
	public void userPostDetail() throws Exception {
		String data = (String) hashData.get("userdetail");
		String id = (String) hashData.get("userdetailId");
		mockMvc.perform(post("/users/detail/" + id).content(data)).andExpect(
				status().isOk());
	}

	// user POST del "/users/detail/del/{id}"

	@Test
	public void userPostDetailDel() throws Exception {
		String data = (String) hashData.get("userdetail");
		String id = (String) hashData.get("userdetailId");
		mockMvc.perform(post("/users/detail/del/" + id).content(data))
				.andExpect(status().isOk());
	}
	
	

}
