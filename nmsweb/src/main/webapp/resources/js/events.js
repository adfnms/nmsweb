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

/** Get a list of outages for Interface [recent]
 * 최근 recentCount개 의 Events 목록
 * @param callback
 * @param nodeId
 * @param recentCount
 */
function getEventsForInterface(callback, nodeId, ipAddress, recentCount) {

	if(nodeId == null){
		alert("노드 아이디가 없습니다.");
		return;
	}
	
	//var data = "query=this_.nodeId = '"+nodeId+"' AND this_.iflostservice > '"+new Date().format("yyyy-MM-ddTHH:MM:ss")+"'";
	var query = encodeURI("query=this_.nodeId = '"+nodeId+"' AND this_.ipaddr = '"+ipAddress+"'");
	var filter ="&orderBy=eventTime&order=desc&limit="+recentCount;

	getTotalEvenstList(callback,query+filter);
}





/************************** view String edit *****************************/

/** 이벤트 정보를 table 테그 Str로 만들어준다. 
 * @param jsonObj
 */
function getTabletagToEventJsonObj(jsonObj){
	
	var events = jsonObj["event"];
	var str = "";
	if (jsonObj["@count"] > 0) {

		str = "	<div class='row-fluid'>"
				+ "		<h5>이벤트&nbsp;목록&nbsp;["
				+ jsonObj["@count"]
				+ "]</h5>"
				+ "	</div>"
				+ "	<div class='row-fluid'>"
				+ "		<div class='span12 well well-small'>"
				+ "<table class='table'>"
				+ "<tr><th>EventId</th><th>time</th><th>stats</th><th class='span4'>Message</th></tr>";
		if (jsonObj["@count"] > 1) {

			for ( var i in events) {
				str += "<tr>";
				str += "<td><a href='#'>" + events[i]["@id"]
						+ "</a></td>";
				str += "<td>"
						+ new Date(events[i]["createTime"])
								.format('yy-MM-dd hh:mm:ss') + "</td>";
				str += "<th class='"+events[i]["@severity"].toLowerCase()+"'>" + events[i]["@severity"] + "</th>";
				str += "<td>" + events[i]["logMessage"].replace(/<p>|<\/p>/gi,'') + "</td>";
				str += "</tr>";
			}

		} else {
			str += "<tr>";
			str += "<td><a href='#'>" + events["@id"] + "</a></td>";
			str += "<td>"
					+ new Date(events["createTime"])
							.format('yy-MM-dd hh:mm:ss') + "</td>";
			str += "<td>" + events["@severity"] + "</td>";
			str += "<td>" + events["logMessage"] + "</td>";
			str += "</tr>";
		}

		
		str += "</table></div>";
	}

	return str;
}