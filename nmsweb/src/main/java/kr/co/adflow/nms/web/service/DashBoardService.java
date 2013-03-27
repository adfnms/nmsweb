package kr.co.adflow.nms.web.service;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.vo.Outage;
import kr.co.adflow.nms.web.vo.categories.Catinfo;
import kr.co.adflow.nms.web.vo.categoryDetail.CategoryInfo;
import kr.co.adflow.nms.web.vo.categoryDetail.CategoryInfoList;
import kr.co.adflow.nms.web.vo.categoryDetail.CategoryMain;
import kr.co.adflow.nms.web.vo.resultcategory.CategoryJsonGroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DashBoardService {

	private static final Logger logger = LoggerFactory
			.getLogger(DashBoardService.class);

	private CategoryInfoList infoList;

	private CategoryInfo info;
	
	private @Value("#{config['XMLPATH']}")
	String xmlPath;
	
	
	private CategoryMain categoryMain;
	
	public CategoryMain getCategoryMain() {
		return categoryMain;
	}

	private Outage outage;

	private Hashtable<String, Outage> outageList = null;

	public Hashtable<String, Outage> getOutageList() {
		return outageList;
	}

	public String categoryJsonXml() throws HandleException {
		String result = null;
		try {

			JAXBContext jc = JAXBContext.newInstance(Catinfo.class);
			Unmarshaller u = jc.createUnmarshaller();
			File f = new File(xmlPath);
			// JAXBElement element = (JAXBElement) u.unmarshal (f);
			// File f = new File("d:\\OpenNMS\\etc\\destinationPaths.xml");

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
						CategoryJsonGroup group = new CategoryJsonGroup();
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
		CategoryMain categoryMain = new CategoryMain();
		logger.debug("cateGoryGroupname:" + categorygroupName);
		if (categorygroupId.length() < 1) {

			CategoryMain mainCate = new CategoryMain();
			CategoryInfoList infoList = new CategoryInfoList();
			mainCate.getCateGoryTable().put(categorygroupName, infoList);
			mainCate.getCateGoryTable().get(categorygroupName);

			return mainCate;
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
						int av = 1;
						while (rst.next()) {

							String nodeid = String.valueOf(rst.getInt(1));
							CategoryInfo tempInfo = new CategoryInfo();
							if (infoList.getCateGoryInfo().containsKey(nodeid)) {

								tempInfo = infoList.getCateGoryInfo().get(
										nodeid);
								int tempCount = tempInfo.getServiceCount();
								tempCount++;
								tempInfo.setServiceCount(tempCount);
								totalServiceCount++;
								String outageKey = String.valueOf(rst.getInt(1)
										+ ":" + rst.getString(2) + ":"
										+ rst.getInt(4));
								if (getOutageList().containsKey(outageKey)) {
									int tempOutageCount = tempInfo.getOutageCount();
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
								info.setAvailabili(nodeAvailability(info
										.getNodeId()));
								totalAvl=info.getAvailabili();
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
							

						}
						totalAvl = totalAvl / av;
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
						categoryMain.getCateGoryTable().put(categorygroupName,
								infoList);
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
