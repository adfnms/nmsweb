<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>

$(document).ready(function(){
	
	rePositionSideBar();
	$(window).resize( function(e){ rePositionSideBar();});
	
	/* 노드리스트 갖고오기 */
	getNodeListSideBar(addNodeListsSideBar, "orderBy=id&limit=0");
});

function rePositionSideBar(){
	var conWidth = $(".container").width();
	var	width  = conWidth + ( ($(document).width() -conWidth) /2 ); 
	$("#sideBar").css("left",width+26);
	$("#sideBarNodeList").css("margin-right",-30);
	$("#sideBarNodeList").css("margin-left",-30);
	$("#sideBarNodeList").css("margin-top",-14);
}

function addNodeListsSideBar(jsonObj) {
	
	$('#sideBarNodeList').empty();
	
	
			var str = getNodelistJsonObj(jsonObj);
			
			$('#sideBarNodeList').append(str);
	
	
}

</script>

<div class="well affix hidden-phone" id="sideBar">
	<ul class="nav nav-list">
		<li class="nav-header"><h5>노드&nbsp;리스트</h5></li>
		<li class="nav-header" id="sideBarNodeList"></li>
	</ul>
</div>
<!--/.well -->

