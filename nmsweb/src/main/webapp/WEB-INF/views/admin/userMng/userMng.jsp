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
<script type="text/javascript">
	$(document).ready(function() {
		
		//전체 리스트 
		getUserListTotal(callbackUseList);

	});

	function callbackUseList(jsonObj) {
		console.log(jsonObj);
		var str = "";

		var userObj = jsonObj["user"];
		
		for ( var i in userObj) {
			str += "<tr>";
			str += "	<td onclick=\"javascript:getUserDetail('"+userObj[i]["user-id"]+"');\">";
			str += userObj[i]["user-id"];
			str += "	</td>";
			str += "	<td onclick=\"javascript:getUserDetail('"+userObj[i]["user-id"]+"');\">";
			str += userObj[i]["full-name"];
			str += "	</td>";
			str += "	<td onclick=\"javascript:getUserDetail('"+userObj[i]["user-id"]+"');\">";
			str += userObj[i]["user-comments"];
			str += "	</td>";
			str += "	<td>";
 			str += "<a type=\"button\" class=\"btn\" href=\"javascript:deleteUser('"+userObj[i]["user-id"]+"');\">삭제</a>";
 			str += "	</td>";
			str += "</tr>";
		}

		$("#userListTable").append(str);
	}

	function getUserDetail(user_id){
		
		$("#userIdFrm").find('[name=user-id]:input').val(user_id);
		
		var frm = document.getElementById("userIdFrm");
		
		frm.action = "/v1/admin/userMng/userModify.do";
		frm.submit();
		
		
	}
	
function deleteUser(userId){
	
	$("#userIdFrm").find('[name=user-id]:input').val(userId);
		
		$.ajax({
			
			type : 'delete',
			url : 'http://localhost:8080/v1/users/'+userId,
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
	
function deleteToDb(userId){
	
	//var userId = $("#userInfoFrm input[name=user-id]").val();
	
	var frm = document.getElementById("userIdFrm");
	
	
	//frm.action = '<c:url value="/admin/userMng/modifyToDb.do"/>';
	frm.action = "/v1/admin/userMng/deleteToDb.do";
    frm.submit();
}
</script>
</head>

<body>
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
			<div class="span12">
			<form  id="userIdFrm" name="userIdFrm" method="post">
				<input type="hidden" id ="user-id" name="user-id" value="" />
			</form>
				<table class="table table-striped" id="userListTable" >
					<colgroup>
						<col class="span2"/>
						<col class="span3"/>
						<col class="span4"/>
					</colgroup>
					<thead>
						<tr>
							<th>사용자 ID</th>
							<th>이름(FullName)</th>
							<th>소개</th>
						</tr>
					</thead>
					
				</table>
			</div>
				<div class="row-fluid">
					<div class="span12">
						<div class="span10"></div>
						<div class="span2">
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
