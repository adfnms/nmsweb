/** 그래프를 보여줄수 있는 node 리스트
 * @param callback
 */
function getTotalGraphList(callback){
	
	$.ajax({
		type : 'get',
		url : '/' + version + '/graph',
		dataType : 'json',
		async : false,
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

/** 노드에대한 그래프 정보가져오기
 * @param callback
 */
function getGraphInfoToNodeId(nodeId){
	
	var _return = "";
	
	$.ajax({
		type : 'get',
		url : '/' + version + '/graph/'+nodeId,
		dataType : 'json',
		async : false,
		contentType : 'application/json',
		error : function(data) {
			alert('그래프 정보 가져오기 실패');
		},
		success : function(data) {
			_return = data;
		}
	});
	
	return _return;
	
}

/** 노드에대한 그래프 정보가져오기
 * @param callback
 */
function getGraphUrls(val){
	
	var _return = "";
	
	$.ajax({
		type : 'get',
		url : '/' + version + '/graph/resource/'+val,
		dataType : 'json',
		async : false,
		contentType : 'application/json',
		error : function(data) {
			alert('그래프 정보 가져오기 실패');
		},
		success : function(data) {
			_return = data;
		}
	});
	
	return _return;
	
}