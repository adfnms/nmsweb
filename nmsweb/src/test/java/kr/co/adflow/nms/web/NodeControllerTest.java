package kr.co.adflow.nms.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
public class NodeControllerTest extends AbstractContextControllerTests {

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(this.wac).build();
	}
	

	// get
	@Test
	public void getNodes() throws Exception {
		mockMvc.perform(get("/nodes").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType("application/json;charset=UTF-8"));
	}

	// post
	@Test
	public void nodeIpPost() throws Exception {

		mockMvc.perform(post("/nodes/scan/192.168.0.1").content("test".getBytes())).andExpect(
				status().isOk());

		// mockMvc.perform(
		// request(HttpMethod.POST, "/nodes/scan/{ip}", "192.168.0.1")
		// .content("testData".getBytes())).andExpect(
		// status().isOk());
	}

	// put

	// delete
	@Test
	public void nodesDelete() throws Exception {

		mockMvc.perform(delete("/nodes/1000")).andExpect(status().is(500));

		// mockMvc.perform(request(HttpMethod.DELETE, "/nodes/{id}", "1000"))
		// .andExpect(status().is(500));
	}
}
