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

/** Get a list of events For dashboard
 * @param callback
 * @param data
 */
function getTotalEvenstListForDashboard(callback,data, limit) {

	$.ajax({
		type : 'get',
		url : '/' + version + '/eventquery',
		dataType : 'json',
		contentType : 'application/json',
		data : "eventseverity"+data+"&limit="+limit,
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
				+ "		<table class='table'>"
				+ "			<colgroup><col width='15%'/><col  width='25%'/><col  width='15%'/><col  width='45%'/></colgroup>"		
				+ "			<thead><tr><th>이벤트ID</th><th>시간</th><th>상태</th><th class='span4'>메세지</th></tr></thead>"
				+ "		</table><div id='outageScrollDiv'><table class='table'>	"
				+ "			<colgroup><col width='15%'/><col  width='25%'/><col  width='15%'/><col  width='45%'/></colgroup>"
				+ "			<tbody>";
		if (jsonObj["@count"] > 1) {

			for ( var i in events) {
				str += "<tr>";
				str += "<td><a href='/"+version+"/search/event/eventDesc.do?eventId="+events[i]["@id"]+"'>" + events[i]["@id"]
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
			str += "<td><a href='/"+version+"/search/event/eventDesc.do?eventId="+events["@id"]+"'>" + events["@id"] + "</a></td>";
			str += "<td>"
					+ new Date(events["createTime"])
							.format('yy-MM-dd hh:mm:ss') + "</td>";
			str += "<th class='"+events["@severity"].toLowerCase()+"'>" + events["@severity"] + "</th>";
			str += "<td>" + events["logMessage"].replace(/<p>|<\/p>/gi,'') + "</td>";
			str += "</tr>";
		}

		
		str += "</tbody></table></div></div>";
	}

	return str;
}

/** 이벤트 정보를 div 형태로 만들어줌 
 * @param jsonObj
 */
function getEventinfoBox(jsonObj){
	console.log(jsonObj);
	var eventInfoStr = 	'<div class="row-fluid">'+
						'	<div class="span12">'+
						'		<h5>이벤트&nbsp;['+jsonObj["event"]["@id"]+']</h5>'+
						'	</div>'+
						'</div>'+
						'<div class="row-fluid">'+
						'	<div class="span12 well well-small">'+
						'	<table class="table table-striped">'+
						'		<tr>'+
						'			<th>상태</th>'+
						'			<td>'+jsonObj["event"]["@severity"]+'</td>'+
						'			<th>노드</th>'+
						'			<td>'+
						'				<a href="/'+version+'/search/node/nodeDesc.do?nodeId='+jsonObj["event"]["nodeId"]+'">'+
											jsonObj["event"]["nodeLabel"]+
						'				</a>'+
						'			</td>'+
						'			<th>확인자</th>'+
						'			<td></td>'+
						'		</tr>'+
						'		<tr>'+
						'			<th>시간</th>'+
						'			<td>'+new Date(jsonObj["event"]["time"]).format('yy-MM-dd hh:mm:ss')+'</td>'+
						'			<th>인터페이스</th>'+
						'			<td>'+
						'				<a href="/'+version+'/search/node/interfaceDesc.do?nodeId='+jsonObj["event"]["nodeId"]+'&intf='+jsonObj["event"]["ipAddress"]+'">'+
											jsonObj["event"]["ipAddress"]+
						'				</a>'+
						'			</td>'+
						'			<th>승인 시간</th>'+
						'			<td></td>'+
						'		</tr>'+
						'		<tr>'+
						'			<th>서비스</th>'+
						'			<td>'+
						'				<a href="/'+version+'/search/service/serviceDesc?nodeId='+jsonObj["event"]["nodeId"]+'&intf='+jsonObj["event"]["ipAddress"]+'&serviceNm='+nullCheckJsonObject(jsonObj["event"]["serviceType"], ["name"])+'">'+
											nullCheckJsonObject(jsonObj["event"]["serviceType"], ["name"])+
						'				</a>'+
						'			</td>'+
						'			<td colspan="4"></td>'+
						'		</tr> '+
						'		<tr>'+
						'			<th>Uei</th>'+
						'			<td>'+jsonObj["event"]["uei"]+'</td>'+
						'			<td colspan="4"></td>'+
						'		</tr>'+
						'	</table>'+
						'	</div>'+
						'</div>';
	return eventInfoStr;
	
}

/** 이벤트 정보를 div 형태로 만들어줌 
 * @param jsonObj
 */
function getEventLogBox(jsonObj){

	var eventLogStr = 	'<div class="row-fluid">'+
						'	<div class="span12">'+
						'		<h5>로그&nbsp;메시지</h5>'+
						'	</div>'+
						'</div>'+
						'<div class="row-fluid">'+
						'	<div class="span12 well well-small">'+
								jsonObj["event"]["logMessage"]+
						'	</div>'+
						'</div>';
	
	return eventLogStr;

}

/** 이벤트 정보를 div 형태로 만들어줌 
 * @param jsonObj
 */
function getEventDescBox(jsonObj){

	var eventDescStr = 	'<div class="row-fluid">'+
						'	<div class="span12">'+
						'		<h5>설명</h5>'+
						'	</div>'+
						'</div>'+
						'<div class="row-fluid">'+
						'	<div class="span12 well well-small">'+
								jsonObj["event"]["description"]+
						'	</div>'+
						'</div>';
	
	return eventDescStr;

}