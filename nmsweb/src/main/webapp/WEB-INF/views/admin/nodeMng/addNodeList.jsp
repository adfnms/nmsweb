<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script type="text/javascript">

var orderBy = "id";

$(document).ready(function(){
	saveAjax(save, "orderBy="+orderBy);
	paramNodesCallback("orderBy="+orderBy);
	/* rePositionSaveButton();
	$(window).resize(function(){
		rePositionSaveButton();
	}); */
});

function saveNode(){
	window.opener.location.reload();
	window.close();
}

function rePositionSaveButton(){
	var popupWidth = $(".nodeDiv").width();//form의 가로
	var popupHeight = $(".nodeDiv").height();//form의 세로
	
	//var height = popupHeight + ( ($(document).width() -popupWidth) /2 ); 
	$("#saveBtn").css("margin-left",550);
	$("#saveBtn").css("margin-left",550);
	/* $("#sideBarOutageList").css("margin-right",-35);
	$("#sideBarOutageList").css("margin-left",-35);
	$("#sideBarOutageList").css("margin-top",-14); */
}

</script>
</head>

<body>
	<div class='nodeDiv'>
		<form id="nodeFrm">
			<table class="table table-striped table-hover" id="getNodeList">
			<!-- 노드 리스트 -->	
			</table>
		</form>
	</div>
</body>
<div class="nodeDiv" id='nodeBtnDiv'>
	<button type="button" class="btn btn-primary" id='saveBtn' title="저장" onclick="javascript:saveNode();">저장</button>
</div>	
</html>
