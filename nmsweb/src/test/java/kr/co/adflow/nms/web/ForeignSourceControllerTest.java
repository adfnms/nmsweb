package kr.co.adflow.nms.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
public class ForeignSourceControllerTest extends AbstractContextControllerTests {

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
					"src/test/java/properties/foreignsource.properties");
			properties.load(is);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// foreignSources
	// /foreignSources GET
	@Test
	public void foreignSources() throws Exception{
		mockMvc.perform(get("/requisitions"));
	}
	
	@Test
	public void foreignSourcesDefault() throws Exception{
		mockMvc.perform(get("/requisitions/defalut"));
	}
	
	

	@Test
	public void foreignSourcesDeployed() throws Exception{
		mockMvc.perform(get("/requisitions/deployed"));
	}


}

















