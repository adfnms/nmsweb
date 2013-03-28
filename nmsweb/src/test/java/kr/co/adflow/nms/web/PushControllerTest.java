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
public class PushControllerTest extends AbstractContextControllerTests {

	private MockMvc mockMvc;
	
	HashMap hashData = new HashMap();
	
	String pushData= null;
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
	   is = new FileInputStream("src/test/properties/push.properties");
	   properties.load(is);
	   
	   pushData = properties.getProperty("pushData");
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

	

		// //// post
	 	// junit은 websocket을 지원하지않아 테스트 진행이 안됨.
	 
		@Test
		public void notificationsDestincationPathsPost() throws Exception {

			mockMvc.perform(post("/push").content(pushData.getBytes())).andExpect(status().isOk());

			// mockMvc.perform(request(HttpMethod.DELETE, "/nodes/{id}", "1000"))
			// .andExpect(status().is(500));
		}	 
	
	


	
	

	
}
