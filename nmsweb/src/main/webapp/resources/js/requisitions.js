//메뉴의 운영관리 -> 노드 관리 -> +노드 추가 버튼 클릭시 새로 생성되는 팝업창
function addProvisioningRequisition(){
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

//메뉴의 운영관리 -> 노드 관리 -> +노드 추가 버튼 클릭시 새로 생성된 하단부 창의 요구 삭제 버튼
function delRequisition(foreignSource) {
	//동기화 실행 스크립트
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
			synRequisition(foreignSource);
			getTotalRequisitionsList(getTotalRequisitions);
		}
	});
}
//메뉴의 운영관리 -> 노드 관리 -> +노드 추가 버튼 클릭시 새로 생성된 하단부 창의 새로운 요구추가 버튼
function addRequisition() {
	if($("#requisitionsBox input[name=requisitions]").val() == ""){
		alert("추가할 노드 그룹명을 입력하십시오.");
		return;
	}
	var text = $("#requisitionsBox input[name=requisitions]").val();
	if(regRequisitionCheck(text))
	{
		regRequisitionAjax(text);
		addRequisitionCheck(text);
		getTotalRequisitionsList(getTotalRequisitions);
	}
	else
	{
		alert("이미 존체하는 노드 그룹명 입니다.");
		return;
	}
}

//메뉴의 운영관리 -> 노드 관리 -> +노드 구릅 설정 > 새로운 노드 그룹 버튼 클릭 시 노드  이름 체크
function regRequisitionCheck(text)
{
	var isText = true;
	$.ajax({
		type : 'get',
		url : '/' + version + '/requisitions',
		contentType : 'application/json',
		async: false,
		error : function() {
			alert('새로운 요구추가 실패');
		},
		success : function(data) {
			var jsonObj = JSON.parse(data);
			var modelImport ='';
			
			if(jsonObj["@count"] != '0')
			{
				modelImport = jsonObj["model-import"];
				
				if (modelImport instanceof Array)
					for(var i in modelImport)
					{
						isText = !equers(modelImport[i]["@foreign-source"],text);
						
						if(isText == false)
						{
							break;
						}
					}
				else
				{
					isText = !equers(modelImport["@foreign-source"],text);
				}
			}				
		}
	});
	
	return isText;
}

//메뉴의 운영관리 -> 노드 관리 -> +노드 구릅 설정 > 새로운 노드 그룹 버튼 클릭 시 노드  새로운 노드 그룹 채크
function addRequisitionCheck(text)
{
	$.ajax({
		type : 'get',
		url : '/' + version + '/requisitions',
		dataType : 'json',
		contentType : 'application/json',
		async: false,
		error : function(data) {
			alert('필요조건 리스트 가져오기 서비스 실패');
		},
		success : function(data) {
			var isText = false;
			var modelImport = data["model-import"];
			
			if (modelImport instanceof Array)
				for(var i in modelImport)
				{
					isText = equers(modelImport[i]["@foreign-source"],text);
					
					if(isText == true)
						break;
				}
			else
			{
				isText = equers(modelImport["@foreign-source"],text);
			}
			
			if(isText == true)
				alert('등록 성공');
			else
				alert('등록 실패');
		}
	});
}

//문자열 비교
function equers(str1,str2)
{
	return str1 == str2;
}

function regRequisitionAjax(text){
	var str = "{\"model-import\":[{\"foreign-source\":\"" + text + "\"}]}";
	$.ajax({
		type : 'post',
		url : '/' + version + '/requisitions',
		contentType : 'application/json',
		data : str,
		async: false,
		error: function (error) {
//            alert('error; ' + eval(error));
            console.log(error);
//			alert('새로운 요구추가 실패');
		},
		success : function(data) {
			getTotalRequisitionsList(getTotalRequisitions);
		}
	}); 
}

//메뉴의 운영관리 -> 노드 관리 -> +노드 추가 버튼 클릭시 새로 생성된 하단부 창의 기본외부소스 초기화 버튼
function resetRequisitionAjax(callback) {
	$.ajax({
		type : 'get',
		url : '/' + version + '/foreignSources/default',
		data: 'json',
		error : function(data) {
			alert('기본외부소스 초기화 실패');
		},
		success : function(data) {
			getTotalRequisitionsList(getTotalRequisitions);
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
}

function resetRequisition(){
	resetRequisitionAjax(editDefaultDetectors);
}

//메뉴의 운영관리 -> 노드 관리 -> +노드 추가 버튼 클릭시 새로 생성된 하단부 창의 동기화 버튼
function synRequisition(foreignSource){
	$.ajax({
		type : 'put',
		url : '/' + version + '/requisitions/' + foreignSource + '/import',
		data: 'json',
		async: false,
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
	nodeListAjax(foreignSource);
	synRequisition(foreignSource);
	$('#hiddenForm input[name=foreignSource]').val(foreignSource);
	$('#editRequisitionPopTitle').empty();
	$('#editRequisitionPopTitle').append(foreignSource);
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
//================================================================================================================================================================================
function nodeListInfo(jsonObj){
	var str = getTableToEditRequisitionPop(jsonObj);
	$("#requisitionListTable").empty();
	$("#requisitionListTable").append(str);
}

function nodeListAjax(foreignSource){
	$.ajax({
		type : 'get',
		url : '/' + version + '/requisitions/' + foreignSource + '/nodes',
		dataType : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert('노드 리스트 가져오기 서비스 실패');
		},
		success : function(data) {
			nodeListInfo(data);
		}
	});
}
//================================================================================================================================================================================
function modifyRequisitionPopList(nodeObj, trId, nodeLabel, inputForeignId){
	if(!confirm("수정하면 기존의 노드 정보가 지워질 수 있습니다. 계속하시겠습니까?")){
		return;
	}
	$('#' + trId + '').hide();
	var trDisplay = document.getElementById(inputForeignId);
	trDisplay.style.display = '';
}

function returnCancelRequisitionPopList(jsonObj, foreignSource, trId, nodeLabel, inputForeignId){
	$('#' + trId + '').show();
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
			var foreignId = $("#hiddenForm input[name=foreignIds]").val();
			$('#' + inputForeignId + '' + '\ input[name=editRequisitionForeignIdFirst]').val(foreignId);
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

function addInterface(trId, foreignSource, foreignId){
	var str = getTableToAddInterface(trId, foreignSource, foreignId);
	$('#' + trId + '').append(str);
}

function saveIpInterfaces(foreignSource, foreignId, trId, ipAddr, descr, snmpPrimary){
	var ipInterface = $('#' + trId + '' + '\ input[name=ipInterface]').val();
	var description = $('#' + trId + '' + '\ input[name=description]').val();
	var snmpPrimary = $('#' + trId + '' + '\ select').val();
	var foreignSource = $('#hiddenForm input[name=foreignSource]').val();
	var status = '1';
	if($('#' + trId + '' + '\ input[name=ipInterface]').val() == ""){
		alert("IP Interface를 입력하십시오.");
		return;
	}
	
	if($('#' + trId + '' + '\ input[name=description]').val() == ""){
		alert("Description을 입력하십시오.");
		return;
	}
	
	if($('#' + trId + '' + '\ input[name=ipInterface]').val() == ipAddr){
		alert("다른 IP Interface를 입력하십시오.");
		return;
	}
	
	if($('#' + trId + '' + '\ input[name=description]').val() == descr){
		alert("다른 Description을 입력하십시오.");
		return;
	}
	
	var str = "{\"interface\":[{\"snmp-primary\": \"" + snmpPrimary + "\",\"status\": \"" + status + "\",\"ip-addr\": \"" + ipInterface + "\",\"descr\":\"" + description + "\"}]}"; 
	$.ajax({
		type : 'post',
		url : '/' + version + '/requisitions/' + foreignSource + '/nodes/' + foreignId + '/interfaces',
		contentType : 'application/json',
		data : str,
		error : function() {
			alert('새로운 인터페이스 ID 추가 실패');
		},
		success : function(data) {
			$.ajax({
				type : 'delete',
				url : '/' + version + '/requisitions/' + foreignSource + '/nodes/' + foreignId + '/interfaces/' + ipAddr,
				datatype : 'json',
				contentType : 'application/json',
				error : function(data) {
					alert('기본외부소스 초기화 실패');
				},
				success : function(data) {
					getNodeListInfoAjax(NodeListInfo, foreignSource);
				}
			});
		}
	});
}

function getToAddInterface(callback, foreignSource, foreignId){
	$.ajax({
		type : 'get',
		url : '/' + version + '/requisitions/' + foreignSource + '/nodes/' + foreignId + '/interfaces',
		dataType : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert('인터페이스 추가 실패');
		},
		success : function(data) {
			if (typeof callback == "function") {
				callback(data, foreignId);
			}
		}
	});
}

function finishToAddInterface(jsonObj, foreignId){
	var str = getTableToFinishInterface(jsonObj);
	$('#' + foreignId + '').append(str);
}

function cancelIpInterface(tripInterface){
	$('#' + tripInterface + '').remove();
}

function modifyInterfaces(interfaces, interfacesFirst){
	if(!confirm("수정하면 기존의 인터페이스 정보가 지워질 수 있습니다. 계속하시겠습니까?")){
		return;
	}
	$('#' + interfacesFirst + '').hide();
	$('#' + interfaces + '').show();
}

function cancelInterfaces(interfaces, interfacesFirst){
	$('#' + interfacesFirst + '').show();
	$('#' + interfaces + '').hide();
}

function modifyInterface(interface, interfaceFirst){
	if(!confirm("수정하면 기존의 인터페이스 정보가 지워질 수 있습니다. 계속하시겠습니까?")){
		return;
	}
	$('#' + interfaceFirst + '').hide();
	$('#' + interface + '').show();
}

function cancelInterface(interface, interfaceFirst){
	$('#' + interfaceFirst + '').show();
	$('#' + interface + '').hide();
}

function modifyCategories(categories, categoriesFirst){
	$('#' + categoriesFirst + '').hide();
	$('#' + categories + '').show();
}

function cancelCategories(categories, categoriesFirst){
	$('#' + categoriesFirst + '').show();
	$('#' + categories + '').hide();
}

function modifyCategory(category, categoryFirst){
	$('#' + categoryFirst + '').hide();
	$('#' + category + '').show();
}

function cancelCategory(category, categoryFirst){
	$('#' + categoryFirst + '').show();
	$('#' + category + '').hide();
}

function modifyAssets(assets, assetsFirst){
	$('#' + assetsFirst + '').hide();
	$('#' + assets + '').show();
}

function cancelAssets(assets, assetsFirst){
	$('#' + assetsFirst + '').show();
	$('#' + assets + '').hide();
}

function modifyAsset(asset, assetFirst){
	$('#' + assetFirst + '').hide();
	$('#' + asset + '').show();
}

function cancelAsset(asset, assetFirst){
	$('#' + assetFirst + '').show();
	$('#' + asset + '').hide();
}

function addService(interfacesFirst, foreignId, ipAddr){
	getServiceTotalList(addServices, interfacesFirst, foreignId, ipAddr);
}

function addServices(jsonObj, interfacesFirst, foreignId, ipAddr){
	var str = getAddServices(jsonObj, interfacesFirst, foreignId, ipAddr);
	$('#' + interfacesFirst + '').append(str);
}

function cancelGetAddService(getAddService){
	$('#' + getAddService + '').remove();
}

function modifyServices(services, servicesFirst){
	$('#' + servicesFirst + '').hide();
	$('#' + services + '').show();
}

function cancelServices(services, servicesFirst){
	$('#' + servicesFirst + '').show();
	$('#' + services + '').hide();
}

function modifyService(service, serviceFirst){
	$('#' + serviceFirst + '').hide();
	$('#' + service + '').show();
}

function cancelService(service, serviceFirst){
	$('#' + serviceFirst + '').show();
	$('#' + service + '').hide();
}

function delInterface(interface, foreignId, ipAddr){
	var foreignSource = $('#hiddenForm input[name=foreignSource]').val();
	$.ajax({
		type : 'delete',
		url : '/' + version + '/requisitions/' + foreignSource + '/nodes/' + foreignId + '/interfaces/' + ipAddr,
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

function delService(service, foreignId, ipAddr, serviceName){
	var foreignSource = $('#hiddenForm input[name=foreignSource]').val();
	$.ajax({
		type : 'delete',
		url : '/' + version + '/requisitions/' + foreignSource + '/nodes/' + foreignId + '/interfaces/' + ipAddr + '/services/' + serviceName,
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

function delNodeCategory(category, foreignId, categoryName){
	var foreignSource = $('#hiddenForm input[name=foreignSource]').val();
	$.ajax({
		type : 'delete',
		url : '/' + version + '/requisitions/' + foreignSource + '/nodes/' + foreignId + '/categories/' + categoryName,
		datatype : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert("카테고리 삭제 실패");
		},
		success : function(data) {
			getNodeListInfoAjax(NodeListInfo, foreignSource);
		}
	});
}

function delAssets(assets, foreignId, assetsName){
	var foreignSource = $('#hiddenForm input[name=foreignSource]').val();
	$.ajax({
		type : 'delete',
		url : '/' + version + '/requisitions/' + foreignSource + '/nodes/' + foreignId + '/assets/' + assetsName,
		datatype : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert("Assets 삭제 실패");
		},
		success : function(data) {
			getNodeListInfoAjax(NodeListInfo, foreignSource);
		}
	});
}

function addNodeCategory(trId, foreignId){
	var str = getTableToAddNodeCategory(trId, foreignId);
	$('#' + trId + '').append(str);
}

function addNodeAsset(trId, foreignId){
	var str = getTableToAddNodeAsset(trId, foreignId);
	$('#' + trId + '').append(str);
}

function cancelAddNodeCategory(addNodeCategory){
	$('#' + addNodeCategory + '').remove();
}

function saveAddNodeCategory(trId, foreignId){
	var categoryName = $('#' + trId + '' + '\ select').val();
	var foreignSource = $('#hiddenForm input[name=foreignSource]').val();
	var str = "{\"category\":[{\"name\": \"" + categoryName + "\"}]}"; 
	$.ajax({
		type : 'post',
		url : '/' + version + '/requisitions/' + foreignSource + '/nodes/' + foreignId + '/categories',
		contentType : 'application/json',
		data : str,
		error : function() {
			alert('새로운 인터페이스 ID 추가 실패');
		},
		success : function(data) {
			var str = getTableToFinishAddNodeCategory(foreignId, categoryName);
			$('#' + trId + '').append(str);
			getNodeListInfoAjax(NodeListInfo, foreignSource);
		}
	});
}

function saveCategory(category, foreignId, categoriesFirst, categoryName){
	var categoryNm = $('#' + category + '' + '\ select').val();
	var str = "{\"category\":[{\"name\": \"" + categoryNm + "\"}]}";
	var foreignSource = $('#hiddenForm input[name=foreignSource]').val();
	$.ajax({
		type : 'post',
		url : '/' + version + '/requisitions/' + foreignSource + '/nodes/' + foreignId + '/categories',
		contentType : 'application/json',
		data : str,
		error : function() {
			alert('새로운 카테고리 추가 실패');
		},
		success : function(data) {
			$.ajax({
				type : 'delete',
				url : '/' + version + '/requisitions/' + foreignSource + '/nodes/' + foreignId + '/categories/' + categoryName,
				datatype : 'json',
				contentType : 'application/json',
				error : function(data) {
					alert("카테고리 삭제 실패");
				},
				success : function(data) {
					var str = getTableToFinishAddNodeCategory(foreignId, categoryNm);
					$('#' + categoriesFirst + '').append(str);
					getNodeListInfoAjax(NodeListInfo, foreignSource);
				}
			});
		}
	});
}

function cancelAddNodeAsset(addNodeAsset){
	$('#' + addNodeAsset + '').remove();
}

function saveAddNodeAsset(trId, foreignId){
	var assetValue = $('#' + trId + '' + '\ select').val();
	var assetName = $('#' + trId + '' + '\ input[name=assetsValue]').val();
	var foreignSource = $('#hiddenForm input[name=foreignSource]').val();
	var str = "{\"asset\":[{\"value\": \"" + assetValue + "\",\"name\": \"" + assetName + "\"}]}"; 
	if($('#' + trId + '' + '\ input[name=assetsValue]').val() == ""){
		alert("assetValue를 입력하십시오.");
		return;
	}
	
	$.ajax({
		type : 'post',
		url : '/' + version + '/requisitions/' + foreignSource + '/nodes/' + foreignId + '/assets',
		contentType : 'application/json',
		data : str,
		error : function() {
			alert("assetValue를 입력하십시오.");
		},
		success : function(data) {
			var str = getTableToFinishAddNodeAsset(foreignId, assetValue, assetName);
			$('#' + trId + '').append(str);
			getNodeListInfoAjax(NodeListInfo, foreignSource);
		}
	});
}

function saveAssets(assets, foreignId, assetsName, assetsFirst){
	var assetValue = $('#' + assets + '' + '\ select').val();
	var assetNm = $('#' + assets + '' + '\ input[name=assetsValue]').val();
	var foreignSource = $('#hiddenForm input[name=foreignSource]').val();
	var str = "{\"asset\":[{\"value\": \"" + assetValue + "\",\"name\": \"" + assetNm + "\"}]}"; 
	if($('#' + assets + '' + '\ input[name=assetsValue]').val() == ""){
		alert("assetValue를 입력하십시오.");
		return;
	}
	
	$.ajax({
		type : 'post',
		url : '/' + version + '/requisitions/' + foreignSource + '/nodes/' + foreignId + '/assets',
		contentType : 'application/json',
		data : str,
		error : function() {
			alert('�덈줈●Asset 저장 �ㅽ뙣');
		},
		success : function(data) {
			$.ajax({
				type : 'delete',
				url : '/' + version + '/requisitions/' + foreignSource + '/nodes/' + foreignId + '/assets/' + assetsName,
				contentType : 'application/json',
				dataType : 'json',
				error : function(data) {
					alert("Asset 삭제 실패");
				},
				success : function(data) {
					var str = getTableToFinishAddNodeCategory(foreignId, assetNm);
					$('#' + assetsFirst + '').append(str);
					getNodeListInfoAjax(NodeListInfo, foreignSource);
				}
			});
		}
	});
}

function saveToGetAddService(getAddService, interfaceFirst, foreignId, ipAddr){
	var getAddServiceValue = $('#' + getAddService + '' + '\ select').val();
	var foreignSource = $('#hiddenForm input[name=foreignSource]').val();
	var str = "{\"monitored-service\":[{\"service-name\": \"" + getAddServiceValue + "\"}]}";
	$.ajax({
		type : 'post',
		url : '/' + version + '/requisitions/' + foreignSource + '/nodes/' + foreignId + '/interfaces/' + ipAddr + '/services',
		contentType : 'application/json',
		data : str,
		error : function() {
			alert('새로운 서비스 추가 실패');
		},
		success : function(data) {
			var str = getTableToFinishAddService(foreignId, getAddServiceValue);
			$('#' + getAddService + '').append(str);
			getNodeListInfoAjax(NodeListInfo, foreignSource);
		}
	});
}

function saveGetAddService(services, foreignId, ipAddr, servicesFirst, serviceNm){
	var serviceValue = $('#' + services + '' + '\ select').val();
	var foreignSource = $('#hiddenForm input[name=foreignSource]').val();
	var str = "{\"monitored-service\":[{\"service-name\": \"" + serviceValue + "\"}]}"; 
	$.ajax({
		type : 'post',
		url : '/' + version + '/requisitions/' + foreignSource + '/nodes/' + foreignId + '/interfaces/' + ipAddr + '/services',
		contentType : 'application/json',
		data : str,
		error : function() {
			alert('새로운 서비스 추가 실패');
		},
		success : function(data) {
			$.ajax({
				type : 'delete',
				url : '/' + version + '/requisitions/' + foreignSource + '/nodes/' + foreignId + '/interfaces/' + ipAddr + '/services/' + serviceNm,
				contentType : 'application/json',
				dataType : 'json',
				error : function(data) {
					alert("서비스 삭제 실패");
				},
				success : function(data) {
					var str = getTableToFinishAddService(foreignId, serviceValue);
					$('#' + servicesFirst + '').append(str);
					getNodeListInfoAjax(NodeListInfo, foreignSource);
				}
			});
		}
	});
}

function getServiceTotalList(callback, interfacesFirst, foreignId, ipAddr){
	$.ajax({
		type : 'get',
		url : '/' + version + '/getServiceCategories.do',
		contentType : 'application/json',
		dataType : 'json',
		error : function(data) {
			alert('서비스 리스트 가져오기 실패');
		},
		success : function(data) {
			if (typeof callback == "function") {
				callback(data, interfacesFirst, foreignId, ipAddr);
			}
		}
	});
}

/****************************************************************************************Foreign Source Name: default***********************************************************************************/
function editDefaultDetectors(jsonObj){
	var str = getTableToDetectors(jsonObj);
	$('#defaultRequisitionListTable1').empty();
	$('#defaultRequisitionListTable1').append(str);
}

function editDefaultPolicies(jsonObj){
	var row = getTableToPolicies(jsonObj);
	$('#defaultRequisitionListTable2').empty();
	$('#defaultRequisitionListTable2').append(row);
}

function editDefaultRequisition(){
	getDefaultForeignSourceNameAjax(editDefaultDetectors);
	getDefaultForeignSourceNameAjax(editDefaultPolicies);
}

function getDefaultForeignSourceNameAjax(callback){
	$.ajax({
		type : 'get',
		url : '/' + version + '/foreignSources/default',
		contentType : 'application/json',
		dataType : 'json',
		error : function(data) {
			alert('기본외부소스 가져오기 실패');
		},
		success : function(data) {
			getTotalRequisitionsList(getTotalRequisitions);
			if (typeof callback == "function") {
				callback(data);
			}
		}
	});
}

function addPolicy(){
	var str = addPolicyTab();
	$('#defaultRequisitionListTable2').append(str);
}

function addDetector(){
	var str = addDetectorTab();
	$('#defaultRequisitionListTable1').append(str);
}

function cancelPolicies(ulId){
	$('#' + ulId + '').remove();
}

function cancelDetectors(ulId){
	$('#' + ulId + '').remove();
}

function savePolicies(ulId, policiesName){
	var policyName = $('#' + ulId + '' + '\ input[name=policiesName]').val();
	var policyClass = $('#' + ulId + '' + '\ select').val();
	var policyKey1 = 'action';
	var policyKey2 = 'matchBehavior';
	var policyValue1 = 'DISABLE_COLLECTION'; 
	var policyValue2 = 'ALL_PARAMETERS';
	var str = "{\"policy\":[{\"class\": \"" + policyClass + "\",\"name\": \"" + policyName + "\",\"parameter\":[{\"value\": \"" + policyValue1 + "\",\"key\": \"" + policyKey1 + "\"},{\"value\": \"" + policyValue2 + "\",\"key\": \"" + policyKey2 + "\"}]}]}"; 
	$.ajax({
		type : 'post',
		url : '/' + version + '/foreignSources/default/policies',
		contentType : 'application/json',
		data : str,
		error : function() {
			alert('새로운 policies 추가 실패');
		},
		success : function(data) {
			$.ajax({
				type : 'delete',
				url : '/' + version + '/foreignSources/default/policies/' + policiesName,
				datatype : 'json',
				contentType : 'application/json',
				error : function(data) {
					alert("policy 삭제 실패");
				},
				success : function(data) {
					getDefaultForeignSourceNameAjax(editDefaultDetectors);
					getDefaultForeignSourceNameAjax(editDefaultPolicies);
				}
			});
		}
	});
}

function saveDetectors(ulId, detectorsName){
	var detectorName = $('#' + ulId + '' + '\ input[name=detectorsName]').val();
	var detectorsNm = detectorsName;
	var detectorClass = $('#' + ulId + '' + '\ select').val();
	var str = "{\"detector\":[{\"class\": \"" + detectorClass + "\",\"name\": \"" + detectorName + "\"}]}"; 
	$.ajax({
		type : 'post',
		url : '/' + version + '/foreignSources/default/detectors',
		contentType : 'application/json',
		data : str,
		error : function() {
			alert('새로운 detectors 추가 실패');
		},
		success : function(data) {
			$.ajax({
				type : 'delete',
				url : '/' + version + '/foreignSources/default/detectors/' + detectorsNm,
				datatype : 'json',
				contentType : 'application/json',
				error : function(data) {
					alert("detector 삭제 실패");
				},
				success : function(data) {
					getDefaultForeignSourceNameAjax(editDefaultDetectors);
					getDefaultForeignSourceNameAjax(editDefaultPolicies);
				}
			});
		}
	});
}

function savePolicy(ulId){
	var policyName = $('#' + ulId + '' + '\ input[name=policiesName]').val();
	var policyClass = $('#' + ulId + '' + '\ select').val();
	var policyKey1 = 'action';
	var policyKey2 = 'matchBehavior';
	var policyValue1 = 'DISABLE_COLLECTION'; 
	var policyValue2 = 'ALL_PARAMETERS';
	var str = "{\"policy\":[{\"class\": \"" + policyClass + "\",\"name\": \"" + policyName + "\",\"parameter\":[{\"value\": \"" + policyValue1 + "\",\"key\": \"" + policyKey1 + "\"},{\"value\": \"" + policyValue2 + "\",\"key\": \"" + policyKey2 + "\"}]}]}"; 
	$.ajax({
		type : 'post',
		url : '/' + version + '/foreignSources/default/policies',
		contentType : 'application/json',
		data : str,
		error : function() {
			alert('새로운 detectors 추가 실패');
		},
		success : function(data) {
			getDefaultForeignSourceNameAjax(editDefaultDetectors);
			getDefaultForeignSourceNameAjax(editDefaultPolicies);
		}
	});
}

function saveDetector(ulId){
	var detectorName = $('#' + ulId + '' + '\ input[name=detectorsName]').val();
	var detectorClass = $('#' + ulId + '' + '\ select').val();
	var str = "{\"detector\":[{\"class\": \"" + detectorClass + "\",\"name\": \"" + detectorName + "\"}]}"; 
	$.ajax({
		type : 'post',
		url : '/' + version + '/foreignSources/default/detectors',
		contentType : 'application/json',
		data : str,
		error : function() {
			alert('새로운 detectors 추가 실패');
		},
		success : function(data) {
			getDefaultForeignSourceNameAjax(editDefaultDetectors);
			getDefaultForeignSourceNameAjax(editDefaultPolicies);
		}
	});
}

function delDetector(detectorName)
{
	var url = '/' + version + '/foreignSources/default/detectors/' + encodeURIComponent(detectorName);
	$.ajax({
		type : 'delete',
		url : url,
		datatype : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert("detector 삭제 실패");
		},
		success : function(data) {
			getDefaultForeignSourceNameAjax(editDefaultDetectors);
			getDefaultForeignSourceNameAjax(editDefaultPolicies);
		}
	});
}

function delPolicy(policyName){
	$.ajax({
		type : 'delete',
		url : '/' + version + '/foreignSources/default/policies/' + policyName,
		datatype : 'json',
		contentType : 'application/json',
		error : function(data) {
			alert("policy 삭제 실패");
		},
		success : function(data) {
			getDefaultForeignSourceNameAjax(editDefaultDetectors);
			getDefaultForeignSourceNameAjax(editDefaultPolicies);
		}
	});
}

function modifyDetector(detectorBefore, detectorAfter){
	$('#' + detectorBefore + '').hide();
	$('#' + detectorAfter + '').show();
}

function modifyPolicy(policyBefore, policyAfter){
	$('#' + policyBefore + '').hide();
	$('#' + policyAfter + '').show();
}

function cancelPolicy(policyBefore, policyAfter){
	$('#' + policyBefore + '').show();
	$('#' + policyAfter + '').hide();
}

function cancelDetector(detectorBefore, detectorAfter){
	$('#' + detectorBefore + '').show();
	$('#' + detectorAfter + '').hide();
}

function modifySubDetector(subDetectorBefore, subDetectorAfter){
	$('#' + subDetectorBefore + '').hide();
	$('#' + subDetectorAfter + '').show();
}

function modifySubPolicy(subPolicyBefore, subPolicyAfter){
	$('#' + subPolicyBefore + '').hide();
	$('#' + subPolicyAfter + '').show();
}

function addDetectorParameter(detectorBefore){
	var str = getDetectorParameter();
	$('#' + detectorBefore + '').append(str);
}

function addPolicyParameter(policyBefore){
	var str = getPolicyParameter();
	$('#' + policyBefore + '').append(str);
}

/******************************************************************************************************* view String edit *******************************************************************************************************/
function getTableToFinishAddNodeAsset(foreignId, assetValue, assetName){
	var addAsset = Math.floor(Math.random() * Math.pow(10,15));
	var str = '';
		str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + addAsset + '\'>';
		str += '	<li>';
		str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
		str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifyAssets()">수정</a>';
		str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delAssets()">삭제</a>';
		str += '		<font size="2" style="margin-left: 10px;">asset</font>';
		str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="assetKey" value=\'' + assetValue + '\'/>';
		str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="assetsValue" value=\'' + assetName + '\'/>';
		str += '	</li>';
		str += '</ul>';
	return str;
}

function getTableToFinishAddService(foreignId, getAddServiceValue){
	var addAsset = Math.floor(Math.random() * Math.pow(10,15));
	var str = '';
		str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + addAsset + '\'>';
		str += '	<li>';
		str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
		str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifyAssets()">수정</a>';
		str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delAssets()">삭제</a>';
		str += '		<font size="2" style="margin-left: 10px;">Service</font>';
		str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="assetKey" value=\'' + getAddServiceValue + '\'/>';
		str += '	</li>';
		str += '</ul>';
	return str;
}

function getTableToAddNodeAsset(trId, foreignId){
	var addNodeAsset = Math.floor(Math.random() * Math.pow(10,15));
	var str = '';
		str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + addNodeAsset + '\'>';
		str += '	<li>';
		str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
		str += '		<font size="2" style="margin-left: 10px;">asset</font>';
		str += '		<select id="getAddService" name="getAddService" style="margin-bottom: 0px;" type="text">';
		str += '			<option value="additionalhardware">additionalhardware</option>';
		str += '			<option value="address1">address1</option>';
		str += '			<option value="address2">address2</option>';
		str += '			<option value="admin">admin</option>';
		str += '			<option value="assetNumber">assetNumber</option>';
		str += '			<option value="autoenable">autoenable</option>';
		str += '			<option value="building">building</option>';
		str += '			<option value="category">category</option>';
		str += '			<option value="circuitId">circuitId</option>';
		str += '			<option value="city">city</option>';
		str += '			<option value="class">class</option>';
		str += '			<option value="comment">comment</option>';
		str += '			<option value="connection">connection</option>';
		str += '			<option value="cpu">cpu</option>';
		str += '			<option value="dateInstalled">dateInstalled</option>';
		str += '			<option value="department">department</option>';
		str += '			<option value="description">description</option>';
		str += '			<option value="displayCategory">displayCategory</option>';
		str += '			<option value="division">division</option>';
		str += '			<option value="enable">enable</option>';
		str += '			<option value="floor">floor</option>';
		str += '			<option value="hdd1">hdd1</option>';
		str += '			<option value="hdd2">hdd2</option>';
		str += '			<option value="hdd3">hdd3</option>';
		str += '			<option value="hdd4">hdd4</option>';
		str += '			<option value="hdd5">hdd5</option>';
		str += '			<option value="hdd6">hdd6</option>';
		str += '			<option value="id">id</option>';
		str += '			<option value="inputpower">inputpower</option>';
		str += '			<option value="lastModifiedBy">lastModifiedBy</option>';
		str += '			<option value="lastModifiedDate">lastModifiedDate</option>';
		str += '			<option value="lease">lease</option>';
		str += '			<option value="leaseExpires">leaseExpires</option>';
		str += '			<option value="maintContractExpiration">maintContractExpiration</option>';
		str += '			<option value="maintContractNumber">maintContractNumber</option>';
		str += '			<option value="maintcontract">maintcontract</option>';
		str += '			<option value="managedObjectInstance">managedObjectInstance</option>';
		str += '			<option value="managedObjectType">managedObjectType</option>';
		str += '			<option value="manufacturer">manufacturer</option>';
		str += '			<option value="modelNumber">modelNumber</option>';
		str += '			<option value="node">node</option>';
		str += '			<option value="notifyCategory">notifyCategory</option>';
		str += '			<option value="numpowersupplies">numpowersupplies</option>';
		str += '			<option value="operatingSystem">operatingSystem</option>';
		str += '			<option value="password">password</option>';
		str += '			<option value="pollerCategory">pollerCategory</option>';
		str += '			<option value="port">port</option>';
		str += '			<option value="rack">rack</option>';
		str += '			<option value="rackunitheight">rackunitheight</option>';
		str += '			<option value="ram">ram</option>';
		str += '			<option value="region">region</option>';
		str += '			<option value="room">room</option>';
		str += '			<option value="serialNumber">serialNumber</option>';
		str += '			<option value="slot">slot</option>';
		str += '			<option value="snmpcommunity">snmpcommunity</option>';
		str += '			<option value="state">state</option>';
		str += '			<option value="storagectrl">storagectrl</option>';
		str += '			<option value="supportPhone">supportPhone</option>';
		str += '			<option value="thresholdCategory">thresholdCategory</option>';
		str += '			<option value="username">username</option>';
		str += '			<option value="vendor">vendor</option>';
		str += '			<option value="vendorAssetNumber">vendorAssetNumber</option>';
		str += '			<option value="vendorFax">vendorFax</option>';
		str += '			<option value="vendorPhone">vendorPhone</option>';
		str += '			<option value="zip">zip</option>';
		str += '		</select>';
		str += '		<input style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" name="assetsValue" value=""/>';
		str += '		<a type="button" class="btn btn-primary btn-mini" onclick="saveAddNodeAsset(\'' + trId + '\',\'' + foreignId + '\')">저장</a>';
		str += '		<a type="button" class="btn btn-danger btn-mini" onclick="cancelAddNodeAsset(\'' + addNodeAsset + '\')">취소</a>';
		str += '	</li>';
		str += '</ul>';
	return str;
}

function getTableToAddNodeCategory(trId, foreignId){
	var addNodeCategory = Math.floor(Math.random() * Math.pow(10,15));
	var str = '';
		str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + addNodeCategory + '\'>';
		str += '	<li>';
		str += '		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
		str += '		<font size="2" style="margin-left: 10px;">Node Category</font>';
		str += '		<select id="getAddService" name="getAddService" style="margin-bottom: 0px;" type="text">';
		str += '			<option value="Development" selected="selected">Development</option>';
		str += '			<option value="Production">Production</option>';
		str += '			<option value="Routers">Routers</option>';
		str += '			<option value="Servers">Servers</option>';
		str += '			<option value="Switches">Switches</option>';
		str += '			<option value="Test">Test</option>';
		str += '		</select>';
		str += '		<a type="button" class="btn btn-primary btn-mini" onclick="saveAddNodeCategory(\'' + trId + '\',\'' + foreignId + '\')">저장</a>';
		str += '		<a type="button" class="btn btn-danger btn-mini" onclick="cancelAddNodeCategory(\'' + addNodeCategory + '\')">취소</a>';
		str += '	</li>';
		str += '</ul>';
	return str;
}

function getTableToFinishAddNodeCategory(foreignId, categoryName){
	var addNodeCategory = Math.floor(Math.random() * Math.pow(10,15));
	var categories = $("#hiddenForm input[name=categories]").val();
	var categoriesFirst = $("#hiddenForm input[name=categoriesFirst]").val();
	var str = '';	
		str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + addNodeCategory + '\'>';
		str += '	<li>';
		str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
		str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifyCategories(\'' + categories + '\',\'' + categoriesFirst + '\')">수정</a>';
		str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delNodeCategory(\'' + categories + '\',\'' + categoriesFirst + '\')">삭제</a>';
		str += '		<font size="2" style="margin-left: 10px;">Node Category</font>';
		str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="categories" value=\'' + categoryName + '\'/>';
		str += '	</li>';
	return str;
}

function getAddServices(jsonObj, interfaceFirst, foreignId, ipAddr){
	var getAddService = Math.floor(Math.random() * Math.pow(10,15));
	var getAddServiceFirst = Math.floor(Math.random() * Math.pow(10,15));
	$("#hiddenForm input[name=getAddService]").val(getAddService);
	$("#hiddenForm input[name=getAddServiceFirst]").val(getAddServiceFirst);
	var str = '';
		str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + getAddService + '\'>';
		str += '	<li>';
		str += '		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
		str += '		<font size="2" style="margin-left: 10px;">Service</font>';
		str += '		<select id="getAddService" name="getAddService" style="margin-bottom: 0px;" type="text">';
		for(var i in jsonObj['ServicesItem']){
			str += '			<option value=\'' + jsonObj['ServicesItem'][i]['servicename'] + '\'>\'' + jsonObj['ServicesItem'][i]['servicename'] + '\'</option>';
		}
		str += '		</select>';
		str += '		<a type="button" class="btn btn-primary btn-mini" onclick="saveToGetAddService(\'' + getAddService + '\',\'' + interfaceFirst+ '\',\'' + foreignId + '\',\'' + ipAddr + '\')">저장</a>';
		str += '		<a type="button" class="btn btn-danger btn-mini" onclick="cancelGetAddService(\'' + getAddService + '\')">취소</a>';
		str += '	</li>';
	return str;
}

function getTableToFinishInterface(jsonObj){
	var interfaceObj = jsonObj['interface'];
	var str = '';
	if(jsonObj['@count'] > 1){
		for(var i in interfaceObj){
			var trFinishIpInterface = Math.floor(Math.random() * Math.pow(10,10));
			$("#hiddenForm input[name=trFinishIpInterface]").val(trFinishIpInterface);
				str += '<ul style="display:inline; list-style:none;" id=\'' + trFinishIpInterface + '\'>';
				str += '	<li>';
				str += '		<font size="2" style="margin-left: 10px;">IP Interface</font>';
				str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="ipInterface" value=\'' + interfaceObj[i]['@ip-addr'] + '\'/>';
				str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">Description</font>';
				str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="description" value=\'' + interfaceObj[i]['@descr'] + '\'/>';
				str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">SNMP Primary</font>';
				str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;width: 50px;" readonly="readonly" type="text" name="snmpPrimary" value=\'' + interfaceObj[i]['@snmp-primary'] + '\'/>';
				str += '		<a type="button" class="btn btn-primary btn-mini" onclick="saveIpInterface()">저장</a>';
				str += '		<a type="button" class="btn btn-primary btn-mini" onclick="cancelIpInterface(\'' + trFinishIpInterface + '\')">취소</a>';
				str += '	</li>';
				str += '</ul>';
		}
	}else if(jsonObj['@count'] == 1){
		var trFinishIpInterface = Math.floor(Math.random() * Math.pow(10,10));
		$("#hiddenForm input[name=trFinishIpInterface]").val(trFinishIpInterface);
			str += '<ul style="display:inline; list-style:none;" style="display:none" id=\'' + trFinishIpInterface + '\'>';
			str += '	<li>';
			str += '		<font size="2" style="margin-left: 10px;">IP Interface</font>';
			str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="ipInterface" value=\'' + interfaceObj['@ip-addr'] + '\'/>';
			str += '		<font size="2" style="margin-left: 10px;">Description</font>';
			str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="description" value=\'' + interfaceObj['@descr'] + '\'/>';
			str += '		<font size="2" style="margin-left: 10px;">SNMP Primary</font>';
			str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;width: 50px;" readonly="readonly" type="text" name="snmpPrimary" value=\'' + interfaceObj['@snmp-primary'] + '\'';
			str += '		<a type="button" class="btn btn-primary btn-mini" onclick="saveIpInterface()">저장</a>';
			str += '		<a type="button" class="btn btn-primary btn-mini" onclick="cancelIpInterface()">취소</a></td>';
			str += '	</li>';
			str += '</ul>';
	}
	return str;
}

function getTableToAddInterface(trId, foreignSource, foreignId){
	var tripInterface = Math.floor(Math.random() * Math.pow(10,11));
	var str = '';
		str += '<ul style="display:inline;" id=\'' + tripInterface + '\'>';
		str += '	<li>';
		str += '		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
		str += '		<font size="2" style="margin-left: 10px;">IP Interface</font>';
		str += '		<input name="ipInterface" type="text" style="height: 13px;margin-left: 0px;margin-bottom: 0px;" placeholder="127.0.0.1"/>';
		str += '		<font size="2" style="margin-left: 10px;">Description</font>';
		str += '		<input name="description" type="text" style="height: 13px;margin-left: 0px;margin-bottom: 0px;"/>';
		str += '		<font size="2" style="margin-left: 10px;">SNMP Primary</font>';
		str += '		<select id="snmpPrimary" name="snmpPrimary" style="width: 60px;margin-bottom: 0px;" type="text">';
		str += '			<option value="N">N</option>';
		str += '			<option value="S">S</option>';
		str += '			<option value="P" selected="selected">P</option>';
		str += '		</select>';
		str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="saveIpInterfaces(\'' + foreignSource + '\',\'' + foreignId + '\',\'' + tripInterface + '\')">저장</a>';
		str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelIpInterface(\'' + tripInterface + '\')">취소</a>';
		str += '	</li>';
		str += '</ul>';
	return str;
}

//메뉴의 운영관리 -> 노드 관리 -> + 노드 추가 클릭 시 새로 생성된 하단부 리스트의 편집 버튼 클릭 시 새로 생성된 팝업창 안의 하위 리스트
function getTableToEditRequisitionPop(jsonObj){
	console.log(jsonObj);
	var nodeObj = jsonObj["node"];
	var str = "";
		str += '<tr>';
		str += '	<td><b>노드명</b></td>';
		str += '	<td><b>외부 아이디</b></td>';
		str += '</tr>';
			if(nodeObj != undefined){
				if(nodeObj['@foreign-id'] == undefined){
					for(var i in nodeObj){	
						str += '<tr>';
						str += '	<td>' + nodeObj[i]['@node-label'] + '</td>';
						str += '	<td>' + nodeObj[i]['@foreign-id'] + '</td>';
						str += '</tr>';
					}
				}else{
					str += '<tr>';
					str += '	<td>' + nodeObj['@node-label'] + '</td>';
					str += '	<td>' + nodeObj['@foreign-id'] + '</td>';
					str += '</tr>';
				}
			}else{
				str += '<tr>';
				str += '	<td>노드가 없습니다.</td>';
				str += '	<td></td>';
				str += '</tr>';
			}
	return str;
	
	
	
	
	
	
	
	
	
	
	
/*	var foreignSource = $("#hiddenForm input[name=foreignSource]").val();
	var nodeObj = jsonObj["node"];
	var str = "";
	if(jsonObj["node"] != undefined){
		if(jsonObj["node"]["@foreign-id"] == undefined){	
			for(var i in nodeObj){
				var inputForeignId = Math.floor(Math.random() * Math.pow(10,13));
				var trId = Math.floor(Math.random() * Math.pow(10,12));
				var interfaceObj = nodeObj[i]['interface'];
				var categoryObj = nodeObj[i]['category'];
				var assetObj = nodeObj[i]['asset'];
					str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + trId + '\'>';
					str += '	<li>';
					str += '  		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="modifyRequisitionPopList(\'' + nodeObj + '\',\'' + trId + '\',\'' + nodeObj[i]["@node-label"] + '\',\'' + inputForeignId + '\')">수정</a>';
					str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="delRequisitionPopList(\'' + nodeObj[i]["@foreign-id"] + '\')">삭제</a>';
					str += '		<font size="2" style="margin-left: 10px;">Node</font>';
					str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" value=\'' + nodeObj[i]["@node-label"] + '\'/>';
					str += '		<font size="2" style="margin-left: 10px;">ForeignId</font>';
					str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" value=\'' + nodeObj[i]["@foreign-id"] + '\'/>';
					str += '		<font size="2" style="margin-left: 10px;">Site</font>';
					str += '		<input style="border:0; background: lightgrey; width: 80px; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" value=\'' + nodeObj[i]["@building"] + '\'/>';
					str += '		<a href="#" onclick="javascript:addInterface(\'' + trId + '\',\'' + nodeObj[i]["@building"] + '\',\'' + nodeObj[i]["@foreign-id"] + '\')"><font size="1" color="black" color="black">[Add Interface]&nbsp;</font></a>';
					str += '		<a href="#" onclick="javascript:addNodeCategory(\'' + trId + '\',\'' + nodeObj[i]["@foreign-id"] + '\')"><font size="1" color="black" color="black">[Add Node Category]&nbsp;</font></a>';
					str += '		<a href="#" onclick="javascript:addNodeAsset(\'' + trId + '\',\'' + nodeObj[i]["@foreign-id"] + '\')"><font size="1" color="black">[Add Node Asset]</font></a>';
					str += '	</li>';
					str += '</ul>';
					str += '<ul style="display:none; list-style:none; padding: 0px;margin: 0px;" id=\'' + inputForeignId + '\'>';
					str += '	<li>';
					str += '		<font size="2" style="margin-left: 10px;">Node</font>';
					str += '		<input name="editRequisitionNodeLabelFirst" style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" placeholder=\'' + nodeObj[i]["@node-label"] + '\'/>';
					str += '		<font size="2" style="margin-left: 10px;">ForeignId</font>';
					str += '		<input name="editRequisitionForeignIdFirst" style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" placeholder=\'' + nodeObj[i]["@foreign-id"] + '\'/>';
					str += '		<font size="2" style="margin-left: 10px;">Site</font>';
					str += '		<input name="editRequisitionBuildingFirst" type="text" style="height: 13px;margin-left: 0px;margin-bottom: 0px; width: 80px;" placeholder=\'' + nodeObj[i]["@building"] + '\' style="width: 80px"/>';
					str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="updateSaveRequisitionPopList(\'' + foreignSource + '\',\'' + inputForeignId + '\',\'' + nodeObj[i]["@foreign-id"] + '\')">저장</a>';
					str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="returnCancelRequisitionPopList(\'' + jsonObj + '\',\'' + foreignSource + '\',\'' + trId + '\',\'' + nodeObj[i]["@node-label"] + '\',\'' + inputForeignId + '\')">취소</a>';
					str += '	</li>';
					str += '</ul>';
				if(nodeObj[i]['interface'] != undefined){
					if(nodeObj[i]['interface']['@descr'] == undefined){
						for(var q in interfaceObj){
							var interfaces = Math.floor(Math.random() * Math.pow(10,15));
							var interfacesFirst = Math.floor(Math.random() * Math.pow(10,15));
								$("#hiddenForm input[name=interfaces]").val(interfaces);
								$("#hiddenForm input[name=interfacesFirst]").val(interfacesFirst);
								str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + interfacesFirst +'\'>';
								str += '	<li>';
								str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
								str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifyInterfaces(\'' + interfaces + '\',\'' + interfacesFirst+'\')">수정</a>';
								str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delInterface(\'' + interfaces + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + interfaceObj[q]['@ip-addr'] + '\')">삭제</a>';
								str += '		<font size="2" style="margin-left: 10px;">IP Interface</font>';
								str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="ipInterface" value=\'' + interfaceObj[q]['@ip-addr'] + '\'/>';
								str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">Description</font>';
								str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="description" value=\'' + interfaceObj[q]['@descr'] + '\'/>';
								str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">SNMP Primary</font>';
								str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;width: 50px;" readonly="readonly" type="text" name="snmpPrimary" value=\'' + interfaceObj[q]['@snmp-primary'] + '\'/>';
								str += '		<a href="#" onclick="javascript:addService(\'' + interfacesFirst +'\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + interfaceObj[q]['@ip-addr'] + '\')"><font size="1" color="black">Add Service</font></a>';
								str += '	</li>';
								str += '</ul>';
								str += '<ul style="display:inline;display:none;padding: 0px;margin: 0px;" id=\'' + interfaces + '\'>';
								str += '	<li>';
								str += '		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
								str += '		<font size="2" style="margin-left: 10px;">IP Interface</font>';
								str += '		<input name="ipInterface" type="text" style="height: 13px;margin-left: 0px;margin-bottom: 0px;" placeholder="127.0.0.1"/>';
								str += '		<font size="2" style="margin-left: 10px;">Description</font>';
								str += '		<input name="description" type="text" style="height: 13px;margin-left: 0px;margin-bottom: 0px;"/>';
								str += '		<font size="2" style="margin-left: 10px;">SNMP Primary</font>';
								str += '		<select id="snmpPrimary" name="snmpPrimary" style="width: 60px;margin-bottom: 0px;" type="text">';
								str += '			<option value="N">N</option>';
								str += '			<option value="S">S</option>';
								str += '			<option value="P" selected="selected">P</option>';
								str += '		</select>';
								str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="saveIpInterfaces(\'' + foreignSource + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + interfaces + '\',\'' + interfaceObj[q]['@ip-addr'] + '\',\'' + interfaceObj[q]['@descr'] + '\',\'' + interfaceObj[q]['@snmp-primary'] + '\')">저장</a>';
								str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelInterfaces(\'' + interfaces + '\',\'' + interfacesFirst+'\')">취소</a>';
								str += '	</li>';
								str += '</ul>';
								if(interfaceObj[q]["monitored-service"] != undefined){
									if(interfaceObj[q]["monitored-service"]['@service-name'] == undefined){
										for(var w in interfaceObj[q]["monitored-service"]){
											var services = Math.floor(Math.random() * Math.pow(10,15));
											var servicesFirst = Math.floor(Math.random() * Math.pow(10,15));
												str += '<ul style="padding: 0px;margin: 0px;" id=\'' + servicesFirst +'\'>';
												str += '	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
												str += '	<a type="button" class="btn btn-primary btn-mini" onclick="modifyServices(\'' + services + '\',\'' + servicesFirst+'\')">수정</a>';
												str += '	<a type="button" class="btn btn-danger btn-mini" onclick="delService(\'' + services + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + interfaceObj[q]['@ip-addr'] + '\',\'' + interfaceObj[q]["monitored-service"][w]['@service-name'] + '\')">삭제</a>';
												str += '	<font size="2" style="margin-left: 10px;">Service</font>';
												str += '	<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="description" value=\'' + interfaceObj[q]["monitored-service"][w]['@service-name'] + '\'/>';
												str += '</ul>';
												str += '<ul style="display:inline; list-style:none; display:none;padding: 0px;margin: 0px;" id=\'' + services + '\'>';
												str += '	<li>';
												str += '		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
												str += '		<font size="2" style="margin-left: 10px;">Service</font>';
												str += '		<select id="getAddService" name="getAddService" style="margin-bottom: 0px;" type="text">';
												str += '			<option value="DNS" selected="selected">DNS</option>';
												str += '			<option value="Dell-OpenManage">Dell-OpenManage</option>';
												str += '			<option value="FTP">FTP</option>';
												str += '			<option value="HP Insight Manager">HP Insight Manager</option>';
												str += '			<option value="HTTP">HTTP</option>';
												str += '			<option value="HTTP-8000">HTTP-8000</option>';
												str += '			<option value="HTTP-8000">HTTP-8000</option>';
												str += '			<option value="HTTPS">HTTPS</option>';
												str += '			<option value="HypericAgent">HypericAgent</option>';
												str += '			<option value="HypericHQ">HypericHQ</option>';
												str += '			<option value="ICMP">ICMP</option>';
												str += '			<option value="IMAP">IMAP</option>';
												str += '			<option value="LDAP">LDAP</option>';
												str += '			<option value="MSExchange">MSExchange</option>';
												str += '			<option value="MySQL">MySQL</option>';
												str += '			<option value="NRPE">NRPE</option>';
												str += '			<option value="NRPE-NoSSL">NRPE-NoSSL</option>';
												str += '			<option value="New Detector">New Detector</option>';
												str += '			<option value="OpenNMS-JVM">OpenNMS-JVM</option>';
												str += '			<option value="Oracle">Oracle</option>';
												str += '			<option value="POP3">POP3</option>';
												str += '			<option value="Postgres">Postgres</option>';
												str += '			<option value="Router">Router</option>';
												str += '			<option value="SMTP">SMTP</option>';
												str += '			<option value="SNMP">SNMP</option>';
												str += '			<option value="SQLServer">SQLServer</option>';
												str += '			<option value="SSH">SSH</option>';
												str += '			<option value="SVC">SVC</option>';
												str += '			<option value="StrafePing">StrafePing</option>';
												str += '			<option value="Telnet">Telnet</option>';
												str += '			<option value="Windows-Task-Scheduler">Windows-Task-Scheduler</option>';
												str += '		</select>';
												str += '		<a type="button" class="btn btn-primary btn-mini" onclick="saveGetAddService(\'' + services + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + interfaceObj[q]['@ip-addr'] + '\',\'' + servicesFirst + '\',\'' + interfaceObj[q]["monitored-service"][w]['@service-name'] + '\')">저장</a>';
												str += '		<a type="button" class="btn btn-danger btn-mini" onclick="cancelServices(\'' + services + '\',\'' + servicesFirst+'\')">취소</a>';
												str += '	</li>';
												str += '</ul>';
										}
									}else{
										var service = Math.floor(Math.random() * Math.pow(10,15));
										var serviceFirst =  Math.floor(Math.random() * Math.pow(10,15));
											str += '<ul style="padding: 0px;margin: 0px;" id=\'' + serviceFirst +'\'>';
											str += '	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
											str += '	<a type="button" class="btn btn-primary btn-mini" onclick="modifyService(\'' + service + '\',\'' + serviceFirst+'\')">수정</a>';
											str += '	<a type="button" class="btn btn-danger btn-mini" onclick="delService(\'' + service + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + interfaceObj[q]['@ip-addr'] + '\',\'' + interfaceObj[q]["monitored-service"]['@service-name'] + '\')">삭제</a>';
											str += '	<font size="2" style="margin-left: 10px;">Service</font>';
											str += '	<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="service" value=\'' + interfaceObj[q]["monitored-service"]['@service-name'] + '\'/>';
											str += '</ul>';
											str += '<ul style="display:inline; list-style:none; padding: 0px;display:none;margin: 0px;" id=\'' + service + '\'>';
											str += '	<li>';
											str += '		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
											str += '		<font size="2" style="margin-left: 10px;">Service</font>';
											str += '		<select id="getAddService" name="getAddService" style="margin-bottom: 0px;" type="text">';
											str += '			<option value="DNS" selected="selected">DNS</option>';
											str += '			<option value="Dell-OpenManage">Dell-OpenManage</option>';
											str += '			<option value="FTP">FTP</option>';
											str += '			<option value="HP Insight Manager">HP Insight Manager</option>';
											str += '			<option value="HTTP">HTTP</option>';
											str += '			<option value="HTTP-8000">HTTP-8000</option>';
											str += '			<option value="HTTP-8000">HTTP-8000</option>';
											str += '			<option value="HTTPS">HTTPS</option>';
											str += '			<option value="HypericAgent">HypericAgent</option>';
											str += '			<option value="HypericHQ">HypericHQ</option>';
											str += '			<option value="ICMP">ICMP</option>';
											str += '			<option value="IMAP">IMAP</option>';
											str += '			<option value="LDAP">LDAP</option>';
											str += '			<option value="MSExchange">MSExchange</option>';
											str += '			<option value="MySQL">MySQL</option>';
											str += '			<option value="NRPE">NRPE</option>';
											str += '			<option value="NRPE-NoSSL">NRPE-NoSSL</option>';
											str += '			<option value="New Detector">New Detector</option>';
											str += '			<option value="OpenNMS-JVM">OpenNMS-JVM</option>';
											str += '			<option value="Oracle">Oracle</option>';
											str += '			<option value="POP3">POP3</option>';
											str += '			<option value="Postgres">Postgres</option>';
											str += '			<option value="Router">Router</option>';
											str += '			<option value="SMTP">SMTP</option>';
											str += '			<option value="SNMP">SNMP</option>';
											str += '			<option value="SQLServer">SQLServer</option>';
											str += '			<option value="SSH">SSH</option>';
											str += '			<option value="SVC">SVC</option>';
											str += '			<option value="StrafePing">StrafePing</option>';
											str += '			<option value="Telnet">Telnet</option>';
											str += '			<option value="Windows-Task-Scheduler">Windows-Task-Scheduler</option>';
											str += '		</select>';
											str += '		<a type="button" class="btn btn-primary btn-mini" onclick="saveGetAddService(\'' + service + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + interfaceObj[q]['@ip-addr'] + '\',\'' + serviceFirst + '\',\'' + interfaceObj[q]["monitored-service"]['@service-name'] + '\')">저장</a>';
											str += '		<a type="button" class="btn btn-danger btn-mini" onclick="cancelService(\'' + service + '\',\'' + serviceFirst+'\')">취소</a>';
											str += '	</li>';
											str += '</ul>';
									}
								}
						}
					}else{
						var interface = Math.floor(Math.random() * Math.pow(10,15));
							$("#hiddenForm input[name=interface]").val(interface);
							var interfaceFirst = Math.floor(Math.random() * Math.pow(10,15));
							$("#hiddenForm input[name=interfacesFirst]").val(interfaceFirst);
							str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + interfaceFirst + '\'>';
							str += '	<li>';
							str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
							str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifyInterface(\'' + interface + '\',\'' + interfaceFirst+'\')">수정</a>';
							str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delInterface(\'' + interface + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + interfaceObj['@ip-addr'] + '\')">삭제</a></td>';
							str += '		<font size="2" style="margin-left: 10px;">IP Interface</font>';
							str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="ipInterface" value=\'' + interfaceObj['@ip-addr'] + '\'/>';
							str += '		<font size="2" style="margin-left: 10px;">Description</font>';
							str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="description" value=\'' + interfaceObj['@descr'] + '\'/>';
							str += '		<font size="2" style="margin-left: 10px;">SNMP Primary</font>';
							str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;width: 50px;" readonly="readonly" type="text" name="snmpPrimary" value=\'' + interfaceObj['@snmp-primary'] + '\'>';
							str += '		<a href="#" onclick="javascript:addService(\'' + interfaceFirst +'\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + interfaceObj['@ip-addr'] + '\')"><font size="1" color="black">Add Service</font></a>';
							str += '	</li>';
							str += '</ul>';
							str += '<ul style="display:inline;display:none;padding: 0px;margin: 0px;" id=\'' + interface + '\'>';
							str += '	<li>';
							str += '		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
							str += '		<font size="2" style="margin-left: 10px;">IP Interface</font>';
							str += '		<input name="ipInterface" type="text" style="height: 13px;margin-left: 0px;margin-bottom: 0px;" placeholder="127.0.0.1"/>';
							str += '		<font size="2" style="margin-left: 10px;">Description</font>';
							str += '		<input name="description" type="text" style="height: 13px;margin-left: 0px;margin-bottom: 0px;"/>';
							str += '		<font size="2" style="margin-left: 10px;">SNMP Primary</font>';
							str += '		<select id="snmpPrimary" name="snmpPrimary" style="width: 60px;margin-bottom: 0px;" type="text">';
							str += '			<option value="N">N</option>';
							str += '			<option value="S">S</option>';
							str += '			<option value="P" selected="selected">P</option>';
							str += '		</select>';
							str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="saveIpInterfaces(\'' + foreignSource + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + interface + '\',\'' + interfaceObj['@ip-addr'] + '\',\'' + interfaceObj['@descr'] + '\',\'' + interfaceObj['@snmp-primary'] + '\')">저장</a>';
							str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelInterface(\'' + interface + '\',\'' + interfaceFirst+'\')">취소</a>';
							str += '	</li>';
							str += '</ul>';
							if(interfaceObj["monitored-service"] != undefined){
								if(interfaceObj["monitored-service"]['@service-name'] == undefined){
									for(var q in interfaceObj["monitored-service"]){
										var services = Math.floor(Math.random() * Math.pow(10,15));
										var servicesFirst = Math.floor(Math.random() * Math.pow(10,15));
											str += '<ul style="padding: 0px;margin: 0px;" id=\'' + servicesFirst +'\'>';
											str += '	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
											str += '	<a type="button" class="btn btn-primary btn-mini" onclick="modifyServices(\'' + services + '\',\'' + servicesFirst+'\')">수정</a>';
											str += '	<a type="button" class="btn btn-danger btn-mini" onclick="delService(\'' + services + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + interfaceObj['@ip-addr'] + '\',\'' + interfaceObj["monitored-service"][q]['@service-name'] + '\')">삭제</a>';
											str += '	<font size="2" style="margin-left: 10px;">Service</font>';
											str += '	<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="description" value=\'' + interfaceObj["monitored-service"][q]['@service-name'] + '\'/>';
											str += '</ul>';
											str += '<ul style="display:inline; list-style:none; display:none;padding: 0px;margin: 0px;" id=\'' + services + '\'>';
											str += '	<li>';
											str += '		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
											str += '		<font size="2" style="margin-left: 10px;">Service</font>';
											str += '		<select id="getAddService" name="getAddService" style="margin-bottom: 0px;" type="text">';
											str += '			<option value="DNS" selected="selected">DNS</option>';
											str += '			<option value="Dell-OpenManage">Dell-OpenManage</option>';
											str += '			<option value="FTP">FTP</option>';
											str += '			<option value="HP Insight Manager">HP Insight Manager</option>';
											str += '			<option value="HTTP">HTTP</option>';
											str += '			<option value="HTTP-8000">HTTP-8000</option>';
											str += '			<option value="HTTP-8000">HTTP-8000</option>';
											str += '			<option value="HTTPS">HTTPS</option>';
											str += '			<option value="HypericAgent">HypericAgent</option>';
											str += '			<option value="HypericHQ">HypericHQ</option>';
											str += '			<option value="ICMP">ICMP</option>';
											str += '			<option value="IMAP">IMAP</option>';
											str += '			<option value="LDAP">LDAP</option>';
											str += '			<option value="MSExchange">MSExchange</option>';
											str += '			<option value="MySQL">MySQL</option>';
											str += '			<option value="NRPE">NRPE</option>';
											str += '			<option value="NRPE-NoSSL">NRPE-NoSSL</option>';
											str += '			<option value="New Detector">New Detector</option>';
											str += '			<option value="OpenNMS-JVM">OpenNMS-JVM</option>';
											str += '			<option value="Oracle">Oracle</option>';
											str += '			<option value="POP3">POP3</option>';
											str += '			<option value="Postgres">Postgres</option>';
											str += '			<option value="Router">Router</option>';
											str += '			<option value="SMTP">SMTP</option>';
											str += '			<option value="SNMP">SNMP</option>';
											str += '			<option value="SQLServer">SQLServer</option>';
											str += '			<option value="SSH">SSH</option>';
											str += '			<option value="SVC">SVC</option>';
											str += '			<option value="StrafePing">StrafePing</option>';
											str += '			<option value="Telnet">Telnet</option>';
											str += '			<option value="Windows-Task-Scheduler">Windows-Task-Scheduler</option>';
											str += '		</select>';
											str += '		<a type="button" class="btn btn-primary btn-mini" onclick="saveGetAddService(\'' + services + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + interfaceObj['@ip-addr'] + '\',\'' + servicesFirst + '\',\'' + interfaceObj["monitored-service"][q]['@service-name'] + '\')">저장</a>';
											str += '		<a type="button" class="btn btn-danger btn-mini" onclick="cancelServices(\'' + services + '\',\'' + servicesFirst+'\')">취소</a>';
											str += '	</li>';
											str += '</ul>';
									}
								}else{
									var service = Math.floor(Math.random() * Math.pow(10,15));
									var serviceFirst =  Math.floor(Math.random() * Math.pow(10,15));
										str += '<ul style="padding: 0px;margin: 0px;" id=\'' + serviceFirst +'\'>';
										str += '	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
										str += '	<a type="button" class="btn btn-primary btn-mini" onclick="modifyService(\'' + service + '\',\'' + serviceFirst+'\')">수정</a>';
										str += '	<a type="button" class="btn btn-danger btn-mini" onclick="delService(\'' + service + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + interfaceObj['@ip-addr'] + '\',\'' + interfaceObj["monitored-service"]['@service-name'] + '\')">삭제</a>';
										str += '	<font size="2" style="margin-left: 10px;">Service</font>';
										str += '	<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="service" value=\'' + interfaceObj["monitored-service"]['@service-name'] + '\'/>';
										str += '</ul>';
										str += '<ul style="display:inline; list-style:none; padding: 0px;display:none;margin: 0px;" id=\'' + service + '\'>';
										str += '	<li>';
										str += '		&nbsp;&nbsp;&nbsp;';
										str += '		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
										str += '		<font size="2" style="margin-left: 10px;">Service</font>';
										str += '		<select id="getAddService" name="getAddService" style="margin-bottom: 0px;" type="text">';
										str += '			<option value="DNS" selected="selected">DNS</option>';
										str += '			<option value="Dell-OpenManage">Dell-OpenManage</option>';
										str += '			<option value="FTP">FTP</option>';
										str += '			<option value="HP Insight Manager">HP Insight Manager</option>';
										str += '			<option value="HTTP">HTTP</option>';
										str += '			<option value="HTTP-8000">HTTP-8000</option>';
										str += '			<option value="HTTP-8000">HTTP-8000</option>';
										str += '			<option value="HTTPS">HTTPS</option>';
										str += '			<option value="HypericAgent">HypericAgent</option>';
										str += '			<option value="HypericHQ">HypericHQ</option>';
										str += '			<option value="ICMP">ICMP</option>';
										str += '			<option value="IMAP">IMAP</option>';
										str += '			<option value="LDAP">LDAP</option>';
										str += '			<option value="MSExchange">MSExchange</option>';
										str += '			<option value="MySQL">MySQL</option>';
										str += '			<option value="NRPE">NRPE</option>';
										str += '			<option value="NRPE-NoSSL">NRPE-NoSSL</option>';
										str += '			<option value="New Detector">New Detector</option>';
										str += '			<option value="OpenNMS-JVM">OpenNMS-JVM</option>';
										str += '			<option value="Oracle">Oracle</option>';
										str += '			<option value="POP3">POP3</option>';
										str += '			<option value="Postgres">Postgres</option>';
										str += '			<option value="Router">Router</option>';
										str += '			<option value="SMTP">SMTP</option>';
										str += '			<option value="SNMP">SNMP</option>';
										str += '			<option value="SQLServer">SQLServer</option>';
										str += '			<option value="SSH">SSH</option>';
										str += '			<option value="SVC">SVC</option>';
										str += '			<option value="StrafePing">StrafePing</option>';
										str += '			<option value="Telnet">Telnet</option>';
										str += '			<option value="Windows-Task-Scheduler">Windows-Task-Scheduler</option>';
										str += '		</select>';
										str += '		<a type="button" class="btn btn-primary btn-mini" onclick="saveGetAddService(\'' + service + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + interfaceObj['@ip-addr'] + '\',\'' + serviceFirst + '\',\'' + interfaceObj["monitored-service"]['@service-name'] + '\')">저장</a>';
										str += '		<a type="button" class="btn btn-danger btn-mini" onclick="cancelService(\'' + service + '\',\'' + serviceFirst+'\')">취소</a>';
										str += '	</li>';
										str += '</ul>';
								}
							}
					}
				}
				if(nodeObj[i]['category'] != undefined){
					if(nodeObj[i]['category']['@name'] == undefined){
						for(var q in categoryObj){
							var categories = Math.floor(Math.random() * Math.pow(10,15));
							var categoriesFirst = Math.floor(Math.random() * Math.pow(10,15));
								$("#hiddenForm input[name=categories]").val(categories);
								$("#hiddenForm input[name=categoriesFirst]").val(categoriesFirst);
								str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + categoriesFirst + '\'>';
								str += '	<li>';
								str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
								str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifyCategories(\'' + categories + '\',\'' + categoriesFirst+ '\')">수정</a>';
								str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delNodeCategory(\'' + categories + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + categoryObj[q]['@name'] + '\')">삭제</a>';
								str += '		<font size="2" style="margin-left: 10px;">Node Category</font>';
								str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="categories" value=\'' + categoryObj[q]['@name'] + '\'/>';
								str += '	</li>';
								str += '</ul>';
								str += '<ul style="display:inline; list-style:none; display:none;padding: 0px;margin: 0px;" id=\'' + categories + '\'>';
								str += '	<li>';
								str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
								str += '		<font size="2" style="margin-left: 10px;">Node Category</font>';
								str += '		<select id="getAddService" name="getAddService" style="margin-bottom: 0px;" type="text">';
								str += '			<option value="Development" selected="selected">Development</option>';
								str += '			<option value="Production">Production</option>';
								str += '			<option value="Routers">Routers</option>';
								str += '			<option value="Servers">Servers</option>';
								str += '			<option value="Switches">Switches</option>';
								str += '			<option value="Test">Test</option>';
								str += '		</select>';
								str += '		<a type="button" class="btn btn-primary btn-mini" onclick="saveCategory(\'' + categories + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + categoriesFirst + '\',\'' + categoryObj[q]['@name'] + '\')">저장</a>';
								str += '		<a type="button" class="btn btn-danger btn-mini" onclick="cancelCategories(\'' + categories + '\',\'' + categoriesFirst+ '\')">취소</a>';
								str += '	</li>';
								str += '</ul>';
						}
					}else{
						var category = Math.floor(Math.random() * Math.pow(10,15));
						var categoryFirst = Math.floor(Math.random() * Math.pow(10,15));
							$("#hiddenForm input[name=category]").val(category);
							$("#hiddenForm input[name=categoryFirst]").val(categoryFirst);
							str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + categoryFirst + '\'>';
							str += '	<li>';
							str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
							str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifyCategory(\'' + category + '\',\'' + categoryFirst + '\')">수정</a>';
							str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delNodeCategory(\'' + category + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + categoryObj['@name'] + '\')">삭제</a>';
							str += '		<font size="2" style="margin-left: 10px;">Node Category</font>';
							str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="category" value=\'' + categoryObj['@name'] + '\'/>';
							str += '	</li>';
							str += '</ul>';
							str += '<ul style="display:inline; list-style:none; display:none;padding: 0px;margin: 0px;" id=\'' + category + '\'>';
							str += '	<li>';
							str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
							str += '		<font size="2" style="margin-left: 10px;">Node Category</font>';
							str += '		<select id="getAddService" name="getAddService" style="margin-bottom: 0px;" type="text">';
							str += '			<option value="Development" selected="selected">Development</option>';
							str += '			<option value="Production">Production</option>';
							str += '			<option value="Routers">Routers</option>';
							str += '			<option value="Servers">Servers</option>';
							str += '			<option value="Switches">Switches</option>';
							str += '			<option value="Test">Test</option>';
							str += '		</select>';
							str += '		<a type="button" class="btn btn-primary btn-mini" onclick="saveCategory(\'' + category + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + categoryFirst + '\',\'' + categoryObj['@name'] + '\')">저장</a>';
							str += '		<a type="button" class="btn btn-danger btn-mini" onclick="cancelCategory(\'' + category + '\',\'' + categoryFirst + '\')">취소</a>';
							str += '	</li>';
							str += '</ul>';
					}
				}
				if(nodeObj[i]['asset'] != undefined){
					if(nodeObj[i]['asset']['@name'] == undefined){
						for(var q in assetObj){
							var assets = Math.floor(Math.random() * Math.pow(10,15));
							var assetsFirst = Math.floor(Math.random() * Math.pow(10,15));
								$("#hiddenForm input[name=assets]").val(assets);
								$("#hiddenForm input[name=assetsFirst]").val(assetsFirst);
								str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + assetsFirst + '\'>';
								str += '	<li>';
								str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
								str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifyAssets(\'' + assets + '\',\'' + assetsFirst + '\')">수정</a>';
								str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delAssets(\'' + assets + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + assetObj[q]['@name'] + '\')">삭제</a>';
								str += '		<font size="2" style="margin-left: 10px;">asset</font>';
								str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="assetKey" value=\'' + assetObj[q]['@value'] + '\'/>';
								str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="assetsValue" value=\'' + assetObj[q]['@name'] + '\'/>';
								str += '	</li>';
								str += '</ul>';
								str += '<ul style="display:inline; list-style:none; display:none;padding: 0px;margin: 0px;" id=\'' + assets + '\'>';
								str += '	<li>';
								str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
								str += '		<font size="2" style="margin-left: 10px;">asset</font>';
								str += '		<select id="getAddService" name="getAddService" style="margin-bottom: 0px;" type="text">';
								str += '			<option value="additionalhardware">additionalhardware</option>';
								str += '			<option value="address1">address1</option>';
								str += '			<option value="address2">address2</option>';
								str += '			<option value="admin">admin</option>';
								str += '			<option value="assetNumber">assetNumber</option>';
								str += '			<option value="autoenable">autoenable</option>';
								str += '			<option value="building">building</option>';
								str += '			<option value="category">category</option>';
								str += '			<option value="circuitId">circuitId</option>';
								str += '			<option value="city">city</option>';
								str += '			<option value="class">class</option>';
								str += '			<option value="comment">comment</option>';
								str += '			<option value="connection">connection</option>';
								str += '			<option value="cpu">cpu</option>';
								str += '			<option value="dateInstalled">dateInstalled</option>';
								str += '			<option value="department">department</option>';
								str += '			<option value="description">description</option>';
								str += '			<option value="displayCategory">displayCategory</option>';
								str += '			<option value="division">division</option>';
								str += '			<option value="enable">enable</option>';
								str += '			<option value="floor">floor</option>';
								str += '			<option value="hdd1">hdd1</option>';
								str += '			<option value="hdd2">hdd2</option>';
								str += '			<option value="hdd3">hdd3</option>';
								str += '			<option value="hdd4">hdd4</option>';
								str += '			<option value="hdd5">hdd5</option>';
								str += '			<option value="hdd6">hdd6</option>';
								str += '			<option value="id">id</option>';
								str += '			<option value="inputpower">inputpower</option>';
								str += '			<option value="lastModifiedBy">lastModifiedBy</option>';
								str += '			<option value="lastModifiedDate">lastModifiedDate</option>';
								str += '			<option value="lease">lease</option>';
								str += '			<option value="leaseExpires">leaseExpires</option>';
								str += '			<option value="maintContractExpiration">maintContractExpiration</option>';
								str += '			<option value="maintContractNumber">maintContractNumber</option>';
								str += '			<option value="maintcontract">maintcontract</option>';
								str += '			<option value="managedObjectInstance">managedObjectInstance</option>';
								str += '			<option value="managedObjectType">managedObjectType</option>';
								str += '			<option value="manufacturer">manufacturer</option>';
								str += '			<option value="modelNumber">modelNumber</option>';
								str += '			<option value="node">node</option>';
								str += '			<option value="notifyCategory">notifyCategory</option>';
								str += '			<option value="numpowersupplies">numpowersupplies</option>';
								str += '			<option value="operatingSystem">operatingSystem</option>';
								str += '			<option value="password">password</option>';
								str += '			<option value="pollerCategory">pollerCategory</option>';
								str += '			<option value="port">port</option>';
								str += '			<option value="rack">rack</option>';
								str += '			<option value="rackunitheight">rackunitheight</option>';
								str += '			<option value="ram">ram</option>';
								str += '			<option value="region">region</option>';
								str += '			<option value="room">room</option>';
								str += '			<option value="serialNumber">serialNumber</option>';
								str += '			<option value="slot">slot</option>';
								str += '			<option value="snmpcommunity">snmpcommunity</option>';
								str += '			<option value="state">state</option>';
								str += '			<option value="storagectrl">storagectrl</option>';
								str += '			<option value="supportPhone">supportPhone</option>';
								str += '			<option value="thresholdCategory">thresholdCategory</option>';
								str += '			<option value="username">username</option>';
								str += '			<option value="vendor">vendor</option>';
								str += '			<option value="vendorAssetNumber">vendorAssetNumber</option>';
								str += '			<option value="vendorFax">vendorFax</option>';
								str += '			<option value="vendorPhone">vendorPhone</option>';
								str += '			<option value="zip">zip</option>';
								str += '		</select>';
								str += '		<input style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" name="assetsValue" value=""/>';
								str += '		<a type="button" class="btn btn-primary btn-mini" onclick="saveAssets(\'' + assets + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + assetObj[q]['@name'] + '\',\'' + assetsFirst + '\')">저장</a>';
								str += '		<a type="button" class="btn btn-danger btn-mini" onclick="cancelAssets(\'' + assets + '\',\'' + assetsFirst + '\')">취소</a>';
								str += '	</li>';
								str += '</ul>';
						}
					}else{
						var asset = Math.floor(Math.random() * Math.pow(10,15));
						var assetFirst = Math.floor(Math.random() * Math.pow(10,15));
							$("#hiddenForm input[name=assets]").val(asset);
							$("#hiddenForm input[name=assetsFirst]").val(assetFirst);
							str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + assetFirst + '\'>';
							str += '	<li>';
							str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
							str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifyAsset(\'' + asset + '\',\'' + assetFirst + '\')">수정</a>';
							str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delAssets(\'' + asset + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + assetObj['@name'] + '\')">삭제</a>';
							str += '		<font size="2" style="margin-left: 10px;">asset</font>';
							str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="assetKey" value=\'' + assetObj['@value'] + '\'/>';
							str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="assetValue" value=\'' + assetObj['@name'] + '\'/>';
							str += '	</li>';
							str += '</ul>';
							str += '<ul style="display:inline; list-style:none; display:none;padding: 0px;margin: 0px;" id=\'' + asset + '\'>';
							str += '	<li>';
							str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
							str += '		<font size="2" style="margin-left: 10px;">asset</font>';
							str += '		<select id="getAddService" name="getAddService" style="margin-bottom: 0px;" type="text">';
							str += '			<option value="additionalhardware">additionalhardware</option>';
							str += '			<option value="address1">address1</option>';
							str += '			<option value="address2">address2</option>';
							str += '			<option value="admin">admin</option>';
							str += '			<option value="assetNumber">assetNumber</option>';
							str += '			<option value="autoenable">autoenable</option>';
							str += '			<option value="building">building</option>';
							str += '			<option value="category">category</option>';
							str += '			<option value="circuitId">circuitId</option>';
							str += '			<option value="city">city</option>';
							str += '			<option value="class">class</option>';
							str += '			<option value="comment">comment</option>';
							str += '			<option value="connection">connection</option>';
							str += '			<option value="cpu">cpu</option>';
							str += '			<option value="dateInstalled">dateInstalled</option>';
							str += '			<option value="department">department</option>';
							str += '			<option value="description">description</option>';
							str += '			<option value="displayCategory">displayCategory</option>';
							str += '			<option value="division">division</option>';
							str += '			<option value="enable">enable</option>';
							str += '			<option value="floor">floor</option>';
							str += '			<option value="hdd1">hdd1</option>';
							str += '			<option value="hdd2">hdd2</option>';
							str += '			<option value="hdd3">hdd3</option>';
							str += '			<option value="hdd4">hdd4</option>';
							str += '			<option value="hdd5">hdd5</option>';
							str += '			<option value="hdd6">hdd6</option>';
							str += '			<option value="id">id</option>';
							str += '			<option value="inputpower">inputpower</option>';
							str += '			<option value="lastModifiedBy">lastModifiedBy</option>';
							str += '			<option value="lastModifiedDate">lastModifiedDate</option>';
							str += '			<option value="lease">lease</option>';
							str += '			<option value="leaseExpires">leaseExpires</option>';
							str += '			<option value="maintContractExpiration">maintContractExpiration</option>';
							str += '			<option value="maintContractNumber">maintContractNumber</option>';
							str += '			<option value="maintcontract">maintcontract</option>';
							str += '			<option value="managedObjectInstance">managedObjectInstance</option>';
							str += '			<option value="managedObjectType">managedObjectType</option>';
							str += '			<option value="manufacturer">manufacturer</option>';
							str += '			<option value="modelNumber">modelNumber</option>';
							str += '			<option value="node">node</option>';
							str += '			<option value="notifyCategory">notifyCategory</option>';
							str += '			<option value="numpowersupplies">numpowersupplies</option>';
							str += '			<option value="operatingSystem">operatingSystem</option>';
							str += '			<option value="password">password</option>';
							str += '			<option value="pollerCategory">pollerCategory</option>';
							str += '			<option value="port">port</option>';
							str += '			<option value="rack">rack</option>';
							str += '			<option value="rackunitheight">rackunitheight</option>';
							str += '			<option value="ram">ram</option>';
							str += '			<option value="region">region</option>';
							str += '			<option value="room">room</option>';
							str += '			<option value="serialNumber">serialNumber</option>';
							str += '			<option value="slot">slot</option>';
							str += '			<option value="snmpcommunity">snmpcommunity</option>';
							str += '			<option value="state">state</option>';
							str += '			<option value="storagectrl">storagectrl</option>';
							str += '			<option value="supportPhone">supportPhone</option>';
							str += '			<option value="thresholdCategory">thresholdCategory</option>';
							str += '			<option value="username">username</option>';
							str += '			<option value="vendor">vendor</option>';
							str += '			<option value="vendorAssetNumber">vendorAssetNumber</option>';
							str += '			<option value="vendorFax">vendorFax</option>';
							str += '			<option value="vendorPhone">vendorPhone</option>';
							str += '			<option value="zip">zip</option>';
							str += '		</select>';
							str += '		<input style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" name="assetValue" value=""/>';
							str += '		<a type="button" class="btn btn-primary btn-mini" onclick="saveAssets(\'' + asset + '\',\'' + nodeObj[i]["@foreign-id"] + '\',\'' + assetObj['@name'] + '\',\'' + assetFirst + '\'))">저장</a>';
							str += '		<a type="button" class="btn btn-danger btn-mini" onclick="cancelAsset(\'' + asset + '\',\'' + assetFirst + '\')">취소</a>';
							str += '	</li>';
							str += '</ul>';
					}
				}
			}
		}else{
			var inputForeignId = Math.floor(Math.random() * Math.pow(10,13));
			var trId = Math.floor(Math.random() * Math.pow(10,12));
			var interfaceObj = nodeObj['interface'];
			var categoryObj = nodeObj['category'];
			var assetObj = nodeObj['asset'];
				str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + trId + '\'>';
				str += '	<li>';
				str += '  		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="modifyRequisitionPopList(\'' + nodeObj + '\',\'' + trId + '\',\'' + nodeObj["@node-label"] + '\',\'' + inputForeignId + '\')">수정</a>';
				str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="delRequisitionPopList(\'' + nodeObj["@foreign-id"] + '\')">삭제</a>';
				str += '		<font size="2" style="margin-left: 10px;">Node</font>';
				str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" value=\'' + nodeObj["@node-label"] + '\'/>';
				str += '		<font size="2" style="margin-left: 10px;">ForeignId</font>';
				str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" value=\'' + nodeObj["@foreign-id"] + '\'/>';
				str += '		<font size="2" style="margin-left: 10px;">Site</font>';
				str += '		<input style="border:0; background: lightgrey; width: 80px; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" value=\'' + nodeObj["@building"] + '\'/>';
				str += '		<a href="#" onclick="javascript:addInterface(\'' + trId + '\',\'' + nodeObj["@building"] + '\',\'' + nodeObj["@foreign-id"] + '\')"><font size="1" color="black">[Add Interface]&nbsp;</font></a>';
				str += '		<a href="#" onclick="javascript:addNodeCategory(\'' + trId + '\',\'' + nodeObj["@foreign-id"] + '\')"><font size="1" color="black">[Add Node Category]&nbsp;</font></a>';
				str += '		<a href="#" onclick="javascript:addNodeAsset(\'' + trId + '\',\'' + nodeObj["@foreign-id"] + '\')"><font size="1" color="black">[Add Node Asset]</font></a>';
				str += '	</li>';
				str += '</ul>';
				str += '<ul style="display:none; list-style:none; padding: 0px;margin: 0px;" id=\'' + inputForeignId + '\'>';
				str += '	<li>';
				str += '		<font size="2" style="margin-left: 10px;">Node</font>';
				str += '		<input name="editRequisitionNodeLabelFirst" style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" placeholder=\'' + nodeObj["@node-label"] + '\'/>';
				str += '		<font size="2" style="margin-left: 10px;">ForeignId</font>';
				str += '		<input name="editRequisitionForeignIdFirst" style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" placeholder=\'' + nodeObj["@foreign-id"] + '\'/>';
				str += '		<font size="2" style="margin-left: 10px;">Site</font>';
				str += '		<input name="editRequisitionBuildingFirst" type="text" style="height: 13px;margin-left: 0px;margin-bottom: 0px; width: 80px;" placeholder=\'' + nodeObj["@building"] + '\' style="width: 80px"/>';
				str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="updateSaveRequisitionPopList(\'' + foreignSource + '\',\'' + inputForeignId + '\',\'' + nodeObj["@foreign-id"] + '\')">저장</a>';
				str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="returnCancelRequisitionPopList(\'' + jsonObj + '\',\'' + foreignSource + '\',\'' + trId + '\',\'' + nodeObj["@node-label"] + '\',\'' + inputForeignId + '\')">취소</a>';
				str += '	</li>';
				str += '</ul>';
			if(nodeObj['interface'] != undefined){
				if(nodeObj['interface']['@descr'] == undefined){
					for(var i in interfaceObj){
						var interfaces = Math.floor(Math.random() * Math.pow(10,15));
						var interfacesFirst = Math.floor(Math.random() * Math.pow(10,15));
							$("#hiddenForm input[name=interfaces]").val(interfaces);
							$("#hiddenForm input[name=interfacesFirst]").val(interfacesFirst);
							str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + interfacesFirst +'\'>';
							str += '	<li>';
							str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
							str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifyInterfaces(\'' + interfaces + '\',\'' + interfacesFirst + '\')">수정</a>';
							str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delInterface(\'' + interfaces + '\',\'' + nodeObj["@foreign-id"] + '\',\'' + interfaceObj[i]['@ip-addr'] + '\')">삭제</a>';
							str += '		<font size="2" style="margin-left: 10px;">IP Interface</font>';
							str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="ipInterface" value=\'' + interfaceObj[i]['@ip-addr'] + '\'/>';
							str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">Description</font>';
							str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="description" value=\'' + interfaceObj[i]['@descr'] + '\'/>';
							str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">SNMP Primary</font>';
							str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;width: 50px;" readonly="readonly" type="text" name="snmpPrimary" value=\'' + interfaceObj[i]['@snmp-primary'] + '\'/>';
							str += '		<a href="#" onclick="javascript:addService(\'' + interfacesFirst +'\',\'' + nodeObj["@foreign-id"] + '\',\'' + interfaceObj[i]['@ip-addr'] + '\')"><font size="1" color="black">Add Service</font></a>';
							str += '	</li>';
							str += '</ul>';
							str += '<ul style="display:inline;display:none;padding: 0px;margin: 0px;" id=\'' + interfaces + '\'>';
							str += '	<li>';
							str += '		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
							str += '		<font size="2" style="margin-left: 10px;">IP Interface</font>';
							str += '		<input name="ipInterface" type="text" style="height: 13px;margin-left: 0px;margin-bottom: 0px;" placeholder="127.0.0.1"/>';
							str += '		<font size="2" style="margin-left: 10px;">Description</font>';
							str += '		<input name="description" type="text" style="height: 13px;margin-left: 0px;margin-bottom: 0px;"/>';
							str += '		<font size="2" style="margin-left: 10px;">SNMP Primary</font>';
							str += '		<select id="snmpPrimary" name="snmpPrimary" style="width: 60px;margin-bottom: 0px;" type="text">';
							str += '			<option value="N">N</option>';
							str += '			<option value="S">S</option>';
							str += '			<option value="P" selected="selected">P</option>';
							str += '		</select>';
							str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="saveIpInterfaces(\'' + foreignSource + '\',\'' + nodeObj["@foreign-id"] + '\',\'' + interfaces + '\',\'' + interfaceObj[i]['@ip-addr'] + '\',\'' + interfaceObj[i]['@descr'] + '\',\'' + interfaceObj[i]['@snmp-primary'] + '\')">저장</a>';
							str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelInterfaces(\'' + interfaces + '\',\'' + interfacesFirst+'\')">취소</a>';
							str += '	</li>';
							str += '</ul>';
							if(interfaceObj[i]["monitored-service"] != undefined){
								if(interfaceObj[i]["monitored-service"]['@service-name'] == undefined){
									for(var q in interfaceObj[i]["monitored-service"]){
										var services = Math.floor(Math.random() * Math.pow(10,15));
										var servicesFirst = Math.floor(Math.random() * Math.pow(10,15));
											str += '<ul style="padding: 0px;margin: 0px;" id=\'' + servicesFirst +'\'>';
											str += '	&nbsp;&nbsp;&nbsp;';
											str += '	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
											str += '	<a type="button" class="btn btn-primary btn-mini" onclick="modifyServices(\'' + services + '\',\'' + servicesFirst+'\')">수정</a>';
											str += '	<a type="button" class="btn btn-danger btn-mini" onclick="delService(\'' + services + '\',\'' + nodeObj["@foreign-id"] + '\',\'' + interfaceObj[i]['@ip-addr'] + '\',\'' + interfaceObj[i]["monitored-service"][q]['@service-name'] + '\')">삭제</a>';
											str += '	<font size="2" style="margin-left: 10px;">Service</font>';
											str += '	<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="description" value=\'' + interfaceObj[i]["monitored-service"][q]['@service-name'] + '\'/>';
											str += '</ul>';
											str += '<ul style="display:inline; list-style:none; display:none;padding: 0px;margin: 0px;" id=\'' + services + '\'>';
											str += '	<li>';
											str += '		&nbsp;&nbsp;&nbsp;';
											str += '		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
											str += '		<font size="2" style="margin-left: 10px;">Service</font>';
											str += '		<select id="getAddService" name="getAddService" style="margin-bottom: 0px;" type="text">';
											str += '			<option value="DNS" selected="selected">DNS</option>';
											str += '			<option value="Dell-OpenManage">Dell-OpenManage</option>';
											str += '			<option value="FTP">FTP</option>';
											str += '			<option value="HP Insight Manager">HP Insight Manager</option>';
											str += '			<option value="HTTP">HTTP</option>';
											str += '			<option value="HTTP-8000">HTTP-8000</option>';
											str += '			<option value="HTTP-8000">HTTP-8000</option>';
											str += '			<option value="HTTPS">HTTPS</option>';
											str += '			<option value="HypericAgent">HypericAgent</option>';
											str += '			<option value="HypericHQ">HypericHQ</option>';
											str += '			<option value="ICMP">ICMP</option>';
											str += '			<option value="IMAP">IMAP</option>';
											str += '			<option value="LDAP">LDAP</option>';
											str += '			<option value="MSExchange">MSExchange</option>';
											str += '			<option value="MySQL">MySQL</option>';
											str += '			<option value="NRPE">NRPE</option>';
											str += '			<option value="NRPE-NoSSL">NRPE-NoSSL</option>';
											str += '			<option value="New Detector">New Detector</option>';
											str += '			<option value="OpenNMS-JVM">OpenNMS-JVM</option>';
											str += '			<option value="Oracle">Oracle</option>';
											str += '			<option value="POP3">POP3</option>';
											str += '			<option value="Postgres">Postgres</option>';
											str += '			<option value="Router">Router</option>';
											str += '			<option value="SMTP">SMTP</option>';
											str += '			<option value="SNMP">SNMP</option>';
											str += '			<option value="SQLServer">SQLServer</option>';
											str += '			<option value="SSH">SSH</option>';
											str += '			<option value="SVC">SVC</option>';
											str += '			<option value="StrafePing">StrafePing</option>';
											str += '			<option value="Telnet">Telnet</option>';
											str += '			<option value="Windows-Task-Scheduler">Windows-Task-Scheduler</option>';
											str += '		</select>';
											str += '		<a type="button" class="btn btn-primary btn-mini" onclick="saveGetAddService(\'' + services + '\',\'' + nodeObj["@foreign-id"] + '\',\'' + interfaceObj[i]['@ip-addr'] + '\',\'' + servicesFirst + '\',\'' + interfaceObj[i]["monitored-service"][q]['@service-name'] + '\')">저장</a>';
											str += '		<a type="button" class="btn btn-danger btn-mini" onclick="cancelServices(\'' + services + '\',\'' + servicesFirst+'\')">취소</a>';
											str += '	</li>';
											str += '</ul>';
									}
								}else{
									var service = Math.floor(Math.random() * Math.pow(10,15));
									var serviceFirst =  Math.floor(Math.random() * Math.pow(10,15));
										str += '<ul style="padding: 0px;margin: 0px;" id=\'' + serviceFirst +'\'>';
										str += '	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
										str += '	<a type="button" class="btn btn-primary btn-mini" onclick="modifyService(\'' + service + '\',\'' + serviceFirst+'\')">수정</a>';
										str += '	<a type="button" class="btn btn-danger btn-mini" onclick="delService(\'' + service + '\',\'' + nodeObj["@foreign-id"] + '\',\'' + interfaceObj[i]['@ip-addr'] + '\',\'' + interfaceObj[i]["monitored-service"]['@service-name'] + '\')">삭제</a>';
										str += '	<font size="2" style="margin-left: 10px;">Service</font>';
										str += '	<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="service" value=\'' + interfaceObj[i]["monitored-service"]['@service-name'] + '\'/>';
										str += '</ul>';
										str += '<ul style="display:inline; list-style:none; padding: 0px;display:none;margin: 0px;" id=\'' + service + '\'>';
										str += '	<li>';
										str += '		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
										str += '		<font size="2" style="margin-left: 10px;">Service</font>';
										str += '		<select id="getAddService" name="getAddService" style="margin-bottom: 0px;" type="text">';
										str += '			<option value="DNS" selected="selected">DNS</option>';
										str += '			<option value="Dell-OpenManage">Dell-OpenManage</option>';
										str += '			<option value="FTP">FTP</option>';
										str += '			<option value="HP Insight Manager">HP Insight Manager</option>';
										str += '			<option value="HTTP">HTTP</option>';
										str += '			<option value="HTTP-8000">HTTP-8000</option>';
										str += '			<option value="HTTP-8000">HTTP-8000</option>';
										str += '			<option value="HTTPS">HTTPS</option>';
										str += '			<option value="HypericAgent">HypericAgent</option>';
										str += '			<option value="HypericHQ">HypericHQ</option>';
										str += '			<option value="ICMP">ICMP</option>';
										str += '			<option value="IMAP">IMAP</option>';
										str += '			<option value="LDAP">LDAP</option>';
										str += '			<option value="MSExchange">MSExchange</option>';
										str += '			<option value="MySQL">MySQL</option>';
										str += '			<option value="NRPE">NRPE</option>';
										str += '			<option value="NRPE-NoSSL">NRPE-NoSSL</option>';
										str += '			<option value="New Detector">New Detector</option>';
										str += '			<option value="OpenNMS-JVM">OpenNMS-JVM</option>';
										str += '			<option value="Oracle">Oracle</option>';
										str += '			<option value="POP3">POP3</option>';
										str += '			<option value="Postgres">Postgres</option>';
										str += '			<option value="Router">Router</option>';
										str += '			<option value="SMTP">SMTP</option>';
										str += '			<option value="SNMP">SNMP</option>';
										str += '			<option value="SQLServer">SQLServer</option>';
										str += '			<option value="SSH">SSH</option>';
										str += '			<option value="SVC">SVC</option>';
										str += '			<option value="StrafePing">StrafePing</option>';
										str += '			<option value="Telnet">Telnet</option>';
										str += '			<option value="Windows-Task-Scheduler">Windows-Task-Scheduler</option>';
										str += '		</select>';
										str += '		<a type="button" class="btn btn-primary btn-mini" onclick="saveGetAddService(\'' + service + '\',\'' + nodeObj["@foreign-id"] + '\',\'' + interfaceObj[i]['@ip-addr'] + '\',\'' + serviceFirst + '\',\'' + interfaceObj[i]["monitored-service"]['@service-name'] + '\')">저장</a>';
										str += '		<a type="button" class="btn btn-danger btn-mini" onclick="cancelService(\'' + service + '\',\'' + serviceFirst+'\')">취소</a>';
										str += '	</li>';
										str += '</ul>';
								}
							}
					}
				}else{
					var interface = Math.floor(Math.random() * Math.pow(10,15));
						$("#hiddenForm input[name=interface]").val(interface);
						var interfaceFirst = Math.floor(Math.random() * Math.pow(10,15));
						$("#hiddenForm input[name=interfacesFirst]").val(interfaceFirst);
						str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + interfaceFirst + '\'>';
						str += '	<li>';
						str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
						str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifyInterface(\'' + interface + '\',\'' + interfaceFirst+'\')">수정</a>';
						str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delInterface(\'' + interface + '\',\'' + nodeObj["@foreign-id"] + '\',\'' + interfaceObj['@ip-addr'] + '\')">삭제</a></td>';
						str += '		<font size="2" style="margin-left: 10px;">IP Interface</font>';
						str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="ipInterface" value=\'' + interfaceObj['@ip-addr'] + '\'/>';
						str += '		<font size="2" style="margin-left: 10px;">Description</font>';
						str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="description" value=\'' + interfaceObj['@descr'] + '\'/>';
						str += '		<font size="2" style="margin-left: 10px;">SNMP Primary</font>';
						str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;width: 50px;" readonly="readonly" type="text" name="snmpPrimary" value=\'' + interfaceObj['@snmp-primary'] + '\'>';
						str += '		<a href="#" onclick="javascript:addService(\'' + interfaceFirst +'\',\'' + nodeObj["@foreign-id"] + '\',\'' + interfaceObj['@ip-addr'] + '\')"><font size="1" color="black">Add Service</font></a>';
						str += '	</li>';
						str += '</ul>';
						str += '<ul style="display:inline;display:none;padding: 0px;margin: 0px;" id=\'' + interface + '\'>';
						str += '	<li>';
						str += '		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
						str += '		<font size="2" style="margin-left: 10px;">IP Interface</font>';
						str += '		<input name="ipInterface" type="text" style="height: 13px;margin-left: 0px;margin-bottom: 0px;" placeholder="127.0.0.1"/>';
						str += '		<font size="2" style="margin-left: 10px;">Description</font>';
						str += '		<input name="description" type="text" style="height: 13px;margin-left: 0px;margin-bottom: 0px;"/>';
						str += '		<font size="2" style="margin-left: 10px;">SNMP Primary</font>';
						str += '		<select id="snmpPrimary" name="snmpPrimary" style="width: 60px;margin-bottom: 0px;" type="text">';
						str += '			<option value="N">N</option>';
						str += '			<option value="S">S</option>';
						str += '			<option value="P" selected="selected">P</option>';
						str += '		</select>';
						str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="saveIpInterfaces(\'' + foreignSource + '\',\'' + nodeObj["@foreign-id"] + '\',\'' + interface + '\',\'' + interfaceObj['@ip-addr'] + '\',\'' + interfaceObj['@descr'] + '\',\'' + interfaceObj['@snmp-primary'] + '\')">저장</a>';
						str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelInterface(\'' + interface + '\',\'' + interfaceFirst+'\')">취소</a>';
						str += '	</li>';
						str += '</ul>';
						if(interfaceObj["monitored-service"] != undefined){
							if(interfaceObj["monitored-service"]['@service-name'] == undefined){
								for(var q in interfaceObj["monitored-service"]){
									var services = Math.floor(Math.random() * Math.pow(10,15));
									var servicesFirst = Math.floor(Math.random() * Math.pow(10,15));
										str += '<ul style="padding: 0px;margin: 0px;" id=\'' + servicesFirst +'\'>';
										str += '	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
										str += '	<a type="button" class="btn btn-primary btn-mini" onclick="modifyServices(\'' + services + '\',\'' + servicesFirst+'\')">수정</a>';
										str += '	<a type="button" class="btn btn-danger btn-mini" onclick="delService(\'' + services + '\',\'' + nodeObj["@foreign-id"] + '\',\'' + interfaceObj['@ip-addr'] + '\',\'' + interfaceObj["monitored-service"][q]['@service-name'] + '\')">삭제</a>';
										str += '	<font size="2" style="margin-left: 10px;">Service</font>';
										str += '	<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="description" value=\'' + interfaceObj["monitored-service"][q]['@service-name'] + '\'/>';
										str += '</ul>';
										str += '<ul style="display:inline; list-style:none; display:none;padding: 0px;margin: 0px;" id=\'' + services + '\'>';
										str += '	<li>';
										str += '		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
										str += '		<font size="2" style="margin-left: 10px;">Service</font>';
										str += '		<select id="getAddService" name="getAddService" style="margin-bottom: 0px;" type="text">';
										str += '			<option value="DNS" selected="selected">DNS</option>';
										str += '			<option value="Dell-OpenManage">Dell-OpenManage</option>';
										str += '			<option value="FTP">FTP</option>';
										str += '			<option value="HP Insight Manager">HP Insight Manager</option>';
										str += '			<option value="HTTP">HTTP</option>';
										str += '			<option value="HTTP-8000">HTTP-8000</option>';
										str += '			<option value="HTTP-8000">HTTP-8000</option>';
										str += '			<option value="HTTPS">HTTPS</option>';
										str += '			<option value="HypericAgent">HypericAgent</option>';
										str += '			<option value="HypericHQ">HypericHQ</option>';
										str += '			<option value="ICMP">ICMP</option>';
										str += '			<option value="IMAP">IMAP</option>';
										str += '			<option value="LDAP">LDAP</option>';
										str += '			<option value="MSExchange">MSExchange</option>';
										str += '			<option value="MySQL">MySQL</option>';
										str += '			<option value="NRPE">NRPE</option>';
										str += '			<option value="NRPE-NoSSL">NRPE-NoSSL</option>';
										str += '			<option value="New Detector">New Detector</option>';
										str += '			<option value="OpenNMS-JVM">OpenNMS-JVM</option>';
										str += '			<option value="Oracle">Oracle</option>';
										str += '			<option value="POP3">POP3</option>';
										str += '			<option value="Postgres">Postgres</option>';
										str += '			<option value="Router">Router</option>';
										str += '			<option value="SMTP">SMTP</option>';
										str += '			<option value="SNMP">SNMP</option>';
										str += '			<option value="SQLServer">SQLServer</option>';
										str += '			<option value="SSH">SSH</option>';
										str += '			<option value="SVC">SVC</option>';
										str += '			<option value="StrafePing">StrafePing</option>';
										str += '			<option value="Telnet">Telnet</option>';
										str += '			<option value="Windows-Task-Scheduler">Windows-Task-Scheduler</option>';
										str += '		</select>';
										str += '		<a type="button" class="btn btn-primary btn-mini" onclick="saveGetAddService(\'' + services + '\',\'' + nodeObj["@foreign-id"] + '\',\'' + interfaceObj['@ip-addr'] + '\',\'' + servicesFirst + '\',\'' + interfaceObj["monitored-service"][q]['@service-name'] + '\')">저장</a>';
										str += '		<a type="button" class="btn btn-danger btn-mini" onclick="cancelServices(\'' + services + '\',\'' + servicesFirst+'\')">취소</a>';
										str += '	</li>';
										str += '</ul>';
								}
							}else{
								var service = Math.floor(Math.random() * Math.pow(10,15));
								var serviceFirst =  Math.floor(Math.random() * Math.pow(10,15));
									str += '<ul style="padding: 0px;margin: 0px;" id=\'' + serviceFirst +'\'>';
									str += '	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
									str += '	<a type="button" class="btn btn-primary btn-mini" onclick="modifyService(\'' + service + '\',\'' + serviceFirst+'\')">수정</a>';
									str += '	<a type="button" class="btn btn-danger btn-mini" onclick="delService(\'' + service + '\',\'' + nodeObj["@foreign-id"] + '\',\'' + interfaceObj['@ip-addr'] + '\',\'' + interfaceObj["monitored-service"]['@service-name'] + '\')">삭제</a>';
									str += '	<font size="2" style="margin-left: 10px;">Service</font>';
									str += '	<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="service" value=\'' + interfaceObj["monitored-service"]['@service-name'] + '\'/>';
									str += '</ul>';
									str += '<ul style="display:inline; list-style:none; padding: 0px;display:none;margin: 0px;" id=\'' + service + '\'>';
									str += '	<li>';
									str += '		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●';
									str += '		<font size="2" style="margin-left: 10px;">Service</font>';
									str += '		<select id="getAddService" name="getAddService" style="margin-bottom: 0px;" type="text">';
									str += '			<option value="DNS" selected="selected">DNS</option>';
									str += '			<option value="Dell-OpenManage">Dell-OpenManage</option>';
									str += '			<option value="FTP">FTP</option>';
									str += '			<option value="HP Insight Manager">HP Insight Manager</option>';
									str += '			<option value="HTTP">HTTP</option>';
									str += '			<option value="HTTP-8000">HTTP-8000</option>';
									str += '			<option value="HTTP-8000">HTTP-8000</option>';
									str += '			<option value="HTTPS">HTTPS</option>';
									str += '			<option value="HypericAgent">HypericAgent</option>';
									str += '			<option value="HypericHQ">HypericHQ</option>';
									str += '			<option value="ICMP">ICMP</option>';
									str += '			<option value="IMAP">IMAP</option>';
									str += '			<option value="LDAP">LDAP</option>';
									str += '			<option value="MSExchange">MSExchange</option>';
									str += '			<option value="MySQL">MySQL</option>';
									str += '			<option value="NRPE">NRPE</option>';
									str += '			<option value="NRPE-NoSSL">NRPE-NoSSL</option>';
									str += '			<option value="New Detector">New Detector</option>';
									str += '			<option value="OpenNMS-JVM">OpenNMS-JVM</option>';
									str += '			<option value="Oracle">Oracle</option>';
									str += '			<option value="POP3">POP3</option>';
									str += '			<option value="Postgres">Postgres</option>';
									str += '			<option value="Router">Router</option>';
									str += '			<option value="SMTP">SMTP</option>';
									str += '			<option value="SNMP">SNMP</option>';
									str += '			<option value="SQLServer">SQLServer</option>';
									str += '			<option value="SSH">SSH</option>';
									str += '			<option value="SVC">SVC</option>';
									str += '			<option value="StrafePing">StrafePing</option>';
									str += '			<option value="Telnet">Telnet</option>';
									str += '			<option value="Windows-Task-Scheduler">Windows-Task-Scheduler</option>';
									str += '		</select>';
									str += '		<a type="button" class="btn btn-primary btn-mini" onclick="saveGetAddService(\'' + service + '\',\'' + nodeObj["@foreign-id"] + '\',\'' + interfaceObj['@ip-addr'] + '\',\'' + serviceFirst + '\',\'' + interfaceObj["monitored-service"]['@service-name'] + '\')">저장</a>';
									str += '		<a type="button" class="btn btn-danger btn-mini" onclick="cancelService(\'' + service + '\',\'' + serviceFirst+'\')">취소</a>';
									str += '	</li>';
									str += '</ul>';
							}
						}
				}
			}
			if(nodeObj['category'] != undefined){
				if(nodeObj['category']['@name'] == undefined){
					for(var i in categoryObj){
						var categories = Math.floor(Math.random() * Math.pow(10,15));
						var categoriesFirst = Math.floor(Math.random() * Math.pow(10,15));
							$("#hiddenForm input[name=categories]").val(categories);
							$("#hiddenForm input[name=categoriesFirst]").val(categoriesFirst);
							str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + categoriesFirst + '\'>';
							str += '	<li>';
							str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
							str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifyCategories(\'' + categories + '\',\'' + categoriesFirst+ '\')">수정</a>';
							str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delNodeCategory(\'' + categories + '\',\'' + nodeObj["@foreign-id"] + '\',\'' + categoryObj[i]['@name'] + '\')">삭제</a>';
							str += '		<font size="2" style="margin-left: 10px;">Node Category</font>';
							str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="categories" value=\'' + categoryObj[i]['@name'] + '\'/>';
							str += '	</li>';
							str += '</ul>';
							str += '<ul style="display:inline; list-style:none; display:none;padding: 0px;margin: 0px;" id=\'' + categories + '\'>';
							str += '	<li>';
							str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●/font>';
							str += '		<font size="2" style="margin-left: 10px;">Node Category</font>';
							str += '		<select id="getAddService" name="getAddService" style="margin-bottom: 0px;" type="text">';
							str += '			<option value="Development" selected="selected">Development</option>';
							str += '			<option value="Production">Production</option>';
							str += '			<option value="Routers">Routers</option>';
							str += '			<option value="Servers">Servers</option>';
							str += '			<option value="Switches">Switches</option>';
							str += '			<option value="Test">Test</option>';
							str += '		</select>';
							str += '		<a type="button" class="btn btn-primary btn-mini" onclick="saveCategory(\'' + categories + '\',\'' + nodeObj["@foreign-id"] + '\',\'' + categoriesFirst + '\',\'' + categoryObj['@name'] + '\')">저장</a>';
							str += '		<a type="button" class="btn btn-danger btn-mini" onclick="cancelCategories(\'' + categories + '\',\'' + categoriesFirst+ '\')">취소</a>';
							str += '	</li>';
							str += '</ul>';
					}
				}else{
					var category = Math.floor(Math.random() * Math.pow(10,15));
					var categoryFirst = Math.floor(Math.random() * Math.pow(10,15));
						$("#hiddenForm input[name=category]").val(category);
						$("#hiddenForm input[name=categoryFirst]").val(categoryFirst);
						str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + categoryFirst + '\'>';
						str += '	<li>';
						str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
						str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifyCategory(\'' + category + '\',\'' + categoryFirst + '\')">수정</a>';
						str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delNodeCategory(\'' + category + '\',\'' + nodeObj["@foreign-id"] + '\',\'' + categoryObj['@name'] + '\')">삭제</a>';
						str += '		<font size="2" style="margin-left: 10px;">Node Category</font>';
						str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="category" value=\'' + categoryObj['@name'] + '\'/>';
						str += '	</li>';
						str += '</ul>';
						str += '<ul style="display:inline; list-style:none; display:none;padding: 0px;margin: 0px;" id=\'' + category + '\'>';
						str += '	<li>';
						str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
						str += '		<font size="2" style="margin-left: 10px;">Node Category</font>';
						str += '		<select id="getAddService" name="getAddService" style="margin-bottom: 0px;" type="text">';
						str += '			<option value="Development" selected="selected">Development</option>';
						str += '			<option value="Production">Production</option>';
						str += '			<option value="Routers">Routers</option>';
						str += '			<option value="Servers">Servers</option>';
						str += '			<option value="Switches">Switches</option>';
						str += '			<option value="Test">Test</option>';
						str += '		</select>';
						str += '		<a type="button" class="btn btn-primary btn-mini" onclick="saveCategory(\'' + category + '\',\'' + nodeObj["@foreign-id"] + '\',,\'' + categoryFirst + '\',\'' + categoryObj['@name'] + '\')">저장</a>';
						str += '		<a type="button" class="btn btn-danger btn-mini" onclick="cancelCategory(\'' + category + '\',\'' + categoryFirst + '\')">취소</a>';
						str += '	</li>';
						str += '</ul>';
				}
			}
			if(nodeObj['asset'] != undefined){
				if(nodeObj['asset']['@name'] == undefined){
					for(var i in assetObj){
						var assets = Math.floor(Math.random() * Math.pow(10,15));
						var assetsFirst = Math.floor(Math.random() * Math.pow(10,15));
							$("#hiddenForm input[name=assets]").val(assets);
							$("#hiddenForm input[name=assetsFirst]").val(assetsFirst);
							str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + assetsFirst + '\'>';
							str += '	<li>';
							str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
							str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifyAssets(\'' + assets + '\',\'' + assetsFirst + '\')">수정</a>';
							str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delAssets(\'' + assets + '\',\'' + nodeObj["@foreign-id"] + '\',\'' + assetObj[i]['@name'] + '\')">삭제</a>';
							str += '		<font size="2" style="margin-left: 10px;">asset</font>';
							str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="assetsKey" value=\'' + assetObj[i]['@value'] + '\'/>';
							str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="assetsValue" value=\'' + assetObj[i]['@name'] + '\'/>';
							str += '	</li>';
							str += '</ul>';
							str += '<ul style="display:inline; list-style:none; display:none;padding: 0px;margin: 0px;" id=\'' + assets + '\'>';
							str += '	<li>';
							str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
							str += '		<font size="2" style="margin-left: 10px;">asset</font>';
							str += '		<select id="getAddService" name="getAddService" style="margin-bottom: 0px;" type="text">';
							str += '			<option value="additionalhardware">additionalhardware</option>';
							str += '			<option value="address1">address1</option>';
							str += '			<option value="address2">address2</option>';
							str += '			<option value="admin">admin</option>';
							str += '			<option value="assetNumber">assetNumber</option>';
							str += '			<option value="autoenable">autoenable</option>';
							str += '			<option value="building">building</option>';
							str += '			<option value="category">category</option>';
							str += '			<option value="circuitId">circuitId</option>';
							str += '			<option value="city">city</option>';
							str += '			<option value="class">class</option>';
							str += '			<option value="comment">comment</option>';
							str += '			<option value="connection">connection</option>';
							str += '			<option value="cpu">cpu</option>';
							str += '			<option value="dateInstalled">dateInstalled</option>';
							str += '			<option value="department">department</option>';
							str += '			<option value="description">description</option>';
							str += '			<option value="displayCategory">displayCategory</option>';
							str += '			<option value="division">division</option>';
							str += '			<option value="enable">enable</option>';
							str += '			<option value="floor">floor</option>';
							str += '			<option value="hdd1">hdd1</option>';
							str += '			<option value="hdd2">hdd2</option>';
							str += '			<option value="hdd3">hdd3</option>';
							str += '			<option value="hdd4">hdd4</option>';
							str += '			<option value="hdd5">hdd5</option>';
							str += '			<option value="hdd6">hdd6</option>';
							str += '			<option value="id">id</option>';
							str += '			<option value="inputpower">inputpower</option>';
							str += '			<option value="lastModifiedBy">lastModifiedBy</option>';
							str += '			<option value="lastModifiedDate">lastModifiedDate</option>';
							str += '			<option value="lease">lease</option>';
							str += '			<option value="leaseExpires">leaseExpires</option>';
							str += '			<option value="maintContractExpiration">maintContractExpiration</option>';
							str += '			<option value="maintContractNumber">maintContractNumber</option>';
							str += '			<option value="maintcontract">maintcontract</option>';
							str += '			<option value="managedObjectInstance">managedObjectInstance</option>';
							str += '			<option value="managedObjectType">managedObjectType</option>';
							str += '			<option value="manufacturer">manufacturer</option>';
							str += '			<option value="modelNumber">modelNumber</option>';
							str += '			<option value="node">node</option>';
							str += '			<option value="notifyCategory">notifyCategory</option>';
							str += '			<option value="numpowersupplies">numpowersupplies</option>';
							str += '			<option value="operatingSystem">operatingSystem</option>';
							str += '			<option value="password">password</option>';
							str += '			<option value="pollerCategory">pollerCategory</option>';
							str += '			<option value="port">port</option>';
							str += '			<option value="rack">rack</option>';
							str += '			<option value="rackunitheight">rackunitheight</option>';
							str += '			<option value="ram">ram</option>';
							str += '			<option value="region">region</option>';
							str += '			<option value="room">room</option>';
							str += '			<option value="serialNumber">serialNumber</option>';
							str += '			<option value="slot">slot</option>';
							str += '			<option value="snmpcommunity">snmpcommunity</option>';
							str += '			<option value="state">state</option>';
							str += '			<option value="storagectrl">storagectrl</option>';
							str += '			<option value="supportPhone">supportPhone</option>';
							str += '			<option value="thresholdCategory">thresholdCategory</option>';
							str += '			<option value="username">username</option>';
							str += '			<option value="vendor">vendor</option>';
							str += '			<option value="vendorAssetNumber">vendorAssetNumber</option>';
							str += '			<option value="vendorFax">vendorFax</option>';
							str += '			<option value="vendorPhone">vendorPhone</option>';
							str += '			<option value="zip">zip</option>';
							str += '		</select>';
							str += '		<input style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" name="assetsValue" value=""/>';
							str += '		<a type="button" class="btn btn-primary btn-mini" onclick="saveAssets(\'' + assets + '\',\'' + nodeObj["@foreign-id"] + '\',\'' + assetObj[i]['@name'] + '\',\'' + assetsFirst + '\'))">저장</a>';
							str += '		<a type="button" class="btn btn-danger btn-mini" onclick="cancelAssets(\'' + assets + '\',\'' + assetsFirst + '\')">취소</a>';
							str += '	</li>';
							str += '</ul>';
					}
				}else{
					var asset = Math.floor(Math.random() * Math.pow(10,15));
					var assetFirst = Math.floor(Math.random() * Math.pow(10,15));
						$("#hiddenForm input[name=assets]").val(asset);
						$("#hiddenForm input[name=assetsFirst]").val(assetFirst);
						str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + assetFirst + '\'>';
						str += '	<li>';
						str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
						str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifyAsset(\'' + asset + '\',\'' + assetFirst + '\')">수정</a>';
						str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delAssets(\'' + asset + '\',\'' + nodeObj["@foreign-id"] + '\',\'' + assetObj['@name'] + '\')">삭제</a>';
						str += '		<font size="2" style="margin-left: 10px;">asset</font>';
						str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="assetKey" value=\'' + assetObj['@value'] + '\'/>';
						str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="assetValue" value=\'' + assetObj['@name'] + '\'/>';
						str += '	</li>';
						str += '</ul>';
						str += '<ul style="display:inline; list-style:none; display:none;padding: 0px;margin: 0px;" id=\'' + asset + '\'>';
						str += '	<li>';
						str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
						str += '		<font size="2" style="margin-left: 10px;">asset</font>';
						str += '		<select id="getAddService" name="getAddService" style="margin-bottom: 0px;" type="text">';
						str += '			<option value="additionalhardware">additionalhardware</option>';
						str += '			<option value="address1">address1</option>';
						str += '			<option value="address2">address2</option>';
						str += '			<option value="admin">admin</option>';
						str += '			<option value="assetNumber">assetNumber</option>';
						str += '			<option value="autoenable">autoenable</option>';
						str += '			<option value="building">building</option>';
						str += '			<option value="category">category</option>';
						str += '			<option value="circuitId">circuitId</option>';
						str += '			<option value="city">city</option>';
						str += '			<option value="class">class</option>';
						str += '			<option value="comment">comment</option>';
						str += '			<option value="connection">connection</option>';
						str += '			<option value="cpu">cpu</option>';
						str += '			<option value="dateInstalled">dateInstalled</option>';
						str += '			<option value="department">department</option>';
						str += '			<option value="description">description</option>';
						str += '			<option value="displayCategory">displayCategory</option>';
						str += '			<option value="division">division</option>';
						str += '			<option value="enable">enable</option>';
						str += '			<option value="floor">floor</option>';
						str += '			<option value="hdd1">hdd1</option>';
						str += '			<option value="hdd2">hdd2</option>';
						str += '			<option value="hdd3">hdd3</option>';
						str += '			<option value="hdd4">hdd4</option>';
						str += '			<option value="hdd5">hdd5</option>';
						str += '			<option value="hdd6">hdd6</option>';
						str += '			<option value="id">id</option>';
						str += '			<option value="inputpower">inputpower</option>';
						str += '			<option value="lastModifiedBy">lastModifiedBy</option>';
						str += '			<option value="lastModifiedDate">lastModifiedDate</option>';
						str += '			<option value="lease">lease</option>';
						str += '			<option value="leaseExpires">leaseExpires</option>';
						str += '			<option value="maintContractExpiration">maintContractExpiration</option>';
						str += '			<option value="maintContractNumber">maintContractNumber</option>';
						str += '			<option value="maintcontract">maintcontract</option>';
						str += '			<option value="managedObjectInstance">managedObjectInstance</option>';
						str += '			<option value="managedObjectType">managedObjectType</option>';
						str += '			<option value="manufacturer">manufacturer</option>';
						str += '			<option value="modelNumber">modelNumber</option>';
						str += '			<option value="node">node</option>';
						str += '			<option value="notifyCategory">notifyCategory</option>';
						str += '			<option value="numpowersupplies">numpowersupplies</option>';
						str += '			<option value="operatingSystem">operatingSystem</option>';
						str += '			<option value="password">password</option>';
						str += '			<option value="pollerCategory">pollerCategory</option>';
						str += '			<option value="port">port</option>';
						str += '			<option value="rack">rack</option>';
						str += '			<option value="rackunitheight">rackunitheight</option>';
						str += '			<option value="ram">ram</option>';
						str += '			<option value="region">region</option>';
						str += '			<option value="room">room</option>';
						str += '			<option value="serialNumber">serialNumber</option>';
						str += '			<option value="slot">slot</option>';
						str += '			<option value="snmpcommunity">snmpcommunity</option>';
						str += '			<option value="state">state</option>';
						str += '			<option value="storagectrl">storagectrl</option>';
						str += '			<option value="supportPhone">supportPhone</option>';
						str += '			<option value="thresholdCategory">thresholdCategory</option>';
						str += '			<option value="username">username</option>';
						str += '			<option value="vendor">vendor</option>';
						str += '			<option value="vendorAssetNumber">vendorAssetNumber</option>';
						str += '			<option value="vendorFax">vendorFax</option>';
						str += '			<option value="vendorPhone">vendorPhone</option>';
						str += '			<option value="zip">zip</option>';
						str += '		</select>';
						str += '		<input style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" name="assetValue" value=""/>';
						str += '		<a type="button" class="btn btn-primary btn-mini" onclick="saveAssets(\'' + asset + '\',\'' + nodeObj["@foreign-id"] + '\',\'' + assetObj['@name'] + '\',\'' + assetFirst + '\'))">저장</a>';
						str += '		<a type="button" class="btn btn-danger btn-mini" onclick="cancelAsset(\'' + asset + '\',\'' + assetFirst + '\')">취소</a>';
						str += '	</li>';
						str += '</ul>';
				}
			}
		}
	}
	return str;*/
}

function getTableToEditRequisitionJsonObj(jsonObj, foreignSource, foreignId){
	var str = "";
		str += '<ul style="display:inline;" id="editRequisitionDownItemSecond">';
		str += '	<li>';
		str += '		<font size="2" style="margin-left: 10px;">Node</font>';
		str += '		<input name="editRequisitionNodeLabel" style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" placeholder="New Node"/>';
		str += '		<font size="2" style="margin-left: 10px;">ForeignId</font>';
		str += '		<input name="editRequisitionForeignId" style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" placeholder=\'' + foreignId + '\'/>';
		str += '		<font size="2" style="margin-left: 10px;">Site</font>';
		str += '		<input name="editRequisitionBuilding" type="text" style="height: 13px;width: 80px;margin-left: 0px;margin-bottom: 0px;" placeholder=\'' + foreignSource + '\'/>';
		str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="showSaveRequisitionPopList(\'' + foreignSource + '\')">저장</a>';
		str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="showCancelRequisitionPopList()">취소</a>';
		str += '	</li>';
		str += '</ul>';
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
			str += '	<td><button type="button" class="btn btn-primary" style="" title="" onclick="javascript:delRequisition(\'' + requisitionObj["@foreign-source"] + '\');">노드 그룹 삭제</button></td>';
			str += '	<td><button type="button" class="btn btn-primary" style="margin-left:-796px" title="" onclick="javascript:synRequisition(\'' + requisitionObj["@foreign-source"] +'\');">동기화</button></td>';
			str += '	<td><button type="button" class="btn btn-info" style="margin-left:-713px" data-toggle="modal" href="#editRequisitionPop" onclick="javascript:showEditRequisitionInfoDivTitle(\'' + requisitionObj["@foreign-source"] + '\')">편집</button>';
			str += "</tr>";
		}else{
			str += "<tr>";
			str += '	<td><button type="button" class="btn btn-primary" style="" title="" onclick="javascript:delRequisition(\'' + requisitionObj["@foreign-source"] + '\');">노드 그룹 삭제</button></td>';
			str += '	<td><button type="button" class="btn btn-primary" style="margin-left:-796px" title="" onclick="javascript:synRequisition(\'' + requisitionObj["@foreign-source"] +'\');">동기화</button></td>';
			str += '	<td><button type="button" class="btn btn-info" style="margin-left:-713px" data-toggle="modal" href="#editRequisitionPop" onclick="javascript:showEditRequisitionInfoDivTitle(\'' + requisitionObj["@foreign-source"] + '\')">편집</button>';
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
				str += '	<td><button type="button" class="btn btn-primary" style="" title="" onclick="javascript:delRequisition(\'' + requisitionObj[i]["@foreign-source"] + '\');">노드 그룹 삭제</button></td>';
				str += '	<td><button type="button" class="btn btn-primary" style=";margin-left:-796px" title="" onclick="javascript:synRequisition(\'' + requisitionObj[i]["@foreign-source"] +'\');">동기화</button></td>';
				str += '	<td><button type="button" class="btn btn-info" style="margin-left:-713px" data-toggle="modal" href="#editRequisitionPop" onclick="javascript:showEditRequisitionInfoDivTitle(\'' + requisitionObj[i]["@foreign-source"] + '\')">편집</button>';
				str += '</tr>';
			}else{
				str += "<tr>";
				str += '	<td><button type="button" class="btn btn-primary" style="" title="" onclick="javascript:delRequisition(\'' + requisitionObj[i]["@foreign-source"] + '\');">노드 그룹 삭제</button></td>';
				str += '	<td><button type="button" class="btn btn-primary" style=";margin-left:-796px" title="" onclick="javascript:synRequisition(\'' + requisitionObj[i]["@foreign-source"] +'\');">동기화</button></td>';
				str += '	<td><button type="button" class="btn btn-info" style="margin-left:-713px" data-toggle="modal" href="#editRequisitionPop" onclick="javascript:showEditRequisitionInfoDivTitle(\'' + requisitionObj[i]["@foreign-source"] + '\')">편집</button>';
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
		str	+= "		<h3>노드 그룹이 없습니다.</h3>";
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

/***************************************************************************************Foreign Source Name: default**********************************************************************************************/
function delSubDetector(){
	
}

function delSubPolicy(){
	
}

//=======================================================================================
function getDetectorParameter(){
	var subCancelDetector = Math.floor(Math.random() * Math.pow(10,20));
	var str ='';
		str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + subCancelDetector + '\'>';
		str += '	<li>';
		str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
		str += '		<font size="2" style="margin-left: 10px;">key</font>';
		str += '		<select id="detectorKey" name="detectorKey" style="width: 190px;margin-bottom: 0px;">';
		str += '			<option value="agentConfigFactory" selected="selected">agentConfigFactory</option>';
		str += '			<option value="forceVersion">forceVersion</option>';
		str += '			<option value="oid">oid</option>';
		str += '			<option value="port">port</option>';
		str += '			<option value="retries">retries</option>';
		str += '			<option value="serviceName">serviceName</option>';
		str += '			<option value="timeout">timeout</option>';
		str += '			<option value="vbvalue">vbvalue</option>';
		str += '		</select>';
		str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
		str += '		<input style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" name="detectorKey" value="value"/>';
		str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="saveDetectors()">저장</a>';
		str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelDetectors(\'' + subCancelDetector + '\')">취소</a>';
		str += '	</li>';
		str += '</ul>';
	return str;
}

function getPolicyParameter(){
	var subCancelPolicy = Math.floor(Math.random() * Math.pow(10,20));;
	var str = '';
		str += '<ul style="display:inline; list-style:none; padding: 0px; margin: 0px;" id=\'' + subCancelPolicy + '\'>';
		str += '	<li>';
		str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>';
		str += '		<font size="2" style="margin-left: 10px;">key</font>';
		str += '		<input style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" name="policyKey" value=""/>';
		str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
		str += '		<select id="policyClass" name="policyClass" style="width: 210px; margin-bottom: 0px;" type="text">';
		str += '			<option value="DISABLE_COLLECTION" selected="selected">DISABLE_COLLECTION</option>';
		str += '			<option value="DISABLE_SNMP_POLL">DISABLE_SNMP_POLL</option>';
		str += '			<option value="DO_NOT_PERSIST">DO_NOT_PERSIST</option>';
		str += '			<option value="ENABLE_COLLECTION">ENABLE_COLLECTION</option>';
		str += '			<option value="ENABLE_SNMP_POLL">ENABLE_SNMP_POLL</option>';
		str += '			<option value="MANAGE">MANAGE</option>';
		str += '			<option value="UNMANAGE">UNMANAGE</option>';
		str += '		</select>';
		str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="savePolicies()">저장</a>';
		str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelPolicies(\'' + subCancelPolicy + '\')">취소</a>';
		str += '	</li>';
		str += '</ul>';
	return str;
}

function addPolicyTab(){
	var str = '';
	var addPolicyTab = Math.floor(Math.random() * Math.pow(10,20));
		str += '<ul style="display:inline; list-style:none; padding: 0px; margin: 0px;" id=\'' + addPolicyTab + '\'>';
		str += '	<li>';
		str += '		<font size="2" style="margin-left: 10px;">name</font>';
		str += '		<input style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" name="policiesName" value=""/>';
		str += '		<font size="2" style="margin-left: 10px;">class</font>';
		str += '		<select id="policyClass" name="policyClass" style="width: 190px;margin-bottom: 0px;" type="text">';
		str += '			<option value="org.opennms.netmgt.provision.persist.policies.MatchingIpInterfacePolicy" selected="selected">Match IP Interface</option>';
		str += '			<option value="org.opennms.netmgt.provision.persist.policies.MatchingSnmpInterfacePolicy">Match SNMP Interface</option>';
		str += '			<option value="org.opennms.netmgt.provision.persist.policies.NodeCategorySettingPolicy">Set Node Category</option>';
		str += '		</select>';
		str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="savePolicy(\'' + addPolicyTab + '\')">저장</a>';
		str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelPolicies(\'' + addPolicyTab + '\')">취소</a>';
		str += '	</li>';
		str += '</ul>';
	return str;
}

function addDetectorTab(){
	var str = '';
	var addDetectorTab = Math.floor(Math.random() * Math.pow(10,20));
		str += '<ul style="display:inline; list-style:none; padding: 0px; margin: 0px;" id=\'' + addDetectorTab + '\'>';
		str += '	<li>';
		str += '		<font size="2" style="margin-left: 10px;">name</font>';
		str += '		<input style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" name="detectorsName" value="" placeholder="New Detector"/>';
		str += '		<font size="2" style="margin-left: 10px;">class</font>';
		str += '		<select id="detectorClass" name="detectorClass" style="width: 250px;margin-bottom: 0px;">';
		str += '			<option value="org.opennms.netmgt.provision.detector.snmp.BgpSessionDetector" selected="selected">BGP_Session</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.bsf.BSFDetector">BSF</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.simple.CitrixDetector">CITRIX</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.snmp.CiscoIpSlaDetector">Cisco_IP_SLA</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.datagram.DnsDetector">DNS</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.snmp.OpenManageChassisDetector">Dell_OpenManageChassis</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.snmp.DiskUsageDetector">DiskUsage</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.simple.DominoIIOPDetector">DominoIIOP</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.simple.FtpDetector">FTP</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.snmp.HostResourceSWRunDetector">HOST-RESOURCES</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.simple.HttpDetector">HTTP</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.simple.HttpsDetector">HTTPS</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.icmp.IcmpDetector">ICMP</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.simple.ImapDetector">IMAP</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.jmx.JBossDetector">JBoss</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.jdbc.JdbcDetector">JDBC</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.jmx.Jsr160Detector">JSR160</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.jdbc.JdbcQueryDetector">JdbcQueryDetector</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.jdbc.JdbcStoredProcedureDetector">JdbcStoredProcedureDetector</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.simple.LdapDetector">LDAP</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.simple.LdapsDetector">LDAPS</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.loop.LoopDetector">LOOP</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.msexchange.MSExchangeDetector">MSExchange</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.jmx.MX4JDetector">MX4J</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.simple.MemcachedDetector">Memcached</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.simple.NotesHttpDetector">NOTES</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.simple.NrpeDetector">NRPE</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.datagram.NtpDetector">NTP</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.snmp.OmsaStorageDetector">OMSAStorage</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.snmp.PercDetector">PERC</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.simple.Pop3Detector">POP3</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.smb.SmbDetector">SMB</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.sms.SmsDetector">SMS</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.simple.SmtpDetector">SMTP</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.snmp.SnmpDetector">SNMP</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.ssh.SshDetector">SSH</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.simple.TcpDetector">TCP</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.simple.TrivialTimeDetector">TrivialTime</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.web.WebDetector">WEB</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.wmi.WmiDetector">WMI</option>';
		str += '			<option value="org.opennms.netmgt.provision.detector.snmp.Win32ServiceDetector">Win32Service</option>';
		str += '		</select>';
		str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="saveDetector(\'' + addDetectorTab + '\')">저장</a>';
		str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelDetectors(\'' + addDetectorTab + '\')">취소</a>';
		str += '	</li>';
		str += '</ul>';
	return str;
}

function getTableToDetectors(jsonObj){
	var str = '';
	if(jsonObj['detectors'] != null){
		var detectorsObj = jsonObj['detectors'];
		if(detectorsObj['detector']['@class'] == null){
			for(var i in detectorsObj['detector']){
				if(typeof detectorsObj['detector'][i]['parameter'] == 'object'){
					if(detectorsObj['detector'][i]['parameter']['@key'] == null){
						
						var detectorBefore = Math.floor(Math.random() * Math.pow(10,20));
						var detectorAfter = Math.floor(Math.random() * Math.pow(10,20));
						str += '<ul style="display:inline; list-style:none; padding: 0px; margin: 0px;" id=\'' + detectorBefore + '\'>';
						str += '	<li>';
						str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifyDetector(\'' + detectorBefore + '\',\'' + detectorAfter + '\')">수정</a>';
						str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delDetector(\'' + detectorsObj['detector'][i]['@name'] + '\')">삭제</a>';
						str += '		<font size="2" style="margin-left: 10px;">name</font>';
						str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="detectorsName" value=\'' + detectorsObj['detector'][i]['@name'] + '\'/>';
						str += '		<font size="2" style="margin-left: 10px;">class</font>';
						str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px; width: 580px;" readonly="readonly" type="text" name="detectorsClass" value=\'' + detectorsObj['detector'][i]['@class'] + '\'/>';
						str += '		<a href="#" onclick="javascript:addDetectorParameter(\'' + detectorBefore + '\')"><font size="1" color="black">[Add Parameter]</font></a>';
						str += '	</li>';
						str += '</ul>';
						
						str += '<ul style="display:inline; list-style:none; padding: 0px; margin: 0px; display:none;" id=\'' + detectorAfter + '\'>';
						str += '	<li>';
						str += '		<font size="2" style="margin-left: 10px;">name</font>';
						str += '		<input style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" name="detectorsName" value="" placeholder="New Detector"/>';
						str += '		<font size="2" style="margin-left: 10px;">class</font>';
						str += '		<select id="detectorClass" name="detectorClass" style="width: 250px;margin-bottom: 0px;">';
						str += '			<option value="org.opennms.netmgt.provision.detector.snmp.BgpSessionDetector" selected="selected">BGP_Session</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.bsf.BSFDetector">BSF</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.CitrixDetector">CITRIX</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.snmp.CiscoIpSlaDetector">Cisco_IP_SLA</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.datagram.DnsDetector">DNS</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.snmp.OpenManageChassisDetector">Dell_OpenManageChassis</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.snmp.DiskUsageDetector">DiskUsage</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.DominoIIOPDetector">DominoIIOP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.FtpDetector">FTP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.snmp.HostResourceSWRunDetector">HOST-RESOURCES</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.HttpDetector">HTTP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.HttpsDetector">HTTPS</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.icmp.IcmpDetector">ICMP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.ImapDetector">IMAP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.jmx.JBossDetector">JBoss</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.jdbc.JdbcDetector">JDBC</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.jmx.Jsr160Detector">JSR160</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.jdbc.JdbcQueryDetector">JdbcQueryDetector</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.jdbc.JdbcStoredProcedureDetector">JdbcStoredProcedureDetector</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.LdapDetector">LDAP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.LdapsDetector">LDAPS</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.loop.LoopDetector">LOOP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.msexchange.MSExchangeDetector">MSExchange</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.jmx.MX4JDetector">MX4J</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.MemcachedDetector">Memcached</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.NotesHttpDetector">NOTES</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.NrpeDetector">NRPE</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.datagram.NtpDetector">NTP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.snmp.OmsaStorageDetector">OMSAStorage</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.snmp.PercDetector">PERC</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.Pop3Detector">POP3</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.smb.SmbDetector">SMB</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.sms.SmsDetector">SMS</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.SmtpDetector">SMTP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.snmp.SnmpDetector">SNMP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.ssh.SshDetector">SSH</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.TcpDetector">TCP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.TrivialTimeDetector">TrivialTime</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.web.WebDetector">WEB</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.wmi.WmiDetector">WMI</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.snmp.Win32ServiceDetector">Win32Service</option>';
						str += '		</select>';
						str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="saveDetectors(\'' + detectorAfter + '\',\'' + detectorsObj['detector'][i]['@name'] + '\')">저장</a>';
						str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelDetector(\'' + detectorBefore + '\',\'' + detectorAfter + '\')">취소</a>';
						str += '	</li>';
						str += '</ul>';
						for(var q in detectorsObj['detector'][i]['parameter']){
							
							var subDetectorBefore1 = Math.floor(Math.random() * Math.pow(10,20));
							var subDetectorAfter1 = Math.floor(Math.random() * Math.pow(10,20));
							str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + subDetectorBefore1 + '\'>';
							str += '	<li>';
							str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
							str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifySubDetector(\'' + subDetectorBefore1 + '\',\'' + subDetectorAfter1 + '\')">수정</a>';
							str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delSubDetector()">삭제</a>';
							str += '		<font size="2" style="margin-left: 10px;">key</font>';
							str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="detectorKey" value=\'' + detectorsObj['detector'][i]['parameter'][q]['@key'] + '\'/>';
							str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
							str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="detectorValue" value=\'' + detectorsObj['detector'][i]['parameter'][q]['@value'] + '\'/>';
							str += '	</li>';
							str += '</ul>';
							
							str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px; display:none;" id=\'' + subDetectorAfter1 + '\'>';
							str += '	<li>';
							str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
							str += '		<font size="2" style="margin-left: 10px;">key</font>';
							str += '		<select id="detectorKey" name="detectorKey" style="width: 190px;margin-bottom: 0px;">';
							str += '			<option value="agentConfigFactory" selected="selected">agentConfigFactory</option>';
							str += '			<option value="forceVersion">forceVersion</option>';
							str += '			<option value="oid">oid</option>';
							str += '			<option value="port">port</option>';
							str += '			<option value="retries">retries</option>';
							str += '			<option value="serviceName">serviceName</option>';
							str += '			<option value="timeout">timeout</option>';
							str += '			<option value="vbvalue">vbvalue</option>';
							str += '		</select>';
							str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
							str += '		<input style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" name="detectorKey" value="value"/>';
							str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="saveDetectors(\'' + subDetectorAfter1 + '\',\'' + detectorsObj['detector'][i]['@name'] + '\')">저장</a>';
							str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelDetector(\'' + subDetectorBefore1 + '\',\'' + subDetectorAfter1 + '\')">취소</a>';
							str += '	</li>';
							str += '</ul>';
						}
					}else{
						
						var detectorBefore = Math.floor(Math.random() * Math.pow(10,20));
						var detectorAfter = Math.floor(Math.random() * Math.pow(10,20));
						str += '<ul style="display:inline; list-style:none; padding: 0px; margin: 0px;" id=\'' + detectorBefore + '\'>';
						str += '	<li>';
						str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifyDetector(\'' + detectorBefore + '\',\'' + detectorAfter + '\')">수정</a>';
						str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delDetector(\'' + detectorsObj['detector'][i]['@name'] + '\')">삭제</a>';
						str += '		<font size="2" style="margin-left: 10px;">name</font>';
						str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="detectorsName" value=\'' + detectorsObj['detector'][i]['@name'] + '\'/>';
						str += '		<font size="2" style="margin-left: 10px;">class</font>';
						str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px; width: 580px;" readonly="readonly" type="text" name="detectorsClass" value=\'' + detectorsObj['detector'][i]['@class'] + '\'/>';
						str += '		<a href="#" onclick="javascript:addDetectorParameter(\'' + detectorBefore + '\')"><font size="1" color="black">[Add Parameter]</font></a>';
						str += '	</li>';
						str += '</ul>';
						
						str += '<ul style="display:inline; list-style:none; padding: 0px; margin: 0px; display:none;" id=\'' + detectorAfter + '\'>';
						str += '	<li>';
						str += '		<font size="2" style="margin-left: 10px;">name</font>';
						str += '		<input style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" name="detectorsName" value="" placeholder="New Detector"/>';
						str += '		<font size="2" style="margin-left: 10px;">class</font>';
						str += '		<select id="detectorClass" name="detectorClass" style="width: 250px;margin-bottom: 0px;">';
						str += '			<option value="org.opennms.netmgt.provision.detector.snmp.BgpSessionDetector" selected="selected">BGP_Session</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.bsf.BSFDetector">BSF</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.CitrixDetector">CITRIX</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.snmp.CiscoIpSlaDetector">Cisco_IP_SLA</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.datagram.DnsDetector">DNS</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.snmp.OpenManageChassisDetector">Dell_OpenManageChassis</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.snmp.DiskUsageDetector">DiskUsage</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.DominoIIOPDetector">DominoIIOP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.FtpDetector">FTP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.snmp.HostResourceSWRunDetector">HOST-RESOURCES</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.HttpDetector">HTTP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.HttpsDetector">HTTPS</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.icmp.IcmpDetector">ICMP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.ImapDetector">IMAP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.jmx.JBossDetector">JBoss</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.jdbc.JdbcDetector">JDBC</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.jmx.Jsr160Detector">JSR160</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.jdbc.JdbcQueryDetector">JdbcQueryDetector</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.jdbc.JdbcStoredProcedureDetector">JdbcStoredProcedureDetector</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.LdapDetector">LDAP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.LdapsDetector">LDAPS</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.loop.LoopDetector">LOOP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.msexchange.MSExchangeDetector">MSExchange</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.jmx.MX4JDetector">MX4J</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.MemcachedDetector">Memcached</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.NotesHttpDetector">NOTES</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.NrpeDetector">NRPE</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.datagram.NtpDetector">NTP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.snmp.OmsaStorageDetector">OMSAStorage</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.snmp.PercDetector">PERC</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.Pop3Detector">POP3</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.smb.SmbDetector">SMB</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.sms.SmsDetector">SMS</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.SmtpDetector">SMTP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.snmp.SnmpDetector">SNMP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.ssh.SshDetector">SSH</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.TcpDetector">TCP</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.simple.TrivialTimeDetector">TrivialTime</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.web.WebDetector">WEB</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.wmi.WmiDetector">WMI</option>';
						str += '			<option value="org.opennms.netmgt.provision.detector.snmp.Win32ServiceDetector">Win32Service</option>';
						str += '		</select>';
						str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="saveDetectors(\'' + detectorAfter + '\',\'' + detectorsObj['detector'][i]['@name'] + '\')">저장</a>';
						str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelDetector(\'' + detectorBefore + '\',\'' + detectorAfter + '\')">취소</a>';
						str += '	</li>';
						str += '</ul>';
							
							var subDetectorBefore1 = Math.floor(Math.random() * Math.pow(10,20));
							var subDetectorAfter1 = Math.floor(Math.random() * Math.pow(10,20));
							str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + subDetectorBefore1 + '\'>';
							str += '	<li>';
							str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
							str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifySubDetector(\'' + subDetectorBefore1 + '\',\'' + subDetectorAfter1 + '\')">수정</a>';
							str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delSubDetector()">삭제</a>';
							str += '		<font size="2" style="margin-left: 10px;">key</font>';
							str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="detectorKey" value=\'' + detectorsObj['detector'][i]['parameter']['@key'] + '\'/>';
							str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
							str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="detectorValue" value=\'' + detectorsObj['detector'][i]['parameter']['@value'] + '\'/>';
							str += '	</li>';
							str += '</ul>';
							
							str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px; display:none;" id=\'' + subDetectorAfter1 + '\'>';
							str += '	<li>';
							str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
							str += '		<font size="2" style="margin-left: 10px;">key</font>';
							str += '		<select id="detectorKey" name="detectorKey" style="width: 190px;margin-bottom: 0px;">';
							str += '			<option value="agentConfigFactory" selected="selected">agentConfigFactory</option>';
							str += '			<option value="forceVersion">forceVersion</option>';
							str += '			<option value="oid">oid</option>';
							str += '			<option value="port">port</option>';
							str += '			<option value="retries">retries</option>';
							str += '			<option value="serviceName">serviceName</option>';
							str += '			<option value="timeout">timeout</option>';
							str += '			<option value="vbvalue">vbvalue</option>';
							str += '		</select>';
							str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
							str += '		<input style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" name="detectorKey" value="value"/>';
							str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="saveDetectors(\'' + subDetectorAfter1 + '\',\'' + detectorsObj['detector'][i]['@name'] + '\')">저장</a>';
							str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelDetector(\'' + subDetectorBefore1 + '\',\'' + subDetectorAfter1 + '\')">취소</a>';
							str += '	</li>';
							str += '</ul>';
					}
				}else{
					
					var detectorBefore = Math.floor(Math.random() * Math.pow(10,20));
					var detectorAfter = Math.floor(Math.random() * Math.pow(10,20));
					str += '<ul style="display:inline; list-style:none; padding: 0px; margin: 0px;" id=\'' + detectorBefore + '\'>';
					str += '	<li>';
					str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifyDetector(\'' + detectorBefore + '\',\'' + detectorAfter + '\')">수정</a>';
					str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delDetector(\'' + detectorsObj['detector'][i]['@name'] + '\')">삭제</a>';
					str += '		<font size="2" style="margin-left: 10px;">name</font>';
					str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="detectorsName" value=\'' + detectorsObj['detector'][i]['@name'] + '\'/>';
					str += '		<font size="2" style="margin-left: 10px;">class</font>';
					str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px; width: 580px;" readonly="readonly" type="text" name="detectorsClass" value=\'' + detectorsObj['detector'][i]['@class'] + '\'/>';
					str += '		<a href="#" onclick="javascript:addDetectorParameter(\'' + detectorBefore + '\')"><font size="1" color="black">[Add Parameter]</font></a>';
					str += '	</li>';
					str += '</ul>';
					
					str += '<ul style="display:inline; list-style:none; padding: 0px; margin: 0px; display:none;" id=\'' + detectorAfter + '\'>';
					str += '	<li>';
					str += '		<font size="2" style="margin-left: 10px;">name</font>';
					str += '		<input style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" name="detectorsName" value="" placeholder="New Detector"/>';
					str += '		<font size="2" style="margin-left: 10px;">class</font>';
					str += '		<select id="detectorClass" name="detectorClass" style="width: 250px;margin-bottom: 0px;">';
					str += '			<option value="org.opennms.netmgt.provision.detector.snmp.BgpSessionDetector" selected="selected">BGP_Session</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.bsf.BSFDetector">BSF</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.simple.CitrixDetector">CITRIX</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.snmp.CiscoIpSlaDetector">Cisco_IP_SLA</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.datagram.DnsDetector">DNS</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.snmp.OpenManageChassisDetector">Dell_OpenManageChassis</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.snmp.DiskUsageDetector">DiskUsage</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.simple.DominoIIOPDetector">DominoIIOP</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.simple.FtpDetector">FTP</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.snmp.HostResourceSWRunDetector">HOST-RESOURCES</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.simple.HttpDetector">HTTP</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.simple.HttpsDetector">HTTPS</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.icmp.IcmpDetector">ICMP</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.simple.ImapDetector">IMAP</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.jmx.JBossDetector">JBoss</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.jdbc.JdbcDetector">JDBC</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.jmx.Jsr160Detector">JSR160</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.jdbc.JdbcQueryDetector">JdbcQueryDetector</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.jdbc.JdbcStoredProcedureDetector">JdbcStoredProcedureDetector</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.simple.LdapDetector">LDAP</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.simple.LdapsDetector">LDAPS</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.loop.LoopDetector">LOOP</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.msexchange.MSExchangeDetector">MSExchange</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.jmx.MX4JDetector">MX4J</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.simple.MemcachedDetector">Memcached</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.simple.NotesHttpDetector">NOTES</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.simple.NrpeDetector">NRPE</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.datagram.NtpDetector">NTP</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.snmp.OmsaStorageDetector">OMSAStorage</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.snmp.PercDetector">PERC</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.simple.Pop3Detector">POP3</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.smb.SmbDetector">SMB</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.sms.SmsDetector">SMS</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.simple.SmtpDetector">SMTP</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.snmp.SnmpDetector">SNMP</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.ssh.SshDetector">SSH</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.simple.TcpDetector">TCP</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.simple.TrivialTimeDetector">TrivialTime</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.web.WebDetector">WEB</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.wmi.WmiDetector">WMI</option>';
					str += '			<option value="org.opennms.netmgt.provision.detector.snmp.Win32ServiceDetector">Win32Service</option>';
					str += '		</select>';
					str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="saveDetectors(\'' + detectorAfter + '\',\'' + detectorsObj['detector'][i]['@name'] + '\')">저장</a>';
					str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelDetector(\'' + detectorBefore + '\',\'' + detectorAfter + '\')">취소</a>';
					str += '	</li>';
					str += '</ul>';
				}
			}
		}else{
			
			var detectorBefore = Math.floor(Math.random() * Math.pow(10,20));
			var detectorAfter = Math.floor(Math.random() * Math.pow(10,20));
			str += '<ul style="display:inline; list-style:none; padding: 0px; margin: 0px;" id=\'' + detectorBefore + '\'>';
			str += '	<li>';
			str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifyDetector(\'' + detectorBefore + '\',\'' + detectorAfter + '\')">수정</a>';
			str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delDetector(\'' + detectorsObj['detector']['@name'] + '\')">삭제</a>';
			str += '		<font size="2" style="margin-left: 10px;">name</font>';
			str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="detectorsName" value=\'' + detectorsObj['detector']['@name'] + '\'/>';
			str += '		<font size="2" style="margin-left: 10px;">class</font>';
			str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px; width: 580px;" readonly="readonly" type="text" name="detectorsClass" value=\'' + detectorsObj['detector']['@class'] + '\'/>';
			str += '		<a href="#" onclick="javascript:addDetectorParameter(\'' + detectorBefore + '\')"><font size="1" color="black">[Add Parameter]</font></a>';
			str += '	</li>';
			str += '</ul>';
			
			str += '<ul style="display:inline; list-style:none; padding: 0px; margin: 0px; display:none;" id=\'' + detectorAfter + '\'>';
			str += '	<li>';
			str += '		<font size="2" style="margin-left: 10px;">name</font>';
			str += '		<input style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" name="detectorsName" value="" placeholder="New Detector"/>';
			str += '		<font size="2" style="margin-left: 10px;">class</font>';
			str += '		<select id="detectorClass" name="detectorClass" style="width: 250px;margin-bottom: 0px;">';
			str += '			<option value="org.opennms.netmgt.provision.detector.snmp.BgpSessionDetector" selected="selected">BGP_Session</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.bsf.BSFDetector">BSF</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.simple.CitrixDetector">CITRIX</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.snmp.CiscoIpSlaDetector">Cisco_IP_SLA</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.datagram.DnsDetector">DNS</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.snmp.OpenManageChassisDetector">Dell_OpenManageChassis</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.snmp.DiskUsageDetector">DiskUsage</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.simple.DominoIIOPDetector">DominoIIOP</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.simple.FtpDetector">FTP</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.snmp.HostResourceSWRunDetector">HOST-RESOURCES</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.simple.HttpDetector">HTTP</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.simple.HttpsDetector">HTTPS</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.icmp.IcmpDetector">ICMP</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.simple.ImapDetector">IMAP</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.jmx.JBossDetector">JBoss</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.jdbc.JdbcDetector">JDBC</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.jmx.Jsr160Detector">JSR160</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.jdbc.JdbcQueryDetector">JdbcQueryDetector</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.jdbc.JdbcStoredProcedureDetector">JdbcStoredProcedureDetector</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.simple.LdapDetector">LDAP</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.simple.LdapsDetector">LDAPS</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.loop.LoopDetector">LOOP</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.msexchange.MSExchangeDetector">MSExchange</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.jmx.MX4JDetector">MX4J</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.simple.MemcachedDetector">Memcached</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.simple.NotesHttpDetector">NOTES</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.simple.NrpeDetector">NRPE</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.datagram.NtpDetector">NTP</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.snmp.OmsaStorageDetector">OMSAStorage</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.snmp.PercDetector">PERC</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.simple.Pop3Detector">POP3</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.smb.SmbDetector">SMB</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.sms.SmsDetector">SMS</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.simple.SmtpDetector">SMTP</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.snmp.SnmpDetector">SNMP</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.ssh.SshDetector">SSH</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.simple.TcpDetector">TCP</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.simple.TrivialTimeDetector">TrivialTime</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.web.WebDetector">WEB</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.wmi.WmiDetector">WMI</option>';
			str += '			<option value="org.opennms.netmgt.provision.detector.snmp.Win32ServiceDetector">Win32Service</option>';
			str += '		</select>';
			str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="saveDetectors(\'' + detectorAfter + '\',\'' + detectorsObj['detector']['@name'] + '\')">저장</a>';
			str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelDetector(\'' + detectorBefore + '\',\'' + detectorAfter + '\')">취소</a>';
			str += '	</li>';
			str += '</ul>';
			for(var i in detectorsObj['detector']['parameter']){
				
				var subDetectorBefore1 = Math.floor(Math.random() * Math.pow(10,20));
				var subDetectorAfter1 = Math.floor(Math.random() * Math.pow(10,20));
				str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + subDetectorBefore1 + '\'>';
				str += '	<li>';
				str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
				str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifySubDetector(\'' + subDetectorBefore1 + '\',\'' + subDetectorAfter1 + '\')">수정</a>';
				str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delSubDetector()">삭제</a>';
				str += '		<font size="2" style="margin-left: 10px;">key</font>';
				str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="detectorKey" value=\'' + detectorsObj['detector']['parameter'][i]['@key'] + '\'/>';
				str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
				str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="detectorValue" value=\'' + detectorsObj['detector']['parameter'][i]['@value'] + '\'/>';
				str += '	</li>';
				str += '</ul>';
				
				str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px; display:none;" id=\'' + subDetectorAfter1 + '\'>';
				str += '	<li>';
				str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
				str += '		<font size="2" style="margin-left: 10px;">key</font>';
				str += '		<select id="detectorKey" name="detectorKey" style="width: 190px;margin-bottom: 0px;">';
				str += '			<option value="agentConfigFactory" selected="selected">agentConfigFactory</option>';
				str += '			<option value="forceVersion">forceVersion</option>';
				str += '			<option value="oid">oid</option>';
				str += '			<option value="port">port</option>';
				str += '			<option value="retries">retries</option>';
				str += '			<option value="serviceName">serviceName</option>';
				str += '			<option value="timeout">timeout</option>';
				str += '			<option value="vbvalue">vbvalue</option>';
				str += '		</select>';
				str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
				str += '		<input style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" name="detectorKey" value="value"/>';
				str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="saveDetectors(\'' + subDetectorAfter1 + '\',\'' + detectorsObj['detector']['@name'] + '\')">저장</a>';
				str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelDetector(\'' + subDetectorBefore1 + '\',\'' + subDetectorAfter1 + '\')">취소</a>';
				str += '	</li>';
				str += '</ul>';
			}
		}
	}else{
		str += '<ul><li>Detector가 없습니다.</li></ul>';
	}
	return str;
}

function getTableToPolicies(jsonObj){
	var str = '';
	if(jsonObj['policies'] != null){
		var policiesObj = jsonObj['policies'];
		if(policiesObj['policy']['parameter'] == null){
			for(var i in policiesObj['policy']){
				
				var policyBefore = Math.floor(Math.random() * Math.pow(10,20));
				var policyAfter = Math.floor(Math.random() * Math.pow(10,20));
				str += '<ul style="display:inline; list-style:none; padding: 0px; margin: 0px;" id=\'' + policyBefore + '\'>';
				str += '	<li>';
				str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifyPolicy(\'' + policyBefore + '\',\'' + policyAfter + '\')">수정</a>';
				str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delPolicy(\'' + policiesObj['policy'][i]['@name'] + '\')">삭제</a>';
				str += '		<font size="2" style="margin-left: 10px;">name</font>';
				str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policiesName" value=\'' + policiesObj['policy'][i]['@name'] + '\'/>';
				str += '		<font size="2" style="margin-left: 10px;">class</font>';
				str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px; width: 580px;" readonly="readonly" type="text" name="policiesClass" value=\'' + policiesObj['policy'][i]['@class'] + '\'/>';
				if(policiesObj['policy'][i]['parameter'].length < 4){
					str += '		<a href="#" onclick="javascript:addPolicyParameter(\'' + policyBefore + '\')"><font size="1" color="black">[Add Parameter]</font></a>';
				}
				str += '	</li>';
				str += '</ul>';
				
				str += '<ul style="display:inline; list-style:none; display:none; padding: 0px; margin: 0px;" id=\'' + policyAfter + '\'>';
				str += '	<li>';
				str += '		<font size="2" style="margin-left: 10px;">name</font>';
				str += '		<input style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" name="policiesName" value=""/>';
				str += '		<font size="2" style="margin-left: 10px;">class</font>';
				str += '		<select id="policyClass" name="policyClass" style="width: 190px;margin-bottom: 0px;" type="text">';
				str += '			<option value="org.opennms.netmgt.provision.persist.policies.MatchingIpInterfacePolicy" selected="selected">Match IP Interface</option>';
				str += '			<option value="org.opennms.netmgt.provision.persist.policies.MatchingSnmpInterfacePolicy">Match SNMP Interface</option>';
				str += '			<option value="org.opennms.netmgt.provision.persist.policies.NodeCategorySettingPolicy">Set Node Category</option>';
				str += '		</select>';
				str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="savePolicies(\'' + policyAfter + '\',\'' + policiesObj['policy'][i]['@name'] + '\')">저장</a>';
				str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelPolicy(\'' + policyBefore + '\',\'' + policyAfter + '\')">취소</a>';
				str += '	</li>';
				str += '</ul>';
					
					var subPolicyBefore1 = Math.floor(Math.random() * Math.pow(10,20));
					var subPolicyAfter1 = Math.floor(Math.random() * Math.pow(10,20));
					str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + subPolicyBefore1 + '\'>';
					str += '	<li>';
					str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>';
					str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifySubPolicy(\'' + subPolicyBefore1 + '\',\'' + subPolicyAfter1 + '\')">수정</a>';
					str += '		<font size="2" style="margin-left: 10px;">key</font>';
					str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyKey" value=\'' + policiesObj['policy'][i]['parameter'][0]['@key'] + '\'/>';
					str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
					str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyValue" value=\'' + policiesObj['policy'][i]['parameter'][0]['@value'] + '\'/>';
					str += '	</li>';
					str += '</ul>';
					
					str += '<ul style="display:inline; list-style:none; padding: 0px; display:none; margin: 0px;" id=\'' + subPolicyAfter1 + '\'>';
					str += '	<li>';
					str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>';
					str += '		<font size="2" style="margin-left: 10px;">key</font>';
					str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyKey" value=\'' + policiesObj['policy'][i]['parameter'][0]['@key'] + '\'/>';
					str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
					str += '		<select id="policyClass" name="policyClass" style="width: 210px; margin-bottom: 0px;" type="text">';
					str += '			<option value="DISABLE_COLLECTION" selected="selected">DISABLE_COLLECTION</option>';
					str += '			<option value="DISABLE_SNMP_POLL">DISABLE_SNMP_POLL</option>';
					str += '			<option value="DO_NOT_PERSIST">DO_NOT_PERSIST</option>';
					str += '			<option value="ENABLE_COLLECTION">ENABLE_COLLECTION</option>';
					str += '			<option value="ENABLE_SNMP_POLL">ENABLE_SNMP_POLL</option>';
					str += '			<option value="MANAGE">MANAGE</option>';
					str += '			<option value="UNMANAGE">UNMANAGE</option>';
					str += '		</select>';
					str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="savePolicies(\'' + subPolicyAfter1 + '\',\'' + policiesObj['policy'][i]['@name'] + '\')">저장</a>';
					str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelPolicy(\'' + subPolicyBefore1 + '\',\'' + subPolicyAfter1 + '\')">취소</a>';
					str += '	</li>';
					str += '</ul>';
					
					var subPolicyBefore2 = Math.floor(Math.random() * Math.pow(10,20));
					var subPolicyAfter2 = Math.floor(Math.random() * Math.pow(10,20));
					str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + subPolicyBefore2 + '\'>';
					str += '	<li>';
					str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>';
					str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifySubPolicy(\'' + subPolicyBefore2 + '\',\'' + subPolicyAfter2 + '\')">수정</a>';
					str += '		<font size="2" style="margin-left: 10px;">key</font>';
					str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyKey" value=\'' + policiesObj['policy'][i]['parameter'][1]['@key'] + '\'/>';
					str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
					str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyValue" value=\'' + policiesObj['policy'][i]['parameter'][1]['@value'] + '\'/>';
					str += '	</li>';
					str += '</ul>';
					
					str += '<ul style="display:inline; list-style:none; padding: 0px; display:none; margin: 0px;" id=\'' + subPolicyAfter2 + '\'>';
					str += '	<li>';
					str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>';
					str += '		<font size="2" style="margin-left: 10px;">key</font>';
					str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyKey" value=\'' + policiesObj['policy'][i]['parameter'][1]['@key'] + '\'/>';
					str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
					str += '		<select id="policyClass" name="policyClass" style="width: 210px; margin-bottom: 0px;" type="text">';
					str += '			<option value="DISABLE_COLLECTION" selected="selected">DISABLE_COLLECTION</option>';
					str += '			<option value="DISABLE_SNMP_POLL">DISABLE_SNMP_POLL</option>';
					str += '			<option value="DO_NOT_PERSIST">DO_NOT_PERSIST</option>';
					str += '			<option value="ENABLE_COLLECTION">ENABLE_COLLECTION</option>';
					str += '			<option value="ENABLE_SNMP_POLL">ENABLE_SNMP_POLL</option>';
					str += '			<option value="MANAGE">MANAGE</option>';
					str += '			<option value="UNMANAGE">UNMANAGE</option>';
					str += '		</select>';
					str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="savePolicies(\'' + subPolicyAfter2 + '\',\'' + policiesObj['policy'][i]['@name'] + '\')">저장</a>';
					str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelPolicy(\'' + subPolicyBefore2 + '\',\'' + subPolicyAfter2 + '\')">취소</a>';
					str += '	</li>';
					str += '</ul>';
				try{
					if(typeof policiesObj['policy'][i]['parameter'][2]['@key'] != 'undefined'){
						
						var subPolicyBefore3 = Math.floor(Math.random() * Math.pow(10,20));
						var subPolicyAfter3 = Math.floor(Math.random() * Math.pow(10,20));
						str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + subPolicyBefore3 + '\'>';
						str += '	<li>';
						str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
						str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifySubPolicy(\'' + subPolicyBefore3 + '\',\'' + subPolicyAfter3 + '\')">수정</a>';
						str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delSubPolicy()">삭제</a>';
						str += '		<font size="2" style="margin-left: 10px;">key</font>';
						str += '		<input style="border:0; background: lightgrey; height: 13px; width: 340px; margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyKey" value=\'' + policiesObj['policy'][i]['parameter'][2]['@key'] + '\'/>';
						str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
						str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyValue" value=\'' + policiesObj['policy'][i]['parameter'][2]['@value'] + '\'/>';
						str += '	</li>';
						str += '</ul>';
						
						str += '<ul style="display:inline; list-style:none; display:none; padding: 0px;margin: 0px;" id=\'' + subPolicyAfter3 + '\'>';
						str += '	<li>';
						str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>';
						str += '		<font size="2" style="margin-left: 10px;">key</font>';
						str += '		<select id="policyClass" name="policyClass" style="width: 130px; margin-bottom: 0px;" type="text">';
						str += '			<option value="hostName" selected="selected">hostName</option>';
						str += '		</select>';
						str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
						str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyKey" value="value"/>';
						str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="savePolicies(\'' + subPolicyAfter3 + '\',\'' + policiesObj['policy'][i]['@name'] + '\')">저장</a>';
						str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelPolicy(\'' + subPolicyBefore3 + '\',\'' + subPolicyAfter3 + '\')">취소</a>';
						str += '	</li>';
						str += '</ul>';
					}
				}catch(e){}
				try{
					if(typeof policiesObj['policy'][i]['parameter'][3]['@key'] != 'undefined'){
						
						var subPolicyBefore4 = Math.floor(Math.random() * Math.pow(10,20));
						var subPolicyAfter4 = Math.floor(Math.random() * Math.pow(10,20));
						str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + subPolicyBefore4 + '\'>';
						str += '	<li>';
						str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
						str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifySubPolicy(\'' + subPolicyBefore4 + '\',\'' + subPolicyAfter4 + '\')">수정</a>';
						str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delSubPolicy()">삭제</a>';
						str += '		<font size="2" style="margin-left: 10px;">key</font>';
						str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px; width: 340px; margin-bottom: 0px;" readonly="readonly" type="text" name="policyKey" value=\'' + policiesObj['policy'][i]['parameter'][3]['@key'] + '\'/>';
						str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
						str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyValue" value=\'' + policiesObj['policy'][i]['parameter'][3]['@value'] + '\'/>';
						str += '	</li>';
						str += '</ul>';
						
						str += '<ul style="display:inline; list-style:none; padding: 0px; display:none; margin: 0px;" id=\'' + subPolicyAfter4 + '\'>';
						str += '	<li>';
						str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>';
						str += '		<font size="2" style="margin-left: 10px;">key</font>';
						str += '		<select id="policyClass" name="policyClass" style="width: 130px; margin-bottom: 0px;" type="text">';
						str += '			<option value="hostName" selected="selected">hostName</option>';
						str += '		</select>';
						str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
						str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyKey" value="value"/>';
						str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="savePolicies(\'' + subPolicyAfter4 + '\',\'' + policiesObj['policy'][i]['@name'] + '\')">저장</a>';
						str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelPolicy(\'' + subPolicyBefore4 + '\',\'' + subPolicyAfter4 + '\')">취소</a>';
						str += '	</li>';
						str += '</ul>';
					}
				}catch(e){}
			}
		}else{
			
			var policyBefore = Math.floor(Math.random() * Math.pow(10,20));
			var policyAfter = Math.floor(Math.random() * Math.pow(10,20));
			str += '<ul style="display:inline; list-style:none; padding: 0px; margin: 0px;" id=\'' + policyBefore + '\'>';
			str += '	<li>';
			str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifyPolicy(\'' + policyBefore + '\',\'' + policyAfter + '\')">수정</a>';
			str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delPolicy(\'' + policiesObj['policy']['@name'] + '\')">삭제</a>';
			str += '		<font size="2" style="margin-left: 10px;">name</font>';
			str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policiesName" value=\'' + policiesObj['policy']['@name'] + '\'/>';
			str += '		<font size="2" style="margin-left: 10px;">class</font>';
			str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px; width: 580px;" readonly="readonly" type="text" name="policiesClass" value=\'' + policiesObj['policy']['@class'] + '\'/>';
			if(policiesObj['policy']['parameter'].length < 4){
				str += '		<a href="#" onclick="javascript:addPolicyParameter(\'' + policyBefore + '\')"><font size="1" color="black">[Add Parameter]</font></a>';
			}
			str += '	</li>';
			str += '</ul>';
			
			str += '<ul style="display:inline; list-style:none; padding: 0px; margin: 0px; display:none;" id=\'' + policyAfter + '\'>';
			str += '	<li>';
			str += '		<font size="2" style="margin-left: 10px;">name</font>';
			str += '		<input style="height: 13px;margin-left: 0px;margin-bottom: 0px;" type="text" name="policiesName" value=""/>';
			str += '		<font size="2" style="margin-left: 10px;">class</font>';
			str += '		<select id="policyClass" name="policyClass" style="width: 190px;margin-bottom: 0px;" type="text">';
			str += '			<option value="org.opennms.netmgt.provision.persist.policies.MatchingIpInterfacePolicy" selected="selected">Match IP Interface</option>';
			str += '			<option value="org.opennms.netmgt.provision.persist.policies.MatchingSnmpInterfacePolicy">Match SNMP Interface</option>';
			str += '			<option value="org.opennms.netmgt.provision.persist.policies.NodeCategorySettingPolicy">Set Node Category</option>';
			str += '		</select>';
			str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="savePolicies(\'' + policyAfter + '\',\'' + policiesObj['policy']['@name'] + '\')">저장</a>';
			str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelPolicy(\'' + policyBefore + '\',\'' + policyAfter + '\')">취소</a>';
			str += '	</li>';
			str += '</ul>';
				
				var subPolicyBefore1 = Math.floor(Math.random() * Math.pow(10,20));
				var subPolicyAfter1 = Math.floor(Math.random() * Math.pow(10,20));
				str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + subPolicyBefore1 + '\'>';
				str += '	<li>';
				str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>';
				str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifySubPolicy(\'' + subPolicyBefore1 + '\',\'' + subPolicyAfter1 + '\')">수정</a>';
				str += '		<font size="2" style="margin-left: 10px;">key</font>';
				str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyKey" value=\'' + policiesObj['policy']['parameter'][0]['@key'] + '\'/>';
				str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
				str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyValue" value=\'' + policiesObj['policy']['parameter'][0]['@value'] + '\'/>';
				str += '	</li>';
				str += '</ul>';
				
				str += '<ul style="display:inline; list-style:none; padding: 0px; display:none; margin: 0px;" id=\'' + subPolicyAfter1 + '\'>';
				str += '	<li>';
				str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>';
				str += '		<font size="2" style="margin-left: 10px;">key</font>';
				str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyKey" value=\'' + policiesObj['policy']['parameter'][0]['@key'] + '\'/>';
				str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
				str += '		<select id="policyClass" name="policyClass" style="width: 210px; margin-bottom: 0px;" type="text">';
				str += '			<option value="DISABLE_COLLECTION" selected="selected">DISABLE_COLLECTION</option>';
				str += '			<option value="DISABLE_SNMP_POLL">DISABLE_SNMP_POLL</option>';
				str += '			<option value="DO_NOT_PERSIST">DO_NOT_PERSIST</option>';
				str += '			<option value="ENABLE_COLLECTION">ENABLE_COLLECTION</option>';
				str += '			<option value="ENABLE_SNMP_POLL">ENABLE_SNMP_POLL</option>';
				str += '			<option value="MANAGE">MANAGE</option>';
				str += '			<option value="UNMANAGE">UNMANAGE</option>';
				str += '		</select>';
				str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="savePolicies(\'' + subPolicyAfter1 + '\',\'' + policiesObj['policy']['@name'] + '\')">저장</a>';
				str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelPolicy(\'' + subPolicyBefore1 + '\',\'' + subPolicyAfter1 + '\')">취소</a>';
				str += '	</li>';
				str += '</ul>';
				
				var subPolicyBefore2 = Math.floor(Math.random() * Math.pow(10,20));
				var subPolicyAfter2 = Math.floor(Math.random() * Math.pow(10,20));
				str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + subPolicyBefore2 + '\'>';
				str += '	<li>';
				str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>';
				str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifySubPolicy(\'' + subPolicyBefore2 + '\',\'' + subPolicyAfter2 + '\')">수정</a>';
				str += '		<font size="2" style="margin-left: 10px;">key</font>';
				str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyKey" value=\'' + policiesObj['policy']['parameter'][1]['@key'] + '\'/>';
				str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
				str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyValue" value=\'' + policiesObj['policy']['parameter'][1]['@value'] + '\'/>';
				str += '	</li>';
				str += '</ul>';
				
				str += '<ul style="display:inline; list-style:none; padding: 0px; display:none; margin: 0px;" id=\'' + subPolicyAfter2 + '\'>';
				str += '	<li>';
				str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>';
				str += '		<font size="2" style="margin-left: 10px;">key</font>';
				str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyKey" value=\'' + policiesObj['policy']['parameter'][1]['@key'] + '\'/>';
				str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
				str += '		<select id="policyClass" name="policyClass" style="width: 210px; margin-bottom: 0px;" type="text">';
				str += '			<option value="DISABLE_COLLECTION" selected="selected">DISABLE_COLLECTION</option>';
				str += '			<option value="DISABLE_SNMP_POLL">DISABLE_SNMP_POLL</option>';
				str += '			<option value="DO_NOT_PERSIST">DO_NOT_PERSIST</option>';
				str += '			<option value="ENABLE_COLLECTION">ENABLE_COLLECTION</option>';
				str += '			<option value="ENABLE_SNMP_POLL">ENABLE_SNMP_POLL</option>';
				str += '			<option value="MANAGE">MANAGE</option>';
				str += '			<option value="UNMANAGE">UNMANAGE</option>';
				str += '		</select>';
				str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="savePolicies(\'' + subPolicyAfter2 + '\',\'' + policiesObj['policy']['@name'] + '\')">저장</a>';
				str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelPolicy(\'' + subPolicyBefore2 + '\',\'' + subPolicyAfter2 + '\')">취소</a>';
				str += '	</li>';
				str += '</ul>';
			try{
				if(typeof policiesObj['policy']['parameter'][2]['@key'] != 'undefined'){
					
					var subPolicyBefore3 = Math.floor(Math.random() * Math.pow(10,20));
					var subPolicyAfter3 = Math.floor(Math.random() * Math.pow(10,20));
					str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + subPolicyBefore3 + '\'>';
					str += '	<li>';
					str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
					str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifySubPolicy(\'' + subPolicyBefore3 + '\',\'' + subPolicyAfter3 + '\')">수정</a>';
					str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delSubPolicy()">삭제</a>';
					str += '		<font size="2" style="margin-left: 10px;">key</font>';
					str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyKey" value=\'' + policiesObj['policy']['parameter'][2]['@key'] + '\'/>';
					str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
					str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyValue" value=\'' + policiesObj['policy']['parameter'][2]['@value'] + '\'/>';
					str += '	</li>';
					str += '</ul>';
					
					str += '<ul style="display:inline; list-style:none; display:none; padding: 0px;margin: 0px;" id=\'' + subPolicyAfter3 + '\'>';
					str += '	<li>';
					str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>';
					str += '		<font size="2" style="margin-left: 10px;">key</font>';
					str += '		<select id="policyClass" name="policyClass" style="width: 130px; margin-bottom: 0px;" type="text">';
					str += '			<option value="hostName" selected="selected">hostName</option>';
					str += '		</select>';
					str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
					str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyKey" value="value"/>';
					str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="savePolicies(\'' + subPolicyAfter3 + '\',\'' + policiesObj['policy']['@name'] + '\')">저장</a>';
					str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelPolicy(\'' + subPolicyBefore3 + '\',\'' + subPolicyAfter3 + '\')">취소</a>';
					str += '	</li>';
					str += '</ul>';
				}
			}catch(e){}
			try{
				if(typeof policiesObj['policy']['parameter'][3]['@key'] != 'undefined'){
					
					var subPolicyBefore4 = Math.floor(Math.random() * Math.pow(10,20));
					var subPolicyAfter4 = Math.floor(Math.random() * Math.pow(10,20));
					str += '<ul style="display:inline; list-style:none; padding: 0px;margin: 0px;" id=\'' + subPolicyBefore4 + '\'>';
					str += '	<li>';
					str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●</font>';
					str += '		<a type="button" class="btn btn-primary btn-mini" onclick="modifySubPolicy(\'' + subPolicyBefore4 + '\',\'' + subPolicyAfter4 + '\')">수정</a>';
					str += '		<a type="button" class="btn btn-danger btn-mini" onclick="delSubPolicy()">삭제</a>';
					str += '		<font size="2" style="margin-left: 10px;">key</font>';
					str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyKey" value=\'' + policiesObj['policy']['parameter'][3]['@key'] + '\'/>';
					str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
					str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyValue" value=\'' + policiesObj['policy']['parameter'][3]['@value'] + '\'/>';
					str += '	</li>';
					str += '</ul>';
					
					str += '<ul style="display:inline; list-style:none; padding: 0px; display:none; margin: 0px;" id=\'' + subPolicyAfter4 + '\'>';
					str += '	<li>';
					str += '		<font color="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>';
					str += '		<font size="2" style="margin-left: 10px;">key</font>';
					str += '		<select id="policyClass" name="policyClass" style="width: 130px; margin-bottom: 0px;" type="text">';
					str += '			<option value="hostName" selected="selected">hostName</option>';
					str += '		</select>';
					str += '		<font size="2" style="margin-left: 10px; height: 13px;margin-left: 0px;margin-bottom: 0px;">value</font>';
					str += '		<input style="border:0; background: lightgrey; height: 13px;margin-left: 0px;margin-bottom: 0px;" readonly="readonly" type="text" name="policyKey" value="value"/>';
					str += '		<a type="button" class="btn btn-primary btn-mini" style="margin-left: 5px;" onclick="savePolicies(\'' + subPolicyAfter4 + '\',\'' + policiesObj['policy']['@name'] + '\')">저장</a>';
					str += '		<a type="button" class="btn btn-danger btn-mini" style="margin-left: 5px;" onclick="cancelPolicy(\'' + subPolicyBefore4 + '\',\'' + subPolicyAfter4 + '\')">취소</a>';
					str += '	</li>';
					str += '</ul>';
				}
			}catch(e){}
		}
	}else{
		str += '<ul><li>Policy가 없습니다.</li></ul>';
	}
	return str;
}










































