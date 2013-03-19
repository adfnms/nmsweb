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
	<jsp:param value="노드 상세보기" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script src="<c:url value="/resources/js/outages.js" />"></script>
<script src="<c:url value="/resources/js/events.js" />"></script>
<script src="<c:url value="/resources/js/service.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		/* Node base info */
		getSpecificNode(addNodeDesc, "${nodeId}");

		/* Recent Outages */
		getOutagesForNode(addOutages, "${nodeId}", "10");

		/* Recent Events */
		getEventsForNode(addEvents, "${nodeId}", "10");
		
		/* Node Availability */
		getNodeAvailability(addNodeAvailability, "${nodeId}");
		
		/* Interface Availability */
		getInterfacesFromNodeId(addInterfaceAvailability, "${nodeId}");
		
	});

	/* Node base info Callback */
	function addNodeDesc(jsonObj) {

		$('#nodeLabel').append("[ " + jsonObj["@label"] + " ]");

		/* Surveillance Category Memberships */
		addCategories(jsonObj["categories"]);

	}

	/* Surveillance Category Memberships Callback*/
	function addCategories(jsonObj) {

		var str = getTabletagToCategoryJsonObj(jsonObj);
		$('#rightDiv').append(str);

	}
	/*//Surveillance Category MembershipsCallback */

	/* Recent Outages Callback */
	function addOutages(jsonObj) {

		var str = getTabletagToOutageJsonObj(jsonObj,"${nodeId}");
		$('#rightDiv').append(str);

	}
	/*//Recent Outages */

	/* Recent Events Callback */
	function addEvents(jsonObj) {

		var str = getTabletagToEventJsonObj(jsonObj);
		$('#rightDiv').append(str);

	}
	/*//Recent Events Callback*/

	
	/* Availability Callback */
	
	function addNodeAvailability(jsonObj){

		var nodeAvail = Number(jsonObj["nodeAvailability"][0]["avail"]).toFixed(3); 
		
		$('#availNode').append("가용성[&nbsp;"+nodeAvail+"%&nbsp;]");
		
	}

	function addInterfaceAvailability(jsonObj){
		
		var interfaceAvail = getInterfaceAvailability("${nodeId}", jsonObj["ipInterface"]["ipAddress"]);
		var str ="";
		
		if(jsonObj["@count"] > 0){
			if(jsonObj["@count"] > 1){
				interfaceObj = jsonObj["ipInterface"];
				for(var i in interfaceObj){
					
					var ipAddrs = interfaceAvail[i]["ipAddress"];
					
					//인터페이스 가용성
					var interfaceAvail = Number(getInterfaceAvailability("${nodeId}", ipAddrs)).toFixed(3)+"%";
					var headStr = '<h5>' + ipAddrs + '&nbsp;[&nbsp;'+interfaceAvail+'&nbsp;]&nbsp;</h5></a>';
					
					//서비스 가용성
					var serviceAvailSte = getTabletagToAvailJsonObj("${nodeId}", ipAddrs);
					
					str += headStr+serviceAvailSte;
					
				}
			}else{
				var ipAddrs =jsonObj["ipInterface"]["ipAddress"]
				
				//인터페이스 가용성
 				var interfaceAvail = Number(getInterfaceAvailability("${nodeId}", ipAddrs)).toFixed(3)+"%";
				var headStr = '<h5>' + ipAddrs + '&nbsp;[&nbsp;'+interfaceAvail+'&nbsp;]&nbsp;</h5></a>';
				
				//서비스 가용성
				var serviceAvailSte = getTabletagToAvailJsonObj("${nodeId}", ipAddrs);
				
				str += headStr+serviceAvailSte;
			}
		}
		
		$("#leftDiv").append(str);
	}

	function addServiceAvailability(jsonObj, nodeId, ipAddress, interfaceAvail){
		
		var headStr = '<a href="<c:url value="/search/node/interfaceDesc.do?nodeId=${nodeId}&intf='
			+ ipAddress + '" />"><h5>' + ipAddress + '</h5></a>';
			
		$("#leftDiv").prepend(headStr);
		
		var str = getTabletagToAvailJsonObj(jsonObj, ipAddress);
		
		$("#leftDiv").append(str);
		
	}
	
	/*//Availability Callback*/
	
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
					<li class="active">노드</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
			<div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span9">
						<h4 id="nodeLabel">노드정보</h4>
					</div>
					<div class="span3">
						<jsp:include page="/include/statsBar.jsp" />
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span4">
				<div class="row-fluid">
					<h5 id="availNode"></h5>
				</div>
				<div class="row-fluid">
					<div class="span12 well well-small" id="leftDiv"></div>
				</div>
			</div>
			<div class="span8" id="rightDiv"></div>
		</div>

	</div>
	<hr>
	<!-- /container -->
</body>
</html>
