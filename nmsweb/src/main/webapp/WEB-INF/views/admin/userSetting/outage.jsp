<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<html lang="en">
<head>
<jsp:include page="/include/header.jsp">
	<jsp:param value="장애 목록 리스트" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>

<script src="<c:url value="/resources/js/users.js" />"></script>
<script src="<c:url value="/resources/js/requisitions.js" />"></script>
<script src="<c:url value="/resources/js/notification.js" />"></script>
<script src="<c:url value="/resources/js/outages.js" />"></script>
<script type="text/javascript">

	$(document).ready(function() {
		
		/* Recent Outages */
		getOutagesForNode(addOutages, "${nodeId}", "10");
		
	});
	
	/* Recent Outages Callback */
	function addOutages(jsonObj) {

		var str = getTabletagToOutageJsonObj(jsonObj,"${nodeId}");
		$('#outageDiv').append(str);

	}
	/*//Recent Outages */
	

</script>
</head>

<body>

	<div class="container">

		<jsp:include page="/include/menu.jsp" />
		
		<!-- Example row of columns -->
		
		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="#">운영관리</a> <span class="divider">/</span></li>
					<li><a href="/v1/admin/setting.do">사용자 설정</a> <span class="divider">/</span></li>
					<li class="active">장애 목록 상세 정보</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		 <div class="alert alert-error">
			  <button type="button" class="close" data-dismiss="alert">&times;</button>
			    See outages for  <strong>${nodeLabel}.</strong>  
		</div> 
		<!---------------------------------<div> 삽입--------------------------------------->
		
		<div class="row-fluid" id = "outageDiv"></div>
		
		<!---------------------------------//<div> 삽입--------------------------------------->
		
	</div>
	<!-- /container -->
</body>
</html>
 --%>