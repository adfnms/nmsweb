<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.bluecapsystem.nms.define.NMSProperties"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/include/header.jsp">
	<jsp:param value="회원가입" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/js/userMng.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		//전체 리스트 
		getUserListTotal(callbackUseList);

	});

	function callbackUseList(data) {
		var jsonObj = JSON.parse(data);

		var str = "";

		for ( var i in jsonObj.user) {

			str += "<tr>";
			str += "	<td>";
			str += jsonObj.user[i].user_id;
			str += "	</td>";
			str += "	<td>";
			str += jsonObj.user[i].full_name;
			str += "	</td>";
			str += "	<td>";
			str += jsonObj.user[i].user_comments;
			str += "	</td>";
// 			str += "	<td>";
// 			str += jsonObj.user[i].password;
// 			str += "	</td>";
			str += "</tr>";
		}

		$("#userListTable").append(str);
	}
</script>
</head>

<body>
	<div class="container">

		<jsp:include page="/include/menu.jsp" />

		<div class="row-fluid">
			<div class="span9">
				<h2 class="muted">운영관리 > 사용자 관리</h2>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>

		<div class="row-fluid">
			<div class="span9">
				<table class="table table-striped" id="userListTable">
					<colgroup>
						<col class="span2"/>
						<col class="span3"/>
						<col class="span4"/>
					</colgroup>
					<thead>
						<tr>
							<th>사용자 ID</th>
							<th>이름(FullName)</th>
							<th>소개</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<hr>
	</div>
	<!-- /container -->
</body>
</html>
