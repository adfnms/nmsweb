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

/**
 * Get the IP interface for the given node 
 * 
 * @param callback
 * @param nodeId
 * @param ipAddress
 */
function getServiceFromNodeId(callback, nodeId) {

	if (nodeId == "") {
		alert("노드 ID가 없습니다.");
		return;
	}

	$.ajax({
		type : 'get',
		url : '/' + version + '/nodes/' + nodeId + '/ipinterfaces',
		dataType : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert("[" + nodeId + '] 인터페이스 정보 검색 실패');
		},
		success : function(data) {

			var jsonObj = data;

			if (jsonObj["@count"] > 0) {
				var ipInterfaceObj = jsonObj["ipInterface"];

				if (jsonObj["@count"] > 1) {

					for ( var i in ipInterfaceObj) {
						getServiceFromNodeidIpaddress(callback, nodeId, ipInterfaceObj[i]["ipAddress"]);
					}

				} else {

					getServiceFromNodeidIpaddress(callback, nodeId, ipInterfaceObj["ipAddress"]);

				}

			}

		}
	});

}

/**Get the IP interface for the given node
 * @param callback
 * @param nodeId
 * @param ipAddress
 */
function getServiceFromNodeidIpaddress(callback, nodeId, ipAddress){
	
	$.ajax({
		type : 'get',
		url : '/' + version + '/nodes/' + nodeId
				+ '/ipinterfaces/'
				+ ipAddress
				+ '/services',
		dataType : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert("[" + ipAddress + '] 아이피 정보 검색 실패');
		},
		success : function(data) {

			// 콜백함수
			if (typeof callback == "function") {
				callback(data, ipAddress);
			}

		}
	});
	
}