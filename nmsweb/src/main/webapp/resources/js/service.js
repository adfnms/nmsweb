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






/************************** view String edit *****************************/

/** 이벤트 정보를 table 테그 Str로 만들어준다. 
 * @param jsonObj
 */
function getTabletagToServiceJsonObj(jsonObj){
	
	var str = "";
	
	if (jsonObj["@count"] > 0) {
	
		var serviceObj = jsonObj["service"];
		str += '<table class="table table-striped">';
		
		if (jsonObj["@count"] > 1) {

			for ( var i in serviceObj) {
				str += '<tr>';
				str += '	<td><a href="#">';
				str += serviceObj[i]["serviceType"]["name"];
				str += '	</a></td>';
				str += '	<td>';
				str += '100.000%';
				str += '	</td>';
				str += '</tr>';
			}

		} else {

			str += '<tr>';
			str += '	<td><a href="#">';
			str += serviceObj["serviceType"]["name"];
			str += '	</a></td>';
			str += '	<td>';
			str += '100.000%';
			str += '	</td>';
			str += '</tr>';

		}
		str += '</table>';
	}

	return str;
}


/** 서비스 리스트를 <option> 테그 str로 바꿔줌
 * @param jsonObj
 * @returns {String}
 */
function getOptiontagToServiceList(jsonObj) {

	var services = jsonObj["services"];

	var optionStr = "";
	if (services.length > 1) {

		for ( var i in services) {
			optionStr += "<option value='"+services[i]["serviceid"]+"'>"
					+ services[i]["servicename"] + "</option>";
		}

	} else {
		optionStr += "<option value='"+services["serviceid"]+"'>"
				+ services["servicename"] + "</option>";

	}

	return optionStr;
}
