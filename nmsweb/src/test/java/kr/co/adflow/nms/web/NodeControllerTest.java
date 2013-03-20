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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringJUnit4ClassRunner.class)
public class NodeControllerTest extends AbstractContextControllerTests {

	private MockMvc mockMvc;
	
	HashMap hashData = new HashMap();
	
	String id= null;
	String ipAddress =null;
	String serviceName= null;
	String ifIndex= null;
	String categoryName= null;
	String serviceId= null;
	String iplike= null;
	String ip= null;

	Properties properties = new Properties();

	 @Before
	 public void setUpClass() {
	        // rcarver - setup the jndi context and the datasource
	        try {
	            // Create initial context
	            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
	                "org.apache.naming.java.javaURLContextFactory");
	            System.setProperty(Context.URL_PKG_PREFIXES, 
	                "org.apache.naming");            
	            InitialContext ic = new InitialContext();

	            ic.createSubcontext("java:");
	            ic.createSubcontext("java:/comp");
	            ic.createSubcontext("java:/comp/env");
	            ic.createSubcontext("java:/comp/env/jdbc");
	           
	            // Construct DataSource
	            PGPoolingDataSource  ds = new PGPoolingDataSource();
	            ds.setServerName("127.0.0.");
	            ds.setDatabaseName("opennms");
	            ds.setUser("opennms");
	            ds.setPassword("admin");
	            
	            ic.bind("java:/comp/env/jdbc/postgres", ds);
	        } catch (Exception ex) {
	           System.out.println("err::"+ex); 
	        }
	        
	    }

	
	@Before
	public void setup() {
		mockMvc = webAppContextSetup(this.wac).build();
	}
	
	 @Before
	 public void configload() {
//	  Properties properties = new Properties();
	  
	  InputStream is = null;
	  try {
	   is = new FileInputStream("src/test/properties/node.properties");
	   properties.load(is);
	   
	   id = properties.getProperty("id");
	   ipAddress = properties.getProperty("ipAddress");
	   serviceName = properties.getProperty("serviceName");
	   ifIndex = properties.getProperty("ifIndex");
	   categoryName = properties.getProperty("categoryName");
	   serviceId = properties.getProperty("serviceId");
	   iplike = properties.getProperty("iplike");
	   ip = properties.getProperty("ip");
	   

//	   for (String key : properties.stringPropertyNames()) {
//	    String value = properties.getProperty(key);
//	    System.out.println("value:"+value);
//	    hashData.put(key, value);
//
//	   }
	  } catch (Exception e) {

	  }
	 }
	 
	
	

	// get
	@Test
	public void getNodes() throws Exception {
		mockMvc.perform(get("/nodes").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(content().string(containsString("@totalCount")))
				;
	}
	
	@Test
	public void nodeId() throws Exception {
		mockMvc.perform(get("/nodes/"+id).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(content().string(containsString("@label")))
				;
	}
	
	@Test
	public void nodeIdIpinterfaces() throws Exception {
		mockMvc.perform(get("/nodes/"+id+"/ipinterfaces").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(content().string(containsString("@totalCount")))
				;
	}
	
	@Test
	public void nodeIdIpinterfacesIpAddress() throws Exception {
		mockMvc.perform(get("/nodes/"+id+"/ipinterfaces/"+ipAddress).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(content().string(containsString("@isDown")))
				;
	}
	
	@Test
	public void nodeIdIpinterfacesService() throws Exception {
		mockMvc.perform(get("/nodes/"+id+"/ipinterfaces/"+ipAddress+"/services").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(content().string(containsString("totalCount")))
				;
	}
	
	@Test
	public void nodeIdIpinterfacesServiceServiceName() throws Exception {
		mockMvc.perform(get("/nodes/"+id+"/ipinterfaces/"+ipAddress+"/services/"+serviceName).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(content().string(containsString("source")))
				;
	}
	
	@Test
	public void nodeSnmpinterfaces() throws Exception {
		mockMvc.perform(get("/nodes/"+id+"/snmpinterfaces").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(content().string(containsString("@totalCount")))
				;
	}
	
	@Test
	public void nodeSnmpinterfacesIfIndex() throws Exception {
		mockMvc.perform(get("/nodes/"+id+"/snmpinterfaces/"+ifIndex).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(content().string(containsString("@ifIndex")))
				;
	}
	
	@Test
	public void nodeCategories() throws Exception {
		mockMvc.perform(get("/nodes/"+id+"/categories").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(content().string(containsString("category")))
				;
	}
	
	@Test
	public void nodeCategoriesCategoryName() throws Exception {
		mockMvc.perform(get("/nodes/"+id+"/categories/"+categoryName).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(content().string(containsString("@name")))
				;
	}
	
	@Test
	public void nodeAssetRecord() throws Exception {
		mockMvc.perform(get("/nodes/"+id+"/assetRecord").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(content().string(containsString("category")))
				;
	}
	
	@Test
	public void serviceList() throws Exception {
		mockMvc.perform(get("/serviceList"))
				.andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(content().string(containsString("services")))
				;
	}
	
	
	@Test
	public void nodeSearchService() throws Exception {
		mockMvc.perform(get("/nodes/searchService/"+serviceId).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(content().string(containsString("nodeid")))
				;
	}
	
	
	@Test
	public void nodeSearchIp() throws Exception {
		mockMvc.perform(get("/nodes/searchIp/"+iplike).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(content().string(containsString("@totalCount")))
				;
	}

	
	
// //// put
	@Test
	public void nodesIpInterfacesServicesPut() throws Exception {

		mockMvc.perform(put("/nodes/"+id+"/ipinterfaces/"+ipAddress+"/services/"+serviceName+"?status=R")).andExpect(status().isOk());

		// mockMvc.perform(request(HttpMethod.DELETE, "/nodes/{id}", "1000"))
		// .andExpect(status().is(500));
	}
	
	
	@Test
	public void nodesIpinterfacesPut() throws Exception {

		mockMvc.perform(put("/nodes/"+id+"/ipinterfaces/"+ipAddress+"?isManaged=M")).andExpect(status().isOk());

		// mockMvc.perform(request(HttpMethod.DELETE, "/nodes/{id}", "1000"))
		// .andExpect(status().is(500));
	}
	
	
	@Test
	public void nodesSnmpinterfacesPut() throws Exception {

		mockMvc.perform(put("/nodes/"+id+"/snmpinterfaces/"+ifIndex+"?collect=UN")).andExpect(status().isOk());

		// mockMvc.perform(request(HttpMethod.DELETE, "/nodes/{id}", "1000"))
		// .andExpect(status().is(500));
	}

	@Test
	public void nodesPut() throws Exception {

		mockMvc.perform(put("/nodes/"+id+"?label=test")).andExpect(status().isOk());

		// mockMvc.perform(request(HttpMethod.DELETE, "/nodes/{id}", "1000"))
		// .andExpect(status().is(500));
	}
	
	
	

//	// post
//	@Test
//	public void nodeIpPost() throws Exception {
//
//		mockMvc.perform(post("/nodes/scan/"+ip).content("test".getBytes())).andExpect(
//				status().isOk());
//
//		// mockMvc.perform(
//		// request(HttpMethod.POST, "/nodes/scan/{ip}", "192.168.0.1")
//		// .content("testData".getBytes())).andExpect(
//		// status().isOk());
//	}
//
//	// put
//
//	// delete
//	@Test
//	public void nodesIpInterfacesServicesDelete() throws Exception {
//
//		mockMvc.perform(delete("/nodes/"+id+"/ipinterfaces/"+ipAddress+"/services"+serviceName)).andExpect(status().isOk());
//
//		// mockMvc.perform(request(HttpMethod.DELETE, "/nodes/{id}", "1000"))
//		// .andExpect(status().is(500));
//	}
//	@Test
//	public void nodesIpinterfacesDelete() throws Exception {
//
//		mockMvc.perform(delete("/nodes/"+id+"/ipinterfaces/"+ipAddress)).andExpect(status().isOk());
//
//		// mockMvc.perform(request(HttpMethod.DELETE, "/nodes/{id}", "1000"))
//		// .andExpect(status().is(500));
//	}
//	@Test
//	public void nodesSnmpinterfacesDelete() throws Exception {
//
//		mockMvc.perform(delete("/nodes/"+id+"/snmpinterfaces/"+ifIndex)).andExpect(status().isOk());
//
//		// mockMvc.perform(request(HttpMethod.DELETE, "/nodes/{id}", "1000"))
//		// .andExpect(status().is(500));
//	}
//	@Test
//	public void nodesCategoriesDelete() throws Exception {
//
//		mockMvc.perform(delete("/nodes/"+id+"categories/"+categoryName)).andExpect(status().isOk());
//
//		// mockMvc.perform(request(HttpMethod.DELETE, "/nodes/{id}", "1000"))
//		// .andExpect(status().is(500));
//	}
//
//	@Test
//	public void nodesDelete() throws Exception {
//
//		mockMvc.perform(delete("/nodes/"+id)).andExpect(status().isOk());
//
//		// mockMvc.perform(request(HttpMethod.DELETE, "/nodes/{id}", "1000"))
//		// .andExpect(status().is(500));
//	}
	
}
