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
});

</script>
</head>

<body>
	<div>
		<form id="nodeFrm">
			<table class="table table-striped table-hover" id="getNodeList">
			<!-- 노드 리스트 -->	
			</table>
			<button type="button" class="btn btn-primary" style="margin-left:570px" title="저장" onclick="javascript:savesAjax();">저장</button>
		</form>
	</div>
</body>
</html>
