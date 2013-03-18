package kr.co.adflow.nms.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
public class AdminTestControllerTest extends AbstractContextControllerTests {

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
					"src/test/java/properties/admintest.properties");
			properties.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add
	@Test
	public void adminUserPut() throws Exception {
		String data = (String) properties.get("adminuseradd");
		mockMvc.perform(put("/adminuser/" + data)).andExpect(
				content().string("succed"));
	}

	// get
	@Test
	public void adminUser() throws Exception {
		mockMvc.perform(get("/adminuser"));
	}

	// Del
	@Test
	public void adminUserDelete() throws Exception {
		String data = (String) properties.get("adminuseradd");
		mockMvc.perform(delete("/adminuser/del/" + data)).andExpect(
				content().string("succed"));
	}

	// dashboarduser

	// add
	@Test
	public void dashUserPut() throws Exception {
		String id = (String) properties.get("dashuseradd");
		mockMvc.perform(put("/dashboarduser/" + id)).andExpect(
				content().string("succed"));
	}

	// get
	@Test
	public void dashUser() throws Exception {
		mockMvc.perform(get("/dashboarduser"));
	}

	// del
	// /dashboarduser/del/{id}
	@Test
	public void dashUserDelete() throws Exception {
		String id = (String) properties.get("dashuseradd");
		mockMvc.perform(delete("/dashboarduser/del/" + id)).andExpect(
				content().string("succed"));
	}

}
