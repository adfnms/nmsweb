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
<script src="<c:url value="/resources/js/requisitions.js" />"></script>

<script type="text/javascript">

var orderBy = "id";

$(document).ready(function(){
	saveAjax(save, "orderBy="+orderBy);
});


function saveNode(){
	
	addNodeIdListRequisitions('',$('#nodeFrm'));
	return;
	window.opener.location.reload();
	window.close();
}

</script>
</head>

<body>
	<form id="nodeFrm">
		<input type="hidden" name="requisitions" id="requisitions">
		<div class='nodeDiv'>
			
			<table class="table table-striped table-hover" id="getNodeList">
			<!-- 노드 리스트 -->	
			</table>
		</div>
	</form>
</body>
<div class="nodeDiv" id='nodeBtnDiv'>
	<button type="button" class="btn btn-primary" id='saveBtn' style="margin-left: 550px;" title="저장" onclick="javascript:saveNode();">저장</button>
</div>	
</html>
