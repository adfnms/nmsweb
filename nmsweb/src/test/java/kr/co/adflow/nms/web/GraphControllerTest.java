package kr.co.adflow.nms.web;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class GraphControllerTest extends AbstractContextControllerTests {

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
					"src/test/java/properties/graph.properties");
			properties.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void graphGet() throws Exception {
		mockMvc.perform(get("/graph")).andExpect(status().isOk())
				.andExpect(content().string(containsString("resourceID0")));
	}

	// /graph/{nodeid}
	@Test
	public void graphGetResourceId() throws Exception {
		String id = (String) properties.get("graphNodeid");
		mockMvc.perform(get("/graph/" + id)).andExpect(status().isOk())
				.andExpect(content().string(containsString("resourceID0")));
	}

	// /graph/resource/{resourceId:.+}

	@Test
	public void graphGetGraphData() throws Exception {
		String id = (String) properties.get("graphResourceid");
		mockMvc.perform(get("/graph/resource/" + id))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("url1")));
	}

	@Test
	public void graphGetGraphDay() throws Exception {
		String id = (String) properties.get("graphResourceid");
		mockMvc.perform(
				get("/graph/resource/" + id + "/day"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("url1")));
	}

	@Test
	public void graphGetGraphWeek() throws Exception {
		String id = (String) properties.get("graphResourceid");
		mockMvc.perform(
				get("/graph/resource/" + id + "/week"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("url1")));
	}

	@Test
	public void graphGetGraphMonth() throws Exception {
		String id = (String) properties.get("graphResourceid");
		mockMvc.perform(
				get("/graph/resource/" + id + "/month"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("url1")));
	}

	@Test
	public void graphGetGraphYear() throws Exception {
		String id = (String) properties.get("graphResourceid");
		mockMvc.perform(
				get("/graph/resource/" + id + "/year"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("url1")));
	}
}
