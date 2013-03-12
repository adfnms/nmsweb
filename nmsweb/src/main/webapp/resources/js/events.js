/** Get a list of events
 * @param callback
 * @param data
 */
function getTotalEvenstList(callback,data) {

	$.ajax({
		type : 'get',
		url : '/' + version + '/events',
		dataType : 'json',
		contentType : 'application/json',
		data : data,
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

/** Get a list of outages for nodeId
 * @param callback
 * @param nodeId
 */
function getEventsForNode(callback,nodeId) {

	if(nodeId == null){
		alert("노드 아이디가 없습니다.");
		return;
	}
	
	getTotalEvenstList(callback,"this_.nodeId%20%3D%20'"+nodeId+"'");
}