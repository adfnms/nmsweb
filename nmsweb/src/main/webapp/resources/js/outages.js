/**
 * Get a list of outages
 * 
 * @param callback
 */
function getTotalOutagesList(callback, data,ipaddr,nodeId, serviceNm) {
	
	$.ajax({
		type : 'get',
		url : '/' + version + '/outages',
		dataType : 'json',
		data : data,
		contentType : 'application/json',
		error : function(data) {
			alert("장애목록 가져오기 실패");
		},
		success : function(data) {
			//�깃났 ���곗씠��遺덈윭�ㅺ린
			
			if (typeof callback == "function") {
				callback(data,ipaddr,nodeId, serviceNm);
			}
		}
	});
}

/** Current Outages List
 * @param callback
 */
function getCurrentOutagesForNode(callback) {

	//current outage query
	var query = "query="+encodeURI("this_.svcregainedeventid is null&limit=0");
	
	getTotalOutagesList(callback,query);
	
}

/**
 * Get a list of outages for nodeId [recent] & recentCount
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
 * Get a list of outages for nodeId & ipAddress [recent] & recentCount & serviceNm
 * 
 * @param callback
 * @param nodeId
 * @param recentCount
 */
function getOutagesForInterface(callback, nodeId, ipAddress, recentCount, serviceNm) {
	
	if (nodeId == null) {
		alert("노드 아이디가 없습니다.");
		return;
	}

	// var data = "query=this_.nodeId = '"+nodeId+"' AND this_.iflostservice >
	// '"+new Date().format("yyyy-MM-ddTHH:MM:ss")+"'";
	var query = encodeURI("query=this_.nodeId = '" + nodeId
			+ "' AND this_.ipaddr = '" + ipAddress + "'");
	var filter = "&orderBy=ifLostService&order=desc&limit=" + recentCount;

	//var ipAddress = "null";
	getTotalOutagesList(callback, query + filter,ipAddress,nodeId, serviceNm);
	
}


/**Get information related with certain outage_ID
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
			alert('중단 아이디가 없습니다.');
		},
		success : function(data) {
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
			alert('노드 아이디가 없습니다.');
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
 * outage 
 * 메뉴의 검색 -> 노드 검색 -> 노드 목록에서 node를 클릭 시 새로 이동하는 화면단의 이벤트 장애 목록
 * @param jsonObj
 */
function getTabletagToOutageJsonObj(jsonObj) {
	var outages = jsonObj["outage"];

	//var str = "";
	/*********************************************/
	var DIVobj = $("<div></div>");
	var TABLEobj = $("<table></table>");
	var TRobj = $("<tr></tr>");
	var THobj = $("<th></th>");
	var TDobj = $("<td></td>");
	var H5obj = $("<h5></h5>");
	var Aobj = $("<a></a>");
	var FONTobj = $("<font></font>");
	/*********************************************/
	if (jsonObj["@count"] > 0) {

		/*str = "	<div class='row-fluid'>"
				+ "		<h5>�대깽��nbsp;�μ븷&nbsp;紐⑸줉["
				+ jsonObj["@count"]
				+ "]</h5>"
				+ "	</div>"
				+ "	<div class='row-fluid'>"
				+ "		<div class='span12 well well-small'>"
				+ "<table class='table table-striped'>"
				+ "<tr><th>�명꽣�섏씠��/th><th>�μ븷 �쒓컙</th><th>�뚮났 �쒓컙</th><th>ID</th></tr>";*/
		/**********************************************************************************************************************************************/
		DIVobj.attr("class", "row-fluid").append(
				H5obj.append().text("이벤트 장애 목록" + "[" + jsonObj["@count"] + "]"),
				DIVobj.clone().attr("class", "span12 well well-small").attr("style", "margin-left:0px").append(
					TABLEobj.attr("class", "table table-striped").append(
						TRobj.clone().append(
							THobj.clone().append().text("인터페이스"),
							THobj.clone().append().text("상태"),
							THobj.clone().append().text("서비스"),
							THobj.clone().append().text("장애 시간"),
							THobj.clone().append().text("회복 시간"),
							THobj.clone().append().text("ID")
						)
					)
				)
			);
		/**********************************************************************************************************************************************/
		if (jsonObj["@count"] > 1) {

			for ( var i in outages) {
				var nullchenkserviceLostEvent =nullCheckJsonObject(outages[i]["serviceLostEvent"], ["time"]);
				var nullchenkserviceRegainedEvent =nullCheckJsonObject(outages[i]["serviceRegainedEvent"], ["time"]);
		
				/*str += "<tr>";
				str += "	<td><a href='/" + version+ "/search/node/interfaceDesc.do?nodeId=" + outages[i]["serviceLostEvent"]["nodeId"]+ "&intf=" + outages[i]["ipAddress"] + "'>"+ outages[i]["ipAddress"] + "</a></td>";
				str += "	<td class=\"text-error\">"+ new Date(nullchenkserviceLostEvent).format('yy-MM-dd hh:mm:ss') + "</td>";
				str += "	<td class=\"text-success\">"+ new Date(nullchenkserviceRegainedEvent).format('yy-MM-dd hh:mm:ss') + "</td>";
				str += "	<td><a href='/" + version+ "/search/outage/outageDesc?outageId="+ outages[i]["@id"] + "'>" + outages[i]["@id"]+ "</a></td>";
				str += "</tr>";*/
				/**********************************************************************************************************************************************/
				TABLEobj.append(
						TRobj.clone().append(
							TDobj.clone().append(
								FONTobj.clone().attr("color", "gray").text(outages[i]["ipAddress"])
								//Pobj.clone().attr("class", "text-muted").text(outages[i]["ipAddress"])
							),
							TDobj.clone().append().text(outages[i]["serviceLostEvent"]["@severity"]),
							TDobj.clone().append().text(outages[i]["monitoredService"]["serviceType"]["name"]),
							TDobj.clone().append().text(new Date(nullchenkserviceLostEvent).format('yy-MM-dd hh:mm:ss')),
							TDobj.clone().append().text(new Date(nullchenkserviceRegainedEvent).format('yy-MM-dd hh:mm:ss')),
							TDobj.clone().append(
								Aobj.clone().attr("href", "/" + version + "/search/outage/outageDesc?outageId=" + outages[i]["@id"]).append().text(outages[i]["@id"])
							)
						)
					);
				/**********************************************************************************************************************************************/
			}

		} else {
			var nullchenkserviceLostEvent =nullCheckJsonObject(outages["serviceLostEvent"], ["time"]);
			var nullchenkserviceRegainedEvent =nullCheckJsonObject(outages["serviceRegainedEvent"], ["time"]);
			
			/*str += "<tr>";
			str += "<td><a href='#'>" + outages["ipAddress"] + "</a></td>";
			str += "<td><a href='#'>"
					+ nullchenk
					+ "</a></td>";
			str += "<td>"
					+ new Date(nullchenk).format('yy-MM-dd hh:mm:ss') + "</td>";
			str += "<td>"
					+ new Date(nullchenk).format('yy-MM-dd hh:mm:ss') + "</td>";
			str += "<td><a href='#'>" + outages["@id"] + "</a></td>";
			str += "</tr>";*/
			/**********************************************************************************************************************************************/
			TABLEobj.append(
				TRobj.clone().append(
					TDobj.clone().append(
						Aobj.clone().attr("href", "#").append().text(outages["@id"])
					),
					TDobj.clone().append().text(new Date(nullchenkserviceLostEvent).format('yy-MM-dd hh:mm:ss')),
					TDobj.clone().append().text(new Date(nullchenkserviceRegainedEvent).format('yy-MM-dd hh:mm:ss')),
					TDobj.clone().append(
							Aobj.clone().attr("href", "/" + version + "/search/outage/outageDesc?outageId=" + outages["@id"]).append().text(outages["@id"])
						)
					)
				);
			/**********************************************************************************************************************************************/

		}

		//str += "</table></div>";

	}else{
		DIVobj.attr("class", "row-fluid").append(
				H5obj.append().text("이벤트 장애 목록" + "[" + jsonObj["@count"] + "]"),
				DIVobj.clone().attr("class", "span12 well well-small").attr("style", "margin-left:0px").append(
					TABLEobj.attr("class", "table table-striped").append(
						TRobj.clone().append(
							THobj.clone().append().text("인터페이스"),
							THobj.clone().append().text("상태"),
							THobj.clone().append().text("서비스"),
							THobj.clone().append().text("장애 시간"),
							THobj.clone().append().text("회복 시간"),
							THobj.clone().append().text("ID")
						),
						TRobj.clone().text("이벤트 장애 목록이 없습니다.")
					)
				)
			);
	}
	//return str;
	/*************/
	return DIVobj;
	/*************/
}
/*
 * 메뉴의 검색 -> 노드 검색 -> 노드 목록에서 node를 클릭 시 새로 이동하는 화면단의 인터페이스 장애 목록
 */
function getTabletagToInterfaceOutageJsonObj(jsonObj, ipAddress) {
	var outages = jsonObj["outage"];

	//var str = "";
	/*********************************************/
	var DIVobj = $("<div></div>");
	var TABLEobj = $("<table></table>");
	var TRobj = $("<tr></tr>");
	var THobj = $("<th></th>");
	var TDobj = $("<td></td>");
	var H5obj = $("<h5></h5>");
	var Aobj = $("<a></a>");
	var FONTobj = $("<font></font>");
	/*********************************************/
	if (jsonObj["@count"] > 0) {
		/*str = "	<div class='row-fluid'>"
		+ "		<h5>인터페이스&nbsp;장애&nbsp;목록&nbsp;["
		+ jsonObj["@count"]
		+ "]</h5>"
		+ "	</div>"
		+ "	<div class='row-fluid'>"
		+ "		<div class='span12 well well-small'>"
		+ "<table class='table table-striped'>"
		+ "<tr><th>인터페이스</th><th>서비스</th><th>장애 시간</th><th>회복 시간</th><th>ID</th></tr>";*/
		/**********************************************************************************************************************************************/
		DIVobj.attr("class", "row-fluid").append(
				H5obj.append(
						FONTobj.clone().attr("color", "blue").text(ipAddress),
						FONTobj.clone().attr("color", "black").text(" �명꽣�섏씠���μ븷 紐⑸줉" + "[" + jsonObj["@count"] + "]")
				),
				DIVobj.clone().attr("class", "span12 well well-small").attr("style", "margin-left:0px").append(
						TABLEobj.attr("class", "table table-striped").append(
							TRobj.clone().append(
								THobj.clone().append().text("인터페이스"),
								THobj.clone().append().text("서비스"),
								THobj.clone().append().text("장애 시간"),
								THobj.clone().append().text("회복 시간"),
								THobj.clone().append().text("ID")
							)
						)
					)
		);
		/**********************************************************************************************************************************************/
		if (jsonObj["@count"] > 1) {
			for ( var i in outages) {
				/*str += "<tr>";
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
				str += "<td class=\"text-success\">"
						+ new Date(nullCheckJsonObject(
								outages[i]["serviceRegainedEvent"], "time"))
								.format('yy-MM-dd hh:mm:ss') + "</td>";
				str += "<td><a href='/" + version
						+ "/search/outage/outageDesc?outageId="
						+ outages[i]["@id"] + "'>" + outages[i]["@id"]
						+ "</a></td>";
				str += "</tr>";*/
				/**********************************************************************************************************************************************/
				TABLEobj.append(
					TRobj.clone().append(
						TDobj.clone().append(
							FONTobj.clone().attr("color", "gray").text(outages[i]["ipAddress"])
							//Pobj.clone().attr("class", "text-muted").text(outages[i]["ipAddress"])
						),
						TDobj.clone().append(
							Aobj.clone().attr("href", "/" + version + "/search/service/serviceDesc?nodeId=" + outages[i]["serviceLostEvent"]["nodeId"] + "&intf=" + outages[i]["ipAddress"] + "&serviceNm=" + nullCheckJsonObject(outages[i]["serviceLostEvent"]["serviceType"],["name"])).append().text(nullCheckJsonObject(outages[i]["serviceLostEvent"]["serviceType"],["name"]))
						),
						TDobj.clone().append().text(new Date(nullCheckJsonObject(outages[i]["serviceLostEvent"], ["time"])).format('yy-MM-dd hh:mm:ss')),
						TDobj.clone().append().text(new Date(nullCheckJsonObject(outages[i]["serviceRegainedEvent"], ["time"])).format('yy-MM-dd hh:mm:ss')),
						TDobj.clone().append(
							Aobj.clone().attr("href", "/" + version + "/search/outage/outageDesc?outageId=" + outages[i]["@id"]).append().text(outages[i]["@id"])
						)
					)
				);
				/**********************************************************************************************************************************************/
			}
		} else {
			/*str += "<tr>";
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
			str += "</tr>";*/
			/**********************************************************************************************************************************************/
			TABLEobj.append(
				TRobj.clone().append(
					TDobj.clone().append(
						Aobj.clone().attr("href", "#").append().text(outages["@id"])
					),
					TDobj.clone().append(
						Aobj.clone().attr("href", "#").append().text(nullCheckJsonObject(outages["serviceLostEvent"]["serviceType"], ["name"]))
					),
					TDobj.clone().append().text(new Date(nullCheckJsonObject(outages["serviceLostEvent"],["time"]).format('yy-MM-dd hh:mm:ss')),
					TDobj.clone().append().text(new Date(nullCheckJsonObject(outages["serviceRegainedEvent"], ["time"])).format('yy-MM-dd hh:mm:ss')),
					TDobj.clone().append(
						Aobj.clone().attr("href", "#").append().text(outages["@id"])
					)
				)
			)
			);
			/**********************************************************************************************************************************************/
		}

		//str += "</table></div>";

	}else{
		DIVobj.attr("class", "row-fluid").append(
				H5obj.append().text("인터페이스 장애 목록" + "[" + jsonObj["@count"] + "]"),
				DIVobj.clone().attr("class", "span12 well well-small").attr("style", "margin-left:0px").append(
					TABLEobj.attr("class", "table table-striped").append(
						TRobj.clone().append(
							THobj.clone().append().text("인터페이스"),
							THobj.clone().append().text("상태"),
							THobj.clone().append().text("서비스"),
							THobj.clone().append().text("장애 시간"),
							THobj.clone().append().text("회복 시간"),
							THobj.clone().append().text("ID")
						),
						TRobj.clone().text("인터페이스 장애 목록이 없습니다.")
					)
				)
			);
	}
	//return str;
	/*************/
	return DIVobj;
	/*************/
}
/*
 * 메뉴의 검색 -> 노드 검색 -> 노드 목록에서 node를 클릭 시 새로 이동하는 화면단의 서비스 장애 목록
 */
function getTabletagToServiceOutageJsonObj(jsonObj,serviceNm) {
	var outages = jsonObj["outage"];
	
	var serviceObj = jsonObj["service"];
	//var str = "";
	/*********************************************/
	var DIVobj = $("<div></div>");
	var TABLEobj = $("<table></table>");
	var TRobj = $("<tr></tr>");
	var THobj = $("<th></th>");
	var TDobj = $("<td></td>");
	var H5obj = $("<h5></h5>");
	var Aobj = $("<a></a>");
	var FONTobj = $("<font></font>");
	/*********************************************/
	if (jsonObj["@count"] > 0) {
		
		/*str = "	<div class='row-fluid'>"
		+ "		<h5>서비스&nbsp;장애&nbsp;목록&nbsp;["
		+ jsonObj["@count"]
		+ "]</h5>"
		+ "	</div>"
		+ "	<div class='row-fluid'>"
		+ "		<div class='span12 well well-small'>"
		+ "<table class='table table-striped'>"
		+ "<tr><th>인터페이스</th><th>서비스</th><th>장애 시간</th><th>회복 시간</th><th>ID</th></tr>";*/
		/**********************************************************************************************************************************************/
		DIVobj.attr("class", "row-fluid").append(
				H5obj.append(
						FONTobj.clone().attr("color", "blue").text(serviceNm),
						FONTobj.clone().attr("color", "black").text(" 서비스 장애 목록" + "[" + jsonObj["@count"] + "]")
				),
				DIVobj.clone().attr("class", "span12 well well-small").attr("style", "margin-left:0px").append(
					TABLEobj.attr("class", "table table-striped").append(
						TRobj.clone().append(
							THobj.clone().append().text("인터페이스"),
							THobj.clone().append().text("서비스"),
							THobj.clone().append().text("장애 시간"),
							THobj.clone().append().text("회복 시간"),
							THobj.clone().append().text("ID")
						)
					)
				)
			);
		/**********************************************************************************************************************************************/
		if (jsonObj["@count"] > 1) {

			for ( var i in outages) {

				/*str += "<tr>";
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
				str += "<td class=\"text-success\">"
						+ new Date(nullCheckJsonObject(
								outages[i]["serviceRegainedEvent"], "time"))
								.format('yy-MM-dd hh:mm:ss') + "</td>";
				str += "<td><a href='/" + version
						+ "/search/outage/outageDesc?outageId="
						+ outages[i]["@id"] + "'>" + outages[i]["@id"]
						+ "</a></td>";
				str += "</tr>";*/
				/**********************************************************************************************************************************************/
				TABLEobj.append(
					TRobj.clone().append(
						TDobj.clone().append(
							FONTobj.clone().attr("color", "gray").text(outages[i]["ipAddress"])
							//Pobj.clone().attr("class", "text-muted").text(outages[i]["ipAddress"])
						),
						TDobj.clone().append(
							Aobj.clone().attr("href", "/" + version + "/search/service/serviceDesc?nodeId=" + outages[i]["serviceLostEvent"]["nodeId"] + "&intf=" + outages[i]["ipAddress"] + "&serviceNm=" + nullCheckJsonObject(outages[i]["serviceLostEvent"]["serviceType"],["name"])).append().text(nullCheckJsonObject(outages[i]["serviceLostEvent"]["serviceType"],"name"))
						),
						TDobj.clone().append().text(new Date(nullCheckJsonObject(outages[i]["serviceLostEvent"], ["time"])).format('yy-MM-dd hh:mm:ss')),
						TDobj.clone().append().text(new Date(nullCheckJsonObject(outages[i]["serviceRegainedEvent"], ["time"])).format('yy-MM-dd hh:mm:ss')),
						TDobj.clone().append(
							Aobj.clone().attr("href", "/" + version + "/search/outage/outageDesc?outageId=" + outages[i]["@id"]).append().text(outages[i]["@id"])
						)
					)
				);
				/**********************************************************************************************************************************************/
				
			}

		} else {
			/*str += "<tr>";
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
			str += "</tr>";*/
			/**********************************************************************************************************************************************/
			TABLEobj.append(
				TRobj.clone().append(
					TDobj.clone().append(
						Aobj.clone().attr("href", "#").append().text(outages["@id"])
					),
					TDobj.clone().append(
						Aobj.clone().attr("href", "#").append().text(nullCheckJsonObject(outages["serviceLostEvent"]["serviceType"], ["name"]))
					),
					TDobj.clone().append().text(new Date(nullCheckJsonObject(outages["serviceLostEvent"],["time"])).format('yy-MM-dd hh:mm:ss')),
					TDobj.clone().append().text(new Date(nullCheckJsonObject(outages["serviceRegainedEvent"], ["time"])).format('yy-MM-dd hh:mm:ss')),
					TDobj.clone().append(
						Aobj.clone().attr("href", "#").append().text(outages["@id"])
					)
				)
			);
			/**********************************************************************************************************************************************/
		}

		//str += "</table></div>";

	}else{
		DIVobj.attr("class", "row-fluid").append(
				H5obj.append().text("서비스 장애 목록" + "[" + jsonObj["@count"] + "]"),
				DIVobj.clone().attr("class", "span12 well well-small").attr("style", "margin-left:0px").append(
					TABLEobj.attr("class", "table table-striped").append(
						TRobj.clone().append(
							THobj.clone().append().text("인터페이스"),
							THobj.clone().append().text("상태"),
							THobj.clone().append().text("서비스"),
							THobj.clone().append().text("장애 시간"),
							THobj.clone().append().text("회복 시간"),
							THobj.clone().append().text("ID")
						),
						TRobj.clone().text("서비스 장애 목록이 없습니다.")
					)
				)
			);
	}
	//return str;
	/*************/
	return DIVobj;
	/*************/
}
/**
 * Outage info
 * 장애 정보  
 * 1) /v1/search/outage/outageDesc 의 장애정보 
 * 2) /v1/index 의 장애 정보 <popup>
 * 3) /include/sideBar 의 장애 정보 <popup> 
 * @param jsonObj
 */
function getOutageInfoBox(jsonObj) {
	
	var outageObj = jsonObj["outage"];

	/****************************************/
	var DIVobj = $("<div></div>");
	var H5obj = $("<h5></h5>");
	var TABLEobj = $("<table></table>");
	var TRobj = $("<tr></tr>");
	var THobj = $("<th></th>");
	var TDobj = $("<td></td>");
	var Aobj = $("<a></a>");
	var FONTobj = $("<font></font>");
	
	var outageInfoStr = DIVobj.attr("class", "row-fluid").append(
							H5obj.append(
								FONTobj.attr("color", "red").clone().text(outageObj["ipAddress"]),
								FONTobj.attr("color", "black").clone().text("의 장애 아이디"),
								FONTobj.attr("color", "black").clone().text(" ["),
								FONTobj.attr("color", "blue").clone().text(outageObj["@id"]),
								FONTobj.attr("color", "black").clone().text("]")
							),
							DIVobj.clone().attr("class", "span12 well well-small").attr("style", "margin-left:0px").append(
								TABLEobj.clone().attr("class", "table table-striped").append(
									TRobj.clone().append(
											THobj.clone().attr("style", "width:80px;").text("노드명"),
										TDobj.clone().append(
											Aobj.clone().attr("href", "/" + version + "/search/node/nodeDesc.do?nodeId=" +  outageObj["serviceLostEvent"]["nodeId"]).text(outageObj["serviceLostEvent"]["nodeLabel"])	
										),
										THobj.clone().attr("style", "width: 1px").text("LostTime"),
										TDobj.clone().text(new Date(outageObj["serviceLostEvent"]["time"]).format('yy-MM-dd hh:mm:ss'))
									),
									TRobj.clone().append(
											THobj.clone().text("노드ID"),
											TDobj.clone().append(
												Aobj.clone().attr("href", "/" + version + "/search/event/eventDesc.do?eventId=" +  outageObj["serviceLostEvent"]["@id"]).text(outageObj["serviceLostEvent"]["@id"])	
											),
											THobj.clone().text("아이피"),
											TDobj.clone().text(outageObj["ipAddress"])
										),
										TRobj.clone().append(
											THobj.clone().text("호스트"),
											TDobj.clone().text(outageObj["serviceLostEvent"]["host"]),
											THobj.clone().text("서비스"),
											TDobj.clone().text(outageObj["monitoredService"]["serviceType"]["name"])
										),
										TRobj.clone().append(
											THobj.clone().text("설명"),
											TDobj.clone().attr("colspan", "5").text(outageObj["serviceLostEvent"]["logMessage"]),
											TDobj.clone().attr("colspan", "4").text()
										)
									)
								)
							);
	/**********************************************************************************************************************************/
	/*var outageInfoStr = '<div class="row-fluid">' + '	<h5>getOutageInfoBox['
	+ outageObj["@id"]
	+ ']</h5>'
	+ '</div>'
	+ '<div class="row-fluid">'
	+ '	<div class="span12 well well-small">'
	+ '		<table class="table table-striped">'
	+ '			<tr>'
	+ '				<th>노드명</th>'
	+ '				<td>'
	+ '					<a href="/'
	+ version
	+ '/search/node/nodeDesc.do?nodeId='
	+ outageObj["serviceLostEvent"]["nodeId"]
	+ '">'
	+ outageObj["serviceLostEvent"]["nodeLabel"]
	+ '					</a>'
	+ '				</td>'
	+ '				<th style="width: 1px;">LostTime</th>'
	+ '				<td>'
	+ new Date(outageObj["serviceLostEvent"]["time"])
			.format('yy-MM-dd hh:mm:ss')
	+ '</td>'
	+ '			</tr>'
	+ '			<tr>'
	+ '				<th>노드ID</th>'
	+ '				<td><a href="/'
	+ version
	+ '/search/event/eventDesc.do?eventId='
	+ outageObj["serviceLostEvent"]["@id"]
	+ '">'
	+ outageObj["serviceLostEvent"]["@id"]
	+ '</a></td>'
	
	+ '				<th>아이피</th>'
	+ '				<td>'
	+ outageObj["ipAddress"]
	+ '</td>'
	+ '			</tr>'
	+ '			<tr>'
	+ '				<th style ="width: 82px;";>호스트</th>'
	+ '				<td>'
	+ outageObj["serviceLostEvent"]["host"]
	+ '</td>'
	+ '				<th>서비스</th>'
	+ '				<td>'
	+ outageObj["monitoredService"]["serviceType"]["name"]
	+ '</td>'
	+ '			</tr>'
	+ '			<tr>'
	+ '				<th>설명</th>'
	+ '				<td colspan="5">'
		+outageObj["serviceLostEvent"]["logMessage"]
	//+ nullCheckJsonObject(
	//	+outageObj["serviceLostEvent"]["logMessage"]
	+ '				</td>' 
	+ '				<td colspan="4"></td>'
	+ '			</tr>' 
	+ '		</table>' 
	+ '	</div>' 
	+ '</div>';*/

return outageInfoStr;
}

/* Outage search
* 메뉴의 검색 -> 장애 검색 의 장애 목록 리스트
* @param jsonObj
 */	
function getTabletagToOutageSearchJsonObj(jsonObj) {
	var outages = jsonObj["outage"];
	//var str = "";
	
	/************************************************************************************************************************/
	var TRobj = $("<tr></tr>");
	var TDobj = $("<td></td>");
	var Aobj = $("<a></a>");
	var H4obj = $("<h4></h4>");
	var TBODYobj = $("<tbody></tbody>");
	var Bobj = $("<b></b>");
	/************************************************************************************************************************/
	
	if(jsonObj["@totalCount"] == 0){
		/*****************************************************************************************************************/
		TBODYobj.append(
			TRobj.clone().append(
					TDobj.clone().append(
							Bobj.clone().text("ID")
						),
						TDobj.clone().append(
							Bobj.clone().text("인터페이스")
						),
						TDobj.clone().append(
							Bobj.clone().text("장애 시간")
						),
						TDobj.clone().append(
							Bobj.clone().text("회복 시간")
						),
						TDobj.clone().append(
							Bobj.clone().text("서비스")
						)
			),	
			TRobj.clone().append(
				TDobj.clone().append().text(""),
				TDobj.clone().append().text(""),
				TDobj.clone().append().text(""),
				TDobj.clone().append(
						H4obj.attr("class", "text-error").text("장애가 검색되지 않습니다.")
				)
			)
		);
		/******************************************************************************************************************/
		/*str += "<tr>";
		str += "<td></td>";
		str += "<td></td>";
		str += "<td></td>";
		str += "<td><h4 class='text-error'>�μ븷媛�寃�깋�섏� �딆뒿�덈떎.</h4></td>";
		str += "</tr>";*/
		
	
	
	} else if (jsonObj["@count"] != "undefined" && jsonObj["@count"] != 0) {
		
		if (jsonObj["@count"] > 1) {
				TBODYobj.append(
					TRobj.clone().append(
							TDobj.clone().append(
									Bobj.clone().text("ID")
								),
								TDobj.clone().append(
									Bobj.clone().text("인터페이스")
								),
								TDobj.clone().append(
									Bobj.clone().text("장애 시간")
								),
								TDobj.clone().append(
									Bobj.clone().text("회복 시간")
								),
								TDobj.clone().append(
									Bobj.clone().text("서비스")
								)
					)
				);
			for ( var i in outages) {
				/*****************************************************************************************************************/
				TBODYobj.append(	
					TRobj.clone().append(
						TDobj.clone().append(
							Aobj.clone().attr("href", "/" + version + "/search/outage/outageDesc?outageId=" + outages[i]["@id"]).text(outages[i]["@id"])
						),
						TDobj.clone().append(
							Aobj.clone().attr("href", "/" + version + "/search/node/nodeDesc.do?nodeId=" + outages[i]["serviceLostEvent"]["nodeId"]).text(outages[i]["ipAddress"])
						),
						TDobj.clone().attr("class", "text-error").text(new Date(nullCheckJsonObject(outages[i]["serviceLostEvent"], "time")).format('yy-MM-dd hh:mm:ss')),
						TDobj.clone().attr("class", "text-success").text(new Date(nullCheckJsonObject(outages[i]["serviceRegainedEvent"], "time")).format('yy-MM-dd hh:mm:ss')),
						TDobj.clone().append(
							Aobj.clone().attr("href", "/" + version + "/search/service/serviceDesc?nodeId=" + outages[i]["serviceLostEvent"]["nodeId"] + "&intf=" + outages[i]["ipAddress"] + "&serviceNm=" + nullCheckJsonObject(outages[i]["serviceLostEvent"]["serviceType"],"name")).text(nullCheckJsonObject(outages[i]["serviceLostEvent"]["serviceType"],"name"))
						)
					)
				);
				/******************************************************************************************************************/
				/*str += "<tr>";
				
				str += "<td><a href='/" + version + "/search/outage/outageDesc?outageId=" + outages[i]["@id"] + "'>" + outages[i]["@id"] + "</a></td>";
				str += "<td><a href='/" + version + "/search/node/nodeDesc.do?nodeId="+ outages[i]["serviceLostEvent"]["nodeId"]+ "'>"
					//"<td><a href='/" + version + "/search/node/interfaceDesc.do?nodeId=" + outages[i]["serviceLostEvent"]["nodeId"] + "&intf=" + outages[i]["ipAddress"] + "'>"
						+ outages[i]["ipAddress"]+ "</a></td>";
				str += "<td class=\"text-error\">"
						+ new Date(nullCheckJsonObject(
								outages[i]["serviceLostEvent"], "time"))
								.format('yy-MM-dd hh:mm:ss') + "</td>";
				str += "<td class=\"text-success\">"
						+ new Date(nullCheckJsonObject(
								outages[i]["serviceRegainedEvent"], "time"))
								.format('yy-MM-dd hh:mm:ss') + "</td>";
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
				str += "</tr>";*/
			}

		} else {
			outages = outages == null ? jsonObj : jsonObj["outage"];
			/*****************************************************************************************************************/
			TBODYobj.append(	
				TRobj.clone().append(
						TDobj.clone().append(
								Bobj.clone().text("ID")
							),
							TDobj.clone().append(
								Bobj.clone().text("인터페이스")
							),
							TDobj.clone().append(
								Bobj.clone().text("장애 시간")
							),
							TDobj.clone().append(
								Bobj.clone().text("회복 시간")
							),
							TDobj.clone().append(
								Bobj.clone().text("서비스")
							)
				),
				TRobj.clone().append(
					TDobj.clone().append(
						Aobj.clone().attr("href", "/" + version + "/search/outage/outageDesc?outageId=" + outages["@id"]).text(outages["@id"])
					),
					TDobj.clone().append(
						Aobj.clone().attr("href", "/" + version + "/search/node/nodeDesc.do?nodeId=" + outages["serviceLostEvent"]["nodeId"] + "&intf=" + outages["ipAddress"]).text(outages["ipAddress"])
					),
					TDobj.clone().attr("class", "text-error").text(new Date(nullCheckJsonObject(outages["serviceLostEvent"], "time")).format('yy-MM-dd hh:mm:ss')),
					TDobj.clone().attr("class", "text-success").text(new Date(nullCheckJsonObject(outages["serviceRegainedEvent"], "time")).format('yy-MM-dd hh:mm:ss')),
					TDobj.clone().append(
						Aobj.clone().attr("href", "/" + version + "/search/service/serviceDesc?nodeId=" + outages["serviceLostEvent"]["nodeId"] + "&intf=" + outages["ipAddress"] + "&serviceNm=" + nullCheckJsonObject(outages["serviceLostEvent"]["serviceType"],"name")).text(nullCheckJsonObject(outages["serviceLostEvent"]["serviceType"],"name"))
					)
				)
			);
			/******************************************************************************************************************/
			/*str += "<tr>";
			str += "<td><a href='/" + version
				+ "/search/outage/outageDesc?outageId="
				+ outages["@id"] + "'>" + outages["@id"]
				+ "</a></td>";
			
			str += "<td><a href='/" + version
				+ "/search/node/interfaceDesc.do?nodeId=" + outages["serviceLostEvent"]["nodeId"]
				+ "&intf=" + outages["ipAddress"] + "'>"
				+ outages["ipAddress"] + "</a></td>";
			
			str += "<td class=\"text-error\">"
				+ new Date(nullCheckJsonObject(
							outages["serviceLostEvent"], "time"))
							.format('yy-MM-dd hh:mm:ss') + "</td>";
			str += "<td>"
				+ new Date(nullCheckJsonObject(
							outages["serviceRegainedEvent"], "time"))
							.format('yy-MM-dd hh:mm:ss') + "</td>";
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
			str += "</tr>";*/
		}

	}
	//return str;
	/**********/
	return TBODYobj;
	/**********/
}
