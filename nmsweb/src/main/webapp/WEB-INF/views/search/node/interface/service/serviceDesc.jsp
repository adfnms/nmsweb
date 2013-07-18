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
	<jsp:param value="서비스 상세보기" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script src="<c:url value="/resources/js/outages.js" />"></script>
<script src="<c:url value="/resources/js/events.js" />"></script>
<script src="<c:url value="/resources/js/service.js" />"></script>
<script src="<c:url value="/resources/js/category.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {

		/* Recent Outages */
		getOutagesForInterface(addOutages, "${nodeId}", "${intf}","5");

		/* Recent Events */
		getEventsForInterface(addEvents, "${nodeId}", "${intf}","5");
		
		/* ServiceInfo */
		//getServiceInfo(addServiceInfo, "${nodeId}", "${intf}","${serviceNm}");
		
	});
	
	/* Recent Outages Callback*/
	function addOutages(jsonObj) {

		var str = getTabletagToServiceOutageJsonObj(jsonObj,"${nodeId}");
		$('#rightDiv').append(str);

	}
	/*//Recent Outages Callback */
	
	/* Recent Events Callback */
	function addEvents(jsonObj) {

		var str = getTabletagToServiceEventJsonObj(jsonObj);
		$('#rightDiv').append(str);

	}
	/*//Recent Events Callback */
	
	/* ServiceInfo Callback */
	/* function addServiceInfo(jsonObj, nodeId, ipAddress, serviceNm){
		
		var serviceInfoStr = getServiceInfoBox(jsonObj, nodeId, ipAddress, serviceNm);
								
		$('#leftDiv').append(serviceInfoStr);
		
	} */
	/*//ServiceInfo Callback */
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
					<li><a href="<c:url value="/search/node/interfaceDesc.do?nodeId=${nodeId}&intf=${intf}" />" />인터페이스</a> <span class="divider">/</span></li>
					<li class="active">서비스</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
			<div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span2" style="margin-top: 11px;">
						<h4 id="nodeLabel">서비스&nbsp;정보</h4>
					</div>
					<div class="span1">
						<h3 id="" style="margin-left: -47px;">${serviceNm}</h3>
					</div>
					<div class="span9" style="margin-top: 20px;"> 
						<jsp:include page="/include/statsBar.jsp" />
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<!-- <div class="span4" id="leftDiv"></div> -->
			<div class="span12" id="rightDiv"></div>
		</div>

	</div>
	<hr>
	<!-- /container -->
</body>
</html>
 --%>