package kr.co.adflow.nms.web;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest extends AbstractContextControllerTests {
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
			is = new FileInputStream("src/test/java/properties/user.properties");
			properties.load(is);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// /users GET
	@Test
	public void usersGet() throws Exception {
		mockMvc.perform(get("/users")).andExpect(status().isOk())
				.andExpect(content().string(containsString("totalCount")));
	}

	// user Post
	@Test
	public void userPost() throws Exception {
		String data = (String) properties.get("userdata");
		mockMvc.perform(post("/users").content(data))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("user-id")));
	}

	// users/{username}

	// /users GET
	@Test
	public void usersGetName() throws Exception {
		String data = (String) properties.get("delUser");
		mockMvc.perform(get("/users/" + data)).andExpect(status().isOk())
				.andExpect(content().string(containsString("chanAmtest")));
	}

	// // users/{username} Delete
	//
	@Test
	public void usersDelete() throws Exception {
		String data = (String) properties.get("delUser");
		mockMvc.perform(delete("/users/" + data)).andExpect(status().isOk())
				.andExpect(content().string(containsString("success")));
		;
	}

	// /users/detail etc xml modify Post
	@Test
	public void userPostDetail() throws Exception {
		String data = (String) properties.get("userdetail");
		String id = (String) properties.get("userdetailId");
		mockMvc.perform(post("/users/detail/" + id).content(data))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("success")));
	}

	// user POST del "/users/detail/del/{id}"

	@Test
	public void userPostDetailDel() throws Exception {
		String data = (String) properties.get("userdetail");
		String id = (String) properties.get("userdetailId");
		mockMvc.perform(post("/users/detail/del/" + id).content(data))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("success")));
	}

}
