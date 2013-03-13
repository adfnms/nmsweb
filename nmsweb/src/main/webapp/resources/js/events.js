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

/** Get a list of outages for nodeId [recent]
 * 최근 recentCount개 의 Events 목록
 * @param callback
 * @param nodeId
 * @param recentCount
 */
function getEventsForNode(callback,nodeId,recentCount) {

	if(nodeId == null){
		alert("노드 아이디가 없습니다.");
		return;
	}
	
	//var data = "query=this_.nodeId = '"+nodeId+"' AND this_.iflostservice > '"+new Date().format("yyyy-MM-ddTHH:MM:ss")+"'";
	var query = encodeURI("query=this_.nodeId = '"+nodeId+"'");
	var filter ="&orderBy=eventTime&order=desc&limit="+recentCount;

	getTotalEvenstList(callback,query+filter);
}