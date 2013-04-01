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
<script src="<c:url value="/resources/js/category.js" />"></script>
<script type="text/javascript">
	//var socket = connect("ws://localhost:8080/v1/ws",wsReceiveData);
	
	$(document).ready(function() {
		
		/* Recent Events */
		eventListAct();
		
		getTotalIndexInfo(initDashboard, null);
		//getCurrentOutagesForNode(addOutage);
		
	});

	function initDashboard(jsonObj){

		var categoryObj = jsonObj["CategoryInfo"];
		
		for ( var i in categoryObj) {
			
			var cateIdx = getCategorieIdx(categoryObj[i]["name"]);
			
			var status = Number(categoryObj[i]["availabili"]).toFixed(2) >= 100 ? "normal" :  "critical";
 			
			$('.graph-container>li:nth-child('+cateIdx+') span div').html(" ["+Number(categoryObj[i]["availabili"]).toFixed(2)+"%]");
			$('.graph-container>li:nth-child('+cateIdx+')').attr("class",status+"-graph");
			$('.graph-container>li:nth-child('+cateIdx+') .bar-inner').css("height",Number(categoryObj[i]["availabili"]).toFixed(2)+"%").css("bottom", "0");
		}
		addOutage(jsonObj);
		
		setTimeout(chkstats, 2000);
	}
	
	
	var index = 0;
	function chkstats(){
		
		grapHide(index+1);
		index++;
		
		if( index == 7){
			eventListAct();
			index = 0;
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
	
	/* includeOut outage */
	function includeOut(id){
		
		$('#outage_'+id).animate({opacity:'0.4',}).hide("slow",function(){
			$('#outage_'+id).remove();}
		);
		
	}
	/*//includeOut outage */
	
	/* 그래프를 0%만들고 json데이터를 호출한다. */
	function grapHide(idx){
		
		$('.graph-container>li:nth-child('+idx+') .bar-inner').css("height","");
		var categoriNm = getCategorieName(idx);
		setTimeout(getCategoryToName, 800, grapShow, categoriNm, idx);
	}
	/*//그래프를 0%만들고 json데이터를 호출한다. */
	
	/* 그래프를 그려준다. */
	function grapShow(jsonObj, idx){
		
		var categoryObj = jsonObj["CategoryInfo"];
		
		var status = Number(categoryObj["availabili"]).toFixed(2) >= 100 ? "normal" :  "critical";
		
		$('.graph-container>li:nth-child('+idx+') span div').html(" ["+Number(categoryObj["availabili"]).toFixed(2)+"%]");
		$('.graph-container>li:nth-child('+idx+')').attr("class",status+"-graph");
		$('.graph-container>li:nth-child('+idx+') .bar-inner').css("height",Number(categoryObj["availabili"]).toFixed(2)+"%").css("bottom", "0");
		
		addOutage(jsonObj);
	}
	/*//그래프를 그려준다. */
	
	/* outage append */
	function addOutage(jsonObj){
		//중단 목록
		var outageObj = jsonObj["Outages"];
		$('#outageDiv').empty();
		for ( var i in outageObj) {
			
			var lostTime = new Date(outageObj[i]["iflostservice"]);
			var current = new Date();
			var lastTime = dateDiff(lostTime, current);
			var sec = getSecDateDiff(lostTime, current);
			var statu = sec <= 86400 ? "critical" : "major";
			var str= "";
			
			str += "<li id='outage_"+outageObj[i]["outageid"]+"'>";
			str += "	<table>";
			str += "		<tr>";
			str += "			<td style='text-align:center;'>";
			str += "				<img src='<c:url value="/resources/images/" />"+statu+".png' style='width:110px; height:60px;'/>";
			str += "			</td>";
			str += "		</tr>";
			str += "		<tr class='"+statu+"'>";
			str += "			<td style='text-align:center;'>";
			str +=					outageObj[i]["ipaddr"];
			str += "			</td>";
			str += "		<tr>";
			str += "			<td style='text-align:center;'>";
			str += "				<a href=\"javascript:includeOut('"+outageObj[i]["outageid"]+"');\">";
			str += "					["+lastTime+"]";
			str += "				</a>";
			str += "			</td>";
			str += "		</tr>";
			str += "	</table>";
			str += "</li>";
			
			if(sec <= 86400){
				$('#outageDiv').prepend($(str)).fadeIn('slow');
			}else{
				$('#outageDiv').append($(str)).fadeIn('slow');
			}
		}
	}
	/*//outage append */
	
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
					<div class="span12">
						<h4 id="nodeLabel">데쉬보드</h4>
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
				<h5>중단&nbsp;서비스&nbsp;목록</h5>
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
