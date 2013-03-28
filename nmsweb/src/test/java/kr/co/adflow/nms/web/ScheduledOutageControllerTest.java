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
public class ScheduledOutageControllerTest extends AbstractContextControllerTests {

	private MockMvc mockMvc;
	
	HashMap hashData = new HashMap();
	
	String id= null;
	String pathName= null;
	String notificationName= null;
	String userName= null;
	String pagetime= null;
	String destPathData= null;
	String eventNotificationsData= null;


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
	   is = new FileInputStream("src/test/properties/schedOutage.properties");
	   properties.load(is);
	   
	   id = properties.getProperty("id");
	   pathName = properties.getProperty("pathName");
	   notificationName = properties.getProperty("notificationName");
	   userName = properties.getProperty("userName");
	   pagetime = properties.getProperty("pagetime");
	   destPathData = properties.getProperty("destPathData");
	   eventNotificationsData = properties.getProperty("eventNotificationsData");

	   
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
		@Test
		public void notificationsDestincationPathsPost() throws Exception {

			mockMvc.perform(post("/notifications/destinationPaths").content(destPathData.getBytes())).andExpect(status().isOk());

			// mockMvc.perform(request(HttpMethod.DELETE, "/nodes/{id}", "1000"))
			// .andExpect(status().is(500));
		}
		
		@Test
		public void notificationsEventNotificationsPost() throws Exception {

			mockMvc.perform(post("/notifications/eventNotifications").content(eventNotificationsData.getBytes())).andExpect(status().isOk());

			// mockMvc.perform(request(HttpMethod.DELETE, "/nodes/{id}", "1000"))
			// .andExpect(status().is(500));
		}	 
	
	

	// get
	@Test
	public void notifications() throws Exception {
		mockMvc.perform(get("/notifications").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("totalCount")))
				;
	}
	
	@Test
	public void notificationsId() throws Exception {
		mockMvc.perform(get("/notifications/"+id).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("severity")))
				;
	}
	
	@Test
	public void notificationsCount() throws Exception {
		mockMvc.perform(get("/notifications/count").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				;
	}
	
	@Test
	public void notificationsDestincationPathsPathName() throws Exception {
		mockMvc.perform(get("/notifications/destinationPaths/"+pathName).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("name")))
				;
	}
	
	@Test
	public void notificationsDestincationPaths() throws Exception {
		mockMvc.perform(get("/notifications/destinationPaths").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("name")))
				;
	}
	
	@Test
	public void notificationsCommands() throws Exception {
		mockMvc.perform(get("/notifications/commands").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("name")))
				;
	}
	
	@Test
	public void notificationsEvents() throws Exception {
		mockMvc.perform(get("/notifications/events").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("event")))
				;
	}
	
	@Test
	public void notificationsEventNotifications() throws Exception {
		mockMvc.perform(get("/notifications/eventNotifications").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("varbind")))
				;
	}
	
	@Test
	public void notificationsEventNotificationsName() throws Exception {
		mockMvc.perform(get("/notifications/eventNotifications/"+notificationName).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("varbind")))
				;
	}
	
	@Test
	public void notificationsSearchUser() throws Exception {
		mockMvc.perform(get("/notifications/searchUser/"+userName+"?pagetime="+pagetime+"&limit=1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("notifications")))
				;
	}
	
	@Test
	public void notificationsSearchUserCount() throws Exception {
		mockMvc.perform(get("/notifications/searchUser/"+userName+"/count").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				;
	}
	
	@Test
	public void notificationsAllOutstand() throws Exception {
		mockMvc.perform(get("/notifications/allOutstand?pagetime="+pagetime+"&limit=1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("notifications")))
				;
	}
	
	@Test
	public void notificationsAllOutstandCount() throws Exception {
		mockMvc.perform(get("/notifications/allOutstand/count").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				;
	}
	
	@Test
	public void notificationsAllAck() throws Exception {
		mockMvc.perform(get("/notifications/allAck?pagetime="+pagetime+"&limit=1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("notifications")))
				;
	}
	
	@Test
	public void notificationsAllAckCount() throws Exception {
		mockMvc.perform(get("/notifications/allAck/count").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				;
	}
	

	
	// //// put
		@Test
		public void notificationsDestincationPathsPut() throws Exception {

			mockMvc.perform(put("/notifications/destinationPaths").content(destPathData.getBytes())).andExpect(status().isOk());

			// mockMvc.perform(request(HttpMethod.DELETE, "/nodes/{id}", "1000"))
			// .andExpect(status().is(500));
		}
		
		@Test
		public void notificationsEventNotificationsPut() throws Exception {

			mockMvc.perform(put("/notifications/eventNotifications").content(eventNotificationsData.getBytes())).andExpect(status().isOk());

			// mockMvc.perform(request(HttpMethod.DELETE, "/nodes/{id}", "1000"))
			// .andExpect(status().is(500));
		}
		
		@Test
		public void notificationsEventNotificationsStatusPut() throws Exception {

			mockMvc.perform(put("/notifications/eventNotifications/"+notificationName+"?status=on").content(eventNotificationsData.getBytes())).andExpect(status().isOk());

			// mockMvc.perform(request(HttpMethod.DELETE, "/nodes/{id}", "1000"))
			// .andExpect(status().is(500));
		}
		
		@Test
		public void notificationsConfigStatusPut() throws Exception {

			mockMvc.perform(put("/notificationConfig?status=on").content(eventNotificationsData.getBytes())).andExpect(status().isOk());

			// mockMvc.perform(request(HttpMethod.DELETE, "/nodes/{id}", "1000"))
			// .andExpect(status().is(500));
		}

	
//		//delete
		

		@Test
		public void notificationsEventNotificationsDelete() throws Exception {
	
			mockMvc.perform(delete("/notifications/eventNotifications/"+notificationName)).andExpect(status().isOk());

		}
		
		
		@Test
		public void notificationsDestincationPathsDelete() throws Exception {
			
			mockMvc.perform(delete("/notifications/destinationPaths/"+pathName)).andExpect(status().isOk());

		}

	
	

	
}
