/**
 *  add Node Popup
 */
//메뉴의 운영관리 -> 노드 관리 -> +노드 추가 버튼 클릭시 새로 생성되는 팝업창
function addNodePop(){
	
	$("#requisitionsBox").show();
	
	getTotalRequisitionsList(getTotalRequisitions);
	
	/*var settings ="toolbar=no ,width=350 ,height=205 ,directories=no,status=no,scrollbars=no,menubar=no";
	var winObject = window.open("/" + version + "/admin/addNode.pop", "addNodePop", settings);
	winObject.focus();*/	

}

function getTotalRequisitionsList(callback) {

	$.ajax({
		type : 'get',
		url : '/' + version + '/requisitions/',
		dataType : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert('필요조건 리스트 가져오기 서비스 실패');
		},
		success : function(data) {
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
}
//메뉴의 운영관리 -> 노드 관리 -> +노드 추가 버튼 클릭시 새로 생성된 하단부 창의 요구 삭제 버튼
function delRequisition(nodeNm) {
	//동기화 실행 스크립트
	synRequisition(nodeNm);
	if(!confirm("정말 삭제하시겠습니까?")){
		return;
	}
	
	$.ajax({
		type : 'delete',
		url : '/' + version + '/requisitions/' + nodeNm,
		datatype : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert("노드 [" + nodeNm + "] 삭제 실패");
		},
		success : function(data) {
			getTotalRequisitionsList(getTotalRequisitions);
		}
	});
}
//메뉴의 운영관리 -> 노드 관리 -> +노드 추가 버튼 클릭시 새로 생성된 하단부 창의 새로운 요구추가 버튼
function addRequisition() {
	if($("#requisitionsBox input[name=requisitions]").val() == ""){
		alert("추가할 노드명을 입력하십시오.");
		return;
	}
		var text = $("#requisitionsBox input[name=requisitions]").val();
		regRequisitionAjax(text);
	
}

function regRequisitionAjax(text){
	var str = "{\"model-import\":[{\"foreign-source\":\"" + text + "\"}]}";
	$.ajax({
		type : 'post',
		url : '/' + version + '/requisitions',
		contentType : 'application/json',
		data : str,
		error : function() {
			alert('새로운 요구추가 실패');
		},
		success : function(data) {
			getTotalRequisitionsList(getTotalRequisitions);
		}
	}); 
}

//메뉴의 운영관리 -> 노드 관리 -> +노드 추가 버튼 클릭시 새로 생성된 하단부 창의 기본외부소스 초기화 버튼
function resetRequisition() {
	$.ajax({
		type : 'get',
		url : '/' + version + '/foreignSources/default',
		data: 'json',
		error : function(data) {
			alert('기본외부소스 초기화 실패');
		},
		success : function(data) {
			getTotalRequisitionsList(getTotalRequisitions);
		}
	});
}

//메뉴의 운영관리 -> 노드 관리 -> +노드 추가 버튼 클릭시 새로 생성된 하단부 창의 동기화 버튼
function synRequisition(nodeNm){
	$.ajax({
		type : 'put',
		url : '/' + version + '/requisitions/' + nodeNm + '/import',
		data: 'json',
		error : function(data) {
			alert("[" + nodeNm + '] 동기화 실패');
		},
		success : function(data) {
			getTotalRequisitionsList(getTotalRequisitions);
		}
	});
}

function editRequisition(nodeNm){
	//동기화 실행 스크립트
	synRequisition(nodeNm);
	$("#editRequisitions").append("<tr><td>dddddddd</tr></td>");
}

//메뉴의 운영관리 -> 노드 관리 -> +노드 추가 버튼 클릭시 새로 생성된 하단부 창의 노드 삭제 버튼
function delNodeRequisition(nodeNm, nodeId){
	synRequisition(nodeNm);
	
	console.log(nodeId);
	if(!confirm("정말 삭제하시겠습니까?")){
		return;
	}
	
	$.ajax({
		type : 'delete',
		url : '/' + version + '/requisitions/' + nodeNm + '/nodes/' + nodeId,
		datatype : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert("노드 [" + nodeNm + "] 삭제 실패");
		},
		success : function(data) {
			getTotalRequisitionsList(getTotalRequisitions);
		}
	});
}
/************************** view String edit *****************************/

//메뉴의 운영관리 -> 노드 관리 -> + 노드 추가 클릭 사 새로 생성된 하단부 리스트
function getTableToRequisitionsJsonObj(jsonObj) {
	console.log("---------------getTableToRequisitionsJsonObj--------------");
	console.log(jsonObj);
	
	var requisitionObj = jsonObj["model-import"];
	var str = "";
	
	if(jsonObj["@count"] == 1){
	
		var nullStrLastImport =nullCheckJsonObject(jsonObj["model-import"], ["@last-import"]);
		
		if(nullStrLastImport == ""){
			
			nullStrLastImport ="최근 동기화를 하지 않았습니다. ";
		}
		
		var nullStrNode =nullCheckJsonObject(jsonObj["model-import"], ["node"]);
			
			if(nullStrNode.length >1){
			nodeStr = nullStrNode.length;
			}
			else if(nullStrNode == ""){
				nodeStr ="0";
				
			}else{
				nodeStr ="1";
			}
		
		
		str += "<tr>";
		str += "	<td class='text-info'>";
		str	+= "		<h3>" + requisitionObj["@foreign-source"] + "</h3>";
		str += "	</td>";
		str += "	<td>";
		str += "	</td>";
		str += "</tr>";
		str += "<tr>";
		str += '	<td id= "deleteButton" ><button type="button" class="btn btn-primary" style="" title="" onclick="javascript:delRequisition(\'' + requisitionObj["@foreign-source"] + '\');">노드 삭제</button></td>';
		//str += '	<td><button type="button" class="btn btn-primary" style=";margin-left:-100px" title="" onclick="javascript:synRequisition(\'' + requisitionObj["@foreign-source"] +'\');">동기화</button></td>';
		str += '	<td><button type="button" class="btn btn-info" style=";margin-left:-840px" title="" onclick="javascript:editRequisition(\'' + requisitionObj["@foreign-source"] +'\');">편집</button></td>';
		str += "</tr>";
		str += "<tr>";
		str += "    <td><b class='text-error'>" + nodeStr + "</b> nodes defined</td>";
		str += "</tr>";
		str += "<tr>";
		str += "	<td>마지막 수정일: <b>" + requisitionObj["@date-stamp"] + "</b></td>";
		str += "</tr>";
		str += "<tr>";
		
		str += "	<td>최근 동기화: <b>" + nullStrLastImport + "</b></td>";
		str += "</tr>";
		
	}else if(jsonObj["@count"] > 1){
		
		
		
		for (var i in requisitionObj) {
			var nullStrLastImport =nullCheckJsonObject(jsonObj["model-import"][i], ["@last-import"]);
			if(nullStrLastImport == ""){
				
				nullStrLastImport ="최근 동기화를 하지 않았습니다. ";
			}
			
			var nullStrNode =nullCheckJsonObject(jsonObj["model-import"][i], ["node"]);
				
				if(nullStrNode.length >1){
				nodeStr = nullStrNode.length;
				}
				else if(nullStrNode == ""){
					nodeStr ="0";
					/*$("#deleteButton").append('<td><button type="button" class="btn btn-primary">요구삭제</button><td>');*/
				}else{
					nodeStr ="1";
				}
		
				
		str += "<tr>";
		str += "	<td class='text-info'>";
		str	+= "		<h3>" + requisitionObj[i]["@foreign-source"] + "</h3>";
		str += "	</td>";
		str += "	<td>";
		str += "	</td>";
		str += "</tr>";
		if(nodeStr > 0){
			str += "<tr>";
			str += "<div id= 'deleteButton'>";
			str += '	<td><button type="button" class="btn btn-primary" style="" title="" onclick="javascript:delNodeRequisition(\'' + requisitionObj[i]["@foreign-source"] + '\', \'' + requisitionObj[i]["node"]["@foreign-id"] +'\');">노드 삭제</button></td>';
			str += '	<td><button type="button" class="btn btn-info" style=";margin-left:-840px" title="" onclick="javascript:editRequisition(\'' + requisitionObj[i]["@foreign-source"] + '\');">편집</button></td>';
			//str += '	<td><button type="button" class="btn btn-primary" style=";margin-left:-840px" title="" onclick="javascript:synRequisition(\'' + requisitionObj[i]["@foreign-source"] +'\');">동기화</button></td>';
			str += "</div>";
			str += "</tr>";
		}else{
			str += "<tr>";
			str += "<div id= 'deleteButton'>";
			str += '	<td><button type="button" class="btn btn-primary" style="" title="" onclick="javascript:delRequisition(\'' + requisitionObj[i]["@foreign-source"] + '\');">요구 삭제</button></td>';
			str += '	<td><button type="button" class="btn btn-info" style=";margin-left:-840px" title="" onclick="javascript:editRequisition(\'' + requisitionObj[i]["@foreign-source"] + '\');">편집</button></td>';
			//str += '	<td><button type="button" class="btn btn-primary" style=";margin-left:-840px" title="" onclick="javascript:synRequisition(\'' + requisitionObj[i]["@foreign-source"] +'\');">동기화</button></td>';
			str += "</div>";
			str += "</tr>";
		}
		str += '<div id="editRequisitions"></div>';
		str += "<tr>";
		str += "    <td><b class='text-error'>"+nodeStr+"</b> nodes defined</td>";
		str += "</tr>";
		str += "<tr>";
		str += "	<td>마지막 수정일: <b>" + new Date(requisitionObj[i]["@date-stamp"]).format('yy-MM-dd hh:mm:ss') + "</b></td>";
		str += "</tr>";
		str += "<tr>";
		str += "	<td>최근 동기화: <b>" + new Date(nullStrLastImport).format('yy-MM-dd hh:mm:ss') + "</b></td>";
		str += "</tr>";
		str += "<tr><td style='width:1000px;'><hr/></td></tr>";
		}
	
	}else{

		str += "<tr>";
		str += "	<td class='text-info'>";
		str	+= "		<h3>Requisition을 등록해주세요.</h3>";
		str += "	</td>";
		str += "	<td>";
		str += "	</td>";
		str += "</tr>";
		
	}
	
	
	
	
/*	for (var i in requisitionObj) {
	
		if(requisitionObj[i]["node"] == undefined) {
			str += "<tr>";
			str += "	<td class='text-info'>";
			str	+= "		<h3>" + requisitionObj[i]["@foreign-source"] + "</h3>";
			str += "	</td>";
			str += "	<td>";
			str += "	</td>";
			str += "</tr>";
			str += "<tr>";
			str += '	<td><button type="button" class="btn btn-primary" style="" title="" onclick="javascript:delRequisition();">요구 삭제</button></td>';
			str += '	<td><button type="button" class="btn btn-primary" style=";margin-left:-800px" title="" onclick="javascript:synRequisition();">동기화</button></td>';
			str += "</tr>";
			str += "<tr>";
			str += "    <td>요구(Requisition) : <b ><a class='text-success'>Edit</a></b></td>";
			str += "</tr>";
			str += "<tr>";
			str += "    <td>외부 소스 정의(Foreign Source Definition:) : <b ><a class='text-success'>Edit</a></b></td>";
			str += "</tr>";
			str += "<tr>";
			str += "    <td><b class='text-error'>0</b> nodes defined</td>";
			str += "</tr>";
			str += "<tr>";
			str += "	<td>Last Modified: <b>" + requisitionObj[i]["@date-stamp"] + "</b></td>";
			str += "</tr>";
			str += "<tr>";
			str += "	<td>Last Synchronization Requested: <b>Never</b><hr/></td>";
			str += "</tr>";	
			str += "<tr>";
			str += "	<td>Last Synchronization Requested: <b>" + requisitionObj[i]["@last-import"] +"dd" +"</b><hr/></td>";
			str += "</tr>";	
		}else{
			if(requisitionObj[i] > 1){
				str += "<tr>";
				str += "	<td class='text-info'>";
				str	+= "		<h3>" + requisitionObj[i]["@foreign-source"] + "</h3>";
				str += "	</td>";
				str += "	<td>";
				str += "	</td>";
				str += "</tr>";
				str += "<tr>";
				str += '	<td><button type="button" class="btn btn-primary" style="" title="" onclick="javascript:delRequisition();">노드 삭제</button></td>';
				str += '	<td><button type="button" class="btn btn-primary" style=";margin-left:-800px" title="" onclick="javascript:synRequisition();">동기화</button></td>';
				str += "</tr>";
				str += "<tr>";
				str += "    <td><b class='text-error'>1</b> nodes defined</td>";
				str += "</tr>";
				str += "<tr>";
				str += "	<td>Last Modified: " + requisitionObj[i]["@date-stamp"] + "</td>";
				str += "</tr>";
				str += "<tr>";
				str += "	<td>Last Synchronization Requested: " + requisitionObj[i]["@last-import"] + "</td>";
				str += "</tr>";
			}else{
				str += "<tr>";
				str += "	<td class='text-info'>";
				str	+= "		<h3>" + requisitionObj[i]["@foreign-source"] + "</h3>";
				str += "	</td>";
				str += "	<td>";
				str += "	</td>";
				str += "</tr>";
				str += "<tr>";
				str += '	<td><button type="button" class="btn btn-primary" style="" title="" onclick="javascript:delRequisition();">노드 삭제</button></td>';
				str += '	<td><button type="button" class="btn btn-primary" style=";margin-left:-800px" title="" onclick="javascript:synRequisition();">동기화</button></td>';
				str += "</tr>";
				str += "<tr>";
				str += "    <td><b class='text-error'>"+requisitionObj[i]["node"].length + "</b> nodes defined</td>";
				str += "</tr>";
				str += "<tr>";
				str += "	<td>Last Modified: <b>" + requisitionObj[i]["@date-stamp"] + "</b></td>";
				str += "</tr>";
				str += "<tr>";
				str += "	<td>Last Synchronization Requested: <b>" + requisitionObj[i]["@last-import"] + "</b></td>";
				str += "</tr>";
			}
		}
	}*/
	return str;
}


/** 이벤트 정보를 table 테그 Str로 만들어준다. 
 * @param jsonObj
 */
function getOptionTagToRequisitionJsonObj(jsonObj){
	
	var requisitionObj = jsonObj["model-import"];
	
	var str = "";
	if(jsonObj["@count"] > 0){
		if(jsonObj["@count"] > 1){
		
			for ( var i in requisitionObj) {

				str += "<option value="+requisitionObj[i]["@foreign-source"]+">"+requisitionObj[i]["@foreign-source"]+"</option>";
				
			}
			
		}else{
			str += "<option value="+requisitionObj["@foreign-source"]+">"+requisitionObj["@foreign-source"]+"</option>";
		}
		
	}
	return str;
}
