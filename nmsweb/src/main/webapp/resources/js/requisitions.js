//메뉴의 운영관리 -> 노드 관리 -> +노드 추가 버튼 클릭시 새로 생성되는 팝업창
function addProvisionigRequisition(){
	$("#requisitionsBox").show();
	getTotalRequisitionsList(getTotalRequisitions);
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

function getTotalNodesList(callback, foreignSource, foreignId){
	$.ajax({
		type : 'get',
		url : '/' + version + '/requisitions/' + foreignSource + '/nodes',
		dataType : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert('노드 리스트 가져오기 서비스 실패');
		},
		success : function(data) {
			if (typeof callback == "function") {
				callback(data, foreignSource, foreignId);
			}
		}
	});
}

function getNodeListInfoAjax(callback, foreignSource){
	$.ajax({
		type : 'get',
		url : '/' + version + '/requisitions/' + foreignSource,
		dataType : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert('노드 리스트 가져오기 서비스 실패');
		},
		success : function(data) {
			if (typeof callback == "function") {
				callback(data, foreignSource);
			}
		}
	});
}

function getNodeListInfo(foreignSource){
	getNodeListInfoAjax(NodeListInfo, foreignSource);
}

function getTotalRequisitionsListForNodes(callback){
	getTotalRequisitionsList(callback);
}

//메뉴의 운영관리 -> 노드 관리 -> +노드 추가 버튼 클릭시 새로 생성된 하단부 창의 요구 삭제 버
function delRequisition(foreignSource) {
	//동기화 실행 스크립트
	synRequisition(foreignSource);
	if(!confirm("정말 삭제하시겠습니까?")){
		return;
	}
	$.ajax({
		type : 'delete',
		url : '/' + version + '/requisitions/deployed/' + foreignSource,
		datatype : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert("노드 [" + foreignSource + "] 삭제 실패");
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
function synRequisition(foreignSource){
	$.ajax({
		type : 'put',
		url : '/' + version + '/requisitions/' + foreignSource + '/import',
		data: 'json',
		error : function(data) {
			alert("[" + foreignSource + '] 동기화 실패');
		},
		success : function(data) {
			getTotalRequisitionsList(getTotalRequisitions);
		}
	});
}

function delRequisitionPopList(foreignId){
	var foreignSource = $('#hiddenForm input[name=foreignSource]').val();
	$.ajax({
		type : 'delete',
		url : '/' + version + '/requisitions/' + foreignSource + '/nodes/' + foreignId,
		datatype : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert("요구 삭제 실패");
		},
		success : function(data) {
			getNodeListInfoAjax(NodeListInfo, foreignSource);
		}
	});
}

function showEditRequisitionPopList(){
	var foreignSource = $("#hiddenForm input[name=foreignSource]").val();
	
	/*랜덤하게 값 생성*/
	var foreignId = Math.floor(Math.random() * Math.pow(10,9)) + 1377 * Math.pow(10,9);
	
	$("#hiddenForm input[name=foreignIds]").val(foreignId);
	getTotalNodesList(editRequisitionList, foreignSource, foreignId);
}

function editRequisitionList(jsonObj, foreignSource, foreignId){
	
	var str = getTableToEditRequisitionJsonObj(jsonObj, foreignSource, foreignId);
	
	$('#requisitionListTable').append(str);
}

function showEditRequisitionInfoDivTitle(foreignSource){
	
	getNodeListInfo(foreignSource);
	
	synRequisition(foreignSource);
	
	$('#hiddenForm input[name=foreignSource]').val(foreignSource);
	
	$('#editRequisitionPopTitle').empty();
	
	$('#editRequisitionPopTitle').append("Requisitioned Nodes: " + foreignSource);
}

function showCancelRequisitionPopList(){
	$('#editRequisitionDownItemSecond').remove();
} 

function showSaveRequisitionPopList(foreignSource){
	if($("#editRequisitionDownItemSecond input[name=editRequisitionNodeLabel]").val() == ""){
		if($("#editRequisitionDownItemSecond input[name=editRequisitionForeignId]").val() == "" & $("#editRequisitionDownItemSecond input[name=editRequisitionBuilding]").val() == ""){
			var nodeLabel = "New Node";
				$("#editRequisitionDownItemSecond input[name=editRequisitionNodeLabel]").val(nodeLabel);
			var foreignId = $("#hiddenForm input[name=foreignIds]").val();
				$("#editRequisitionDownItemSecond input[name=editRequisitionForeignId]").val(foreignId);
			var building = foreignSource;
				$("#editRequisitionDownItemSecond input[name=editRequisitionBuilding]").val(building);
			showSaveRequisitionPopListAjax(nodeLabel, foreignId, building, foreignSource);
		}else if($("#editRequisitionDownItemSecond input[name=editRequisitionForeignId]").val() == "" & $("#editRequisitionDownItemSecond input[name=editRequisitionBuilding]").val() != ""){
			var nodeLabel = "New Node";
				$("#editRequisitionDownItemSecond input[name=editRequisitionNodeLabel]").val(nodeLabel);
			var foreignId = $("#hiddenForm input[name=foreignIds]").val();
				$("#editRequisitionDownItemSecond input[name=editRequisitionForeignId]").val(foreignId);
			var building = $("#editRequisitionDownItemSecond input[name=editRequisitionBuilding]").val();
			showSaveRequisitionPopListAjax(nodeLabel, foreignId, building, foreignSource);
		}else if($("#editRequisitionDownItemSecond input[name=editRequisitionForeignId]").val() != "" & $("#editRequisitionDownItemSecond input[name=editRequisitionBuilding]").val() == ""){
			var nodeLabel = "New Node";
				$("#editRequisitionDownItemSecond input[name=editRequisitionNodeLabel]").val(nodeLabel);
			var foreignId = $("#editRequisitionDownItemSecond input[name=editRequisitionForeignId]").val();
			var building = foreignSource;
				$("#editRequisitionDownItemSecond input[name=editRequisitionBuilding]").val(building);
			showSaveRequisitionPopListAjax(nodeLabel, foreignId, building, foreignSource);
		}else if($("#editRequisitionDownItemSecond input[name=editRequisitionForeignId]").val() != "" & $("#editRequisitionDownItemSecond input[name=editRequisitionBuilding]").val() != ""){
			var nodeLabel = "New Node";
				$("#editRequisitionDownItemSecond input[name=editRequisitionNodeLabel]").val(nodeLabel);
			var foreignId = $("#editRequisitionDownItemSecond input[name=editRequisitionForeignId]").val();
				$("#editRequisitionDownItemSecond input[name=editRequisitionForeignId]").val(foreignId);
			var building = $("#editRequisitionDownItemSecond input[name=editRequisitionBuilding]").val();
				$("#editRequisitionDownItemSecond input[name=editRequisitionBuilding]").val(building);
			showSaveRequisitionPopListAjax(nodeLabel, foreignId, building, foreignSource);
		}
	}else if($("#editRequisitionDownItemSecond input[name=editRequisitionNodeLabel]").val() != ""){
		if($("#editRequisitionDownItemSecond input[name=editRequisitionForeignId]").val() == "" & $("#editRequisitionDownItemSecond input[name=editRequisitionBuilding]").val() == ""){
			var nodeLabel = $("#editRequisitionDownItemSecond input[name=editRequisitionNodeLabel]").val();
			var foreignId = $("#hiddenForm input[name=foreignIds]").val();
				$("#editRequisitionDownItemSecond input[name=editRequisitionForeignId]").val(foreignId);
			var building = foreignSource;
				$("#editRequisitionDownItemSecond input[name=editRequisitionBuilding]").val(building);
			showSaveRequisitionPopListAjax(nodeLabel, foreignId, building, foreignSource);
		}else if($("#editRequisitionDownItemSecond input[name=editRequisitionForeignId]").val() == "" & $("#editRequisitionDownItemSecond input[name=editRequisitionBuilding]").val() != ""){
			var nodeLabel = $("#editRequisitionDownItemSecond input[name=editRequisitionNodeLabel]").val();
			var foreignId = $("#hiddenForm input[name=foreignIds]").val();
				$("#editRequisitionDownItemSecond input[name=editRequisitionForeignId]").val(foreignId);
			var building = $("#editRequisitionDownItemSecond input[name=editRequisitionBuilding]").val();
			showSaveRequisitionPopListAjax(nodeLabel, foreignId, building, foreignSource);
		}else if($("#editRequisitionDownItemSecond input[name=editRequisitionForeignId]").val() != "" & $("#editRequisitionDownItemSecond input[name=editRequisitionBuilding]").val() == ""){
			var nodeLabel = $("#editRequisitionDownItemSecond input[name=editRequisitionNodeLabel]").val();
			var foreignId = $("#editRequisitionDownItemSecond input[name=editRequisitionForeignId]").val();
			var building = foreignSource;
				$("#editRequisitionDownItemSecond input[name=editRequisitionBuilding]").val(building);
			showSaveRequisitionPopListAjax(nodeLabel, foreignId, building, foreignSource);
		}else if($("#editRequisitionDownItemSecond input[name=editRequisitionForeignId]").val() != "" & $("#editRequisitionDownItemSecond input[name=editRequisitionBuilding]").val() != ""){
			var nodeLabel = $("#editRequisitionDownItemSecond input[name=editRequisitionNodeLabel]").val();
			var foreignId = $("#editRequisitionDownItemSecond input[name=editRequisitionForeignId]").val();
			var building = $("#editRequisitionDownItemSecond input[name=editRequisitionBuilding]").val();
			showSaveRequisitionPopListAjax(nodeLabel, foreignId, building, foreignSource);
		}
	}
}

function showSaveRequisitionPopListAjax(nodeLabel, foreignId, building, foreignSource){
	var str = "{\"node\":[{\"node-label\": \"" + nodeLabel + "\",\"foreign-id\": \"" + foreignId + "\",\"building\": \"" + building + "\"}]}"; 
	$.ajax({
		type : 'post',
		url : '/' + version + '/requisitions/' + foreignSource + '/nodes',
		contentType : 'application/json',
		data : str,
		error : function() {
			alert('새로운 노드추가 실패');
		},
		success : function(data) {
			getNodeListInfoAjax(NodeListInfo, foreignSource);
		}
	});
}

function NodeListInfo(jsonObj){
	var str = getTableToEditRequisitionPop(jsonObj);
	$("#requisitionListTable").empty();
	$("#requisitionListTable").append(str);
}

function modifyRequisitionPopList(jsonObj, foreignId, nodeLabel, inputForeignId){
	$('#' + foreignId + '').hide();
	var trDisplay = document.getElementById(inputForeignId);
	trDisplay.style.display = '';
}

function returnCancelRequisitionPopList(jsonObj, foreignSource, foreignId, nodeLabel, inputForeignId){
	$('#' + foreignId + '').show();
	$('#' + inputForeignId + '').hide();
}

function updateSaveRequisitionPopList(foreignSource, inputForeignId, foreignIdFirst){
	if($('#' + inputForeignId + '' + '\ input[name=editRequisitionNodeLabelFirst]').val() == ""){
		if($('#' + inputForeignId + '' + '\ input[name=editRequisitionForeignIdFirst]').val() == "" & $('#' + inputForeignId + '' + '\ input[name=editRequisitionBuildingFirst]').val() == ""){
			var nodeLabel = "New Node";
				$('#' + inputForeignId + '' + '\ input[name=editRequisitionNodeLabelFirst]').val(nodeLabel);
			var foreignId = $("#hiddenForm input[name=foreignIds]").val();
				$('#' + inputForeignId + '' + '\ input[name=editRequisitionForeignIdFirst]').val(foreignId);
			var building = foreignSource;
				$('#' + inputForeignId + '' + '\ input[name=editRequisitionBuildingFirst]').val(building);
			delRequisitionPopList(foreignIdFirst);
			showSaveRequisitionPopListAjax(nodeLabel, foreignId, building, foreignSource);
			getNodeListInfoAjax(NodeListInfo, foreignSource);
		}else if($('#' + inputForeignId + '' + '\ input[name=editRequisitionForeignIdFirst]').val() == "" & $('#' + inputForeignId + '' + '\ input[name=editRequisitionBuildingFirst]').val() != ""){
			var nodeLabel = "New Node";
				$('#' + inputForeignId + '' + '\ input[name=editRequisitionNodeLabelFirst]').val(nodeLabel);
			var foreignId = $("#hiddenForm input[name=foreignIds]").val();
				$('#' + inputForeignId + '' + '\ input[name=editRequisitionForeignIdFirst]').val(foreignId);
			var building = $('#' + inputForeignId + '' + '\ input[name=editRequisitionBuildingFirst]').val();
			delRequisitionPopList(foreignIdFirst);
			showSaveRequisitionPopListAjax(nodeLabel, foreignId, building, foreignSource);
			getNodeListInfoAjax(NodeListInfo, foreignSource);
		}else if($('#' + inputForeignId + '' + '\ input[name=editRequisitionForeignIdFirst]').val() != "" & $('#' + inputForeignId + '' + '\ input[name=editRequisitionBuildingFirst]').val() == ""){
			var nodeLabel = "New Node";
				$('#' + inputForeignId + '' + '\ input[name=editRequisitionNodeLabelFirst]').val(nodeLabel);
			var foreignId = $('#' + inputForeignId + '' + '\ input[name=editRequisitionForeignIdFirst]').val();
			var building = foreignSource;
				$('#' + inputForeignId + '' + '\ input[name=editRequisitionBuildingFirst]').val(building);
			delRequisitionPopList(foreignIdFirst);
			showSaveRequisitionPopListAjax(nodeLabel, foreignId, building, foreignSource);
			getNodeListInfoAjax(NodeListInfo, foreignSource);
		}else if($('#' + inputForeignId + '' + '\ input[name=editRequisitionForeignIdFirst]').val() != "" & $('#' + inputForeignId + '' + '\ input[name=editRequisitionBuildingFirst]').val() != ""){
			var nodeLabel = "New Node";
				$('#' + inputForeignId + '' + '\ input[name=editRequisitionNodeLabelFirst]').val(nodeLabel);
			var foreignId = $('#' + inputForeignId + '' + '\ input[name=editRequisitionForeignIdFirst]').val();
				$('#' + inputForeignId + '' + '\ input[name=editRequisitionForeignIdFirst]').val(foreignId);
			var building = $('#' + inputForeignId + '' + '\ input[name=editRequisitionBuildingFirst]').val();
				$('#' + inputForeignId + '' + '\ input[name=editRequisitionBuildingFirst]').val(building);
			delRequisitionPopList(foreignIdFirst);	
			showSaveRequisitionPopListAjax(nodeLabel, foreignId, building, foreignSource);
			getNodeListInfoAjax(NodeListInfo, foreignSource);
		}
	}else if($('#' + inputForeignId + '' + '\ input[name=editRequisitionNodeLabelFirst]').val() != ""){
		if($('#' + inputForeignId + '' + '\ input[name=editRequisitionForeignIdFirst]').val() == "" & $('#' + inputForeignId + '' + '\ input[name=editRequisitionBuildingFirst]').val() == ""){
			var nodeLabel = $('#' + inputForeignId + '' + '\ input[name=editRequisitionNodeLabelFirst]').val();
			var foreignId = $('#' + inputForeignId + '' + '\ input[name=editRequisitionForeignIdFirst]').val();
			var building = foreignSource;
				$('#' + inputForeignId + '' + '\ input[name=editRequisitionBuildingFirst]').val(building);
			delRequisitionPopList(foreignIdFirst);
			showSaveRequisitionPopListAjax(nodeLabel, foreignId, building, foreignSource);
			getNodeListInfoAjax(NodeListInfo, foreignSource);
		}else if($('#' + inputForeignId + '' + '\ input[name=editRequisitionForeignIdFirst]').val() == "" & $('#' + inputForeignId + '' + '\ input[name=editRequisitionBuildingFirst]').val() != ""){
			var nodeLabel = $('#' + inputForeignId + '' + '\ input[name=editRequisitionNodeLabelFirst]').val();
			var foreignId = $("#hiddenForm input[name=foreignIds]").val();
			$('#' + inputForeignId + '' + '\ input[name=editRequisitionForeignIdFirst]').val(foreignId);
			var building = $('#' + inputForeignId + '' + '\ input[name=editRequisitionBuildingFirst]').val();
			delRequisitionPopList(foreignIdFirst);	
			showSaveRequisitionPopListAjax(nodeLabel, foreignId, building, foreignSource);
			getNodeListInfoAjax(NodeListInfo, foreignSource);
		}else if($('#' + inputForeignId + '' + '\ input[name=editRequisitionForeignIdFirst]').val() != "" & $('#' + inputForeignId + '' + '\ input[name=editRequisitionBuildingFirst]').val() == ""){
			var nodeLabel = $('#' + inputForeignId + '' + '\ input[name=editRequisitionNodeLabelFirst]').val();
			var foreignId = $('#' + inputForeignId + '' + '\ input[name=editRequisitionForeignIdFirst]').val();
			var building = foreignSource;
				$('#' + inputForeignId + '' + '\ input[name=editRequisitionBuildingFirst]').val(building);
			delRequisitionPopList(foreignIdFirst);
			showSaveRequisitionPopListAjax(nodeLabel, foreignId, building, foreignSource);
			getNodeListInfoAjax(NodeListInfo, foreignSource);
		}else if($('#' + inputForeignId + '' + '\ input[name=editRequisitionForeignIdFirst]').val() != "" & $('#' + inputForeignId + '' + '\ input[name=editRequisitionBuildingFirst]').val() != ""){
			var nodeLabel = $('#' + inputForeignId + '' + '\ input[name=editRequisitionNodeLabelFirst]').val();
			var foreignId = $('#' + inputForeignId + '' + '\ input[name=editRequisitionForeignIdFirst]').val();
			var building = $('#' + inputForeignId + '' + '\ input[name=editRequisitionBuildingFirst]').val();
			delRequisitionPopList(foreignIdFirst);
			showSaveRequisitionPopListAjax(nodeLabel, foreignId, building, foreignSource);
			getNodeListInfoAjax(NodeListInfo, foreignSource);
		}
	}
}

function addInterface(){
	
}

function addNodeCategory(){
	
}

function addNodeAsset(){
	
}
/********************************************************* view String edit ********************************************************************/
//메뉴의 운영관리 -> 노드 관리 -> + 노드 추가 클릭 시 새로 생성된 하단부 리스트의 편집 버튼 클릭 시 새로 생성된 팝업창 안의 하위 리스트
function getTableToEditRequisitionPop(jsonObj){
	var foreignSource = $("#hiddenForm input[name=foreignSource]").val();
	var nodeObj = jsonObj["node"];
	var str = "";
	if(jsonObj["node"] != undefined){
		if(jsonObj["node"]["@foreign-id"] == undefined){	
			for(var i in nodeObj){
				var inputForeignId = Math.floor(Math.random() * Math.pow(10,13));
				str += '<tr id=\'' + nodeObj[i]["@foreign-id"] + '\'>';
				str += '	<td>Node</td>';
				str += '	<td>\'' + nodeObj[i]["@node-label"] + '\'</td>';
				str += '	<td>ForeignId</td>';
				str += '	<td>\'' + nodeObj[i]["@foreign-id"] + '\'</td>';
				str += '	<td>Site</td>';
				str += '	<td>\'' + nodeObj[i]["@building"] + '\'</td>';
				str += '	<td><a type="button" class="btn btn-primary" onclick="modifyRequisitionPopList(\'' + jsonObj + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + nodeObj[i]["@node-label"] + '\',\'' + inputForeignId + '\')">수정</a></td>';
				str += '	<td><a type="button" class="btn btn-primary" onclick="delRequisitionPopList(\'' + nodeObj[i]["@foreign-id"] + '\')">삭제</a></td>';
				str += '	<td><a type="button" class="btn btn-primary" onclick="addInterface(\'' + nodeObj[i]["@foreign-id"] + '\')">Add Interface</a></td>';
				str += '	<td><a type="button" class="btn btn-primary" onclick="addNodeCategory(\'' + nodeObj[i]["@foreign-id"] + '\')">Add Node Category</a></td>';
				str += '	<td><a type="button" class="btn btn-primary" onclick="addNodeAsset(\'' + nodeObj[i]["@foreign-id"] + '\')">Add Node Asset</a></td>';
				str += '</tr>';
				str += '<tr style="display:none" id=\'' + inputForeignId + '\'>';
				str += '	<td>Node</td>';
				str += '	<td><input name="editRequisitionNodeLabelFirst" type="text" placeholder=\'' + nodeObj[i]["@node-label"] + '\' style="margin-left: -15px;width : 100px;"/></td>';
				str += '	<td>ForeignId</td>';
				str += '	<td><input name="editRequisitionForeignIdFirst" type="text" placeholder=\'' + nodeObj[i]["@foreign-id"] + '\' style="margin-left: -15px;width : 100px"/></td>';
				str += '	<td>Site</td>';
				str += '	<td><input name="editRequisitionBuildingFirst" type="text" placeholder="\'' + nodeObj[i]["@building"] + '\'" style="margin-left: -15px;width : 100px"/></td>';
				str += '	<td><a type="button" class="btn btn-primary" onclick="updateSaveRequisitionPopList(\'' + foreignSource + '\',\'' + inputForeignId + '\',\'' + nodeObj[i]["@foreign-id"] + '\')">저장</a></td>';
				str += '	<td><a type="button" class="btn btn-danger" onclick="returnCancelRequisitionPopList(\'' + jsonObj + '\',\'' + foreignSource + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + nodeObj[i]["@node-label"] + '\',\'' + inputForeignId + '\')">취소</a></td>';
				str += '</tr>';
			}
		}else{
			var inputForeignId = Math.floor(Math.random() * Math.pow(10,13));
			str += '<tr id=\'' + nodeObj["@foreign-id"] + '\'>';
			str += '	<td>Node</td>';
			str += '	<td>\'' + nodeObj["@node-label"] + '\'</td>';
			str += '	<td>ForeignId</td>';
			str += '	<td>\'' + nodeObj["@foreign-id"] + '\'</td>';
			str += '	<td>Site</td>';
			str += '	<td>\'' + nodeObj["@building"] + '\'</td>';
			str += '	<td><a type="button" class="btn btn-primary" onclick="modifyRequisitionPopList(\'' + jsonObj + '\',\'' + nodeObj["@foreign-id"] + '\',\'' + nodeObj["@node-label"] + '\',\'' + inputForeignId + '\')">수정</a></td>';
			str += '	<td><a type="button" class="btn btn-danger" onclick="delRequisitionPopList(\'' + nodeObj["@foreign-id"] + '\')">삭제</a></td>';
			str += '</tr>';
			str += '<tr style="display:none" id=\'' + inputForeignId + '\'>';
			str += '	<td>Node</td>';
			str += '	<td><input name="editRequisitionNodeLabelFirst" type="text" placeholder=\'' + nodeObj["@node-label"] + '\' style="margin-left: -15px;width: 338px;"/></td>';
			str += '	<td>ForeignId</td>';
			str += '	<td><input name="editRequisitionForeignIdFirst" type="text" placeholder=\'' + nodeObj["@foreign-id"] + '\' style="margin-left: -15px;"/></td>';
			str += '	<td>Site</td>';
			str += '	<td><input name="editRequisitionBuildingFirst" type="text" placeholder="\'' + nodeObj["@building"] + '\'" style="margin-left: -15px;"/></td>';
			str += '	<td><a type="button" class="btn btn-primary" onclick="updateSaveRequisitionPopList(\'' + foreignSource + '\',\'' + inputForeignId + '\',\'' + nodeObj["@foreign-id"] + '\')">저장</a></td>';
			str += '	<td><a type="button" class="btn btn-danger" onclick="returnCancelRequisitionPopList(\'' + jsonObj + '\',\'' + foreignSource + '\',\'' + nodeObj["@foreign-id"] + '\',\'' + nodeObj["@node-label"] + '\',\'' + inputForeignId + '\')">취소</a></td>';
			str += '</tr>';
		}
	}
	return str;
}

function getTableToEditRequisitionJsonObj(jsonObj, foreignSource, foreignId){
	var str = "";
	str += '<tr id="editRequisitionDownItemSecond">';
	str += '	<td>Node</td>';
	str += '	<td><input name="editRequisitionNodeLabel" type="text" placeholder="New Node" style="margin-left: -15px;width : 100px"/></td>';
	str += '	<td>ForeignId</td>';
	str += '	<td><input name="editRequisitionForeignId" type="text" placeholder=\'' + foreignId + '\' style="margin-left: -15px;width : 100px"/></td>';
	str += '	<td>Site</td>';
	str += '	<td><input name="editRequisitionBuilding" type="text" placeholder="\'' + foreignSource + '\'" style="margin-left: -15px;width : 100px"/></td>';
	str += '	<td><a type="button" class="btn btn-primary" onclick="showSaveRequisitionPopList(\'' + foreignSource + '\')">저장</a></td>';
	str += '	<td><a type="button" class="btn btn-danger" onclick="showCancelRequisitionPopList()">취소</a></td>';
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
			str += '	<td><button type="button" class="btn btn-primary" style="margin-left:-832px" title="" onclick="javascript:synRequisition(\'' + requisitionObj["@foreign-source"] +'\');">동기화</button></td>';
			str += '	<td><button type="button" class="btn btn-info" style="margin-left:-750px" data-toggle="modal" href="#editRequisitionPop" onclick="javascript:showEditRequisitionInfoDivTitle(\'' + requisitionObj["@foreign-source"] + '\')">편집</button>';
			str += "</tr>";
		}else{
			str += "<tr>";
			str += '	<td><button type="button" class="btn btn-primary" style="" title="" onclick="javascript:delRequisition(\'' + requisitionObj["@foreign-source"] + '\');">노드 삭제</button></td>';
			str += '	<td><button type="button" class="btn btn-primary" style="margin-left:-832px" title="" onclick="javascript:synRequisition(\'' + requisitionObj["@foreign-source"] +'\');">동기화</button></td>';
			str += '	<td><button type="button" class="btn btn-info" style="margin-left:-750px" data-toggle="modal" href="#editRequisitionPop" onclick="javascript:showEditRequisitionInfoDivTitle(\'' + requisitionObj["@foreign-source"] + '\')">편집</button>';
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
		for (var i in requisitionObj){
			var nullStrLastImport =nullCheckJsonObject(jsonObj["model-import"][i], ["@last-import"]);
			var nullStrNode =nullCheckJsonObject(jsonObj["model-import"][i], ["node"]);
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
			str	+= "		<h3>" + requisitionObj[i]["@foreign-source"] + "</h3>";
			str += "	</td>";
			str += "	<td>";
			str += "	</td>";
			str += "</tr>";
			if(nodeStr > 0){
				str += "<tr>";
				str += '	<td><button type="button" class="btn btn-primary" style="" title="" onclick="javascript:delRequisition(\'' + requisitionObj[i]["@foreign-source"] + '\');">노드 삭제</button></td>';
				str += '	<td><button type="button" class="btn btn-primary" style=";margin-left:-832px" title="" onclick="javascript:synRequisition(\'' + requisitionObj[i]["@foreign-source"] +'\');">동기화</button></td>';
				str += '	<td><button type="button" class="btn btn-info" style="margin-left:-750px" data-toggle="modal" href="#editRequisitionPop" onclick="javascript:showEditRequisitionInfoDivTitle(\'' + requisitionObj[i]["@foreign-source"] + '\')">편집</button>';
				str += '</tr>';
			}else{
				str += "<tr>";
				str += '	<td><button type="button" class="btn btn-primary" style="" title="" onclick="javascript:delRequisition(\'' + requisitionObj[i]["@foreign-source"] + '\');">노드 삭제</button></td>';
				str += '	<td><button type="button" class="btn btn-primary" style=";margin-left:-832px" title="" onclick="javascript:synRequisition(\'' + requisitionObj[i]["@foreign-source"] +'\');">동기화</button></td>';
				str += '	<td><button type="button" class="btn btn-info" style="margin-left:-750px" data-toggle="modal" href="#editRequisitionPop" onclick="javascript:showEditRequisitionInfoDivTitle(\'' + requisitionObj[i]["@foreign-source"] + '\')">편집</button>';
				str += '</tr>';
			}
			str += "<tr>";
			str += "    <td><b class='text-error'>" + nodeStr + "</b> nodes defined</td>";
			str += "</tr>";
			str += "<tr>";
			str += "	<td>마지막 수정일: <b>" + new Date(requisitionObj[i]["@date-stamp"]).format('yy-MM-dd hh:mm:ss') + "</b></td>";
			str += "</tr>";
			if(nullStrLastImport == ""){
				str += "<tr>";
				str += "	<td>최근 동기화: <b>최근 동기화를 하지 않았습니다.  </b></td>";
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