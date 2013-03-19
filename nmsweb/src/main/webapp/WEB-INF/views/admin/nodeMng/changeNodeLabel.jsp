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
	$(document).ready(function(){
		
		/* Node base info */
		getSpecificNode(addNodeDesc, "${nodeId}");
		
	});
	
	function addNodeDesc(jsonObj){
		
		$('#nodeLabel').val(jsonObj["@label"]);
		
	}
	
	function chgNodeLabel(){
		
		var result = changeNodeLabel("${nodeId}", $('#nodeLabel').val());
		
		if(result == true)
		{
			alert("변경되었습니다.");
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
				<h5>노드&nbsp;라벨&nbsp;변경</h5>
			</div>
			<div class="row-fluid">
				<div class=" well well-small">
					<input type="text" id="nodeLabel" name="nodeLabel" class="span12"/>
				</div> 
			</div>
			<div class="row-fluid">
				<button type="button" class="btn btn-primary span12" title="등록"
					onclick="javascript:chgNodeLabel();">등록</button>
			</div>
		</form>
	</div>
	<!-- /container -->
</body>
</html>
