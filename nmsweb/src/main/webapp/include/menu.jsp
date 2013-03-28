<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<div class="masthead">
	<h3 class="muted"><a href="<c:url value="/index.do" />">Network manage System</a></h3>
	<!-- /.navbar -->
          <div class="breadcrumb well well-small span10">
            <ul class="nav nav-pills ">
              <li class="dropdown span2">
              	<a class="dropdown-toggle muted" style="font-size:18px;" href="<c:url value="/index.do" />">Home</a>
              </li>
              <li class="dropdown span2">
				<a class="dropdown-toggle muted" style="font-size:16px;" href="<c:url value="/dashboard.do" />">DashBoard</a>
			  </li>
              <li class="dropdown span2">
                <a class="dropdown-toggle muted" id="drop5" role="button" data-toggle="dropdown" style="font-size:16px;" href="<c:url value="/monitoring/nodelist.do" />">모니터링 <b class="caret"></b></a>
                <ul id="menu2" class="dropdown-menu" role="menu" aria-labelledby="drop5">
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">지도보기</a></li>
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/monitoring/nodelist.do" />">노드목록</a></li>
                </ul>
              </li>
              <li class="dropdown span2">
                <a class="dropdown-toggle muted" id="drop5" role="button" data-toggle="dropdown" style="font-size:16px;" href="#">검색<b class="caret"></b></a>
                <ul id="menu3" class="dropdown-menu" role="menu" aria-labelledby="drop5">
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/search/node.do" />">노드검색</a></li>
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/search/outage.do" />">중단 검색</a></li>
                </ul>
              </li>
              <li class="dropdown span2">
                <a class="dropdown-toggle muted" id="drop5" role="button" data-toggle="dropdown" style="font-size:16px;" href="#">리포트<b class="caret"></b></a>
                <ul id="menu3" class="dropdown-menu" role="menu" aria-labelledby="drop5">
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">자원별 리포트</a></li>
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">사용자 지정 리포트</a></li>
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">DB 리포트</a></li>
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">통계리포트</a></li>
                </ul>
              </li>
              <li class="dropdown">
                <a class="dropdown-toggle muted" id="drop5" role="button" data-toggle="dropdown" style="font-size:16px;" href="<c:url value="/admin/node.do" />">운영관리<b class="caret"></b></a>
                <ul id="menu3" class="dropdown-menu" role="menu" aria-labelledby="drop5">
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/admin/node.do" />">노드관리</a></li>
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/admin/groupMng.do" />">그룹관리</a></li>
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/admin/userMng.do" />">사용자 관리</a></li>
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/admin/setting.do" />">사용자 설정</a></li>
                </ul>
              </li>
            </ul> 
            <!-- /tabs -->
          </div>
</div>
