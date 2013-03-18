
//Get list of user's notification
function getNotoficationList(callback, userId ,data){
	alert("2");
	$.ajax({
		type : 'get',
		url : '/' + version + '/notifications/searchUser/'+userId,
		dataType : 'json',
		contentType : 'application/json',
		data : data,
		error : function(data) {
			alert('나의 공지 리스트 가져오기 서비스 실패');
		},
		success : function(data) {
			// 콜백함수
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
	
}

//Get total list of notification
function gettotalNotoficationList(callback ,data){
	alert("2");
	$.ajax({
		type : 'get',
		url : '/' + version + '/notifications/allOutstand',
		dataType : 'json',
		contentType : 'application/json',
		data : data,
		error : function(data) {
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
 *return notification list about userId
 * notifications/searchUser/userId
 * @param callback
 * @param userId
 * @param recentCount
 */
function getUserNotiList(callback , userId, recentCount){
	
	alert("1");
	
	if(userId == null){
		
		alert("사용자 아이디가 없습니다.");
		
		return;
		
	}
	
	
	var filter ="orderBy=notifyid&order=desc&limit="+recentCount;
	getNotoficationList(callback, userId ,filter);
		
}

/**
 *return notification list about userId
 * notifications/searchUser/userId
 * @param callback
 * @param userId
 * @param recentCount
 */
function getTotalNotiList(callback, recentCount){
	
	alert("1");
	
	var filter ="orderBy=notifyid&order=desc&limit="+recentCount;
	gettotalNotoficationList(callback ,filter);
		
}
/******************************************  view String edit  **************************************************/

//나의 공지 정보 가져오기
/**
 * @param jsonObj
 */
function userNotiListjsonObj(jsonObj) {
	alert("3");
	console.log(jsonObj);
	
	var str = "";

	var userObj = jsonObj["notifications"];
	
	for ( var i in userObj) {
		str += "<tr>";
		str += "	<td onclick=\"javascript:getUserDetail('"+userObj[i]["ipAddress"]+"');\">";		//Id
		str += userObj[i]["ipAddress"];
		str += "	</td>";
		str += "	<td>";																			//condition
		str += userObj[i]["pagetime"];
		str += "	</td>";
		str += "	<td>";																			//notification Time
		str += userObj[i]["nodeid"];
		str += "	</td>";																			//interface
		str += "	<td onclick=\"javascript:getUserDetail('"+userObj[i]["ipAddress"]+"');\">";
		str += userObj[i]["interfaceid"];
		str += "	</td>";																			//log
		str += "	<td onclick=\"javascript:getUserDetail('"+userObj[i]["ipAddress"]+"');\">";
		str += userObj[i]["notifconfigname"];
		str += "	</td>";
		str += "</tr>";
	}

	$("#userDiv").append(str);
}


//전체 공지 정보 가져오기
/**
 * @param jsonObj
 */
function totalNotiListjsonObj(jsonObj) {
	
	console.log(jsonObj);
	
	var str = "";

	var userObj = jsonObj["notifications"];
	
	for ( var i in userObj) {
		str += "<tr>";
		str += "	<td onclick=\"javascript:getUserDetail('"+userObj[i]["ipAddress"]+"');\">";		//Id
		str += userObj[i]["ipAddress"];
		str += "	</td>";
		str += "	<td>";																			//condition
		str += userObj[i]["pagetime"];
		str += "	</td>";
		str += "	<td>";																			//notification Time
		str += userObj[i]["nodeid"];
		str += "	</td>";																			//interface
		str += "	<td onclick=\"javascript:getUserDetail('"+userObj[i]["ipAddress"]+"');\">";
		str += userObj[i]["interfaceid"];
		str += "	</td>";																			//log
		str += "	<td onclick=\"javascript:getUserDetail('"+userObj[i]["ipAddress"]+"');\">";
		str += userObj[i]["notifconfigname"];
		str += "	</td>";
		str += "</tr>";
	}

	$("#totalDiv").append(str);
}