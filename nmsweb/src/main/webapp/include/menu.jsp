<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<div class="masthead">
	<h3 class="muted">Network manage System</h3>
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<ul class="nav">
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">Home</a>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">DashBoard</a>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">모니터링
							<b class = "caret"></b>
						</a>
						<ul class="dropdown-menu" >
							<li><a href="#">지도보기</a></li>
							<li><a href="#">노드목록</a></li>
							
						</ul>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">검색
							<b class = "caret"></b>
						</a>
						<ul class="dropdown-menu" >
							<li><a href="#">노드검색</a></li>
							<li><a href="#">장애검색</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">리포트
							<b class = "caret"></b>
						</a>
						<ul class="dropdown-menu" >
							<li><a href="#">자원별 리포트</a></li>
							<li><a href="#">사용자 지정 리포트</a></li>
							<li><a href="#">DB 리포트</a></li>
							<li><a href="#">통계리포트</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">운영관리
							<b class = "caret"></b>
						</a>
						<ul class="dropdown-menu" >
							<li><a href="#">노드관리</a></li>
							<li><a href="#">그룹관리</a></li>
							<li><a href="#">사용자 관리</a></li>
							<li><a href="#">사용자 설정</a></li>
						</ul>
					</li>
					
				</ul>
			</div>
		</div>
	</div>
	<!-- /.navbar -->
</div>
