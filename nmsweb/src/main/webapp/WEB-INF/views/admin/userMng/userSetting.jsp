<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.bluecapsystem.nms.define.NMSProperties"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/include/header.jsp">
	<jsp:param value="사용자 설정" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/users.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		
	});


</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span9">
				<ul class="breadcrumb well well-small">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li><a href="#">운영관리</a> <span class="divider">/</span></li>
					<li class="active">사용자 설정</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>

		<div class="row-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="span4"><h4>나의공지정보</h4></div>
					<div class="span3">
						<div class="progress">
						  <div class="bar bar-inverse" style="width: 20%;"></div>
						  <div class="bar bar-info" style="width: 20%;"></div>
						  <div class="bar bar-success" style="width: 20%;"></div>
						  <div class="bar bar-warning" style="width: 20%;"></div>
						  <div class="bar bar-danger" style="width: 20%;"></div>
						</div>
					</div>
					<div class="span2">
						<a type="button" class="btn btn-primary" title="" href="/v1/admin/userMng/userReg.do">+ 공지 추가</a>
					</div>
				</div>
			</div>
			<div class="span9">
				<table class="table table-striped" id="userListTable">
					<colgroup>
						<col class="span1"/>
						<col class="span2"/>
						<col class="span2"/>
						<col class="span2"/>
						<col class="span2"/>
					</colgroup>
					<thead>
						<tr>
							<th>ID</th>
							<th>상태</th>
							<th>공지시간</th>
							<th>Interface</th>
							<th>로그</th>
						</tr>
					</thead>
				</table>
			</div>
			<hr>
			<div class="row-fluid">
				<div class="span12">
					<div class="span4"><h4>전체공지정보</h4></div>
					<div class="span3">
						
					</div>
					<div class="span2">
					</div>
				</div>
			</div>
			<div class="span9">
				<table class="table table-striped" id="userListTable">
					<colgroup>
						<col class="span1"/>
						<col class="span2"/>
						<col class="span2"/>
						<col class="span2"/>
						<col class="span2"/>
					</colgroup>
					<thead>
						<tr>
							<th>ID</th>
							<th>상태</th>
							<th>공지시간</th>
							<th>Interface</th>
							<th>로그</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<hr>
		
		
	</div>
	<!-- /container -->
</body>
</html>
