function getTotalRequisitionsList(callback) {

	$.ajax({
		type : 'get',
		url : '/' + version + '/requisitions/deployed',
		dataType : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert('필요조건 리스트 가져오기 서비스 실패');
		},
		success : function(data) {
			// 콜백함수
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});

}


/************************** view String edit *****************************/

/** 이벤트 정보를 table 테그 Str로 만들어준다. 
 * @param jsonObj
 */
function getOptionTagToRequisitionJsonObj(jsonObj){
	
	var requisitionObj = jsonObj["model-import"];
	
	var str = "";
	if(jsonObj["@count"] > 0){
		if(jsonObj["@count"] > 1){
		
			for ( var i in requisitionObj) {

				str += "<option value="+requisitionObj[i]["@foreign-source"]+">"+requisitionObj[i]["@foreign-source"]+"</option>";
				
			}
			
		}else{
			str += "<option value="+requisitionObj["@foreign-source"]+">"+requisitionObj["@foreign-source"]+"</option>";
		}
		
	}
	return str;
}