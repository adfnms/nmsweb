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
<script src="<c:url value="/resources/js/notification.js" />"></script>

<script type="text/javascript">
	$(document).ready(function() {
		
		/*--------------------시간함수----------------------*/
		var time = new Date().format("yyyy-MM-dd hh:mm:ss");
		
		//var reDate	=	time.addDate(1); 
		var encodeTime = encodeURI(time);
		
		/*-------------------- //시간함수----------------------*/
		
		/*Your outstanding notices*/
		getUserNotiList(userNotificationList, "${userId}", encodeTime, "5");
		
		/*All outstanding notices*/
		getTotalNotiList(totalNotificationList, encodeTime, "5");
		
		/* All acknowledged notices*/
		//getAllAcknowledged(allAcknowledgedList, encodeTime, "5");
		
		
		
		
	});
	
	
	
	
	
	
	/* My Notification Callback */
	function userNotificationList(jsonObj) {
		
		//console.log(jsonObj);
		
		if(jsonObj.result == "success")
		{
		var str = userNotiListjsonObj(jsonObj);
		$('#userTable').append(str);
		}else{
			//console.log(jsonObj);
			var str = getFailJsonObj();
			$('#userTable').append(str);
		}
		

	}
	/*//My Notification Callback*/

	
	
	/* total Notification Callback */
	  function totalNotificationList(jsonObj) {
		  if(jsonObj.result == "success")
			{
		var str = totalNotiListjsonObj(jsonObj);
		
		$('#totalTable').append(str);
			}else{
				
				var str = getFailJsonObj();
				$('#totalTable').append(str);
			}
	}  
	/*//total Notification Callback*/
</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />
		
		<!-- Example row of columns -->
		<form id = "settingFrm" name = "settingFrm">
			<input type = "hidden" id = "userId" name = "userId" value = "${userId}" >
		</form>
		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="#">운영관리</a> <span class="divider">/</span></li>
					<li><a href="/v1/admin/setting.do">사용자 설정</a></li>
				</ul>
			</div>
				<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="span3"><h4>나의공지정보</h4></div>
					<div class="span3"></div>
					<div class="span3"><jsp:include page="/include/statsBar.jsp" /></div>
					<div class="span3">
						<a type="button" class="btn btn-primary" title="" href="/v1/admin/setting/configureNotification.do">configure notification</a>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12 well well-small">
					<div class="row-fluid">
						<table class="table table-striped table-condensed" id="userTable">
							<tr>
								<th class="span1">ID</th>
								<th class="span1">EventId</th>
								<th class="span1">Status</th>
								<th class="span2">PageTime</th>
								<th class="span1">Interface</th>
								<th class="span6">Message</th>
							</tr>
						</table>
					</div>
				</div>
			</div>
			
			
			<div class="row-fluid">
				<div class="span12">
					<div class="span4"><h4>전체공지정보</h4></div>
					<div class="span3"></div>
					<div class="span2"></div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12 well well-small">
					<div class="row-fluid">
						<table class="table table-striped table-condensed" id="totalTable">
							<tr>
								<th class="span1">ID</th>
								<th class="span1">EventId</th>
								<th class="span1">Status</th>
								<th class="span2">PageTime</th>
								<th class="span1">Interface</th>
								<th class="span6">Message</th>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<!--<hr>
		 	<div class="row-fluid">
				<div class="span12">
					<div class="span4"><h4> All acknowledged notices</h4></div>
					<div class="span3"></div>
					<div class="span2"></div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12">
					<div class="row-fluid">
						<table class="table table-striped" id="ackTable">
							<tr>
								<th class="span1">ID</th>
								<th class="span1">Status</th>
								<th class="span2">PageTime</th>
								<th class="span1">Interface</th>
								<th class="span7">Message</th>
							</tr>
						</table>
					</div>
				</div>
			</div> -->
		</div>
		<hr>
		</div>
		
		
	
	<!-- /container -->
</body>
</html>
