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
	<jsp:param value="인터페이스 상세보기" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script src="<c:url value="/resources/js/outages.js" />"></script>
<script src="<c:url value="/resources/js/events.js" />"></script>
<script src="<c:url value="/resources/js/service.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {

		
		
	});
</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value="/index.do" />" />Home</a> <span class="divider">/</span></li>
					<li><a href="<c:url value="/monitering/node/search.do" />" />노드검색</a> <span class="divider">/</span></li>
					<li class="active">노드 상세보기</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
			<div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<h4 id="nodeLabel">노드정보</h4>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span9"></div>
			<div class="span3">
				<jsp:include page="/include/statsBar.jsp" />
			</div>
		</div>
		<div class="row-fluid">
			<div class="span4">
				<div class="row-fluid">
					<h5>일반</h5>
				</div>
				<div class="row-fluid">
					<div class="span12 well well-small">
						<table class='table table-striped'>
							<tr>
								<th>노드</th>
								<td>192.168.0.1</td>
							</tr>
							<tr>
								<th>폴링 상태</th>
								<td>192.168.0.1</td>
							</tr>
							<tr>
								<th>폴링 페키지</th>
								<td></td>
							</tr>
							<tr>
								<th>Interface Index</th>
								<td></td>
							</tr>
							<tr>
								<th>Last Service Scan</th>
								<td></td>
							</tr>
						</table>
					</div>
				</div>
				<div class="row-fluid">
					<h5>가용성</h5>
				</div>
				<div class="row-fluid">
					<div class="span12 well well-small" id="leftDiv"></div>
				</div>
			</div>
			<div class="span8" id="rightDiv"></div>
		</div>

	</div>
	<hr>
	<!-- /container -->
</body>
</html>
