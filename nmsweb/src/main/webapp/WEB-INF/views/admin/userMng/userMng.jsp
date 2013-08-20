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
	<jsp:param value="회원가입" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/users.js" />"></script>
<script src="<c:url value="/resources/js/requisitions.js" />"></script>
<script src="<c:url value="/resources/js/notification.js" />"></script>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script src="<c:url value="/resources/js/category.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		/* 사용자 전체 리스트 */
		/* GETGet a list of users */
		getUserListTotal(callbackUseList);
		
		
		
	});
	
	
/**
 * GETGet a list of users
 * 사용자 리스트 전체가져오기
 */
 	function callbackUseList(jsonObj) {
		console.log(jsonObj);
		
		$.ajax({
			type : 'get',
			url : '<c:url value="/menu/showMenu.do" />',
			contentType : 'application/json', 
			error : function(data) {
				alert('사용자 그룹 menu리스트 서비스 실패');
			},
			success : function(data) {
				console.log(data);
				
				for(var i = 0; i < data.userList.length; i++)
	       		{
	        		var	groupName= data.userList[i].groupNm;
	       		}
				var str = userListStr(jsonObj,groupName);
				
				$("#userListTable").append(str);
			}
		});   	
		
		
		
		

	} 

/**
 * 사용자 상세정보 갖고 오기
 */
	function getUserDetail(user_id){
		
		$("#userIdModiFrm").find('[name=user-id]:input').val(user_id);
		
		 var frm = document.getElementById("userIdModiFrm");
		
		frm.action = "/v1/admin/userMng/userModify.do";
		frm.submit(); 
		
		
	}
	
	/**
	 * 사용자 상세정보 삭제하기
	 */
	function deleteUser(userId){
		
		$("#userIdModiFrm").find('[name=user-id]:input').val(userId);
		
		
	
		var option = confirm(" 삭제 하시겠습니까? ");
		
		if(option == true )
		{
			if(userId == "admin"){
				
				alert("삭제할 수 없습니다.");
				return false;
			
			}else{
				$.ajax({
					
					type : 'delete',
					//url : 'http://localhost:8080/v1/users/'+userId,
					url : '<c:url value="/users"/>/'+userId,
					contentType : 'application/json',
					dataType:'json',
					error : function(data) {
						alert('삭제 서비스 실패');
					},
					success : function(data) {
						alert("삭제성공");
						
						deleteToDb(userId);
					}
				});
			}
		
		}else if(option == false ){
		 	
			alert("취소 되었습니다.");
			}
		
	}
	
	function deleteToDb(userId){
		
		var frm = document.getElementById("userIdModiFrm");
		
		var data = $("#userIdModiFrm").serialize();
		
		frm.action = "/v1/admin/userMng/deleteToDb.do";
	    frm.submit();
	}
	
</script>
</head>

<body>


	<form  id="userIdModiFrm" name="userIdModiFrm">
		<input type="hidden" id ="user-id" name="user-id" value="" />
	</form>

	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="#">Home</a> <span class="divider">/</span></li>
					<li><a href="#">운영관리</a> <span class="divider">/</span></li>
					<li class="active">사용자 관리</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>

		<div class="row-fluid">
			<div class="span12 well well-small">
			
				<table class="table table-striped table-hover table-condensed " id="userListTable" >
					<colgroup>
						<col class="span2"/>
						<col class="span3"/>
						<col class="span4"/>
					</colgroup>
					<thead>
						<tr>
							<th><h4>사용자&nbsp;ID</h4></th>
							<th><h4>이름(FullName)</h4></th>
							<th><h4>소개</h4></th>
						</tr>
					</thead>
				</table>
			</div>
				<div class="row-fluid">
					<div class="span12">
					<div class="span10"></div>
						<div class="span2 ">
							<a type="button" class="btn btn-primary" title="" href="/v1/admin/userMng/userReg.do">+ 사용자 추가</a>
						</div>
					</div>
					
				</div>
			
				
		</div>
		<hr>
	</div>
	<!-- /container -->
	
	
	
</body>
</html>
