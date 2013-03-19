package kr.co.adflow.nms.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
public class AlarmStatisticsControllerTest extends
		AbstractContextControllerTests {
	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(this.wac).build();

	}

	// get /stats/alarms
	@Test
	public void getStatsAr() throws Exception {
		mockMvc.perform(get("/stats/alarms"))
				.andExpect(status().isOk())
				.andExpect(
						content().string(containsString("unacknowledgedCount")));
	}

	// get /stats/alarms/by-severity
	@Test
	public void getStatsArBy() throws Exception {
		mockMvc.perform(get("/stats/alarms/by-severity"))
				.andExpect(status().isOk())
				.andExpect(
						content().string(containsString("unacknowledgedCount")));
	}

}
