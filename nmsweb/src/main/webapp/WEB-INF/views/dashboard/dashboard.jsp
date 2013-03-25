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
<script src="<c:url value="/resources/js/common/websocket.js" />"></script>
<script src="<c:url value="/resources/js/outages.js" />"></script>
<script src="<c:url value="/resources/js/events.js" />"></script>
<script type="text/javascript">
	//var socket = connect("ws://localhost:8080/v1/ws",wsReceiveData);
	
	$(document).ready(function() {
		
		/* Recent Events */
		var query = "query="+encodeURI("this_.eventSeverity > 5");
		var filter = "&orderBy=eventTime&order=desc&limit=10";
		getTotalEvenstList(addEvents, query+filter);
		
		getCurrentOutagesForNode(addOutage);
	});

	function addEvents(jsonObj){

		var str = getTabletagToEventJsonObj(jsonObj);
		$('#eventDiv').append(str);
		
		//Add Scroll From #outageScrollDiv 
		$('#outageScrollDiv').css('overflow-y','scroll').css('height','300px');
		
	}
	
	function addOutage(jsonObj){
		
		var outageObj = jsonObj["outage"];

		for( var i in outageObj ){
			
			var lostTime = new Date(outageObj[i]["ifLostService"]);
			var current = new Date();
			var lastTime = dateDiff(lostTime, current);
		
			var str= "";
			
			str += "<li id='outage_"+outageObj[i]["@id"]+"'>";
			str += "	<table>";
			str += "		<tr>";
			str += "			<td style='text-align:center;'>";
			str += "				<img src='https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRqXCxP5r2U7rdMIwXYdQ16xABGNm3TCFCMfKPmXZp3lMAhDCK0Fw' style='width:50px; height:50px;'/>";
			str += "			</td>";
			str += "		</tr>";
			str += "		<tr class='"+outageObj[i]["serviceLostEvent"]["@severity"].toLowerCase()+"'>";
			str += "			<td style='text-align:center;'>";
			str +=					outageObj[i]["ipAddress"];
			str += "			</td>";
			str += "		<tr>";
			str += "			<td style='text-align:center;'>";
			str += "				<a href=\"javascript:includeOut('"+outageObj[i]["@id"]+"');\">";
			str += "					["+lastTime+"]";
			str += "				</a>";
			str += "			</td>";
			str += "		</tr>";
			str += "	</table>";
			str += "</li>";
			
			$('#outageDiv').append($(str)).hide().fadeIn('slow');
			
		}
		
	}
	
	function includeOut(id){
		
		$('#outage_'+id).animate({opacity:'0.4',}).hide("slow",function(){
			$('#outage_'+id).remove();}
		);
		
	}
	
	
	
	function wsReceiveData(data){
		
		alert(data);
		
	}
	
</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value='/index.do'/>">Home</a> <span class="divider">/</span></li>
					<li class="active">데쉬보드</li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span9">
						<h4 id="nodeLabel">데쉬보드</h4>
					</div>
					<div class="span3">
						<jsp:include page="/include/statsBar.jsp" />
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="row-fluid">
				<h5>그래프</h5>
			</div>
			<div class="span12 well well-small">
				<img src="http://www.greentransport.org/xe/files/attach/images/61/252/004/5%EC%9B%94%EC%A1%B0%EC%82%AC%EA%B7%B8%EB%9E%98%ED%94%84.jpg" style="height:200px;width:600px;"/>
			</div>
		</div>
		<div class="row-fluid">
			<div class="row-fluid">
				<h5>장애</h5>
			</div>
			<div class="row-fluid">
				<div class="span12 well well-small">
					<ul class="inline" id="outageDiv">
					</ul>
				</div>
			</div>
		</div>
		<div class="row-fluid" id="eventDiv"></div>
	</div>
	<hr>
	<!-- /container -->
</body>
</html>
