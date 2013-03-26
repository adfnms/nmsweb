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
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.vo.categories.Catinfo;
import kr.co.adflow.nms.web.vo.categoryDetail.CategoryInfo;
import kr.co.adflow.nms.web.vo.categoryDetail.CategoryInfoList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DashBoardService {

	private static final Logger logger = LoggerFactory
			.getLogger(DashBoardService.class);

	public String categoryJsonXml() throws HandleException {
		String result = null;
		try {

			JAXBContext jc = JAXBContext.newInstance(Catinfo.class);
			Unmarshaller u = jc.createUnmarshaller();
			File f = new File("c:\\temp\\categories.xml");
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
			logger.debug("aaaaaa:::");
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

	public String getCategoryIdLabelIp(String categorygroup)
			throws HandleException {

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
					sql = "SELECT node.nodeid, node.nodelabel, ipAddr,serviceid FROM node, "
							+ "ifservices WHERE node.nodeid = ifservices.nodeid and serviceid in ("
							+ categorygroup
							+ ") and node.nodeid in "
							+ "(SELECT nodeid FROM ifservices WHERE status != 'D' AND serviceid in ("
							+ categorygroup + "))";

					logger.debug("sql:::" + sql);
					rst = stmt.executeQuery(sql);
					while (rst.next()) {

						result.append(rst.getInt(1) + ",");
						result.append(rst.getString(2) + ",");
						result.append(rst.getString(3) + ",");
						result.append(rst.getInt(4) + "^");
					}

					rst.close();

					result.deleteCharAt(result.length() - 1);

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

	public CategoryInfoList getCategoryNodeIdServiceID(String categorygroup)
			throws HandleException {

		StringBuffer result = new StringBuffer();
		CategoryInfoList nodeInfoList = new CategoryInfoList();
		Statement stmt = null;
		ResultSet rst = null;
		Connection conn = null;
		String sql = null;
		ArrayList arrNodeID = new ArrayList();
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
					sql = "SELECT node.nodeid FROM node, "
							+ "ifservices WHERE node.nodeid = ifservices.nodeid and serviceid in ("
							+ categorygroup
							+ ") and node.nodeid in "
							+ "(SELECT nodeid FROM ifservices WHERE status != 'D' AND serviceid in ("
							+ categorygroup + "))";

					logger.debug("sql:::" + sql);
					rst = stmt.executeQuery(sql);

					while (rst.next()) {
						CategoryInfo info=new CategoryInfo();
						result.append(rst.getInt(1));
						info.setNodeId(rst.getInt(1));
						nodeInfoList.getnodeInfo().add(info);
					}
					
				}

				rst.close();

				result.deleteCharAt(result.length() - 1);

				logger.debug("ResultSet Json:::" + result.toString());
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

		return nodeInfoList;

	}

	public String serviceAvailability(int nodeId, String ipAddr,
			ArrayList<Integer> serviceIds) throws HandleException {
		if (ipAddr == null) {
			throw new IllegalArgumentException("Cannot take null parameters.");
		}

		Calendar cal = new GregorianCalendar();
		Date now = cal.getTime();
		cal.add(Calendar.DATE, -1);
		Date yesterday = cal.getTime();

		return serviceAvailability(nodeId, ipAddr, serviceIds, yesterday, now);
	}

	public String serviceAvailability(int nodeId, String ipAddr,
			ArrayList<Integer> serviceIds, Date start, Date end)
			throws HandleException {
		if (serviceIds == null || serviceIds.size() == 0) {
			throw new HandleException(
					"Cannot take nodeIds null or with length 0.");
		}
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

					sql.append("select serviceid, getPercentAvailabilityInWindow("
							+ nodeId
							+ ", '"
							+ ipAddr
							+ "', serviceId,'"
							+ endTime
							+ "','"
							+ startTime
							+ "')  as avail from ifservices, ipinterface where ifservices.ipaddr = ipinterface.ipaddr and ifservices.nodeid = ipinterface.nodeid and ipinterface.ismanaged='M' and ifservices.nodeid="
							+ nodeId
							+ " and ifservices.ipaddr='"
							+ ipAddr
							+ "' and serviceid in (");
					// ifservices.nodeid and serviceid in (21,18)

					Iterator<Integer> it = serviceIds.iterator();
					while (it.hasNext()) {
						sql.append(it.next());
						if (it.hasNext()) {
							sql.append(", ");
						}
					}
					sql.append(")");

					logger.debug("sql:::" + sql.toString());
					rst = stmt.executeQuery(sql.toString());

					result.append("{\"serviceAvailability\":[");
					while (rst.next()) {

						result.append("{\"serviceId\":\"" + rst.getInt(1)
								+ "\",");
						result.append("\"avail\":\"" + rst.getDouble(2)
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

	public String nodeAvailability(ArrayList nodeIds,CategoryInfoList nodeinfoList) throws HandleException {
		Calendar cal = new GregorianCalendar();
		Date now = cal.getTime();
		cal.add(Calendar.DATE, -1);
		Date yesterday = cal.getTime();

		return nodeAvailability(nodeIds, yesterday, now,nodeinfoList);
	}

	public String nodeAvailability(ArrayList nodeIds, Date start, Date end,CategoryInfoList nodeinfoList)
			throws HandleException {
		if (nodeIds == null || nodeIds.size() == 0) {
			throw new IllegalArgumentException(
					"Cannot take nodeIds null or with length 0.");
		}
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

					sql.append("select nodeid, getManagePercentAvailNodeWindow(nodeid, '"
							+ endTime
							+ "','"
							+ startTime
							+ "')  from node where nodeid in (");
					Iterator<Integer> it = nodeIds.iterator();
					while (it.hasNext()) {
						sql.append(it.next());
						if (it.hasNext()) {
							sql.append(", ");
						}
					}
					sql.append(")");

					logger.debug("sql:::" + sql.toString());
					rst = stmt.executeQuery(sql.toString());

					result.append("{\"nodeAvailability\":[");
					while (rst.next()) {
						 CategoryInfo info=new CategoryInfo();
						result.append("{\"nodeid\":\"" + rst.getInt(1) + "\",");
						result.append("\"avail\":\"" + rst.getDouble(2)
								+ "\"},");
						info.setAvailabili(rst.getDouble(2));
						nodeinfoList.getnodeInfo().add(info);
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

}