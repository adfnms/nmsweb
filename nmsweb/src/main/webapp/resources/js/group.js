
function groupStr (name,comments,user){
 		
 		var str ="{\"group\":[{\"name\":\""+name+"\",\"comments\":\""+comments+"\"," +
 				"\"user\":\""+user+"\"}]}";
 		
 		return str;
 	}	


function getGroupDeteil(callback , name){
	console.log('http://192.168.0.37:8080/' + version + '/groups/?'+name);
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
			
			console.log("그룹 상세정보 가져오기 : "+data["name"]);
			
			// 콜백함수
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
	
}

function getAuth(callback){
	
	$.ajax({
		url:'/v1/admin/getGroup.ajax',
        type:'get',
        dataType:'json',
        async:false,
		error : function(data) {
			console.log(data);
			alert('권한 가져오기 서비스 실패');
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


function getGroupMember(callback , name){
	
	getGroupDeteil(callback, name );
	
}

//--------------------------------------<div>---------------------------------------------


function groupNameStr(jsonObj){
	
	var str = "";

	var userObj = jsonObj["group"];
	
	for ( var i in userObj) {
		str += "<tr>";
		str += "	<td onclick=\"javascript:destinationGroup('"+userObj[i]["name"]+"');\">";
		str += userObj[i]["name"];
		str += "	</td>";
		str += "</tr>";
	}

	$("#groupTable").append(str);
}

function groupListStr(jsonObj){
	
	
	console.log(jsonObj);
	console.log("======jsonObj========");
	
	var str = "";
	var groupObj = jsonObj["group"];
	
	var TBODYobj = $("<tbody></tbody>");
	var TRobj = $("<tr></tr>");
	var TDobj = $("<td></td>");
	var ABBRobj = $("<abbr></abbr>");
	var BUTTONobj = $("<button></button>");
	var Aobj = $("<a></a>");
	
	if (jsonObj["@totalCount"] == 1) {
		
		
		
	}else if (jsonObj["@totalCount"] > 1){
		
		
		
		for ( var i in groupObj) {
			
			console.log("groupname : "+groupObj[i]["name"]);
			
			var groupname = groupObj[i]["name"];
			
			TBODYobj.append(
			
			
			TRobj.clone().append(	
				TDobj.clone().attr("class","text-info").append().text(groupObj[i]["name"]),
				TDobj.clone().attr("class","text-success").append().text(groupObj[i]["comments"]),
				TDobj.clone().append(
					
						
						//BUTTONobj.clone().attr("type", "button").attr("class", "btn").attr("onclick","javascript:getGroupInfo("+groupObj[i]["name"]+")").text("권한 설정"),
						BUTTONobj.clone().attr("type","button").attr("class","btn btn-info").attr("onclick","getGroupInfo()").text("권한 설정")
						//Aobj.clone().attr("type", "button").attr("class", "btn").attr("href","javascript:getGroupInfo('"+groupObj[i]["name"]+")").append().text(nodeObj[i]["@label"])
						
					
					
				),
				TDobj.clone().attr("title","그룹 삭제 버튼").append(
					ABBRobj.clone().attr("title","그룹 삭제 버튼").append(
							BUTTONobj.clone().attr("type", "button").attr("class", "btn btn-danger").attr("href", "javascript:deleteGroup("+groupObj[i]["name"]+")").text("삭제")
					)
				)
			)
		);
	

//<a href='/" + version + "/search/node/nodeDesc.do?nodeId="+ nodeObj[i]["@id"]+ "'></a>
			
			
			
			
			
			
			
			
			
			
			
			
			
			/*str += "<tr>";
			str += "	<td class=\"text-info\">";
			str += groupObj[i]["name"];
			str += "	</td>";
			str += "	<td class=\"text-success\">";
			str += groupObj[i]["comments"];
			str += "	</td>";
			str += "	<td>";
			str += "<abbr title=\"권한 설정 버튼\"><a type=\"button\" class=\"btn \" href=\"javascript:getGroupInfo('"+groupObj[i]["name"]+"');\">권한 설정</a></abbr>";
			str += "	</td>";
			str += "	<td>";
			str += "<abbr title=\"그룹 삭제 버튼\"><a type=\"button\" class=\"btn btn-danger\" href=\"javascript:deleteGroup('"+groupObj[i]["name"]+"');\">삭제</a></abbr>";
			str += "	</td>";
			str += "</tr>";*/

		}
	}
	//<a href='/" + version + "/search/node/nodeDesc.do?nodeId="+ nodeObj[i]["@id"]+ "'></a>
			
	
	//$("#groupTable").append(str);
	return TBODYobj;
	//return str;
}

//userInfo <select> str
function groupMemberSelectStr(jsonObj){
	
	
	var name = jsonObj["name"];
	var comments = jsonObj["comments"];
	var user = jsonObj["user"];
//	console.log("groupMemberSelectStr name : "+name);
//	console.log("groupMemberSelectStr comments : "+comments);
//	console.log("groupMemberSelectStr user : "+user);
	
	var selectStr = "";
	
	if(user == undefined ){
		
		
		selectStr += "<option value=\"\"></option>";
		$("#groupMemberListSelect").append(selectStr);
	}else{
	
		for ( var i=0 ; i<user.length ; i++){
			var userOne = user[i].length;
		}
			if(userOne == 1){
				
				selectStr += "<option value=\""+user+"\">"+user+"</option>";
				
			}else{
				for ( var i=0 ; i<user.length ; i++){
						{
							selectStr += "<option class=\"text-warning\" value=\""+user[i]+"\">"+user[i]+"</option>";
						}
					}
			}
		}
		
	$("#groupMemberListSelect").append(selectStr);
}
