/**get Service total list
 * @param callback
 */
function getServiceList(callback){
	
	$.ajax({
		type : 'get',
		url : '/' + version + '/serviceList',
		dataType : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert('이벤트 리스트 가져오기 서비스 실패');
		},
		success : function(data) {
			// 콜백함수
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
	
}