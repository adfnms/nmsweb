package kr.co.adflow.nms.web.service;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.push.Pusher;
import kr.co.adflow.nms.web.push.WebSocketHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * NodeProcess
 * 
 * @author kicho@adflow.co.kr
 * @version 1.2
 */
@Service
public class AvailabilityService {

	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String Accept = "accept";
	
	private @Value("#{config['NMSURL']}")
	String ipAddr;
	private @Value("#{config['LOGINID']}") String loginId;
	private @Value("#{config['LOGINPASS']}") String loginPass;
	
	private @Value("#{config['GROOVYPATH']}") String groovyPath;

	private static final Logger logger = LoggerFactory
			.getLogger(AvailabilityService.class);

	@Autowired
	private Handler handler;

	
    public String nodeAvailability(ArrayList<Integer> nodeIds) throws HandleException {
        Calendar cal = new GregorianCalendar();
        Date now = cal.getTime();
        cal.add(Calendar.DATE, -1);
        Date yesterday = cal.getTime();

        return nodeAvailability(nodeIds, yesterday, now);
    }    
    
    public String nodeAvailability(ArrayList<Integer> nodeIds, Date start, Date end) throws HandleException {
    	if(nodeIds==null || nodeIds.size()==0){
    		throw new IllegalArgumentException("Cannot take nodeIds null or with length 0.");
    	}
        if (start == null || end == null) {
            throw new IllegalArgumentException("Cannot take null parameters.");
        }

        if (end.before(start)) {
            throw new IllegalArgumentException("Cannot have an end time before the start time.");
        }

        if (end.equals(start)) {
            throw new IllegalArgumentException("Cannot have an end time equal to the start time.");
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
					
					sql.append("select nodeid, getManagePercentAvailNodeWindow(nodeid, '"+endTime+"','"+ startTime +"')  from node where nodeid in (");
					Iterator<Integer> it = nodeIds.iterator();
					while (it.hasNext()){
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

						result.append("{\"nodeid\":\"" + rst.getInt(1) + "\",");
						result.append("\"avail\":\""
								+ rst.getDouble(2) + "\"},");

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
    
    public String interfaceAvailability(int nodeId, ArrayList<String> ipAddr) throws HandleException {
        if (ipAddr == null) {
            throw new IllegalArgumentException("Cannot take null parameters.");
        }

        Calendar cal = new GregorianCalendar();
        Date now = cal.getTime();
        cal.add(Calendar.DATE, -1);
        Date yesterday = cal.getTime();

        return interfaceAvailability(nodeId, ipAddr, yesterday, now);
    }
    

    public String interfaceAvailability(int nodeId, ArrayList<String> ipAddrs, Date start, Date end) throws HandleException {
    	if(ipAddrs==null || ipAddrs.size()==0){
    		throw new HandleException("Cannot take nodeIds null or with length 0.");
    	}
        if (start == null || end == null) {
            throw new HandleException("Cannot take null parameters.");
        }

        if (end.before(start)) {
            throw new HandleException("Cannot have an end time before the start time.");
        }

        if (end.equals(start)) {
            throw new HandleException("Cannot have an end time equal to the start time.");
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
					
					sql.append("select ipAddr, getManagePercentAvailIntfWindow("+nodeId+", ipAddr, '"+endTime+"','"+ startTime +"')  from ipinterface where ipAddr in ('");
					Iterator<String> it = ipAddrs.iterator();
					while (it.hasNext()){
						sql.append(it.next());
		        		if (it.hasNext()) {
		        			sql.append("', '");
		        		}
		        	}
					sql.append("')");

					logger.debug("sql:::" + sql.toString());
					rst = stmt.executeQuery(sql.toString());

					result.append("{\"interfaceAvailability\":[");
					while (rst.next()) {

						result.append("{\"ipAddr\":\"" + rst.getString(1) + "\",");
						result.append("\"avail\":\""
								+ rst.getDouble(2) + "\"},");

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
    
    
    public String serviceAvailability(int nodeId, String ipAddr, ArrayList<Integer> serviceIds) throws HandleException {
        if (ipAddr == null) {
            throw new IllegalArgumentException("Cannot take null parameters.");
        }

        Calendar cal = new GregorianCalendar();
        Date now = cal.getTime();
        cal.add(Calendar.DATE, -1);
        Date yesterday = cal.getTime();

        return serviceAvailability(nodeId, ipAddr, serviceIds, yesterday, now);
    }
    
    public String serviceAvailability(int nodeId, String ipAddr, ArrayList<Integer> serviceIds, Date start, Date end) throws HandleException {
    	if(serviceIds==null || serviceIds.size()==0){
    		throw new HandleException("Cannot take nodeIds null or with length 0.");
    	}
        if (start == null || end == null) {
            throw new HandleException("Cannot take null parameters.");
        }

        if (end.before(start)) {
            throw new HandleException("Cannot have an end time before the start time.");
        }

        if (end.equals(start)) {
            throw new HandleException("Cannot have an end time equal to the start time.");
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
					
					sql.append("select serviceid, getPercentAvailabilityInWindow("+nodeId+", '"+ipAddr+"', serviceId,'"+endTime+"','"+ startTime +"')  as avail from ifservices, ipinterface where ifservices.ipaddr = ipinterface.ipaddr and ifservices.nodeid = ipinterface.nodeid and ipinterface.ismanaged='M' and ifservices.nodeid="+nodeId+" and ifservices.ipaddr='"+ipAddr+"' and serviceid in (");
					Iterator<Integer> it = serviceIds.iterator();
					while (it.hasNext()){
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

						result.append("{\"serviceId\":\"" + rst.getInt(1) + "\",");
						result.append("\"avail\":\""
								+ rst.getDouble(2) + "\"},");

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
