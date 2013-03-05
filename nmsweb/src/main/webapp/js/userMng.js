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
	
	/*$.ajax({
		type:'get',
		url:'/v1/users',
		dataType:'json',
		error:function(data){
            alert('사용자 전체 리스트 가져오기 서비스 실패');
        },
        success: function(data){
        	//콜백함수
        	if( typeof callback == "function" ) {
		        callback(data);
		    }else{
		    	alert('콜백함수를 지정해주세요.');
		    }
        }
	});*/
	var data  = '{"@totalCount":"14","@count":"14","user":[{"user_id":"test","full_name":"","user_comments":"","password":"D41D8CD98F00B204E9800998ECF8427E"},{"user_id":"adflow","full_name":"adflow","user_comments":"","password":"21232F297A57A5A743894A0E4A801FC3"},{"user_id":"admin","full_name":"Administrator","user_comments":"Default administrator, do not delete","password":"21232F297A57A5A743894A0E4A801FC3"},{"user_id":"bcs","full_name":"bcs","user_comments":"????????????????","password":"CA129D9BF2875916863BB25324A23A5D"},{"user_id":"byun","full_name":"byun Hyui Seob","user_comments":"My name is BHS,","password":"4C0960E770DCBEF4AF00354BEDED176F"},{"user_id":"chacha5","full_name":"Leechanho","user_comments":"null","password":"B2CA678B4C936F905FB82F2733F5297F"},{"user_id":"chacha99","full_name":"Leechanho","user_comments":"testchan","password":"B2CA678B4C936F905FB82F2733F5297F"},{"user_id":"chan","full_name":"chan","user_comments":"testtest","password":"26C322652770620E64AC90682EB6504C"},{"user_id":"hyunsooLee","full_name":"","user_comments":"testing","password":"827CCB0EEA8A706C4C34A16891F84E7B"},{"user_id":"jdbae","full_name":"jdbae","user_comments":"jdbae","password":"81D07797375E2669F3F87E2083042AE2"},{"user_id":"kicho","full_name":"cho","user_comments":"","password":"668A8F0194A84EAB1B7441DC1BD3AFBC"},{"user_id":"kth","full_name":"","user_comments":"","password":"D41D8CD98F00B204E9800998ECF8427E"},{"user_id":"matin","full_name":"Matang","user_comments":"God of City","password":"CBEC14F4BA505CC32CB0AFBDDC845614","duty_schedule":"MoTuSu0_0"},{"user_id":"wqewqe","full_name":"chaddo","user_comments":"testeswqe","password":"81DC9B2asdasd313ED055DB5"}]}';
	callback(data);
}