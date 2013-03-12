/**
 * Get a list of nodes. This includes the ID and node label
 * 
 * @param callback
 * @param urlData : url에 포함될 파라미터 <ex> /v1/nodes/21
 * @param data : 파라미터 설정
 */
function getNodeTotalList(callback, data) {

	$.ajax({
		type : 'get',
		url : '/' + version + '/nodes',
		dataType : 'json',
		data: data,
		contentType : 'application/json',
		error : function(data) {
			alert('모든 노드정보 가져오기 실패');
		},
		success : function(data) {
			// 콜백함수
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
}

/** Get the requested service associated with the given node, IP interface, and service name
 * @param callback
 * @param nodeId
 * @param ipAddress
 * @param seviceName
 */
function searchNodeList(callback, nodeId, ipAddress, seviceName) {

	$.ajax({
		type : 'get',
		url : '/' + version + '/nodes/'+ nodeId + '/ipinterfaces/'+ipAddress+'/services/'+seviceName,
		dataType : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert('노드 검색 실패');
		},
		success : function(data) {
			// 콜백함수
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
}