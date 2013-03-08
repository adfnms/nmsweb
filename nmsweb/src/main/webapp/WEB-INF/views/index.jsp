<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.bluecapsystem.nms.define.NMSProperties"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="/include/header.jsp">
	<jsp:param value="노드관리" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/requisitions.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {

	});
</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span10">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value='/index.do'/>">Home</a></li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
			<div class="span10">
				<div class="row-fluid">
					<div class="span12">
						<div class="span3 well well-small">
							<div class="row-fluid">
								<div class="span12">
									<h4>기본속성</h4>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<a href="#">debianOPENNMS.local</a> (2 days)
								</div>
							</div>
						</div>
						<div class="span6 well well-small">
							<div class="row-fluid">
								<div class="span12">
									<h4>24시간 이용내역</h4>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<table class="table table-striped" id="userListTable">
										<colgroup>
											<col class="span6" />
											<col class="span3" />
											<col class="span3" />
										</colgroup>
										<thead>
											<tr>
												<th>카테고리</th>
												<th>중단</th>
												<th>이용률</th>
											</tr>
											<tr>
												<td>Network Interfaces</td>
												<td>1 of 20</td>
												<td>92.104%</td>
											</tr>
											<tr>
												<td>Network Interfaces</td>
												<td>1 of 20</td>
												<td>92.104%</td>
											</tr>
											<tr>
												<td>Network Interfaces</td>
												<td>1 of 20</td>
												<td>92.104%</td>
											</tr>
										</thead>
									</table>
									<table class="table table-striped" id="userListTable">
										<colgroup>
											<col class="span6" />
											<col class="span3" />
											<col class="span3" />
										</colgroup>
										<thead>
											<tr>
												<th>전체</th>
												<th>중단</th>
												<th>이용률</th>
											</tr>
											<tr>
												<td>Overall Service Availability</td>
												<td>4 of 46</td>
												<td>92.104%</td>
											</tr>
										</thead>
									</table>
								</div>
							</div>
						</div>
						<div class="span3 well well-small">
							<div class="row-fluid">
								<div class="span12">
									<h4>기본속성</h4>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<a href="#">debianOPENNMS.local</a> (2 days)
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<hr>
	</div>
	<!-- /container -->
</body>
</html>
