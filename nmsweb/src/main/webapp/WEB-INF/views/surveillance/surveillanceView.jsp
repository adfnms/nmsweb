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
<script src="<c:url value="/resources/js/category.js" />"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	
	
	
});	
	

	
	
	
	
	
	
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
