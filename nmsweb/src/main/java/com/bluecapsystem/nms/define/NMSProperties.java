package com.bluecapsystem.nms.define;

/**
 * config/*.properties 
 * @author kth
 * @version 0.1, build 2013/03/03
 * @since JDK1.6
 */
public class NMSProperties 
{	
	
	private static String driverClassName;
	private static String url;
	private static String username;
	private static String password;
	private static String nmswebVersion;
	
	
	public static String getNmswebVersion() {
		return nmswebVersion;
	}
	public void setNmswebVersion(String nmswebVersion) {
		NMSProperties.nmswebVersion = nmswebVersion;
	}
	public static String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		NMSProperties.driverClassName = driverClassName;
	}
	public static String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		NMSProperties.url = url;
	}
	public static String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		NMSProperties.username = username;
	}
	public static String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		NMSProperties.password = password;
	}
	

}