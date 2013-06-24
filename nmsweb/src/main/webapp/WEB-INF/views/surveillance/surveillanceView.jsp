<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<%@page import="com.bluecapsystem.nms.define.Define"%>
<%
boolean g_isLogin 	= false;
String userId = null;
try{
	userId	= session.getAttribute(Define.USER_ID_KEY).toString(); 
}catch(Exception ex){
	g_isLogin = false;
}
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="/include/header.jsp">
	<jsp:param value="사용자등록" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>

<script src="<c:url value="/resources/js/users.js" />"></script>
<script src="<c:url value="/resources/js/requisitions.js" />"></script>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	getSurveillanceNodeId();
	
	
});	
	
	
	// Reg User Info
	function getSurveillanceNodeId(){
			
			$.ajax({
		
				type : 'get',
			//	url : '/' + version + '/nodes',
				url : '<c:url value="/getSurveillance"/>',
				contentType : 'application/json',
				dataType : 'json',
				error : function() {
					alert('노드 리스트 가져오기 서비스 실패');
				},
				success : function(data) {
					console.log("getSurveillanceNodeId");
					console.log(data);
					
					/* for ( var i in data.node) {
						var	nodeid=data.node[i]["@id"];
						
						alert("nodeId :"+nodeid);
						
						
						
						
						
						var category = data.node[i]["categories"];
						
						
						alert("name :"+category[i]["@name"]);
						
							
						
							
						
					
					
					} */
 					if( data.nodeId.length >0) {
				//	alert(data.nodeId.length);
						for ( var i in data.nodeId) {
							
							
							
						var	nodeid=data.nodeId[i]["nodeid"];
							
							alert(nodeid);
							$.ajax({
								type : 'get',
								url : '/' + version + '/nodes/'+data.nodeId[i]["nodeid"]+'/categories ',
								dataType : 'json',
								contentType : 'application/json',
								error : function(data) {
									alert('노드별 카테고리그룹정보 실패');
								},
								success : function(data) {
									var sumSER_DEV = 0;
									var sumSER_TEST = 0;
									var sumSER_PROD = 0;
									
									var sumTEST_ROUT = 0;
									var sumDEV_POUT = 0;
									var sumPRO_ROUT = 0;
									
									var sumPRO_SWIT = 0;
									var sumDEV_SWIT = 0;
									var sumTEST_SWIT = 0;
									
									for ( var i in data.category) {
											
									alert(data.category[i]["@name"]);
										
										
										 	if((data.category[i]["@name"] == "Servers")){
												alert("server & Development");
												 sumSER_DEV+=1;
												alert("-------------sumSER_DEV-계산값------------"+sumSER_DEV);
												$("#SERVERS_DEVELOPMENT").html("0 of "+sumSER_DEV);
												return  sumSER_DEV; 
												
											}
											
											 if(data.category[i]["@name"]== "Servers" && data.category[i]["@name"]=="Test"){
												alert("server & Test");
												sumSER_TEST+=1;
												alert("-------------sumSER_TEST-계산값------------"+sumSER_TEST);
												$("#SERVERS_TEST").html("0 of "+sumSER_TEST);
												return  sumSER_TEST;
											}
											
											if(data.category[i]["@name"]== "Servers" && data.category[i]["@name"]=="Production"){
												alert("server & Production");
												sumSER_PROD+=1;
												alert("-------------sumSER_PROD-계산값------------"+sumSER_PROD);
												$("#SERVERS_PRODUCTION").html("0 of "+sumSER_PROD);
												return  sumSER_PROD; 
											}
											
											if(data.category[i]["@name"]== "Test" && data.category[i]["@name"]=="Routers"){
												alert("Test & Routers");
												sumTEST_ROUT+=1;
												alert("-------------sumTEST_ROUT-계산값------------"+sumTEST_ROUT);
												$("#ROUTERS_TEST").html("0 of "+sumTEST_ROUT);
												return  sumTEST_ROUT;
											}
											
											if(data.category[i]["@name"]== "Development" && data.category[i]["@name"]=="Routers"){
												alert("Development & Routers");
												sumDEV_POUT+=1;
												alert("-------------sumDEV_POUT-계산값------------"+sumDEV_POUT);
												$("#ROUTERS_DEVELOPMENT").html("0 of "+sumDEV_POUT);
												return  sumDEV_POUT;
											}
											
											if(data.category[i]["@name"]== "Production" && data.category[i]["@name"]=="Routers"){
												alert("Production & Routers");
												sumPRO_ROUT+=1;
												alert("--------------sumPRO_ROUT계산값------------"+sumPRO_ROUT);
												$("#ROUTERS_PRODUCTION").html("0 of "+sumPRO_ROUT);
												return  sumPRO_ROUT;
											}
											
											if(data.category[i]["@name"]== "Production" && data.category[i]["@name"]=="Switches"){
												alert("Production & Switches");
												sumPRO_SWIT+=1;
												alert("-------------- sumPRO_SWIT계산값------------"+sumPRO_SWIT);
												$("#SWITCHES_PRODUCTION").html("0 of "+sumPRO_SWIT);
												return  sumPRO_SWIT;
											}
											
											if(data.category[i]["@name"]== "Development" && data.category[i]["@name"]=="Switches"){
												alert("Development & Switches");
												sumDEV_SWIT+=1;
												alert("--------------sumDEV_SWIT계산값------------"+sumDEV_SWIT);
												$("#SWITCHES_DEVELOPMENT").html("0 of "+sumDEV_SWIT);
												return  sumDEV_SWIT;
											}
											
											if(data.category[i]["@name"]== "Test" && data.category[i]["@name"]=="Switches"){
												alert("Test & Switches");
												sumTEST_SWIT+=1;
												alert("--------------sumDEV_SWIT계산값------------"+sumTEST_SWIT);
												$("#SWITCHES_TEST").html("0 of "+sumTEST_SWIT);
												return  sumTEST_SWIT;
											}
										
										
									}
									console.log(data);
									alert(nodeid);
									/* if(data.category[0]["@name"]!=null || data.category[0]["@id"]!=null){
										
										
									 	if((data.category[i]["@name"]== "Servers" && data.category[i]["@name"]=="Development")||(data.category[i]["@name"]== "Servers" && data.category[i]["@name"]=="Development")){
											alert("server & Development");
											 sumSER_DEV+=1;
											alert("-------------sumSER_DEV-계산값------------"+sumSER_DEV);
											$("#SERVERS_DEVELOPMENT").html("0 of "+sumSER_DEV);
											return  sumSER_DEV; 
											
											
											
											
										}
										
										 if((data.category[1]["@name"]== "Servers" && data.category[0]["@name"]=="Test")||(data.category[0]["@name"]== "Servers" && data.category[1]["@name"]=="Test")){
											alert("server & Test");
											sumSER_TEST+=1;
											alert("-------------sumSER_TEST-계산값------------"+sumSER_TEST);
											$("#SERVERS_TEST").html("0 of "+sumSER_TEST);
											return  sumSER_TEST;
										}
										
										if((data.category[1]["@name"]== "Servers" && data.category[0]["@name"]=="Production")||(data.category[0]["@name"]== "Servers" && data.category[1]["@name"]=="Production")){
											alert("server & Production");
											sumSER_PROD+=1;
											alert("-------------sumSER_PROD-계산값------------"+sumSER_PROD);
											$("#SERVERS_PRODUCTION").html("0 of "+sumSER_PROD);
											return  sumSER_PROD; 
										}
										
										if((data.category[1]["@name"]== "Test" && data.category[0]["@name"]=="Routers")||(data.category[0]["@name"]== "Test" && data.category[1]["@name"]=="Routers")){
											alert("Test & Routers");
											sumTEST_ROUT+=1;
											alert("-------------sumTEST_ROUT-계산값------------"+sumTEST_ROUT);
											$("#ROUTERS_TEST").html("0 of "+sumTEST_ROUT);
											return  sumTEST_ROUT;
										}
										
										if((data.category[1]["@name"]== "Development" && data.category[0]["@name"]=="Routers")||(data.category[0]["@name"]== "Development" && data.category[1]["@name"]=="Routers")){
											alert("Development & Routers");
											sumDEV_POUT+=1;
											alert("-------------sumDEV_POUT-계산값------------"+sumDEV_POUT);
											$("#ROUTERS_DEVELOPMENT").html("0 of "+sumDEV_POUT);
											return  sumDEV_POUT;
										}
										
										if((data.category[1]["@name"]== "Production" && data.category[0]["@name"]=="Routers")||(data.category[0]["@name"]== "Production" && data.category[1]["@name"]=="Routers")){
											alert("Production & Routers");
											sumPRO_ROUT+=1;
											alert("--------------sumPRO_ROUT계산값------------"+sumPRO_ROUT);
											$("#ROUTERS_PRODUCTION").html("0 of "+sumPRO_ROUT);
											return  sumPRO_ROUT;
										}
										
										if((data.category[1]["@name"]== "Production" && data.category[0]["@name"]=="Switches")||(data.category[0]["@name"]== "Production" && data.category[1]["@name"]=="Switches")){
											alert("Production & Switches");
											sumPRO_SWIT+=1;
											alert("-------------- sumPRO_SWIT계산값------------"+sumPRO_SWIT);
											$("#SWITCHES_PRODUCTION").html("0 of "+sumPRO_SWIT);
											return  sumPRO_SWIT;
										}
										
										if((data.category[1]["@name"]== "Development" && data.category[0]["@name"]=="Switches")||(data.category[0]["@name"]== "Development" && data.category[1]["@name"]=="Switches")){
											alert("Development & Switches");
											sumDEV_SWIT+=1;
											alert("--------------sumDEV_SWIT계산값------------"+sumDEV_SWIT);
											$("#SWITCHES_DEVELOPMENT").html("0 of "+sumDEV_SWIT);
											return  sumDEV_SWIT;
										}
										
										if((data.category[1]["@name"]== "Test" && data.category[0]["@name"]=="Switches")||(data.category[0]["@name"]== "Test" && data.category[1]["@name"]=="Switches")){
											alert("Test & Switches");
											sumTEST_SWIT+=1;
											alert("--------------sumDEV_SWIT계산값------------"+sumTEST_SWIT);
											$("#SWITCHES_TEST").html("0 of "+sumTEST_SWIT);
											return  sumTEST_SWIT;
										}
									} */
								}
							});
						}
						
					}; 
				}
			});
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</script>
</head>

<body>

	<div class="container">

		<jsp:include page="/include/menu.jsp" />
		
		<!-- Example row of columns -->
		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li><a href="/v1/surveillance.do">Surveillance</a></li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
			<h3 style = "margin-top: -9px;"><code>Surveillance View</code></h3>
			<div class="bs-docs-example">
			<form id="userInfoFrm" name = "userInfoFrm">
				<input type="hidden" name="regrId" value="<%= userId %>"  protect="true" />
				<input type="hidden" name="modrId" value="<%= userId %>"  protect="true" />
			</form>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Nodes Down</th>
							<th class="info">PRODUCTION</th>
							<th class="info" style ="width: 234px;">TEST</th>
							<th class="info">DEVELOPMENT</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="info"><strong>ROUTERS</strong></td>
							<td id="ROUTERS_PRODUCTION">0 of 0</td>
							<td id="ROUTERS_TEST">0 of 0</td>
							<td id="ROUTERS_DEVELOPMENT">0 of 0</td>
						</tr>
						<tr>
							<td class="info"><strong>SWITCHES</strong></td>
							<td id="SWITCHES_PRODUCTION">0 of 0</td>
							<td id="SWITCHES_TEST">0 of 0</td>
							<td id="SWITCHES_DEVELOPMENT">0 of 0</td>
						</tr>
						<tr>
							<td class="info"><strong>SERVERS</strong></td>
							<td id="SERVERS_PRODUCTION">0 of 0</td>
							<td id="SERVERS_TEST">0 of 0</td>
							<td id="SERVERS_DEVELOPMENT">0 of 0</td>
						</tr>
					</tbody>
				</table>
			</div>
		
			<hr>
		</div>
	</div>
	<!-- /container -->
</body>
</html>
