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
function togling(){
    var b = document.getElementById('ShowTwo').style.display;
    document.getElementById('ShowTwo').style.display=(b=='none')?'block':'none';
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
								<th>-------------------------------------------------------------------------------------------------이벤트 리스트----------------------------------------------------------------------------------------</th>
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
					<!-- <a type="button" class="btn btn-primary" title="" href="/v1/admin/setting/addMessage.do">선택</a> -->
					<a type="button" class="btn btn-primary" href="#togle" onclick="togling()" class="toggle">선택</a>
					</div>
				</div>
			</div>
			
			<div class="togglelayer"></div>
			<div id="ShowTwo" style="display:none;">
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
								<a type="button" class="btn" title="" href="#popDestinationPaths" data-toggle="modal">목적지관리</a>
							</div>
						</div>
					</div>
				</form>
		</div>
		<div class="row-fluid">
					<div class="span12">
						<div class = "span2"></div>
						<div class="span4 controls">
							<a type="button" class="btn" title="" href="#popupRegMethod" data-toggle="modal">입력방법</a> 
						</div>
						<div class = "span2"></div>
						<div class="span4">
							<a type="button" class="btn btn-primary" title="" href="javascript:regMember()">+ 공지등록</a>
						</div>
					</div>
				</div>
			</div>
			
			<hr>
		</div>
	</div>
	<!-- /container -->
	<!-- ----------------------------------------------- --><!-- --------------------------popDestinationPaths--------------------- -->	<!-- ----------------------------------------------- -->	
					
	 <!-- Modal -->
		 <div id="popDestinationPaths" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="myModalLabel">DestinationPath</h3>
			</div>
			<div class="modal-body">
				<p>목적지 관리 1단계</p>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
				<a type="button" class="btn" title="" href="#popDestinationPathsTwo" data-toggle="modal">2단계로</a> 
			</div>
		</div> 
	
	<!-- ----------------------------------------------- --><!-- --------------------------popDestinationPathsTwo--------------------- -->	<!-- ----------------------------------------------- -->	
					
	 <!-- Modal -->
		 <div id="popDestinationPathsTwo" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="myModalLabel">Modal header</h3>
			</div>
			<div class="modal-body">
				<p>목적지 관리 2단계</p>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">1단계로</button>
				<a type="button" class="btn" title="" href="#popDestinationPathsThree" data-toggle="modal">3단계로</a> 
			</div>
		</div> 
	<!-- ----------------------------------------------- --><!-- --------------------------popDestinationPathsThree--------------------- -->	<!-- ----------------------------------------------- -->	

 <!-- Modal -->
	 <div id="popDestinationPathsThree" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">Modal header</h3>
		</div>
		<div class="modal-body">
			<p>목적지 관리 3단계</p>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">3단계로</button>
			<button class="btn btn-primary">목적지 저장</button>
		</div>
	</div> 
	<!-- ----------------------------------------------- -->	<!-- ----------------------------------------------- -->	<!-- ----------------------------------------------- -->
				 <!-- Modal -->
						 <div id="popupRegMethod" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
								<h3 id="myModalLabel">입력방법</h3>
							</div>
							<div class="modal-body">
								<p>입력방법 설명 Popup창</p>
							</div>
							<div class="modal-footer">
								<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
							</div>
						</div> 
	
</body>
</html>
