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
		/*이벤트 목록  */
		eventListAct();
		
		getTotalIndexInfo(initDashboard, null);
		//getCurrentOutagesForNode(addOutage);
		
	});

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
	
	/* 그래프에 필요한 카테고리 목록 */
	function initDashboard(jsonObj){
		
		var categoryObj = jsonObj["CategoryInfo"];
		
		for ( var i in categoryObj) {
			var cateIdx = getCategorieIdx(categoryObj[i]["name"]);
			$('.graph-container>li:nth-child('+cateIdx+') span div').html(" ["+Number(categoryObj[i]["availabili"]).toFixed(2)+"%]");
			$('.graph-container>li:nth-child('+cateIdx+')').attr("class",status+"-graph");
			$('.graph-container>li:nth-child('+cateIdx+') .bar-inner').css("height",Number(categoryObj[i]["availabili"]).toFixed(2)+"%").css("bottom", "0");
		}
		addOutage(jsonObj);
		
		setTimeout(chkstats, 2000);
	}
	
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
		
		if(Number(categoryObj["availabili"]).toFixed(2) >= 100){
			var status = "normal";
		}else if (Number(categoryObj["availabili"]).toFixed(2) >= 90 && Number(categoryObj["availabili"]).toFixed(2) <= 99){
			var status = "warning";
		}else if (Number(categoryObj["availabili"]).toFixed(2) >= 80 && Number(categoryObj["availabili"]).toFixed(2) <= 89){
			var status = "minor";
		}else if (Number(categoryObj["availabili"]).toFixed(2) >= 70 && Number(categoryObj["availabili"]).toFixed(2) <= 79){
			var status = "major";
		}else if (Number(categoryObj["availabili"]).toFixed(2) <= 69){
			var status = "critical";
		}
		
		$('.graph-container>li:nth-child('+idx+') span div').html(" ["+Number(categoryObj["availabili"]).toFixed(2)+"%]");
		$('.graph-container>li:nth-child('+idx+')').attr("class",status+"-graph");
		$('.graph-container>li:nth-child('+idx+') .bar-inner').css("height",Number(categoryObj["availabili"]).toFixed(2)+"%").css("bottom", "0");
		
		addOutage(jsonObj);
	}
	/*//그래프를 그려준다. */
	
	/*이벤트 목록  */
	function eventListAct(){
		
		var data = "==6";
 		getTotalEvenstListForDashboard(addEvents,data,"10");
	}
	
	/* outage append ,장애 서비스 목록*/
	function addOutage(jsonObj){
		//장애 목록
		var str= "";
		var outageObj = jsonObj["Outages"];
		$('#outageDiv').empty();
		
		if(outageObj == "null"){
			
			str += "<li id='outage'>";
			str += "	<table>";
			str += "		<tr>";
			str += "			<td >";
			str +="				<h4  class ='text-error'>";
			str +="				현재 장애가 있는 서비스가 없습니다.";
			str +="				</h4>";
			str += "			</td>";
			str += "		</tr>";
			str += "	</table>";
			str += "</li>";
			$('#outageDiv').append($(str));
			$("#outageDiv").css("margin-bottom",-7);
		}else{
			
			for ( var i in outageObj) {
				
				var lostTime = new Date(outageObj[i]["iflostservice"]);
				var current = new Date();
				var lastTime = dateDiff(lostTime, current);
				var sec = getSecDateDiff(lostTime, current);
				var statu = sec <= 86400 ? "critical" : "major";
				
				
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
	}
	/*//outage append ,장애 서비스 목록*/
	
	
	/*이벤트 목록  */
	/* Evnet Callback */
	function addEvents(jsonObj){
		var str = "";
		var events = jsonObj["event"];
		str = "	<div class='row-fluid'>"
			+ "			<div class='row-fluid'>"
			+ "				<div class='span3'>"
			+ "				</div>"
			+ "		</div>"
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
				str += "<td><a href='/"+version+"/search/event/eventDesc.do?eventId="+events[i]["eventid"]+"'>" + events[i]["eventid"]
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
						<div class="progress" style="margin-bottom: 7px;">
							<div class="bar bar-success " style="width: 30%">100%</div>
							<div class="bar " style="width: 25%">99%~90%</div>
							<div class="bar bar-info" style="width: 20%">89%~80%</div>
							<div class="bar bar-warning" style="width: 15%">79%~70%</div>
							<div class="bar bar-danger" style="width: 10%">69%~0%</div> 
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="row-fluid">
				<div class="span12 well well-small">
					<jsp:include page="/include/graph.jsp" />
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="row-fluid">
				<h5>장애&nbsp;서비스&nbsp;목록</h5>
			</div>
			<div class="row-fluid">
				<div class="span12 well well-small" style ="margin-bottom: -14px;">
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
