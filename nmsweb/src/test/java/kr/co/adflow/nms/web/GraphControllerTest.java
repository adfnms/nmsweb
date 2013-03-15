package kr.co.adflow.nms.web;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
public class GraphControllerTest extends AbstractContextControllerTests {

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
					"src/test/java/properties/graph.properties");
			properties.load(is);

			for (String key : properties.stringPropertyNames()) {
				String value = properties.getProperty(key);
				hashData.put(key, value);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void graphGet() throws Exception{
		mockMvc.perform(get("/graph").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	///graph/{nodeid}
	@Test
	public void graphGetResourceId() throws Exception{
		String id=(String)hashData.get("graphNodeid");
		mockMvc.perform(get("/graph/"+id).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	///graph/resource/{resourceId:.+}
	
	@Test
	public void graphGetGraphData() throws Exception{
		String id=(String)hashData.get("graphResourceid");
		mockMvc.perform(get("/graph/resource/"+id).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void graphGetGraphDay() throws Exception{
		String id=(String)hashData.get("graphResourceid");
		mockMvc.perform(get("/graph/resource/"+id+"/day").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	@Test
	public void graphGetGraphWeek() throws Exception{
		String id=(String)hashData.get("graphResourceid");
		mockMvc.perform(get("/graph/resource/"+id+"/week").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	@Test
	public void graphGetGraphMonth() throws Exception{
		String id=(String)hashData.get("graphResourceid");
		mockMvc.perform(get("/graph/resource/"+id+"/month").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	@Test
	public void graphGetGraphYear() throws Exception{
		String id=(String)hashData.get("graphResourceid");
		mockMvc.perform(get("/graph/resource/"+id+"/year").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
}






