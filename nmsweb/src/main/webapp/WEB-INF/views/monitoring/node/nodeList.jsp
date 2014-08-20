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
	<jsp:param value="노드목록" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script src="<c:url value="/resources/js/category.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		/* 모든 데이터를 id로 정렬하여 가져온다 */
		getNodeTotalList(addNodeLists, "orderBy=id&limit=0");
		
	});
	
	//callback 함수 jsonObj를 이용 파싱 후 append 노드목록
	function addNodeLists(jsonObj) {
		$('#nodeListTable').empty();
		
		
		var str = getTabletagToSearchJsonObj(jsonObj,"<c:out value='${GROUP_NM}'/>");
				
		$('#nodeListTable').append(str);
// 		$.ajax({
// 			type : 'get',
// 			url : '<c:url value="/menu/showMenu.do" />',
// 			contentType : 'application/json', 
// 			error : function(data) {
// 				alert('사용자 그룹 menu리스트 서비스 실패');
// 			},
// 			success : function(data) {
// 				console.log(data);
				
// 				for(var i = 0; i < data.userList.length; i++)
// 	       		{
// 	        		var	groupName= data.userList[i].groupNm;
// 	       		}
// 				var str = getTabletagToSearchJsonObj(jsonObj,groupName);
				
// 				$('#nodeListTable').append(str);
// 			}
// 		});   	
	}
</script>
</head>

<body>
	<div class="container">
		<jsp:include page="/include/menu.jsp" />
		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb well well-small">
					<li><a href="<c:url value="/index.do" />" >Home</a> <span class="divider">/</span></li>
					<li><a href="<c:url value="/monitoring/nodelist.do" />" >모니터링</a> <span class="divider">/</span></li>
					<li class="active">노드 목록<span class="divider">/</span></li>
					<li><a href="#" onclick="javascript:beforeUrl()">이전 화면</a><span class="divider">/</span></li>
				</ul>
			</div>
			<jsp:include page="/include/sideBar.jsp" />
		</div>
		<form id="searchNodeFrm" name="searchNodeFrm">
			<div class="row-fluid">
				<div class="span12 well well-small" style="height: 38px;">
					<div class="row-fluid">
						<div class="span12">
							<h5 style="margin-top: 0px;">노드&nbsp;목록</h5>
						</div>
					</div>
				</div>
			</div>
		</form>
		<div class="row-fluid">
			<div class="span12 well well-small">
				<div class="row-fluid">
					<div class="span12">
						<table class="table table-striped table-hover" id="nodeListTable"></table>
					</div>
				</div>
			</div>
		</div>
		<hr>
	</div>
	<!-- /container -->
</body>
</html>