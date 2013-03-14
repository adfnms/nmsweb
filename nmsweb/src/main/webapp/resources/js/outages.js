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
			alert('중단목록 가져오기 실패');
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
 * 최근 recentCount개 의 outages 목록
 * @param callback
 * @param nodeId
 * @param recentCount
 */
function getOutagesForNode(callback,nodeId,recentCount) {

	if(nodeId == null){
		alert("노드 아이디가 없습니다.");
		return;
	}

	//var data = "query=this_.nodeId = '"+nodeId+"' AND this_.iflostservice > '"+new Date().format("yyyy-MM-ddTHH:MM:ss")+"'";
	var query = encodeURI("query=this_.nodeId = '"+nodeId+"'");
	var filter ="&orderBy=ifLostService&order=desc&limit="+recentCount;

	getTotalOutagesList(callback,query+filter);
}