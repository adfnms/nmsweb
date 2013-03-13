/** Get a list of outages
 * @param callback
 */
function getTotalOutagesList(callback,data) {

	$.ajax({
		type : 'get',
		url : '/' + version + '/outages',
		dataType : 'json',
		data: data,
		contentType : 'application/json',
		error : function(data) {
			alert('모든 중단정보 가져오기 실패');
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
function getOutagesForNode(callback,nodeId) {

	if(nodeId == null){
		alert("노드 아이디가 없습니다.");
		return;
	}
	
	getTotalOutagesList(callback,"this_.nodeId%20%3D%20'"+nodeId+"'");
}