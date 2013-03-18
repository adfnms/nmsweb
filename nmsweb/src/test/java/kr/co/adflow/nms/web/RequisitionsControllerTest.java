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
public class RequisitionsControllerTest extends AbstractContextControllerTests {

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
					"src/test/java/properties/requisitions.properties");
			properties.load(is);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// requisitions
	// !!!POST
	// // <model-import foreign-source="reTestCase"/>
	@Test
	public void requisitionsAdd() throws Exception {
		String foreignSourceDATA = (String) properties.get("foreignSourceDATA");
		mockMvc.perform(post("/requisitions").content(foreignSourceDATA));
	}

	// /requisitions GET
	@Test
	public void requisitionsGet() throws Exception {
		mockMvc.perform(get("/requisitions"));

	}

	// requisitions/count GET

	@Test
	public void requisitionsCountGet() throws Exception {
		mockMvc.perform(get("/requisitions/count"));
	}

	// requisitions/deployed GET

	@Test
	public void requisitionsDeployed() throws Exception {
		mockMvc.perform(get("/requisitions/deployed"));
	}

	// requisitions/deployed/count GET

	@Test
	public void DeployedCount() throws Exception {
		mockMvc.perform(get("/requisitions/deployed/count"));
	}

	// <node node-label="testCaseNode" foreign-id="13622227"
	// building="BuildTestCase"/>
	// {"node[{"node-label:"testCaseNode","foreign-id":"13622227","building":"BuildTestCase"}]};
	// / requisitions/{name}/nodes
	// !!!POST
	@Test
	public void nodeIdBuildAdd() throws Exception {
		String foreignSourceName = (String) properties.get("foreignSourceName");
		String nodeIdBuild = (String) properties.get("nodeIdBuild");
		mockMvc.perform(post("/requisitions/" + foreignSourceName + "/nodes")
				.content(nodeIdBuild));
	}

	// requisitions/{name} GET
	@Test
	public void requisitionsName() throws Exception {
		String foreignSourceName = (String) properties.get("foreignSourceName");
		mockMvc.perform(get("/requisitions/" + foreignSourceName));
	}

	// /requisitions/{name}/nodes GET
	@Test
	public void requisitionsNameNdoes() throws Exception {
		String foreignSourceName = (String) properties.get("foreignSourceName");
		mockMvc.perform(get("/requisitions/" + foreignSourceName + "/nodes"));
	}

	// requisitions/{name}/nodes/{foreignId} GET

	@Test
	public void requisitionsforId() throws Exception {
		String foreignSourceName = (String) properties.get("foreignSourceName");
		String foreignId = (String) properties.get("foreignId");
		mockMvc.perform(get("/requisitions/" + foreignSourceName + "/nodes/"
				+ foreignId));
	}

	// <interface snmp-primary="S" status="1" ip-addr="61.78.35.200"
	// descr="">!!!													POST
	// requisitions/{name}/nodes/{foreignId}/interfaces
	// {"interface":[{"snmp-primary":"S","ip-addr":"61.78.35.200",
	// "descr"="testCaseInterface"}]}
	@Test
	public void nodeInterfaceAdd() throws Exception {
		String foreignSourceName = (String) properties.get("foreignSourceName");
		String foreignId = (String) properties.get("foreignId");
		String interfaceAdd = (String) properties.get("interfaceAdd");
		mockMvc.perform(post(
				"/requisitions/" + foreignSourceName + "/nodes/" + foreignId
						+ "/interfaces").content(interfaceAdd));
	}

	// /requisitions/{name}/nodes/{foreignId}/interfaces GET

	@Test
	public void requisitionsforIdInter() throws Exception {
		String foreignSourceName = (String) properties.get("foreignSourceName");
		String foreignId = (String) properties.get("foreignId");
		mockMvc.perform(get("/requisitions/" + foreignSourceName + "/nodes/"
				+ foreignId + "/interfaces"));
	}

	// /requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress} GET
	@Test
	public void requisitionsforIdInterIP() throws Exception {
		String foreignSourceName = (String) properties.get("foreignSourceName");
		String foreignId = (String) properties.get("foreignId");
		String ipAddress = (String) properties.get("ipAddress");
		mockMvc.perform(get("/requisitions/" + foreignSourceName + "/nodes/"
				+ foreignId + "/interfaces/" + ipAddress));
	}

	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services
	// POST!!!!																POST
	// <monitored-service service-name="ICMP"/>
	// {"monitored-service":[{"service-name":"ICMP"}]}
	@Test
	public void serviceAdd() throws Exception {
		String foreignSourceName = (String) properties.get("foreignSourceName");
		String foreignId = (String) properties.get("foreignId");
		String ipAddress = (String) properties.get("ipAddress");
		String serviceData = (String) properties.get("serviceData");
		mockMvc.perform(post(
				"/requisitions/" + foreignSourceName + "/nodes/" + foreignId
						+ "/interfaces/" + ipAddress + "/services").content(
				serviceData));
	}

	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services GET
	@Test
	public void requisitionsIPServices() throws Exception {
		String foreignSourceName = (String) properties.get("foreignSourceName");
		String foreignId = (String) properties.get("foreignId");
		String ipAddress = (String) properties.get("ipAddress");
		mockMvc.perform(get("/requisitions/" + foreignSourceName + "/nodes/"
				+ foreignId + "/interfaces/" + ipAddress + "/services"));
	}

	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services/{service}
	// GET
	@Test
	public void requisitionsIPServicesName() throws Exception {
		String foreignSourceName = (String) properties.get("foreignSourceName");
		String foreignId = (String) properties.get("foreignId");
		String ipAddress = (String) properties.get("ipAddress");
		String service = (String) properties.get("service");
		mockMvc.perform(get("/requisitions/" + foreignSourceName + "/nodes/"
				+ foreignId + "/interfaces/" + ipAddress + "/services/"
				+ service));
	}

	// Post!!
	// requisitions/{name}/nodes/{foreignId}/categories
	// <category name="Production"/>										POST
	// {"category":[{"name":"Production"}]}
	@Test
	public void categoryAdd() throws Exception {
		String foreignSourceName = (String) properties.get("foreignSourceName");
		String foreignId = (String) properties.get("foreignId");
		String categoryData = (String) properties.get("categoryData");
		mockMvc.perform(post(
				"/requisitions/" + foreignSourceName + "/nodes/" + foreignId
						+ "/categories").content(categoryData));
	}

	// requisitions/{name}/nodes/{foreignId}/categories GET
	@Test
	public void requisitionsCategory() throws Exception {
		String foreignSourceName = (String) properties.get("foreignSourceName");
		String foreignId = (String) properties.get("foreignId");
		mockMvc.perform(get("/requisitions/" + foreignSourceName + "/nodes/"
				+ foreignId + "/categories"));
	}

	// requisitions/{name}/nodes/{foreignId}/categories/{categoryName} GET

	@Test
	public void requisitionsCategoryName() throws Exception {
		String foreignSourceName = (String) properties.get("foreignSourceName");
		String foreignId = (String) properties.get("foreignId");
		String category = (String) properties.get("category");
		mockMvc.perform(get("/requisitions/" + foreignSourceName + "/nodes/"
				+ foreignId + "/categories/" + category));
	}

	// Post!!
	// /requisitions/{name}/nodes/{foreignId}/assets
	// <asset value="test" name="admin"/>									POST
	// {"asset":[{"value":"test","name":"admin"}]}
	@Test
	public void assetsAdd() throws Exception {
		String foreignSourceName = (String) properties.get("foreignSourceName");
		String foreignId = (String) properties.get("foreignId");
		String assetsData = (String) properties.get("assetsData");
		mockMvc.perform(post(
				"/requisitions/" + foreignSourceName + "/nodes/" + foreignId
						+ "/assets").content(assetsData));
	}

	// requisitions/{name}/nodes/{foreignId}/assets GET

	@Test
	public void requisitionsAssets() throws Exception {
		String foreignSourceName = (String) properties.get("foreignSourceName");
		String foreignId = (String) properties.get("foreignId");
		mockMvc.perform(get("/requisitions/" + foreignSourceName + "/nodes/"
				+ foreignId + "/assets"));
	}

	// // PUT												PUT
	// // /requisitions/{name}/import
	@Test
	public void importRequisitions() throws Exception {
		String foreignSourceName = (String) properties.get("foreignSourceName");
		mockMvc.perform(put("/requisitions/" + foreignSourceName + "/import"));
	}
				
	// // PUT													PUT
	// // requisitions/{name}/import?rescanExisting=false
	//
	@Test
	public void importFalseRequisitions() throws Exception {
		String foreignSourceName = (String) properties.get("foreignSourceName");
		mockMvc.perform(put("/requisitions/" + foreignSourceName
				+ "/import?rescanExisting=false"));
	}

	// PUT													PUT
	// requisitions/{name}
	// {"foreign-source":"putChangeTestCase"}
	// copy version

	@Test
	public void putRequisitions() throws Exception {
		String foreignSourceName = (String) properties.get("foreignSourceName");
		String putForeignSource = (String) properties.get("putForeignSource");
		mockMvc.perform(put("/requisitions/" + foreignSourceName).content(
				putForeignSource));
	}

	// PUt															PUT
	// /requisitions/{name}/nodes/{foreignId}
	// foreign-id="123455"
	@Test
	public void putRequisitionsNode() throws Exception {
		String foreignSourceName = (String) properties.get("foreignSourceName");
		String foreignId = (String) properties.get("foreignId");
		String changeForeignIdDATA = (String) properties
				.get("changeForeignIdDATA");
		mockMvc.perform(put(
				"/requisitions/" + foreignSourceName + "/nodes/" + foreignId)
				.content(changeForeignIdDATA));
	}

	// PUT														PUT
	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}

	@Test
	public void putRequisitionsInterfacesIP() throws Exception {
		String foreignSourceName = (String) properties.get("foreignSourceName");
		String changeForeignID = (String) properties.get("changeForeignID");
		String ipAddress = (String) properties.get("ipAddress");
		String ipAddressDATA = (String) properties.get("ipPutData");
		mockMvc.perform(put(
				"/requisitions/" + foreignSourceName + "/nodes/"
						+ changeForeignID + "/interfaces/" + ipAddress)
				.content(ipAddressDATA));
	}

	// DELETE
	// requisitions/{name}/nodes/{foreignId}/assets/{field}

	@Test
	public void delAssets() throws Exception {
		String delSourceName = (String) properties.get("foreignSourceName");
		String changeForeignID = (String) properties.get("changeForeignID");
		String field = (String) properties.get("field");
		mockMvc.perform(delete("/requisitions/" + delSourceName + "/nodes/"
				+ changeForeignID + "/assets/" + field));

	}

//	// DELETE															DELETE
//	// requisitions/{name}/nodes/{foreignId}/categories/{category}
//
//	@Test
//	public void delCategory() throws Exception {
//		String delSourceName = (String) properties.get("foreignSourceName");
//		String changeForeignID = (String) properties.get("changeForeignID");
//		String delCategories = (String) properties.get("delCategories");
//		mockMvc.perform(delete("/requisitions/" + delSourceName + "/nodes/"
//				+ changeForeignID + "/categories/" + delCategories));
//
//	}
//	
//		// DELETE														DELETE
//	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}/services/{service}
//	@Test
//	public void delServices() throws Exception {
//		String delSourceName = (String) properties.get("foreignSourceName");
//		String changeForeignID = (String) properties.get("changeForeignID");
//		String delIpAddress = (String) properties.get("delIpAddress");
//		String service = (String) properties.get("delService");
//		mockMvc.perform(delete("/requisitions/" + delSourceName + "/nodes/"
//				+ changeForeignID + "/interfaces/" + delIpAddress
//				+ "/services/" + service));
//
//	}
//
//	// DELETE														DELETE
//	// requisitions/{name}/nodes/{foreignId}/interfaces/{ipAddress}
//
//	@Test
//	public void delNodesIpAddress() throws Exception {
//		String delSourceName = (String) properties.get("foreignSourceName");
//		String changeForeignID = (String) properties.get("changeForeignID");
//		String delIpAddress = (String) properties.get("delIpAddress");
//		mockMvc.perform(delete("/requisitions/" + delSourceName + "/nodes/"
//				+ changeForeignID + "/interfaces/" + delIpAddress));
//	}
//
//	//
//	// // // DELETE                                             DELETE
//	// // // all delete
//	// // // requisitions/{name}/nodes/{foreignId}
//	@Test
//	public void delNodesForeingId() throws Exception {
//		String delSourceName = (String) properties.get("foreignSourceName");
//		String changeForeignID = (String) properties.get("changeForeignID");
//		mockMvc.perform(delete("/requisitions/" + delSourceName + "/nodes/"
//				+ changeForeignID));
//
//	}
//
//	// // PUT													
//	// // /requisitions/{name}/import
//	@Test
//	public void importRequisitions2() throws Exception {
//		String foreignSourceName = (String) properties.get("foreignSourceName");
//		mockMvc.perform(put("/requisitions/" + foreignSourceName + "/import"));
//	}
//
//	// DELETE
//	// alldelete
//	// PUT delete
//	// /requisitions/{name}
//	@Test
//	public void putDelSourceName() throws Exception {
//
//		String delPutForeignSourceName = (String) properties
//				.get("delPutForeignSourceName");
//
//		mockMvc.perform(delete("/requisitions/" + delPutForeignSourceName));
//
//	}
//
//	// // DELETE
//	// // alldelete
//	// // requisitions/deployed/{name}
//	@Test
//	public void DelSourceNameDeployed() throws Exception {
//
//		String delSourceName = (String) properties.get("foreignSourceName");
//
//		mockMvc.perform(delete("/requisitions/deployed/" + delSourceName));
//
//	}

}
