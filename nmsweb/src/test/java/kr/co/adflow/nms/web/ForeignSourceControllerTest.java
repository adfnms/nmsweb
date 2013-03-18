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
	public void foreignSources() throws Exception {
		mockMvc.perform(get("/foreignSources"));
	}

	// foreignSources/default
	@Test
	public void foreignSourcesDefault() throws Exception {
		mockMvc.perform(get("/foreignSources/defalut"));
	}

	// /foreignSources/deployed GET
	@Test
	public void foreignSourcesDeployed() throws Exception {
		mockMvc.perform(get("/foreignSources/deployed"));
	}

	// /foreignSources/deployed/count GET
	@Test
	public void DeployedCount() throws Exception {
		mockMvc.perform(get("/foreignSources/deployed/count"));
	}

	// POST
	// /foreignSources
	// <foreign-source name="foreignTest"/>
	// {"foreign-source":[{"name":"foreignTest"}]}
	// new Requisitions == foreignPost
	@Test
	public void foreignPost() throws Exception {
		String foreignNameDATA = (String) properties.get("foreignNameDATA");
		mockMvc.perform(post("/foreignSources").content(foreignNameDATA));
	}

	// foreignSources/{name} GET
	@Test
	public void foreignSourcesName() throws Exception {
		String foreignName = (String) properties.get("foreignName");
		mockMvc.perform(get("/foreignSources/" + foreignName));
	}

	// POST
	// <detector
	// class="org.opennms.netmgt.provision.detector.simple.HttpDetector"
	// name="chan2"/>
	// {"detector":[{"class":"org.opennms.netmgt.provision.detector.simple.HttpDetector","name":"chan2"}]}
	// foreignSources/{name}/detectors

	@Test
	public void detectorsPost() throws Exception {
		String foreignName = (String) properties.get("foreignName");
		String detectorsDATA = (String) properties.get("detectorsDATA");
		mockMvc.perform(post("/foreignSources/" + foreignName + "/detectors")
				.content(detectorsDATA));
	}

	// foreignSources/{name}/detectors/ GET

	@Test
	public void detectors() throws Exception {
		String foreignName = (String) properties.get("foreignName");
		mockMvc.perform(get("/foreignSources/" + foreignName + "/detectors"));
	}

	// foreignSources/{name}/detectors/{detector} GET
	@Test
	public void detectorsName() throws Exception {
		String foreignName = (String) properties.get("foreignName");
		String detectorName = (String) properties.get("detectorName");
		mockMvc.perform(get("/foreignSources/" + foreignName + "/detectors/"
				+ detectorName));
	}

	// POST
	// foreignSources/{name}/policies
	// <policy
	// class="org.opennms.netmgt.provision.persist.policies.MatchingIpInterfacePolicy"
	// name="pol2chan">
	// <parameter value="DISABLE_COLLECTION" key="action"/><parameter
	// value="ALL_PARAMETERS" key="matchBehavior"/></policy>

	@Test
	public void policiesPost() throws Exception {
		String foreignName = (String) properties.get("foreignName");
		String policyDATA = (String) properties.get("policyDATA");
		mockMvc.perform(post("/foreignSources/" + foreignName + "/policies")
				.content(policyDATA));
	}

	// foreignSources/{name}/policies GET

	@Test
	public void polices() throws Exception {
		String foreignName = (String) properties.get("foreignName");
		mockMvc.perform(get("/foreignSources/" + foreignName + "/policies"));
	}

	// foreignSources/{name}/policies/{policy} GET
	@Test
	public void policyName() throws Exception {
		String foreignName = (String) properties.get("foreignName");
		String policyName = (String) properties.get("policyName");
		mockMvc.perform(get("/foreignSources/" + foreignName + "/policies/"
				+ policyName));
	}

	// foreignSources/{name}
	// PUT
	// copy
	@Test
	public void foreignSourcePut() throws Exception {
		String foreignName = (String) properties.get("foreignName");
		String putforeignName = (String) properties.get("putforeignName");
		mockMvc.perform(put("/foreignSources/" + foreignName).content(
				putforeignName));
	}

	// DEL
	// foreignSources/{name}/policies/{policy}
	@Test
	public void forDelPolicy() throws Exception {
		String foreignName = (String) properties.get("foreignName");
		String policyName = (String) properties.get("policyName");
		mockMvc.perform(delete("/foreignSources/" + foreignName + "/policies/"
				+ policyName));
	}

	// DEL
	// foreignSources/{name}/detectors/{detector}

	@Test
	public void forDelDecetors() throws Exception {
		String foreignName = (String) properties.get("foreignName");
		String detectorName = (String) properties.get("detectorName");
		mockMvc.perform(delete("/foreignSources/" + foreignName + "/detectors/"
				+ detectorName));
	}

	// DELETE
	// foreignSources/{name}

	@Test
	public void foreignSourceDel() throws Exception {
		String foreignName = (String) properties.get("foreignName");
		mockMvc.perform(delete("/foreignSources/" + foreignName));
	}

	//testputForeignData copy Delete
	
	
	@Test
	public void foreignSourceDelCopy() throws Exception {
		String foreignNameCopy = (String) properties.get("foreignNameCopy");
		mockMvc.perform(delete("/foreignSources/" + foreignNameCopy));
	}
}
