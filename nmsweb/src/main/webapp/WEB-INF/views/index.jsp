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
<script src="<c:url value="/resources/js/outages.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		getTotalOutagesList(outageList,null);
	});

	function outageList(jsonObj) {

		console.log(jsonObj);
		var outageObj = jsonObj["outage"];

		for( var i in outageObj ){
			
			$('#outageInfo').append("<a href='#'>"+outageObj[i]["serviceLostEvent"]["host"]+"</a> (2 days)<br/>");
			
		}
		
		
	}
</script>	
</head>

<body>
	<div class="container">
		
		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value='/index.do'/>">Home</a></li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid">
					<div class="span12">
						<div class="span3">
							<div class="row-fluid">
								<div class="span12">
									<h4>기본속성</h4>
								</div>
							</div>
							<div class="well well-small">
								<div class="row-fluid">
									<div class="span12" id="outageInfo"></div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<h4>Quick Search</h4>
								</div>
							</div>
							<div class="well well-small">
								<div class="row-fluid">
									<div class="span12">
										<form>
											<fieldset>
												<label for="">노드 ID</label><input type="text" class="span12">
											</fieldset>
											<fieldset>
												<label for="">노드명</label><input type="text"  class="span12">
											</fieldset>
											<fieldset>
												<label for="">TCP/IP</label><input type="text" class="span12">
											</fieldset>
											<fieldset>
												<label for="">서비스</label>
												<select class="span12">
													<option>DNS</option>
												</select>
											</fieldset>
											<fieldset>
												<button type="button" class="btn btn-primary span12" title="Quick Search" onclick="#">검색</button>
											</fieldset>
										</form>
									</div>
								</div>
							</div>
						</div>
						<div class="span6">
							<div class="row-fluid">
								<div class="span12">
									<h4>24시간 이용내역</h4>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<div class="well well-small">
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
									</div>
									<div class="well well-small">
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
						</div>
						<div class="span3">
							<div class="row-fluid">
								<div class="span12">
									<h4>알림 정보</h4>
								</div>
							</div>
							<div class="well well-small">
								<div class="row-fluid">
									<div class="span12">
										나의 알림(2,314) <a href="#">[확인]</a><br/>
										모든 알림(2,314) <a href="#">[확인]</a><br/>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<h4>그래프 검색</h4>
								</div>
							</div>
							<div class="well well-small">
								<div class="row-fluid">
									<div class="span12">
										<form>
											<fieldset>
												<label for="">사용자 그래프</label><input type="text" class="span12">
											</fieldset>
											<fieldset>
												<label for="">자원 그래프</label><input type="text"  class="span12">
											</fieldset>
											<fieldset>
												<button type="button" class="btn btn-primary span12" title="Quick Search" onclick="#">검색</button>
											</fieldset>
										</form>
									</div>
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
