

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
		//url : '/' + version + '/notifications/searchUser/'+userId,
		url :'/v1/notifications/searchUser/admin?pagetime=2013-03-20T06:22:43.467-04:00&limit=2',
		//data : data,
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
				console.log(data);
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
	//console.log('http://192.168.0.5:8081/' + version + '/notifications/destinationPaths');
	//console.log('http://localhost:8080/' + version + '/notifications/destinationPaths');
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
	//console.log('http://192.168.0.5:8081/' + version + '/notifications/destinationPaths');
	//console.log('http://localhost:8080/' + version + '/notifications/destinationPaths');
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
function getUserNotiList(callback , userId, nowDate, recentCount){
	
	if(userId == null){
		
		alert("사용자 아이디가 없습니다.");
		
		return;
		
	}
	var filter ="pagetime="+nowDate+".467-04:00&limit="+recentCount;
	getNotoficationList(callback, userId ,filter);
		
}

/**
 *Return list of outstanded notifications
 * @param callback
 * @param userId
 * @param recentCount
 */
function getTotalNotiList(callback, nowDate, recentCount){
	
	var filter ="pagetime="+nowDate+".510547-09&limit="+recentCount;
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
 * @param jsonObj
 */
function userNotiListjsonObj(jsonObj) {
	
		var str = "";
		//alert("jsonObj");
	var userObj = jsonObj["notifications"];
	
	
	
	if(userObj.length > 1){
		
		//for ( var i = 1; i < userObj.length; i++){
		
		for ( var i in  userObj){
			
			
			/************************Get @severity  from event****************************/
			 
			var eventId = userObj[i]["eventid"];
			
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
					
				}

			});
			
			/************************Get @severity  from event****************************/
			
			
			str += "<tr>";
			str += "	<td class=\"span1\"><a href='/"+version+"/admin/setting/notificationDetali.do?notifyid="+userObj[i]["notifyid"]+"&eventId="+userObj[i]["eventid"]+"'>";										
			str += userObj[i]["notifyid"];											//notifyid
			str += "	</a></td>";
			str += "	<td class=\"span1\"><a href='/"+version+"/search/event/eventDesc.do?eventId="+userObj[i]["eventid"]+"'>";										
			str += userObj[i]["eventid"];											//eventid
			str += "	</a></td>";
			str += "<th class='"+stat.toLowerCase()+"'>" + stat + "</th>";			//condition
			str += "	<td class=\"span2\">";										
			str +=  new Date(userObj[i]["pagetime"]).format('yy-MM-dd hh:mm:ss');	//pagetime 
			str += "	</td>";														
			str += "	<td class=\"span1\" ><a href='/"+version+"/search/node/interfaceDesc.do?nodeId="+userObj[i]["nodeid"]+"&intf="+nullCheckJsonObject(userObj[i]["notifications"],"interfaceid")+"'>";
			str += nullCheckJsonObject(userObj[i]["notifications"],"interfaceid");	//interface
			str += "	</a></td>";	
			str += "	<td class=\"span6\" >";
			str += userObj[i]["textmsg"];											//textmsg
			str += "	</td>";
			str += "</tr>";
			}
		
	} else {
		str += "<tr>";
		str += "	<td onclick=\"javascript:getUserDetail('"+userObj[0]["notifyid"]+"');\">";		//Id
		str += userObj[0]["notifyid"];
		str += "	</td>";
		str += "	<td>";																			//condition
		str += userObj[0]["notifyid"];
		str += "	</td>";
		str += "	<td>";																			//notification Time
		str += userObj[0]["pagetime"];
		str += "	</td>";																			//interface
		str += "	<td onclick=\"javascript:getUserDetail('"+userObj[0]["notifyid"]+"');\">";
		str += userObj[0]["interfaceid"];
		str += "	</td>";																			//log
		str += "	<td onclick=\"javascript:getUserDetail('"+userObj[0]["notifyid"]+"');\">";
		str += userObj[0]["textmsg"];
		str += "	</td>";
		str += "</tr>";
	
		
	}
	$("#userTable").append(str);
}
//전체 공지 정보 가져오기
/**
 * @param jsonObj
 */
function totalNotiListjsonObj(jsonObj) {
	
	var str = "";

	var userObj = jsonObj["notifications"];
	if(userObj.length >1){
		for ( var i in userObj) {
			
			var eventId = userObj[i]["eventid"];
			
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
					
					
				}

			});
			
			
			str += "<tr>";
			str += "	<td class=\"span1\"><a href='/"+version+"/admin/setting/notificationDetali.do?notifyid="+userObj[i]["notifyid"]+"&eventId="+userObj[i]["eventid"]+"'>";										
			str += userObj[i]["notifyid"];											//notifyid
			str += "	</a></td>";
			str += "	<td class=\"span1\"><a href='/"+version+"/search/event/eventDesc.do?eventId="+userObj[i]["eventid"]+"'>";										
			str += userObj[i]["eventid"];											//eventid
			str += "	</a></td>";
			str += "<th class='"+stat.toLowerCase()+"'>" + stat + "</th>";			//condition
			str += "	<td class=\"span2\">";										
			str +=  new Date(userObj[i]["pagetime"]).format('yy-MM-dd hh:mm:ss');	//pagetime 
			str += "	</td>";														
			str += "	<td class=\"span1\" ><a href='/"+version+"/search/node/interfaceDesc.do?nodeId="+userObj[i]["nodeid"]+"&intf="+nullCheckJsonObject(userObj[i]["notifications"],"interfaceid")+"'>";
			str += nullCheckJsonObject(userObj[i]["notifications"],"interfaceid");	//interface
			str += "	</a></td>";	
			str += "	<td class=\"span6\" >";
			str += userObj[i]["textmsg"];											//textmsg
			str += "	</td>";
			str += "</tr>";
		}
		
	}else{
		str += "<tr>";
		str += "	<td onclick=\"javascript:getUserDetail('"+userObj[0]["notifyid"]+"');\">";		//Id
		str += userObj[0]["notifyid"];
		str += "	</td>";
		str += "	<td>";																			//condition
		str += userObj[0]["notifyid"];
		str += "	</td>";
		str += "	<td>";																			//notification Time
		str += userObj[0]["pagetime"];
		str += "	</td>";																			//interface
		str += "	<td onclick=\"javascript:getUserDetail('"+userObj[0]["notifyid"]+"');\">";
		str += userObj[0]["interfaceid"];
		str += "	</td>";																			//log
		str += "	<td onclick=\"javascript:getUserDetail('"+userObj[0]["notifyid"]+"');\">";
		str += userObj[0]["textmsg"];
		str += "	</td>";
		str += "</tr>";
		
	}
	$("#totalTable").append(str);
	
}

function notifiInfo(jsonObj){
	
	
	
	var notifiInfoStr = '<div class="row-fluid">'+
						'	<div class="span12">'+
						'		<h5>공지&nbsp;상세&nbsp;정보</h5>'+
						'	</div>'+
						'</div>'+
						'<div class="row-fluid">'+
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
function destiInfo(jsonObj){
	
	var str = "";

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
}


//모든 이벤트 목록 가져오기
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
function getEventSelectJsonObj(jsonObj){
	
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
}



function pathsNameStr(jsonObj){
	var str = "";

	var pathsObj = jsonObj["path"];
	
	if(pathsObj.length > 1){
	
		for ( var i in pathsObj) {
			
			
			str += "<tr>";
			str += "	<td>";
			str += pathsObj[i]["name"];
			str += "	</td>";
			str += "	<td>";
			str += "	<a type=\"button\" class=\"btn btn-success accordion-toggle\" data-toggle=\"collapse\" data-parent=\"#accordion3\" onclick=\"javascript:modifyPath('"+pathsObj[i]["name"]+"') \" href=\"#collapseThree\">수정</a>";
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
//추가된 공지 목록 가져오기
function addNotiStr(jsonObj){
	
	var str = "";
	
	 if(jsonObj.length > 1){
	
		for ( var i in  jsonObj){
			
			
			str += "<tr>";//데이터가 한개 이상일때 
			str += "	<td class=\"span1 text-error\">";										
			str += "<strong>&nbsp;"+jsonObj[i]["status"];												//event-label
			str += "	<strong></td>";
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
	
	console.log(commendsObj);
	
	/*if(pathsObj.length > 1){
	
		for ( var i in pathsObj) {
			
				str += "<option value=\""+pathsObj[i]["name"]+"\" >"+pathsObj[i]["name"]+"</option>";
		}
	}else{
		str += "<option value=\""+pathsObj[0]["name"]+"\">"+pathsObj[0]["name"]+"</option>";	
	}*/
	
	$("#commends").append(str);
	
	
	
	
	
	
}









