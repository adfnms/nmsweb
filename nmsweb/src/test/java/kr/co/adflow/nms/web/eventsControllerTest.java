package kr.co.adflow.nms.web;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jndi.JndiTemplate;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringJUnit4ClassRunner.class)
public class eventsControllerTest extends AbstractContextControllerTests {

	private MockMvc mockMvc;
	
	HashMap hashData = new HashMap();
	
	String id= null;
	String postgresqlURL= null;
	String postgresqlUser= null;
	String postgresqlPass= null;
	

	Properties properties = new Properties();
	
	@Before
	public void setup() {
		mockMvc = webAppContextSetup(this.wac).build();
	}
	
	 @Before
	 public void configload() {
//	  Properties properties = new Properties();
	  
	  InputStream is = null;
	  try {
	   is = new FileInputStream("src/test/properties/event.properties");
	   properties.load(is);
	   
	   id = properties.getProperty("id");
	   postgresqlPass = properties.getProperty("postgresqlPass");
	   postgresqlPass = properties.getProperty("postgresqlPass");
	   postgresqlURL = properties.getProperty("postgresqlURL");
	   
	   
	  } catch (Exception e) {

	  }
	 }

	 @Before
	 public void setUpClass() {

		 
		 SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
			try {
				builder.activate();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}

			JndiTemplate jt = new JndiTemplate();

			SimpleDriverDataSource ds = new SimpleDriverDataSource();
			ds.setDriverClass(org.postgresql.Driver.class);
//			ds.setUrl("jdbc:postgresql://"+postgresqlURL+"/opennms");
//			ds.setUsername(postgresqlUser);
//			ds.setPassword(postgresqlPass);
			ds.setUrl("jdbc:postgresql://127.0.0.1:5432/opennms");
			ds.setUsername("opennms");
			ds.setPassword("admin");
			try {
				jt.bind("java:comp/env/jdbc/postgres", ds);
			} catch (NamingException e) {
				e.printStackTrace();
			}
	        
	    }

	

	 
	
	

	// get
	@Test
	public void events() throws Exception {
		mockMvc.perform(get("/events").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(content().string(containsString("@totalCount")))
				;
	}
	
	@Test
	public void eventsId() throws Exception {
		mockMvc.perform(get("/events/"+id).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(content().string(containsString("@id")))
				;
	}
	
	@Test
	public void eventsCount() throws Exception {
		mockMvc.perform(get("/events/count").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
//				.andExpect(content().string(containsString("@totalCount")))
				;
	}
	

	

	
}
