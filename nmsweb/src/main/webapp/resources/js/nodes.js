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
	console.log('/' + version + '/nodes/' + nodeId + '/ipinterfaces');
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

/**
 *  add Node Popup
 */
function addNodePop(){
	
	var settings ="toolbar=no ,width=350 ,height=205 ,directories=no,status=no,scrollbars=no,menubar=no";
	var winObject = window.open("/" + version + "/admin/addNode.pop", "addNodePop", settings);
	winObject.focus();	

}

/**
 *  add Node Popup
 */
function addNodePop(){
	
	var settings ="toolbar=no ,width=350 ,height=205 ,directories=no,status=no,scrollbars=no,menubar=no";
	var winObject = window.open("/" + version + "/admin/addNode.pop", "addNodePop", settings);
	winObject.focus();	

}

/**
 *  add Node Popup
 */
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
function goInterfaceDescPage(nodeId,intf){
	location.href ="/"+version+"/search/node/interfaceDesc.do?nodeId="+nodeId+"&intf="+intf;
}


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
			alert("[" + nodeId + '] 노드 삭제 실패');
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
		data: "label="+label,
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
			alert("[" + collect + '] SNMP 서비스 관리 실패');
			_return = false;
		},
		success : function(data) {
			_return = true;
		}
	});

	return _return;
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

function getTabletagToSearchJsonObj(jsonObj, auth){
	
	var nodeObj = jsonObj["node"] != null ? jsonObj["node"] : jsonObj["nodes"];
	var str = "";

	if (jsonObj["node"] == null || jsonObj["node"] ==""){
		
		str += "<tr>";
		str += "<td>노드 목록이 없습니다!</td>";
		str += "</tr>";
		
	}else if (jsonObj["@totalCount"] == 1 || jsonObj["@count"] == 1) {
		
		
		str += "<tr>";
		str += "<td>1</td>";
		str += "<td><a href='/" + version + "/search/node/nodeDesc.do?nodeId="+ nodeObj["@id"]+ "'>"
				+ nodeObj["@label"]+"&nbsp;[&nbsp;"+nodeObj["@id"]+"&nbsp;]"
				+ "</a></td>";
		str += "<td>"
				+  new Date(nodeObj["createTime"]).format('yy-MM-dd hh:mm:ss')
				+ "</td>";
		if(auth == "Y"){
			str += '<td><button type="button" class="btn btn-info" title="관리" onclick="javascript:goNodeManagePage(\''+nodeObj["@id"]+'\');">관리</button></td>';
			str += '<td><button type="button" class="btn btn-danger" title="삭제" onclick="javascript:deleteNode(\''+nodeObj["@id"]+'\');">삭제</button></td>';
		}
		str += "</tr>";
		
		
	}else if (jsonObj["@totalCount"] > 1 || jsonObj["@count"] > 1) {
			
			for ( var i in nodeObj) {
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
				if(auth == "Y"){
					str += '<td><button type="button" class="btn btn-info" title="관리" onclick="javascript:goNodeManagePage(\''+nodeObj[i]["@id"]+'\');">관리</button></td>';
					str += '<td><button type="button" class="btn btn-danger" title="삭제" onclick="javascript:deleteNode(\''+nodeObj[i]["@id"]+'\');">삭제</button></td>';
				}
				str += "</tr>";
			}
			
		} 

	
	
	return str;
}

/** 이벤트 정보를 table 테그 Str로 만들어준다. 
 * @param jsonObj
 */
function getTabletagToAvailJsonObj(nodeId, ipAddress){
	
	var str = "";
	var jsonObj = null;
	var AvailJsonObj = null;
	
	$.ajax({
		type : 'get',
		url : '/' + version + '/nodes/'+nodeId+'/ipinterfaces/'+ipAddress+'/services',
		dataType : 'json',
		contentType : 'application/json',
		async : false,
		error : function(data) {
			alert("[" + ipAddress + '] 서비스 정보 검색 실패');
		},
		success : function(data) {

			jsonObj = data;
			console.log(jsonObj);
		}
	});
		
	if(jsonObj == null){return false;}
	
	if (jsonObj["@count"] > 0) {
		
		var serviceObj = jsonObj["service"];
		var data = "";
		
		if (jsonObj["@count"] > 1) {

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
		console.log(serviceObj);
		str += '<table class="table table-striped">';

		if (jsonObj["@count"] > 1) {

			for ( var i in serviceObj) {
				var serviceAvail = "";
				
				for(var j in AvailJsonObj["serviceAvailability"]){
					if(AvailJsonObj["serviceAvailability"][j]["serviceId"] == serviceObj[i]["serviceType"]["@id"]){
						serviceAvail = AvailJsonObj["serviceAvailability"][j]["avail"];
						break;
					}
				}
				
				str += '<tr>';
				str += '	<td><a href="/'+version+'/search/service/serviceDesc?nodeId='+nodeId+'&intf='+ipAddress+'&serviceNm='+serviceObj[i]["serviceType"]["name"]+'">';
				str += serviceObj[i]["serviceType"]["name"];
				str += '	</a></td>';
				str += '	<td>';
				str += availToStringFromStatoCode(serviceObj[i]["@status"],Number(serviceAvail).toFixed(3));
				str += '	</td>';
				str += '</tr>';
			}

		} else {

			str += '<tr>';
			str += '	<td><a href="/'+version+'/search/service/serviceDesc?nodeId='+nodeId+'&intf='+ipAddress+'&serviceNm='+serviceObj["serviceType"]["name"]+'">';
			str += serviceObj["serviceType"]["name"];
			str += '	</a></td>';
			str += '	<td>';
			str += availToStringFromStatoCode(serviceObj["@status"],Number(serviceAvail).toFixed(3));
			str += '	</td>';
			str += '</tr>';

		}

		str += '</table>';
		
	}

	return str;
}

function getInterfaceInfoBox(jsonObj){
	var nodeObj = getSpecificNode(null, jsonObj["nodeId"]);

	var str = 	"<table class='table table-striped'>"+
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
	
	return str;
}
