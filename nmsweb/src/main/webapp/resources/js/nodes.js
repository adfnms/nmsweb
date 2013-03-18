/**
 * Get a list of nodes. This includes the ID and node label
 * 
 * @param callback
 * @param urlData :
 *            url에 포함될 파라미터 <ex> /v1/nodes/21
 * @param data :
 *            파라미터 설정
 */
function getNodeTotalList(callback, data) {

	$.ajax({
		type : 'get',
		url : '/' + version + '/nodes',
		dataType : 'json',
		data : data,
		contentType : 'application/json',
		error : function(data) {
			alert('모든 노드정보 가져오기 실패');
		},
		success : function(data) {
			// 콜백함수
			if (typeof callback == "function") {
				callback(data);
			}else{
				console.log(data);
				return data;
			}
		}
	});
}

/**
 * Get the requested service associated with the given node, IP interface, and
 * service name
 * 
 * @param callback
 * @param nodeId
 * @param ipAddress
 * @param seviceName
 */
function searchNodeList(callback, nodeId, ipAddress, seviceName) {

	$.ajax({
		type : 'get',
		url : '/' + version + '/nodes/' + nodeId + '/ipinterfaces/' + ipAddress
				+ '/services/' + seviceName,
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

/**
 * Get a specific node, by ID
 * 
 * @param callback
 * @param nodeId
 */
function getSpecificNode(callback, nodeId) {

	if (nodeId == "") {
		alert("노드 아이디 정보가 없습니다.");
		return;
	}

	$.ajax({
		type : 'get',
		url : '/' + version + '/nodes/' + nodeId,
		dataType : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert("[" + nodeId + '] 노드 정보 가져오기 서비스 실패');
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
 * search node from serviceId
 * 
 * @param callback
 * @param ipAddress
 */
function searchNodeFromServiceId(callback, serviceId) {

	if (serviceId == "") {
		alert("서비스 정보가 없습니다.");
		return;
	}

	$.ajax({
		type : 'get',
		url : '/' + version + '/nodes/searchService/' + serviceId,
		dataType : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert("[" + ipAddress + '] 서비스 정보 검색 실패');
		},
		success : function(data) {

			data = JSON.stringify(data).replaceAll('"nodeid"', '"@id"');
			data = data.replaceAll('"nodelabel"', '"@label"');
			data = JSON.parse(data);

			// 콜백함수
			if (typeof callback == "function") {
				callback(data);
			}

		}
	});

}

/**
 * search node from ipArress
 * 
 * @param callback
 * @param ipAddress
 */
function searchNodeFromIpAddress(callback, ipAddress) {

	if (ipAddress == "") {
		alert("아이피 정보가 없습니다.");
		return;
	}

	$.ajax({
		type : 'get',
		url : '/' + version + '/nodes/searchIp/' + ipAddress,
		dataType : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert("[" + ipAddress + '] 아이피 정보 검색 실패');
		},
		success : function(data) {

			data = JSON.stringify(data).replaceAll('"nodeid"', '"@id"');
			data = data.replaceAll('"nodelabel"', '"@label"');
			data = JSON.parse(data);

			// 콜백함수
			if (typeof callback == "function") {
				callback(data);
			}

		}
	});

}

/**
 * Get the list of IP interfaces associated with the given node
 * 
 * @param callback
 * @param nodeId
 */
function getInterfacesFromNodeId(callback, nodeId) {

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

			if (typeof callback == "function") {
				callback(data);
			}

		}
	});

}

/** ************************ view String edit **************************** */

/**
 * categories 정보를 table 테그 Str로 만들어준다.
 * 
 * @param jsonObj
 *            jsonObj["categories"]
 */
function getTabletagToCategoryJsonObj(jsonObj) {
	
	var categories = jsonObj;
	var str = "";
	
	if (categories != undefined) {
		
		str = "	<div class='row-fluid'>"
			+ "		<h5>감시&nbsp;카테고리&nbsp;회원&nbsp;</h5></div>"
			+ "	<div class='row-fluid'>"
			+ "		<div class='span12 well well-small'>";
		
		if (categories.length > 1) {

			for ( var i in categories) {
				str += "<div class='row-fluid'>" + "	<a href='#'>"
						+ categories[i]["@name"] + "</a>" + "</div>";
			}

		} else {
			str += "<div class='row-fluid'>" + "	<a href='#'>"
					+ categories["@name"] + "</a>" + "</div>";
		}

		str += "</div>";
	}
	
	return str;
}