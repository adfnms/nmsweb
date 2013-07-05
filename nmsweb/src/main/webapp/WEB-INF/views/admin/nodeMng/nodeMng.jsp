<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="/include/header.jsp">
	<jsp:param value="노드관리" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script src="<c:url value="/resources/js/service.js" />"></script>
<script src="<c:url value="/resources/js/category.js" />"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		/* Node base info */
		getSpecificNode(addNodeDesc, "${nodeId}");
		
		/* Interface info */
		getInterfacesFromNodeId(addInterfaceInfo, "${nodeId}");
		
		/* SNMP Interface info */
		getSnmpInterfaceInfo(addSnmpInterfaceInfo, "${nodeId}");
		
	});
	
	/* Node base info Callback */
	function addNodeDesc(jsonObj){
		
		$('#nodeLabel').val(jsonObj["@label"]);
		
	}
	/*//Node base info Callback */
	
	/* Interface info Callback */
	function addInterfaceInfo(jsonObj){

		var str ="";
		
		if(jsonObj["@count"] > 0){
			if(jsonObj["@count"] > 1){
				var interfaceObj = jsonObj["ipInterface"];
				for(var i in interfaceObj){
					
					str = '<table class="table table-striped">';
					str += '	<tr>';
					str += '		<td>';
					str += 				interfaceObj[i]["ipAddress"];
					str += '		</td>';
					str += '		<td>';
					str += '			<select name="'+interfaceObj[i]["ipAddress"]+'">';
					str += '				<option value="M"'+interfaceObj[i]["@isManaged"] == "M" ? "selected" : "" +'>관리</option>';
					str += '				<option value="F"'+interfaceObj[i]["@isManaged"] == "F" ? "selected" : "" +'>비관리</option>';
					str += '			</select>';
					str += '		</td>';
					str += '	</tr>';
					str += '</table>';
				
					/* service info */
					getServiceFromNodeidIpaddress(addServiceInfo, "${nodeId}", interfaceObj[i]["ipAddress"]);
				}
					
			}else{
			
				str = '<table class="table table-striped">';
				str += '	<tr>';
				str += '		<td>';
				str += 				jsonObj["ipInterface"]["ipAddress"];
				str += '		</td>';
				str += '		<td>';
				str += '			<select name="'+jsonObj["ipInterface"]["ipAddress"]+'">';
				str += '				<option value="M" '+ (jsonObj["ipInterface"]["@isManaged"] == "M" ? "selected" : "") +' >관리</option>';
				str += '				<option value="F" '+ (jsonObj["ipInterface"]["@isManaged"] == "F" ? "selected" : "") +' >비관리</option>';
				str += '			</select>';
				str += '		</td>';
				str += '	</tr>';
				str += '</table>';
			
				/* service info */
				getServiceFromNodeidIpaddress(addServiceInfo, "${nodeId}", jsonObj["ipInterface"]["ipAddress"]);
			}
			
		}
		
		$('#interfaceInfo').prepend(str);	
			
	}
	/*//Interface info Callback */
	
	/*//service info Callback */
	function addServiceInfo(jsonObj, nodeId, ipAddress){
		
		var str ="";
		
		if(jsonObj["@count"] > 0){
			if(jsonObj["@count"] > 1){
				var serviceObj = jsonObj["service"];
				str += "<h5>"+ipAddress+"</h5>";
				str += '<table class="table table-striped">';
				str += '	<tr>';

				for(var i in serviceObj){
					
					if(i % 6 == 0 && i != 0){
						str += '<tr></tr>';
					}
					
					str += '		<td class="span2">';
					str += '			<label class="checkbox">';
					str += '				<input value="'+serviceObj[i]["@status"]+'" name="'+ipAddress+'" id="'+serviceObj[i]["serviceType"]["name"]+'"  type="checkbox" '+ (serviceObj[i]["@status"] != "S" ? "checked" : "" ) +'/> ';
					str +=					serviceObj[i]["serviceType"]["name"];
					str += '			</label>';
					str += '		</td>';
									
				}

				str += '	</tr>';
				str += '</table>';
					
			}else{
				str += "<h5>"+ipAddress+"</h5>";
				str += '<table class="table table-striped">';
				str += '	<tr>';
				str += '		<td>';
				str += '			<label class="checkbox">';
				str += '				<input value="'+jsonObj["service"]["@status"]+'" name="'+ipAddress+'" id="'+jsonObj["service"]["serviceType"]["name"]+'" type="checkbox" '+ (jsonObj["service"]["@status"] != "S" ? "checked" : "" ) +'/> ';
				str +=					jsonObj["service"]["serviceType"]["name"];
				str += '			</label>';
				str += '		</td>';
				str += '	</tr>';
				str += '</table>';
			
			}
			
		}
		
		$('#serviceInfo').prepend(str);
		
	}
	/* service info Callback */
	
	
	/* SNMP Interface info Callback */
	function addSnmpInterfaceInfo(jsonObj){
		
		var str = "";
		if(jsonObj["@count"] > 0){
			
			
			str += '<table class="table table-striped">';
			str += '<tr><th>아이디</th><th>SNMP 종류</th><th>설명</th><th>수집 설정</th></tr>';
			var snmpInterObj = jsonObj["snmpInterface"];
			if(jsonObj["@count"] > 1){
			
				for(var i in snmpInterObj)
				{
					str += "<tr>";
					str += "	<td>";
					str += snmpInterObj[i]["@ifIndex"];
					str += "	</td>";
					str += "	<td>";
					str += snmpInterObj[i]["ifType"];
					str += "	</td>";
					str += "	<td>";
					str += snmpInterObj[i]["ifDescr"];
					str += "	</td>";
					str += "	<td>";
					str += "		<select name='"+snmpInterObj[i]["@ifIndex"]+"' class='span10'>";
					str += "			<option value='UC' "+( snmpInterObj[i]["@collectFlag"] == "UC" ? "selected" : "")+" >수집</option>";
					str += "			<option value='UN' "+( snmpInterObj[i]["@collectFlag"] == "UN" ? "selected" : "")+">수집 않음</option>";
					str += "			<option value='D' "+( snmpInterObj[i]["@collectFlag"] == "D" ? "selected" : "")+">기본</option>";
					str += "		</select>";
					str += "	</td>";
					str += "<tr>";
			}	
				
			}else{
				
				str += "<tr>";
				str += "	<td>";
				str += snmpInterObj["@ifIndex"];
				str += "	</td>";
				str += "	<td>";
				str += snmpInterObj["ifType"];
				str += "	</td>";
				str += "	<td>";
				str += snmpInterObj["ifDescr"];
				str += "	</td>";
				str += "	<td>";
				str += "		<select name='"+snmpInterObj["@ifIndex"]+"' class='span10'>";
				str += "			<option value='UC' "+( snmpInterObj["@collectFlag"] == "UC" ? "selected" : "")+" >수집</option>";
				str += "			<option value='UN' "+( snmpInterObj["@collectFlag"] == "UN" ? "selected" : "")+">수집 않음</option>";
				str += "			<option value='D' "+( snmpInterObj["@collectFlag"] == "D" ? "selected" : "")+">기본</option>";
				str += "		</select>";
				str += "	</td>";
				str += "<tr>";

			}
			
			str += '</table>';
			
		}
		$('#snmpInfo').prepend(str);
		
	}
	/*//SNMP Interface info Callback */
	
	/*change Node Label script */
	function chgNodeLabel(){
		
		var result = changeNodeLabel("${nodeId}", $('#nodeLabel').val());
		if(result == true){alert("변경되었습니다.");}
		
	}
	/*//change Node Label script */

	
	/* interface Manage script */
	function interfaceManage(){
		var select = $('#interfaceInfo select');
		var result = true; 
		for(var i = 0 ; i < select.length ; i++){
			
			var isManaged = $('#interfaceInfo select:eq('+i+')').val();
			var ipAddress = $('#interfaceInfo select:eq('+i+')').attr("name");
			
			if(!manageInteface("${nodeId}", ipAddress, isManaged)){
				result = false;
			}
			
		}
		
		if(result == true){
			alert("변경되었습니다.");
		}
		
	}
	/*//interface Manage script */
	
	/* interface Manage script */
	function serviceManage(){
		
		var result = true;
		var chkObj = $('#serviceInfo input[type=checkbox]');

		for(var i = 0 ; i < chkObj.length ; i++){
			
			var chk = $('#serviceInfo input[type=checkbox]:eq('+i+')');
			var status = "";
			
			if($(chk).attr("checked") != "checked"){
				status = "S";
			}else if($(chk).attr("checked") == "checked" && $(chk).val() == "S"){
		 		status = "R";
			}
			
			if(status == ""){continue;}	
			
			var serviceName = $(chk).attr("id");
			var ipAddress = $(chk).attr("name");
		
 			if(!manageService("${nodeId}", ipAddress, serviceName, status)){
 				result = false;
 			}else{
 				$(chk).val(status);
 			}
 			
			
		}
		
		if(result == true){
			alert("변경되었습니다.");
		}
		
	}
	/*//interface Manage script */
	
	/* snmp Manage script */
	function snmpManage(){
		
		var result = true;
		var selectObj = $('#snmpInfo select');
		
		for(var i = 0 ; i < selectObj.length ; i++){
			
			var nodeId = "${nodeId}";
			var ifIndex = $(selectObj[i]).attr("name");
			var collect = $(selectObj[i]).val();
			
			if(!manageSnmpService(nodeId, ifIndex, collect)){
 				result = false;
 			}
			
		}
		
		if(result == true){
			alert("변경되었습니다.");
		}
		
	}
	/*//snmp Manage script */
</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value="/index.do" />">Home</a> <span class="divider">/</span></li>
					<li><a href="<c:url value="/admin/node.do" />">운영관리</a> <span class="divider">/</span></li>
					<li><a href="<c:url value="/admin/node.do" />">노드 관리 목록</a> <span class="divider">/</span></li>
					<li class="active">노드 관리</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		
		<div class="row-fluid">
			<div class="row-fluid">
				<div class="span12 well well-small">
					<h4 id="nodeCount">노드&nbsp;관리</h4>
				</div>
			</div>
			<div class="row-fluid">
				<div class="row-fluid">
					<h5>노드명&nbsp;변경</h5>
				</div>
				<div class="well well-small">
					<form class="form-inline">
						<input type="text" id="nodeLabel" name="nodeLabel"/>
						<button type="button" class="btn btn-primary" title="수정"
					onclick="javascript:chgNodeLabel();">수정</button>
					</form>
				</div>
			</div>
			<div class="row-fluid">
				<div class="row-fluid">
					<h5>인터페이스&nbsp;관리</h5>
				</div>
				<div class="well well-small">
					<form class="form-inline" id="interfaceInfo">
						<div class="span11"></div>
						<button type="button" class="btn btn-primary" title="수정" onclick="javascript:interfaceManage();">수정</button>
					</form>
				</div>
				<div class="row-fluid">
					<h5>서비스&nbsp;관리</h5>
				</div>
				<div class="well well-small">
					<form class="form-inline" id="serviceInfo">
						<div class="span11"></div>
						<button type="button" class="btn btn-primary" title="수정" onclick="javascript:serviceManage();">수정</button>
					</form>
				</div>
				<div class="row-fluid">
					<h5>SNMP 관리</h5>
				</div>
				<div class="well well-small">
					<form class="form-inline" id="snmpInfo">
						<div class="span11"></div>
						<button type="button" class="btn btn-primary" title="수정" onclick="javascript:snmpManage();">수정</button>
					</form>
				</div>
			</div>
		</div>
		<hr>
	</div>
	<!-- /container -->
</body>
</html>
