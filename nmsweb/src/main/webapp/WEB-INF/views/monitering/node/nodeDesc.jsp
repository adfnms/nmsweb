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
	<jsp:param value="노드관리" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		getSpecificNode(addNodeDesc,"${nodeId}");
		
	});
	
	function addNodeDesc(jsonObj){
		console.log(jsonObj);
		
		$('#nodeLabel').append("[ "+jsonObj["@label"]+" ]");
	}
</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span10">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value="/index.do" />" />Home</a> <span class="divider">/</span></li>
					<li><a href="<c:url value="/monitering/node/search.do" />" />노드검색</a> <span class="divider">/</span></li></li>
					<li class="active">노드 상세보기</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<div class="row-fluid">
			<div class="span10 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<h4 id="nodeLabel">노드정보</h4>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span5">
				<div class="row-fluid">
					<h5>가용성</h5>
				</div>
				<div class="row-fluid">
					<div class="span12 well well-small">
						<table class="table table-striped">
							<colgroup>
								<col span="span3"/>
								<col span="span3"/>
								<col span="span3"/>
							</colgroup>
							<tr>
								<th>24시간</th>
								<td colspan="2">100.000%</td>
							</tr>
							<tr>
								<th rowspan="5">211.168.0.1</th>
								<td>전체</td>
								<td>100.000%</td>
							</tr>
							<tr>
								<td>http</td>
								<td>100.000%</td>
							</tr>
							<tr>
								<td>http</td>
								<td>100.000%</td>
							</tr>
							<tr>
								<td>http</td>
								<td>100.000%</td>
							</tr>
							<tr>
								<td>http</td>
								<td>모니터링 되지 않음</td>
							</tr>
							<tr>
								<th rowspan="5">211.168.0.1</th>
								<td>전체</td>
								<td>100.000%</td>
							</tr>
							<tr>
								<td>http</td>
								<td>100.000%</td>
							</tr>
							<tr>
								<td>http</td>
								<td>100.000%</td>
							</tr>
							<tr>
								<td>http</td>
								<td>100.000%</td>
							</tr>
							<tr>
								<td>http</td>
								<td>모니터링 되지 않음</td>
							</tr>
						</table>
					</div>	
				</div>
			</div>
			<div class="span5">
			
			</div>
		</div>
		
		
		
		<hr>
	</div>
	<!-- /container -->
</body>
</html>
