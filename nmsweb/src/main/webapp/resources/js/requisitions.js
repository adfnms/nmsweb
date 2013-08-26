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
		url : '/' + version + '/requisitions',
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

function getTotalNodesList(callback, foreignId){
	$.ajax({
		type : 'get',
		url : '/' + version + '/requisitions/' + foreignId + '/nodes',
		dataType : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert('노드 리스트 가져오기 서비스 실패');
		},
		success : function(data) {
			if (typeof callback == "function") {
				callback(data, foreignId);
			}
		}
	});
}

function getTotalRequisitionsListForNodes(callback){
	getTotalRequisitionsList(callback);
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
		url : '/' + version + '/requisitions/deployed/' + nodeNm,
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

//메뉴의 운영관리 -> 노드 관리 -> +노드 추가 버튼 클릭시 새로 생성된 하단부 창의 노드 삭제 버튼
function delNodeRequisition(nodeNm, nodeId){
	synRequisition(nodeNm);
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

function returnEditRequisitionPopList(){
	var requisitionObj = $("#requisitionsListTable input[name=requisitionObj]").val();
	$.each(requisitionObj, function(i){
	var foreignId = $("#requisitionsListTable input[name=foreignSources]").eq(i).val();
	return foreignId;
	});
} 

function showEditRequisitionPopList(){
	var foreignId = returnEditRequisitionPopList();
	alert(foreignId);
	getTotalNodesList(editRequisitionPopList, foreignId);
}

function editRequisitionPopList(jsonObj, foreignId){
	var str = getTableToEditRequisitionPop(jsonObj, foreignId);
	$('#requisitionListTable').empty();
	$('#requisitionListTable').append(str);
}

function showEditRequisitionInfoDivTitle(foreignId){
	$('#editRequisitionPopTitle').empty();
	$('#editRequisitionPopTitle').append("Requisitioned Nodes: " + foreignId);
	//showEditRequisitionPopList(foreignId);
}

function showCancelRequisitionPopList(){
	$('#editRequisitionItem').remove();
} 

function showSaveRequisitionPopList(foreignSource){
	var nodeLabel = $("#editRequisitionItem input[name=editRequisitionNodeLabel]").val();
	var foreignId = $("#editRequisitionItem input[name=editRequisitionForeignId]").val();
	var building = $("#editRequisitionItem input[name=editRequisitionBuilding]").val();
	showSaveRequisitionPopListAjax(nodeLabel, foreignId, building, foreignSource);
}

function showSaveRequisitionPopListAjax(nodeLabel, foreignId, building, foreignSource){
	var str = "{\"model-import\":[{\"node-label\":\"" + nodeLabel + "\"" +
			", \"foreign-id\":\"" + foreignId + "\"" +
			", \"building\":\"" + building + "\"}]}";
	$.ajax({
		type : 'post',
		url : '/' + version + '/requisitions/' + foreignSource + '/nodes',
		contentType : 'application/json',
		data : str,
		error : function() {
			alert('새로운 노드추가 실패');
		},
		success : function(data) {
			//getTotalRequisitionsList(getTotalRequisitions);
		}
	}); 
}


/********************************************************* view String edit ********************************************************************/
/*function foreignSourceList(jsonObj){
	var requisitionObj = jsonObj["model-import"];
	$.each(requisitionObj, function(i){
		var foreignSourceList = $("#requisitionsListTable input[name='foreignSourceList']").eq(i).val();
		//var foreignSourceList = $("#requisitionsListTable input[name='foreignSourceList']").last(i).val();
	return foreignSourceList;
	});
}*/

//메뉴의 운영관리 -> 노드 관리 -> + 노드 추가 클릭 시 새로 생성된 하단부 리스트의 편집 버튼 클릭 시 새로 생성된 팝업창 안의 리스트
function getTableToEditRequisitionPop(jsonObj, foreignId){
	var str = "";
	str += '<tr id="editRequisitionItem">';
	str += '<td>Node</td>';
	str += '<td><input name="editRequisitionNodeLabel" type="text" placeholder="New Node" style="margin-left: -15px;width: 338px;"></td>';
	str += '<td>ForeignId</td>';
	str += '<td><input name="editRequisitionForeignId" type="text" placeholder="Foreign ID" style="margin-left: -15px;"></td>';
	str += '<td>Site</td>';
	str += '<td><input name="editRequisitionBuilding" type="text" placeholder="Site" style="margin-left: -15px;"></td>';
	str += '<td><a type="button" class="btn btn-primary" onclick="showSaveRequisitionPopList(\'' + foreignId + '\')">저장</a></td>';
	str += '<td><a type="button" class="btn btn-danger" onclick="showCancelRequisitionPopList()">취소</a></td>';
	//str += '<td><input type="hidden" value="' + foreignId + '" name="foreignSource"/></td>';
	str += '</tr>';
	return str;
}

//메뉴의 운영관리 -> 노드 관리 -> + 노드 추가 클릭 사 새로 생성된 하단부 리스트
function getTableToRequisitionsJsonObj(jsonObj) {
	var requisitionObj = jsonObj["model-import"];
	var str = "";
	if(jsonObj["@count"] == 1){
		var nullStrLastImport =nullCheckJsonObject(jsonObj["model-import"], ["@last-import"]);
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
		str += "	</td>";
		str += "</tr>";
		if(nodeStr > 0){
			str += "<tr>";
			str += '	<td><button type="button" class="btn btn-primary" style="" title="" onclick="javascript:delRequisition(\'' + requisitionObj["@foreign-source"] + '\');">노드 삭제</button></td>';
			str += '	<td><button type="button" class="btn btn-primary" style="margin-left:-823px" title="" onclick="javascript:synRequisition(\'' + requisitionObj["@foreign-source"] +'\');">동기화</button></td>';
			str += '	<td><button type="button" class="btn btn-info" style="margin-left:-750px" data-toggle="modal" href="#editRequisitionPop" onclick="javascript:showEditRequisitionInfoDivTitle(\'' + requisitionObj["@foreign-source"] +'\')">편집</button>';
			//str += '	<td><input type="hidden" value="' + requisitionObj["@foreign-source"] + '" name="foreignSource"/>';
			str += "</tr>";
		}else{
			str += "<tr>";
			str += '	<td><button type="button" class="btn btn-primary" style="" title="" onclick="javascript:delRequisition(\'' + requisitionObj["@foreign-source"] + '\');">요구 삭제</button></td>';
			str += '	<td><button type="button" class="btn btn-primary" style="margin-left:-823px" title="" onclick="javascript:synRequisition(\'' + requisitionObj["@foreign-source"] +'\');">동기화</button></td>';
			str += '	<td><button type="button" class="btn btn-info" style="margin-left:-750px" data-toggle="modal" href="#editRequisitionPop" onclick="javascript:showEditRequisitionInfoDivTitle(\'' + requisitionObj["@foreign-source"] +'\')">편집</button>';
			//str += '	<td><input type="hidden" value="' + requisitionObj["@foreign-source"] + '" name="foreignSource"/>';
			str += "</tr>";
		}
		str += "<tr>";
		str += "    <td><b class='text-error'>" + nodeStr + "</b> nodes defined</td>";
		str += "</tr>";
		str += "<tr>";
		str += "	<td>마지막 수정일: <b>" + new Date(requisitionObj["@date-stamp"]).format('yy-MM-dd hh:mm:ss') + "</b></td>";
		str += "</tr>";
		if(nullStrLastImport == ""){
			str += "<tr>";
			str += "	<td>최근 동기화: <b>최근 동기화를 하지 않았습니다. </b></td>";
			str += "</tr>";
		}else{
			str += "<tr>";
			str += "	<td>최근 동기화: <b>" + new Date(nullStrLastImport).format('yy-MM-dd hh:mm:ss') + "</b></td>";
			str += "</tr>";
		}
		str += "<tr><td style='width:1000px;'><hr/></td></tr>";
	}else if(jsonObj["@count"] > 1){
		for (var i in requisitionObj) 
		{
			
			var nullStrLastImport =nullCheckJsonObject(jsonObj["model-import"][i], ["@last-import"]);
			var nullStrNode =nullCheckJsonObject(jsonObj["model-import"][i], ["node"]);
			if(nullStrNode.length >1)
			{
				nodeStr = nullStrNode.length;
			}
			else if(nullStrNode == ""){
				nodeStr ="0";
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
				str += '	<td><button type="button" class="btn btn-primary" style="" title="" onclick="javascript:delRequisition(\'' + requisitionObj[i]["@foreign-source"] + '\');">노드 삭제</button></td>';
				str += '	<td><button type="button" class="btn btn-primary" style=";margin-left:-823px" title="" onclick="javascript:synRequisition(\'' + requisitionObj[i]["@foreign-source"] +'\');">동기화</button></td>';
				str += '	<td><button type="button" class="btn btn-info" style="margin-left:-750px" data-toggle="modal" href="#editRequisitionPop" onclick="javascript:showEditRequisitionInfoDivTitle(\'' + requisitionObj[i]["@foreign-source"] +'\')">편집</button>';
				//str += '	<td><input type="hidden" value="' + requisitionObj[i]["@foreign-source"] + '" name="foreignSources"/>';
				str += '</tr>';
			}else{
				str += "<tr>";
				str += '	<td><button type="button" class="btn btn-primary" style="" title="" onclick="javascript:delRequisition(\'' + requisitionObj[i]["@foreign-source"] + '\');">요구 삭제</button></td>';
				str += '	<td><button type="button" class="btn btn-primary" style=";margin-left:-823px" title="" onclick="javascript:synRequisition(\'' + requisitionObj[i]["@foreign-source"] +'\');">동기화</button></td>';
				str += '	<td><button type="button" class="btn btn-info" style="margin-left:-750px" data-toggle="modal" href="#editRequisitionPop" onclick="javascript:showEditRequisitionInfoDivTitle(\'' + requisitionObj[i]["@foreign-source"] +'\')">편집</button>';
				str += '	<td><input type="hidden" value="' + requisitionObj[i]["@foreign-source"] + '" name="foreignSources"/>';
				str += '	<td><input type="hidden" value="' + requisitionObj + '" name="requisitionObj"/>';
				str += '</tr>';
			}
			str += "<tr>";
			str += "    <td><b class='text-error'>"+nodeStr+"</b> nodes defined</td>";
			str += "</tr>";
			str += "<tr>";
			str += "	<td>마지막 수정일: <b>" + new Date(requisitionObj[i]["@date-stamp"]).format('yy-MM-dd hh:mm:ss') + "</b></td>";
			str += "</tr>";
			if(nullStrLastImport == ""){
				str += "<tr>";
				str += "	<td>최근 동기화: <b>최근 동기화를 하지 않았습니다. </b></td>";
				str += "</tr>";
			}else{
				str += "<tr>";
				str += "	<td>최근 동기화: <b>" + new Date(nullStrLastImport).format('yy-MM-dd hh:mm:ss') + "</b></td>";
				str += "</tr>";
			}
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