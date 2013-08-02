/** Get a list of events
 * @param callback
 * @param data
 */
function getTotalEvenstList(callback,data,notiId) {

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
				callback(data,notiId);
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
	/*********************************************/
	
	var MainDIVObj = $("<div></div>");
	var DIVobj = $("<div></div>");
	var TABLEobj = $("<table></table>");
	var TRobj = $("<tr></tr>");
	var TDobj = $("<td></td>");
	var THobj = $("<th></th>");
	var COLGROUPobj = $("<colgroup></colgroup>");
	var COLobj = $("<col></col>");
	var H5obj = $("<h5></h5>");
	var Aobj = $("<a></a>");
	
	
	/*********************************************/
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
		/*****************************************************************************************************************************************************************/
		MainDIVObj.attr("class", "row-fluid").append(
			H5obj.append().text("이벤트 목록" + "[" + jsonObj["@count"] + "]"),
			DIVobj.clone().attr("class", "span12 well well-small").attr("style", "margin-left:0px").append(
				TABLEobj.attr("class", "table").append(
					COLGROUPobj.clone().append(
						COLobj.clone().attr("width", "15%").append(),
						COLobj.clone().attr("width", "25%").append(),
						COLobj.clone().attr("width", "15%").append(),
						COLobj.clone().attr("width", "45%").append()
					),
					TRobj.clone().append(
						THobj.clone().append().text("이벤트ID"),
						THobj.clone().append().text("시간"),
						THobj.clone().append().text("상태"),
						THobj.clone().append().text("메세지")
					)
				)
			)
		);
		/*****************************************************************************************************************************************************************/
		if (jsonObj["@count"] > 1) {

			for ( var i in events) {
				
				if(events[i]["@severity"]=="CRITICAL"){
					 statusProgress = "progress-danger";
				}
				else if(events[i]["@severity"]=="MAJOR"){
					 statusProgress = "progress-caution";						
				}
				else if(events[i]["@severity"]=="MINOR"){
					 statusProgress = "progress-warning";
				}
				else if(events[i]["@severity"]=="WARNING"){
					 statusProgress = "progress-gray";
				}
				else if(events[i]["@severity"]=="NORMAL"){
					 statusProgress = "progress-info";
				}
				else if(events[i]["@severity"]=="CLEARED"){
					 statusProgress = "progress";
				}
				else if(events[i]["@severity"]=="INDETERMINATE"){
					 statusProgress = "progress-success";
				}
				
				str += "<tr>";
				str += "<td><a href='/"+version+"/search/event/eventDesc.do?eventId="+events[i]["@id"]+"'>" + events[i]["@id"]
						+ "</a></td>";
				str += "<td>"
						+ new Date(events[i]["createTime"])
								.format('yy-MM-dd hh:mm:ss') + "</td>";
				
				//str += "<th class='"+events[i]["@severity"].toLowerCase()+"'>" + events[i]["@severity"] + "</th>";
				str += '		<td class=""><div class="progress progress-striped active '+statusProgress+'  " style="margin-bottom: 0px;width: 130px; ">';
				str += '		<div class="bar" style="width:100%">' +events[i]["@severity"]+ '</div>';
				str += '		</div></td>';
				str += "<td>" + events[i]["logMessage"].replace(/<p>|<\/p>/gi,'') + "</td>";
				str += "</tr>";
				/*****************************************************************************************************************************************************************/
				TABLEobj.append(
					COLGROUPobj.clone().append(
						COLobj.clone().attr("width", "13%").append(),
						COLobj.clone().attr("width", "15%").append(),
						COLobj.clone().attr("width", "15%").append(),
						COLobj.clone().attr("width", "62%").append()
					),
					TRobj.clone().append(
						TDobj.clone().append(
							Aobj.clone().attr("href", "/"+version+"/search/event/eventDesc.do?eventId="+events[i]["@id"]+ "").append().text(events[i]["@id"])
						),
						TDobj.clone().append().text(new Date(events[i]["createTime"]).format('yy-MM-dd hh:mm:ss')),
						TDobj.clone().attr("class", "").append(
							DIVobj.clone().attr("class", "progress progress-striped active " + statusProgress +"").attr("style", "margin-bottom: 0px;width: 130px;").append(
								DIVobj.clone().attr("class", "bar").attr("style", "width:100%;").append().text(events[i]["@severity"])
							)	
						),
						TDobj.clone().append().text(events[i]["logMessage"].replace(/<p>|<\/p>/gi,''))
					)
				);
				/*****************************************************************************************************************************************************************/
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
			/*****************************************************************************************************************************************************************/
			TABLEobj.append(
				COLGROUPobj.clone().append(
					COLobj.clone().attr("width", "13%").append(),
					COLobj.clone().attr("width", "15%").append(),
					COLobj.clone().attr("width", "15%").append(),
					COLobj.clone().attr("width", "62%").append()
				),	
				TRobj.clone().append(
					TDobj.clone().append(
						Aobj.clone().attr("href", "/"+version+"/search/event/eventDesc.do?eventId="+events["@id"]+"").append().text(events["@id"])
					),
					TDobj.clone().append().text(new Date(events["createTime"]).format('yy-MM-dd hh:mm:ss')),
					THobj.clone().attr("class", ""+events["@severity"].toLowerCase()+"").append().text(events["@severity"]),
					TDobj.clone().append().text(events["logMessage"].replace(/<p>|<\/p>/gi,''))
				)
			);
			/*****************************************************************************************************************************************************************/
		}

		
		str += "</tbody></table></div></div>";
	}

	return str;
	/*************/
	//return MainDIVObj;
	/*************/
}
function getTabletagToInterfaceEventJsonObj(jsonObj){
	
	var events = jsonObj["event"];
	//var str = "";
	/*********************************************/
	var DIVobj = $("<div></div>");
	var TABLEobj = $("<table></table>");
	var TRobj = $("<tr></tr>");
	var TDobj = $("<td></td>");
	var THobj = $("<th></th>");
	var COLGROUPobj = $("<colgroup></colgroup>");
	var COLobj = $("<col></col>");
	var THEADobj = $("<thead></thead>");
	var H5obj = $("<h5></h5>");
	var Aobj = $("<a></a>");
	/*********************************************/
	if (jsonObj["@count"] > 0) {
			
		/*str = "	<div class='row-fluid'>"
				+ "		<h5>인터페이스&nbsp;이벤트&nbsp;목록&nbsp;["
				+ jsonObj["@count"]
				+ "]</h5>"
				+ "	</div>"
				+ "	<div class='row-fluid'>"
				+ "		<div class='span12 well well-small'>"
				+ "		<table class='table'>"
				+ "			<colgroup><col width='13%'/><col  width='15%'/><col  width='15%'/><col  width='62%'/></colgroup>"		
				+ "			<thead><tr><th>이벤트ID</th><th>시간</th><th>상태</th><th class='span4'>메세지</th></tr></thead>"
				+ "		</table><div id='outageScrollDiv'><table class='table'>	"
				+ "			<colgroup><col width='10%'/><col  width='15%'/><col  width='15%'/><col  width='62%'/></colgroup>"
				+ "			<tbody>";*/
				/**********************************************************************************************************************************************************/
				DIVobj.attr("class", "row-fluid").append(
					H5obj.append().text("인터페이스 이벤트 목록" + "[" + jsonObj["@count"] + "]"),
					DIVobj.clone().append(
						TABLEobj.clone().attr("class", "table").append(
							COLGROUPobj.clone().append(
								COLobj.clone().attr("width", "13%").append(),
								COLobj.clone().attr("width", "15%").append(),
								COLobj.clone().attr("width", "15%").append(),
								COLobj.clone().attr("width", "62%").append()
							),
							THEADobj.clone().append(	
								TRobj.clone().append(
									THobj.clone().append().text("이벤트ID"),
									THobj.clone().append().text("시간"),
									THobj.clone().append().text("상태"),
									THobj.clone().append().text("메세지")
								)
							)
						)
					)
				);
				/**********************************************************************************************************************************************************/
		if (jsonObj["@count"] > 1) {

			for ( var i in events) {
				
				if(events[i]["@severity"]=="CRITICAL"){
					 statusProgress = "progress-danger";
				}
				else if(events[i]["@severity"]=="MAJOR"){
					 statusProgress = "progress-caution";						
				}
				else if(events[i]["@severity"]=="MINOR"){
					 statusProgress = "progress-warning";
				}
				else if(events[i]["@severity"]=="WARNING"){
					 statusProgress = "progress-gray";
				}
				else if(events[i]["@severity"]=="NORMAL"){
					 statusProgress = "progress-info";
				}
				else if(events[i]["@severity"]=="CLEARED"){
					 statusProgress = "progress";
				}
				else if(events[i]["@severity"]=="INDETERMINATE"){
					 statusProgress = "progress-success";
				}
				
				/*str += "<tr>";
				str += "<td><a href='/"+version+"/search/event/eventDesc.do?eventId="+events[i]["@id"]+"'>" + events[i]["@id"]
						+ "</a></td>";
				str += "<td>"
						+ new Date(events[i]["createTime"])
								.format('yy-MM-dd hh:mm:ss') + "</td>";
				
				//str += "<th class='"+events[i]["@severity"].toLowerCase()+"'>" + events[i]["@severity"] + "</th>";
				str += '		<td class=""><div class="progress progress-striped active '+statusProgress+'  " style="margin-bottom: 0px;width: 130px; ">';
				str += '		<div class="bar" style="width:100%">' +events[i]["@severity"]+ '</div>';
				str += '		</div></td>';
				str += "<td>" + events[i]["logMessage"].replace(/<p>|<\/p>/gi,'') + "</td>";
				str += "</tr>";*/
				/**********************************************************************************************************************************************************/
				DIVobj.attr("class", "span12 well well-small").attr("id", "outageScrollDiv").append(
					TABLEobj.clone().attr("class", "table").append(
						COLGROUPobj.clone().append(
							COLobj.clone().attr("width", "13%").append(),
							COLobj.clone().attr("width", "15%").append(),
							COLobj.clone().attr("width", "15%").append(),
							COLobj.clone().attr("width", "62%").append()
						),
						TRobj.clone().append(
							TDobj.clone().append(
								Aobj.clone().attr("href", "/"+version+"/search/event/eventDesc.do?eventId="+events[i]["@id"]+ "").append().text(events[i]["@id"])
							),
							TDobj.clone().append().text(new Date(events[i]["createTime"]).format('yy-MM-dd hh:mm:ss')),
							TDobj.clone().attr("class", "").append(
								DIVobj.clone().attr("class", "progress progress-striped active"+statusProgress+"").attr("style", "margin-bottom: 0px;width: 130px;").append(
									DIVobj.clone().attr("class", "bar").attr("style", "width:100%").append().text(events[i]["@severity"])
								)	
							),
							TDobj.clone().append().text(events[i]["logMessage"].replace(/<p>|<\/p>/gi,''))
						)
					)
				);
				/**********************************************************************************************************************************************************/
			}

		} else {
			/*str += "<tr>";
			str += "<td><a href='/"+version+"/search/event/eventDesc.do?eventId="+events["@id"]+"'>" + events["@id"] + "</a></td>";
			str += "<td>"
					+ new Date(events["createTime"])
							.format('yy-MM-dd hh:mm:ss') + "</td>";
			str += "<th class='"+events["@severity"].toLowerCase()+"'>" + events["@severity"] + "</th>";
			str += "<td>" + events["logMessage"].replace(/<p>|<\/p>/gi,'') + "</td>";
			str += "</tr>";*/
			/**********************************************************************************************************************************************************/
			DIVobj.attr("class", "span12 well well-small").attr("id", "outageScrollDiv").append(
				TABLEobj.clone().attr("class", "table").append(
					COLGROUPobj.clone().append(
						COLobj.clone().attr("width", "13%").append(),
						COLobj.clone().attr("width", "15%").append(),
						COLobj.clone().attr("width", "15%").append(),
						COLobj.clone().attr("width", "62%").append()
					),	
					TRobj.clone().append(
						TDobj.clone().append(
							Aobj.clone().attr("href", "/"+version+"/search/event/eventDesc.do?eventId="+events["@id"]+"").append().text(events["@id"])
						),
						TDobj.clone().append().text(new Date(events["createTime"]).format('yy-MM-dd hh:mm:ss')),
						THobj.clone().attr("class", ""+events["@severity"].toLowerCase()+"").append().text(events["@severity"]),
						TDobj.clone().append().text(events["logMessage"].replace(/<p>|<\/p>/gi,''))
					)
				)
			);
			/**********************************************************************************************************************************************************/
		}

		
		//str += "</tbody></table></div></div>";
		/*****************************************/
		
		/*****************************************/
	}

	//return str;
	/***********/
	return DIVobj;
	/***********/
}
function getTabletagToServiceEventJsonObj(jsonObj){
	
	var events = jsonObj["event"];
	//var str = "";
	/*********************************************/
	var DIVobj = $("<div></div>");
	var TABLEobj = $("<table></table>");
	var TRobj = $("<tr></tr>");
	var TDobj = $("<td></td>");
	var THobj = $("<th></th>");
	var COLGROUPobj = $("<colgroup></colgroup>");
	var COLobj = $("<col></col>");
	var THEADobj = $("<thead></thead>");
	var H5obj = $("<h5></h5>");
	var Aobj = $("<a></a>");
	/*********************************************/
	if (jsonObj["@count"] > 0) {
			
		/*str = "	<div class='row-fluid'>"
				+ "		<h5>서비스&nbsp;이벤트&nbsp;목록&nbsp;["
				+ jsonObj["@count"]
				+ "]</h5>"
				+ "	</div>"
				+ "	<div class='row-fluid'>"
				+ "		<div class='span12 well well-small'>"
				+ "		<table class='table'>"
				+ "			<colgroup><col width='13%'/><col  width='15%'/><col  width='15%'/><col  width='62%'/></colgroup>"		
				+ "			<thead><tr><th>이벤트ID</th><th>시간</th><th>상태</th><th class='span4'>메세지</th></tr></thead>"
				+ "		</table><div id='outageScrollDiv'><table class='table'>	"
				+ "			<colgroup><col width='15%'/><col  width='25%'/><col  width='15%'/><col  width='45%'/></colgroup>"
				+ "			<tbody>";*/
		/**********************************************************************************************************************************************************/
		DIVobj.attr("class", "row-fluid").append(
			H5obj.append().text("서비스 이벤트 목록" + "[" + jsonObj["@count"] + "]"),
			DIVobj.clone().append(
				TABLEobj.clone().attr("class", "table").append(
					COLGROUPobj.clone().append(
						COLobj.clone().attr("width", "13%").append(),
						COLobj.clone().attr("width", "15%").append(),
						COLobj.clone().attr("width", "15%").append(),
						COLobj.clone().attr("width", "62%").append()
					),
					THEADobj.clone().append(	
						TRobj.clone().append(
							THobj.clone().append().text("이벤트ID"),
							THobj.clone().append().text("시간"),
							THobj.clone().append().text("상태"),
							THobj.clone().append().text("메세지")
						)
					)
				)
			)
		);
		/**********************************************************************************************************************************************************/
		if (jsonObj["@count"] > 1) {

			for ( var i in events) {
				
				if(events[i]["@severity"]=="CRITICAL"){
					 statusProgress = "progress-danger";
				}
				else if(events[i]["@severity"]=="MAJOR"){
					 statusProgress = "progress-caution";						
				}
				else if(events[i]["@severity"]=="MINOR"){
					 statusProgress = "progress-warning";
				}
				else if(events[i]["@severity"]=="WARNING"){
					 statusProgress = "progress-gray";
				}
				else if(events[i]["@severity"]=="NORMAL"){
					 statusProgress = "progress-info";
				}
				else if(events[i]["@severity"]=="CLEARED"){
					 statusProgress = "progress";
				}
				else if(events[i]["@severity"]=="INDETERMINATE"){
					 statusProgress = "progress-success";
				}
				
				/*str += "<tr>";
				str += "<td><a href='/"+version+"/search/event/eventDesc.do?eventId="+events[i]["@id"]+"'>" + events[i]["@id"]
						+ "</a></td>";
				str += "<td>"
						+ new Date(events[i]["createTime"])
								.format('yy-MM-dd hh:mm:ss') + "</td>";
				
				//str += "<th class='"+events[i]["@severity"].toLowerCase()+"'>" + events[i]["@severity"] + "</th>";
				str += '		<td class=""><div class="progress progress-striped active '+statusProgress+'  " style="margin-bottom: 0px;width: 130px; ">';
				str += '		<div class="bar" style="width:100%">' +events[i]["@severity"]+ '</div>';
				str += '		</div></td>';
				str += "<td>" + events[i]["logMessage"].replace(/<p>|<\/p>/gi,'') + "</td>";
				str += "</tr>";*/
				/**********************************************************************************************************************************************************/
				DIVobj.attr("class", "span12 well well-small").attr("id", "outageScrollDiv").append(
					TABLEobj.clone().attr("class", "table").append(
						COLGROUPobj.clone().append(
							COLobj.clone().attr("width", "15%").append(),
							COLobj.clone().attr("width", "25%").append(),
							COLobj.clone().attr("width", "15%").append(),
							COLobj.clone().attr("width", "45%").append()
						),
						TRobj.clone().append(
							TDobj.clone().append(
								Aobj.clone().attr("href", "/"+version+"/search/event/eventDesc.do?eventId="+events[i]["@id"]+ "").append().text(events[i]["@id"])
							),
							TDobj.clone().append().text(new Date(events[i]["createTime"]).format('yy-MM-dd hh:mm:ss')),
							TDobj.clone().attr("class", "").append(
								DIVobj.clone().attr("class", "progress progress-striped active"+statusProgress+"").attr("style", "margin-bottom: 0px;width: 130px;").append(
									DIVobj.clone().attr("class", "bar").attr("style", "width:100%").append().text(events[i]["@severity"])
								)	
							),
							TDobj.clone().append().text(events[i]["logMessage"].replace(/<p>|<\/p>/gi,''))
						)
					)
				);
				/**********************************************************************************************************************************************************/
			}

		} else {
			/*str += "<tr>";
			str += "<td><a href='/"+version+"/search/event/eventDesc.do?eventId="+events["@id"]+"'>" + events["@id"] + "</a></td>";
			str += "<td>"
					+ new Date(events["createTime"])
							.format('yy-MM-dd hh:mm:ss') + "</td>";
			str += "<th class='"+events["@severity"].toLowerCase()+"'>" + events["@severity"] + "</th>";
			str += "<td>" + events["logMessage"].replace(/<p>|<\/p>/gi,'') + "</td>";
			str += "</tr>";*/
			/**********************************************************************************************************************************************************/
			DIVobj.attr("class", "span12 well well-small").attr("id", "outageScrollDiv").append(
				TABLEobj.clone().attr("class", "table").append(
					COLGROUPobj.clone().append(
						COLobj.clone().attr("width", "13%").append(),
						COLobj.clone().attr("width", "15%").append(),
						COLobj.clone().attr("width", "15%").append(),
						COLobj.clone().attr("width", "62%").append()
					),	
					TRobj.clone().append(
						TDobj.clone().append(
							Aobj.clone().attr("href", "/"+version+"/search/event/eventDesc.do?eventId="+events["@id"]+"").append().text(events["@id"])
						),
						TDobj.clone().append().text(new Date(events["createTime"]).format('yy-MM-dd hh:mm:ss')),
						THobj.clone().attr("class", ""+events["@severity"].toLowerCase()+"").append().text(events["@severity"]),
						TDobj.clone().append().text(events["logMessage"].replace(/<p>|<\/p>/gi,''))
					)
				)
			);
			/**********************************************************************************************************************************************************/
		}

		
		//str += "</tbody></table></div></div>";
	}

	//return str;
	/***********/
	return DIVobj;
	/***********/
}
/** 이벤트 정보를 div 형태로 만들어줌 
 * @param jsonObj
 */
function getEventinfoBox(jsonObj){
	//console.log(jsonObj);
	small= jsonObj["event"]["@severity"].toLowerCase();
	/*var eventInfoStr = 	'<div class="row-fluid">'+
						'	<div class="span12">'+
						'		<h5>이벤트&nbsp;['+jsonObj["event"]["@id"]+']</h5>'+
						'	</div>'+
						'</div>'+*/
	var eventInfoStr =	'<div class="row-fluid">'+
						'	<div class="span12 well well-small">'+
						'	<table class="table table-striped">'+
						'		<tr>'+
						'			<th>상태</th>'+
						'			<td class="'+small+'">'+jsonObj["event"]["@severity"]+'</td>'+
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