<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="/include/header.jsp">
	<jsp:param value="이벤트 상세보기" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/events.js" />"></script>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script src="<c:url value="/resources/js/category.js" />"></script>
<script src="<c:url value="/resources/js/outages.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		var data = "id=${eventId}";
		getTotalEvenstList(addEventInfo,data);

	});
	
	/* Event Info */
	function addEventInfo(jsonObj){
		//Event Info
		var eventInfoStr = 	getEventinfoBox(jsonObj);
		$('#eventInfoDiv').append(eventInfoStr);
		
		//Event Log
		var eventLogStr = 	getEventLogBox(jsonObj);
		$('#eventInfoDiv').append(eventLogStr);
	
		//Event Desc
		var eventDescStr = 	getEventDescBox(jsonObj);
		$('#eventInfoDiv').append(eventDescStr);
	}
	/*//Event Info */
</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value="/index.do" />" />Home</a> <span
						class="divider">/</span></li>
					<li><a href="<c:url value="/search/node.do" />" />노드검색</a>
						<span class="divider">/</span></li>
					<li class="active">이벤트<span class="divider">/</span></li>
					<li><a href="#" onclick="javascript:beforeUrl()">이전 화면</a><span class="divider">/</span></li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
			<div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span3">
						<h4 id="nodeLabel">이벤트&nbsp;정보</h4>
					</div>
					<div class="span9">
						<jsp:include page="/include/statsBar.jsp" />
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid" id="eventInfoDiv"></div>
	</div>
	<hr>
	<!-- /container -->
</body>
</html>
