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

/** Current Outages Lisgt
 * @param callback
 */
function getCurrentOutagesForNode(callback) {

	//current outage query
	var query = "query="+encodeURI("this_.svcregainedeventid is null&limit=0");
	
	getTotalOutagesList(callback,query);
	
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


/**Get information related with certaain outage_ID
 * @param callback
 * @param outageId
 */
function seachOutageToOutageId(callback, outageId){
	$.ajax({
		type : 'get',
		url : '/' + version + '/outages/'+outageId,
		dataType : 'json',
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

/** Get outages's information related with certain nodeID
 * @param callback
 * @param outageId
 */
function seachOutageToNodeId(callback, nodeId){
	$.ajax({
		type : 'get',
		url : '/' + version + '/outages/forNode/'+nodeId,
		dataType : 'json',
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
/** ************************ view String edit **************************** */

/**
 * Outage 정보를 table 테그 Str로 만들어준다.
 * 
 * @param jsonObj
 */
function getTabletagToOutageJsonObj(jsonObj) {

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
				+ "<tr><th>인터페이스</th><th>서비스</th><th>중단 시간</th><th>회복 시간</th><th>ID</th></tr>";
		if (jsonObj["@count"] > 1) {

			for ( var i in outages) {

				str += "<tr>";
				str += "<td><a href='/" + version
						+ "/search/node/interfaceDesc.do?nodeId=" + outages[i]["serviceLostEvent"]["nodeId"]
						+ "&intf=" + outages[i]["ipAddress"] + "'>"
						+ outages[i]["ipAddress"] + "</a></td>";
				str += "<td><a href='/"
						+ version
						+ "/search/service/serviceDesc?nodeId="
						+ outages[i]["serviceLostEvent"]["nodeId"]
						+ "&intf="
						+ outages[i]["ipAddress"]
						+ "&serviceNm="
						+ nullCheckJsonObject(
								outages[i]["serviceLostEvent"]["serviceType"],
								"name")
						+ "'>"
						+ nullCheckJsonObject(
								outages[i]["serviceLostEvent"]["serviceType"],
								"name") + "</a></td>";
				str += "<td class=\"text-error\">"
						+ new Date(nullCheckJsonObject(
								outages[i]["serviceLostEvent"], "time"))
								.format('yy-MM-dd hh:mm:ss') + "</td>";
				str += "<td>"
						+ new Date(nullCheckJsonObject(
								outages[i]["serviceRegainedEvent"], "time"))
								.format('yy-MM-dd hh:mm:ss') + "</td>";
				str += "<td><a href='/" + version
						+ "/search/outage/outageDesc?outageId="
						+ outages[i]["@id"] + "'>" + outages[i]["@id"]
						+ "</a></td>";
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

/**
 * Outage 정보를 Str테그로 만들어준다.
 * 
 * @param jsonObj
 */
function getOutageInfoBox(jsonObj) {

	var outageObj = jsonObj["outage"];

	var outageInfoStr = '<div class="row-fluid">' + '	<h5>중단['
			+ outageObj["@id"]
			+ ']</h5>'
			+ '</div>'
			+ '<div class="row-fluid">'
			+ '	<div class="span12 well well-small">'
			+ '		<table class="table table-striped">'
			+ '			<tr>'
			+ '				<th>노드</th>'
			+ '				<td>'
			+ '					<a href="/'
			+ version
			+ '/search/node/nodeDesc.do?nodeId='
			+ outageObj["serviceLostEvent"]["nodeId"]
			+ '">'
			+ outageObj["serviceLostEvent"]["nodeLabel"]
			+ '					</a>'
			+ '				</td>'
			+ '				<th>중단 시간</th>'
			+ '				<td>'
			+ new Date(outageObj["serviceLostEvent"]["time"])
					.format('yy-MM-dd hh:mm:ss')
			+ '</td>'
			+ '				<th>중단 이벤트</th>'
			+ '				<td><a href="/'
			+ version
			+ '/search/event/eventDesc.do?eventId='
			+ outageObj["serviceLostEvent"]["@id"]
			+ '">'
			+ outageObj["serviceLostEvent"]["@id"]
			+ '</a></td>'
			+ '			</tr>'
			+ '			<tr>'
			+ '				<th>인터페이스</th>'
			+ '				<td><a href="/'
			+ version
			+ '/search/node/interfaceDesc.do?nodeId='
			+ outageObj["serviceLostEvent"]["nodeId"]
			+ '&intf='
			+ outageObj["ipAddress"]
			+ '">'
			+ outageObj["ipAddress"]
			+ '</a></td>'
			+ '				<th>회복 시간</th>'
			+ '				<td>'
			+ outageObj["serviceRegainedEvent"]["time"]
			+ '</td>'
			+ '				<th>회복 이벤트</th>'
			+ '				<td><a href="/'
			+ version
			+ '/search/event/eventDesc.do?eventId='
			+ outageObj["serviceRegainedEvent"]["@id"]
			+ '">'
			+ outageObj["serviceRegainedEvent"]["@id"]
			+ '</a></td>'
			+ '			</tr>'
			+ '			<tr>'
			+ '				<th>서비스</th>'
			+ '				<td>'
			+ '					<a href="/'
			+ version
			+ '/search/service/serviceDesc?nodeId='
			+ outageObj["serviceLostEvent"]["nodeId"]
			+ '&intf='
			+ outageObj["ipAddress"]
			+ '&serviceNm='
			+ nullCheckJsonObject(
					outageObj["serviceRegainedEvent"]["serviceType"], "name")
			+ '">'
			+ nullCheckJsonObject(
					outageObj["serviceRegainedEvent"]["serviceType"], "name")
			+ '					</a>' + '				</td>' + '				<td colspan="4"></td>'
			+ '			</tr>' + '		</table>' + '	</div>' + '</div>';

	return outageInfoStr;
}

/**
 * Outage search 정보를 table 테그 Str로 만들어준다.
 * 
 * @param jsonObj
 */
function getTabletagToOutageSearchJsonObj(jsonObj) {
	var outages = jsonObj["outage"];
	
	var str = "";
	if (jsonObj["@count"] != "undefined" && jsonObj["@count"] != 0) {

		if (jsonObj["@count"] > 1) {

			for ( var i in outages) {

				str += "<tr>";
				str += "<td><a href='/" + version
						+ "/search/node/interfaceDesc.do?nodeId=" + outages[i]["serviceLostEvent"]["nodeId"]
						+ "&intf=" + outages[i]["ipAddress"] + "'>"
						+ outages[i]["ipAddress"] + "</a></td>";
				str += "<td><a href='/"
						+ version
						+ "/search/service/serviceDesc?nodeId="
						+ outages[i]["serviceLostEvent"]["nodeId"]
						+ "&intf="
						+ outages[i]["ipAddress"]
						+ "&serviceNm="
						+ nullCheckJsonObject(
								outages[i]["serviceLostEvent"]["serviceType"],
								"name")
						+ "'>"
						+ nullCheckJsonObject(
								outages[i]["serviceLostEvent"]["serviceType"],
								"name") + "</a></td>";
				str += "<td class=\"text-error\">"
						+ new Date(nullCheckJsonObject(
								outages[i]["serviceLostEvent"], "time"))
								.format('yy-MM-dd hh:mm:ss') + "</td>";
				str += "<td>"
						+ new Date(nullCheckJsonObject(
								outages[i]["serviceRegainedEvent"], "time"))
								.format('yy-MM-dd hh:mm:ss') + "</td>";
				str += "<td><a href='/" + version
						+ "/search/outage/outageDesc?outageId="
						+ outages[i]["@id"] + "'>" + outages[i]["@id"]
						+ "</a></td>";
				str += "</tr>";
			}

		} else {
			outages = outages == null ? jsonObj : jsonObj["outage"];
			str += "<tr>";
			str += "<td><a href='/" + version
					+ "/search/node/interfaceDesc.do?nodeId=" + outages["serviceLostEvent"]["nodeId"]
					+ "&intf=" + outages["ipAddress"] + "'>"
					+ outages["ipAddress"] + "</a></td>";
			str += "<td><a href='/"
					+ version
					+ "/search/service/serviceDesc?nodeId="
					+ outages["serviceLostEvent"]["nodeId"]
					+ "&intf="
					+ outages["ipAddress"]
					+ "&serviceNm="
					+ nullCheckJsonObject(
							outages["serviceLostEvent"]["serviceType"],
							"name")
					+ "'>"
					+ nullCheckJsonObject(
							outages["serviceLostEvent"]["serviceType"],
							"name") + "</a></td>";
			str += "<td class=\"text-error\">"
					+ new Date(nullCheckJsonObject(
							outages["serviceLostEvent"], "time"))
							.format('yy-MM-dd hh:mm:ss') + "</td>";
			str += "<td>"
					+ new Date(nullCheckJsonObject(
							outages["serviceRegainedEvent"], "time"))
							.format('yy-MM-dd hh:mm:ss') + "</td>";
			str += "<td><a href='/" + version
					+ "/search/outage/outageDesc?outageId="
					+ outages["@id"] + "'>" + outages["@id"]
					+ "</a></td>";
			str += "</tr>";
		}

	}
	return str;
}
