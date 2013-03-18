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
					<li class="active">노드 상세 정보</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
		
			<div class="span12 well well-small">
				<form id="memberInfoFrm" name = "memberInfoFrm" method="post">
					<div class="row-fluid">
						<div class="span12">
							<label class="span2 control-label">Notification Time</label>
							<div class="span4 controls" >
								<input  type="text"   id=""   name=""    placeholder=""> 
							</div>
							<label class="span2 control-label">Node</label>
							<div class="span4 controls" >
								<input  type="text"   id=""   name=""    placeholder=""> 
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
							<label class="span2 control-label">Interface</label>
							<div class="span4 controls" >
								<input  type="text"   id=""   name=""    placeholder=""> 
							</div>
							<label class="span2 control-label">Service</label>
							<div class="span4 controls" >
								<input  type="text"   id=""   name=""    placeholder=""> 
							</div>
						</div>
					</div>
				</form>
		</div>
		</div>
		<div class="row-fluid">
			<div class="span12 well well-small">
				<form id="memberInfoFrm" name = "memberInfoFrm" method="post">
					<div class="row-fluid">
						<div class="span12">
							<label class="span2 control-label">Numeric Message</label>
							<div class="span10 controls" >
								<input  type="text"   id=""   name="" class="span11"   placeholder=""> 
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
							<label class="span2 control-label">Text Message</label>
							<div class="span10 controls" >
								<input  type="text"   id=""   name="" class="span11"   placeholder=""> 
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12 well well-small">
				<table class="table table-striped" id="userListTable">
					<colgroup>
						<col class="span2"/>
						<col class="span3"/>
						<col class="span4"/>
					</colgroup>
					<thead>
						<tr>
							<th>Sent to</th>
							<th>Sent at</th>
							<th>Media</th>
						</tr>
					</thead>
					
				</table>
			</div>
		</div>
	</div>
	<!-- /container -->
</body>
</html>
