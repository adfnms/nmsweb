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
	
var g_categoryHorTypes = ["Production", "Test", "Development"];
var g_categoryVertTypes = ["Routers", "Switches", "Servers"];

var g_nodes = new Array();

function getCategoryHorTypesIndex(nodeItem)
{
	for(var i = 0; i < g_categoryHorTypes.length; i++)
	{
		
		console.log("g_categoryHorTypes :"+g_categoryHorTypes[i]+" == HorcategoryName :"+nodeItem);
		
		
		if(nodeItem == g_categoryHorTypes[i])
		{
			return i;
		}
	}
	return -1;
}

function getCategoryVertTypesIndex(nodeItem)
{
	console.log("VertcategoryName :"+nodeItem);
	for(var i = 0; i < g_categoryVertTypes.length; i++)
	{
		console.log("g_categoryVertTypes :"+g_categoryVertTypes[i]+" == VertcategoryName :"+nodeItem);
		if(nodeItem== g_categoryVertTypes[i])
		{
			return i;
		}
	}
	return -1;
}


function getCountNodes(hIdx, vIdx)
{
	var count = 0;
	for(var i = 0; i< g_nodes.length; i++)
	{
		var vHas = false;
		var hHas = false;
		for(var j = 0; j < g_nodex[i].length; j++)
		{
			
			if(nodeItem["@name"] == g_categoryVertTypes[vIdx])
				vHas = true;
			
			if(nodeItem["@name"] == g_categoryHorTypes[hIdx])
				hHas = true;
		}
		
		if(vHas && hHas)
		{
			count +=1;
		}
	}
	
	return count;
}




	
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
		
 					if( data.nodeId.length >0) {

 						for ( var i = 0; i < data.nodeId.length; i++) 
						{
							nodeid=data.nodeId[i];
							
							console.log(" nodeid : "+nodeid["nodeid"]);////////////console/////////////
							
							g_nodes[i] = new Array();
							var nodeIdx = 0;
							$.ajax({
								type : 'get',
								url : '/' + version + '/nodes/'+nodeid["nodeid"]+'/categories ',
								dataType : 'json',
								contentType : 'application/json',
								error : function(data) {},
								success : function(data) 
								{
									console.log(data);////////////console/////////////
									
									for(var j = 0; j < data.category.length; j++)
									{
										categoryName=data.category[j];
										console.log("Get CategoryName in nodeId : "+categoryName["@name"]);////////////console/////////////
										
										var idx = -1;
										
										var vIdx =  getCategoryVertTypesIndex(categoryName["@name"]);
										console.log("vIdx : "+vIdx);
										var hIdx = getCategoryHorTypesIndex(categoryName["@name"]);
										console.log("hIdx : "+hIdx);
										
										if(vIdx >= 0)
										{
											idx = vIdx;
										}else if(hIdx >= 0)
										{
											idx = hIdx;
										}
										
										if(idx < 0)
										{
											continue;
										}
										g_nodes[i][nodeIdx] = data.category[j];
										nodeIdx++;
									}
									
									
							
								}// success : function(data)
							}); // $.ajax : categories
						
							/* console.log(vNodeItem);
							g_categoryCounts[g_categoryCounts.length -1] = vNodeItem; */
						} // for ( var i = 0; i < data.nodeId.length; i++)
						
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
