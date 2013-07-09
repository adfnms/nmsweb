<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>

$(document).ready(function(){
	
	rePositionSideBar();
	$(window).resize( function(e){ rePositionSideBar();});
	
	/* 노드리스트 갖고오기 */
	getNodeListSideBar(addNodeListsSideBar, "orderBy=id&limit=0");
	
	getTotalIndexInfo(sidebarInfo, null);
	
});

function rePositionSideBar(){
	var conWidth = $(".container").width();
	var	width  = conWidth + ( ($(document).width() -conWidth) /2 ); 
	
	$("#sideBar").css("left",width+26);
	$("#sideBarOutageList").css("margin-right",-35);
	$("#sideBarOutageList").css("margin-left",-35);
	$("#sideBarOutageList").css("margin-top",-14);
}

function addNodeListsSideBar(jsonObj) {
	
	$('#sideBarNodeList').empty();
	
	
			var str = getNodelistJsonObj(jsonObj);
			
			$('#sideBarNodeList').append(str);
	
	
}

function sidebarInfo(jsonObj) {
	
	console.log(jsonObj);
	
	//중단 목록
	var sideOutageObj = jsonObj["Outages"];

	for ( var i in sideOutageObj) {

		var lostTime = new Date(sideOutageObj[i]["iflostservice"]);
		var current = new Date();
		var lastTime = dateDiff(lostTime, current);
		
		$('#sideBarOutageList').append("<h5><a class=text-error href='<c:url value='/search/outage/outageDesc?outageId=' />"+sideOutageObj[i]["outageid"]+"'>" + sideOutageObj[i]["ipaddr"] + "</a> ("+ lastTime + ")<br/></h5>");
	}
}



</script>

<!-- <div class="well affix hidden-phone" id="sideBar">
	<ul class="nav nav-list">
		<li class="nav-header"><h5>노드&nbsp;리스트</h5></li>
		<li class="nav-header" id="sideBarNodeList"></li>
	</ul>
</div> -->

<div class="well affix hidden-phone" id="sideBar">
	<ul class="nav nav-list">
		<li class="nav-header"><h5>장애&nbsp;목록</h5></li>
		<li class="nav-header" id="sideBarOutageList"></li>
	</ul>
</div>

<!--/.well -->

