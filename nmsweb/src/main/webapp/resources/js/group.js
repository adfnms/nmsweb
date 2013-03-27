
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
