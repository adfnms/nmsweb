<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>

$(document).ready(function(){
	
	rePositionSideBar();
	$(window).resize( function(e){rePositionSideBar();});
	
});

function rePositionSideBar(){
	var conWidth = $(".container").width();
	var	width  = conWidth + ( ($(document).width() -conWidth) /2 ); 
	$("#sideBar").css("left",width+20);
}
</script>

<div class="well affix hidden-phone" id="sideBar">
	<ul class="nav nav-list">
		<li class="nav-header">노드 리스트</li>
		<li class="active"><a href="#">192.168.0.10</a></li>
		<li><a href="#">192.168.0.10</a></li>
		<li><a href="#">192.168.0.10</a></li>
		<li><a href="#">192.168.0.10</a></li>
		<li><a href="#">192.168.0.10</a></li>
		<li><a href="#">192.168.0.10</a></li>
		<li><a href="#">192.168.0.10</a></li>
		<li><a href="#">192.168.0.10</a></li>
		<li><a href="#">192.168.0.10</a></li>
		<li><a href="#">192.168.0.10</a></li>
	</ul>
</div>
<!--/.well -->

