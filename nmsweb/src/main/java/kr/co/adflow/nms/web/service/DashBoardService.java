package kr.co.adflow.nms.web.service;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.mapper.CategoryGroupMapper;
import kr.co.adflow.nms.web.mapper.ServiceIdNameMapper;
import kr.co.adflow.nms.web.util.CategoryUtil;
import kr.co.adflow.nms.web.vo.Outage;
import kr.co.adflow.nms.web.vo.categories.Catinfo;
import kr.co.adflow.nms.web.vo.categoryDetail.CategoryInfo;
import kr.co.adflow.nms.web.vo.categoryDetail.CategoryInfoList;
import kr.co.adflow.nms.web.vo.categoryDetail.CategoryMain;

import kr.co.adflow.nms.web.vo.resultcategory.CategoryXmlResultGroup;
import kr.co.adflow.nms.web.vo.servicesid.ServiceVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DashBoardService {

	private static final Logger logger = LoggerFactory
			.getLogger(DashBoardService.class);

	private CategoryInfoList infoList;

	private CategoryInfo info;

	@Autowired
	ServiceIdNameMapper serviceMapper;
	@Autowired
	CategoryGroupMapper cateMapper;
	@Autowired
	CategoryXmlResultGroup group;
	@Autowired
	CategoryUtil cateUtil;
	@Autowired
	ServiceVo serviceVo;

	private @Value("#{config['XMLPATH']}")
	String xmlPath;

	private CategoryMain categoryMain = new CategoryMain();

	public CategoryMain getCategoryMain() {
		return categoryMain;
	}

	public void setCategoryMain(CategoryMain categoryMain) {
		this.categoryMain = categoryMain;
	}

	private Hashtable<String, Outage> outageList = null;

	public Hashtable<String, Outage> getOutageList() {
		return outageList;
	}

	public void setOutageList(Hashtable<String, Outage> outageList) {
		this.outageList = outageList;
	}

	@PostConstruct
	public void initCategory() {
		String categoryString = null;
		String serviceString = null;
		String result = null;
		StringBuffer resultBuffer = new StringBuffer();
		try {

			categoryString = (String) this.categoryJsonXml();
			serviceString = (String) this.serviceIDandNameJson();
			serviceVo = serviceMapper.serviceInfo2(serviceString);

			group = cateMapper.cateJson(categoryString);

			// netWork
			int netWorkSize = group.netWorkService().size();
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < netWorkSize; i++) {

				buf.append(group.netWorkService().get(i).getNetWorkServers());
			}
			String netWorkInterfaces = buf.toString();

			// database
			int dataBaseServerSize = group.dataBaseServer().size();
			StringBuffer buf1 = new StringBuffer();
			for (int i = 0; i < dataBaseServerSize; i++) {

				buf1.append(group.dataBaseServer().get(i).getDataBaseServer());
			}
			String dataBaseServer = buf1.toString();

			// dnsserver
			int dnsSize = group.dnsDhcpServers().size();
			StringBuffer buf2 = new StringBuffer();
			for (int i = 0; i < dnsSize; i++) {

				buf2.append(group.dnsDhcpServers().get(i).getDnsDhcpServer());
			}
			String dnsDhcpServers = buf2.toString();

			// eamilserver
			int emailSize = group.emailServers().size();
			StringBuffer buf3 = new StringBuffer();
			for (int i = 0; i < emailSize; i++) {

				buf3.append(group.emailServers().get(i).getEmailServer());
			}
			String emailServers = buf3.toString();

			// jmxserver
			int jmxSize = group.jmxServers().size();
			StringBuffer buf4 = new StringBuffer();
			for (int i = 0; i < jmxSize; i++) {

				buf4.append(group.jmxServers().get(i).getJmxServers());
			}
			String jmxServers = buf4.toString();

			// otherServer
			int otherSize = group.otherServers().size();
			StringBuffer buf5 = new StringBuffer();
			for (int i = 0; i < otherSize; i++) {

				buf5.append(group.otherServers().get(i).getOtherServers());
			}
			String otherServers = buf5.toString();

			// webServer
			int websize = group.webServers().size();
			StringBuffer buf6 = new StringBuffer();
			for (int i = 0; i < websize; i++) {

				buf6.append(group.webServers().get(i).getWebServers());
			}
			String webServers = buf6.toString();

			// dataBaseId
			String dataBaseServerID = null;
			String dataBaseServergroup = "DatabaseServer";
			dataBaseServerID = cateUtil.categoriesId(dataBaseServer, serviceVo);
			logger.debug("dataBaseServerID::" + dataBaseServerID);
			this.getCategoryNodeIdServiceID(dataBaseServerID,
					dataBaseServergroup);

			// netWorkInterfacesID
			String netWorkInterfacesID = null;
			String netWorkInterfacesGroup = "NetWorkInterfaces";
			netWorkInterfacesID = cateUtil.categoriesId(netWorkInterfaces,
					serviceVo);
			logger.debug("netWorkInterfacesID::" + netWorkInterfacesID);
			this.getCategoryNodeIdServiceID(netWorkInterfacesID,
					netWorkInterfacesGroup);

			// dnsDhcpServersID
			String dnsDhcpServersID = null;
			String dnsDhcpServersGroup = "DnsDhcpServers";
			dnsDhcpServersID = cateUtil.categoriesId(dnsDhcpServers, serviceVo);
			logger.debug("dnsDhcpServersID::" + dnsDhcpServersID);
			this.getCategoryNodeIdServiceID(dnsDhcpServersID,
					dnsDhcpServersGroup);

			// emailServersID
			String emailServersID = null;
			String emailServersGroup = "EmailServers";
			emailServersID = cateUtil.categoriesId(emailServers, serviceVo);
			logger.debug("emailServersID::" + emailServersID);

			this.getCategoryNodeIdServiceID(emailServersID, emailServersGroup);

			// otherServersID
			String otherServersID = null;
			String otherServersGroup = "OtherServers";
			otherServersID = cateUtil.categoriesId(otherServers, serviceVo);
			logger.debug("otherServersID::" + otherServersID);

			this.getCategoryNodeIdServiceID(otherServersID, otherServersGroup);

			// jmxServersID
			String jmxServersID = null;
			String jmxServersGroup = "JmxServers";
			jmxServersID = cateUtil.categoriesId(jmxServers, serviceVo);
			logger.debug("jmxServersID::" + jmxServersID);

			this.getCategoryNodeIdServiceID(jmxServersID, jmxServersGroup);

			// webServersID
			String webServersID = null;
			String webServersGroup = "WebServers";
			webServersID = cateUtil.categoriesId(webServers, serviceVo);
			logger.debug("webServersID::" + webServersID);

			this.getCategoryNodeIdServiceID(webServersID, webServersGroup);

		} catch (Exception e) {
			logger.error("Failed in processing", e);
		}
	}

	public String categoryJsonXml() throws HandleException {
		String result = null;

		try {

			JAXBContext jc = JAXBContext.newInstance(Catinfo.class);
			Unmarshaller u = jc.createUnmarshaller();
			File f = new File(xmlPath + "categories.xml");

			Object ob = u.unmarshal(f);
			result = ob.toString();
			Catinfo info = new Catinfo();
			info = (Catinfo) ob;
			logger.debug("xml Read : success");
			int categorySize = 0;
			int serviceSize = 0;
			int size = info.getCategorygroup().size();
			StringBuffer bufLable = new StringBuffer();

			for (int i = 0; i < size; i++) {
				categorySize = info.getCategorygroup().get(i).getCategories()
						.getCategory().size();

				for (int j = 0; j < categorySize; j++) {
					serviceSize = info.getCategorygroup().get(i)
							.getCategories().getCategory().get(j).getService()
							.size();
					String labelName = info.getCategorygroup().get(i)
							.getCategories().getCategory().get(j).getLabel();
					bufLable.append("{");
					bufLable.append("\"" + labelName + "\":");
					bufLable.append("[");
					for (int k = 0; k < serviceSize; k++) {
						info.getCategorygroup().get(i).getCategories()
								.getCategory().get(j).getService().get(k);
						String serviceName = info.getCategorygroup().get(i)
								.getCategories().getCategory().get(j)
								.getService().get(k);

						bufLable.append("\"" + serviceName + "\",");

					}
					bufLable.delete(bufLable.length() - 1, bufLable.length());
					bufLable.append("]},");
				}
				bufLable.delete(bufLable.length() - 1, bufLable.length());

			}
			result = bufLable.toString();
			result = result.substring(35);
			StringBuffer buf = new StringBuffer();
			buf.append(result);
			result = buf.insert(0, "{\"result\":[").toString();
			logger.debug(bufLable.toString());
			result = result + "]}";
			logger.debug(result);

		} catch (Exception e) {

			throw new HandleException(e);
		}

		logger.debug("ServiceRetunData::" + result);
		return result;
	}

	public String serviceIDandNameJson() throws HandleException {

		StringBuffer result = new StringBuffer();

		Statement stmt = null;
		ResultSet rst = null;
		Connection conn = null;
		String sql = null;

		try {

			Context ctx = new InitialContext();

			if (ctx == null)
				throw new Exception("Boom - No Context");

			// /jdbc/postgres is the name of the resource above
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/postgres");

			if (ds != null) {
				conn = ds.getConnection();

				if (conn != null) {
					stmt = conn.createStatement();
					sql = "SELECT serviceid, servicename FROM service";

					rst = stmt.executeQuery(sql);

					result.append("{\"services\":[");
					while (rst.next()) {
						CategoryXmlResultGroup group = new CategoryXmlResultGroup();
						result.append("{\"serviceid\":\"" + rst.getInt(1)
								+ "\",");
						result.append("\"servicename\":\"" + rst.getString(2)
								+ "\"},");

					}

					rst.close();

					// last "&" delete.
					result.deleteCharAt(result.length() - 1);
					result.append("]}");
					logger.debug("ResultSet Json:::" + result.toString());
				}
			}
		} catch (Exception e) {
			throw new HandleException(e);
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
		}

		return result.toString();
	}

	public CategoryMain getCategoryNodeIdServiceID(String categorygroupId,
			String categorygroupName) throws HandleException {
		CategoryMain categoryMain = this.getCategoryMain();
		logger.debug("cateGoryGroupname:" + categorygroupName);
		if (categorygroupId.length() < 1) {
			CategoryInfoList cateinfoList = new CategoryInfoList();
			categoryMain.getCateGoryTable()
					.put(categorygroupName, cateinfoList);
			return categoryMain;
		} else {

			StringBuffer result = new StringBuffer();
			Statement stmt = null;
			ResultSet rst = null;
			Connection conn = null;
			String sql = null;
			this.outTage();
			try {
				Context ctx = new InitialContext();
				if (ctx == null)
					throw new Exception("Boom - No Context");

				// /jdbc/postgres is the name of the resource above
				DataSource ds = (DataSource) ctx
						.lookup("java:comp/env/jdbc/postgres");
				if (ds != null) {
					conn = ds.getConnection();

					if (conn != null) {
						stmt = conn.createStatement();
						sql = "SELECT node.nodeid, ipAddr,node.nodelabel,serviceid FROM node, "
								+ "ifservices WHERE node.nodeid = ifservices.nodeid and serviceid in ("
								+ categorygroupId
								+ ") and node.nodeid in "
								+ "(SELECT nodeid FROM ifservices WHERE status != 'D' AND serviceid in ("
								+ categorygroupId + "))";

						rst = stmt.executeQuery(sql);
						infoList = new CategoryInfoList();
						int temp = 0;
						int count = 0;
						int totalServiceCount = 0;

						int totalOutageCount = 0;

						double totalAvl = 0;
						double totalserviceAvl = 0;
						int av = 0;
						while (rst.next()) {

							int nodeid = rst.getInt(1);
							CategoryInfo tempInfo = new CategoryInfo();
							if (infoList.getCateGoryInfo().containsKey(nodeid)) {

								tempInfo = infoList.getCateGoryInfo().get(
										nodeid);
								int tempCount = tempInfo.getServiceCount();
								tempCount++;
								tempInfo.setServiceCount(tempCount);
								totalServiceCount++;

								// double availabili = info.getAvailabili();

								double availabili2 = serviceAvailability(
										info.getNodeId(), info.getIpAddress(),
										rst.getInt(4));

								totalserviceAvl = totalserviceAvl + availabili2;

								totalAvl = totalAvl + availabili2;

								// info.setAvailabili((availabili + availabili2)
								// / totalServiceCount);
								info.setAvailabili(availabili2
										/ totalServiceCount);

								String outageKey = String.valueOf(rst.getInt(1)
										+ ":" + rst.getString(2) + ":"
										+ rst.getInt(4));
								if (getOutageList().containsKey(outageKey)) {
									int tempOutageCount = tempInfo
											.getOutageCount();
									tempOutageCount++;
									tempInfo.setOutageCount(tempOutageCount);
									totalOutageCount++;
								}

							} else {
								info = new CategoryInfo();
								info.setNodeId(rst.getInt(1));
								info.setIpAddress(rst.getString(2));
								info.setNodeLabel(rst.getString(3));
								info.setServiceCount(1);
								totalServiceCount++;
								info.setAvailabili(serviceAvailability(
										info.getNodeId(), info.getIpAddress(),
										rst.getInt(4)));
								totalserviceAvl = info.getAvailabili();
								// totalAvl = totalAvl+ info.getAvailabili();
								av++;
								logger.debug("totalAvl11:"
										+ String.valueOf(totalAvl) + ", "
										+ info.getAvailabili());
								logger.debug("av11:" + av + ", nodeid =="
										+ info.getNodeId());

								String outageKey = String.valueOf(rst.getInt(1)
										+ ":" + rst.getString(2) + ":"
										+ rst.getInt(4));

								if (getOutageList().containsKey(outageKey)) {

									info.setOutageCount(1);
									totalOutageCount++;
								} else {
									info.setOutageCount(0);

								}

								infoList.getCateGoryInfo().put(nodeid, info);

							}

							// totalAvl = totalAvl / av;
							// infoList.setServiceids(categorygroupId);
							// infoList.setOutageTotalCount(totalOutageCount);
							// infoList.setServiceTotalCount(totalServiceCount);
							// infoList.setAvailabiliAv(totalAvl);
							//
							// categoryMain.getCateGoryTable().put(categorygroupName,
							// infoList);

						}

						Hashtable<Integer, CategoryInfo> cateGoryInfo = infoList
								.getCateGoryInfo();
						Iterator<Integer> it = cateGoryInfo.keySet().iterator();

						while (it.hasNext()) {
							int categoryInfoKey = it.next();

							CategoryInfo categoryInfo = cateGoryInfo
									.get(categoryInfoKey);

							totalAvl = totalAvl + categoryInfo.getAvailabili();

						}

						logger.debug("totalAvl:" + String.valueOf(totalAvl));
						logger.debug("av:" + av);
						totalAvl = totalAvl / av;
						if (Double.isNaN(totalAvl)) {
							totalAvl = 0.0;
						}
						infoList.setServiceids(categorygroupId);
						infoList.setOutageTotalCount(totalOutageCount);
						infoList.setServiceTotalCount(totalServiceCount);
						infoList.setAvailabiliAv(totalAvl);
						logger.debug("totalAv:" + infoList.getAvailabili());
						logger.debug("Serviceids:" + infoList.getServiceids());
						logger.debug("totalServiceCount:"
								+ infoList.getServiceTotalCount());
						logger.debug("totalOutageCount:"
								+ infoList.getOutageTotalCount());
						logger.debug("categorygroupName:" + categorygroupName);
						categoryMain.getCateGoryTable().put(categorygroupName,
								infoList);
						this.setCategoryMain(categoryMain);
					}

					rst.close();

				}

			} catch (Exception e) {
				throw new HandleException(e);
			} finally {
				if (stmt != null)
					try {
						stmt.close();
					} catch (Exception e) {
					}
				if (conn != null)
					try {
						conn.close();
					} catch (Exception e) {
					}
			}

			return categoryMain;
		}
	}

	public double nodeAvailability(int nodeIds) throws HandleException {
		Calendar cal = new GregorianCalendar();
		Date now = cal.getTime();
		cal.add(Calendar.DATE, -1);
		Date yesterday = cal.getTime();

		return nodeAvailability(nodeIds, yesterday, now);
	}

	public String allCategory() throws HandleException {
		StringBuffer result = new StringBuffer();

		try {

			Hashtable<String, CategoryInfoList> cateGoryTable = this
					.getCategoryMain().getCateGoryTable();

			Iterator<String> it = cateGoryTable.keySet().iterator();

			result.append("{\"CategoryInfo\":[");

			while (it.hasNext()) {
				String categoryInfoKey = (String) it.next();

				CategoryInfoList categoryInfoList = cateGoryTable
						.get(categoryInfoKey);

				result.append("{\"name\":\"" + categoryInfoKey
						+ "\",\"outageTotalCount\":\""
						+ categoryInfoList.getOutageTotalCount()
						+ "\",\"serviceTotalCount\":\""
						+ categoryInfoList.getServiceTotalCount()
						+ "\",\"availabili\":\""
						+ categoryInfoList.getAvailabili() + "\"},");

			}

			// last "," delete

			result.deleteCharAt(result.length() - 1);
			result.append("],");

			Hashtable<String, Outage> outageList = this.getOutageList();

			Iterator<String> outageIt = outageList.keySet().iterator();

			result.append("\"Outages\":[");

			while (outageIt.hasNext()) {
				String outageKey = (String) outageIt.next();

				Outage outage = outageList.get(outageKey);

				result.append("{\"outageid\":\"" + outage.getOutageid()
						+ "\",\"iflostservice\":\"" + outage.getIflostservice()
						+ "\",\"nodeid\":\"" + outage.getNodeid()
						+ "\",\"serviceid\":\"" + outage.getServiceid()
						+ "\",\"ipaddr\":\"" + outage.getIpaddr() + "\"},");

			}

			// last "," delete

			result.deleteCharAt(result.length() - 1);
			result.append("]}");

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result.toString();

	}

	public String allCategory(String categoryName) throws HandleException {
		StringBuffer result = new StringBuffer();

		try {

			CategoryInfoList categoryInfoList = this.getCategoryMain()
					.getCateGoryTable().get(categoryName);

			result.append("{\"CategoryInfo\":");

			result.append("{\"name\":\"" + categoryName
					+ "\",\"outageTotalCount\":\""
					+ categoryInfoList.getOutageTotalCount()
					+ "\",\"serviceTotalCount\":\""
					+ categoryInfoList.getServiceTotalCount()
					+ "\",\"availabili\":\"" + categoryInfoList.getAvailabili()
					+ "\"},");

			Hashtable<String, Outage> outageList = this.getOutageList();

			Iterator<String> outageIt = outageList.keySet().iterator();

			result.append("\"Outages\":[");

			while (outageIt.hasNext()) {
				String outageKey = (String) outageIt.next();

				Outage outage = outageList.get(outageKey);

				result.append("{\"outageid\":\"" + outage.getOutageid()
						+ "\",\"iflostservice\":\"" + outage.getIflostservice()
						+ "\",\"nodeid\":\"" + outage.getNodeid()
						+ "\",\"serviceid\":\"" + outage.getServiceid()
						+ "\",\"ipaddr\":\"" + outage.getIpaddr() + "\"},");

			}

			// last "," delete

			result.deleteCharAt(result.length() - 1);
			result.append("]}");

		} catch (Exception e) {
			throw new HandleException(e);
		}

		return result.toString();

	}

	public String cateGoryDetail(String categoryName) throws HandleException {
		StringBuffer result = new StringBuffer();

		try {

			CategoryInfoList categoryInfoList = this.getCategoryMain()
					.getCateGoryTable().get(categoryName);

			result.append("{\"CategoryInfo\":");

			result.append("{\"name\":\"" + categoryName + "\"},");

			Iterator<Integer> nodeCate = categoryInfoList.getCateGoryInfo()
					.keySet().iterator();

			result.append("\"Detail\":[");

			while (nodeCate.hasNext()) {
				int infoKey = nodeCate.next();

				CategoryInfo cateInfo = categoryInfoList.getCateGoryInfo().get(
						infoKey);

				result.append("{\"nodeLabel\":\"" + cateInfo.getNodeLabel()
						+ "\",\"outageCount\":\"" + cateInfo.getOutageCount()
						+ "\",\"serviceCount\":\"" + cateInfo.getServiceCount()
						+ "\",\"availavili\":\"" + cateInfo.getAvailabili()
						+ "\"},");

			}

			// last "," delete
			result.deleteCharAt(result.length() - 1);
			result.append("],");

			Hashtable<String, Outage> outageList = this.getOutageList();

			Iterator<String> outageIt = outageList.keySet().iterator();

			result.append("\"Outages\":[");

			while (outageIt.hasNext()) {
				String outageKey = (String) outageIt.next();

				Outage outage = outageList.get(outageKey);

				result.append("{\"outageid\":\"" + outage.getOutageid()
						+ "\",\"iflostservice\":\"" + outage.getIflostservice()
						+ "\",\"nodeid\":\"" + outage.getNodeid()
						+ "\",\"serviceid\":\"" + outage.getServiceid()
						+ "\",\"ipaddr\":\"" + outage.getIpaddr() + "\"},");

			}

			// last "," delete

			result.deleteCharAt(result.length() - 1);
			result.append("]}");

		} catch (Exception e) {
			throw new HandleException(e);
		}
		String result2 = result.toString();
		if (result2.contains("\"Detail\":],")) {
			result2 = result2.replace("\"Detail\":],", "");
		}

		return result2;

	}

	public double nodeAvailability(int nodeIds, Date start, Date end)
			throws HandleException {

		if (start == null || end == null) {
			throw new IllegalArgumentException("Cannot take null parameters.");
		}

		if (end.before(start)) {
			throw new IllegalArgumentException(
					"Cannot have an end time before the start time.");
		}

		if (end.equals(start)) {
			throw new IllegalArgumentException(
					"Cannot have an end time equal to the start time.");
		}

		double avail = -1;
		int nodeid = 0;

		StringBuffer result = new StringBuffer();

		Statement stmt = null;
		ResultSet rst = null;
		Connection conn = null;
		StringBuffer sql = new StringBuffer();

		try {
			Context ctx = new InitialContext();
			if (ctx == null)
				throw new Exception("Boom - No Context");

			// /jdbc/postgres is the name of the resource above
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/postgres");
			if (ds != null) {
				conn = ds.getConnection();

				if (conn != null) {
					stmt = conn.createStatement();

					Timestamp startTime = new Timestamp(start.getTime());
					Timestamp endTime = new Timestamp(end.getTime());

					sql.append("select getManagePercentAvailNodeWindow("
							+ nodeIds + ", '" + endTime + "','" + startTime
							+ "')  from node ");

					rst = stmt.executeQuery(sql.toString());

					while (rst.next()) {

						avail = rst.getDouble(1);
					}

					rst.close();

				}
			}
		} catch (Exception e) {
			throw new HandleException(e);
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
		}

		return avail;

	}

	public double serviceAvailability(int nodeId, String ipAddr, int serviceIds)
			throws HandleException {
		if (ipAddr == null) {
			throw new IllegalArgumentException("Cannot take null parameters.");
		}

		Calendar cal = new GregorianCalendar();
		Date now = cal.getTime();
		cal.add(Calendar.DATE, -1);
		Date yesterday = cal.getTime();

		return serviceAvailability(nodeId, ipAddr, serviceIds, yesterday, now);
	}

	public double serviceAvailability(int nodeId, String ipAddr,
			int serviceIds, Date start, Date end) throws HandleException {
		if (start == null || end == null) {
			throw new HandleException("Cannot take null parameters.");
		}

		if (end.before(start)) {
			throw new HandleException(
					"Cannot have an end time before the start time.");
		}

		if (end.equals(start)) {
			throw new HandleException(
					"Cannot have an end time equal to the start time.");
		}

		double result = 0;

		Statement stmt = null;
		ResultSet rst = null;
		Connection conn = null;
		StringBuffer sql = new StringBuffer();

		try {
			Context ctx = new InitialContext();
			if (ctx == null)
				throw new Exception("Boom - No Context");

			// /jdbc/postgres is the name of the resource above
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/postgres");
			if (ds != null) {
				conn = ds.getConnection();

				if (conn != null) {
					stmt = conn.createStatement();

					Timestamp startTime = new Timestamp(start.getTime());
					Timestamp endTime = new Timestamp(end.getTime());

					sql.append("select getPercentAvailabilityInWindow("
							+ nodeId
							+ ", '"
							+ ipAddr
							+ "', '"
							+ serviceIds
							+ "','"
							+ endTime
							+ "','"
							+ startTime
							+ "')  as avail from ifservices, ipinterface where ifservices.ipaddr = ipinterface.ipaddr and ifservices.nodeid = ipinterface.nodeid and ipinterface.ismanaged='M' and ifservices.nodeid="
							+ nodeId + " and ifservices.ipaddr='" + ipAddr
							+ "'  and serviceid = " + serviceIds);

					logger.debug("sql:::" + sql.toString());
					rst = stmt.executeQuery(sql.toString());

					while (rst.next()) {

						result = rst.getInt(1);

					}

					rst.close();

				}
			}
		} catch (Exception e) {
			throw new HandleException(e);
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
		}

		return result;

	}

	@PostConstruct
	public void outTage() throws HandleException {

		Statement stmt = null;
		ResultSet rst = null;
		Connection conn = null;
		String sql = null;

		try {

			Context ctx = new InitialContext();

			if (ctx == null)
				throw new Exception("Boom - No Context");

			// /jdbc/postgres is the name of the resource above
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/postgres");

			if (ds != null) {
				conn = ds.getConnection();

				if (conn != null) {
					stmt = conn.createStatement();
					sql = "SELECT nodeid, ipaddr, serviceid, outageid, iflostservice FROM outages where ifregainedservice is null order by iflostservice desc";

					rst = stmt.executeQuery(sql);
					outageList = new Hashtable<String, Outage>();
					while (rst.next()) {
						Outage outages = new Outage();
						outages.setNodeid(rst.getInt(1));
						outages.setIpaddr(rst.getString(2));
						outages.setServiceid(rst.getInt(3));
						outages.setOutageid(rst.getInt(4));
						outages.setIflostservice(rst.getString(5));
						String key = String.valueOf(rst.getInt(1) + ":"
								+ rst.getString(2) + ":" + rst.getInt(3));

						outageList.put(key, outages);
					}
					this.setOutageList(outageList);
					rst.close();

				}
			}
		} catch (Exception e) {
			throw new HandleException(e);
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
		}

	}

}
