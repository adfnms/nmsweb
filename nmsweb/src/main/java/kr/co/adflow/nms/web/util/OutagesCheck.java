package kr.co.adflow.nms.web.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.co.adflow.nms.web.exception.HandleException;
import kr.co.adflow.nms.web.service.DashBoardService;
import kr.co.adflow.nms.web.vo.Outage;
import kr.co.adflow.nms.web.vo.categoryDetail.CategoryInfo;
import kr.co.adflow.nms.web.vo.categoryDetail.CategoryInfoList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 
 * 
 * @author kicho@adflow.co.kr
 * @version 1.1
 */

public class OutagesCheck extends TimerTask{

	

	private static final Logger logger = LoggerFactory
			.getLogger(OutagesCheck.class);

	Timer timer;
	DashBoardService service;
	
	
	public OutagesCheck() {
	}


	public OutagesCheck(Timer timer, DashBoardService service){
		this.timer = timer;
		this.service = service;
	}
	
	
	@Override
	public void run() {
		
//		logger.debug("outageCheck start");

		Statement stmt = null;
		ResultSet rst = null;
		Connection conn = null;
		String sql = null;
		
		
		StringBuffer result = new StringBuffer();
		
		Hashtable<String, CategoryInfoList> CateGoryTable ;
		Hashtable<String, Outage> outageList;
		

		try {
			
			CateGoryTable = service.getCategoryMain().getCateGoryTable();
			
		} catch (Exception e) {
			logger.debug("Exception  "+e.toString());
			
			return;
		}
		
		
		try {
			
			outageList = service.getOutageList();
			
		} catch (Exception e) {
			logger.debug("Exception outageList "+e.toString());
			
			return;
		}
		
		Hashtable<String, Outage> oldOutageList = null;
		Hashtable<String, Outage> newOutageList = null;
		
		
		

		try {
			
//			logger.debug("outageList  "+outageList);
			
			if (outageList == null) {
				
				

				
			} else {
				
				oldOutageList = new Hashtable(outageList);
				newOutageList = new Hashtable<String, Outage>();
				
//				logger.debug("outageCheck sql ");
				
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

//							logger.debug("sql:::" + sql);
							rst = stmt.executeQuery(sql);

							while (rst.next()) {
								
								Outage tempOutage = new Outage();
								tempOutage.setNodeid(rst.getInt(1));
								tempOutage.setIpaddr(rst.getString(2));
								tempOutage.setServiceid(rst.getInt(3));
								tempOutage.setOutageid(rst.getInt(4));
								tempOutage.setIflostservice(rst.getString(5));
								String key = String.valueOf(tempOutage.getNodeid()) + ":"+tempOutage.getIpaddr()+":"+String.valueOf(tempOutage.getServiceid());
								
//								logger.debug("Outage key :::" + key);
								
								if (oldOutageList.containsKey(key)) {
									
									oldOutageList.remove(key);
									
								} else {
									newOutageList.put(key, tempOutage);

								}
								
							}
							rst.close();
							
//							logger.debug("outageCheck sql ");
							
							// Outage delete
							if(oldOutageList.size()>0){
								
								logger.debug("oldOutageList size :::; "+ oldOutageList.size());
								
								Set oldKetSet = oldOutageList.keySet();
								
								Iterator it = oldKetSet.iterator();
								
								while (it.hasNext()) {
									String oldKey = (String) it.next();
									
									Outage tempOldOutage = oldOutageList.get(oldKey);
									
									String key = tempOldOutage.getNodeid()+":"+tempOldOutage.getIpaddr()+":"+tempOldOutage.getServiceid();
									
									//Outage 정보 수정
									Set categorySet = CateGoryTable.keySet();
									
									Iterator categoryIt = categorySet.iterator();
									
									while (categoryIt.hasNext()) {
										String categoryKey = (String) categoryIt.next();
										
										CategoryInfoList tempOldCategoryInfoList = CateGoryTable.get(categoryKey);
										

										//category에 해당 service가 있는지 확인 후 있으면 outage수를 -1함.
										String serviceIds = tempOldCategoryInfoList.getServiceids();
										
										if (serviceIds != null) {
											
											if (serviceIds.contains(String.valueOf(tempOldOutage.getServiceid()))) {
												
												logger.debug("outagedelete :::; "+ categoryKey);
												
												Hashtable<Integer, CategoryInfo> tempCateGoryInfo = tempOldCategoryInfoList.getCateGoryInfo();
												
												logger.debug("getNodeid :::; "+ tempOldOutage.getNodeid());
												
												if (tempCateGoryInfo.containsKey(tempOldOutage.getNodeid())) {
													
													
													
													CategoryInfo tempCategoryInfo2 = tempCateGoryInfo.get(tempOldOutage.getNodeid());
													
													int tempOutageCount = tempCategoryInfo2.getOutageCount();
//													int tempOutageCount = tempCateGoryInfo.get(tempOldOutage.getNodeid()).getOutageCount();
													tempOutageCount--;
													tempCateGoryInfo.get(tempOldOutage.getNodeid()).setOutageCount(tempOutageCount);
													
													
													int tempOutageTotalCount = tempOldCategoryInfoList.getOutageTotalCount();
													tempOutageTotalCount--;
													tempOldCategoryInfoList.setOutageTotalCount(tempOutageTotalCount);
													
												}
												
												
												
											}
											
										}
										
										
										



									}
									
									
									if (outageList.containsKey(oldKey)) {
										outageList.remove(oldKey);
									}
									
								}
								
							}
							
							
							// Outage add
							if(newOutageList.size()>0){
								
								logger.debug("newOutageList size :::; "+ newOutageList.size());
								
								Set newKetSet = newOutageList.keySet();
								
								Iterator it = newKetSet.iterator();
								
								while (it.hasNext()) {
									String newKey = (String) it.next();
									
									Outage tempNewOutage = newOutageList.get(newKey);
									
									//Outage 정보 수정
									Set categorySet = CateGoryTable.keySet();
									
									Iterator categoryIt = categorySet.iterator();
									
									while (categoryIt.hasNext()) {
										String categoryKey = (String) categoryIt.next();
										
										CategoryInfoList tempNewCategoryInfoList = CateGoryTable.get(categoryKey);
										

										//category에 해당 service가 있는지 확인 후 있으면 outage수를 +1함.
										
										String serviceIds = tempNewCategoryInfoList.getServiceids();
										
										
										
										if (serviceIds != null) {
											
											if (serviceIds.contains(String.valueOf(tempNewOutage.getServiceid()))) {
												
												logger.debug("outage ADD :::; "+ categoryKey);
												
												Hashtable<Integer, CategoryInfo> tempCateGoryInfo = tempNewCategoryInfoList.getCateGoryInfo();
												
												logger.debug("getNodeid :::; "+ tempNewOutage.getNodeid());
												
												if (tempCateGoryInfo.containsKey(tempNewOutage.getNodeid())) {
													
													CategoryInfo tempCategoryInfo2 = tempCateGoryInfo.get(tempNewOutage.getNodeid());
													
													int tempOutageCount = tempCategoryInfo2.getOutageCount();
//													int tempOutageCount = tempCateGoryInfo.get(tempOldOutage.getNodeid()).getOutageCount();
													tempOutageCount++;
													tempCateGoryInfo.get(tempNewOutage.getNodeid()).setOutageCount(tempOutageCount);
													
													int tempOutageTotalCount = tempNewCategoryInfoList.getOutageTotalCount();
													tempOutageTotalCount++;
													tempNewCategoryInfoList.setOutageTotalCount(tempOutageTotalCount);
													
												}
												
												
												
											}
											
										}
										
										
										
										
										

									}
									
									if (outageList.containsKey(newKey)) {
					
									}else{
										outageList.put(newKey,tempNewOutage);
									}
									
								}
								
								
								
							}
							
							
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
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

		} catch (Exception e) {
			logger.error(e.toString());
		} 


	}
	
}
