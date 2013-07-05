<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.bluecapsystem.nms.define.Define"%>
<%@ page session="true"%>
<%
boolean g_isLogin 	= false;
String userNm = null;

try{
	userNm	= session.getAttribute(Define.FULL_NAME_KEY).toString(); 
}catch(Exception ex){
	g_isLogin = false;
}

%>
<script type="text/javascript">
$(document).ready(function() {
	menuManager();
});

/*그룹별 메뉴사용 Manager*/

/*****************************Menu Manager**************************************/
function menuManager(){
	 
	$.ajax({
		type : 'get',
		url : '<c:url value="/menu/showMenu.do" />',
		contentType : 'application/json', 
		error : function(data) {
			alert('사용자 그룹 menu리스트 서비스 실패');
		},
		success : function(data) {
			///console.log(data);
			
			if(data.groupNm != "visitor"){
				for(var i = 0; i < data.userList.length; i++)
	       		{
					menuId = data.userList[i].menuId; 
        			userId = data.userList[i].userId; 
        			userNm = data.userList[i].userNm; 
        			showMenu(menuId);
        			$("#logInDiv").html("<h4><a class=\"text-success\" href=\"<c:url value="/admin/userMng.do" />\">"+userNm+"</a>&nbsp;님</h4>");
	       		}
			}else{
    			
    			var userName = $("#userIdFrm input[name=userNm]").val();
    			$("#logInDiv").html("<h4><a class=\"text-success\">"+userName+"</a>&nbsp;님</h4>");
    		}
			
			$("#logOutDiv").html("<h5><a class=\"text-error\" href=\"javascript:logOut();\">LogOut&nbsp;&nbsp;&nbsp;</a></h5>");
		}
	});   
}


function logOut(){
	
	$.ajax({
		type : 'post',
		url : '<c:url value="/logout.do" />',
		async: false,
		error : function(data) {
			_return = false;
		},
		success : function(data) {
			_return = true;
			location.href ="/"+version+"/login.do?";
		}
	});
}


 function showMenu(menuId){
	var Id=new Array(menuId);
	 for (i = 0; i < Id.length; i++) {
		 $('#'+Id[i]+'').show();
		 }
	 } 
/****************************Menu Manager**************************************/ 


</script>
<form id="userIdFrm" name="userIdFrm">
	<input type="hidden" id ="userNm" name="userNm" value="<%= userNm %>" />
</form>
<div class="row-fluid">
	<div class="span12">
		<div class="span9 controls"><h3 class="muted"><a href="<c:url value="/index.do" />">Network manage System</a></h3></div>
		<div class="span1 controls" style="text-align: right; margin-top: 15px; " id="logOutDiv">
			<h5><a class="text-error" href="<c:url value="/login.do" />">LogIn&nbsp;&nbsp;&nbsp;</a></h5>
		</div>
		<div class="span2 controls" style="text-align: left; margin-top: 15px; margin-left: 20px;" id="logInDiv">
			<h4 class="muted"><a href="<c:url value="" />">로그인&nbsp;하세요</a></h4>
		</div>
	</div>
</div>	
<%-- <h3 class="muted"><a href="<c:url value="/index.do" />">Network manage System</a></h3> --%>
<div class="navbar-inner " style="margin-bottom: 10px;">
	<div class="container">
		<div class="">
			<ul class="nav nav-pills ">
				<li id="1" class="" style="display:none;"><a class="muted" href="<c:url value="/index.do" />" style="width: 110px; "><h4>Home</h4></a></li>
				<li id="2" style="display:none;"><a class="muted" href="<c:url value="/dashboard.do" />" style="width: 120px;"><h4>DashBoard</h4></a></li>
				<li class="dropdown">
					<a id="3" class="dropdown-toggle muted" data-toggle="dropdown" href="<c:url value="/monitoring/nodelist.do" />" style="width: 120px; display:none;"><h4>모니터링<b class="caret"></b> </h4></a>
					<ul class="dropdown-menu">
						<!-- <li id="31" style="display:none;"><a href="#">지도보기</a></li> -->
						<li id="32" style="display:none;"><a href="<c:url value="/monitoring/nodelist.do" />">노드목록</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a id="4" class="dropdown-toggle muted" data-toggle="dropdown" href="#" style="width: 90px; display:none;"><h4>검색<b class="caret"></b></h4></a>
					<ul class="dropdown-menu">
						<li id="41" style="display:none;"><a href="<c:url value="/search/node.do" />">노드검색</a></li>
						<li id="42" style="display:none;"><a href="<c:url value="/search/outage.do" />">중단 검색</a></li>
					</ul>
				</li>
				<li id="5" class="" style="display:none;"><a class="muted" href="<c:url value="/report/resource.do"/>" style="width: 110px;"><h4>그래프</h4></a></li>
				<li id="6" class="" style="display:none;"><a class="muted" href="<c:url value="/assets.do"/>" style="width: 110px;"><h4>ASSETS</h4></a></li>
				<%-- <li class="dropdown">
					<a id="5" class="dropdown-toggle muted" data-toggle="dropdown" href="#" style="width: 130px;"><h4>그래프<b class="caret"></b></h4> </a>
				<ul class="dropdown-menu">
					<li><a tabindex="-1" href="<c:url value="/report/resource.do"/>">자원별 그래프</a></li>
					      <!-- <li><a tabindex="-1" href="#">사용자 지정 리포트</a></li>
					      <li><a tabindex="-1" href="#">DB 리포트</a></li>
					      <li><a tabindex="-1" href="#">통계리포트</a></li> -->
					</ul>
				</li> --%>
				<li class="dropdown">
					<a id="7" class="dropdown-toggle muted" data-toggle="dropdown" href="#" style="width: 120px; display:none;"><h4>운영관리<b class="caret"></b></h4> </a>
					<ul class="dropdown-menu">
						<li id="71" style="display:none;"><a tabindex="-1" href="<c:url value="/admin/node.do" />">노드관리</a></li>
						<li id="72" style="display:none;"><a tabindex="-1" href="<c:url value="/admin/groupMng.do" />">그룹관리</a></li>
						<li id="73" style="display:none;"><a tabindex="-1" href="<c:url value="/admin/userMng.do" />">사용자 관리</a></li>
						<li id="74" style="display:none;"><a tabindex="-1" href="<c:url value="/admin/setting.do" />">사용자 설정</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</div>
