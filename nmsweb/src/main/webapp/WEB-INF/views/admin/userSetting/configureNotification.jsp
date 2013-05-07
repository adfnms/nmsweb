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
<script type="text/javascript">
	$(document).ready(function() {
		
		/* 추가된 공지 리스트 */
		/* Get information related with real occured all event */
		/* v1/notifications/eventNotifications */
		getAddedNotifiList(getAddNotiList);
		
		
		
	});
	
	
/**
 * Get information related with real occured all event
 * 추가된 공지 리스트
 */
 	function getAddNotiList(jsonObj) {
 		//console.log(jsonObj);
 		
		var str = addNotiStr(jsonObj);
		
		$("#notificationTable").append(str);

	} 

/**
 * 추가된 공지 리스트 상세정보 갖고 오기
 */
	function getUserDetail(user_id){
		
		$("#userIdFrm").find('[name=user-id]:input').val(user_id);
		
		var frm = document.getElementById("userIdFrm");
		
		frm.action = "/v1/admin/userMng/userModify.do";
		frm.submit();
		
		
	}
	
		/**
		*DELETE <Get the information related with real occured, certain event>
		* 추가된 공지 리스트 삭제하기	 
		*/
	function deleteNotification(name){
		
		//$("#userIdFrm").find('[name=user-id]:input').val(userId);
	
		var option = confirm(" 삭제 하시겠습니까? ");
		
		if(option == true )
		{
		
			$.ajax({
				
				type : 'delete',
				url : '/' + version + '/notifications/eventNotifications/'+name,
				contentType : 'application/json',
				dataType:'json',
				error : function(data) {
					alert('삭제 서비스 실패');
				},
				success : function(data) {
					alert("삭제성공");
				}
			});
			
		}else if(option == false ){
		 	
			alert("취소 되었습니다.");
			}
		
	}
	
	function modifyNotification(name){
		location.href ="/"+version+"/admin/setting/modifyNotification.do?name="+name;
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
					<li><a href="/v1/admin/setting.do">사용자 설정</a><span class="divider">/</span></li>
					<li class="active">공지 설정</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>

		<div class="row-fluid">
			
			
			<div class="span12 well well-small">
			<div class="row-fluid">
				<div class="span12">
				<div class="span10"></div>
					<div class="span2 ">
						<a type="button" class="btn btn-primary" title="" href="/v1/admin/setting/addEvent.do">+ 공지 추가</a>
						<!-- <a type="button" class="btn btn-primary" title="" onclick="javascript:alert('test') " >alert test</a> -->
					</div>
				</div>
				
			</div>
			<form  id="userIdFrm" name="userIdFrm" method="post">
				<input type="hidden" id ="user-id" name="user-id" value="" />
			</form>
				<table class="table table-striped table-hover table-condensed" id="notificationTable" >
				
					<thead>
						<tr>
							<th>status</th>
							<th>notification</th>
							<th>uei</th>
							<th></th>
							<th></th>
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
