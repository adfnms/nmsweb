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
 * DELETE JSONSTRING �����
 * @param userId
 * @returns {String}
 */
function JSONStrToUserFordelete(userId){
	
	var str = "{\"user-id\":\""+userId+"\"}";
	
	return str;
	
}



/** ����� �⺻���� ����ϱ�
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


/**����� ��ü ����Ʈ ��������
 * @param callback �ݹ��Լ�
 */
function getUserListTotal(callback){
	
	$.ajax({
		type:'get',
		url:'/v1/users',
		dataType:'json',
		contentType: 'application/json',
		error:function(data){
            alert('����� ��ü ����Ʈ �������� ���� ����');
        },
        success: function(data){
        	//�ݹ��Լ�
        	if( typeof callback == "function" ) {
		        callback(data);
		    }
        }
	});
}