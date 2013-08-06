/**
 * Get a list of outages
 * 
 * @param callback
 */
function getTotalOutagesList(callback, data,ipaddr,nodeId) {
	
	//====3========
	$.ajax({
		type : 'get',
		url : '/' + version + '/outages',
		dataType : 'json',
		data : data,
		contentType : 'application/json',
		error : function(data) {
			alert('以묐떒紐⑸줉 媛�졇�ㅺ린 �ㅽ뙣');
		},
		success : function(data) {
			// 肄쒕갚�⑥닔
			if (typeof callback == "function") {
				callback(data,ipaddr,nodeId);
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
 * Get a list of outages for nodeId [recent] 理쒓렐 recentCount媛���outages 紐⑸줉
 * 
 * @param callback
 * @param nodeId
 * @param recentCount
 */
function getOutagesForNode(callback, nodeId, recentCount) {

	if (nodeId == null) {
		alert("�몃뱶 �꾩씠�붽� �놁뒿�덈떎.");
		return;
	}

	// var data = "query=this_.nodeId = '"+nodeId+"' AND this_.iflostservice >
	// '"+new Date().format("yyyy-MM-ddTHH:MM:ss")+"'";
	var query = encodeURI("query=this_.nodeId = '" + nodeId + "'");
	var filter = "&orderBy=ifLostService&order=desc&limit=" + recentCount;

	getTotalOutagesList(callback, query + filter);
}

/**
 * Get a list of outages for nodeId & ipAddress [recent] 理쒓렐 recentCount媛���
 * outages 紐⑸줉
 * 
 * @param callback
 * @param nodeId
 * @param recentCount
 */
function getOutagesForInterface(callback, nodeId, ipAddress, recentCount) {
	//=====2=======
	if (nodeId == null) {
		alert("�몃뱶 �꾩씠�붽� �놁뒿�덈떎.");
		return;
	}

	// var data = "query=this_.nodeId = '"+nodeId+"' AND this_.iflostservice >
	// '"+new Date().format("yyyy-MM-ddTHH:MM:ss")+"'";
	var query = encodeURI("query=this_.nodeId = '" + nodeId
			+ "' AND this_.ipaddr = '" + ipAddress + "'");
	var filter = "&orderBy=ifLostService&order=desc&limit=" + recentCount;
	var ipAddress = "null";
	getTotalOutagesList(callback, query + filter,ipAddress,nodeId);
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
			alert('以묐떒紐⑸줉 媛�졇�ㅺ린 �ㅽ뙣');
		},
		success : function(data) {
			// 肄쒕갚�⑥닔
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
			alert('以묐떒紐⑸줉 媛�졇�ㅺ린 �ㅽ뙣');
		},
		success : function(data) {
			// 肄쒕갚�⑥닔
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
	
}
/** ************************ view String edit **************************** */

/**
 * Outage �뺣낫瑜�table �뚭렇 Str濡�留뚮뱾�댁���
 * 
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
				+ "		<h5>以묐떒&nbsp;紐⑸줉&nbsp;["
				+ jsonObj["@count"]
				+ "]</h5>"
				+ "	</div>"
				+ "	<div class='row-fluid'>"
				+ "		<div class='span12 well well-small'>"
				+ "<table class='table table-striped'>"
				+ "<tr><th>�명꽣�섏씠��/th><th>�쒕퉬��/th><th>以묐떒 �쒓컙</th><th>�뚮났 �쒓컙</th><th>ID</th></tr>";*/
		/**********************************************************************************************************************************************/
		DIVobj.attr("class", "row-fluid").append(
			H5obj.append().text("以묐떒 紐⑸줉" + "[" + jsonObj["@count"] + "]"),
			DIVobj.clone().attr("class", "span12 well well-small").attr("style", "margin-left:0px").append(
				TABLEobj.attr("class", "table table-striped").append(
					TRobj.clone().append(
						THobj.clone().append().text(""),
						THobj.clone().append().text(""),
						THobj.clone().append().text("以묐떒 �쒓컙"),
						THobj.clone().append().text("�뚮났 �쒓컙"),
						THobj.clone().append().text("ID")
					)
				)
			)
		);
		/**********************************************************************************************************************************************/
		if (jsonObj["@count"] > 0) {

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
							FONTobj.clone().attr("color", "7F7F7F").text(outages[i]["ipAddress"])
							//Pobj.clone().attr("class", "text-muted").text(outages[i]["ipAddress"])
						),
						TDobj.clone().append(
							Aobj.clone().attr("href", "/" + version + "/search/service/serviceDesc?nodeId=" + outages[i]["serviceLostEvent"]["nodeId"] + "&intf=" + outages[i]["ipAddress"] + "&serviceNm=" + nullCheckJsonObject(outages[i]["serviceLostEvent"]["serviceType"],"name")).append().text(nullCheckJsonObject(outages[i]["serviceLostEvent"]["serviceType"],"name"))
						),
						TDobj.clone().append().text(new Date(nullCheckJsonObject(outages[i]["serviceLostEvent"], "time")).format('yy-MM-dd hh:mm:ss')),
						TDobj.clone().append().text(new Date(nullCheckJsonObject(outages[i]["serviceRegainedEvent"], "time")).format('yy-MM-dd hh:mm:ss')),
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
						Aobj.clone().attr("href", "#").append().text(nullCheckJsonObject(outages["serviceLostEvent"]["serviceType"], "name"))
					),
					TDobj.clone().append().text(new Date(nullCheckJsonObject(outages["serviceLostEvent"],"time")).format('yy-MM-dd hh:mm:ss')),
					TDobj.clone().append().text(new Date(nullCheckJsonObject(outages["serviceRegainedEvent"], "time")).format('yy-MM-dd hh:mm:ss')),
					TDobj.clone().append(
						Aobj.clone().attr("href", "#").append().text(outages["@id"])
					)
				)
			);
			/**********************************************************************************************************************************************/

		}

		//str += "</table></div>";

	}
	//return str;
	/*************/
	return DIVobj;
	/*************/
}
function getTabletagToInterfaceOutageJsonObj(jsonObj) {
	//===5=======
	var outages = jsonObj["outage"];

	//var str = "";
	/*********************************************/
	var DIVobj = $("<div></div>");
	var TABLEobj = $("<table></table>");
	var TRobj = $("<tr></tr>");
	//var COLGROUPobj = $("<colgroup></colgroup>");
	//var COLobj = $("<col></col>");
	var THobj = $("<th></th>");
	var TDobj = $("<td></td>");
	var H5obj = $("<h5></h5>");
	var Aobj = $("<a></a>");
	/*********************************************/
	if (jsonObj["@count"] > 0) {
		/*str = "	<div class='row-fluid'>"
				+ "		<h5>�명꽣�섏씠��nbsp;以묐떒&nbsp;紐⑸줉&nbsp;["
				+ jsonObj["@count"]
				+ "]</h5>"
				+ "	</div>"
				+ "	<div class='row-fluid'>"
				+ "		<div class='span12 well well-small'>"
				+ "<table class='table table-striped'>"
				+ "<tr><th>�명꽣�섏씠��/th><th>�쒕퉬��/th><th>以묐떒 �쒓컙</th><th>�뚮났 �쒓컙</th><th>ID</th></tr>";*/
				/**************************************************************************************************************************************************************************/
				DIVobj.attr("class", "row-fluid").append(
					H5obj.text("�명꽣�섏씠��以묐떒 紐⑸줉" + "[" + jsonObj["@count"] + "]"),
					DIVobj.clone().append(
						TABLEobj.clone().attr("class", "table table-striped").append(
							TRobj.clone().append(
								THobj.clone().text("�명꽣�섏씠��"),
								THobj.clone().text("�쒕퉬��"),
								THobj.clone().text("以묐떒 �쒓컙"),
								THobj.clone().text("�뚮났 �쒓컙"),
								THobj.clone().text("ID")
							)
						)
					)
				);
				/**************************************************************************************************************************************************************************/
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
				/**************************************************************************************************************************************************************************/
				DIVobj.attr("class", "span12 well well-small").append(
					TABLEobj.clone().attr("class", "table table-striped").append(
						TRobj.clone().append(
							TDobj.clone().append(
								Aobj.clone().attr("href", "/" + version + "/search/node/interfaceDesc.do?nodeId=" + outages[i]["serviceLostEvent"]["nodeId"] + "&intf=" + outages[i]["ipAddress"]).append().text(outages[i]["ipAddress"])
							),
							TDobj.clone().attr("width","150px").append(
								Aobj.clone().attr("href", "/" + version + "/search/service/serviceDesc?nodeId=" + outages[i]["serviceLostEvent"]["nodeId"] + "&intf=" + outages[i]["ipAddress"] + "&serviceNm=" + nullCheckJsonObject(outages[i]["serviceLostEvent"]["serviceType"],"name")).append().text(nullCheckJsonObject(outages[i]["serviceLostEvent"]["serviceType"],"name"))
							),
							TDobj.clone().attr("class", "text-error").append().text(new Date(nullCheckJsonObject(outages[i]["serviceLostEvent"], "time")).format('yy-MM-dd hh:mm:ss')),
							TDobj.clone().attr("class", "text-success").append().text(new Date(nullCheckJsonObject(outages[i]["serviceRegainedEvent"], "time")).format('yy-MM-dd hh:mm:ss')),
							TDobj.clone().append(
								Aobj.clone().attr("href", "/" + version + "/search/outage/outageDesc?outageId=" + outages[i]["@id"]).append().text(outages[i]["@id"])
							)
						)
					)
				);
			/**************************************************************************************************************************************************************************/
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
			/**************************************************************************************************************************************************************************/
			DIVobj.attr("class", "row-fluid").append(
				DIVobj.clone().attr("class", "span12 well well-small").append(
					TABLEobj.clone().attr("class", "span12 well well-small").append(
						TRobj.clone().append(
							TDobj.clone().append(
								Aobj.clone().attr("href", "#").append().text(outages["@id"])
							)
						),
						TRobj.clone().append(
							TDobj.clone().append(
								Aobj.clone().attr("href", "#").append().text(nullCheckJsonObject(outages["serviceLostEvent"]["serviceType"], "name"))
							)
						),
						TRobj.clone().append(
							TDobj.clone().append().text(new Date(nullCheckJsonObject(outages["serviceLostEvent"],"time")).format('yy-MM-dd hh:mm:ss'))
						),
						TRobj.clone().append(
							TDobj.clone().append().text(new Date(nullCheckJsonObject(outages["serviceRegainedEvent"], "time")).format('yy-MM-dd hh:mm:ss'))
						),
						TRobj.clone().append(
							TDobj.clone().append(
								Aobj.clone().attr("href", "#").append().text(outages["@id"])
							)
						)
					)
				)
			);
			/**************************************************************************************************************************************************************************/
		}

		//str += "</table></div>";
		/*******************************/
		
		
		/*******************************/

	}
	//return str;
	/*************/
	return DIVobj;
	/*************/
}
function getTabletagToServiceOutageJsonObj(jsonObj) {
	
	var outages = jsonObj["outage"];

	//var str = "";
	/*********************************************/
	var DIVobj = $("<div></div>");
	var TABLEobj = $("<table></table>");
	var TRobj = $("<tr></tr>");
	//var COLGROUPobj = $("<colgroup></colgroup>");
	//var COLobj = $("<col></col>");
	var THobj = $("<th></th>");
	var TDobj = $("<td></td>");
	var H5obj = $("<h5></h5>");
	var Aobj = $("<a></a>");
	/*********************************************/
	if (jsonObj["@count"] > 0) {

		/*str = "	<div class='row-fluid'>"
				+ "		<h5>�쒕퉬��nbsp;以묐떒&nbsp;紐⑸줉&nbsp;["
				+ jsonObj["@count"]
				+ "]</h5>"
				+ "	</div>"
				+ "	<div class='row-fluid'>"
				+ "		<div class='span12 well well-small'>"
				+ "<table class='table table-striped'>"
				+ "<tr><th>�명꽣�섏씠��/th><th>�쒕퉬��/th><th>以묐떒 �쒓컙</th><th>�뚮났 �쒓컙</th><th>ID</th></tr>";*/
			/**************************************************************************************************************************************************************************/
			DIVobj.attr("class", "row-fluid").append(
				H5obj.append().text("�쒕퉬��以묐떒 紐⑸줉" + "[" + jsonObj["@count"] + "]"),
				DIVobj.clone().append(
					TABLEobj.clone().attr("class", "table table-striped").append(
						TRobj.clone().append(
							THobj.clone().append().text("�명꽣�섏씠��"),
							THobj.clone().append().text("�쒕퉬��"),
							THobj.clone().append().text("以묐떒 �쒓컙"),
							THobj.clone().append().text("�뚮났 �쒓컙"),
							THobj.clone().append().text("ID")
						)
					)
				)
			);
			/**************************************************************************************************************************************************************************/
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
				/**************************************************************************************************************************************************************************/
				DIVobj.attr("class", "span12 well well-small").append(
						TABLEobj.clone().attr("class", "table table-striped").append(
							TRobj.clone().append(
								TDobj.clone().append(
									Aobj.clone().attr("href", "/" + version + "/search/node/interfaceDesc.do?nodeId=" + outages[i]["serviceLostEvent"]["nodeId"] + "&intf=" + outages[i]["ipAddress"]).append().text(outages[i]["ipAddress"])
								),
								TDobj.clone().attr("width","150px").append(
									Aobj.clone().attr("href", "/" + version + "/search/service/serviceDesc?nodeId=" + outages[i]["serviceLostEvent"]["nodeId"] + "&intf=" + outages[i]["ipAddress"] + "&serviceNm=" + nullCheckJsonObject(outages[i]["serviceLostEvent"]["serviceType"],"name")).append().text(nullCheckJsonObject(outages[i]["serviceLostEvent"]["serviceType"],"name"))
								),
								TDobj.clone().attr("class", "text-error").append().text(new Date(nullCheckJsonObject(outages[i]["serviceLostEvent"], "time")).format('yy-MM-dd hh:mm:ss')),
								TDobj.clone().attr("class", "text-success").append().text(new Date(nullCheckJsonObject(outages[i]["serviceRegainedEvent"], "time")).format('yy-MM-dd hh:mm:ss')),
								TDobj.clone().append(
									Aobj.clone().attr("href", "/" + version + "/search/outage/outageDesc?outageId=" + outages[i]["@id"]).append().text(outages[i]["@id"])
								)
							)
						)
					);
				/**************************************************************************************************************************************************************************/
				
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
			/**************************************************************************************************************************************************************************/
			DIVobj.attr("class", "row-fluid").append(
				DIVobj.clone().attr("class", "span12 well well-small").append(
					TABLEobj.clone().attr("class", "span12 well well-small").append(
						TRobj.clone().append(
							TDobj.clone().append(
								Aobj.clone().attr("href", "#").append().text(outages["@id"])
							)
						),
						TRobj.clone().append(
							TDobj.clone().append(
								Aobj.clone().attr("href", "#").append().text(nullCheckJsonObject(outages["serviceLostEvent"]["serviceType"], "name"))
							)
						),
						TRobj.clone().append(
							TDobj.clone().append().text(new Date(nullCheckJsonObject(outages["serviceLostEvent"],"time")).format('yy-MM-dd hh:mm:ss'))
						),
						TRobj.clone().append(
							TDobj.clone().append().text(new Date(nullCheckJsonObject(outages["serviceRegainedEvent"], "time")).format('yy-MM-dd hh:mm:ss'))
						),
						TRobj.clone().append(
							TDobj.clone().append(
								Aobj.clone().attr("href", "#").append().text(outages["@id"])
							)
						)
					)
				)
			);
			/**************************************************************************************************************************************************************************/
		}

		//str += "</table></div>";

	}
	//return str;
	/*************/
	return DIVobj;
	/*************/
}
/**
 * Outage �뺣낫瑜�Str�뚭렇濡�留뚮뱾�댁���
 * 
 * @param jsonObj
 */
function getOutageInfoBox(jsonObj) {
	
	console.log("-----------getOutageInfoBox-----------");	
	console.log(jsonObj);
	
	var outageObj = jsonObj["outage"];

	var outageInfoStr = '<div class="row-fluid">' + '	<h5>以묐떒['
			+ outageObj["@id"]
			+ ']</h5>'
			+ '</div>'
			+ '<div class="row-fluid">'
			+ '	<div class="span12 well well-small">'
			+ '		<table class="table table-striped">'
			+ '			<tr>'
			+ '				<th>�몃뱶</th>'
			+ '				<td>'
			+ '					<a href="/'
			+ version
			+ '/search/node/nodeDesc.do?nodeId='
			+ outageObj["serviceLostEvent"]["nodeId"]
			+ '">'
			+ outageObj["serviceLostEvent"]["nodeLabel"]
			+ '					</a>'
			+ '				</td>'
			+ '				<th>以묐떒 �쒓컙</th>'
			+ '				<td>'
			+ new Date(outageObj["serviceLostEvent"]["time"])
					.format('yy-MM-dd hh:mm:ss')
			+ '</td>'
			+ '			</tr>'
			+ '			<tr>'
			+ '				<th>�대깽��/th>'
			+ '				<td><a href="/'
			+ version
			+ '/search/event/eventDesc.do?eventId='
			+ outageObj["serviceLostEvent"]["@id"]
			+ '">'
			+ outageObj["serviceLostEvent"]["@id"]
			+ '</a></td>'
			
			+ '				<th>�명꽣�섏씠��/th>'
			+ '				<td>'
			+ outageObj["ipAddress"]
			+ '</td>'
			+ '			</tr>'
			+ '			<tr>'
			+ '				<th style ="width: 82px;";>�몄뒪��/th>'
			+ '				<td>'
			+ outageObj["serviceLostEvent"]["host"]
			+ '</td>'
			+ '				<th>�쒕퉬��/th>'
			+ '				<td>'
			+ outageObj["serviceLostEvent"]["serviceType"]["name"]
			+ '</td>'
			+ '			</tr>'
			+ '			<tr>'
			+ '				<th>硫붿꽭吏�濡쒓렇</th>'
			+ '				<td colspan="5">'
				+outageObj["serviceLostEvent"]["logMessage"]
			//+ nullCheckJsonObject(
			//	+outageObj["serviceLostEvent"]["logMessage"]
			+ '				</td>' 
			+ '				<td colspan="4"></td>'
			+ '			</tr>' 
			+ '		</table>' 
			+ '	</div>' 
			+ '</div>';

	return outageInfoStr;
}

/**
 * Outage search �뺣낫瑜�table �뚭렇 Str濡�留뚮뱾�댁���
 * 
 * @param jsonObj
 */
function getTabletagToOutageSearchJsonObj(jsonObj) {
	var outages = jsonObj["outage"];
	var str = "";
	if(jsonObj["@totalCount"] == 0){
	 
		str += "<tr>";
		str += "<td></td>";
		str += "<td></td>";
		str += "<td></td>";
		str += "<td><h4 class='text-error'>장애가 검색되지 않습니다.</h4></td>";
		str += "</tr>";
		
		
	
	} else if (jsonObj["@count"] != "undefined" && jsonObj["@count"] != 0) {
		
		if (jsonObj["@count"] > 1) {

			for ( var i in outages) {

				str += "<tr>";
				
				str += "<td><a href='/" + version
						+ "/search/outage/outageDesc?outageId="
						+ outages[i]["@id"] + "'>" + outages[i]["@id"]
						+ "</a></td>";
				
				str += "<td><a href='/" + version
						+ "/search/node/interfaceDesc.do?nodeId=" + outages[i]["serviceLostEvent"]["nodeId"]
						+ "&intf=" + outages[i]["ipAddress"] + "'>"
						+ outages[i]["ipAddress"] + "</a></td>";
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
				str += "</tr>";
			}

		} else {
			outages = outages == null ? jsonObj : jsonObj["outage"];
			str += "<tr>";
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
			str += "</tr>";
		}

	}
	return str;
}
