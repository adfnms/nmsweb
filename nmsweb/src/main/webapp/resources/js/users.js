/*
 * User Manager Script
 */

/** ����� �⺻���� JSONSTRING �����
 * @param userId
 * @param fullName
 * @param comments
 * @param password
 * @returns {String}
 */
function getJSONStrToUser(userId,fullName,comments,password){
	
	var str = "{\"user\":[{" +
			"\"user-id\":\""+userId+"\"," +
			"\"full-name\":\""+fullName+"\"," +
			"\"user-comments\":\""+comments+"\"," +
			"\"password\":\""+password+"\"}]}";
	
	return str;
	
}


/**
 * DELETE JSONSTRING
 * @param userId
 * @returns {String}
 */
function JSONStrToUserFordelete(userId){
	
	var str = "{\"user-id\":\""+userId+"\"}";
	
	return str;
	
}



/** 
 * @param userId
 * @param fullName
 * @param comments
 * @param password
 */
function setUserBaseInfo(userId,fullName,comments,password){
	
	var data = getJSONStrToUser(userId,fullName,comments,password);
	
	var _return = false;
	
	$.ajax({
		type:'post',
		url:'<%=NMSProperties.getNmswebAddress()%>/users',
		data:data,
		contentType: 'application/json',
		error:function(){
			_return = false;
		},
        success: function(data){
        	_return = true;
        	alert("성공하였습니다");
        }
	});
	
	alert(_return);
	
	return _return;
}


/**
 * 
 * @param callback
 */
function getUserListTotal(callback){
	
	$.ajax({
		type:'get',
		url:'/v1/users',
		dataType:'json',
		contentType: 'application/json',
		error:function(data){
            alert('사용자 리스트 실패');
        },
        success: function(data){
        	
        	if( typeof callback == "function" ) {
		        callback(data);
		    }
        }
	});
}
/**
 * 사용자DB 그룹삭제  
 * @param userName
 * @param groupName
 */
function deleteUserTbl(userName,groupName){
	
	$.ajax({
		type : 'get',
		//url : '<c:url value="/admin/userMng/deleteUserTbl.do" />',
		url : '/' + version + '/admin/userMng/deleteUserTbl/' ,
		data: 'groupName='+groupName+'&userName='+userName,
		contentType : 'application/json', 
		error : function(data) {
			//console.log(data);
			alert('사용자DB 그룹등록 서비스 실패');
		},
		success : function(data) {
			console.log("사용자DB 그룹등록 서비스 성공");
		}
	});  
	
}

/**
 * 사용자DB 그룹등록  
 * @param userName
 * @param groupName
 */

function regUserTbl(userName,groupName){
	
	$.ajax({
		type : 'get',
		//url : '<c:url value="/admin/userMng/regUserTbl.do" />',
		url : '/' + version + '/admin/userMng/regUserTbl/' ,
		data: 'groupName='+groupName+'&userName='+userName,
		contentType : 'application/json', 
		error : function(data) {
			//console.log(data);
			alert('사용자DB 그룹등록 서비스 실패');
		},
		success : function(data) {
			
			console.log("사용자DB 그룹등록 서비스 성공");
		}
	});  
	
}

//--------------------------------------<div>---------------------------------------------
function userListStr(jsonObj ,groupNm){
	
	var str = "";

	var userObj = jsonObj["user"];
	
	var totalCount = jsonObj["@totalCount"];

	if (totalCount == 1) {
		
		str += "<tr>";
		if(groupNm == "Admin"){
		str += "	<td onclick=\"javascript:getUserDetail('"+userObj["user-id"]+"');\">";
		str += userObj["user-id"];
		str += "	</td>";
		str += "	<td onclick=\"javascript:getUserDetail('"+userObj["user-id"]+"');\">";
		str += userObj["full-name"];
		str += "	</td>";
		str += "	<td>";
		str += userObj["user-comments"];
		str += "	</td>";
		str += "	<td>";
		str += "<a type=\"button\" class=\"btn btn-danger\" href=\"javascript:deleteUser('"+userObj["user-id"]+"');\">삭제</a>";
		str += "	</td>";
		}
		str += "</tr>";
		
	}else if(totalCount > 1){
		
		for ( var i in userObj) {
			
			str += "<tr>";
			if(groupNm == "Admin"){
			str += "	<td onclick=\"javascript:getUserDetail('"+userObj[i]["user-id"]+"');\"><a>";
			str += userObj[i]["user-id"];
			str += "	</a></td>";
			str += "	<td onclick=\"javascript:getUserDetail('"+userObj[i]["user-id"]+"');\"><a>";
			str += userObj[i]["full-name"];
			str += "	</a></td>";
			str += "	<td>";
			str += userObj[i]["user-comments"];
			str += "	</td>";
			str += "	<td>";
			str += "<a type=\"button\" class=\"btn btn-danger\" href=\"javascript:deleteUser('"+userObj[i]["user-id"]+"');\">삭제</a>";
			str += "	</td>";
			}
			str += "</tr>";
		}
	}
	
	$("#userListTable").append(str);
	
	
}
/**/
function userNameStr(jsonObj){
	
	var str = "";

	var userObj = jsonObj["user"];
	
	var totalCount = jsonObj["@totalCount"];
	
	if (totalCount == 1) {

		str += "<tr>";
		str += "	<td onclick=\"javascript:destinationPathInfo('"+userObj["user-id"]+"');\">";
		str += "&nbsp;"+userObj["full-name"];
		str += "	</td>";
		str += "</tr>";
		
		$("#userTable").append(str);
	}else if(totalCount > 1){
		
	
		for ( var i in userObj) {
		
		str += "<tr>";
		str += "	<td onclick=\"javascript:destinationPathInfo('"+userObj[i]["user-id"]+"');\">";
		str += "&nbsp;"+userObj[i]["full-name"];
		str += "	</td>";
		str += "</tr>";
		
		$("#userTable").append(str);
	}

	
	
	
}
}
//userInfo <select> str

function userNameSelectStr(jsonObj){
	
	var selectStr = "";
		
	var userObj = jsonObj["user"];
	
	var totalCount = jsonObj["@totalCount"];
	
	if (totalCount == 1) {
		
		
		selectStr += "<option class=\"text-info\" value=\""+userObj["user-id"]+"\">"+userObj["full-name"]+"</option>";
		
	}else if(totalCount > 1){
		
		for ( var i in userObj) {
					
			selectStr += "<option class=\"text-info\" value=\""+userObj[i]["user-id"]+"\">"+userObj[i]["full-name"]+"</option>";
				}
		
	}
	
		$("#userListSelect").append(selectStr);
	}

	
	
	





