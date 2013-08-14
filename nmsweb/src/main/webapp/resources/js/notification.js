
//2013-08-12
/*POST Adjust the information related with real occured, certain event*/
/*reg Notification obj*/

function requestBodyStr (uei,name,description,subject,numericMessage,textMessage,destinationPath,status,rule,noticeQueue){
 		
 		var str ="{\"name\":\""+name+"\",\"description\": \""+description+"\"," + 
 				"\"subject\": \""+subject+"\",\"status\": \""+status+"\"," +
 				"\"parameter\": [],\"rule\": \""+rule+"\"," +
 				"\"varbind\": {" + 
 				"\"vbname\":\"\",\"vbvalue\": \"\"}," +
 				"\"writeable\":\"yes\",\"uei\": \""+uei+"\"," +
 				"\"noticeQueue\": \""+noticeQueue+"\",\"destinationPath\": \""+destinationPath+"\"," +
 				"\"textMessage\": \""+textMessage+"\",\"numericMessage\": \""+numericMessage+"\"," +
 				"\"eventSeverity\": \"\"}";
 		
 		console.log(str);
 		
 		return str;
 	}

/**
 * /v1/notifications/destinationPaths/TESTPath5
 * get path Name str
 */
function getPathName(name,initialDelay,userInterval,userName,userAutoNotify,userCommand,
					groupInterval,groupName,groupAutoNotify,groupCommand,
					roleInterval,roleName,roleAutoNotify,roleCommand,
					emailInterval,emailCommand,emailAutoNotify,email){
	
	var str = "	{\"name\": \""+name+"\",\"initial-delay\": \""+initialDelay+"\", "+
			  "	\"target\":[{\"interval\": \""+userInterval+"\",\"name\": \""+userName+"\",\"autoNotify\": \""+userAutoNotify+"\",\"command\": \""+userCommand+"\"},"+
						   "{\"interval\": \""+groupInterval+"\",\"name\": \""+groupName+"\",\"autoNotify\": \""+groupAutoNotify+"\",\"command\": \""+groupCommand+"\"},"+
						   "{\"interval\": \""+roleInterval+"\",\"name\": \""+roleName+"\",\"autoNotify\": \""+roleAutoNotify+"\",\"command\": \""+roleCommand+"\"},"+
						   "{\"interval\": \""+emailInterval+"\",\"name\": \""+email+"\",\"autoNotify\": \""+emailAutoNotify+"\",\"command\": \""+emailCommand+"\"}]}"; 
	
	console.log(str);
	
	
	
	
/*	
	var	str="		{\"escalate\": [],										"+
			"		 \"name\": \""+name+"\",								"+
			"		\"target\": [											"+
			" 			{													"+
			"      	    \"interval\": \""+userInterval+"\",					"+
			"				\"command\": [									"+
			"              \""+userCommand+"\"								"+
			"				],												"+
			"           	\"autoNotify\": \""+userAutoNotify+"\",			"+
			"           	\"name\": \""+userName+"\"						"+
			"       	},													"+
			"       	{													"+
			"				\"interval\": \""+groupInterval+"\",			"+
			"				\"command\": [									"+
			"				\""+groupCommand+"\"							"+
			"				],												"+
			"				\"autoNotify\": \""+groupAutoNotify+"\",		"+
			"				\"name\": \""+groupName+"\"						"+
			"			},													"+
			"			{													"+
			"				\"interval\": \""+roleInterval+"\",				"+
			"				\"command\": [									"+
			"				\""+roleCommand+"\"								"+
			"				],												"+
			"				\"autoNotify\": \""+roleAutoNotify+"\",			"+
			"				\"name\": \""+roleName+"\"						"+
			"			},													"+
			"			{													"+
			"				\"interval\": \""+emailInterval+"\",			"+
			"				\"command\": [									"+
			"				\""+emailCommand+"\"							"+
			"				],												"+
			"				\"autoNotify\": \""+emailAutoNotify+"\",		"+
			"				\"name\": \""+email+"\"							"+
			"			}													"+
			"		],														"+
			"		\"initialDelay\": \""+initialDelay+"\"					"+
			"	}";
*/	
	return str;
	
}


//Return notification list related with given userName
function getNotoficationList(callback, userId ,data){
	//console.log('/' + version + '/notifications/searchUser/'+userId+"?"+data);
	$.ajax({
		type : 'get',
		url : '/' + version + '/notifications/searchUser/'+userId,
		//url :'/v1/notifications/searchUser/admin?pagetime=2013-03-20T06:22:43.467-04:00&limit=2',
		data : data,
		contentType: "application/json;charset=UTF-8", 
		dataType : 'json',
		error : function(data) {
			//alert('나의 공지 리스트 가져오기 서비스 실패');
			if (typeof callback == "function") {
				//console.log(data);
				callback(data);
			}
		},
		success : function(data) {
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
}
/**
 * Return list of outstanded notifications
 * @param callback
 * @param data
 */
//Get total list of notification
function getTotalNotoficationList(callback , data){
	//console.log('http://192.168.0.5:8081/' + version + '/notifications/allOutstand/?'+data);
	$.ajax({
		type : 'get',
		url : '/' + version + '/notifications/allOutstand',
		dataType : 'json',
		data : data,
		contentType : "application/json;charset=UTF-8", 
		error : function(data) {
			//console.log(data);
			if (typeof callback == "function") {
				callback(data);
			}
			//alert('전체 공지 리스트 가져오기 서비스 실패');
		},
		success : function(data) {
			// 콜백함수
			
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
	
}
/**
 * Get the notification specified by the certain ID
 * @param callback
 * @param notifyid
 * only notofication
 */
function getNotofication(callback , notifyid){
	//console.log('http://192.168.0.5:8081/' + version + '/notifications/?'+notifyid);
	$.ajax({
		type : 'get',
		url : '/' + version + '/notifications/'+notifyid,
		dataType : 'json',
		contentType : "application/json;charset=UTF-8", 
		error : function(data) {
			//console.log(data);
			alert('상세 공지 가져오기 서비스 실패');
		},
		success : function(data) {
			// 콜백함수
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
	
}
/**
 * Get the notification specified by the certain ID
 * @param callback
 * @param notifyid
 * only destination.
 */
function gedestination(callback , notifyid){
	//console.log('http://192.168.0.5:8081/' + version + '/notifications/?'+notifyid);
	$.ajax({
		type : 'get',
		url : '/' + version + '/notifications/'+notifyid,
		dataType : 'json',
		contentType : "application/json;charset=UTF-8", 
		error : function(data) {
			//console.log(data);
			alert('상세 공지 가져오기 서비스 실패');
		},
		success : function(data) {
			// 콜백함수
			if (typeof callback == "function") {
				
				callback(data);
			}
		}
	});
	
}
/**
 *  GETConfirm kind of registered all event 
 * @param callback
 */
function getEventUei(callback){
	//console.log('http://192.168.0.5:8081/' + version + '/notifications/events');
	//console.log('http://localhost:8080/' + version + '/notifications/events');
	$.ajax({
		type : 'get',
		url : '/' + version + '/notifications/events',
		dataType : 'json',
		contentType : "application/json",
		accept : "application/json",
		error : function(data) {
			console.log(data);
			
			alert('이벤트 리스트 가져오기 서비스 실패');
		},
		success : function(data) {
			// 콜백함수
			//console.log(data);
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
	
}
function getPathList(callback){
	console.log('http://192.168.0.37:8080/' + version + '/notifications/destinationPaths');
	console.log('http://localhost:8080/' + version + '/notifications/destinationPaths');
	$.ajax({
		type : 'get',
		url : '/' + version + '/notifications/destinationPaths',
		dataType : 'json',
		contentType : "application/json",
		accept : "application/json",
		error : function(data) {
			console.log(data);
			
			alert('destinationPaths 리스트 가져오기 서비스 실패');
		},
		success : function(data) {
			// 콜백함수
			//console.log(data);
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
	
}

function getAddedNoti(callback){
	console.log('http://192.168.0.37:8080/' + version + '/notifications/destinationPaths');
	console.log('http://localhost:8080/' + version + '/notifications/destinationPaths');
	$.ajax({
		type : 'get',
		url : '/' + version + '/notifications/eventNotifications',
		dataType : 'json',
		contentType : "application/json",
		accept : "application/json",
		error : function(data) {
			console.log(data);
			
			alert('eventNotifications 리스트 가져오기 서비스 실패');
		},
		success : function(data) {
			// 콜백함수
				//console.log(data);
				
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
	
}


function modifyNoti(callback , name){
	
	$.ajax({
		
		type : 'get',
		url : '/' + version + '/notifications/eventNotifications/'+name,
		contentType : 'application/json',
		dataType:'json',
		async: false,
		error : function(data) {
			console.log(data);
			
			alert('Notification 정보 가져오기 서비스 실패');;
		},
		success : function(data) {
			// 콜백함수
			//console.log(data);
			
			if (typeof callback == "function") {
				callback(data);
			}
			
		}
	});
	
}

function getAllCommends(callback){
	
	$.ajax({
		type : 'get',
		url : '/' + version + '/notifications/commands',
		dataType : 'json',
		contentType : "application/json",
		accept : "application/json",
		error : function(data) {
			console.log(data);
			
			alert('commands 리스트 가져오기 서비스 실패');
		},
		success : function(data) {
			console.log(data);
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
	
}


function deletePathAjax(PathName){
	
	$.ajax({
			
		type : 'delete',
		url : '/' + version + '/notifications/destinationPaths/'+PathName,
		contentType : 'application/json',
		Accept : 'application/json', 
		error : function() {
			alert('Path 삭제 실패');
		},
		success : function(data) {
			
			$("#destinationPath").children().remove();
			$("#PathsTable").children().remove();
			getPathList(getPathsName);
			
		}
	}); 
		
}



function regNotificationAjax(PathName){
	
	$.ajax({
		
		type : 'put',
		url : '/' + version + '/notifications/eventNotifications',
		contentType : 'application/json',
		Accept : 'application/json', 
		data : str,
		error : function() {
			alert('공지 수정 실패');
		},
		success : function(data) {
			$(location).attr('href', "/v1/admin/setting/configureNotification.do");
		}
	}); 
		
}
function modifyPathAjax(PathName){
	$.ajax({
			type : 'get',
			url : '/' + version + '/notifications/destinationPaths/'+PathName,
			dataType : 'json',
			contentType : "application/json",
			accept : "application/json",
			error : function(data) {
				console.log(data);
				
				alert('PathName Info 가져오기 서비스 실패');
			},
			success : function(data) {
				console.log(data);
				modifyPathstr(data);
			}
		});
	
}

function registerSetPathAjax(){
	
	$.ajax({
			
			type : 'post',
			url : '/' + version + '/notifications/destinationPaths',
			contentType : 'application/json',
			Accept : 'application/json', 
			data : str,
			error : function() {
				alert('도착지 등록 실패');
			},
			success : function(data) {
				$("#destinationPath").children().remove();
				$("#PathsTable").children().remove();
				getPathList(getPathsName);
				//$(location).attr('href', "/v1/admin/setting/modifyNotification.do?name="+destiName);
			}
		}); 
	
	
	
	
}


function modifySetPathAjax(){
	
	$.ajax({
		
		type : 'put',
		url : '/' + version + '/notifications/destinationPaths',
		contentType : 'application/json',
		Accept : 'application/json', 
		data : str,
		error : function() {
			alert('도착지 수정 실패');
		},
		success : function(data) {
			$("#destinationPath").children().remove();
			$("#PathsTable").children().remove();
			getPathList(getPathsName);
			//$(location).attr('href', "/v1/admin/setting/modifyNotification.do?name="+destiName);
		}
	}); 
	
}



/**
 *Return notification list related with given userName
 * @param callback
 * @param userId
 * @param recentCount
 */
function getUserNotiList(callback , userId, nowDate, recentCount ){
	if(userId == null){
		
		alert("사용자 아이디가 없습니다.");
		
		return;
		
	}
	var filter ="pagetime="+nowDate+".467-04:00&"+recentCount;
	getNotoficationList(callback, userId ,filter );
		
}

/**
 *Return list of outstanded notifications
 * @param callback
 * @param userId
 * @param recentCount
 */
function getTotalNotiList(callback, nowDate, recentCount ){
	var filter ="pagetime="+nowDate+".510547-09&"+recentCount;
	getTotalNotoficationList(callback,filter);
		
}
/***************************************************************
 *  Get the notification specified by the certain ID 
 * @param callback
 * @param notifyid
 */
function getNotificaitionDetail(callback , notifyid){
		getNotofication(callback, notifyid );
		
}
function getdestinationDetail(callback , notifyid){
	
	gedestination(callback, notifyid );
	
}
/*********Get information related with real occured all event*****/
function getAddedNotifiList(callback){
	
	getAddedNoti(callback);
	
}


/* GETConfirm kind of registered all event Callback */
function getAllEvent(callback){
	
	getEventUei(callback);
	
}



/******************************************  view String edit  **************************************************/



//나의 공지 정보 가져오기
/**
 * 메뉴의 [Home] -> [알림 정보]란의 [나의 알림] 옆 [확인]을 클릭 시 새로 생성된 표의 내용들
 * @param jsonObj
 */
function userNotiListjsonObj(jsonObj) {
	//var str = "";
	/*********************************************************/
	var TRobj = $("<tr></tr>");
	var TDobj = $("<td></td>");
	var ATDobj = $("<td></td>");
	var DIVobj = $("<div></div>");
	var ADIVobj = $("<div></div>");
	var BDIVobj = $("<div></div>");
	var CDIVobj = $("<div></div>");
	var DDIVobj = $("<div></div>");
	var EDIVobj = $("<div></div>");
	var Aobj = $("<a></a>");
	var TBODYobj = $("<tbody></tbody>");
	var H4obj = $("<h4></h4>");
	/*********************************************************/
	var userObj = jsonObj["notifications"];
	
	console.log("------------userNotificationList----------");
	console.log(jsonObj);
	
	if(userObj.length >= 1){
		TBODYobj.append(
			TRobj.clone().append(
				TDobj.clone().append(
					H4obj.clone().text("ID")
				),
				TDobj.clone().append(
					H4obj.clone().text("EventID")
				),
				TDobj.clone().append(
					H4obj.clone().text("Status")
				),
				TDobj.clone().append(
					H4obj.clone().text("PageTime")
				),
				TDobj.clone().append(
					H4obj.clone().text("Message")
				)
			)	
		);
		for ( var i in  userObj){
			/************************Get @severity  from event****************************/
			var eventId = userObj[i]["eventid"];
			
			var statusProgress = "";
			var stat = "";
			$.ajax({
				type : 'get',
				url : '/' + version + '/events',
				dataType : 'json',
				data : encodeURI("query=this_.eventId  = '"+eventId+"'"),
				async: false,
				contentType : "application/json;charset=UTF-8", 
				error : function(data) {
					//console.log(data);
					alert('심각도 가져오기 서비스 실패');
				},
				success : function(data) {
					stat = data["event"]["@severity"];
					
					if(stat=="CRITICAL"){
						 statusProgress = "progress-danger";
					}
					else if(stat=="MAJOR"){
						 statusProgress = "progress-caution";						
					}
					else if(stat=="MINOR"){
						 statusProgress = "progress-warning";
					}
					else if(stat=="WARNING"){
						 statusProgress = "progress-gray";
					}
					else if(stat=="NORMAL"){
						 statusProgress = "progress-info";
					}
					else if(stat=="CLEARED"){
						 statusProgress = "progress";
					}
					else if(stat=="INDETERMINATE"){
						 statusProgress = "progress-success";
					}
				}
				
			});
			
			/************************Get @severity  from event****************************/
			/************************************************************************************************************************************************************************/
			TBODYobj.append(
				TRobj.clone().append(
					TDobj.attr("class", "span1").clone().append(
						Aobj.attr("class", "accordion-toggle").attr("data-toggle","collapse").attr("data-parent","#accordion2").attr("href", "#" + userObj[i]["notifyid"]).attr("onclick", "showNotiInfoDiv(" + userObj[i]["notifyid"] + "," + userObj[i]["eventid"] + ")").clone().text(userObj[i]["notifyid"])	
					),
					TDobj.attr("class", "span1").clone().append(
						Aobj.attr("class", "accordion-toggle").attr("data-toggle","collapse").attr("data-parent","#accordion2").attr("href", "#" + userObj[i]["notifyid"]).attr("onclick", "showEventInfoDiv(" + userObj[i]["notifyid"] + "," + userObj[i]["eventid"] + ")").clone().text(userObj[i]["eventid"])	
					),
					TDobj.attr("class", "span2").clone().append(
						DIVobj.attr("class", "progress progress-striped active  " + statusProgress + "").attr("style", "margin-bottom: 0px;width: 130px;").clone().append(
							ADIVobj.attr("class", "bar").attr("style", "width:100%").clone().text(stat)
						)	
					),
					TDobj.attr("class", "span2").clone().text(new Date(userObj[i]["pagetime"]).format('yy-MM-dd hh:mm:ss')),
					TDobj.attr("class", "span6").clone().text(userObj[i]["textmsg"])
				),
				TRobj.clone().append(
					ATDobj.attr("colspan", "5").clone().append(
						BDIVobj.attr("class", "accordion-group").clone().append(
							CDIVobj.attr("id", userObj[i]["notifyid"]).attr("class", "accordion-body collapse").clone().append(
								DDIVobj.attr("class", "accordion-inner").clone().append(
									EDIVobj.attr("class", "row-fluid").clone().text("")
								)
							)
						)
					)
				)
			);
			/************************************************************************************************************************************************************************/
			/*str += "<tr>";
			//str += "	<td class=\"span1\"><a href='/"+version+"/admin/setting/notificationDetali.do?notifyid="+userObj[i]["notifyid"]+"&eventId="+userObj[i]["eventid"]+"'>";
		//	str += "	<td class=\"span1\"><a href='javascript:goNotiInfo("+userObj[i]["notifyid"]+","+userObj[i]["eventid"]+")'>";
			str += "	<td class=\"span1\"><a class=\"accordion-toggle\" data-toggle=\"collapse\" data-parent=\"#accordion2\"  href=\"#"+userObj[i]["notifyid"]+"\" onclick=\"showNotiInfoDiv("+userObj[i]["notifyid"]+","+userObj[i]["eventid"]+");\">";
			str += userObj[i]["notifyid"];											//notifyid
			str += "	</a></td>";
		//str += "	<td class=\"span1\"><a href='/"+version+"/search/event/eventDesc.do?eventId="+userObj[i]["eventid"]+"'>";										
			str += "	<td class=\"span1\"><a class=\"accordion-toggle\" data-toggle=\"collapse\" data-parent=\"#accordion2\"  href=\"#"+userObj[i]["notifyid"]+"\" onclick=\"showEventInfoDiv("+userObj[i]["notifyid"]+","+userObj[i]["eventid"]+");\">";	
			str += userObj[i]["eventid"];											//eventid
			str += "	</a></td>";
			str += '		<th class=""><div class="progress progress-striped active  '+statusProgress+' " style="margin-bottom: 0px;width: 130px; ">';
			str += '		<div class="bar" style="width:100%">' + stat + '</div>';
			str += '		</div></th>';
			str += "	<td class=\"span2\">";										
			str +=  new Date(userObj[i]["pagetime"]).format('yy-MM-dd hh:mm:ss');	//pagetime 
			str += "	</td>";														
			str += "	<td class=\"span6\" >";
			str += userObj[i]["textmsg"];											//textmsg
			str += "	</td>";
			str += "</tr>";
			
			str += "<tr><td colspan=\"5\">";
			str += "	<div class=\"accordion-group\">";
		//	str += "		<div class=\"accordion-heading\" style=\"height: 32px;\">";
		//	str += "			<h5><a class=\"accordion-toggle\" data-toggle=\"collapse\" data-parent=\"#"+userObj[i]["notifyid"]+"\"  href=\"#"+userObj[i]["notifyid"]+"\" id= StepOne>";
		//	str += "				Configuration&nbsp;Categories</a></h5>";
		//	str += "		</div>";
			str += "		<div id=\""+userObj[i]["notifyid"]+"\" class=\"accordion-body collapse \">";
			str += "			<div class=\"accordion-inner\">";
			//str += "				<div class=\"span12 well well-small\">";
			str += "					<div class=\"row-fluid\">";
			str += "						</div>";
			str += "					</div>";
			//str += "				</div>";
			str += "			</div>";
			str += "		</div>";
			str += "	</div>";
			str += "</td></tr>";*/
			}
	} else {
		/*************************************************************************************************************************************************************************************/
		/************************Get @severity  from event****************************/
		var eventId = userObj[i]["eventid"];
		
		var statusProgress = "";
		var stat = "";
		$.ajax({
			type : 'get',
			url : '/' + version + '/events',
			dataType : 'json',
			data : encodeURI("query=this_.eventId  = '"+eventId+"'"),
			async: false,
			contentType : "application/json;charset=UTF-8", 
			error : function(data) {
				//console.log(data);
				alert('심각도 가져오기 서비스 실패');
			},
			success : function(data) {
				stat = data["event"]["@severity"];
				
				if(stat=="CRITICAL"){
					 statusProgress = "progress-danger";
				}
				else if(stat=="MAJOR"){
					 statusProgress = "progress-caution";						
				}
				else if(stat=="MINOR"){
					 statusProgress = "progress-warning";
				}
				else if(stat=="WARNING"){
					 statusProgress = "progress-gray";
				}
				else if(stat=="NORMAL"){
					 statusProgress = "progress-info";
				}
				else if(stat=="CLEARED"){
					 statusProgress = "progress";
				}
				else if(stat=="INDETERMINATE"){
					 statusProgress = "progress-success";
				}
			}
			
		});
		/************************Get @severity  from event****************************/
		TBODYobj.append(
			TRobj.clone().append(
				TDobj.clone().append(
					H4obj.clone().text("ID")
				),
				TDobj.clone().append(
					H4obj.clone().text("EventID")
				),
				TDobj.clone().append(
					H4obj.clone().text("Status")
				),
				TDobj.clone().append(
					H4obj.clone().text("PageTime")
				),
				TDobj.clone().append(
					H4obj.clone().text("Message")
				)
			),
			TRobj.clone().append(
				TDobj.attr("class", "span1").clone().append(
					Aobj.attr("href", "/" + version + "/admin/setting/notificationDetali.do?notifyid=" + userObj["notifyid"] + "&eventId=" + userObj["eventid"]).clone().text(userObj["notifyid"])	
				),
				TDobj.attr("class", "span1").clone().append(
					Aobj.attr("href", "/" + version + "/search/event/eventDesc.do?eventId=" + userObj["eventid"]).clone().text(userObj["eventid"])	
				),
				TDobj.attr("class", "").clone().append(
					DIVobj.attr("class", "progress progress-striped active  " + statusProgress + "").attr("style", "margin-bottom: 0px;width: 130px;").clone().append(
						ADIVobj.attr("class", "bar").attr("style", "width:100%").clone().text(stat)
					)	
				),
				TDobj.attr("class", "span2").clone().text(new Date(userObj["pagetime"]).format('yy-MM-dd hh:mm:ss')),
				TDobj.attr("class", "span6").clone().text(userObj["textmsg"])
			)
		);
		/*************************************************************************************************************************************************************************************/
		/*str += "<tr>";
		str += "	<td class=\"span1\"><a href='/"+version+"/admin/setting/notificationDetali.do?notifyid="+userObj["notifyid"]+"&eventId="+userObj["eventid"]+"'>";										
		str += userObj["notifyid"];											//notifyid
		str += "	</a></td>";
		str += "	<td class=\"span1\"><a href='/"+version+"/search/event/eventDesc.do?eventId="+userObj["eventid"]+"'>";										
		str += userObj["eventid"];											//eventid
		str += "	</a></td>";
		//str += "<th class='"+stat.toLowerCase()+"'>" + stat + "</th>";			//condition
		str += '		<th class=""><div class="progress progress-striped active  '+statusProgress+' " style="margin-bottom: 0px;width: 130px; ">';
		str += '		<div class="bar" style="width:100%">' + stat + '</div>';
		str += '		</div></td>';
		str += "	<td class=\"span2\">";										
		str +=  new Date(userObj["pagetime"]).format('yy-MM-dd hh:mm:ss');	//pagetime 
		str += "	</td>";														
	//	str += "	<td class=\"span1\" ><a href='/"+version+"/search/node/interfaceDesc.do?nodeId="+userObj[i]["nodeid"]+"&intf="+nullCheckJsonObject(userObj[i]["notifications"],"interfaceid")+"'>";
	//	str += nullCheckJsonObject(userObj[i]["notifications"],"interfaceid");	//interface
	//	str += "	</a></td>";	
		str += "	<td class=\"span6\" >";
		str += userObj["textmsg"];											//textmsg
		str += "	</td>";
		str += "</tr>";*/
	}
	$("#userTable").append(TBODYobj);
}
//전체 공지 정보 가져오기
/**===============================================================================================2013-08-13
 * 메뉴의 [Home] -> [알림 정보]란의 [모든 알림] 옆 [확인]을 클릭 시 새로 생성된 표의 내용들
 * @param jsonObj
 */
function totalNotiListjsonObj(jsonObj) {
	
	var str = "";

	console.log(jsonObj);
	
	
	var userObj = jsonObj["notifications"];
	if(userObj.length >=1){
		for ( var i in userObj) {
			
			var eventId = userObj[i]["eventid"];
			var statusProgress = "";
			var stat = "";
			$.ajax({
				type : 'get',
				url : '/' + version + '/events',
				dataType : 'json',
				data : encodeURI("query=this_.eventId  = '"+eventId+"'"),
				async: false,
				contentType : "application/json;charset=UTF-8", 
				error : function(data) {
					//console.log(data);
					console.log(data);
					alert('심각도 가져오기 서비스 실패');
				},
				success : function(data) {
					stat = data["event"]["@severity"];
					if(stat=="CRITICAL"){
						 statusProgress = "progress-danger";
					}
					else if(stat=="MAJOR"){
						 statusProgress = "progress-caution";						
					}
					else if(stat=="MINOR"){
						 statusProgress = "progress-warning";
					}
					else if(stat=="WARNING"){
						 statusProgress = "progress-gray";
					}
					else if(stat=="NORMAL"){
						 statusProgress = "progress-info";
					}
					else if(stat=="CLEARED"){
						 statusProgress = "progress";
					}
					else if(stat=="INDETERMINATE"){
						 statusProgress = "progress-success";
					}
				}

			});
			
			
			str += "<tr>";
			
			//str += "	<td class=\"span1\"><a href='/"+version+"/admin/setting/notificationDetali.do?notifyid="+userObj[i]["notifyid"]+"&eventId="+userObj[i]["eventid"]+"'>";
			str += "	<td class=\"span1\"><a class=\"accordion-toggle\" data-toggle=\"collapse\" data-parent=\"#accordion2\"  href=\"#total"+userObj[i]["notifyid"]+"\" onclick=\"showTotalNotiInfoDiv("+userObj[i]["notifyid"]+","+userObj[i]["eventid"]+");\">";
			str += userObj[i]["notifyid"];											//notifyid
			str += "	</a></td>";
			//str += "	<td class=\"span1\"><a href='/"+version+"/search/event/eventDesc.do?eventId="+userObj[i]["eventid"]+"'>";	
			str += "	<td class=\"span1\"><a class=\"accordion-toggle\" data-toggle=\"collapse\" data-parent=\"#accordion2\"  href=\"#total"+userObj[i]["notifyid"]+"\" onclick=\"showTotalEventInfoDiv("+userObj[i]["notifyid"]+","+userObj[i]["eventid"]+");\">";
			str += userObj[i]["eventid"];											//eventid
			str += "	</a></td>";
			//str += "<th class='"+stat.toLowerCase()+"'>" + stat + "</th>";			//condition
			str += '		<td class=""><div class="progress progress-striped active  '+statusProgress+' " style="margin-bottom: 0px;width: 130px; ">';
			str += '		<div class="bar" style="width:100%">' + stat + '</div>';
			str += '		</div></td>';
			str += "	<td class=\"span2\">";										
			str +=  new Date(userObj[i]["pagetime"]).format('yy-MM-dd hh:mm:ss');	//pagetime 
			str += "	</td>";														
		//	str += "	<td class=\"span1\" ><a href='/"+version+"/search/node/interfaceDesc.do?nodeId="+userObj[i]["nodeid"]+"&intf="+nullCheckJsonObject(userObj[i]["notifications"],"interfaceid")+"'>";
		//	str += nullCheckJsonObject(userObj[i]["notifications"],"interfaceid");	//interface
		//	str += "	</a></td>";	
			str += "	<td class=\"span6\" >";
			str += userObj[i]["textmsg"];											//textmsg
			str += "	</td>";
			
			str += "</tr>";
			
			str += "<tr><td colspan=\"5\">";
			str += "	<div class=\"accordion-group\">";
			str += "		<div id=\"total"+userObj[i]["notifyid"]+"\" class=\"accordion-body collapse \">";
			str += "			<div class=\"accordion-inner\">";
			str += "					<div class=\"row-fluid\">";
			str += "						</div>";
			str += "					</div>";
			str += "			</div>";
			str += "		</div>";
			str += "	</div>";
			str += "</td></tr>";
			
		}
		
	}else{
		str += "<tr>";
		
		str += "	<td class=\"span1\"><a href='/"+version+"/admin/setting/notificationDetali.do?notifyid="+userObj["notifyid"]+"&eventId="+userObj["eventid"]+"'>";										
		str += userObj["notifyid"];											//notifyid
		str += "	</a></td>";
		str += "	<td class=\"span1\"><a href='/"+version+"/search/event/eventDesc.do?eventId="+userObj["eventid"]+"'>";										
		str += userObj["eventid"];											//eventid
		str += "	</a></td>";
		//str += "<th class='"+stat.toLowerCase()+"'>" + stat + "</th>";			//condition
		str += '		<th class=""><div class="progress progress-striped active  '+statusProgress+' " style="margin-bottom: 0px;width: 130px; ">';
		str += '		<div class="bar" style="width:100%">' + stat + '</div>';
		str += '		</div></td>';
		str += "	<td class=\"span2\">";										
		str +=  new Date(userObj["pagetime"]).format('yy-MM-dd hh:mm:ss');	//pagetime 
		str += "	</td>";														
	//	str += "	<td class=\"span1\" ><a href='/"+version+"/search/node/interfaceDesc.do?nodeId="+userObj[i]["nodeid"]+"&intf="+nullCheckJsonObject(userObj[i]["notifications"],"interfaceid")+"'>";
	//	str += nullCheckJsonObject(userObj[i]["notifications"],"interfaceid");	//interface
	//	str += "	</a></td>";	
		str += "	<td class=\"span6\" >";
		str += userObj["textmsg"];											//textmsg
		str += "	</td>";
		
		str += "</tr>";
		
	}
	$("#totalTable").append(str);
	
}
/*
 * 메뉴의 [Home] -> [알림 정보]란의 [모든 알림] 옆 [확인]을 클릭 시 새로 생성된 표의 내용들 중 [ID] 항목을 클릭 시 새로 생성된 삽입창의 내용들
 */
function notifiInfo(jsonObj){
	
	
	
	/*var notifiInfoStr = '<div class="row-fluid">'+
						'	<div class="span12">'+
						'		<h5>공지&nbsp;상세&nbsp;정보</h5>'+
						'	</div>'+
						'</div>'+*/
var notifiInfoStr ='<div class="row-fluid">'+
						'	<div class="span12 well well-small">'+
						'	<table class="table table-striped">'+
						'		<tr>'+
						'			<th>Notification Time</th>'+
						'			<td>'+new Date(jsonObj["pageTime"]).format('yy-MM-dd hh:mm:ss')+'</td>'+
						'			<th>노드</th>'+
						'			<td>'+
						'				<a href="/'+version+'/search/node/nodeDesc.do?nodeId='+jsonObj["nodeId"]+'">'+
											jsonObj["nodeLabel"]+
						'				</a>'+
						'			</td>'+
						'		</tr>'+
						'		<tr>'+
						'			<td>'+
						'				<a class="text-error" href="/'+version+'/admin/setting/notificationDetali/outages.do?nodeId='+jsonObj["nodeId"]+'&nodeLabel='+jsonObj["nodeLabel"]+'">See outages for '+
											jsonObj["nodeLabel"]+
						'				</a>'+
						'			</td>'+
						'			<th></th>'+
						'			<th>인터페이스</th>'+
						'			<td>'+
						'				<a href="/'+version+'/search/node/interfaceDesc.do?nodeId='+jsonObj["nodeId"]+'&intf='+jsonObj["ipAddress"]+'">'+
											jsonObj["ipAddress"]+
						'				</a>'+
						'			</td>'+
						'		</tr>'+
						'		<tr>'+
						'		</tr> '+
						'	</table>'+
						'	</div>'+
						'</div>'+
						'<div class="row-fluid">'+
						'<div class="span12  well well-small">'+
						'	<table class="table table-striped">'+
						'		<tr>'+
						'			<th>Uei</th>'+
						'			<td>'+jsonObj["uei"]+'</td>'+
						'		</tr>'+
						'		<tr>'+
						'			<th>numericMessage</th>'+
						'			<td>'+jsonObj["numericMessage"]+'</td>'+
						'		</tr>'+
						'		<tr>'+
						'			<th>textMessage</th>'+
						'			<td>'+jsonObj["textMessage"]+'</td>'+
						'		</tr>'+
						'	</table>'+
						'</div>'+
						'</div>';
	
	return notifiInfoStr;	

}

/*
 * 
 */ 
/*function destiInfo(jsonObj){
	
	var str = "";
	
	console.log("-----------destiInfo---------");	
	console.log(jsonObj);
	var destinationObj = jsonObj["destinations"]["destination"]; 
	
	 if(destinationObj.length > 1){
	
		for ( var i in  destinationObj){
			
			str += "<tr>";
			str += "	<td class=\"span1\">";										
			str += destinationObj[i]["userId"];												//userId
			str += "	</td>";
			str += "	<td class=\"span1\">";										
			str += new Date(destinationObj[i]["notifyTime"]).format('yy-MM-dd hh:mm:ss');	//notifyTime
			str += "	</td>";
			str += "	<td class=\"span1\">";										
			str += destinationObj[i]["media"];												//media
			str += "	</td>";													
			str += "</tr>";
			
			} 
		}else{
			str += "<tr>";
			str += "	<td class=\"span1\">";										
			str += destinationObj[0]["userId"];												//userId
			str += "	</td>";
			str += "	<td class=\"span1\">";										
			str += destinationObj[0]["notifyTime"];											//notifyTime
			str += "	</td>";
			str += "	<td class=\"span1\">";										
			str += destinationObj[0]["media"];												//media
			str += "	</td>";													
			str += "</tr>";
			
		}
	 $("#destiInfoDiv").append(str);
}*/


//메뉴의 [운영관리] -> [알림] -> [알림 설정] -> [수정]의 [event List]표의 내용
function getEventJsonObj(jsonObj){
	
	var str = "";

	var eventObj = jsonObj["event"]; 
	
	 if(eventObj.length > 1){
	
		for ( var i in  eventObj){
			
			str += "<tr>";
			str += "	<td class=\"span1\" onclick=\"javascript:setDestination('"+eventObj[i]["uei"]+","+eventObj[i]["event-label"]+"');\">";										
			str += "&nbsp;"+eventObj[i]["event-label"];												//event-label
			str += "	</td>";				
			str += "</tr>";
			
		}
	
	 $("#eventListTable").append(str);
}
}

//모든 이벤트 목록 select div 가져오기
/*function getEventSelectJsonObj(jsonObj){
	var str = "";

	var eventObj = jsonObj["event"]; 
	
	if(eventObj.length > 1){
	
		for ( var i in  eventObj){
			str += "<option value=\""+eventObj[i]["uei"]+"\" >"+eventObj[i]["event-label"]+"</option>";
		}
		
	}else{
		str += "<option value=\""+eventObj[0]["uei"]+","+eventObj[0]["event-label"]+"\">"+eventObj[0]["event-label"]+"</option>";	
	}
	
	 $("#uei").append(str);
}*/


//메뉴의 [운영관리] -> [알림] -> [알림 설정] -> [공지 추가] -> [2단계 공지 메시지 정의] -> [목적지관리] -> [Destination Configration] 클릭 시 새로 생성된 하단부의 [Existing Paths]창의 리스트
function pathsNameStr(jsonObj){
	
	var str = "";

	var pathsObj = jsonObj["path"];
	
	if(pathsObj.length > 1){
	
		for ( var i in pathsObj) {
			
			
			str += "<tr>";
			str += "	<td style=\"width: 330px;\">";
			str += pathsObj[i]["name"];
			str += "	</td>";
			str += "	<td>";
			str += "	<a type=\"button\" class=\"btn btn-warning accordion-toggle\" data-toggle=\"collapse\" data-parent=\"#accordion3\" onclick=\"javascript:modifyPath('"+pathsObj[i]["name"]+"') \" href=\"#collapseThree\">수정</a>";
			//str += "<a type=\"button\"  class=\"btn accordion-toggle btn-success\" href=\"#collapseTwo\" onclick=\"javascript:modifyPath('"+pathsObj[i]["name"]+"') \">수정</a>";
			str += "	</td>";
			str += "	<td>";
			str += "<a type=\"button\"  class=\"btn btn-danger\" onclick=\"javascript:deletePath('"+pathsObj[i]["name"]+"');\">삭제</a>";
			str += "	</td>";
			str += "</tr>";
			}
	}else{
		str += "<tr>";
		str += "	<td>";
		str += pathsObj[0]["name"];
		str += "	</td>";
		str += "	<td>";
		str += "<a type=\"button\"  class=\"btn btn-success\" href=\"#collapseTwo\" onclick=\"javascript:modifyPath('"+pathsObj[0]["name"]+"') \">수정</a>";
		str += "	</td>";
		str += "	<td>";
		str += "<a type=\"button\"  class=\"btn btn-danger\" onclick=\"javascript:deletePath('"+pathsObj[0]["name"]+"');\">삭제</a>";
		str += "	</td>";
		str += "</tr>";
	}

	$("#PathsTable").append(str);
	
	
}

//메뉴의 [운영관리] -> [알 림] -> [알림 설정] -> [+ 공지 추가] -> [2단계 공지 메시지 정의] -> [목적지 선택]탭의 선택 항목
// pathsName <select> str
function pathsNameSelectStr(jsonObj){
	
	var str = "";

	var pathsObj = jsonObj["path"];
	
	//console.log(pathsObj);
	
	if(pathsObj.length > 1){
	
		for ( var i in pathsObj) {
			
				str += "<option value=\""+pathsObj[i]["name"]+"\" >"+pathsObj[i]["name"]+"</option>";
		}
	}else{
		str += "<option value=\""+pathsObj[0]["name"]+"\">"+pathsObj[0]["name"]+"</option>";	
	}
	
	$("#destinationPath").append(str);
}

//메뉴의 [운영관리] -> [알림] -> [알림 설정] -> [공지 추가] -> [2단계 공지 메시지 정의] -> [목적지관리] -> [Destination Target Target Select] 클릭 시 새로 생성된 하단부
function getFailJsonObj(){
	var str = "";

	str += "<tr>";
	str += "<td></td>";
	str += "<td></td>";
	str += "<td></td>";
	str += "<td></td>";
	str += "<td></td>";
	str += "<td>공지 사항이 없습니다!</td>";
	str += "</tr>";
	return str;
}
//메뉴의 [운영관리] -> [알림] -> [알림 설정]의 리스트
//추가된 공지 목록 가져오기
function addNotiStr(jsonObj){
	
	var str = "";

	// 이부분을 바궈볼게요
	
	if(jsonObj.length > 1){
	
		
		for ( var i in  jsonObj){
			
			
			str += "<tr>";//데이터가 한개 이상일때 
			str += "	<td class=\"span1 text-error\">";										
			str += "<strong>&nbsp;"+jsonObj[i]["status"];												//event-label
			str += "	<strong></td>";
			
			//여기까지
			
			str += "	<td class=\"span3\">";
			str += "&nbsp;"+jsonObj[i]["name"];												//event-label
			str += "	</td>";
			str += "	<td class=\"span3\">";
			str += "&nbsp;"+jsonObj[i]["uei"];												//event-label
			str += "	</td>";
			str += "	<td class=\"span1\">";
			str += "<a type=\"button\" class=\"btn btn-warning \" href=\"javascript:modifyNotification('"+jsonObj[i]["name"]+"');\">수정</a>";
			str += "	</td>";
			str += "	<td class=\"span1\">";
			str += "<a type=\"button\" class=\"btn btn-danger\" href=\"javascript:deleteNotification('"+jsonObj[i]["name"]+"');\">삭제</a>";
			str += "	</td>";
			str += "</tr>";
			} 
		}else{//데이터가 한개일때 
			str += "<tr>";
			str += "	<td class=\"span1 text-error\" onclick=\"javascript:getnoti('"+jsonObj[0]["name"]+"');\">";										
			str += "<strong>&nbsp;"+jsonObj[0]["status"];												//event-label
			str += "	<strong></td>";
			str += "	<td class=\"span3\" onclick=\"javascript:getnoti('"+jsonObj[0]["name"]+"');\">";
			str += "&nbsp;"+jsonObj[0]["name"];												//event-label
			str += "	</td>";
			str += "	<td class=\"span3\" onclick=\"javascript:getnoti('"+jsonObj[0]["name"]+"');\">";
			str += "&nbsp;"+jsonObj[0]["uei"];												//event-label
			str += "	</td>";
			str += "	<td class=\"span1\">";
			str += "<a type=\"button\" class=\"btn btn-warning \" href=\"javascript:modifyNotification('"+jsonObj[0]["name"]+"');\">수정</a>";
			str += "	</td>";
			str += "	<td class=\"span1\">";
			str += "<a type=\"button\" class=\"btn btn-danger\" href=\"javascript:deleteNotification('"+jsonObj[0]["name"]+"');\">삭제</a>";
			str += "	</td>";
			str += "</tr>";
			
		}
	
	 $("#notificationTable").append(str);
}



function getCommendsJsonObj(jsonObj){
	
	var str = "";
	
	var commendsObj = jsonObj["command"];
	
	/*if(pathsObj.length > 1){
	
		for ( var i in pathsObj) {
			
				str += "<option value=\""+pathsObj[i]["name"]+"\" >"+pathsObj[i]["name"]+"</option>";
		}
	}else{
		str += "<option value=\""+pathsObj[0]["name"]+"\">"+pathsObj[0]["name"]+"</option>";	
	}*/
	
	$("#commends").append(str);
	
	
	
	
	
	
}









