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
		eventListAct();
		
		getCurrentOutagesForNode(addOutage);
		
		setTimeout(chkstats, 2000);
	});

	var index = 0;
	function chkstats(){
		
		if( index == 7){
			eventListAct();
			index = 0;
		}else{
			grapHide(index+1);
			index++;
		}
		
		
		setTimeout(chkstats, 3000);
	}
	
	function eventListAct(){
		
		var data = "==6";
 		getTotalEvenstListForDashboard(addEvents,data,"10");
		
	}
	
	/* Evnet Callback */
	function addEvents(jsonObj){
		console.log(jsonObj);
		var str = "";
		var events = jsonObj["event"];
		console.log(events);
		str = "	<div class='row-fluid'>"
			+ "		<h5>이벤트&nbsp;목록&nbsp;["
			+ events.length
			+ "]</h5>"
			+ "	</div>"
			+ "	<div class='row-fluid'>"
			+ "		<div class='span12 well well-small'>"
			+ "		<table class='table'>"
			+ "			<colgroup><col width='15%'/><col  width='25%'/><col  width='15%'/><col  width='45%'/></colgroup>"		
			+ "			<thead><tr><th>이벤트ID</th><th>시간</th><th>상태</th><th class='span4'>메세지</th></tr></thead>"
			+ "		</table><div id='outageScrollDiv'><table class='table'>	"
			+ "			<colgroup><col width='15%'/><col  width='25%'/><col  width='15%'/><col  width='45%'/></colgroup>"
			+ "			<tbody>";
		if (events.length > 0) {
	
			for ( var i in events) {
				str += "<tr>";
				str += "<td><a href='/"+version+"/search/event/eventDesc.do?eventId="+events[i]["eventid"]+"' target='_blank'>" + events[i]["eventid"]
						+ "</a></td>";
				str += "<td>"
						+ new Date(events[i]["eventtime"])
								.format('yy-MM-dd hh:mm:ss') + "</td>";
				str += "<th class='"+getEventseverityToNum(events[i]["eventseverity"]).toLowerCase()+"'>" + getEventseverityToNum(events[i]["eventseverity"]) + "</th>";
				str += "<td>" + events[i]["eventlogmsg"] + "</td>";
				str += "</tr>";
				str += "<tr>";
				str += "	<td colspan='4'>";
				str += events[i]["eventdescr"];
				str += "	</td>";
				str += "</tr>";
			}
		}

	
		str += "</tbody></table></div></div>";
		$('#eventDiv').empty();
		$('#eventDiv').append(str);
		
		//Add Scroll From #outageScrollDiv 
		$('#outageScrollDiv').css('overflow-y','auto').css('height','300px');
		
	}
	/*//Evnet Callback */
	
	/* Outage Callback */
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
			str += "				<img src='<c:url value="/resources/images/" />"+outageObj[i]["serviceLostEvent"]["@severity"].toLowerCase()+".png' style='width:110px; height:40px;'/>";
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
	/*//Outage Callback */
	
	/*//includeOut outage */
	function includeOut(id){
		
		$('#outage_'+id).animate({opacity:'0.4',}).hide("slow",function(){
			$('#outage_'+id).remove();}
		);
		
	}
	
	function wsReceiveData(data){
		
		alert(data);
		
	}
	
	function grapHide(idx){
		
		$('.graph-container>li:nth-child('+idx+') .bar-inner').css("height","");
		setTimeout(grapShow, 800, idx);
		
	}
	
	function grapShow(idx){
		$('.graph-container>li:nth-child('+idx+') span div').html(" [50%]");
		$('.graph-container>li:nth-child('+idx+')').attr("class","critical-graph");
		$('.graph-container>li:nth-child('+idx+') .bar-inner').css("height","100%").css("bottom", "0");
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
					<li class="active"><a href="javascript:test();">데쉬보드</a></li>
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
			<div class="row-fluid">
				<div class="span12 well well-small">
					<jsp:include page="/include/graph.jsp" />
				</div>
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
