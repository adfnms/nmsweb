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
		async : false,
		contentType : 'application/json',
		error : function(data) {
			alert('모든 노드정보 가져오기 실패');
		},
		success : function(data) {
			// 콜백함수
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
}

function getNodeListSideBar(callback, data) {

	$.ajax({
		type : 'get',
		url : '/' + version + '/nodes',
		dataType : 'json',
		data : data,
		async : false,
		contentType : 'application/json',
		error : function(data) {
			alert('모든 노드정보 가져오기 실패');
		},
		success : function(data) {
			// 콜백함수
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
}

/** get interface Info
 * @param callback
 * @param data
 */
function getInterfaceInfo(callback, nodeId, ipAddress) {
	
	$.ajax({
		type : 'get',
		url : '/' + version + '/nodes/'+nodeId+'/ipinterfaces/'+ipAddress,
		dataType : 'json',
		async : false,
		contentType : 'application/json',
		error : function(data) {
			alert('모든 노드정보 가져오기 실패');
		},
		success : function(data) {
			// 콜백함수
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
}

/** get interface Info
 * @param callback
 * @param data
 */
function getSnmpInterfaceInfo(callback, nodeId) {

	$.ajax({
		type : 'get',
		url : '/' + version + '/nodes/'+nodeId+'/snmpinterfaces',
		dataType : 'json',
		async : false,
		contentType : 'application/json',
		error : function(data) {
			alert('SnmpInterface 가져오기 실패');
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
	
	var _return = "";

	$.ajax({
		type : 'get',
		url : '/' + version + '/nodes/' + nodeId,
		dataType : 'json',
		contentType : 'application/json',
		async: false,
		error : function(data) {
			alert("[" + nodeId + '] 노드 정보 가져오기 서비스 실패');
		},
		success : function(data) {
			// 콜백함수
			if (typeof callback == "function") {
				callback(data);
			}else{
				_return = data;
			}
		}
	});
	
	return _return;
}

/**
 * search node from serviceId
 * 제공서비스 검색기능
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
			//data = JSON.stringify(data).replaceAll('"createTime"', '"@time"');
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
		async:false,
		error : function(data) {
			alert("[" + ipAddress + '] 아이피 정보 검색 실패');
		},
		success : function(data) {
			console.log(data);
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




function changeNodeLabelPop(nodeId){
	
	var settings ="toolbar=no ,width=350 ,height=255 ,directories=no,status=no,scrollbars=no,menubar=no";
	var url = "/" + version + "/admin/changeNodeLabel.pop?nodeId="+nodeId;
	var winObject = window.open(url, "changeNodeLabelPop", settings);
	winObject.focus();	

}

/**
 * 
 */
function goNodeManagePage(nodeId){
	location.href ="/"+version+"/admin/node/nodeMng.do?nodeId="+nodeId;
}

/**
 * 
 */



/** get Bode Availability
 * @param callback
 * @param nodes
 */
function getNodeAvailability(callback, nodes){
	
	if (nodes == "") {
		alert("노드 ID가 없습니다.");
		return;
	}
	
	var nodesData  = "nodeId=" + nodes;
	
	$.ajax({
		type : 'get',
		url : '/' + version + '/availability/node',
		dataType : 'json',
		contentType : 'application/json',
		data : nodesData,
		error : function(data) {
			alert("[" + nodeId + '] 노드 가용성 가져오기 실패');
		},
		success : function(data) {

			if (typeof callback == "function") {
				callback(data);
			}
			
		}
	});
	
}


function getInterfaceAvailability(nodeId, ipAddress){
	
	var ipaddrData  = "ipAddr=" + ipAddress;
	var interfaceAvail  = "";
	
	$.ajax({
		type : 'get',
		url : '/' + version + '/availability/node/'+nodeId+'/interface',
		dataType : 'json',
		contentType : 'application/json',
		async: false,
		data : ipaddrData,
		error : function(data) {
			alert("[" + nodeId + '] 인터페이스 가용성 가져오기 실패');
		},
		success : function(data) {
			interfaceAvail  = data["interfaceAvailability"][0]["avail"];

		}
	});
	
	return interfaceAvail;
	
}

/** delete Node
 * @param nodeId
 */
function deleteNode(nodeId){
	if (nodeId == "") {
		alert("노드 ID가 없습니다.");
		return;
	}
	
	if(!confirm("정말 삭제하시겠습니까?")){return;}
	
	$.ajax({
		type : 'delete',
		url : '/' + version + '/nodes/' + nodeId,
		dataType : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert("Id [" + nodeId + '] 노드 삭제 실패');
		},
		success : function(data) {
			location.reload();
		}
	});
}


/**  scan and add Node 
 * @param ipAddress
 */
function nodeScan(callback, ipAddress){

	$.ajax({
		type : 'post',
		url : '/' + version + '/nodes/scan/' + ipAddress,
		dataType : 'json',
		contentType : 'application/json',
		async:false,
		error : function(data) {
 
			if (typeof callback == "function") {
				callback(data);
			}
			
		},
		success : function(data) {

			if (typeof callback == "function") {
				callback(data);
			}

		}
	});
	
}

/** change Node_Label
 * @param nodeId
 * @param label
 */
function changeNodeLabel(nodeId, label){
	
	var _return = false;

	$.ajax({
		type : 'put',
		url : '/' + version + '/nodes/'+nodeId,
		data: 'label='+label,
		contentType:'application/x-www-form-urlencoded',
		async: false,
		error : function(data) {
			alert("[" + nodeId + '] 노드 라벨 변경 실패');
			_return = false;
		},
		success : function(data) {
			_return = true;
		}
	});

	return _return;
}

/** Manage Inteface
 * @param nodeId
 * @param label
 */
function manageInteface(nodeId, ipAddress, isManaged){
	
	var _return = false;
	
	$.ajax({
		type : 'put',
		url : '/' + version + '/nodes/'+nodeId+'/ipinterfaces/'+ipAddress,
		data: 'isManaged='+isManaged,
		async: false,
		error : function(data) {
			alert("[" + ipAddress + '] 인터페이스 관리 실패');
			_return = false;
		},
		success : function(data) {
			_return = true;
		}
	});

	return _return;
}

/** Manage Inteface
 * @param nodeId
 * @param label
 */
function manageService(nodeId, ipAddress, serviceName, status){
	
	var _return = false;
	
	$.ajax({
		type : 'put',
		url : '/' + version + '/nodes/'+nodeId+'/ipinterfaces/'+ipAddress+'/services/'+serviceName,
		data: 'status='+status,
		async: false,
		error : function(data) {
			alert("[" + serviceName + '] 서비스 관리 실패');
			_return = false;
		},
		success : function(data) {
			_return = true;
		}
	});

	return _return;
}


/** 
 * @param nodeId
 * @param ifIndex
 * @param collect
 * @returns {Boolean}
 */
function manageSnmpService(nodeId, ifIndex, collect){
	
	var _return = false;
	
	$.ajax({
		type : 'put',
		url : '/' + version + '/nodes/'+nodeId+'/snmpinterfaces/'+ifIndex,
		data: 'collect='+collect,
		async: false,
		error : function(data) {
			alert("노드아이디 : "+nodeId+", [" + collect + '] SNMP 서비스 관리 실패');
			_return = false;
		},
		success : function(data) {
			_return = true;
		}
	});

	return _return;
}

//메뉴의 운영관리 -> 노드 관리 -> +노드 추가 버튼 클릭시 새로 생성되는 팝업창
function addNodePop(){
	var settings ="toolbar=no ,width=350 ,height=205 ,directories=no,status=no,scrollbars=no,menubar=no";
	var winObject = window.open("/" + version + "/admin/addNode.pop", "addNodePop", settings);
	winObject.focus();	
}

/** ************************ view String edit **************************** */

/**
 * categories 정보를 table 테그 Str로 만들어준다.
 * 
 * @param jsonObj
 *            jsonObj["categories"]
 */
/*function getTabletagToCategoryJsonObj(jsonObj) {
	
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
}*/

function getTableToSearchJsonObj(jsonObj, auth){
	var nodeObj = jsonObj["nodeList"];;

	var TBODYobj = $("<tbody></tbody>");
	var TRobj = $("<tr></tr>");
	var TDobj = $("<td></td>");
	var H4obj = $("<h4></h4>");
	var Aobj = $("<a></a>");
	var BUTTONobj = $("<button></button>");
	
	TBODYobj.append(	
		TRobj.clone().append(
			TDobj.clone().append(
				H4obj.clone().text("id")	
			),
			TDobj.clone().append(
				H4obj.clone().text("node")	
			),
			TDobj.clone().append(
				H4obj.clone().text("Node Create Time")	
			),
			TDobj.clone().attr("class","span2").append(
				H4obj.clone().text("")	
			),
			TDobj.clone().attr("class","span2").append(
				H4obj.clone().text("")	
			)
		)
	);
	
		/*str += "<tr>";
		str += "<td><h4>id</h4></td>";
		str += "<td><h4>Node</h4></td>";
		str += "<td><h4>Node Create Time</h4></td>";
		str += "<td><h4>&nbsp;</h4></td>";
		str += "<td><h4>&nbsp;</h4></td>";
		str += "</tr>";*/
	
	if (nodeObj == null || nodeObj ==""){
		TBODYobj.append(	
			TRobj.append().text("노드 목록이 없습니다!")
		);
		
		/*str += "<tr>";
		str += "<td>노드 목록이 없습니다!</td>";
		str += "</tr>";*/		
	
	}else{
		var TRobjClone;
		for (var i in nodeObj) {
			TRobjClone = TRobj.clone();
			TBODYobj.append(
				TRobjClone.append(
					TDobj.clone().append().text(nodeObj[i]["nodeid"]),
					TDobj.clone().append(
						Aobj.clone().attr("href","/"+version+"/search/node/nodeDesc.do?nodeId="+ nodeObj[i]["nodeid"]+"").append().text(nodeObj[i]["nodelabel"])),
					TDobj.clone().append().text(new Date(nodeObj[i]["nodecreatetime"]).format('yy-MM-dd hh:mm:ss'))
				)
			);
			
			if(auth=="Admin")
			{
				TRobjClone.append(
					TDobj.clone().append(
						BUTTONobj.clone().attr("type","button").attr("class","btn btn-info").attr("onclick","javascript:goNodeManagePage("+nodeObj[i]["nodeid"]+")").text("관리")),
					TDobj.clone().append(
						BUTTONobj.clone().attr("type","button").attr("class","btn btn-danger").attr("onclick","javascript:deleteNode("+nodeObj[i]["nodeid"]+")").text("삭제")
					)
						
				);
			}
			else
			{
				TRobjClone.append(
					TDobj.clone().append(),
					TDobj.clone().append()
				);
			}
			
		}
		
//		if(auth!="Admin"){
//			TRobjClone = TRobj.clone();
//			for (var i in nodeObj) {
//				TBODYobj.append(
//					TRobjClone.append(
//						TDobj.clone().append().text(nodeObj[i]["nodeid"]),
//						TDobj.clone().append(
//							Aobj.clone().attr("href","/"+version+"/search/node/nodeDesc.do?nodeId="+ nodeObj[i]["nodeid"]+"").append().text(nodeObj[i]["nodelabel"])),
//						TDobj.clone().append().text(new Date(nodeObj[i]["createTime"]).format('yy-MM-dd hh:mm:ss')),
//						TDobj.clone().append(),
//						TDobj.clone().append()
//					)
//				);
//			}
//		}else if(auth=="Admin"){
//			for (var i in nodeObj) {
//				TBODYobj.append(
//					TRobjClone.append(
//						TDobj.clone().append().text(nodeObj[i]["nodeid"]),
//						TDobj.clone().append(
//							Aobj.clone().attr("href","/"+version+"/search/node/nodeDesc.do?nodeId="+ nodeObj[i]["nodeid"]+"").append().text(nodeObj[i]["nodelabel"])),
//						TDobj.clone().append().text(new Date(nodeObj[i]["createTime"]).format('yy-MM-dd hh:mm:ss')),				
//						TDobj.clone().append(
//							BUTTONobj.clone().attr("type","button").attr("class","btn btn-info").attr("onclick","javascript:goNodeManagePage("+nodeObj[i]["@id"]+")").text("관리")),
//						TDobj.clone().append(
//							BUTTONobj.clone().attr("type","button").attr("class","btn btn-danger").attr("onclick","javascript:deleteNode("+nodeObj[i]["@id"]+")").text("삭제")
//						)
//					)				
//				);		
//			}
//		}
	}	
	
	return TBODYobj;
}


function getTabletagToSearchJsonObj(jsonObj, auth){
	var nodeObj = jsonObj["node"] != null ? jsonObj["node"] : jsonObj["nodes"];
	var str = "";
	
	var TBODYobj = $("<tbody></tbody>");
	var TRobj = $("<tr></tr>");
	var TDobj = $("<td></td>");
	var H4obj = $("<h4></h4>");
	var Aobj = $("<a></a>");
	var BUTTONobj = $("<button></button>");
	
	TBODYobj.append(	
		TRobj.clone().append(
			TDobj.clone().append(
				H4obj.clone().text("id")	
			),
			TDobj.clone().append(
				H4obj.clone().text("node")	
			),
			TDobj.clone().append(
				H4obj.clone().text("Node Create Time")	
			),
			TDobj.clone().attr("class","span2").append(
				H4obj.clone().text("")	
			),
			TDobj.clone().attr("class","span2").append(
				H4obj.clone().text("")	
			)
		)
	);
	
		/*str += "<tr>";
		str += "<td><h4>id</h4></td>";
		str += "<td><h4>Node</h4></td>";
		str += "<td><h4>Node Create Time</h4></td>";
		str += "<td><h4>&nbsp;</h4></td>";
		str += "<td><h4>&nbsp;</h4></td>";
		str += "</tr>";*/
	
	if (jsonObj["node"] == null || jsonObj["node"] ==""){
		TBODYobj.append(	
			TRobj.append().text("노드 목록이 없습니다!")
		);
		
		/*str += "<tr>";
		str += "<td>노드 목록이 없습니다!</td>";
		str += "</tr>";*/		
	
	}else if (jsonObj["@totalCount"] == 1 || jsonObj["@count"] == 1) {
		var TRobjClone = TRobj.clone();
		TBODYobj.append(
				TRobjClone.append(
					TDobj.clone().append().text(nodeObj["@id"]),
					TDobj.clone().append(
						Aobj.clone().attr("href","/"+version+"/search/node/nodeDesc.do?nodeId="+ nodeObj["@id"]+"").append().text(nodeObj["@label"])),
					TDobj.clone().append().text(new Date(nodeObj["createTime"]).format('yy-MM-dd hh:mm:ss'))
			)
		);
		
		if(auth!="Admin"){
			TRobjClone.append(
				TDobj.clone().append(),
				TDobj.clone().append()
			);
		}
		else
		{
			TRobjClone.append(
				TDobj.clone().append(
					BUTTONobj.clone().attr("type","button").attr("class","btn btn-info").attr("onclick","javascript:goNodeManagePage("+nodeObj["@id"]+")").text("관리")),
				TDobj.clone().append(
					BUTTONobj.clone().attr("type","button").attr("class","btn btn-danger").attr("onclick","javascript:deleteRequisitionInNode('"+nodeObj["@id"]+"','"+nodeObj["@foreignSource"]+"','"+nodeObj["@foreignId"]+"')").text("삭제")
				)
			);
		}

		/*str += "<tr>";
		str += "<td>1</td>";
		str += "<td><a href='/" + version + "/search/node/nodeDesc.do?nodeId="+ nodeObj["@id"]+ "'>"
				+ nodeObj["@label"]+"&nbsp;[&nbsp;"+nodeObj["@id"]+"&nbsp;]"
				+ "</a></td>";
		str += "<td>"
				+  new Date(nodeObj["createTime"]).format('yy-MM-dd hh:mm:ss')
				+ "</td>";
		if(auth == "Admin"){
			str += '<td><button type="button" class="btn btn-info" title="관리" onclick="javascript:goNodeManagePage(\''+nodeObj["@id"]+'\');">관리</button></td>';
			str += '<td><button type="button" class="btn btn-danger" title="삭제" onclick="javascript:deleteNode(\''+nodeObj["@id"]+'\');">삭제</button></td>';
		}
		str += "</tr>";*/
		
	}else if (jsonObj["@totalCount"] > 1 || jsonObj["@count"] > 1) {
		for (var i in nodeObj) {
			var TRobjClone = TRobj.clone();
			TBODYobj.append(
					TRobjClone.append(
						TDobj.clone().append().text(nodeObj[i]["@id"]),
						TDobj.clone().append(
							Aobj.clone().attr("href","/"+version+"/search/node/nodeDesc.do?nodeId="+ nodeObj[i]["@id"]+"").append().text(nodeObj[i]["@label"])),
						TDobj.clone().append().text(new Date(nodeObj[i]["createTime"]).format('yy-MM-dd hh:mm:ss'))
				)
			);
			
			if(auth!="Admin"){
				TRobjClone.append(
					TDobj.clone().append(),
					TDobj.clone().append()
				);
			}
			else
			{
				TRobjClone.append(
					TDobj.clone().append(
						BUTTONobj.clone().attr("type","button").attr("class","btn btn-info").attr("onclick","javascript:goNodeManagePage("+nodeObj[i]["@id"]+")").text("관리")),
					TDobj.clone().append(
						BUTTONobj.clone().attr("type","button").attr("class","btn btn-danger").attr("onclick","javascript:deleteRequisitionInNode('"+nodeObj[i]["@id"]+"','"+nodeObj[i]["@foreignSource"]+"','"+nodeObj[i]["@foreignId"]+"')").text("삭제")
					)
				);
			}
		}
	}	
	
	/*for (var i in nodeObj) {
		str += "<tr>";
		str += "<td>"
				+ "[&nbsp;"+nodeObj[i]["@id"]+"&nbsp;]"
				+ "</td>";
		str += "<td><a href='/" + version + "/search/node/nodeDesc.do?nodeId="+ nodeObj[i]["@id"]+ "'>"
				+ nodeObj[i]["@label"]
				+ "</a></td>";
		str += "<td>"
				+  new Date(nodeObj[i]["createTime"]).format('yy-MM-dd hh:mm:ss')
				+ "</td>";
		if(auth == "Admin"){
			str += '<td><button type="button" class="btn btn-info" title="관리" onclick="javascript:goNodeManagePage(\''+nodeObj[i]["@id"]+'\');">관리</button></td>';
			str += '<td><button type="button" class="btn btn-danger" title="삭제" onclick="javascript:deleteNode(\''+nodeObj[i]["@id"]+'\');">삭제</button></td>';
		}
		str += "</tr>";
	}*/
	
	return TBODYobj;
	//return str;
}
/******************************************************************************************************************************/
function getSearchNodeserviceJsonObj(jsonObj, auth){
	var nodeObj = jsonObj["nodes"] != null ? jsonObj["nodes"] : jsonObj["nodes"];
	var str = "";
	
	var TBODYobj = $("<tbody></tbody>");
	var TRobj = $("<tr></tr>");
	var TDobj = $("<td></td>");
	var Aobj = $("<a></a>");
	var H4obj = $("<h4></h4>");
	var BUTTONobj = $("<button></button>");
	
	
	TBODYobj.append(	
			TRobj.clone().append(
				TDobj.clone().append(
					H4obj.clone().text("id")	
				),
				TDobj.clone().append(
					H4obj.clone().text("node list")	
				),
				TDobj.clone().append(
					H4obj.clone().text("")	
				),
				TDobj.clone().attr("class","span2").append(
					H4obj.clone().text("")	
				),
				TDobj.clone().attr("class","span2").append(
					H4obj.clone().text("")	
				)
			)
		);
	
if (jsonObj["nodes"] == null || jsonObj["nodes"] ==""){
	TBODYobj.append(	
		TRobj.attr("class","span2").append().text("노드 목록이 없습니다!")
	);
		/*str += "<tr>";
		str += "<td>노드 목록이 없습니다!</td>";
		str += "</tr>";*/
		
	}else if (nodeObj.length == 1) {
		if(auth!="Admin"){
			TBODYobj.append(
				TRobj.clone().append(
					TDobj.clone().append(
						Aobj.clone().attr("href","/"+version+"/search/node/nodeDesc.do?nodeId="+ nodeObj[0]["@id"]+"").append().text(nodeObj[0]["@id"])),
					TDobj.clone().append(
						Aobj.clone().attr("href","/"+version+"/search/node/nodeDesc.do?nodeId="+ nodeObj[0]["@id"]).append().text(nodeObj[0]["@label"])),
					TDobj.clone().append(
						H4obj.clone().text("")),
					TDobj.clone().attr("class","span2").append(),
					TDobj.clone().attr("class","span2").append()
				)
			);
		}else if(auth=="Admin"){
			TBODYobj.append(
				TRobj.clone().append(
					TDobj.clone().append(
						Aobj.clone().attr("href","/"+version+"/search/node/nodeDesc.do?nodeId="+ nodeObj[0]["@id"]+"").append().text(nodeObj[0]["@id"])),
					TDobj.clone().append(
						Aobj.clone().attr("href","/"+version+"/search/node/nodeDesc.do?nodeId="+ nodeObj[0]["@id"]).append().text(nodeObj[0]["@label"])),	
					TDobj.clone().append(
						H4obj.clone().text("")),
					TDobj.clone().attr("class","span2").append(
						BUTTONobj.clone().attr("type","button").attr("class","btn btn-info").attr("onclick","javascript:goNodeManagePage("+nodeObj[0]["@id"]+")").text("관리")),
					TDobj.clone().attr("class","span2").append(
						BUTTONobj.clone().attr("type","button").attr("class","btn btn-danger").attr("onclick","javascript:deleteNode("+nodeObj[0]["@id"]+")").text("삭제")
					)
				)				
			);		
		}
		
		/*str += "<tr>";
		str += "<td>1</td>";
		str += "<td><a href='/" + version + "/search/node/nodeDesc.do?nodeId="+ nodeObj[0]["@id"]+ "'>"
				+ nodeObj[0]["@label"]+"&nbsp;[&nbsp;"+nodeObj[0]["@id"]+"&nbsp;]"
				+ "</a></td>";
		
		if(auth == "Admin"){
			str += '<td><button type="button" class="btn btn-info" title="관리" onclick="javascript:goNodeManagePage(\''+nodeObj[0]["@id"]+'\');">관리</button></td>';
			str += '<td><button type="button" class="btn btn-danger" title="삭제" onclick="javascript:deleteNode(\''+nodeObj[0]["@id"]+'\');">삭제</button></td>';
		}
		str += "</tr>";*/
		
	}else if (nodeObj.length > 1) {
		if(auth!="Admin"){
			for (var i in nodeObj) {
				TBODYobj.append(
					TRobj.clone().append(
						TDobj.clone().append(
							Aobj.clone().attr("href","/"+version+"/search/node/nodeDesc.do?nodeId="+ nodeObj[i]["@id"]+"").append().text(nodeObj[i]["@id"])),
						TDobj.clone().append(
							Aobj.clone().attr("href","/"+version+"/search/node/nodeDesc.do?nodeId="+ nodeObj[i]["@id"]+"").append().text(nodeObj[i]["@label"])),
						TDobj.clone().append(
							H4obj.clone().text("")),
						TDobj.clone().attr("class","span2").append(),
						TDobj.clone().attr("class","span2").append()
					)
				);
			}
		}else if(auth=="Admin"){
			
			for (var i in nodeObj) {
				TBODYobj.append(
					TRobj.clone().append(
						TDobj.clone().append(
							Aobj.clone().attr("href","/"+version+"/search/node/nodeDesc.do?nodeId="+ nodeObj[i]["@id"]+"").append().text(nodeObj[i]["@id"])),
						TDobj.clone().append(
							Aobj.clone().attr("href","/"+version+"/search/node/nodeDesc.do?nodeId="+ nodeObj[i]["@id"]+"").append().text(nodeObj[i]["@label"])),
						TDobj.clone().append(
								H4obj.clone().text("")),
						TDobj.clone().attr("class","span2").append(
							BUTTONobj.clone().attr("type","button").attr("class","btn btn-info").attr("onclick","javascript:goNodeManagePage("+nodeObj[i]["@id"]+")").text("관리")),
						TDobj.clone().attr("class","span2").append(
							BUTTONobj.clone().attr("type","button").attr("class","btn btn-danger").attr("onclick","javascript:deleteNode("+nodeObj[i]["@id"]+")").text("삭제")
						)
					)				
				);		
			}
		}
		
		/*for (var i in nodeObj) {
			str += "<tr>";
			str += "<td>"
					+ "[&nbsp;"+nodeObj[i]["@id"]+"&nbsp;]"
					+ "</td>";
			str += "<td><a href='/" + version + "/search/node/nodeDesc.do?nodeId="+ nodeObj[i]["@id"]+ "'>"
					+ nodeObj[i]["@label"]
					+ "</a></td>";
			
			if(auth == "Admin"){
				str += '<td><button type="button" class="btn btn-info" title="관리" onclick="javascript:goNodeManagePage(\''+nodeObj[i]["@id"]+'\');">관리</button></td>';
				str += '<td><button type="button" class="btn btn-danger" title="삭제" onclick="javascript:deleteNode(\''+nodeObj[i]["@id"]+'\');">삭제</button></td>';
			}
			str += "</tr>";
		}*/
		
	}
	
	return TBODYobj;
	//return str;
}
/*****************************노드리스트*************************************/
/*index.do page의 감시대상목록*/
function getNodelistJsonObj(jsonObj){
	
	var nodeObj = jsonObj["node"] != null ? jsonObj["node"] : jsonObj["nodes"];
	//var str = "";
	var ULobj = $("<ul></ul>");
	var TRobj = $("<tr></tr>");
	var TDobj = $("<td></td>");
	var Aobj = $("<a></a>");
	var LIobj = $("<li></li>");
	var H5obj = $("<font></font>");
	
	if (jsonObj["node"] == null || jsonObj["node"] ==""){
		
		ULobj.append(
			LIobj.clone().append(
				Aobj.clone().text("노드 목록이 없습니다.")			
			)
		);
		/*str += "<li>";
		str += "<a href=\"\">노드 목록이 없습니다!</a>";
		str += "</li>";*/
		
	}else if (jsonObj["@totalCount"] == 1 || jsonObj["@count"] == 1) {
		
		ULobj.append(
			LIobj.clone().append(
				Aobj.clone().attr("href","/"+version+"/search/node/nodeDesc.do?nodeId="+ nodeObj["@id"]+"").append(
					H5obj.clone().text( nodeObj["@label"])
				)
			)
		);
		/*str += "<li><a href='/" + version + "/search/node/nodeDesc.do?nodeId="+ nodeObj["@id"]+ "'><h5>"
				+ nodeObj["@label"]
				+ "</h5></a></li>";*/
	}else if (jsonObj["@totalCount"] > 1 || jsonObj["@count"] > 1) {
			for ( var i in nodeObj) {
				
				ULobj.append(
					LIobj.clone().append(
						Aobj.clone().attr("href","/" + version + "/search/node/nodeDesc.do?nodeId="+ nodeObj[i]["@id"]+ "").append(
							H5obj.clone().text( nodeObj[i]["@label"])
						)
					)
				);
				
			/*str += "<li><a href='/" + version + "/search/node/nodeDesc.do?nodeId="+ nodeObj[i]["@id"]+ "'><h5>"
					+ nodeObj[i]["@label"]
					+ "</h5></a></li>";*/
			}
		} 
	
	//return str;
	return ULobj;
}
/****************************************노드 라벨 목록 리스트********************************************************/
function getNodeLablelistJsonObj(jsonObj){
	//for(var i in jsonObj){
		//console.log("------------------jsonObj--------------------");
		//console.log(jsonObj["node"][i]["@label"]);
		var TRobj = $("<tr></tr>");
		TRobj.text("2222");
		return TRobj;
	//}
}


/*****************************//*감시대상목록*노드리스트****************************************/
/** 이벤트 정보를 table 테그 Str로 만들어준다. 
 * @param jsonObj
 */
function getTabletagToAvailJsonObj(nodeId, ipAddress){
	
	//var str = "";
	var jsonObj = null;
	var AvailJsonObj = null;
	
	/***********************************/
	var TABLEobj = $("<table></table>");
	var TRobj = $("<tr></tr>");
	var TDobj = $("<td></td>");
	var Aobj = $("<a></a>");
	/***********************************/
	
	$.ajax({
		type : 'get',
		url : '/' + version + '/nodes/'+nodeId+'/ipinterfaces/'+ipAddress+'/services',
		dataType : 'json',
		contentType : 'application/json',
		async : false,
		error : function(data) {
			alert("[" + ipAddress + '] Avail 정보 검색 실패');
		},
		success : function(data) {

			jsonObj = data;
		}
	});
		
	if(jsonObj == null){return false;}
	
	if (jsonObj["@count"] > 0) {
		
		var serviceObj = jsonObj["service"];
		
		
		var data = "";
		
		if (jsonObj["@count"] > 1) {
			serviceObj.sort(serviceSort);
			for ( var i in serviceObj) {
				
				if(data == ""){
					data += "serviceId="+serviceObj[i]["serviceType"]["@id"];
				}else{
					data += "&serviceId="+serviceObj[i]["serviceType"]["@id"];
				}
			}

		} else {
			data += "serviceId="+serviceObj["serviceType"]["@id"];
		}
		
		$.ajax({
			type : 'get',
			url : '/' + version + '/availability/node/'+nodeId+'/interface/'+ipAddress+'/service',
			dataType : 'json',
			contentType : 'application/json',
			async : false,
			data : data,
			error : function(data) {
				alert("[" + ipAddress + '] 서비스 정보 검색 실패');
			},
			success : function(data) {

				AvailJsonObj = data;
				
			}
		});
		if(AvailJsonObj == null){return false;}
		var serviceObj = jsonObj["service"];
		
		/******************************************************/
		//TABLEobj.attr("class", "table table-striped").append(
		/******************************************************/
		//str += '<table class="table table-striped">';

				if (jsonObj["@count"] > 1) {
					for ( var i in serviceObj) {
						var serviceAvail = "";
						var serviceNm = serviceObj[i]["serviceType"]["name"];
						var serviceStatus = serviceObj[i]["@status"];
						for(var j in AvailJsonObj["serviceAvailability"]){
							if(AvailJsonObj["serviceAvailability"][j]["serviceId"] == serviceObj[i]["serviceType"]["@id"]){
								serviceAvail = AvailJsonObj["serviceAvailability"][j]["avail"];
								break;
							}
						}
						
						var serviceId = serviceObj[i]["serviceType"]["@id"];
						/****************************************************************************************************************************************************************************************************/
						TABLEobj.attr("class", "table table-striped").append(
								TRobj.clone().append(
									TDobj.clone().append(
										Aobj.clone().attr("href", "javascript:goServiceDiv('"+nodeId+"', '"+ipAddress+"','"+serviceNm+"','"+serviceId+"')").text(serviceNm)
									),
									TDobj.clone().text(availToStringFromStatoCode(serviceStatus,Number(serviceAvail).toFixed(3)))
								)
						);
						
						/****************************************************************************************************************************************************************************************************/
						/*str += '<tr>';
						//str += '	<td><a href="/'+version+'/search/service/serviceDesc?nodeId='+nodeId+'&intf='+ipAddress+'&serviceNm='+serviceObj[i]["serviceType"]["name"]+'">';
						str += '	<td><a href="javascript:goServiceDiv( \''+nodeId+'\', \''+ipAddress+'\')">';
						str += serviceObj[i]["serviceType"]["name"];
						str += '	</a></td>';
						str += '	<td>';
						str += availToStringFromStatoCode(serviceObj[i]["@status"],Number(serviceAvail).toFixed(3));
						str += '	</td>';
						str += '</tr>';*/
					}
				} else {
					/**********************************************************************************************************************************************************************************************/
					serviceAvail = AvailJsonObj["serviceAvailability"][0]["avail"];
					TABLEobj.attr("class", "table table-striped").append(
							TRobj.clone().append(
								TDobj.clone().append(
//									Aobj.clone().attr("href","/"+version+"/search/service/serviceDesc?nodeId="+nodeId+'&intf='+ipAddress+'&serviceNm='+serviceObj["serviceType"]["name"]+"").text(serviceObj["serviceType"]["name"])
									Aobj.clone().attr("href", "javascript:goServiceDiv('"+nodeId+"', '"+ipAddress+"','"+serviceObj["serviceType"]["name"]+"','"+serviceObj["serviceType"]["@id"]+"')").text(serviceObj["serviceType"]["name"])
								),
								TDobj.clone().text(availToStringFromStatoCode(serviceObj["@status"],Number(serviceAvail).toFixed(3)))
							)
					);
					/**********************************************************************************************************************************************************************************************/
					/*str += '<tr>';
					str += '	<td><a href="/'+version+'/search/service/serviceDesc?nodeId='+nodeId+'&intf='+ipAddress+'&serviceNm='+serviceObj["serviceType"]["name"]+'">';
					str += serviceObj["serviceType"]["name"];
					str += '	</a></td>';
					str += '	<td>';
					str += availToStringFromStatoCode(serviceObj["@status"],Number(serviceAvail).toFixed(3));
					str += '	</td>';
					str += '</tr>';*/
					
				}
				
		
		/*******************/
		
		/*******************/
		//str += '</table>';
		
	}
	/**************/
	return TABLEobj;
	/**************/
	//return str;
}

function getInterfaceInfoBox(jsonObj){
	
	
	
	var nodeObj = getSpecificNode(null, jsonObj["nodeId"]);

	var str = 	"<div class='row-fluid'>"+
					"<table class='table table-striped well well-small'>"+
					"<tr>"+
					"	<th>노드</th>"+
					"	<td>"+
					'		<a href="/'+version+'/search/node/nodeDesc.do?nodeId='+jsonObj["nodeId"]+'">'
							+nodeObj["@label"]+
					'		</a>'+
					"</td>"+
					"</tr>"+
					"<tr>"+
					"	<th>폴링 상태</th>"+
					"	<td>"+statsToStringFromStatoCode(jsonObj["@isManaged"])+"</td>"+
					"</tr>"+
	//				"<tr>"+
	//				"	<th>폴링 페키지</th>"+
	//				"	<td>??????????????????</td>"+
	//				"</tr>"+
	//				"<tr>"+
	//				"	<th>Interface Index</th>"+
	//				"	<td>??????????????????</td>"+
	//				"</tr>"+
					"<tr>"+
					"	<th>Last Service Scan</th>"+
					"	<td>"+new Date(jsonObj["lastCapsdPoll"]).format('yy-MM-dd hh:mm:ss')+"</td>"+
					"</tr>"+
					"</table>";	
					"</div>";	
	
	return str;
}


/** 서비스 리스트를 <option> 테그 str로 바꿔줌
 * @param jsonObj
 * @returns {String}
 *//*
function getSearchSelectJsonObj(jsonObj) {

	var node= jsonObj["node"];

	var optionNodeIdStr = "";
	if (node.length > 1) {
		optionNodeIdStr += "<option value=''>선택</option>";
		for ( var i in node) {
			optionNodeIdStr += "<option value='"+node[i]["@id"]+"'>"
					+ node[i]["@id"] + "</option>";
		}

	} else {
		optionNodeIdStr += "<option value=''>선택</option>";
		optionNodeIdStr += "<option value='"+node["@id"]+"'>"
				+ node["@id"] + "</option>";

	}

	return optionNodeIdStr;
	
}*/

/** 서비스 리스트를 <option> 테그 str로 바꿔줌
 * @param jsonObj
 * @returns {String}
 */
/*function getSearchSelectNodeJsonObj(jsonObj) {

	var node= jsonObj["node"];

	var optionlabelStr = "";
	if (node.length > 1) {
		optionlabelStr += "<option value=''>선택</option>";
		for ( var i in node) {
			optionlabelStr += "<option value='"+node[i]["@label"]+"'>"
					+ node[i]["@label"] + "</option>";
		}

	} else {
		optionlabelStr += "<option value=''>선택</option>";
		optionlabelStr += "<option value='"+node["@label"]+"'>"
				+ node["@label"] + "</option>";

	}

	return optionlabelStr;
	
}*/






/*2013-10-04*/
function nodeListPop(){
	var foreignSource = $('#hiddenForm input[name=foreignSource]').val();
	var url = "/" + version + "/admin/addNodeList.pop?param=" + foreignSource;
	var settings ="toolbar=no, resizable=no, width=620, height=373, directories=no, " +
				"status=no, scrollbars=no, menubar=no";
	var winObject = window.open(url, 'popup', settings);
	winObject.focus();	
}

function getTableToNodeList(foreignSource, data){
	$('#requisitions').val(foreignSource);
	var dataObj = data['node'];
	var count = data['@totalCount'];
	var str = '';
		str += '<tr>';
		str += '	<td><b>ID</b></td>';
		str += '	<td><b>노드명</b></td>';
		str += '	<td><b>생성 시간</b></td>';
		str += '	<td><b>외부 아이디</b></td>';
		str += '	<td><b>추가</b></td>';
		str += '	<td></td>';
		str += '</tr>';
		if(data['node'] != undefined){
			if(count == 1 && dataObj['@foreignId'] != undefined)
			{

				str += '<tr>';
				str += '	<td>' + dataObj['@id'] + '</td>';
				str += '	<td>' + dataObj['@label'] + '</td>';
				str += '	<td>' + new Date(dataObj["createTime"]).format('yy-MM-dd hh:mm:ss') + '</td>';
				str += '	<td>' + dataObj['@foreignId'] + '</td>';
				str += '	<td><input name="nodeids" type="checkbox" style="margin:0px" name="chk" value=\'' + dataObj['@id']+ '\'/></td>';
				
//				if(dataObj[i]['@foreignSource'] == foreignSource){
//					str += '	<td><input type="checkbox" style="margin:0px" name="chk" checked="checked" value=\'' + dataObj[i]['@foreignId'] + '\',\'' + dataObj[i]['@label'] + '\'/></td>';
//				}else{
//					str += '	<td><input name="nodeids" type="checkbox" style="margin:0px" name="chk" value=\'' + dataObj[i]['@foreignId'] + ',' + dataObj[i]['@label'] + '\'/></td>';
//				}
				str += '	<td><button type="button" class="btn btn-danger btn-mini" onclick="javascript:goNodePage(\'' + dataObj['@id'] + '\');">수정</button></td>';
				str += '</tr>';
			}
			else
			for(var i in dataObj){
				
				if(dataObj[i]['@foreignId'] != undefined)
					continue;
				
				str += '<tr>';
				str += '	<td>' + dataObj[i]['@id'] + '</td>';
				str += '	<td>' + dataObj[i]['@label'] + '</td>';
				str += '	<td>' + new Date(dataObj[i]["createTime"]).format('yy-MM-dd hh:mm:ss') + '</td>';
				str += '	<td>' + dataObj[i]['@foreignId'] + '</td>';
				str += '	<td><input name="nodeids" type="checkbox" style="margin:0px" name="chk" value=\'' + dataObj[i]['@id']+ '\'/></td>';
				
//				if(dataObj[i]['@foreignSource'] == foreignSource){
//					str += '	<td><input type="checkbox" style="margin:0px" name="chk" checked="checked" value=\'' + dataObj[i]['@foreignId'] + '\',\'' + dataObj[i]['@label'] + '\'/></td>';
//				}else{
//					str += '	<td><input name="nodeids" type="checkbox" style="margin:0px" name="chk" value=\'' + dataObj[i]['@foreignId'] + ',' + dataObj[i]['@label'] + '\'/></td>';
//				}
				str += '	<td><button type="button" class="btn btn-danger btn-mini" onclick="javascript:goNodePage(\'' + dataObj[i]['@id'] + '\');">수정</button></td>';
				str += '</tr>';
			}
		}else{
			str += '<tr>';
			str += '	<td>노드가 없습니다.</td>';
			str += '	<td></td>';
			str += '	<td></td>';
			str += '	<td></td>';
			str += '	<td></td>';
			str += '	<td></td>';
			str += '</tr>';
		}
	return str;
}

function goNodePage(nodeId){
	var openNewWindow = window.open("about:blank");
	openNewWindow.location.href ="/"+version+"/admin/node/nodeMng.do?nodeId="+nodeId;
}

function savesAjax(data){
	$.ajax({
		type : 'get',
		url : '/' + version + '/nodes',
		dataType : 'json',
		data : data,
		async : false,
		contentType : 'application/json',
		error : function(data) {
			alert('모든 노드 리스트 가져오기 실패');
		},
		success : function(data) {
			paramNodes(saveNodes, data);
		}
	});
}

function paramNodesCallback(data){
	savesAjax(data);
}

function saveNodes(foreignSource, data){
	var chkObj = $('#nodeFrm input[type=checkbox]');
	$.each(chkObj, function(i){
		var chk = $('#nodeFrm input[type=checkbox]:eq('+i+')');
		if($(chk).attr("checked") != "checked"){
			$(chk).change(function(){
				if($(chk).attr("checked") == "checked"){
					//alert('노드명[' + data['node'][i]['@label'] + '] : ' + '저장');
					saveNodeList(data['node'][i]['@label'], data['node'][i]['@foreignId'], foreignSource, foreignSource);
				}else{
					//alert('노드명[' + data['node'][i]['@label'] + '] : ' + '삭제');
					delnodeList(foreignSource, data['node'][i]['@foreignId']);
				}
			});
		}else{
			$(chk).change(function(){
				if($(chk).attr("checked") == "checked"){
					//alert('노드명[' + data['node'][i]['@label'] + '] : ' + '저장');
					saveNodeList(data['node'][i]['@label'], data['node'][i]['@foreignId'], foreignSource, foreignSource);
				}else{
					//alert('노드명[' + data['node'][i]['@label'] + '] : ' + '삭제');
					delnodeList(foreignSource, data['node'][i]['@foreignId']);
				}
			});
		}
	});
}

function saveNodeList(nodeLabel, foreignId, building, foreignSource){
	var str = "{\"node\":[{\"node-label\": \"" + nodeLabel + "\",\"foreign-id\": \"" + foreignId + "\",\"building\": \"" + building + "\"}]}"; 
	$.ajax({
		type : 'post',
		url : '/' + version + '/requisitions/' + foreignSource + '/nodes',
		contentType : 'application/json',
		data : str,
		error : function(data) {alert('저장 실패');},
		success : function(data) {/*do nothing*/}
	});
}

function delnodeList(foreignSource, foreignId){
	$.ajax({
		type : 'delete',
		url : '/' + version + '/requisitions/' + foreignSource + '/nodes/' + foreignId,
		datatype : 'json',
		contentType : 'application/json',
		error : function(data) {alert('삭제 실패');},
		success : function(data) {/*do nothing*/}
	});
}

function saveAjax(callback, data){
	$.ajax({
		type : 'get',
		url : '/' + version + '/nodes',
		dataType : 'json',
		data : data,
		async : false,
		contentType : 'application/json',
		error : function(data) {
			alert('모든 노드 리스트 가져오기 실패');
		},
		success : function(data) {
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
}

function saves(foreignSource, data){

	var str = getTableToNodeList(foreignSource, data);
	$('#getNodeList').append(str);
}

function save(data){
	paramNodes(saves, data);
}

//URL로부터 파라미터값 가져오기
function paramNodes(callback, data){
	var Request = function(){
		this.getParameter = function(name){
			
			var rtnval = '';
			var IpAddress = unescape(location.href);
			var params = (IpAddress.slice(IpAddress.indexOf('?')+1,IpAddress.length)).split('&');
			for(var i=0 ; i < params.length ; i++){
				var varNm = params[i].split('=')[0];
				if(varNm.toUpperCase() == name.toUpperCase()){
					rtnval = params[i].split('=')[1];
				break;
				}
			}

			if (typeof callback == "function") {
				callback(rtnval, data);
			}
		};
	};
	var request = new Request();
	request.getParameter('param');
}	































