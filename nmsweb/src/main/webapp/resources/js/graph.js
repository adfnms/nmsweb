/** 그래프를 보여줄수 있는 node 리스트
 * @param callback
 *  /v1/graph
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

/** 노드에대한 리소스 아이디 정보가져오기
 * @param callback
 * /v1/graph/nodeId
 * 
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
 * /v1/graph/resource/node[nodeId].responseTime[node]
 * ex)/v1/graph/resource/node[135].responseTime[192.168.0.200]
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
			
			console.log(data);
			//alert('그래프 정보 가져오기 실패');
			/*data length == 0 일때*/
		},
		success : function(data) {
			
			_return = data;
		}
	});
	
	return _return;
	
}



function getTimePeriodGraphUrls(graphVal,relativeTime){
	
	var _return = "";
	
	$.ajax({
		type : 'get',
		url : '/' + version + '/graph/resource/'+graphVal+'/'+relativeTime,
		dataType : 'json',
		async : false,
		contentType : 'application/json',
		error : function(data) {
			
			console.log(data);
			//alert('그래프 정보 가져오기 실패');
		},
		success : function(data) {
			
			_return = data;
		}
	});
	
	return _return;
	
}

function getTimePeriodGraphUrlsCustom(graphVal,relativeTime,startDate,endDate){
	
	var _return = "";
	
	$.ajax({
		type : 'get',
		url : '/' + version + '/graph/resource/'+graphVal+'/'+relativeTime+'/'+startDate+'/'+endDate,
		dataType : 'json',
		async : false,
		contentType : 'application/json',
		error : function(data) {
			
			console.log(data);
			//alert('그래프 정보 가져오기 실패');
		},
		success : function(data) {
			_return = data;
		}
	});
	
	return _return;
	
}
