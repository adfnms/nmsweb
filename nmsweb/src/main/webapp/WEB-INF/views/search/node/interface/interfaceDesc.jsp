<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
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
<script src="<c:url value="/resources/js/category.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		/*인터페이스 정보*/
		getInterfaceInfo(addInterface, "${nodeId}", "${intf}");
		
		/* Recent Outages */
		getOutagesForInterface(addOutages, "${nodeId}", "${intf}","5");

		/* Recent Events */
		getEventsForInterface(addEvents, "${nodeId}", "${intf}","5");
		
		/* Interface Availability */
		//addAvailability("${nodeId}","${intf}");
		
	});
	
	/* Interface Callback */
	function addInterface(jsonObj) {
		
		var str = getInterfaceInfoBox(jsonObj);
		$('#rightDiv').append(str);
		
	}
	/*//Interface Callback */
	
	/* Recent Outages Callback*/
	function addOutages(jsonObj) {

		var str = getTabletagToInterfaceOutageJsonObj(jsonObj,"${nodeId}");
		$('#rightDiv').append(str);

	}
	/*//Recent Outages Callback */
	
	/* Recent Events Callback */
	function addEvents(jsonObj) {

		var str = getTabletagToInterfaceEventJsonObj(jsonObj);
		$('#rightDiv').append(str);

	}
	/*//Recent Events Callback */
	
	/* Availability Callback */
	function addAvailability(nodeId,ipAddrs){
		
		//인터페이스 가용성
		var interfaceAvail = Number(getInterfaceAvailability(nodeId, ipAddrs)).toFixed(3)+"%";
		var headStr = '<h5>' + ipAddrs + '&nbsp;[&nbsp;'+interfaceAvail+'&nbsp;]&nbsp;</h5></a>';
		
		//서비스 가용성
		var serviceAvailSte = getTabletagToAvailJsonObj(nodeId, ipAddrs);
		if(serviceAvailSte == false){
			serviceAvailSte ="";
		}
		
		var str = headStr+serviceAvailSte;
		
		
		$('#availDiv').append(str);
		
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
					<div class="span3">
						<h4 id="nodeLabel">인터페이스&nbsp;정보</h4>
					</div>
					<div class="span9">
						<jsp:include page="/include/statsBar.jsp" />
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<!-- <div class="span4" id="leftDiv">
				
				<div class="row-fluid">
					<h5>가용성</h5>
				</div>
				<div class="row-fluid">
					<div class="span12 well well-small">
						<table class="table table-striped" id="availDiv"></table>
					</div>
				</div>
			</div> -->
			
			<div class="span12" id="rightDiv">
			<div class="row-fluid">
					<h5>${intf}&nbsp;인터페이스&nbsp;정보</h5>
				</div>
				<table>
				
				</table>
			</div>
		</div>

	</div>
	<hr>
	<!-- /container -->
</body>
</html>
 --%>