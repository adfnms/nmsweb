<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
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
			console.log(data);
			
			for(var i = 0; i < data.userList.length; i++)
       		{
        		if(data.userList[i].menuId != null)
        		{
        			menuId = data.userList[i].menuId; 
        			showMenu(menuId);
        		}
       		}
			
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
	
<h3 class="muted"><a href="<c:url value="/index.do" />">Network manage System</a></h3>
<div class="navbar-inner " style="margin-bottom: 10px;">
	<div class="container">
		<div class="">
			<ul class="nav nav-pills ">
				<li id="1" class="" style="display:none;"><a class="muted" href="<c:url value="/index.do" />" style="width: 120px; "><h4>Home</h4></a></li>
				<li id="2" style="display:none;"><a class="muted" href="<c:url value="/dashboard.do" />" style="width: 130px;"><h4>DashBoard</h4></a></li>
				<li class="dropdown">
					<a id="3" class="dropdown-toggle muted" data-toggle="dropdown" href="<c:url value="/monitoring/nodelist.do" />" style="width: 130px; display:none;"><h4>모니터링<b class="caret"></b> </h4></a>
					<ul class="dropdown-menu">
						<li id="31" style="display:none;"><a href="#">지도보기</a></li>
						<li id="32" style="display:none;"><a href="<c:url value="/monitoring/nodelist.do" />">노드목록</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a id="4" class="dropdown-toggle muted" data-toggle="dropdown" href="#" style="width: 130px; display:none;"><h4>검색<b class="caret"></b></h4></a>
					<ul class="dropdown-menu">
						<li id="41" style="display:none;"><a href="<c:url value="/search/node.do" />">노드검색</a></li>
						<li id="42" style="display:none;"><a href="<c:url value="/search/outage.do" />">중단 검색</a></li>
					</ul>
				</li>
				<li id="5" class="" style="display:none;"><a class="muted" href="<c:url value="/report/resource.do"/>" style="width: 120px;"><h4>그래프</h4></a></li>
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
					<a id="6" class="dropdown-toggle muted" data-toggle="dropdown" href="#" style="width: 130px; display:none;"><h4>운영관리<b class="caret"></b></h4> </a>
					<ul class="dropdown-menu">
						<li id="61" style="display:none;"><a tabindex="-1" href="<c:url value="/admin/node.do" />">노드관리</a></li>
						<li id="62" style="display:none;"><a tabindex="-1" href="<c:url value="/admin/groupMng.do" />">그룹관리</a></li>
						<li id="63" style="display:none;"><a tabindex="-1" href="<c:url value="/admin/userMng.do" />">사용자 관리</a></li>
						<li id="64" style="display:none;"><a tabindex="-1" href="<c:url value="/admin/setting.do" />">사용자 설정</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</div>
