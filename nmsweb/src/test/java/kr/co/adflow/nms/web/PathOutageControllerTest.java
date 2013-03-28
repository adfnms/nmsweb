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
public class PathOutageControllerTest extends AbstractContextControllerTests {

	private MockMvc mockMvc;
	
	HashMap hashData = new HashMap();
	
	String nodeId= null;
	String pathOutageData= null;

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
	   is = new FileInputStream("src/test/properties/pathOutage.properties");
	   properties.load(is);
	   
	   nodeId = properties.getProperty("nodeId");
	   pathOutageData = properties.getProperty("pathOutageData");
	   
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


		// //// put
		@Test
		public void pathOutagesPUT() throws Exception {

			mockMvc.perform(put("/pathOutages").content(pathOutageData.getBytes())).andExpect(status().isOk());

			// mockMvc.perform(request(HttpMethod.DELETE, "/nodes/{id}", "1000"))
			// .andExpect(status().is(500));
		}


	

	// get
	@Test
	public void pathOutages() throws Exception {
		mockMvc.perform(get("/pathOutages").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("pathOutage")))
				;
	}
	

	

	

		


	
//		//delete
		

		@Test
		public void pathOutagesDelete() throws Exception {
	
			mockMvc.perform(delete("/pathOutages/"+nodeId)).andExpect(status().isOk());

		}
		
		


	
}
