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
		getNodeTotalList(getNodeList, null);
	});

	function getNodeList(jsonObj) {
		
		$('#nodeListTable').empty();

		var nodeObj = jsonObj["node"];
		var str = "";
		for ( var i in nodeObj) {
			str += "<tr>";
			str += "	<td><a href='#'>"+nodeObj[i]["@id"]+"</a></td>";
			str += "	<td><a href='#'>"+nodeObj[i]["@label"]+"</a></td>";
			str += "</tr>";
		}
		
		$('#nodeListTable').append(str);
	}
</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span9">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value="/index.do" />" />Home</a> <span
						class="divider">/</span></li>
					<li><a href="#">노드검색</a> <span class="divider">/</span></li>
					<li class="active">중단 상세보기</li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>

		<div class="row-fluid">
			<div class="span9 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<h4>노드 검색</h4>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<div class="row-fluid" id="cateDiv">
							<div class="span12">
								<label class="span2 control-label">노드 명</label>
								<div class="span4 controls">
									<input type="text" />
								</div>
								<label class="span2 control-label">TCP/IP 주소</label>
								<div class="span4 controls">
									<input type="text" />
								</div>
							</div>
						</div>
						<div class="row-fluid" id="cateDiv">
							<div class="span12">
								<label class="span2 control-label">제곻 서비스</label>
								<div class="span10 controls">
									<select class="span12">
										<option></option>
									</select>
								</div>
							</div>
						</div>
						<div class="row-fluid" id="cateDiv">
							<div class="span12">
								<label class="span2 control-label">MAC 주소</label>
								<div class="span4 controls">
									<input type="text" />
								</div>
								<label class="span2 control-label">외부 별명</label>
								<div class="span4 controls">
									<input type="text" />
								</div>
							</div>
						</div>
						<div class="row-fluid" id="cateDiv">
							<div class="span12">
								<label class="span2 control-label">날짜</label>
								<div class="span3 controls">
									<input type="text" class="span12" />
								</div>
								<div class="span1" style="text-align: center;">~</div>
								<div class="span3 controls">
									<input type="text" class="span12" />
								</div>
								<div class="span3">
									<button type="button" class="btn btn-primary span12" title="검색"
										onclick="#">검색</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span9 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<h4>노드 목록</h4>
					</div>
				</div>
				<div class="row-fluid">
					<table class="table table-striped" id="nodeListTable">
						<colgroup>
							<col class="span3" />
							<col class="span6" />
						</colgroup>
						<tr>
							<th>아이디</th>
							<th>노드명</th>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<hr>
	</div>
	<!-- /container -->
</body>
</html>
