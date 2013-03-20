
//Get list of user's notification
function getNotoficationList(callback, userId ,data){
	console.log('http://192.168.0.5:8081/' + version + '/notifications/searchUser/'+userId+"?"+data);
	
	$.ajax({
		type : 'get',
		//url : '/' + version + '/notifications/searchUser/'+userId,
		url :'/v1/notifications/searchUser/admin?pagetime=2013-03-20%2012:08:14.626-04&limit=1',
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

//Get total list of notification
function gettotalNotoficationList(callback){
	console.log('http://192.168.0.5:8081/' + version + '/notifications/allOutstand');
	$.ajax({
		type : 'get',
		url : '/' + version + '/notifications/allOutstand',
		dataType : 'json',
		contentType : "application/json;charset=UTF-8", 
		error : function(data) {
			//console.log(data);
			console.log(data);
			alert('전체 공지 리스트 가져오기 서비스 실패');
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
 *return notification list about userId
 * notifications/searchUser/userId
 * @param callback
 * @param userId
 * @param recentCount
 */
function getUserNotiList(callback , userId, nowDate, recentCount){
	
	if(userId == null){
		
		alert("사용자 아이디가 없습니다.");
		
		return;
		
	}
	var filter ="pagetime="+nowDate+".626-04&limit="+recentCount;
	//alert("--------filter---------"+filter);
	getNotoficationList(callback, userId ,filter);
		
}

/**
 *return notification list about userId
 * notifications/searchUser/userId
 * @param callback
 * @param userId
 * @param recentCount
 */
function getTotalNotiList(callback){
	
	
	gettotalNotoficationList(callback);
		
}
/******************************************  view String edit  **************************************************/

//나의 공지 정보 가져오기
/**
 * @param jsonObj
 */
function userNotiListjsonObj(jsonObj) {
	
	console.log(jsonObj);
	
	
		var str = "";

	var userObj = jsonObj["notifications"];
	
	if(jsonObj["notifications"] > 1){
		
		for ( var i in userObj) {
			str += "<tr>";
			str += "	<td onclick=\"javascript:getUserDetail('"+userObj[i]["notifyid"]+"');\">";		//Id
			str += userObj[i]["notifyid"];
			str += "	</td>";
			str += "	<td>";																			//condition
			str += userObj[i]["notifyid"];
			str += "	</td>";
			str += "	<td>";																			//notification Time
			str += userObj[i]["pagetime"];
			str += "	</td>";																			//interface
			str += "	<td onclick=\"javascript:getUserDetail('"+userObj[i]["notifyid"]+"');\">";
			str += userObj[i]["interfaceid"];
			str += "	</td>";																			//log
			str += "	<td onclick=\"javascript:getUserDetail('"+userObj[i]["notifyid"]+"');\">";
			str += userObj[i]["textmsg"];
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
	
		$("#userTable").append(str);
	}

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
		str += "	<td onclick=\"javascript:getUserDetail('"+userObj[i]["notifyid"]+"');\">";		//Id
		str += userObj[i]["notifyid"];
		str += "	</td>";
		str += "	<td>";																			//condition
		str += userObj[i]["notifyid"];
		str += "	</td>";
		str += "	<td>";																			//notification Time
		str += userObj[i]["notifyid"];
		str += "	</td>";																			//interface
		str += "	<td onclick=\"javascript:getUserDetail('"+userObj[i]["notifyid"]+"');\">";
		str += userObj[i]["notifyid"];
		str += "	</td>";																			//log
		str += "	<td onclick=\"javascript:getUserDetail('"+userObj[i]["notifyid"]+"');\">";
		str += userObj[i]["notifyid"];
		str += "	</td>";
		str += "</tr>";
	}

	$("#totalTable").append(str);
}