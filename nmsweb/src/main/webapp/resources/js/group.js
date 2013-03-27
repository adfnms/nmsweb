
/**
 * 
 * @param callback
 */
function getGroupList(callback){
	
	$.ajax({
		type:'get',
		url:'/v1/groups',
		dataType:'json',
		contentType: 'application/json',
		error:function(data){
            alert('그룹 리스트 실패');
        },
        success: function(data){
        	
        	if( typeof callback == "function" ) {
		        callback(data);
		    }
        }
	});
}

function getGroupDeteil(callback , name){
	console.log('http://192.168.0.5:8081/' + version + '/groups/?'+name);
	$.ajax({
		type : 'get',
		url : '/' + version + '/groups/'+name,
		dataType : 'json',
		contentType : 'application/json', 
		error : function(data) {
			//console.log(data);
			alert('그룹 상세정보 가져오기 서비스 실패');
		},
		success : function(data) {
			
			console.log(data);
			
			// 콜백함수
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
	
}



function getGroupMember(callback , name){
	
	getGroupDeteil(callback, name );
	
}

//--------------------------------------<div>---------------------------------------------


function groupNameStr(jsonObj){
	
	var str = "";

	var userObj = jsonObj["group"];
	
	for ( var i in userObj) {
		str += "<tr>";
		
		str += "	<td onclick=\"javascript:destinationPathInfo('"+userObj[i]["name"]+"');\">";
		str += userObj[i]["name"];
		str += "	</td>";
		str += "</tr>";
	}

	$("#groupTable").append(str);
}

function groupListStr(jsonObj){
	
	var str = "";

	var userObj = jsonObj["group"];
	
	for ( var i in userObj) {
		str += "<tr>";
		str += "	<td onclick=\"javascript:getGroupInfo('"+userObj[i]["name"]+"');\">";
		str += userObj[i]["name"];
		str += "	</td>";
		str += "	<td onclick=\"javascript:getGroupInfo('"+userObj[i]["name"]+"');\">";
		str += userObj[i]["comments"];
		str += "	</td>";
		str += "	<td>";
		str += "<a type=\"button\" class=\"btn btn-danger\" href=\"javascript:deleteGroup('"+userObj[i]["name"]+"');\">삭제</a>";
		str += "	</td>";
		str += "</tr>";
	}

	$("#groupTable").append(str);
	
}


//userInfo <select> str
function groupMemberSelectStr(jsonObj){
	
	var str = "";
		
		var groupObj = jsonObj["user"];
			for ( var i in groupObj) {
			
				str += "<option value=\""+groupObj[i]+"\">"+groupObj[i]+"</option>";
		}
		
			$("#groupMemberListSelect").append(str);
	}












