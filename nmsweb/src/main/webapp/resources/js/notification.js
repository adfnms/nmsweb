
//Return notification list related with given userName
function getNotoficationList(callback, userId ,data){
	console.log('/' + version + '/notifications/searchUser/'+userId+"?"+data);
	
	$.ajax({
		type : 'get',
		url : '/' + version + '/notifications/searchUser/'+userId,
		//url :'/v1/notifications/searchUser/admin?pagetime=2013-03-19%2014:10:22.510547-09&limit=3',
		data : data,
		contentType: "application/json;charset=UTF-8", 
		dataType : 'json',
		error : function(data) {
			
			alert('나의 공지 리스트 가져오기 서비스 실패');
		},
		success : function(data) {
			//console.log(data);
			
			
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
	console.log('http://192.168.0.5:8081/' + version + '/notifications/allOutstand/?'+data);
	$.ajax({
		type : 'get',
		url : '/' + version + '/notifications/allOutstand',
		dataType : 'json',
		data : data,
		contentType : "application/json;charset=UTF-8", 
		error : function(data) {
			//console.log(data);
		
			alert('전체 공지 리스트 가져오기 서비스 실패');
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
	console.log('http://192.168.0.5:8081/' + version + '/notifications/?'+notifyid);
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
	console.log('http://192.168.0.5:8081/' + version + '/notifications/?'+notifyid);
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
	console.log('http://192.168.0.5:8081/' + version + '/notifications/events');
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
			console.log(data);
			if (typeof callback == "function") {
				callback(data);
			}
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
	var filter ="pagetime="+nowDate+".510547-09&limit="+recentCount;
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
/*********Get the notification specified by the certain ID*****/



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

function getEventJsonObj(jsonObj){
	console.log(jsonObj);
	
	var str = "";

	var eventObj = jsonObj["event"]; 
	
	 if(eventObj.length > 1){
	
		for ( var i in  eventObj){
			
			alert(eventObj[i]["uei"]);
			
			str += "<tr>";
			str += "	<td class=\"span1\">";										
			str += eventObj[i]["uei"];												//uei
			str += "	</td>";
			str += "	<td class=\"span1\">";										
			str += eventObj[i]["event-label"];												//event-label
			str += "	</td>";													
			str += "</tr>";
			
			} 
		}else{
			str += "<tr>";
			str += "	<td class=\"span1\">";										
			str += eventObj[0]["userId"];												//uei
			str += "	</td>";
			str += "	<td class=\"span1\">";										
			str += eventObj[0]["media"];												//event-label
			str += "	</td>";													
			str += "</tr>";
			
		}
	
	 $("#eventListTable").append(str);
}
