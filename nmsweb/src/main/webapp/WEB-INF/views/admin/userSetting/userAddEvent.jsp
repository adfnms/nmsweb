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
<html lang="en">
<head>
<jsp:include page="/include/header.jsp">
	<jsp:param value="사용자등록" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>

<script src="<c:url value="/resources/js/users.js" />"></script>
<script src="<c:url value="/resources/js/requisitions.js" />"></script>
<script type="text/javascript">
	
	
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
					<li class="active">공지추가</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		
		<div class="row-fluid">
		
			<div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<h3>
							1단계<span class="label label-alert">이벤트 선택</span>
						</h3>
					</div>
				</div>
				<form id="memberInfoFrm" name = "memberInfoFrm" method="post">
					<!--리스트 시작  -->	
				
				
				<div class="span12">
					<table class="table table-striped" id="userListTable">
						<colgroup>
							<col class="span10"/>
							
						</colgroup>
						<thead>
							<tr>
								<th>-------------------------------------------------------------------이벤트 리스트----------------------------------------------------------</th>
							</tr>
						</thead>
					</table>
				</div>
					
					
				<!-- 끝 -->	
				</form>
			<hr>
		</div>
			<div class="row-fluid">
				<div class="span12">
					<div class="span8"></div>
					<div class="span1">
						<a type="button" class="btn btn-primary" title="" href="/v1/admin/setting/addMessage.do">선택</a>
					</div>
				</div>
			</div>
			<hr>
		</div>
	</div>
	<!-- /container -->
</body>
</html>
