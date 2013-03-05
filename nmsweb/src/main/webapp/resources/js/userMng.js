/*
 * User Manager Script
 */

/** 사용자 기본정보 JSONSTRING 만들기
 * @param userId
 * @param fullName
 * @param comments
 * @param password
 * @returns {String}
 */
function getJSONStrToUser(userId,fullName,comments,password){
	
	var str = "{\"user\":[{" +
			"\"user_id\":\""+userId+"\"," +
			"\"full_name\":\""+fullName+"\"," +
			"\"user_comments\":\""+comments+"\"," +
			"\"password\":\""+password+"\"}]}";
	
	return str;
	
}

/** 사용자 기본정보 등록하기
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
        	alert("???");
        }
	});
	alert(_return);
	
	return _return;
}


/**사용자 전체 리스트 가져오기
 * @param callback 콜백함수
 */
function getUserListTotal(callback){
	
	$.ajax({
		type:'get',
		url:'/v1/users',
		dataType:'json',
		contentType: 'application/json',
		error:function(data){
            alert('사용자 전체 리스트 가져오기 서비스 실패');
        },
        success: function(data){
        	//콜백함수
        	if( typeof callback == "function" ) {
		        callback(data);
		    }
        }
	});
}