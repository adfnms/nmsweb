package kr.co.adflow.nms.web.process;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.co.adflow.nms.web.DefaultHandlerImpl;
import kr.co.adflow.nms.web.Handler;
import kr.co.adflow.nms.web.HandlerFactory;
import kr.co.adflow.nms.web.exception.HandleException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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
@Controller
public class PathOutagesProcess {

	private static final String ContentType = "contentType";
	private static final String METHOD = "method";
	private static final String URL = "url";
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String Accept = "accept";
	private static final String NMSUrl = "http://localhost:8980/opennms/rest";
//	private static final String NMSUrl = "http://112.223.76.78:8980/opennms/rest";
	private static final Logger logger = LoggerFactory
			.getLogger(PathOutagesProcess.class);

	private PathOutagesProcess() {
	}

	/**
	 * singleton
	 * 
	 */
	public static PathOutagesProcess process = new PathOutagesProcess();

	public static PathOutagesProcess getProcess() {
		return process;
	}
	
	public String pathOutages() throws HandleException {
		
		StringBuffer result = new StringBuffer();
		
		Statement stmt= null;
		ResultSet rst = null;
		Connection conn = null;
		
   		try
		{
			Context ctx = new InitialContext();
			if(ctx == null )	throw new Exception("Boom - No Context");
			
	
			// /jdbc/postgres is the name of the resource above 
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/postgres");  
			if (ds != null) 
			{
				conn = ds.getConnection();
	    
				if(conn != null) 
				{
					stmt = conn.createStatement();
					rst = stmt.executeQuery("SELECT nodeid, criticalpathip, criticalpathservicename  FROM pathoutage");
					
					result.append("{\"pathOutage\":[");
					if(rst.next()) {
						
						result.append("{\"nodeid\":\"" + rst.getInt(1) + "\",");
						result.append("\"criticalpathip\":\"" + rst.getString(2) + "\",");
						result.append("\"criticalpathservicename\":\"" + rst.getString(2) + "\"},");
						
					}
					
					
					// last "&" delete.
					result.deleteCharAt(result.length() - 1);
					result.append("]}");
					logger.debug("ResultSet Json:::" + result.toString());
				}
			}
		}catch(Exception e) 
		{
			e.printStackTrace();
		}finally {
            if ( stmt != null ) try { stmt.close();}catch(Exception e){}
            if ( conn != null ) try { conn.close();}catch(Exception e){}
        }


		return result.toString();
	}


}
