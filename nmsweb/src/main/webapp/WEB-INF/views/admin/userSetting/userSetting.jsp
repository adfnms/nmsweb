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
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script src="<c:url value="/resources/js/category.js" />"></script>


<script type="text/javascript">
var limit =5;
var offset = 0;
//var pageNum = 1;
var time = new Date().format("yyyy-MM-dd hh:mm:ss");

//var reDate	=	time.addDate(1); 
var encodeTime = encodeURI(time);
	$(document).ready(function() {
		
		/*--------------------시간함수----------------------*/
		
		
		/*-------------------- //시간함수----------------------*/
		
		/*Your outstanding notices*/
		//getUserNotiList(userNotificationList, "${userId}", encodeTime, "5" );
		getUserNotiList(userNotificationList, "${userId}", encodeTime, "limit="+limit+"&offset="+offset );
		/*All outstanding notices*/
		getTotalNotiList(totalNotificationList, encodeTime,  "limit="+limit+"&offset="+offset);
		
		/* All acknowledged notices*/
		//getAllAcknowledged(allAcknowledgedList, encodeTime, "5");
		
		
		
		
	});
	
	
	
	
	
	
	/* My Notification Callback */
	function userNotificationList(jsonObj) {
	
		
		//console.log(jsonObj["notifications"]);
		var obj = jsonObj["notifications"];
		 
		if(obj == "null"){
				
			  var str = getFailJsonObj();
				$('#userTable').append(str);
				
			}else{
				var str = userNotiListjsonObj(jsonObj);
				$('#userTable').append(str);
				
				
			}
		//var MyNoti = ('${myNotification}');
		
		
		/* if(jsonObj.result == "success")
		{
		var str = userNotiListjsonObj(jsonObj,MyNoti);
		$('#userTable').append(str);
		}else{
			//console.log(jsonObj);
			var str = getFailJsonObj();
			$('#userTable').append(str);
		} */
		

	}
	/*//My Notification Callback*/

	
	
	/* total Notification Callback */
	  function totalNotificationList(jsonObj) {
		
		 
		 
		  var str = totalNotiListjsonObj(jsonObj);
		  
		  $('#totalTable').append(str);
		  
		  var userObj = jsonObj["notifications"];
			
		//var	totalLength = userObj.length;
		  
		 // getPagingHtml($('#pagingDiv'), "goSearchPageing",totalLength, pageNum, "10", "10" );
	}  
	/*//total Notification Callback*/
	//페이징 처리 스크립트
	/* function goSearchPageing(pageNm){
		
		pageNum = pageNm;
		
		offset = (parseInt(pageNm)-1) * limit;
		
		getTotalNotiList(totalNotificationList, encodeTime,  "limit="+limit+"&offset="+offset);
		
				
	} */
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
					<div class="span1 text-error"><h4 style="margin-left: 15px;">${userId}</h4></div>
					<div class="span3"><h4 style="margin-left: -10px;"> was notified</h4></div>
					<div class="span8" style="margin-top: 6px;"><jsp:include page="/include/statsBar.jsp" /></div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12 well well-small">
					<div class="row-fluid">
						<table class="table table-striped table-condensed" id="userTable">
							<tr>
								<th class="span1"><h4>ID</h4></th>
								<th class="span1"><h4>EventId</h4></th>
								<th class="span1"><h4>Status</h4></th>
								<th class="span2"><h4>PageTime</h4></th>
								<!-- <th class="span1"><h4>Interface</h4></th> -->
								<th class="span6"><h4>Message</h4></th>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12">
					<div class="span1 text-error"><h4 style="margin-left: 15px;">All</h4></div>
					<div class="span8"><h4 style="margin-left: -43px;"> outstanding notices</h4></div>
					<div class="span3"></div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12 well well-small">
					<div class="row-fluid">
						<table class="table table-striped table-condensed" id="totalTable">
							<tr>
								<th class="span1"><h4>ID</h4></th>
								<th class="span1"><h4>EventId</h4></th>
								<th class="span1"><h4>Status</h4></th>
								<th class="span2"><h4>PageTime</h4></th>
								<!-- <th class="span1"><h4>Interface</h4></th> -->
								<th class="span6"><h4>Message</h4></th>
							</tr>
						</table>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12" id="pagingDiv"></div>
				</div>
			</div>
			
		</div>
		<div class="row-fluid">
				<div class="span12">
				<div class="span9" style="width: 80.358974%;"></div>
					<div class="span2" style="width: 16.529915%;">
						<a type="button" class="btn btn-primary" title="" href="/v1/admin/setting/configureNotification.do">configure notification</a>
					</div>
				</div>
			</div>
		</div>
		
		
	
	<!-- /container -->
</body>
</html>
