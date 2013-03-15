/**
 * Get a list of outages
 * 
 * @param callback
 */
function getTotalOutagesList(callback, data) {
	$.ajax({
		type : 'get',
		url : '/' + version + '/outages',
		dataType : 'json',
		data : data,
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

/**
 * Get a list of outages for nodeId [recent] 최근 recentCount개 의 outages 목록
 * 
 * @param callback
 * @param nodeId
 * @param recentCount
 */
function getOutagesForNode(callback, nodeId, recentCount) {

	if (nodeId == null) {
		alert("노드 아이디가 없습니다.");
		return;
	}

	// var data = "query=this_.nodeId = '"+nodeId+"' AND this_.iflostservice >
	// '"+new Date().format("yyyy-MM-ddTHH:MM:ss")+"'";
	var query = encodeURI("query=this_.nodeId = '" + nodeId + "'");
	var filter = "&orderBy=ifLostService&order=desc&limit=" + recentCount;

	getTotalOutagesList(callback, query + filter);
}

/**
 * Get a list of outages for nodeId & ipAddress [recent] 최근 recentCount개 의
 * outages 목록
 * 
 * @param callback
 * @param nodeId
 * @param recentCount
 */
function getOutagesForInterface(callback, nodeId, ipAddress, recentCount) {

	if (nodeId == null) {
		alert("노드 아이디가 없습니다.");
		return;
	}

	// var data = "query=this_.nodeId = '"+nodeId+"' AND this_.iflostservice >
	// '"+new Date().format("yyyy-MM-ddTHH:MM:ss")+"'";
	var query = encodeURI("query=this_.nodeId = '" + nodeId
			+ "' AND this_.ipaddr = '" + ipAddress + "'");
	var filter = "&orderBy=ifLostService&order=desc&limit=" + recentCount;

	getTotalOutagesList(callback, query + filter);
}

/** ************************ view String edit **************************** */

/**
 * Outage 정보를 table 테그 Str로 만들어준다.
 * 
 * @param jsonObj
 */
function getTabletagToOutageJsonObj(jsonObj, nodeId) {

	var outages = jsonObj["outage"];

	var str = "";

	if (jsonObj["@count"] > 0) {

		str = "	<div class='row-fluid'>"
				+ "		<h5>중단&nbsp;목록&nbsp;["
				+ jsonObj["@count"]
				+ "]</h5>"
				+ "	</div>"
				+ "	<div class='row-fluid'>"
				+ "		<div class='span12 well well-small'>"
				+ "<table class='table table-striped'>"
				+ "<tr><th>Interface</th><th>service</th><th>Lost</th><th>Regained</th><th>Id</th></tr>";
		if (jsonObj["@count"] > 1) {

			for ( var i in outages) {

				str += "<tr>";
				str += "<td><a href='/" + version
						+ "/monitering/node/interfaceDesc.do?nodeId=" + nodeId
						+ "&intf=" + outages[i]["ipAddress"] + "'>"
						+ outages[i]["ipAddress"] + "</a></td>";
				str += "<td><a href='#'>"
						+ nullCheckJsonObject(
								outages[i]["serviceLostEvent"]["serviceType"],
								"name") + "</a></td>";
				str += "<td>"
						+ new Date(nullCheckJsonObject(
								outages[i]["serviceLostEvent"], "time"))
								.format('yy-MM-dd hh:mm:ss') + "</td>";
				str += "<td>"
						+ new Date(nullCheckJsonObject(
								outages[i]["serviceRegainedEvent"], "time"))
								.format('yy-MM-dd hh:mm:ss') + "</td>";
				str += "<td><a href='#'>" + outages[i]["@id"] + "</a></td>";
				str += "</tr>";
			}

		} else {
			str += "<tr>";
			str += "<td><a href='#'>" + outages["ipAddress"] + "</a></td>";
			str += "<td><a href='#'>"
					+ nullCheckJsonObject(
							outages["serviceLostEvent"]["serviceType"], "name")
					+ "</a></td>";
			str += "<td>"
					+ new Date(nullCheckJsonObject(outages["serviceLostEvent"],
							"time")).format('yy-MM-dd hh:mm:ss') + "</td>";
			str += "<td>"
					+ new Date(nullCheckJsonObject(
							outages["serviceRegainedEvent"], "time"))
							.format('yy-MM-dd hh:mm:ss') + "</td>";
			str += "<td><a href='#'>" + outages["@id"] + "</a></td>";
			str += "</tr>";
		}

		str += "</table></div>";

	}
	return str;
}