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
			console.log("---------사용자 그룹 menu 리스트--------");
			console.log(data);
			
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

/* 메뉴아이디 갖고와서 그룹과 mapping 후 show()*/
 function showMenu(menuId){
	var Id=new Array(menuId);
	 for (i = 0; i < Id.length; i++) {
		 $('#'+Id[i]+'').show();
		 }
	 } 
/****************************Menu Manager**************************************/ 

function Timer() {
		/*해당 시,분,초 를 구한다.*/
		var now = new Date();
		var H = now.getHours();
		var M = now.getMinutes();
		var S = now.getSeconds();
		//해당 부분에 값 입력
		myForm.myText.value =  H + "시" + M + "분" + S + "초";
		//1초 마다 Timer()로 시간을 구한다.
		setTimeout("Timer()", 1000);
		}
	document.onLoad = setTimeout("Timer()",1000);
</script>
<form name=myForm>
				<input type=text name=myText style="text-align:center">
			</form>
<form id="userIdFrm" name="userIdFrm">
	<input type="hidden" id ="userNm" name="userNm" value="<%= userNm %>" />
</form>
<div class="row-fluid">
	<div class="span12">
		<div class="span9 controls"><h2 class="muted"><a href="<c:url value="/index.do" />">Blue-EyesOpen</a></h2>
		</div>
		<div class="span1 controls" style="text-align: right; margin-top: 15px; " id="logOutDiv">
			<h5><a class="text-error" href="<c:url value="/login.do" />">LogIn&nbsp;&nbsp;&nbsp;</a></h5>
		</div>
		<div class="span2 controls" style="text-align: left; margin-top: 15px; margin-left: 20px;" id="logInDiv">
			<h4 class="muted"><a href="<c:url value="" />">로그인&nbsp;하세요</a></h4>
		</div>
	</div>
</div>	
<div class="navbar-inner " style="margin-bottom: 10px;">
	<div class="container">
		<div class="">
			<ul class="nav nav-pills ">
				<li id="1" class="" style="display:none;"><a class="muted" href="<c:url value="/index.do" />" style="width: 120px; "><h4>Home</h4></a></li>
				<li id="2" style="display:none;"><a class="muted" href="<c:url value="/dashboard.do" />" style="width: 140px;"><h4>DashBoard</h4></a></li>
				<li class="dropdown">
					<a id="3" class="dropdown-toggle muted" data-toggle="dropdown" href="<c:url value="/monitoring/nodelist.do" />" style="width: 140px; display:none;"><h4>모니터링<b class="caret"></b> </h4></a>
					<ul class="dropdown-menu">
						<!-- <li id="31" style="display:none;"><a href="#">지도보기</a></li> -->
						<li id="32" style="display:none;"><a href="<c:url value="/monitoring/nodelist.do" />">노드목록</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a id="4" class="dropdown-toggle muted" data-toggle="dropdown" href="#" style="width: 110px; display:none;"><h4>검색<b class="caret"></b></h4></a>
					<ul class="dropdown-menu">
						<li id="41" style="display:none;"><a href="<c:url value="/search/node.do" />">노드검색</a></li>
						<li id="42" style="display:none;"><a href="<c:url value="/search/outage.do" />">장애 검색</a></li>
						<li id="6"  style="display:none;"><a  href="<c:url value="/assets.do"/>" >ASSETS 검색</a></li>
					</ul>
				</li>
				<li id="5" class="" style="display:none;"><a class="muted" href="<c:url value="/report/resource.do"/>" style="width: 130px;"><h4>그래프</h4></a></li>
				
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
					<a id="7" class="dropdown-toggle muted" data-toggle="dropdown" href="#" style="width: 140px; display:none;"><h4>운영관리<b class="caret"></b></h4> </a>
					<ul class="dropdown-menu">
						<li id="71" style="display:none;"><a tabindex="-1" href="<c:url value="/admin/node.do" />">노드 관리</a></li>
						<li id="72" style="display:none;"><a tabindex="-1" href="<c:url value="/admin/groupMng.do" />">그룹 관리</a></li>
						<li id="73" style="display:none;"><a tabindex="-1" href="<c:url value="/admin/userMng.do" />">사용자 관리</a></li>
						<li id="74" class="dropdown-submenu" style="display:none;"><a tabindex="-1" href="">알&nbsp;&nbsp;림</a>
							<ul class="dropdown-menu">
							<li><a tabindex="-1" href="<c:url value="/admin/notimng/configureNotification.do" />">알림 설정</a></li>
								<li class="dropdown-submenu" ><a tabindex="-1" href="#">알림 보기</a>
									<ul class="dropdown-menu">
										<li ><a tabindex="-1" href="<c:url value="/admin/notimng/allnoti.do" />">모든 알림</a></li>
										<li><a tabindex="-1" href="<c:url value="/admin/notimng/mynoti.do" />">나의 알림</a></li>
									</ul>
								</li>
								
							</ul>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</div>
