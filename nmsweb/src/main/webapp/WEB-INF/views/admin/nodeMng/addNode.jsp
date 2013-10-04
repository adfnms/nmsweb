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
	<jsp:param value="노드등록" name="title" />
	<jsp:param value="Y" name="styleFlag" />
</jsp:include>
<script src="<c:url value="/resources/js/nodes.js" />"></script>
<script type="text/javascript">
	function addNode(){
		
		var ipAddress = $('#addNodeFrm input[name=nodeIpAddress]').val();
		nodeScan(addNodeResult, ipAddress);
		
	}
	
	function addNodeResult(data){
		console.log(data);
		
		if(data.result == "success"){
			alert("추가되었습니다.");
			self.close();
		}else{
			alert("추가가 실패 했습니다.");
			self.close();
		}
		
		window.opener.location.reload();
		window.close();
	}
</script>
</head>

<body>
	<div>
		<form id="addNodeFrm">
			<div class="row-fluid">
				<h5>노드&nbsp;IP&nbsp;<span class="label label-important">노드&nbsp;추가는&nbsp;다소&nbsp;시간이&nbsp;지연될&nbsp;수&nbsp;있습니다.</span></h5>
				<input type="text" id="nodeIpAddress" name="nodeIpAddress" class="span12"/>
			</div>
			<div class="row-fluid">
				<button type="button" class="btn btn-primary span12" title="등록"
					onclick="javascript:addNode();">등록</button>
			</div>
		</form>
	</div>
	<!-- /container -->
</body>
</html>
