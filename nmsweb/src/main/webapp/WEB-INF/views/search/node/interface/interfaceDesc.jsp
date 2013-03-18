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
	<jsp:param value="인터페이스 상세보기" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script src="<c:url value="/resources/js/outages.js" />"></script>
<script src="<c:url value="/resources/js/events.js" />"></script>
<script src="<c:url value="/resources/js/service.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {

		//getSpecificNode(addNodeDesc, "${nodeId}");
		
		/* Recent Outages */
		getOutagesForInterface(addOutages, "${nodeId}", "${intf}","5");

		/* Recent Events */
		getEventsForInterface(addEvents, "${nodeId}", "${intf}","5");
		
		/* Availability */
		getServiceFromNodeidIpaddress(addAvailability, "${nodeId}", "${intf}");
		
	});
	
	/* Recent Outages Callback*/
	function addOutages(jsonObj) {

		var str = getTabletagToOutageJsonObj(jsonObj,"${nodeId}");
		$('#rightDiv').append(str);

	}
	/*//Recent Outages Callback */
	
	/* Recent Events Callback */
	function addEvents(jsonObj) {

		var str = getTabletagToEventJsonObj(jsonObj);
		$('#rightDiv').append(str);

	}
	/*//Recent Events Callback */
	
	/* Availability Callback */
	function addAvailability(jsonObj, ipAddress){
		
		$("#availDiv").empty();
		var str = getTabletagToServiceJsonObj(jsonObj);
		
		$("#availDiv").append(str);
		
	}
	/*//Availability Callback */
</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value="/index.do" />" />Home</a> <span class="divider">/</span></li>
					<li><a href="<c:url value="/search/node.do" />" />노드검색</a> <span class="divider">/</span></li>
					<li><a href="<c:url value="/search/node/nodeDesc.do?nodeId=${nodeId}" />" />노드</a> <span class="divider">/</span></li>
					<li class="active">인터페이스</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
			<div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span9">
						<h4 id="nodeLabel">인터페이스 정보</h4>
					</div>
					<div class="span3">
						<jsp:include page="/include/statsBar.jsp" />
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span4" id="leftDiv">
				<div class="row-fluid">
					<h5>일반</h5>
				</div>
				<div class="row-fluid">
					<div class="span12 well well-small">
						<table class='table table-striped'>
							<tr>
								<th>노드</th>
								<td>192.168.0.1</td>
							</tr>
							<tr>
								<th>폴링 상태</th>
								<td>192.168.0.1</td>
							</tr>
							<tr>
								<th>폴링 페키지</th>
								<td></td>
							</tr>
							<tr>
								<th>Interface Index</th>
								<td></td>
							</tr>
							<tr>
								<th>Last Service Scan</th>
								<td></td>
							</tr>
						</table>
					</div>
				</div>
				<div class="row-fluid">
					<h5>가용성</h5>
				</div>
				<div class="row-fluid">
					<div class="span12 well well-small" id="availDiv">정보가 없습니다.</div>
				</div>
			</div>
			<div class="span8" id="rightDiv">
				<table>
				
				</table>
			</div>
		</div>

	</div>
	<hr>
	<!-- /container -->
</body>
</html>
