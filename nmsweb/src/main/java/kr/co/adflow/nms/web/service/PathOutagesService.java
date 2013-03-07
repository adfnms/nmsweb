package kr.co.adflow.nms.web.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.co.adflow.nms.web.DefaultHandlerImpl;
import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.HandlerFactory;
import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.vo.PathOutage;

import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * AcknowledgementsProcess
 * 
 * @author kicho@adflow.co.kr
 * @version 1.1
 */
@Service
public class PathOutagesService {

	private static final String ContentType = "contentType";
	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String Accept = "accept";
	private @Value("#{config['NMSURL']}") String ipAddr;
//	private @Value("#{config['LOGINID']}") String loginId;
//	private @Value("#{config['LOGINPASS']}") String loginPass;
	private static final Logger logger = LoggerFactory
			.getLogger(PathOutagesService.class);

	@Autowired
	private Handler handler;

	public String pathOutages() throws HandleException {

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
					sql = "SELECT nodeid, criticalpathip, criticalpathservicename  FROM pathoutage";

					logger.debug("sql:::" + sql);
					rst = stmt.executeQuery(sql);

					result.append("{\"pathOutage\":[");
					while (rst.next()) {

						result.append("{\"nodeid\":\"" + rst.getInt(1) + "\",");
						result.append("\"criticalpathip\":\""
								+ rst.getString(2) + "\",");
						result.append("\"criticalpathservicename\":\""
								+ rst.getString(3) + "\"},");

					}

					rst.close();

					// last "&" delete.
					result.deleteCharAt(result.length() - 1);
					result.append("]}");
					logger.debug("ResultSet Json:::" + result.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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

	public String pathOutagesDelete(String nodeId) throws HandleException {

		StringBuffer result = new StringBuffer();

		Statement stmt = null;
		int rc = 0;
		Connection conn = null;
		String sql = null;

		try {
			Context ctx = new InitialContext();
			if (ctx == null)
				throw new HandleException("No Context");

			// /jdbc/postgres is the name of the resource above
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/postgres");
			if (ds != null) {
				conn = ds.getConnection();

				if (conn != null) {
					stmt = conn.createStatement();

					sql = "DELETE FROM pathoutage WHERE nodeid='" + nodeId
							+ "'";

					logger.debug("sql:::" + sql);
					rc = stmt.executeUpdate(sql);

					logger.debug("sql Return Code:::" + rc);
					result.append("{\"result\" : \"success\"}");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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

	public String pathOutagesPut(List<PathOutage> pathOutageList)
			throws HandleException {

		StringBuffer result = new StringBuffer();

		Statement stmt = null;
		int rc = 0;
		Connection conn = null;
		String sql = null;

		Iterator<PathOutage> it = pathOutageList.iterator();

		while (it.hasNext()) {

			try {
				PathOutage pathOutage = (PathOutage) it.next();
				String nodeId = pathOutage.getNodeid();
				String criticalpathip = pathOutage.getCriticalpathip();
				String criticalpathservicename = pathOutage
						.getCriticalpathservicename();

				Context ctx = new InitialContext();
				if (ctx == null)
					throw new HandleException("No Context");

				// /jdbc/postgres is the name of the resource above
				DataSource ds = (DataSource) ctx
						.lookup("java:comp/env/jdbc/postgres");
				if (ds != null) {
					conn = ds.getConnection();

					if (conn != null) {
						stmt = conn.createStatement();

						// set은 delete 후 insert 함(opennms logic임)
						sql = "DELETE FROM pathoutage WHERE nodeid='" + nodeId
								+ "'";

						logger.debug("sql:::" + sql);
						rc = stmt.executeUpdate(sql);

						sql = "INSERT INTO pathoutage(nodeid, criticalpathip, criticalpathservicename) VALUES ('"
								+ nodeId
								+ "', '"
								+ criticalpathip
								+ "', '"
								+ criticalpathservicename + "')";

						logger.debug("sql:::" + sql);
						// rc=0 faile, rc=1 success
						rc = stmt.executeUpdate(sql);

						logger.debug("sql Return Code:::" + rc);

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

		result.append("{\"result\" : \"success\"}");

		return result.toString();
	}

}
