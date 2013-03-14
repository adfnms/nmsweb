package kr.co.adflow.nms.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream.PutField;
import java.util.HashMap;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
public class AdminTestControllerTest extends AbstractContextControllerTests {

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
			is = new FileInputStream(
					"src/test/java/properties/admintest.properties");
			properties.load(is);

			for (String key : properties.stringPropertyNames()) {
				String value = properties.getProperty(key);
				System.out.println("value:" + value);
				hashData.put(key, value);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// get /stats/alarms
	@Test
	public void adminUser() throws Exception {
		String data = (String) hashData.get("adminuser");
		mockMvc.perform(get("/adminuser")).andExpect(content().string(data));
	}

//	@Test
//		public void adminUserAdd() throws Exception {
//			String data=(String) hashData.get("adminuseradd");
//			///adminuser/{id}
//		
//		}
}
