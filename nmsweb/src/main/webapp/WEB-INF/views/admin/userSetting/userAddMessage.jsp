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
							2단계&nbsp;&nbsp;<span class="label label-alert">공지 메시지 정의</span>
						</h3>
					</div>
				</div>
				<form id="memberInfoFrm" name = "memberInfoFrm" method="post">
					<div class="row-fluid">
						<div class="span12">
							<label class="span2 control-label">메시지 명</label>
							<div class="span10 controls" >
								<input  type="text"   id=""   name="" class="span11"   placeholder=""> 
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
							<label class="span2 control-label">설명</label>
							<div class="span10 controls" >
								<input  type="text"   id=""   name="" class="span11"   placeholder=""> 
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
							<label class="span2 control-label">메일 제목</label>
							<div class="span10 controls" >
								<input  type="text"   id=""   name="" class="span11"   placeholder=""> 
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
							<label class="span2 control-label">요약 메세지</label>
							<div class="span10 controls" >
								<textarea rows="3"    id=""   name="" class="span11"   placeholder=""></textarea>
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
							<label class="span2 control-label">메세지</label>
							<div class="span10 controls" >
								<textarea rows="3"    id=""   name="" class="span11"   placeholder=""></textarea>
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
							<label class="span2 control-label">목적지 선택</label>
							<div class="span10 controls" >
								<input  type="text"   id=""   name="" class="span11"   placeholder=""> 
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
							<label class="span2 control-label">이름</label>
							<div class="span4 controls">
								<select>
								  <option>1</option>
								  <option>2</option>
								  <option>3</option>
								  <option>4</option>
								  <option>5</option>
								</select>
							</div>
							<div class= "span2"></div>
							<div class="span4 controls">
								<a type="button" class="btn btn-primary" title="" href="javascript:regMember()">목적지관리</a>
							</div>
						</div>
					</div>
				</form>
			</div>
				<div class="row-fluid">
					<div class="span12">
						<div class = "span2"></div>
						<div class="span4 controls">
							<a type="button" class="btn btn-primary" title="" href="javascript:regMember()">+ 공지등록</a> 
						</div>
						<div class = "span2"></div>
						<div class="span4">
							<a type="button" class="btn btn-primary" title="" href="javascript:regMember()">입력방법</a>
						</div>
					</div>
				</div>
		</div>
	</div>
	<!-- /container -->
</body>
</html>
