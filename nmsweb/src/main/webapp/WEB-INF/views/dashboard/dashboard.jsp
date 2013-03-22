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
					<li><a href="<c:url value='/index.do'/>">Home</a> <span class="divider">/</span></li>
					<li class="active">데쉬보드</li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12 well well-small">
				그래프
			</div>
		</div>
		<div class="row-fluid">
			<div class="row-fluid">
				<h5>장애</h5>
			</div>
			<div class="row-fluid">
				<div class="span12 well well-small">
					<ul class="inline">
						<li>1</li>
						<li>1</li>
						<li>1</li>
						<li>1</li>
						<li>1</li>
						<li>1</li>
						<li>1</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="row-fluid">
				<h5>실시간&nbsp;이벤트</h5>
			</div>
			<div class="row-fluid">
				<div class="span12 well well-small">
					<table class="table table-striped">
						<tr>
							<th class="span1">ID</th>
							<th class="span2">시간</th>
							<th class="span2">상태</th>
							<th class="span7">로그</th>
						</tr>
						<tr>
							<td>2345</td>
							<td>13.02.01 15:00:00</td>
							<th class="normal">NOMAL</th>
							<td>The DNS outage on interface 192.168.0.1 has been cleared. Service is restored.</td>
						</tr>
						<tr>
							<td>2345</td>
							<td>13.02.01 15:00:00</td>
							<th class="normal">NOMAL</th>
							<td>The DNS outage on interface 192.168.0.1 has been cleared. Service is restored.</td>
						</tr>
						<tr>
							<td>2345</td>
							<td>13.02.01 15:00:00</td>
							<th class="normal">NOMAL</th>
							<td>The DNS outage on interface 192.168.0.1 has been cleared. Service is restored.</td>
						</tr>
						<tr>
							<td>2345</td>
							<td>13.02.01 15:00:00</td>
							<th class="normal">NOMAL</th>
							<td>The DNS outage on interface 192.168.0.1 has been cleared. Service is restored.</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<hr>
	<!-- /container -->
</body>
</html>
