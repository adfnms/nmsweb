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
<script src="<c:url value="/resources/js/requisitions.js" />"></script>
<script type="text/javascript">

	$(document).ready(function(){
		getTotalRequisitionsList(setRequisitionsList);
	});
	function sleep(num){	//[1/1000초]
		 var now = new Date();
		   var stop = now.getTime() + num;
		   while(true){
			 now = new Date();
			 if(now.getTime() > stop)return;
		   }
	}
	
	function checkNode(date)
	{
// 		if(date != null)
// 		{
// 			alert('등록된 IP 주소 입니다.');
// 			return;
// 		}
		var ipAddress = $('#addNodeFrm input[name=nodeIpAddress]').val();
		nodeScan(addNodeResult, ipAddress);
	}
	
	function addNode(){
		var ipAddress = $('#addNodeFrm input[name=nodeIpAddress]').val();
		searchNodeFromIpAddress(checkNode,ipAddress);
	}
	
	function addNodeResult(data){
		if(data.result == "success"){
			sleep(3000);
			
			
			addNodeRequisitions('',$('#addNodeFrm input[name=nodeIpAddress]').val(),$('#requisitions').val());
			alert("추가되었습니다.");
			self.close();
		}else{
			alert("추가가 실패 했습니다.");
			self.close();
		}
		
		window.opener.location.reload();
		window.close();
	}
	
	function setRequisitionsList(data){
		$('#getNodeGroup').empty();
		var str = getTableToRequisitionsSelectBoxJsonObj(data);
		$('#getNodeGroup').append(str);
		
	}
	
	
	
</script>
</head>

<body>
	<div>
		<form id="addNodeFrm">
			<div class="row-fluid">
				<h5>노드&nbsp;IP&nbsp;<span class="label label-important">노드&nbsp;추가는&nbsp;다소&nbsp;시간이&nbsp;지연될&nbsp;수&nbsp;있습니다.</span></h5>
				
				<div class='nodeDiv' id="getNodeGroup">
					<select>
					</select>
				</div>
			
				<input type="text" id="nodeIpAddress" name="nodeIpAddress" class="span12"/>
			</div>

			<div class="row-fluid">
				<button type="button" class="btn btn-primary span12" title="등록" onclick="javascript:addNode();">등록</button>
			</div>
		</form>
	</div>
	<!-- /container -->
</body>
</html>
